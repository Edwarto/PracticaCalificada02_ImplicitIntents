package com.example.lab02_intentsandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class Resultado extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado);

        String nombre = getIntent().getStringExtra(MainActivity.NAME);
        TextView txt_name = (TextView)findViewById(R.id.txt_nombre);
        txt_name.setText(nombre);

        String tlf = getIntent().getStringExtra(MainActivity.TLF);
        String mail = getIntent().getStringExtra(MainActivity.EMAIL);
        String url = getIntent().getStringExtra(MainActivity.URL);
        String sexo = getIntent().getStringExtra(MainActivity.SEXO);
        Intent intent = getIntent(); Bitmap bitmap = (Bitmap) intent.getParcelableExtra("BitmapImage");

        ImageView iv_foto = (ImageView)findViewById(R.id.iv_picture2);
        iv_foto.setImageBitmap(bitmap);

        String descripcion = "Teléfono: " + tlf + "\n" +
                "Correo electrónico: " + mail + "\n" +
                "Url: " + url +"\n" +
                "Sexo: " + sexo;
        TextView txt_desc = (TextView)findViewById(R.id.txt_descripcion);
        txt_desc.setText(descripcion);
    }
}