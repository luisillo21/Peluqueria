package com.example.peluqueria.API.Deserializers;

import com.example.peluqueria.Model.Rol;
import com.example.peluqueria.Model.Usuario;
import com.example.peluqueria.ModelListas.UsuarioLst;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDeserializer implements JsonDeserializer {
    @Override
    public Object deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        int id = json.getAsJsonObject().get("id").getAsInt();
        String usuario = json.getAsJsonObject().get("usuario").getAsString();
        String clave = json.getAsJsonObject().get("clave").getAsString();
        int rol_id = json.getAsJsonObject().get("rol_id").getAsInt();
        String estado = json.getAsJsonObject().get("estado").getAsString();
        //JsonObject rol = json.getAsJsonObject().get("rol").getAsJsonObject();
       // Rol obj_rol = new Rol(rol.get("id_rol").getAsInt(),rol.get("descripcion").getAsString(),"A");
        Usuario objUsuario  = new Usuario(id,usuario,clave,rol_id,estado);
        return objUsuario;
    }
}
