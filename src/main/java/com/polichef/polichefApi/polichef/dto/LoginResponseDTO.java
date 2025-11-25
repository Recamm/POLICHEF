package com.polichef.polichefApi.polichef.dto;

public class LoginResponseDTO {
    private boolean success;
    private String message;
    private Integer dni;
    private String nombre;
    private String apellido;

    public LoginResponseDTO() {}

    public LoginResponseDTO(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public boolean isSuccess() { return success; }
    public void setSuccess(boolean success) { this.success = success; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public Integer getDni() { return dni; }
    public void setDni(Integer dni) { this.dni = dni; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getApellido() { return apellido; }
    public void setApellido(String apellido) { this.apellido = apellido; }
}