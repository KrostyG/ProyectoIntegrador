package com.example.BancoAutenticacion;

import com.example.BancoAutenticacion.Entidad.Usuario;
import com.example.BancoAutenticacion.repositorio.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BancoAutenticacionApplication {
	public static void main(String[] args) {
		SpringApplication.run(BancoAutenticacionApplication.class, args);
		/*UsuarioRepositorio usuarioRepositorio = new UsuarioRepositorio();
		Usuario usuario = new Usuario();
		usuario.setEmail("cristopher8@gmail.com");
		usuario.setContrasena("Contrasena1998#");
		System.out.println(usuario.getContrasena());
		System.out.println(usuario.getEmail());
		usuarioRepositorio.validarContraseña(usuario.getContrasena());
		usuarioRepositorio.ValidarEmail(usuario.getEmail());*/
	}

}
