package com.example.peluqueria.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.peluqueria.API.Api;
import com.example.peluqueria.API.Deserializers.UsuarioDeserializer;
import com.example.peluqueria.API.Services.Servicios;
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
        btn_volver = (Button) findViewById(R.id.btn_volver);

        usuario = (EditText) findViewById(R.id.usuario);
        clave =(EditText) findViewById(R.id.clave);
        cnf_clave =(EditText) findViewById(R.id.cnf_clave);



        btn_volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegistroActivity.this, MainActivity.class );
                startActivity(intent);
            }
        });

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
        });



    }

    public void guardar(String usuario, String clave){
        DbPeluqueria admin = new DbPeluqueria(RegistroActivity.this,"peluqueria",null,1);
        SQLiteDatabase db = admin.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put("usuario",usuario);
        valores.put("clave",clave);
        db.insert("usuario",null,valores);
        db.close();
        Intent intent = new Intent(RegistroActivity.this, MainActivity.class );
        startActivity(intent);
    }
}
