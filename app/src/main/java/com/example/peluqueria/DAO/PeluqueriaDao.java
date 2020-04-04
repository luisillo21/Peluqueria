package com.example.peluqueria.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.appcompat.app.AppCompatActivity;

import com.example.peluqueria.Activities.MainActivity;
import com.example.peluqueria.Activities.RegistroActivity;
import com.example.peluqueria.Database.DbPeluqueria;
import com.example.peluqueria.Model.Personal;

import java.util.ArrayList;

public class PeluqueriaDao extends AppCompatActivity {
    ArrayList<Personal> lista;
    public void guardar(String usuario, String clave, Context context){
        DbPeluqueria admin = new DbPeluqueria(context,"peluqueria",null,1);
        SQLiteDatabase db = admin.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put("usuario",usuario);
        valores.put("clave",clave);
        db.insert("usuario",null,valores);
        db.close();
    }
    public void guardar_Personal(Personal persona, Context context){
        DbPeluqueria admin = new DbPeluqueria(context,"peluqueria",null,1);
        SQLiteDatabase db = admin.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put("nombre",persona.getNombre());
        valores.put("edad",persona.getEdad());
        valores.put("cedula",persona.getCedula());
        valores.put("telefono",persona.getTelefono());
        db.insert("personal",null,valores);
        db.close();
    }
    public void update_Personal(Personal persona, Context context){
        DbPeluqueria admin = new DbPeluqueria(context,"peluqueria",null,1);
        SQLiteDatabase db = admin.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put("nombre",persona.getNombre());
        valores.put("edad",persona.getEdad());
        valores.put("cedula",persona.getCedula());
        valores.put("telefono",persona.getTelefono());
        db.update("personal",valores,"id="+persona.getId_personal(),null);
        db.close();
    }
    public void Eliminar_Personal(int id, Context context){
        DbPeluqueria admin = new DbPeluqueria(context,"peluqueria",null,1);
        SQLiteDatabase db = admin.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put("estado","I");
        db.update("personal",valores,"id="+id,null);
        db.close();
    }
    public ArrayList<Personal> Listar_todo(Context context){
        DbPeluqueria admin = new DbPeluqueria(context,"peluqueria",null,1);
        SQLiteDatabase db = admin.getWritableDatabase();
        Cursor row = db.rawQuery("SELECT * from personal where estado = 'A'",null);
        lista = new ArrayList<Personal>();

        while(row.moveToNext()){
            Personal obj = new Personal(row.getInt(0)
                    ,row.getString(1),row.getInt(2)
                    ,row.getString(3),row.getString(4)
                    ,row.getString(5));
            lista.add(obj);
        }
        db.close();
        return lista;
    }


}
