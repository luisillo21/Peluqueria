package com.example.peluqueria.Model;

import java.sql.Date;
import java.sql.Time;

public class Horario {
    private int id_horario;
    private Date fecha;
    private Time hora_inicio;
    private Time hora_fin;
    private String estado;

    public Horario() {
    }

    public Horario(int id_horario, Date fecha, Time hora_inicio, Time hora_fin, String estado) {
        this.id_horario = id_horario;
        this.fecha = fecha;
        this.hora_inicio = hora_inicio;
        this.hora_fin = hora_fin;
        this.estado = estado;
    }

    public int getId_horario() {
        return id_horario;
    }

    public void setId_horario(int id_horario) {
        this.id_horario = id_horario;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Time getHora_inicio() {
        return hora_inicio;
    }

    public void setHora_inicio(Time hora_inicio) {
        this.hora_inicio = hora_inicio;
    }

    public Time getHora_fin() {
        return hora_fin;
    }

    public void setHora_fin(Time hora_fin) {
        this.hora_fin = hora_fin;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
