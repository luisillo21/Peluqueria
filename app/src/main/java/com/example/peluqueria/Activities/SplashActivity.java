package com.example.peluqueria.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ProgressBar;

import com.example.peluqueria.R;
import com.felipecsl.gifimageview.library.GifImageView;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.LogRecord;

public class SplashActivity extends AppCompatActivity {

     GifImageView gif;
     ProgressBar progresBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        gif = (GifImageView) findViewById(R.id.gif);
        progresBar = (ProgressBar) findViewById(R.id.progresBar);
        progresBar.setVisibility(progresBar.VISIBLE);

        try {
            InputStream inputStream = getAssets().open("gif_Splash_screen.gif");
            byte[] bytes = IOUtils.toByteArray(inputStream);
            gif.setBytes(bytes);
            gif.startAnimation();
        } catch (IOException ex) {

        }

        //Espera 4 segundos para ir al siguiente activity
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                SplashActivity.this.startActivity(new Intent(SplashActivity.this,MainActivity.class));
                SplashActivity.this.finish();
            }
        },4000);

    }
}
