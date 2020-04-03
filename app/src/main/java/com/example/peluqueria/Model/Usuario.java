package com.example.peluqueria.Model;

public class Usuario {
    private int id_usuario;
    private String usuario;
    private String clave;
    private int rol_id;
    private String estado;

    public Usuario(int id_usuario, String usuario, String clave, int rol_id, String estado) {
        this.id_usuario = id_usuario;
        this.usuario = usuario;
        this.clave = clave;
        this.rol_id = rol_id;
        this.estado = estado;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public int getRol_id() {
        return rol_id;
    }

    public void setRol_id(int rol_id) {
        this.rol_id = rol_id;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
