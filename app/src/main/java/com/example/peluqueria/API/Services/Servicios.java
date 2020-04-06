package com.example.peluqueria.API.Services;

import com.example.peluqueria.Model.Usuario;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Servicios {

    @GET("/API/Proyecto_peluqueria/usuarios.php")
    Call<List<Usuario>> getUsuarios();

    @GET("/API/Proyecto_peluqueria/usuario_login.php")
    Call<Usuario> getUsuario(@Query("usuario") String usuario);


}
