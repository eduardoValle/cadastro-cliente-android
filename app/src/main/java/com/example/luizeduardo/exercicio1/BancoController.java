package com.example.luizeduardo.exercicio1;

/**
 * Created by Luiz Eduardo on 20/09/2016.
 */

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.content.Context;
import java.util.Map;

public class BancoController {

    private SQLiteDatabase db;
    private CriaBanco banco;

    public BancoController(Context context){
        banco = new CriaBanco(context);
    }

    public String inserirDados(Map<String,String> dados){

        ContentValues valores;
        long resultado;

        db = banco.getWritableDatabase();
        valores = new ContentValues();
        valores.put(CriaBanco.NOME, dados.get(CriaBanco.NOME));
        valores.put(CriaBanco.EMAIL, dados.get(CriaBanco.EMAIL));
        valores.put(CriaBanco.TELEFONE, dados.get(CriaBanco.TELEFONE));
        valores.put(CriaBanco.DADOS, dados.get(CriaBanco.DADOS));
        valores.put(CriaBanco.RUA, dados.get(CriaBanco.RUA));
        valores.put(CriaBanco.BAIRRO, dados.get(CriaBanco.BAIRRO));
        valores.put(CriaBanco.CIDADE, dados.get(CriaBanco.CIDADE));
        valores.put(CriaBanco.ESTADO, dados.get(CriaBanco.ESTADO));
        valores.put(CriaBanco.CATEGORIA, dados.get(CriaBanco.CATEGORIA));

        resultado = db.insert(CriaBanco.TABELA, null, valores);
        db.close();

        if (resultado ==-1)
            return "Erro ao inserir registro!!";
        else
            return "Registro inserido com sucesso!!";
    }

    public Cursor carregaDados(){
        Cursor cursor;
        String[] campos = {CriaBanco.ID, CriaBanco.NOME};
        db = banco.getReadableDatabase();
        cursor = db.query(CriaBanco.TABELA, campos, null, null, null, null, null, null);

        if(cursor!=null){
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }

    public Cursor carregaDadoById(int id){
        Cursor cursor;
        String[] campos =  {CriaBanco.ID, CriaBanco.NOME, CriaBanco.EMAIL, CriaBanco.TELEFONE, CriaBanco.DADOS,
                CriaBanco.RUA, CriaBanco.BAIRRO, CriaBanco.CIDADE, CriaBanco.ESTADO, CriaBanco.CATEGORIA};
        String where = CriaBanco.ID + "=" + id;
        db = banco.getReadableDatabase();
        cursor = db.query(CriaBanco.TABELA, campos, where, null, null, null, null, null);

        if(cursor!=null){
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }

    public String alteraRegistro(Map<String,String> dados){ContentValues valores;

        long resultado;
        String where;
        db = banco.getWritableDatabase();
        where = CriaBanco.ID + "=" + dados.get(CriaBanco.ID);

        valores = new ContentValues();
        valores.put(CriaBanco.NOME, dados.get(CriaBanco.NOME));
        valores.put(CriaBanco.EMAIL, dados.get(CriaBanco.EMAIL));
        valores.put(CriaBanco.TELEFONE, dados.get(CriaBanco.TELEFONE));
        valores.put(CriaBanco.DADOS, dados.get(CriaBanco.DADOS));
        valores.put(CriaBanco.RUA, dados.get(CriaBanco.RUA));
        valores.put(CriaBanco.BAIRRO, dados.get(CriaBanco.BAIRRO));
        valores.put(CriaBanco.CIDADE, dados.get(CriaBanco.CIDADE));
        valores.put(CriaBanco.ESTADO, dados.get(CriaBanco.ESTADO));
        valores.put(CriaBanco.CATEGORIA, dados.get(CriaBanco.CATEGORIA));

        resultado = db.update(CriaBanco.TABELA,valores,where,null);
        db.close();

        if (resultado ==-1)
            return "Erro ao alterar registro!!";
        else
            return "Registro alterado com sucesso!!";
    }

    public String deletaRegistro(int id){

        long resultado;
        String where = CriaBanco.ID + "=" + id;
        db = banco.getReadableDatabase();
        resultado = db.delete(CriaBanco.TABELA,where,null);
        db.close();

        if (resultado ==-1)
            return "Erro ao apagar registro!!";
        else
            return "Registro apagado com sucesso!!";
    }
}
