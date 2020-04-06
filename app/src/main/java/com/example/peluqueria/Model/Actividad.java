package com.example.peluqueria.Model;

import java.io.Serializable;

public class Actividad implements Serializable {
    private int id_actividad;
    private String descripcion;
    private String fecha;
    private String hora_inicio;
    private String hora_fin;
    private float precio;
    private String estado;

    public Actividad() {
    }

    public Actividad(String descripcion) {
        this.descripcion = descripcion;
    }

    public Actividad(int id_actividad, String descripcion, String fecha, String hora_inicio, String hora_fin, float precio, String estado) {
        this.id_actividad = id_actividad;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.hora_inicio = hora_inicio;
        this.hora_fin = hora_fin;
        this.precio = precio;
        this.estado = estado;
    }

    public int getId_actividad() {
        return id_actividad;
    }

    public void setId_actividad(int id_actividad) {
        this.id_actividad = id_actividad;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getHora_inicio() {
        return hora_inicio;
    }

    public void setHora_inicio(String hora_inicio) {
        this.hora_inicio = hora_inicio;
    }

    public String getHora_fin() {
        return hora_fin;
    }

    public void setHora_fin(String hora_fin) {
        this.hora_fin = hora_fin;
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
