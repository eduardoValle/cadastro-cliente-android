package com.example.luizeduardo.exercicio1;

/**
 * Created by Luiz Eduardo on 20/09/2016.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import java.util.HashMap;
import java.util.Map;

public class CadastrarCliente extends AppCompatActivity {

    private EditText txtNome, txtEmail, txtTelefone, txtDados, txtRua, txtBairo, txtCidade;
    private Spinner txtEstados, txtCategoria;
    private Button btnSalvar, btnLimpar;
    private Map<String,String> dados = new HashMap<String, String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cadastrar_cliente);

        // INICIANDO OS CAMPOS CRIADOS NO ARQUIVO activity_main.xml
        txtNome = (EditText) findViewById(R.id.txtNome);
        txtEmail = (EditText) findViewById(R.id.txtEmail);
        txtTelefone = (EditText) findViewById(R.id.txtTelefone);
        txtDados = (EditText) findViewById(R.id.txtDados);
        txtRua = (EditText) findViewById(R.id.txtRua);
        txtBairo = (EditText) findViewById(R.id.txtBairro);
        txtCidade = (EditText) findViewById(R.id.txtCidade);

        /**
         * CRIANDO AQUI Os COMBOBOX DE ESTADOS VINDOS DIRETO DO AQUIVO strings.xml
         */

        // ComboBox de estados
        txtEstados = (Spinner) findViewById(R.id.txtEstados);
        ArrayAdapter listaEstados = ArrayAdapter.createFromResource(this, R.array.estados, android.R.layout.simple_spinner_item);
        txtEstados.setAdapter(listaEstados);

        // ComboBox de categorias
        txtCategoria = (Spinner) findViewById(R.id.txtCategoria);
        ArrayAdapter listaCategoria = ArrayAdapter.createFromResource(this, R.array.categorias, android.R.layout.simple_spinner_item);
        txtCategoria.setAdapter(listaCategoria);


        /**
         * INICIANDO OS BOTÕES CRIADOS E APLICANDO EVENTOS.
         */

        // Botão de salvar dados.
        btnSalvar = (Button) findViewById(R.id.btnSalvar);
        btnSalvar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                BancoController crud = new BancoController(getBaseContext());

                dados.put("nome", txtNome.getText().toString());
                dados.put("email", txtEmail.getText().toString());
                dados.put("telefone", txtTelefone.getText().toString());
                dados.put("dados",  txtDados.getText().toString());
                dados.put("rua", txtRua.getText().toString());
                dados.put("bairro", txtBairo.getText().toString());
                dados.put("cidade", txtCidade.getText().toString());
                dados.put("estado", txtEstados.getSelectedItem().toString());
                dados.put("categoria", txtCategoria.getSelectedItem().toString());

                if(!checarCampos()) {
                    String resultado = crud.inserirDados(dados);
                    Toast.makeText(getApplicationContext(), resultado, Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(getBaseContext(), ListaClientes.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(getApplicationContext(), "Os campos de NOME, EMAIL e TELEFONE não podem estar em branco.", Toast.LENGTH_LONG).show();
                }
            }
        });

        // Botão de salvar dados.
        btnLimpar = (Button) findViewById(R.id.btnLimpar);
        btnLimpar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtNome.setText("");
                txtEmail.setText("");
                txtTelefone.setText("");
                txtDados.setText("");
                txtRua.setText("");
                txtBairo.setText("");
                txtCidade.setText("");
            }
        });
    }

    /**
     * Checa se os campos de NOME, EMAIL e telefone estão em branco.
     * @return true se estão em branco, false se não estão
     */
    public boolean checarCampos(){

        if(txtNome.getText().toString() == "" || txtEmail.getText().toString() == "" || txtTelefone.getText().toString() == ""){
            return true;
        }else{
            return false;
        }
    }
}