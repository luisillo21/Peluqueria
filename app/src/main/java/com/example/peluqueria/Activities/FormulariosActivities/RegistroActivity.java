package com.example.peluqueria.Activities.FormulariosActivities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.peluqueria.API.Api;
import com.example.peluqueria.API.Deserializers.UsuarioDeserializer;
import com.example.peluqueria.API.Services.Servicios;
import com.example.peluqueria.Activities.InicioActivity;
import com.example.peluqueria.Activities.MainActivity;
import com.example.peluqueria.Activities.Success_splash;
import com.example.peluqueria.Activities.activity_actividades;
import com.example.peluqueria.Database.DbPeluqueria;
import com.example.peluqueria.Model.Usuario;
import com.example.peluqueria.R;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegistroActivity extends AppCompatActivity {
    EditText usuario,clave,cnf_clave;
    Button btn_registrar,btn_volver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        btn_registrar = (Button) findViewById(R.id.btn_registrar);


        usuario = (EditText) findViewById(R.id.usuario);
        clave =(EditText) findViewById(R.id.clave);
        cnf_clave =(EditText) findViewById(R.id.cnf_clave);



        btn_registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GsonBuilder builder = new GsonBuilder();
                builder.registerTypeAdapter(Usuario.class, new UsuarioDeserializer());
                Api.retrofit = null;
                Servicios serv = Api.getAPI(builder).create(Servicios.class);


                final String var_usu = usuario.getText().toString();
                final String var_clave = clave.getText().toString();
                final String var_confirmar = cnf_clave.getText().toString();
                if (var_usu.isEmpty() || var_clave.isEmpty() || var_confirmar.isEmpty()){
                    Toast.makeText(RegistroActivity.this,"Ningun campo debe estar vacio",Toast.LENGTH_LONG).show();
                }else{
                if (var_clave.equals(var_confirmar)){
                    Call<Usuario> datos = serv.getUsuario(var_usu);
                    datos.enqueue(new Callback<Usuario>() {
                        @Override
                        public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                            if (response.isSuccessful()){
                                Usuario obj = response.body();
                                if (usuario.equals(obj.getUsuario()) && clave.equals(obj.getClave())){
                                    Toast.makeText(RegistroActivity.this,"Nombre de usuario o contraseña ya  en uso",Toast.LENGTH_LONG).show();
                                }else{
                                    Toast.makeText(RegistroActivity.this,"Contraseña incorrecta",Toast.LENGTH_LONG).show();
                                }

                            }else{
                                Toast.makeText(RegistroActivity.this,response.message(),Toast.LENGTH_LONG).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<Usuario> call, Throwable t) {
                          guardar(var_usu,var_clave);
                        }
                    });
                }else{
                    Toast.makeText(RegistroActivity.this,"La confirmacion de contraseña no coincide",Toast.LENGTH_LONG).show();
                }
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
                preferences = getSharedPreferences("datos_usuario",MODE_PRIVATE);
                preferences.edit().clear().apply();
                Intent intent3 = new Intent(RegistroActivity.this,MainActivity.class);
                startActivity(intent3);
                finish();
                return true;

            case R.id.it_asignar_actividades:
                Intent intent9 = new Intent(RegistroActivity.this,AsignarActividadActivity.class);
                startActivity(intent9);
                finish();
                return true;

            case R.id.it_personal:
                Intent intent4 = new Intent(RegistroActivity.this,InicioActivity.class);
                startActivity(intent4);
                finish();
                return true;

            case R.id.it_guardar_actividades:
                Intent intent7= new Intent(RegistroActivity.this, RegistroActividades.class);
                startActivity(intent7);
                finish();
                return true;

            case R.id.it_actividades:
                Intent intent6 = new Intent(RegistroActivity.this, activity_actividades.class);
                startActivity(intent6);
                finish();
                return true;

            case R.id.it_usuario:
                Intent intent = new Intent(RegistroActivity.this, RegistroActivity.class);
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

    public void guardar(String usuario, String clave){
        DbPeluqueria admin = new DbPeluqueria(RegistroActivity.this,"peluqueria",null,1);
        SQLiteDatabase db = admin.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put("usuario",usuario);
        valores.put("clave",clave);
        db.insert("usuario",null,valores);
        db.close();
        Intent intent = new Intent(RegistroActivity.this, Success_splash.class );
        startActivity(intent);
        finish();
    }
}
