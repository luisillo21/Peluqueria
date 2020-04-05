package com.example.peluqueria.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

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


}
