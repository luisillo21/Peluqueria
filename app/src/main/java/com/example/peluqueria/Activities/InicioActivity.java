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
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.peluqueria.Activities.FormulariosActivities.AsignarActividadActivity;
import com.example.peluqueria.Activities.FormulariosActivities.RegistroActividades;
import com.example.peluqueria.Activities.FormulariosActivities.RegistroActivity;
import com.example.peluqueria.Activities.FormulariosActivities.ResgistroPersonalActivity;
import com.example.peluqueria.Adaptador.PersonalAdapter;
import com.example.peluqueria.DAO.PeluqueriaDao;
import com.example.peluqueria.Model.Personal;
import com.example.peluqueria.R;

import java.util.ArrayList;
import java.util.List;

public class InicioActivity extends AppCompatActivity {
    private Button btn_form_personal;
    private ListView listPersonal;
    private PersonalAdapter adaptador;
    private List<Personal> lista_personal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);
        listPersonal = (ListView) findViewById(R.id.listPersonal);
        lista_personal = new ArrayList<Personal>();
        PeluqueriaDao crud = new PeluqueriaDao();
        lista_personal = crud.Listar_todo(InicioActivity.this);
        adaptador = new PersonalAdapter(getApplicationContext(),lista_personal);
        listPersonal.setAdapter(adaptador);

        listPersonal.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Personal obj =  lista_personal.get(position);
                Intent intent = new Intent(InicioActivity.this,DetallePersonal.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("personal",obj);
                intent.putExtras(bundle);
                startActivity(intent);
                finish();
            }
        });

        btn_form_personal = (Button) findViewById(R.id.btn_form_personal);
        btn_form_personal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_form = new Intent(InicioActivity.this, ResgistroPersonalActivity.class);
                startActivity(intent_form);
                finish();
            }
        });

        //listPersonal



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
                Intent intent3 = new Intent(InicioActivity.this,MainActivity.class);
                startActivity(intent3);
                finish();
                return true;

            case R.id.it_asignar_actividades:
                Intent intent9 = new Intent(InicioActivity.this, AsignarActividadActivity.class);
                startActivity(intent9);
                finish();
                return true;

            case R.id.it_personal:
                Intent intent4 = new Intent(InicioActivity.this,InicioActivity.class);
                startActivity(intent4);
                finish();
                return true;

            case R.id.it_guardar_actividades:
                Intent intent7= new Intent(InicioActivity.this, RegistroActividades.class);
                startActivity(intent7);
                finish();
                return true;

            case R.id.it_actividades:
                Intent intent6 = new Intent(InicioActivity.this,activity_actividades.class);
                startActivity(intent6);
                finish();
                return true;

            case R.id.it_usuario:
               Intent intent = new Intent(InicioActivity.this, RegistroActivity.class);
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
