package es.empresa.currencyconvertor;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText input;
    private TextView labelResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        input = findViewById(R.id.inputvalor);
        Button botonEurosADolares = findViewById(R.id.botoneurosadolares);
        Button botonDolaresAEuros = findViewById(R.id.botondolaresaeuros);
        labelResultado = findViewById(R.id.labelresultado);

        botonEurosADolares.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                convertirDivisas(true);
            }
        });

        botonDolaresAEuros.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                convertirDivisas(false);
            }
        });
    }

    private void convertirDivisas(boolean eurosADolares) {
        String valorEntrada = input.getText().toString().trim();

        if (valorEntrada.isEmpty()) {
            Toast.makeText(this, "Debe introducir una cantidad", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            double cantidad = Double.parseDouble(valorEntrada);
            double resultado;
            if (eurosADolares) {
                resultado = ConversorDivisas.eurosADolares(cantidad);
            } else {
                resultado = ConversorDivisas.dolaresAEuros(cantidad);
            }
            labelResultado.setText(String.valueOf(resultado));
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Formato de número incorrecto", Toast.LENGTH_SHORT).show();
        }
    }

    public static class ConversorDivisas {
        private static final double VALOR_DIVISA_EURO_A_DOLAR = 1.21;

        public static double eurosADolares(double valorEnEUR) {
            return valorEnEUR * VALOR_DIVISA_EURO_A_DOLAR;
        }

        public static double dolaresAEuros(double valorEnUSD) {
            return valorEnUSD / VALOR_DIVISA_EURO_A_DOLAR;
        }
    }
}
