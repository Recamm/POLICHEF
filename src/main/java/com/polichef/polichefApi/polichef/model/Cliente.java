package com.polichef.polichefApi.polichef.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "Cliente")
@JsonIgnoreProperties({"pedidos", "preferencias", "notificaciones"})
public class Cliente {
    @Id
    @Column(name = "dni")
    private Integer dni;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String nombre;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String apellido;

    @Column(nullable = false, columnDefinition = "TEXT")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String contrasena;

    @Column(nullable = false)
    private LocalDate fechaNacimiento;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String email;

    @Column(columnDefinition = "TEXT")
    private String telefono;

    @Column(nullable = false)
    private LocalDate cuentaCreada;

    @Column(nullable = false)
    private Integer puntos = 0;

    @OneToMany(mappedBy = "cliente")
    private List<Pedido> pedidos;

    @OneToMany(mappedBy = "cliente")
    private List<PreferenciasAlimenticiasDelCliente> preferencias;

    @OneToMany(mappedBy = "cliente")
    private List<Notificacion> notificaciones;

    public Cliente() {}

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

    public LocalDate getCuentaCreada() { return cuentaCreada; }
    public void setCuentaCreada(LocalDate cuentaCreada) { this.cuentaCreada = cuentaCreada; }

    public Integer getPuntos() { return puntos; }
    public void setPuntos(Integer puntos) { this.puntos = puntos; }

    public List<Pedido> getPedidos() { return pedidos; }
    public void setPedidos(List<Pedido> pedidos) { this.pedidos = pedidos; }

    public List<PreferenciasAlimenticiasDelCliente> getPreferencias() { return preferencias; }
    public void setPreferencias(List<PreferenciasAlimenticiasDelCliente> preferencias) {
        this.preferencias = preferencias;
    }

    public List<Notificacion> getNotificaciones() { return notificaciones; }
    public void setNotificaciones(List<Notificacion> notificaciones) {
        this.notificaciones = notificaciones;
    }
}