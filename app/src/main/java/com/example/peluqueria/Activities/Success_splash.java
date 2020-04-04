package com.example.peluqueria.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ProgressBar;

import com.example.peluqueria.R;
import com.felipecsl.gifimageview.library.GifImageView;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;

public class Success_splash extends AppCompatActivity {

    GifImageView gif;
    ProgressBar progresBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success_splash);



        gif = (GifImageView) findViewById(R.id.gif_success);
        progresBar = (ProgressBar) findViewById(R.id.progresBarSuccess);
        progresBar.setVisibility(progresBar.VISIBLE);

        try {
            InputStream inputStream = getAssets().open("success_gif.gif");
            byte[] bytes = IOUtils.toByteArray(inputStream);
            gif.setBytes(bytes);
            gif.startAnimation();
        } catch (IOException ex) {

        }

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Success_splash.this.startActivity(new Intent(Success_splash.this,InicioActivity.class));
                Success_splash.this.finish();
            }
        },2500);


    }

}
