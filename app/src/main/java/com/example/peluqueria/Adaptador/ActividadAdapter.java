package com.example.peluqueria.Adaptador;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.peluqueria.Model.Actividad;
import com.example.peluqueria.Model.Personal;
import com.example.peluqueria.R;

import java.util.List;

public class ActividadAdapter extends BaseAdapter {

    private Context mContext;
    private List<Actividad> lstActividad;

    public ActividadAdapter(Context mContext, List<Actividad> lstActividad) {
        this.mContext = mContext;
        this.lstActividad = lstActividad;
    }

    @Override
    public int getCount() {
        return lstActividad.size();
    }

    @Override
    public Object getItem(int position) {
        return lstActividad.get(position) ;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = View.inflate(mContext, R.layout.item_actividad,null);
        TextView actividad = (TextView)v.findViewById(R.id.actividad_item);
        TextView fecha = (TextView) v.findViewById(R.id.fecha_item);
        TextView horaInicio = (TextView) v.findViewById(R.id.horaInicio_item);
        TextView horaFIn = (TextView) v.findViewById(R.id.horaFin_item);
        TextView precio = (TextView) v.findViewById(R.id.precio_item);

        actividad.setText(lstActividad.get(position).getDescripcion());
        fecha.setText(lstActividad.get(position).getFecha());
        horaInicio.setText(lstActividad.get(position).getHora_inicio());
        horaFIn.setText(lstActividad.get(position).getHora_fin());
        precio.setText(String.valueOf( lstActividad.get(position).getPrecio()));


        return v;
    }
}
