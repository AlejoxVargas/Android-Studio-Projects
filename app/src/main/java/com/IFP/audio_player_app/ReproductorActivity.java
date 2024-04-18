package com.IFP.audio_player_app;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.w3c.dom.Text;

import java.io.IOException;

public class ReproductorActivity extends AppCompatActivity {

    private MediaPlayer mp;
    private int pause;
    private boolean isPlaying;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reproductor);

        Intent intent = getIntent();
        String titulo = intent.getStringExtra("titulo");
        String url = intent.getStringExtra("url");

        TextView label1 = findViewById(R.id.textView1_reproductor);
        TextView label2 = findViewById(R.id.textView2_reproductor);
        Button pausarBoton = findViewById(R.id.pausarBoton);
        Button reproducirBoton = findViewById(R.id.reproducirBoton);
        Button reiniciarBoton = findViewById(R.id.reiniciarBoton);
        Button botonVolver = findViewById(R.id.volver_boton_reproductor);

        label1.setText("Titulo:" + titulo);
        label2.setText(url);

        mp = new MediaPlayer(); // Inicializa el objeto MediaPlayer


        reproducirBoton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (pause > 0) {
                    mp.seekTo(pause);
                    mp.start();
                    isPlaying = true;
                } else {
                    try {
                        Uri url2 = Uri.parse(url);
                        mp.setAudioStreamType(AudioManager.STREAM_MUSIC);
                        mp.setDataSource(ReproductorActivity.this, url2);
                        mp.prepare();
                        mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            @Override
                            public void onCompletion(MediaPlayer mp) {
                                mp.stop();
                                pause=0;
                            }
                        });
                        mp.start();
                    } catch (IOException e) {
                        Toast.makeText(ReproductorActivity.this, "Imposible reproducir el sonido", Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                    }
                }
            }
        });

        pausarBoton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mp != null && mp.isPlaying()) {
                    mp.pause();
                    pause = mp.getCurrentPosition();
                    isPlaying = false;
                }
            }
        });
        reiniciarBoton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mp != null) {
                    mp.stop();
                    mp.release(); // Liberar recursos del MediaPlayer
                    mp = null; // Establecer a null para crear uno nuevo
                }
                pause = 0;
                mp = new MediaPlayer(); // Crear un nuevo MediaPlayer
            }
        });

        botonVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pasarPantalla = new Intent(ReproductorActivity.this, StartActivity.class);
                startActivity(pasarPantalla);
            }
        });
    }
}