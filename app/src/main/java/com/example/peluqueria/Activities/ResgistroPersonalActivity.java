package com.example.peluqueria.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.peluqueria.DAO.PeluqueriaDao;
import com.example.peluqueria.Model.Personal;
import com.example.peluqueria.R;

public class ResgistroPersonalActivity extends AppCompatActivity {

    Button btn_registrar_personal,btn_volver_inicio;

    EditText nombre,telefono,cedula,edad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resgistro_personal);
        btn_registrar_personal = (Button) findViewById(R.id.btn_registrar_personal);
        btn_volver_inicio = (Button)   findViewById(R.id.btn_volver_inicio);
        nombre = (EditText) findViewById(R.id.nombre);
        cedula = (EditText) findViewById(R.id.cedula);
        telefono= (EditText) findViewById(R.id.telefono);
        edad = (EditText) findViewById(R.id.edad);

        btn_volver_inicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_form = new Intent(ResgistroPersonalActivity.this,InicioActivity.class);
                startActivity(intent_form);
                finish();
            }
        });

        btn_registrar_personal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String var_3 = telefono.getText().toString();
                final String var_edad  = edad.getText().toString() ;
                final int edad_num = Integer.parseInt(var_edad);
                Personal obj = new Personal();
                obj.setNombre(nombre.getText().toString());
                obj.setCedula(cedula.getText().toString());
                obj.setEdad(edad_num);
                obj.setTelefono(telefono.getText().toString());
                PeluqueriaDao dao = new PeluqueriaDao();
                dao.guardar_Personal(obj,ResgistroPersonalActivity.this);
                Intent intent_form = new Intent(ResgistroPersonalActivity.this,Success_splash.class);
                startActivity(intent_form);
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
                Intent intent3 = new Intent(ResgistroPersonalActivity.this,MainActivity.class);
                startActivity(intent3);
                finish();
                return true;

            case R.id.it_asignar_actividades:
                return true;

            case R.id.it_actividades:
                return true;

            case R.id.it_usuario:
                Intent intent = new Intent(ResgistroPersonalActivity.this,RegistroActivity.class);
                startActivity(intent);
                finish();
                return true;

            case R.id.it_personal:
                Intent intent4 = new Intent(ResgistroPersonalActivity.this,InicioActivity.class);
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
