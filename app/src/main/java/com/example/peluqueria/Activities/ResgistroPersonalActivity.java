package com.example.peluqueria.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
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

            }
        });


    }
}
