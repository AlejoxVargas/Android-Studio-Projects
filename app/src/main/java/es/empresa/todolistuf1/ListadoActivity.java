package es.empresa.todolistuf1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ListadoActivity extends AppCompatActivity {
    private ListView listView;
    private ArrayList<String> filas = new ArrayList<String>();
    private ArrayAdapter<String> adaptador;
    private Intent pasarPantalla;
    protected BaseDatosSQLite db;
    private String contenidoItem = "";
    private String[] partes;
    private int identificador = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listado_activity);

        db = new BaseDatosSQLite(this);

        listView = (ListView) findViewById(R.id.listView_listado);

        filas = db.getAllNotes();
        adaptador = new ArrayAdapter<String>(ListadoActivity.this, android.R.layout.simple_list_item_1, filas);
        listView.setAdapter(adaptador);

        /*listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                contenidoItem = parent.getItemAtPosition(position).toString();
                partes = contenidoItem.split(".-");
                if (partes.length > 1) {
                    Toast.makeText(ListadoActivity.this, "He pulsado " + partes[0], Toast.LENGTH_SHORT).show();
                    identificador = Integer.parseInt(partes[0]);
                    String title = listView.get(position).getTitle();
                    // db.deleteNoteById(identificador);
                    pasarPantalla = new Intent(ListadoActivity.this, VerNotaActivity.class);
                    pasarPantalla.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(pasarPantalla);
                }
                return true;
            }
        });*/
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                contenidoItem = parent.getItemAtPosition(position).toString();
                partes = contenidoItem.split(".-");
                if (partes.length > 1) {
                    identificador = Integer.parseInt(partes[0]);
                    String nota = partes[1].trim(); // Obtener el texto de la nota
                    Toast.makeText(ListadoActivity.this, partes[0] + " " + partes[1], Toast.LENGTH_SHORT).show();
                    // Crear un Intent y pasar los datos a VerNotaActivity
                    pasarPantalla = new Intent(ListadoActivity.this, VerNotaActivity.class);
                    pasarPantalla.putExtra("id", identificador);
                    pasarPantalla.putExtra("nota", nota);
                    startActivity(pasarPantalla);
                }
                return true;
            }
        });


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_informacion:
                pasarPantalla = new Intent(ListadoActivity.this, BorrarNotasActivity.class);
                startActivity(pasarPantalla);
                return true;
            case R.id.menu_agregar:
                pasarPantalla = new Intent(ListadoActivity.this, CrearNotaActivity.class);
                startActivity(pasarPantalla);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}