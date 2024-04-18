package com.ifp.random_sum_game;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private TextView textViewOperacion;
    private Button buttonRespuesta1;
    private Button buttonRespuesta2;
    private Button buttonRespuesta3;

    private int num1;
    private int num2;
    private int resultadoCorrecto;

    private MediaPlayer mediaPlayerCorrecto;
    private MediaPlayer mediaPlayerIncorrecto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewOperacion = findViewById(R.id.label2);
        buttonRespuesta1 = findViewById(R.id.boton1);
        buttonRespuesta2 = findViewById(R.id.boton2);
        buttonRespuesta3 = findViewById(R.id.boton3);

        mediaPlayerCorrecto = MediaPlayer.create(this, R.raw.correcto_sound);
        mediaPlayerIncorrecto = MediaPlayer.create(this, R.raw.incorrecto_sound);

        generarOperacion();
    }

    private void generarOperacion() {
        Random random = new Random();
        num1 = random.nextInt(10);
        num2 = random.nextInt(10);

        resultadoCorrecto = num1 + num2;

        textViewOperacion.setText(String.format("%d + %d = ?", num1, num2));

        int respuestaIncorrecta1 = generarRespuestaIncorrecta();
        int respuestaIncorrecta2 = generarRespuestaIncorrecta();

        buttonRespuesta1.setText(String.valueOf(respuestaIncorrecta1));
        buttonRespuesta2.setText(String.valueOf(respuestaIncorrecta2));
        buttonRespuesta3.setText(String.valueOf(resultadoCorrecto));

        asignarListeners();
    }

    private int generarRespuestaIncorrecta() {
        Random random = new Random();
        int respuestaIncorrecta;
        do {
            respuestaIncorrecta = random.nextInt(20);
        } while (respuestaIncorrecta == resultadoCorrecto);
        return respuestaIncorrecta;
    }

    private void asignarListeners() {
        buttonRespuesta1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verificarRespuesta(Integer.parseInt(buttonRespuesta1.getText().toString()));
            }
        });

        buttonRespuesta2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verificarRespuesta(Integer.parseInt(buttonRespuesta2.getText().toString()));
            }
        });

        buttonRespuesta3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verificarRespuesta(Integer.parseInt(buttonRespuesta3.getText().toString()));
            }
        });
    }

    private void verificarRespuesta(int respuesta) {
        if (respuesta == resultadoCorrecto) {
            mediaPlayerCorrecto.start();
            Toast.makeText(this, "¡Respuesta correcta!", Toast.LENGTH_SHORT).show();
            generarOperacion();
        } else {
            mediaPlayerIncorrecto.start();
            Toast.makeText(this, "Respuesta incorrecta, intenta de nuevo", Toast.LENGTH_SHORT).show();
        }
    }

}