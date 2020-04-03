package com.example.peluqueria.ModelListas;

import com.example.peluqueria.Model.Usuario;

import java.util.List;

public class UsuarioLst {

    private List<Usuario> lstUsuario;

    public UsuarioLst(List<Usuario> lstUsuario) {
        this.lstUsuario = lstUsuario;
    }

    public UsuarioLst() {
    }

    public List<Usuario> getLstUsuario() {
        return lstUsuario;
    }

    public void setLstUsuario(List<Usuario> lstUsuario) {
        this.lstUsuario = lstUsuario;
    }
}
