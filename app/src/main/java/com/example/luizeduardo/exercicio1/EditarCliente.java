package com.example.luizeduardo.exercicio1;

/**
 * Created by Luiz Eduardo on 20/09/2016.
 */

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.HashMap;
import java.util.Map;


public class EditarCliente extends Activity {

    private EditText nome, email, telefone, dados, rua, bairo, cidade;
    private Spinner estados, categoria;
    private Button salvar, deletar;
    private Cursor cursor;
    private BancoController crud;
    private String codigo;
    private Map<String,String> dadosCliente = new HashMap<String, String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editar_cliente);

        codigo = this.getIntent().getStringExtra("codigo");
        crud = new BancoController(getBaseContext());

        nome = (EditText)findViewById(R.id.altNome);
        email = (EditText)findViewById(R.id.altEmail);
        telefone = (EditText)findViewById(R.id.altTelefone);
        dados = (EditText)findViewById(R.id.altDados);
        rua = (EditText)findViewById(R.id.altRua);
        bairo = (EditText)findViewById(R.id.altBairro);
        cidade = (EditText)findViewById(R.id.altCidade);

        /*********************************************************************
         * CRIANDO AQUI A LISTA DE ESTADOS VINDOS DIRETO DO AQUIVO strings.xml*
         *********************************************************************/

        // Lista de estados
        estados = (Spinner) findViewById(R.id.altEstados);
        ArrayAdapter listaEstados = ArrayAdapter.createFromResource(this, R.array.estados, android.R.layout.simple_spinner_item);
        estados.setAdapter(listaEstados);

        // Lista de categorias
        categoria = (Spinner) findViewById(R.id.altCategoria);
        ArrayAdapter listaCategoria = ArrayAdapter.createFromResource(this, R.array.categorias, android.R.layout.simple_spinner_item);
        categoria.setAdapter(listaCategoria);



        cursor = crud.carregaDadoById(Integer.parseInt(codigo));
        nome.setText(cursor.getString(cursor.getColumnIndexOrThrow(CriaBanco.NOME)));
        email.setText(cursor.getString(cursor.getColumnIndexOrThrow(CriaBanco.EMAIL)));
        telefone.setText(cursor.getString(cursor.getColumnIndexOrThrow(CriaBanco.TELEFONE)));

        salvar = (Button)findViewById(R.id.altSalvar);
        salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dadosCliente.put("_id", codigo);
                dadosCliente.put("nome", nome.getText().toString());
                dadosCliente.put("email", email.getText().toString());
                dadosCliente.put("telefone", telefone.getText().toString());
                dadosCliente.put("dados",  dados.getText().toString());
                dadosCliente.put("rua", rua.getText().toString());
                dadosCliente.put("bairro", bairo.getText().toString());
                dadosCliente.put("cidade", cidade.getText().toString());
                dadosCliente.put("estado", estados.getSelectedItem().toString());
                dadosCliente.put("categoria", categoria.getSelectedItem().toString());

                crud.alteraRegistro(dadosCliente);
                Intent intent = new Intent(EditarCliente.this, ListaClientes.class);
                startActivity(intent);
                finish();
            }
        });

        deletar = (Button)findViewById(R.id.altDeletar);
        deletar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                crud.deletaRegistro(Integer.parseInt(codigo));
                Intent intent = new Intent(EditarCliente.this, ListaClientes.class);
                startActivity(intent);
                finish();
            }
        });
    }
}