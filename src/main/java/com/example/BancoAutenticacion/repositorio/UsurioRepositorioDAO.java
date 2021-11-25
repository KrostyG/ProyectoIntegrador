package com.example.BancoAutenticacion.repositorio;

import com.example.BancoAutenticacion.Entidad.Usuario;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UsurioRepositorioDAO extends CrudRepository<Usuario, Integer> {

    @Query(value = "select u from Usuario u where u.email = :email")
    Optional<Usuario> encontrarUsuarioPorEmail(String email);

    @Query(value = "select u from Usuario u where u.numeroTelefono = :numeroTelefono")
    Optional<Usuario> encontrarUsuarioPorNumeroTelefono(String numeroTelefono);
}
