package es.empresa.todolistuf1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class CrearNotaActivity extends AppCompatActivity {

    private TextView label1;
    private EditText caja1;
    private Button boton1_volver;
    private Button boton2_crear;
    private Intent pasarPantalla;
    private String contenidoCaja1 = "";
    protected BaseDatosSQLite db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.crear_nota_activity);
        label1 = (TextView) findViewById(R.id.label1_crear_nota);
        caja1 = (EditText) findViewById(R.id.caja1_crear_nota);
        boton1_volver = (Button) findViewById(R.id.boton_borrar);
        boton2_crear = (Button) findViewById(R.id.boton2_borrar);
        db = new BaseDatosSQLite(this);
        db.getAllNotes();

        boton2_crear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contenidoCaja1 = caja1.getText().toString();
                if (contenidoCaja1.isEmpty()) {
                    Toast.makeText(CrearNotaActivity.this, getString(R.string.toast1_crear_notas), Toast.LENGTH_SHORT).show();
                } else {
                    db.insertNote(contenidoCaja1);
                    Toast.makeText(CrearNotaActivity.this, getString(R.string.toast2_crear_notas), Toast.LENGTH_SHORT).show();
                    pasarPantalla = new Intent(CrearNotaActivity.this, ListadoActivity.class);
                    pasarPantalla.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(pasarPantalla);
                }
            }
        });

        boton1_volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pasarPantalla = new Intent(CrearNotaActivity.this, ListadoActivity.class);
                startActivity(pasarPantalla);
            }
        });
    }
}