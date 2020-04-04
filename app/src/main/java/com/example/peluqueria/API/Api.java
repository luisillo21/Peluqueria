package com.example.peluqueria.API;

import com.example.peluqueria.API.Deserializers.UsuarioDeserializer;
import com.example.peluqueria.Model.Usuario;
import com.example.peluqueria.ModelListas.UsuarioLst;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Api {
    public static Retrofit retrofit = null;
    public static String BASE_URL = "http://192.168.0.16:82";



    public static Retrofit getAPI(GsonBuilder builder) {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(Api.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(builder.create()))
                    .build();
        }
        return retrofit;
    }

}
