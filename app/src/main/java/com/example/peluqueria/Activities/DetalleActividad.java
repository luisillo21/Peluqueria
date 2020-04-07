package com.example.peluqueria.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.peluqueria.Activities.FormulariosActivities.AsignarActividadActivity;
import com.example.peluqueria.Activities.FormulariosActivities.RegistroActividades;
import com.example.peluqueria.Activities.FormulariosActivities.RegistroActivity;
import com.example.peluqueria.DAO.PeluqueriaDao;
import com.example.peluqueria.Model.Actividad;
import com.example.peluqueria.Model.Personal;
import com.example.peluqueria.R;

public class DetalleActividad extends AppCompatActivity {

    PeluqueriaDao dao;
    Button btn_editar_actividad,btn_eliminar_actividad;
    EditText actividad,fecha,hora_inicio,hora_fin,precio;
    Actividad EdObj;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_actividad);

        final Bundle objeto = getIntent().getExtras();
        EdObj = new Actividad();

        if (objeto != null){
            EdObj = (Actividad) objeto.getSerializable("actividad");
        }

        actividad = (EditText)findViewById(R.id.editarActividad);
        fecha = (EditText)findViewById(R.id.editarFecha);
        hora_inicio = (EditText)findViewById(R.id.editarHoraInicio);
        hora_fin = (EditText)findViewById(R.id.editarHoraFin);
        precio = (EditText)findViewById(R.id.editarPrecio);

        actividad.setText(EdObj.getDescripcion().toString());
        fecha.setText(EdObj.getFecha().toString());
        hora_inicio.setText(EdObj.getHora_inicio().toString());
        hora_fin.setText(EdObj.getHora_fin().toString());
        float var = EdObj.getPrecio();
        String var_string = String.valueOf(var);
        precio.setText(var_string);


        btn_editar_actividad = (Button)findViewById(R.id.btn_editar_actividad);
        btn_eliminar_actividad = (Button)findViewById(R.id.btn_eliminar_actividad);

        btn_editar_actividad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (actividad.getText().toString().isEmpty()||fecha.getText().toString().isEmpty()||hora_inicio.getText().toString().isEmpty()||hora_fin.getText().toString().isEmpty()||
                    precio.getText().toString().isEmpty()){
                    Toast.makeText(DetalleActividad.this,"Llene todos los campos",Toast.LENGTH_LONG).show();
                }else{
                    dao = new PeluqueriaDao();
                    Actividad obj =  new Actividad();
                    obj.setId_actividad(EdObj.getId_actividad());
                    obj.setDescripcion(actividad.getText().toString());
                    obj.setFecha(fecha.getText().toString());
                    obj.setHora_inicio(hora_inicio.getText().toString());
                    obj.setHora_fin(hora_fin.getText().toString());
                    String precios = precio.getText().toString();
                    float precio_float = Float.parseFloat(precios);
                    obj.setPrecio(precio_float);
                    dao.update_Actividad(obj,DetalleActividad.this);
                    Toast.makeText(DetalleActividad.this,"Cambios guardados",Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(DetalleActividad.this,activity_actividades.class);
                    startActivity(intent);
                    finish();
                }
            }
        });

        btn_eliminar_actividad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dao = new PeluqueriaDao();
                dao.Eliminar_Actividad(EdObj.getId_actividad(),DetalleActividad.this);
                Toast.makeText(DetalleActividad.this,"Datos eliminados",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(DetalleActividad.this,activity_actividades.class);
                startActivity(intent);
                finish();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        switch (item.getItemId()){
            case R.id.it_cerrar_sesion:
                SharedPreferences preferences;
                preferences = getSharedPreferences("datos_usuario",MODE_PRIVATE);
                preferences.edit().clear().apply();
                Intent intent3 = new Intent(DetalleActividad.this,MainActivity.class);
                startActivity(intent3);
                finish();
                return true;

            case R.id.it_asignar_actividades:
                Intent intent9 = new Intent(DetalleActividad.this, AsignarActividadActivity.class);
                startActivity(intent9);
                finish();
                return true;

            case R.id.it_personal:
                Intent intent4 = new Intent(DetalleActividad.this,InicioActivity.class);
                startActivity(intent4);
                finish();
                return true;

            case R.id.it_guardar_actividades:
                Intent intent7= new Intent(DetalleActividad.this, RegistroActividades.class);
                startActivity(intent7);
                finish();
                return true;

            case R.id.it_actividades:
                Intent intent6 = new Intent(DetalleActividad.this,activity_actividades.class);
                startActivity(intent6);
                finish();
                return true;

            case R.id.it_usuario:
                Intent intent = new Intent(DetalleActividad.this, RegistroActivity.class);
                startActivity(intent);
                finish();
                return true;

            case R.id.it_salir:
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);

        }

    }


}
