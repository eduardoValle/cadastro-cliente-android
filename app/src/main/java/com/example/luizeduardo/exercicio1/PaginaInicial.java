package com.example.luizeduardo.exercicio1;

/**
 * Created by Luiz Eduardo on 21/09/2016.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class PaginaInicial extends AppCompatActivity {

    private Button btnNovo, btnConulta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pagina_inicial);

        btnNovo = (Button) findViewById(R.id.btnNovo);
        btnNovo.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), CadastrarCliente.class);
                startActivity(intent);
            }
        });

        btnConulta = (Button) findViewById(R.id.btnConulta);
        btnConulta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), ListaClientes.class);
                startActivity(intent);
            }
        });
    }
}