package es.empresa.ahorcadodam_b;

import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SecondActivity extends AppCompatActivity {
    private String paquete1 = "";
    private Bundle extras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_second);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        extras = getIntent().getExtras();
        if (extras != null) {
            // He recibido al menos un paquete
            paquete1 = extras.getString("RESPUESTA");
            Toast.makeText(this, "El paquete recibido es " + paquete1, Toast.LENGTH_SHORT).show();
        } else {
            // No he recibido ningun paquete
        }
    }
}