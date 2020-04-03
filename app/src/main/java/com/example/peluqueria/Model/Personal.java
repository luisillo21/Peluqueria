package com.example.peluqueria.Model;

public class Personal {

    private String nombre;
    private int edad;
    private String  cedula;
    private String estado;

    public Personal() {
    }

    public Personal(String nombre, int edad, String cedula, String estado) {
        this.nombre = nombre;
        this.edad = edad;
        this.cedula = cedula;
        this.estado = estado;
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
