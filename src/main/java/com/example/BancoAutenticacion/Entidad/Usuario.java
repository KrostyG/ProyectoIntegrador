package com.example.BancoAutenticacion.Entidad;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "usuarios")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String nombre;
    private String email;
    private String numeroTelefono;
    private int edad;
    private String contrasena;
    private boolean bloqueado = false;

    public Usuario() {
    }

    public Usuario(Integer id, String nombre, String email, String numeroTelefono, int edad, String contrasena, boolean bloqueado) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.numeroTelefono = numeroTelefono;
        this.edad = edad;
        this.contrasena = contrasena;
        this.bloqueado = bloqueado;
    }

    public Usuario(Integer id, String nombre, String email, String numeroTelefono, int edad, String contrasena) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.numeroTelefono = numeroTelefono;
        this.edad = edad;
        this.contrasena = contrasena;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumeroTelefono() {
        return numeroTelefono;
    }

    public void setNumeroTelefono(String numeroTelefono) {
        this.numeroTelefono = numeroTelefono;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public boolean isBloqueado() {
        return bloqueado;
    }

    public void setBloqueado(boolean bloqueado) {
        this.bloqueado = bloqueado;
    }
}
