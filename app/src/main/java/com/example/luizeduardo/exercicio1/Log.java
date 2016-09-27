package com.example.luizeduardo.exercicio1;

/**
 * Created by Luiz Eduardo on 27/09/2016.
 */

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Log {

    /**
     * Escreve a opareção realizada pelo usuário no arquivo de log.
     * @param id id do cliente o qual sofreu a operação.
     * @param operacao Operação realizada pelo usuário, sendo essas 'L'(leitura), 'E'(escrita), 'A'(alteração) e 'D'(exlusão).
     */
    public static void escrever(String id, String operacao) {

        SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
        try{
            BufferedWriter buffWrite = new BufferedWriter(new FileWriter("log.txt", true));
            buffWrite.append(id + "|"+ operacao + formatador.format(new Date()) + "\n");
            buffWrite.close();
        }catch (Exception e){
            System.err.println("PROBLEMAS AO GRAVAR INFORMAÇÕES NO ARQUIVO DE LOG: "+ e);
        }
    }

}