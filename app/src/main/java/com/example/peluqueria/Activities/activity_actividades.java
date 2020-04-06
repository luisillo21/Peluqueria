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

import com.example.peluqueria.Activities.FormulariosActivities.RegistroActividades;
import com.example.peluqueria.Activities.FormulariosActivities.RegistroActivity;
import com.example.peluqueria.Activities.FormulariosActivities.ResgistroPersonalActivity;
import com.example.peluqueria.Adaptador.ActividadAdapter;
import com.example.peluqueria.DAO.PeluqueriaDao;
import com.example.peluqueria.Model.Actividad;
import com.example.peluqueria.R;

import java.util.ArrayList;
import java.util.List;

public class activity_actividades extends AppCompatActivity {
    private Button btn_form_actividad;
    private ListView listActividad;
    private ActividadAdapter adaptador;
    private List<Actividad> lista_actividad;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividades);

        listActividad= (ListView) findViewById(R.id.listActividad);
        lista_actividad = new ArrayList<Actividad>();
        PeluqueriaDao crud = new PeluqueriaDao();
        lista_actividad = crud.Listar_Actividad(activity_actividades.this);
        adaptador = new ActividadAdapter(getApplicationContext(),lista_actividad);
        listActividad.setAdapter(adaptador);

        listActividad.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Actividad obj =  lista_actividad.get(position);
                Intent intent = new Intent(activity_actividades.this, DetalleActividad.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("actividad",obj);
                intent.putExtras(bundle);
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
                Intent intent3 = new Intent(activity_actividades.this,MainActivity.class);
                startActivity(intent3);
                finish();
                return true;

            case R.id.it_asignar_actividades:
                return true;

            case R.id.it_personal:
                Intent intent4 = new Intent(activity_actividades.this,InicioActivity.class);
                startActivity(intent4);
                finish();
                return true;

            case R.id.it_guardar_actividades:
                Intent intent7= new Intent(activity_actividades.this, RegistroActividades.class);
                startActivity(intent7);
                finish();
                return true;

            case R.id.it_actividades:
                Intent intent6 = new Intent(activity_actividades.this,activity_actividades.class);
                startActivity(intent6);
                finish();
                return true;

            case R.id.it_usuario:
                Intent intent = new Intent(activity_actividades.this, RegistroActivity.class);
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
