package com.example.luizeduardo.exercicio1;

/**
 * Created by Luiz Eduardo on 21/09/2016.
 */

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import java.util.ArrayList;

public class ListaClientes extends Activity {
    private ListView listView;
    private ArrayList<ItemListView> itens;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //carrega o layout onde contem o ListView.
        setContentView(R.layout.lista_clientes);

        //Pega a referencia do ListView
        listView = (ListView) findViewById(R.id.listView);

        //Criamos nossa lista que preenchera o ListView
        itens = new ArrayList<ItemListView>();

        //Iniciando a conexão com o o banco.
        BancoController crud = new BancoController(getBaseContext());

        // Carregando os dados do banco.
        final Cursor cursor = crud.carregaDados();
        cursor.moveToFirst();

        // Criando a lista de clientes.d
        while (cursor.isAfterLast() == false) {
            itens.add(new ItemListView(cursor.getString(cursor.getColumnIndexOrThrow(CriaBanco.NOME)),
                    cursor.getString(cursor.getColumnIndexOrThrow(CriaBanco.EMAIL)),
                    cursor.getString(cursor.getColumnIndexOrThrow(CriaBanco.CATEGORIA)), R.drawable.ic_launcher));
            cursor.moveToNext();
        }

        //Define o Adapter
        listView.setAdapter(new AdapterListView(this, itens));
        //Cor quando a lista é selecionada para ralagem.
        listView.setCacheColorHint(Color.TRANSPARENT);

        // Adicionando o evento em cada linha do listView.
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String codigo;
                cursor.moveToPosition(position);
                codigo = cursor.getString(cursor.getColumnIndexOrThrow(CriaBanco.ID));
                Intent intent = new Intent(ListaClientes.this, EditarCliente.class);
                intent.putExtra("codigo", codigo);
                startActivity(intent);
                finish();
            }
        });
    }
}