package com.example.luizeduardo.exercicio1;

/**
 * Created by Luiz Eduardo on 21/09/2016.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class PaginaInicial extends AppCompatActivity implements View.OnClickListener {

    private Button btnNovo, btnConulta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pagina_inicial);

        // INICIANDO OS BOTÕES CRIADOS NO ARQUIVO activity_main.xml
        btnNovo = (Button) findViewById(R.id.btnNovo);
        btnNovo.setOnClickListener(this);

        btnConulta = (Button) findViewById(R.id.btnConulta);
        btnConulta.setOnClickListener(new LimparCampos());
    }

    /**
     * Prepara os dados dos campos e os envia para a classe responsável para renderizar o conteúdo.
     */
    public void onClick(View v) {

        Intent intent = new Intent(this, CadastrarCliente.class);
        startActivity(intent);
    }

    /**
     * Classe respnsável por limpar os dados dos campos.
     */
    class LimparCampos implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getBaseContext(), ListaClientes.class);
            startActivity(intent);
        }
    }
}
