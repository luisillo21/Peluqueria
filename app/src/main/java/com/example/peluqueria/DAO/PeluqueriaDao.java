package com.example.peluqueria.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.example.peluqueria.Database.DbPeluqueria;
import com.example.peluqueria.Model.Actividad;
import com.example.peluqueria.Model.Personal;

import java.util.ArrayList;

public class PeluqueriaDao extends AppCompatActivity {
    ArrayList<Personal> lista;
    ArrayList<Actividad> listaActividad;
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
        valores.put("id_personal",persona.getId_personal());
        valores.put("nombre",persona.getNombre());
        valores.put("edad",persona.getEdad());
        valores.put("cedula",persona.getCedula());
        valores.put("telefono",persona.getTelefono());
        db.update("personal",valores,"id_personal="+persona.getId_personal(),null);
        db.close();
    }
    public void Eliminar_Personal(int id, Context context){
        DbPeluqueria admin = new DbPeluqueria(context,"peluqueria",null,1);
        SQLiteDatabase db = admin.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put("estado","I");
        db.update("personal",valores,"id_personal="+id,null);
        db.close();
    }
    public ArrayList<Personal> Listar_todo(Context context){
        DbPeluqueria admin = new DbPeluqueria(context,"peluqueria",null,1);
        SQLiteDatabase db = admin.getWritableDatabase();
        Cursor row = db.rawQuery("SELECT * from personal where estado = 'A'",null);
        lista = new ArrayList<Personal>();

        while(row.moveToNext()){
            Log.e("ID_PERSONAL:","ID_PERSONA"+row.getInt(row.getColumnIndex("id_personal")));
            Log.e("ID_PERSONAL:","nombre"+row.getString(row.getColumnIndex("nombre")));
            Log.e("ID_PERSONAL:","cedula"+row.getString(row.getColumnIndex("cedula")));
            Log.e("ID_PERSONAL:","telefono"+row.getString(row.getColumnIndex("telefono")));
            Log.e("ID_PERSONAL:","edad"+row.getInt(row.getColumnIndex("edad")));

            Personal obj = new Personal(row.getInt(row.getColumnIndex("id_personal"))
                                       ,row.getString(row.getColumnIndex("nombre"))
                                       ,row.getInt(row.getColumnIndex("edad"))
                                       ,row.getString(row.getColumnIndex("cedula"))
                                       ,row.getString(row.getColumnIndex("telefono"))
                                       ,row.getString(row.getColumnIndex("estado")));
            lista.add(obj);
        }
        db.close();
        return lista;
    }

    public ArrayList<Actividad> Listar_Actividad(Context context){
        DbPeluqueria admin = new DbPeluqueria(context,"peluqueria",null,1);
        SQLiteDatabase db = admin.getWritableDatabase();
        Cursor row = db.rawQuery("SELECT * from actividad where estado = 'A'",null);
        listaActividad = new ArrayList<Actividad>();
        while(row.moveToNext()){
            Actividad obj = new Actividad(row.getInt(row.getColumnIndex("id_actividad"))
                                          ,row.getString(row.getColumnIndex("descripcion"))
                                          ,row.getString(row.getColumnIndex("fecha"))
                                          ,row.getString(row.getColumnIndex("hora_inicio"))
                                          ,row.getString(row.getColumnIndex("hora_fin"))
                                          ,row.getFloat(row.getColumnIndex("precio"))
                                           ,row.getString(row.getColumnIndex("estado")));
            listaActividad.add(obj);
        }
        db.close();
        return listaActividad;
    }


    public void guardar_Actividad(Actividad objeto, Context context){
        DbPeluqueria admin = new DbPeluqueria(context,"peluqueria",null,1);
        SQLiteDatabase db = admin.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put("descripcion",objeto.getDescripcion());
        valores.put("fecha",objeto.getFecha());
        valores.put("hora_inicio",objeto.getHora_inicio());
        valores.put("hora_fin",objeto.getHora_fin());
        valores.put("precio",objeto.getPrecio());
        db.insert("actividad",null,valores);
        db.close();
    }

    public void update_Actividad(Actividad objeto, Context context){
        DbPeluqueria admin = new DbPeluqueria(context,"peluqueria",null,1);
        SQLiteDatabase db = admin.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put("id_actividad",objeto.getId_actividad());
        valores.put("descripcion",objeto.getDescripcion());
        valores.put("fecha",objeto.getFecha());
        valores.put("hora_inicio",objeto.getHora_inicio());
        valores.put("hora_fin",objeto.getHora_fin());
        valores.put("precio",objeto.getHora_fin());
        db.update("actividad",valores,"id_actividad="+objeto.getId_actividad(),null);
        db.close();
    }

    public void Eliminar_Actividad(int id, Context context){
        DbPeluqueria admin = new DbPeluqueria(context,"peluqueria",null,1);
        SQLiteDatabase db = admin.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put("estado","I");
        db.update("actividad",valores,"id_actividad="+id,null);
        db.close();
    }




}
