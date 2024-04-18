package com.IFP.audio_player_app;

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

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        DataBaseSQL db = new DataBaseSQL(this);

        ArrayList<String> titulos = db.getAllAudioTitlesAndIds();

        ListView listView = findViewById(R.id.mainListView);
        ArrayAdapter<String> adaptador = new ArrayAdapter<>(StartActivity.this, android.R.layout.simple_list_item_1, titulos);
        listView.setAdapter(adaptador);

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                String audioInfo = db.getAllAudios().get(position);
                String[] partes = audioInfo.split("\\.-");
                if (partes.length > 1) {
                    int identificador = Integer.parseInt(partes[0]);
                    String titulo = partes[1].trim();
                    String url = partes[2].trim();
                    Intent pasarPantalla = new Intent(StartActivity.this, ReproductorActivity.class);
                    pasarPantalla.putExtra("id", identificador);
                    pasarPantalla.putExtra("titulo", titulo);
                    pasarPantalla.putExtra("url", url);
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
        inflater.inflate(R.menu.menu_start, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.crearItem) {
            Intent pasarPantalla = new Intent(StartActivity.this, CrearActivity.class);
            startActivity(pasarPantalla);
            return true;
        } else if (id == R.id.salirItem) {
            System.exit(0);
        }
        return true;
    }
}