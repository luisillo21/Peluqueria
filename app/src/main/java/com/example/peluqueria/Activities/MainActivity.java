package com.example.peluqueria.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.peluqueria.API.Api;
import com.example.peluqueria.API.Deserializers.UsuarioDeserializer;
import com.example.peluqueria.API.Services.Servicios;
import com.example.peluqueria.Database.DbPeluqueria;
import com.example.peluqueria.Model.Rol;
import com.example.peluqueria.Model.Usuario;
import com.example.peluqueria.R;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    EditText etUsuario,etPass;
    Button btn_iniciar,btn_salir,btn_form;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etUsuario = (EditText) findViewById(R.id.et_usuario);
        etPass = (EditText) findViewById(R.id.et_clave);
        btn_iniciar = (Button) findViewById(R.id.btn_iniciar);
        btn_salir = (Button) findViewById(R.id.btn_salir);
        btn_form = (Button) findViewById(R.id.btn_form_registrar);

        btn_salir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btn_iniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             String usuario = etUsuario.getText().toString();
             String clave = etPass.getText().toString();
             DbPeluqueria admin = new DbPeluqueria(MainActivity.this,"peluqueria",null,1);
             SQLiteDatabase db = admin.getWritableDatabase();
             Cursor row = db.rawQuery("SELECT clave from usuario where usuario='"+usuario+"'",null);
             if (row.moveToFirst()){
                 if (clave.equals(row.getString(0))){
                     Intent intent = new Intent(MainActivity.this, InicioActivity.class );
                     startActivity(intent);
                 }
             }else{
                 Toast.makeText(MainActivity.this,"Usuario o contraseña incorrectos",Toast.LENGTH_LONG).show();
             }


            }
        });

        btn_form.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_form = new Intent(MainActivity.this,RegistroActivity.class);
                startActivity(intent_form);
            }
        });



}



}




