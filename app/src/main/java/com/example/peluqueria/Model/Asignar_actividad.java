package com.example.peluqueria.Model;

import java.io.Serializable;

public class Asignar_actividad implements Serializable {
    private int idasignar_actividad;
    private int id_personal;
    private int id_actividad;


    public Asignar_actividad(int idasignar_actividad, int id_personal, int id_actividad) {
        this.idasignar_actividad = idasignar_actividad;
        this.id_personal = id_personal;
        this.id_actividad = id_actividad;
    }

    public Asignar_actividad() {
    }

    public int getIdasignar_actividad() {
        return idasignar_actividad;
    }

    public void setIdasignar_actividad(int idasignar_actividad) {
        this.idasignar_actividad = idasignar_actividad;
    }

    public int getId_personal() {
        return id_personal;
    }

    public void setId_personal(int id_personal) {
        this.id_personal = id_personal;
    }

    public int getId_actividad() {
        return id_actividad;
    }

    public void setId_actividad(int id_actividad) {
        this.id_actividad = id_actividad;
    }
}
