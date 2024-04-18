package com.IFP.audio_player_app;

import android.content.Intent;
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

public class CrearActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear);

        Button crearButton = findViewById(R.id.crearButton);
        TextView caja1 = findViewById(R.id.audioEditText);
        TextView caja2 = findViewById(R.id.urlEditText);

        DataBaseSQL db = new DataBaseSQL(this);
        db.getAllAudios();

        crearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String contenidoCaja1 = caja1.getText().toString();
                String contenidoCaja2 = caja2.getText().toString();
                if (contenidoCaja1.isEmpty() || contenidoCaja2.isEmpty()) {
                    Toast.makeText(CrearActivity.this, getString(R.string.toast1_crear_activity), Toast.LENGTH_SHORT).show();
                } else {
                    db.insertAudio(contenidoCaja1, contenidoCaja2);
                    Toast.makeText(CrearActivity.this, getString(R.string.toast2_crear_activity), Toast.LENGTH_SHORT).show();
                    Intent pasarPantalla = new Intent(CrearActivity.this, StartActivity.class);
                    pasarPantalla.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(pasarPantalla);
                }
            }
        });
    }
}