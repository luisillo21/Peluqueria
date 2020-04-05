package com.example.peluqueria.Adaptador;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.peluqueria.Model.Personal;
import com.example.peluqueria.R;

import java.util.List;

public class PersonalAdapter extends BaseAdapter {

    private Context mContext;
    private  List<Personal> lstPersonal;

    public PersonalAdapter(Context mContext, List<Personal> lstPersonal) {
        this.mContext = mContext;
        this.lstPersonal = lstPersonal;
    }

    @Override
    public int getCount() {
        return lstPersonal.size();
    }

    @Override
    public Object getItem(int position) {
        return lstPersonal.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = View.inflate(mContext, R.layout.item_personal,null);
        TextView id_personal = (TextView)v.findViewById(R.id.personal_item);
        TextView nombre = (TextView) v.findViewById(R.id.nombre_item);
        TextView cedula = (TextView) v.findViewById(R.id.cedula_item);
        TextView telefono = (TextView) v.findViewById(R.id.telefono_item);
        TextView edad = (TextView) v.findViewById(R.id.edad_item);
        id_personal.setText(String.valueOf(lstPersonal.get(position).getId_personal()));
        nombre.setText(lstPersonal.get(position).getNombre());
        cedula.setText(lstPersonal.get(position).getCedula());
        telefono.setText(lstPersonal.get(position).getTelefono());
        edad.setText(String.valueOf( lstPersonal.get(position).getEdad()));
        return v;
    }
}
