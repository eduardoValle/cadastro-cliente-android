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
    private Logs log;

    public BancoController(Context context){
        banco = new CriaBanco(context);
    }

    /**
     * Insere um cliente no banco de dados.
     * @param dados Hash Map contento os dados do cliente.
     * @return Mensagem de resposta da oparação.
     */
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
            //  log.escrever(dados.get(CriaBanco.NOME), "E");
            return "Registro inserido com sucesso!!";
    }

    /**
     * Pega no banco todos os clientes cadastrados.
     * @return todos os clientes cadastrados.
     */
    public Cursor carregaDados(){
        Cursor cursor;
        String[] campos = {CriaBanco.ID, CriaBanco.NOME, CriaBanco.EMAIL, CriaBanco.CATEGORIA};
        db = banco.getReadableDatabase();
        cursor = db.query(CriaBanco.TABELA, campos, null, null, null, null, null, null);

        if(cursor!=null){
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }

    /**
     * Procura no banco o cliente do id passado como parâmetro.
     * @param id do cliente a procurar.
     * @return dados do cliente procurado, se exixtir.
     */
    public Cursor carregaDadoById(int id){
        Cursor cursor;
        String[] campos =  {CriaBanco.ID, CriaBanco.NOME, CriaBanco.EMAIL, CriaBanco.TELEFONE, CriaBanco.DADOS,
                CriaBanco.RUA, CriaBanco.BAIRRO, CriaBanco.CIDADE, CriaBanco.ESTADO, CriaBanco.CATEGORIA};
        String where = CriaBanco.ID + "=" + id;
        db = banco.getReadableDatabase();
        cursor = db.query(CriaBanco.TABELA, campos, where, null, null, null, null, null);

        if(cursor!=null){
            cursor.moveToFirst();
            //  log.escrever(Integer.toString(id), "L");
        }
        db.close();
        return cursor;
    }

    /**
     * Altera o registro do cliente do id passado como parâmetro.
     * @param dados Hash Map contento os dados do cliente.
     * @return Mensagem de resposta da oparação.
     */
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
            //  log.escrever(dados.get(CriaBanco.ID), "A");
            return "Registro alterado com sucesso!!";
    }

    /**
     * Exclui o cliente do id passado como parâmetro.
     * @param id id do cliente a excluir.
     * @return Mensagem de resposta da oparação.
     */
    public String deletaRegistro(int id){

        long resultado;
        String where = CriaBanco.ID + "=" + id;
        db = banco.getReadableDatabase();
        resultado = db.delete(CriaBanco.TABELA,where,null);
        db.close();

        if (resultado ==-1)
            return "Erro ao apagar registro!!";
        else
            // log.escrever(Integer.toString(id), "D");
            return "Registro apagado com sucesso!!";
    }
}
