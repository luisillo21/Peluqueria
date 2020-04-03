package com.example.peluqueria.Model;

public class Actividad {
    private  String descripcion;
    private float precio;
    private String estado;

    public Actividad() {
    }

    public Actividad(String descripcion, float precio, String estado) {
        this.descripcion = descripcion;
        this.precio = precio;
        this.estado = estado;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
