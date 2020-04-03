package com.example.peluqueria.Model;

public class Rol {
    private int id_rol;
    private String descripcion;
    private String estado;

    public Rol() {
    }

    public Rol(int id_rol, String descripcion, String estado) {

        this.id_rol = id_rol;
        this.descripcion = descripcion;
        this.estado = estado;
    }

    public int getId_rol() {
        return id_rol;
    }

    public void setId_rol(int id_rol) {
        this.id_rol = id_rol;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
