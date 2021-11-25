package com.example.BancoAutenticacion.controlador;

import com.example.BancoAutenticacion.Entidad.Usuario;
import com.example.BancoAutenticacion.configuracion.ResourceNotFoundException;
import com.example.BancoAutenticacion.repositorio.UsurioRepositorioDAO;
import com.example.BancoAutenticacion.servicio.UsuarioServicio;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Getter @Setter
@RestController
@RequestMapping("/usuarios")
public class UsuarioControlador {
    private int bloquear = 0;
    private  Optional<Usuario> usuarioOptional;
    private  Boolean estaLogeado = false;

    @Autowired
    UsuarioServicio usuarioServicio;
    @Autowired
    UsurioRepositorioDAO usurioRepositorioDAO;

    @PostMapping("/registro")
    public ResponseEntity<String> agregarCliente(@RequestBody Usuario usuario) {
        if (!usuarioServicio.esUsuarioExistente(usuario.getEmail())) {
            if (usuarioServicio.esMayorDeEdad(usuario.getEdad())) {
                if (usuarioServicio.tieneDiezDigitosElTelefono(usuario.getNumeroTelefono())) {
                    if(usuarioServicio.ValidarEmail(usuario.getEmail())){
                        if (usuarioServicio.validarContraseña(usuario.getContrasena())){
                            usuarioServicio.agregarUsuario(usuario);
                            return new ResponseEntity<>("Usuario agregado", HttpStatus.OK);
                        }
                    }
                }
            }
        }
        return new ResponseEntity<>("No se pudo registrar", HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/login")
    public ResponseEntity<Usuario> login(@RequestBody Usuario usuario) throws noExisteUsuarioExcepcion, LogeoCorrectoUsuario, UsuarioBloqueado, ContrasenaEquivocada{
         usuarioOptional  = usuarioServicio.buscarPorEmail(usuario);
        if(usuarioOptional.isEmpty()){
            throw new noExisteUsuarioExcepcion();
        }
        if(usuarioOptional.get().isBloqueado()){
            estaLogeado = true;
            throw new UsuarioBloqueado();
        }
        String mailBody = usuario.getEmail();
        String contrasenaBody = usuario.getContrasena();

        if(usuarioServicio.login(usuarioOptional,mailBody, contrasenaBody) && bloquear < 3){
            estaLogeado = true;
            throw new LogeoCorrectoUsuario();
        }
        if(usuarioServicio.login(usuarioOptional,mailBody, contrasenaBody) && bloquear < 3){
            throw new LogeoCorrectoUsuario();

        }
        bloquear = bloquear + 1;
        if (bloquear == 4){
           usuarioOptional.get().setBloqueado(true);
           Usuario usuario1 = usuarioOptional.get();
           usuarioServicio.actualizarBloqueado(usuario1);
           throw new UsuarioBloqueado();
        }
        throw new ContrasenaEquivocada();
    }

    @PutMapping("/desbloquearUsuario/{numeroTelefono}")
    public ResponseEntity<Usuario>desbloquearUsuario(@PathVariable(value = "numeroTelefono")String numeroTelefono) throws ResourceNotFoundException {
        Usuario usuario1 = usurioRepositorioDAO.encontrarUsuarioPorNumeroTelefono(numeroTelefono)
                .orElseThrow(()->new ResourceNotFoundException("Usuario no encontrado con este telefono "+numeroTelefono));
        usuario1.setBloqueado(false);
        final Usuario usuarioActualizado = usurioRepositorioDAO.save(usuario1);
        return ResponseEntity.ok(usuarioActualizado);
    }

    //Buscar por ID
    @GetMapping("/enviarid")
    public Integer getUsuarioPorId() throws noExisteUsuarioExcepcion {
        usuarioOptional.get().getId();
        if(!estaLogeado){
            throw new noExisteUsuarioExcepcion();
        }
        return usuarioOptional.get().getId();
    }


}
