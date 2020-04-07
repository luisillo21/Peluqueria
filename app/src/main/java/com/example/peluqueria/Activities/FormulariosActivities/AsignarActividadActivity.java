package com.example.peluqueria.Activities.FormulariosActivities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.peluqueria.Activities.InicioActivity;
import com.example.peluqueria.Activities.MainActivity;
import com.example.peluqueria.Activities.activity_actividades;
import com.example.peluqueria.DAO.PeluqueriaDao;
import com.example.peluqueria.Database.DbPeluqueria;
import com.example.peluqueria.Model.Actividad;
import com.example.peluqueria.Model.Asignar_actividad;
import com.example.peluqueria.Model.Personal;
import com.example.peluqueria.R;

import java.util.ArrayList;
import java.util.List;

public class AsignarActividadActivity extends AppCompatActivity {
    Spinner comboPersonas,comboActividad;
    TextView etaActividadSelec,etPersonaSelec,idPersonalselect,idActividadselect;
    Button btnAsignarActividad;
    List<Personal> dataPersonal;
    ArrayList<String> lstPersonal;
    List<Actividad> dataActividad;
    ArrayList<String> lstActividad;
    DbPeluqueria conn;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asignar_actividad);
        conn = new DbPeluqueria(getApplicationContext(),"peluquerioa",null,1);

        comboPersonas = (Spinner) findViewById(R.id.comboPersona);
        comboActividad = (Spinner) findViewById(R.id.comboActividad);
        btnAsignarActividad = (Button) findViewById(R.id.btnAsignarActividad);
        etaActividadSelec =(TextView) findViewById(R.id.etaActividadSelec);
        etPersonaSelec = (TextView) findViewById(R.id.etPersonaSelec);
        idPersonalselect = (TextView) findViewById(R.id.idPersonalselect);
        idActividadselect = (TextView) findViewById(R.id.idActividadselect);

        consultarPersonas();
        consultarActividades();
        ArrayAdapter<CharSequence>  adaptador = new ArrayAdapter(AsignarActividadActivity.this,android.R.layout.simple_spinner_item,lstPersonal);
        ArrayAdapter<CharSequence> adaptadorAct = new ArrayAdapter(AsignarActividadActivity.this,android.R.layout.simple_spinner_item,lstActividad);
        comboActividad.setAdapter(adaptadorAct);
        comboPersonas.setAdapter(adaptador);

           comboActividad.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position!=0){
                    idActividadselect.setText(String.valueOf(dataActividad.get(position-1).getId_actividad()));
                    etaActividadSelec.setText(String.valueOf("Actividad: "+dataActividad.get(position-1).getDescripcion()+" Fecha: "+dataActividad.get(position-1).getFecha()));
                }else{
                    idActividadselect.setText("");
                    etaActividadSelec.setText("");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        comboPersonas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position!=0){
                    etPersonaSelec.setText(String.valueOf("Nombre: "+ dataPersonal.get(position-1).getNombre() +" Cedula:"+dataPersonal.get(position-1).getCedula()));
                    idPersonalselect.setText(String.valueOf( dataPersonal.get(position-1).getId_personal()));
                }else{
                    etPersonaSelec.setText("");
                    idPersonalselect.setText("");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnAsignarActividad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (idPersonalselect.getText().toString().equals("") || idActividadselect.getText().toString().equals("")){
                    Toast.makeText(AsignarActividadActivity.this,"Ningun campo debe estar incompleto",Toast.LENGTH_SHORT).show();
                }else{
                    int idPersonal = Integer.valueOf(idPersonalselect.getText().toString());
                    int idActividad = Integer.valueOf(idActividadselect.getText().toString());
                    Asignar_actividad obj = new Asignar_actividad();
                    obj.setId_personal(idPersonal);
                    obj.setId_actividad(idActividad);
                    PeluqueriaDao crud = new PeluqueriaDao();
                    crud.guardar_Asignacion(obj,AsignarActividadActivity.this);
                    Toast.makeText(AsignarActividadActivity.this,"Asignacion guardada",Toast.LENGTH_SHORT).show();
                    etPersonaSelec.setText("");
                    etaActividadSelec.setText("");
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
                Intent intent3 = new Intent(AsignarActividadActivity.this, MainActivity.class);
                startActivity(intent3);
                finish();
                return true;

            case R.id.it_asignar_actividades:
                Intent intent9 = new Intent(AsignarActividadActivity.this,AsignarActividadActivity.class);
                startActivity(intent9);
                finish();
                return true;

            case R.id.it_personal:
                Intent intent4 = new Intent(AsignarActividadActivity.this, InicioActivity.class);
                startActivity(intent4);
                finish();
                return true;

            case R.id.it_guardar_actividades:
                Intent intent7= new Intent(AsignarActividadActivity.this, RegistroActividades.class);
                startActivity(intent7);
                finish();
                return true;

            case R.id.it_actividades:
                Intent intent6 = new Intent(AsignarActividadActivity.this, activity_actividades.class);
                startActivity(intent6);
                finish();
                return true;

            case R.id.it_usuario:
                Intent intent = new Intent(AsignarActividadActivity.this, RegistroActivity.class);
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



    private void consultarActividades() {
        SQLiteDatabase db = conn.getWritableDatabase();
        Actividad act;
        dataActividad = new ArrayList<Actividad>();
        PeluqueriaDao dao = new PeluqueriaDao();
        dataActividad = dao.Listar_Actividad(AsignarActividadActivity.this);
        obtenerListaActividad();
    }

    private void obtenerListaActividad() {
        lstActividad = new ArrayList<String>();
        lstActividad.add("Seleccione");
        for (int i = 0; i<dataActividad.size();i++ ){
            lstActividad.add("Actividad: " +dataActividad.get(i).getDescripcion()+ " Fecha:"+dataActividad.get(i).getFecha()+"\n"+"Hora inicio:"+ dataActividad.get(i).getHora_inicio() + "Hora fin:" + dataActividad.get(i).getHora_fin());

        }
    }

    public void consultarPersonas(){
        SQLiteDatabase db = conn.getWritableDatabase();
        Personal per;
        PeluqueriaDao dao = new PeluqueriaDao();
        dataPersonal= dao.Listar_todo(AsignarActividadActivity.this);
        obtenerLista();
    }

    private void obtenerLista() {
        lstPersonal = new ArrayList<String>();
        lstPersonal.add("Seleccione");
        for (int i = 0; i<dataPersonal.size();i++ ){
            lstPersonal.add("Nombre: " +dataPersonal.get(i).getNombre()+ " Cedula:"+dataPersonal.get(i).getCedula() + " Edad: "+ dataPersonal.get(i).getEdad() );
        }
    }
}
