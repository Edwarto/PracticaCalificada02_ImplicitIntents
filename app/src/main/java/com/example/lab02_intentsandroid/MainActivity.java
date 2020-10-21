package com.example.lab02_intentsandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static String NAME = "NOMBRE";
    public static String TLF = "TLF";
    public static String EMAIL = "EMAIL";
    public static String URL = "URL";
    public static String SEXO = "SEXO";
    public static String seleccion_sexo = "SELECCION";

    public static Bitmap bitmap;

    public static final int CAMERA_PIC_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Spinner spn_sexo = (Spinner)findViewById(R.id.spn_sexo);
        String[] opciones = {"Masculino", "Femenino", "Otro"};
        ArrayAdapter <String> adapter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_spinner_item,opciones);
        spn_sexo.setAdapter(adapter);
        seleccion_sexo = spn_sexo.getSelectedItem().toString();

        Button btn_send = (Button)findViewById(R.id.btn_enviar);
        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText edt_nombre = (EditText)findViewById(R.id.edt_name);
                String nombre = edt_nombre.getText().toString();

                EditText edt_tlf = (EditText)findViewById(R.id.edt_tlf);
                String tlf = edt_tlf.getText().toString();

                EditText edt_email = (EditText)findViewById(R.id.edt_email);
                String email = edt_email.getText().toString();

                EditText edt_URL = (EditText)findViewById(R.id.edt_url);
                String url = edt_URL.getText().toString();

                if(nombre.equals("") || tlf.equals("") || email.equals("") || url.equals("") || seleccion_sexo.equals(""))
                {
                    Toast.makeText(getApplicationContext(),"Todos los datos son necesarios ",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Intent intent = new Intent(getApplicationContext(),Resultado.class);
                    intent.putExtra(NAME,nombre);
                    intent.putExtra(TLF,tlf);
                    intent.putExtra(EMAIL,email);
                    intent.putExtra(URL,url);
                    intent.putExtra(SEXO,seleccion_sexo);
                    intent.putExtra("BitmapImage",bitmap);
                    startActivity(intent);
                }
            }
        });

        ((Button) findViewById(R.id.btn_camera)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_PIC_REQUEST);
            }

        });
    }
    @Override
    protected void onActivityResult(int requesCode, int resultCode, Intent data)
    {
        super.onActivityResult(requesCode,resultCode,data);
        if(requesCode == CAMERA_PIC_REQUEST)
        {
            if(resultCode == RESULT_OK)
            {
                bitmap = (Bitmap)data.getExtras().get("data");
                ImageView iv_foto = (ImageView)findViewById(R.id.iv_picture);
                iv_foto.setImageBitmap(bitmap);
            }
        }
    }
}