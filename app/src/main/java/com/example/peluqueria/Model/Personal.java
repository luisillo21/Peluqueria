package com.example.peluqueria.Model;

import java.io.Serializable;

public class Personal implements Serializable{
    private int id_personal;
    private String nombre;
    private int edad;
    private String  cedula;
    private String telefono;
    private String estado;

    public Personal() {
    }

    public Personal(int id_personal, String nombre, int edad, String cedula, String telefono, String estado) {
        this.id_personal = id_personal;
        this.nombre = nombre;
        this.edad = edad;
        this.cedula = cedula;
        this.telefono = telefono;
        this.estado = estado;
    }

    public int getId_personal() {
        return id_personal;
    }

    public void setId_personal(int id_personal) {
        this.id_personal = id_personal;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
