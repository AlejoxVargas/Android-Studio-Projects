package es.empresa.ahorcadodam_b;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private TextView texto1;
    private TextView texto2;
    private TextView texto3;
    private EditText caja1;
    private Button boton1;
    private ImageView ima;
    private String contenidoCaja1 = "";
    private String solucion = "blanco";
    private int intentos = 5;
    private int maxIntentos = 5;
    private Intent pasarPantalla;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        texto1 = (TextView) findViewById(R.id.texto1_main);
        texto2 = (TextView) findViewById(R.id.texto2_main);
        texto3 = (TextView) findViewById(R.id.texto3_main);

        caja1 = (EditText) findViewById(R.id.caja1_main);
        boton1 = (Button) findViewById(R.id.boton1_main);
        ima = (ImageView) findViewById(R.id.ima_main);

        texto3.setText("Te quedan " + maxIntentos + " intentos");

//        ima.setImageResource(R.drawable.correcto);

        boton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contenidoCaja1 = caja1.getText().toString();
                if (contenidoCaja1.equalsIgnoreCase(solucion)) {
                    Toast.makeText(MainActivity.this, "Has ganado ", Toast.LENGTH_SHORT).show();
                    boton1.setEnabled(false);
                    ima.setImageResource(R.drawable.correcto);
                } else {
                    intentos -= 1;
                    if (intentos == 5) {
                        ima.setImageResource(R.drawable.ahorcado_0);
                    } else if (intentos == 4) {
                        ima.setImageResource(R.drawable.ahorcado_1);
                    } else if (intentos == 3) {
                        ima.setImageResource(R.drawable.ahorcado_2);
                    } else if (intentos == 2) {
                        ima.setImageResource(R.drawable.ahorcado_3);
                    } else if (intentos == 1) {
                        ima.setImageResource(R.drawable.ahorcado_4);
                    } else if (intentos == 0) {
                        ima.setImageResource(R.drawable.ahorcado_5);
                    }
                    texto3.setText("Te quedan " + intentos + " intentos");
                    if (intentos <= 0) {
                        boton1.setEnabled(false);
                    }
                }

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection.
        switch (item.getItemId()) {
            case R.id.menu_main_reiniciar:
                caja1.setText("");
                intentos = maxIntentos;
                texto3.setText("Te quedan " + intentos + " intentos");
                boton1.setEnabled(true);
                ima.setImageResource(R.drawable.ahorcado_0);
                return true;
            case R.id.menu_main_borrar:
                caja1.setText("");
                return true;
            case R.id.menu_main_ira:
                contenidoCaja1 = caja1.getText().toString();
                pasarPantalla = new Intent(MainActivity.this, SecondActivity.class);
                pasarPantalla.putExtra("RESPUESTA", contenidoCaja1);
                startActivity(pasarPantalla);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}