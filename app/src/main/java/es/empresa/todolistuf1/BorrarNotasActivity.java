package es.empresa.todolistuf1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class BorrarNotasActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.borrar_notas_activity);

        BaseDatosSQLite db = new BaseDatosSQLite(this);

        Button boton_volver = findViewById(R.id.boton_volver);
        Button boton_eliminar = findViewById(R.id.boton_borrar);

        boton_volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pasarPantalla = new Intent(BorrarNotasActivity.this, ListadoActivity.class);
                startActivity(pasarPantalla);
            }
        });

        boton_eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.deleteAllNotes();
                Toast.makeText(BorrarNotasActivity.this, getString(R.string.toast_borrar_notas), Toast.LENGTH_SHORT).show();
                Intent pasarPantalla = new Intent(BorrarNotasActivity.this, ListadoActivity.class);
                startActivity(pasarPantalla);
            }
        });
    }
}