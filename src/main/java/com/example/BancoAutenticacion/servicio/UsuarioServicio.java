package com.example.BancoAutenticacion.servicio;

import com.example.BancoAutenticacion.Entidad.Usuario;
import com.example.BancoAutenticacion.repositorio.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioServicio {
    @Autowired
    UsuarioRepositorio usuarioRepositorio;

    public boolean agregarUsuario(Usuario usuario){
        return usuarioRepositorio.agregarUsuario(usuario);
    }

    public boolean tieneDiezDigitosElTelefono(String telefono){
        return usuarioRepositorio.tieneDiezDigitosElTelefono(telefono);
    }

    public boolean esMayorDeEdad(Integer edad){
        return usuarioRepositorio.esMayorDeEdad(edad);
    }

    public boolean esUsuarioExistente(String email){
        return usuarioRepositorio.esUsuarioExistente(email);
    }

    public boolean ValidarEmail(String email){
        return usuarioRepositorio.ValidarEmail(email);
    }

    public boolean validarContraseña(String contrasena){
        return usuarioRepositorio.validarContraseña(contrasena);
    }

    public Optional<Usuario> buscarPorEmail(Usuario usuario) {
       String email=  usuario.getEmail();
        return usuarioRepositorio.encontrarPorEmail(email);
    }

    public boolean login(Optional<Usuario> usuarioOptional, String mailBody, String contrasenaBody) {
        if (usuarioRepositorio.login(usuarioOptional ,mailBody,contrasenaBody)){
            return true;
        }
       return false;

    }

    public void actualizarBloqueado(Usuario usuarioOptional) {
        usuarioRepositorio.actualizarBloqueado(usuarioOptional);
    }
}
