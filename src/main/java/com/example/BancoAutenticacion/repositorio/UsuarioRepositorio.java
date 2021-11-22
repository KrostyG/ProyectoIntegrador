package com.example.BancoAutenticacion.repositorio;

import com.example.BancoAutenticacion.Entidad.Hash;
import com.example.BancoAutenticacion.Entidad.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Repository
public class UsuarioRepositorio {
    @Autowired
    UsurioRepositorioDAO usurioRepositorioDAO;

    public boolean agregarUsuario(Usuario usuario){
        try{
            usuario.setContrasena(Hash.md5(usuario.getContrasena()));
            usurioRepositorioDAO.save(usuario);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public boolean esUsuarioExistente(String email){
        Optional usuarioEncontrado = usurioRepositorioDAO.encontrarUsuarioPorEmail(email);
        if(usuarioEncontrado.isPresent()){
            return true;
        }
        else {
            return false;
        }
    }

    public boolean esMayorDeEdad(Integer edad){
        if(edad <= 17){
            return false;
        }
        return true;
    }

    public boolean tieneDiezDigitosElTelefono(String telefono){
        if(telefono.length() != 10){
            return false;
        }
        return true;
    }

    public boolean ValidarEmail(String email){
        Pattern pattern = Pattern
                .compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        Matcher mather = pattern.matcher(email);
        if (mather.find() == true) {
            System.out.println("El email ingresado es válido.");
            return true;
        } else {
            System.out.println("El email ingresado es inválido.");
            return false;
        }
    }

    public boolean validarContraseña(String contrasena){
        Pattern pattern=Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$");

        Matcher matcher = pattern.matcher(contrasena);
        if (matcher.find() == true) {
            System.out.println("El contrasena ingresada es válida.");
            return true;
        } else {
            System.out.println("El la contraseba ingresada es inválida.");
            return false;
        }
    }
}
