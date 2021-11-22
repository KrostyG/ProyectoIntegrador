package com.example.BancoAutenticacion.controlador;

import com.example.BancoAutenticacion.Entidad.Usuario;
import com.example.BancoAutenticacion.servicio.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
public class UsuarioControlador {
    @Autowired
    UsuarioServicio usuarioServicio;

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
}
