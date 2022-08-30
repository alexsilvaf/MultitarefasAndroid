package com.example.mainproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class BancoController {
    private SQLiteDatabase db;
    private CriarBanco banco;

    public BancoController(Context context){
        banco = new CriarBanco(context);
    }

    public String inserir(String titulo, String autor, String editora){
        ContentValues valores;
        long result;

        db = banco.getWritableDatabase();

        valores = new ContentValues();
        valores.put(CriarBanco.getTITULO(), titulo);
        valores.put(CriarBanco.getAUTOR(), autor);
        valores.put(CriarBanco.getEDITORA(), editora);

        result = db.insert(CriarBanco.getTABELA(), null, valores);;
        db.close();

        if(result == -1){
            return "Erro ao inserir resultado";
        } else {
            return "Registro inserido com sucesso";
        }
    }
}
