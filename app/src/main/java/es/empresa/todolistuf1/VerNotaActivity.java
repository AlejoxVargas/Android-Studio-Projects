package es.empresa.todolistuf1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class VerNotaActivity extends AppCompatActivity {

    private int idNota;
    private String textoNota;
    protected Button boton1_volver;
    protected Button boton2_borrar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ver_notas_activity);

        BaseDatosSQLite db = new BaseDatosSQLite(this);

        Intent intent = getIntent();
        idNota = intent.getIntExtra("id", -1); // Valor predeterminado si no se encuentra
        textoNota = intent.getStringExtra("nota");
        boton1_volver = findViewById(R.id.boton_borrar);
        boton2_borrar = findViewById(R.id.boton2_borrar);

        TextView label1 = findViewById(R.id.label1_ver_notas);
        label1.setText(idNota + ".-" + textoNota);

        boton1_volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pasarPantalla = new Intent(VerNotaActivity.this, ListadoActivity.class);
                startActivity(pasarPantalla);
            }
        });

        boton2_borrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.deleteNoteById(idNota);
                Toast.makeText(VerNotaActivity.this, getString(R.string.toast_ver_nota), Toast.LENGTH_SHORT).show();
                Intent pasarPantalla = new Intent(VerNotaActivity.this, ListadoActivity.class);
                pasarPantalla.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(pasarPantalla);
            }
        });

    }
}