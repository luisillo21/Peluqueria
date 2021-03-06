package com.example.peluqueria.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;
import com.example.peluqueria.API.Api;
import com.example.peluqueria.API.Deserializers.UsuarioDeserializer;
import com.example.peluqueria.API.Services.Servicios;
import com.example.peluqueria.DAO.PeluqueriaDao;
import com.example.peluqueria.Database.DbPeluqueria;
import com.example.peluqueria.Model.Usuario;
import com.example.peluqueria.R;
import com.google.gson.GsonBuilder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    CheckBox cbRecordar;
    EditText etUsuario,etPass;
    Button btn_iniciar,btn_salir;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cbRecordar = (CheckBox) findViewById(R.id.cbRecordar);



        etUsuario = (EditText) findViewById(R.id.et_usuario);
        etPass = (EditText) findViewById(R.id.et_clave);
        btn_iniciar = (Button) findViewById(R.id.btn_iniciar);
        btn_salir = (Button) findViewById(R.id.btn_salir);



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
                if (cbRecordar.isChecked()){
                    SharedPreferences preferences;
                    preferences = getSharedPreferences("datos_usuario", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editors =preferences.edit();
                    editors.putString("usuario",usuario);
                    editors.putString("clave",clave);
                    editors.apply();
                    editors.commit();
                }


             DbPeluqueria admin = new DbPeluqueria(MainActivity.this,"peluqueria",null,1);
             SQLiteDatabase db = admin.getWritableDatabase();
             Cursor row = db.rawQuery("SELECT clave from usuario where usuario='"+usuario+"'",null);
             if (row.moveToFirst()){
                 if (clave.equals(row.getString(0))){
                     Intent intent = new Intent(MainActivity.this, InicioActivity.class );
                     startActivity(intent);
                     finish();
                 }
             }else{
                 GsonBuilder builder = new GsonBuilder();
                 builder.registerTypeAdapter(Usuario.class, new UsuarioDeserializer());
                 Api.retrofit = null;
                 Servicios serv = Api.getAPI(builder).create(Servicios.class);
                 Call<Usuario> datos = serv.getUsuario(usuario);
                 datos.enqueue(new Callback<Usuario>() {
                     @Override
                     public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                         if (response.isSuccessful()){
                             String usuario = etUsuario.getText().toString();
                             String clave = etPass.getText().toString();


                             Usuario obj = response.body();
                             if (usuario.equals(obj.getUsuario()) && clave.equals(obj.getClave())){
                                 PeluqueriaDao object = new PeluqueriaDao();
                                 object.guardar(usuario,clave,MainActivity.this);
                                 Toast.makeText(MainActivity.this,"Usuario Guardado en la base local",Toast.LENGTH_LONG).show();
                                 Intent intent = new Intent(MainActivity.this, InicioActivity.class );
                                 startActivity(intent);
                                 finish();
                             }else{
                                 Toast.makeText(MainActivity.this,"Contraseña incorrecta",Toast.LENGTH_LONG).show();
                             }

                         }else{
                             Toast.makeText(MainActivity.this,"Usuario no registrado",Toast.LENGTH_LONG).show();

                             Toast.makeText(MainActivity.this,response.message(),Toast.LENGTH_LONG).show();
                         }
                     }

                     @Override
                     public void onFailure(Call<Usuario> call, Throwable t) {
                         Toast.makeText(MainActivity.this,"No tiene conexion a internet, por favor consulte a su proveedor",Toast.LENGTH_LONG).show();
                         t.printStackTrace();
                     }
                 });


             }


            }
        });





}







}




