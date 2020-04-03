package com.example.peluqueria.ModelListas;

import com.example.peluqueria.Model.Personal;

import java.util.List;

public class PersonalLst {
    private List<Personal> lstPersonal;

    public PersonalLst() {
    }

    public PersonalLst(List<Personal> lstPersonal) {
        this.lstPersonal = lstPersonal;
    }

    public List<Personal> getLstPersonal() {
        return lstPersonal;
    }

    public void setLstPersonal(List<Personal> lstPersonal) {
        this.lstPersonal = lstPersonal;
    }
}
