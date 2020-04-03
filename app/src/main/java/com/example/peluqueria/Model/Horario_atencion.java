package com.example.peluqueria.Model;

public class Horario_atencion {
    private int id_horario_atencion;
    private Personal personal;
    private Actividad actividad;
    private Horario horario;

    public Horario_atencion() {
    }

    public Horario_atencion(int id_horario_atencion, Personal personal, Actividad actividad, Horario horario) {
        this.id_horario_atencion = id_horario_atencion;
        this.personal = personal;
        this.actividad = actividad;
        this.horario = horario;
    }

    public int getId_horario_atencion() {
        return id_horario_atencion;
    }

    public void setId_horario_atencion(int id_horario_atencion) {
        this.id_horario_atencion = id_horario_atencion;
    }

    public Personal getPersonal() {
        return personal;
    }

    public void setPersonal(Personal personal) {
        this.personal = personal;
    }

    public Actividad getActividad() {
        return actividad;
    }

    public void setActividad(Actividad actividad) {
        this.actividad = actividad;
    }

    public Horario getHorario() {
        return horario;
    }

    public void setHorario(Horario horario) {
        this.horario = horario;
    }
}
