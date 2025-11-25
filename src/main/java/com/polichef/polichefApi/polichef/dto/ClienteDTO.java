package com.polichef.polichefApi.polichef.dto;

import java.time.LocalDate;
import java.util.List;

public class ClienteDTO {
    private Integer dni;
    private String nombre;
    private String apellido;
    private String contrasena;
    private LocalDate fechaNacimiento;
    private String email;
    private String telefono;
    private List<Integer> preferenciasAlimenticias;

    // Constructors
    public ClienteDTO() {}

    // Getters and Setters
    public Integer getDni() { return dni; }
    public void setDni(Integer dni) { this.dni = dni; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getApellido() { return apellido; }
    public void setApellido(String apellido) { this.apellido = apellido; }

    public String getContrasena() { return contrasena; }
    public void setContrasena(String contrasena) { this.contrasena = contrasena; }

    public LocalDate getFechaNacimiento() { return fechaNacimiento; }
    public void setFechaNacimiento(LocalDate fechaNacimiento) { this.fechaNacimiento = fechaNacimiento; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public List<Integer> getPreferenciasAlimenticias() { return preferenciasAlimenticias; }
    public void setPreferenciasAlimenticias(List<Integer> preferenciasAlimenticias) {
        this.preferenciasAlimenticias = preferenciasAlimenticias;
    }
}