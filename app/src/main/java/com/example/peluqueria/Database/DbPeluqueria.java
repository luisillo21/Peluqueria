package com.example.peluqueria.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbPeluqueria  extends SQLiteOpenHelper{

    public DbPeluqueria(Context context,  String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE rol(id_rol integer PRIMARY KEY AUTOINCREMENT NOT NULL,nombre text,estado text DEFAULT 'A')");
        db.execSQL("CREATE TABLE usuario(id integer primary key AUTOINCREMENT NOT NULL,usuario text,clave text, rol_id integer DEFAULT 1,estado text DEFAULT 'A')");
        db.execSQL("CREATE TABLE personal(id_personal integer primary key AUTOINCREMENT NOT NULL,nombre text, edad integer ,cedula text,telefono text,estado text DEFAULT 'A')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS personal");
        db.execSQL("CREATE TABLE personal(id_personal integer primary key AUTOINCREMENT NOT NULL,nombre text, edad integer ,cedula text,telefono text,estado text DEFAULT 'A')");
        db.execSQL("DROP TABLE IF EXISTS rol");
        db.execSQL("CREATE TABLE rol(id_rol integer PRIMARY KEY AUTOINCREMENT NOT NULL,nombre text,estado text DEFAULT 'A')");
        db.execSQL("DROP TABLE IF EXISTS usuario");
        db.execSQL("CREATE TABLE usuario(id_usuario integer primary key,nombre text,telefono text)");
    }
}
