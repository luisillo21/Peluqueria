package com.example.peluqueria.Activities;

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

import com.example.peluqueria.Activities.FormulariosActivities.RegistroActividades;
import com.example.peluqueria.Activities.FormulariosActivities.RegistroActivity;
import com.example.peluqueria.Activities.FormulariosActivities.ResgistroPersonalActivity;
import com.example.peluqueria.DAO.PeluqueriaDao;
import com.example.peluqueria.Model.Personal;
import com.example.peluqueria.R;

public class DetallePersonal extends AppCompatActivity {
    PeluqueriaDao dao;
    Button btn_editar_personal,btn_volver_lista,btn_eliminar_personal;
    EditText nombre_editar,telefono_editar,cedula_editar,edad_editar;
    Personal personal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_personal);
        final Bundle objeto = getIntent().getExtras();
        personal = new Personal();
        if (objeto != null){
            personal = (Personal) objeto.getSerializable("personal");
        }

        btn_volver_lista = (Button)findViewById(R.id.btn_volver_lista);
        btn_editar_personal = (Button)findViewById(R.id.btn_editar_personal);
        btn_eliminar_personal = (Button)findViewById(R.id.btn_eliminar_personal);
        nombre_editar = (EditText)findViewById(R.id.editar_nombre);
        cedula_editar = (EditText)findViewById(R.id.editar_cedula);
        telefono_editar = (EditText)findViewById(R.id.editar_telefono);
        edad_editar = (EditText)findViewById(R.id.editar_edad);
        nombre_editar.setText(personal.getNombre());
        cedula_editar.setText(personal.getCedula());
        telefono_editar.setText(personal.getTelefono());
        edad_editar.setText(String.valueOf(personal.getEdad())  );




        btn_editar_personal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dao = new PeluqueriaDao();
                Personal obj =  new Personal();
                obj.setId_personal(personal.getId_personal());
                obj.setNombre(nombre_editar.getText().toString());
                obj.setCedula(cedula_editar.getText().toString());
                String edad = edad_editar.getText().toString();
                int edad_int = Integer.parseInt(edad);
                obj.setEdad(edad_int);
                obj.setTelefono(telefono_editar.getText().toString());
                dao.update_Personal(obj,DetallePersonal.this);
                Intent intent = new Intent(DetallePersonal.this,Success_splash.class);
                startActivity(intent);
                finish();
            }
        });


        btn_volver_lista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetallePersonal.this,InicioActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btn_eliminar_personal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dao = new PeluqueriaDao();
                dao.Eliminar_Personal(personal.getId_personal(),DetallePersonal.this);
                Intent intent = new Intent(DetallePersonal.this,Success_splash.class);
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
                preferences = getSharedPreferences("datos_usuario", Context.MODE_PRIVATE);
                preferences.edit().clear().apply();
                Intent intent3 = new Intent(DetallePersonal.this,MainActivity.class);
                startActivity(intent3);
                finish();
                return true;

            case R.id.it_asignar_actividades:
                return true;

            case R.id.it_usuario:
                Intent intent = new Intent(DetallePersonal.this, RegistroActivity.class);
                startActivity(intent);
                finish();
                return true;

            case R.id.it_guardar_actividades:
                Intent intent7= new Intent(DetallePersonal.this, RegistroActividades.class);
                startActivity(intent7);
                finish();
                return true;

            case R.id.it_actividades:
                Intent intent6 = new Intent(DetallePersonal.this,activity_actividades.class);
                startActivity(intent6);
                finish();
                return true;

            case R.id.it_personal:
                Intent intent4 = new Intent(DetallePersonal.this, ResgistroPersonalActivity.class);
                startActivity(intent4);
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
