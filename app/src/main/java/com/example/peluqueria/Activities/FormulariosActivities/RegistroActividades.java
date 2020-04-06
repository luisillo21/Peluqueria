package com.example.peluqueria.Activities.FormulariosActivities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
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

import com.example.peluqueria.Activities.MainActivity;
import com.example.peluqueria.Activities.activity_actividades;
import com.example.peluqueria.DAO.PeluqueriaDao;
import com.example.peluqueria.Model.Actividad;
import com.example.peluqueria.Model.Personal;
import com.example.peluqueria.R;

public class RegistroActividades extends AppCompatActivity {

    EditText etActividad,etFecha,etHorainicio,etHoraFin,etPrecio;
    Button btn_registrar_actividad;
    PeluqueriaDao dao;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_registro_actividades);
        etActividad =  (EditText) findViewById(R.id.etActividad);
        etFecha =  (EditText) findViewById(R.id.etFecha);
        etHorainicio =  (EditText) findViewById(R.id.etHora_inicio);
        etHoraFin =  (EditText) findViewById(R.id.ethora_fin);
        etPrecio =  (EditText) findViewById(R.id.etprecio);
        btn_registrar_actividad =  (Button) findViewById(R.id.btn_registrar_actividad);

        btn_registrar_actividad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dao = new PeluqueriaDao();
                Actividad obj = new Actividad();

                if (etActividad.getText().toString().isEmpty()||
                    etFecha.getText().toString().isEmpty()||
                    etHorainicio.getText().toString().isEmpty()||
                    etHoraFin.getText().toString().isEmpty()|| etPrecio.getText().toString().isEmpty()){

                    Toast.makeText(RegistroActividades.this,"Por favor llene todos los campos",Toast.LENGTH_LONG).show();
                }else{
                    obj.setDescripcion(etActividad.getText().toString());
                    obj.setFecha(etFecha.getText().toString());
                    obj.setHora_inicio(etHorainicio.getText().toString());
                    obj.setHora_fin(etHoraFin.getText().toString());
                    obj.setPrecio(Float.valueOf(etPrecio.getText().toString()));
                    dao.guardar_Actividad(obj,RegistroActividades.this);
                }
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
                preferences = getSharedPreferences("datos_usuario", Context.MODE_PRIVATE);
                preferences.edit().clear().apply();
                Intent intent3 = new Intent(RegistroActividades.this, MainActivity.class);
                startActivity(intent3);
                finish();
                return true;

            case R.id.it_asignar_actividades:
                return true;

            case R.id.it_guardar_actividades:
                Intent intent7= new Intent(RegistroActividades.this, RegistroActividades.class);
                startActivity(intent7);
                finish();
                return true;

            case R.id.it_actividades:
                Intent intent6 = new Intent(RegistroActividades.this, activity_actividades.class);
                startActivity(intent6);
                finish();
                return true;

            case R.id.it_personal:
                Intent intent4 = new Intent(RegistroActividades.this, ResgistroPersonalActivity.class);
                startActivity(intent4);
                finish();
                return true;

            case R.id.it_usuario:
                Intent intent = new Intent(RegistroActividades.this,RegistroActivity.class);
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
