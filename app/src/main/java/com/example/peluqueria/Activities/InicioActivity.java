package com.example.peluqueria.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

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
                Toast.makeText(getApplicationContext(),"id"+ lista_personal.get(position).getNombre(),Toast.LENGTH_LONG).show();
            }
        });

        btn_form_personal = (Button) findViewById(R.id.btn_form_personal);
        btn_form_personal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_form = new Intent(InicioActivity.this,ResgistroPersonalActivity.class);
                startActivity(intent_form);
            }
        });

        //listPersonal



    }


}
