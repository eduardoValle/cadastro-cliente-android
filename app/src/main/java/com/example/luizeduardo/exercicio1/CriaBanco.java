package com.example.luizeduardo.exercicio1;

/**
 * Created by Luiz Eduardo on 20/09/2016.
 */

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class CriaBanco extends SQLiteOpenHelper {

    public CriaBanco(Context context){
        super(context, NOME_BANCO,null,VERSAO);
    }

    static final String NOME_BANCO = "banco.db";
    static final String TABELA = "clientes";
    static final String ID = "_id";
    static final String NOME = "nome";
    static final String EMAIL = "email";
    static final String TELEFONE = "telefone";
    static final String DADOS = "dados";
    static final String RUA = "rua";
    static final String BAIRRO = "bairro";
    static final String CIDADE = "cidade";
    static final String ESTADO = "estado";
    static final String CATEGORIA = "categoria";
    static final int VERSAO = 1;

    @Override
    public void onCreate(SQLiteDatabase db) {

        String sql = "CREATE TABLE "+ TABELA +" ("
                + ID + " integer primary key autoincrement,"
                + NOME + " text,"
                + EMAIL + " text,"
                + TELEFONE + " text,"
                + DADOS + " text,"
                + RUA + " text,"
                + BAIRRO + " text,"
                + CIDADE + " text,"
                + ESTADO + " text,"
                + CATEGORIA + " text"
                +")";

        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS" + TABELA);
        onCreate(db);
    }
}
