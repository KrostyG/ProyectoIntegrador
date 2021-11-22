package com.example.BancoAutenticacion.repositorio;

import com.example.BancoAutenticacion.Entidad.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Repository
public class UsuarioRepositorio {

    @Autowired
    UsurioRepositorioDAO usurioRepositorioDAO;

    public void ValidarEmail(String email){
        Pattern pattern = Pattern
                .compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        Matcher mather = pattern.matcher(email);
        if (mather.find() == true) {
            System.out.println("El email ingresado es válido.");
        } else {
            System.out.println("El email ingresado es inválido.");
        }
    }

    public void validarContraseña(String contrasena){
    Pattern pattern=Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$");

    Matcher matcher = pattern.matcher(contrasena);
        if (matcher.find() == true) {
            System.out.println("El contrasena ingresada es válida.");
        } else {
            System.out.println("El la contraseba ingresada es inválida.");
        }
    }
}
