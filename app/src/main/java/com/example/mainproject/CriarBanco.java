package com.example.mainproject;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CriarBanco extends SQLiteOpenHelper {
    private static final String NOME_BANCO = "livraia.db";
    private static final String TABELA = "livros";
    private static final String ID = "id";
    private static final String TITULO = "titulo";
    private static final String AUTOR = "autor";
    private static final String EDITORA = "editora";
    private static final int VERSAO = 1;

    CriarBanco(Context context){
        super(context, NOME_BANCO, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + TABELA + " ("
                + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TITULO + " TEXT,"
                + AUTOR + " TEXT, "
                + EDITORA + " TEXT"
                + ");");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABELA);
        onCreate(sqLiteDatabase);
    }

    public static String getNomeBanco() {
        return NOME_BANCO;
    }

    public static String getTABELA() {
        return TABELA;
    }

    public static String getID() {
        return ID;
    }

    public static String getTITULO() {
        return TITULO;
    }

    public static String getAUTOR() {
        return AUTOR;
    }

    public static String getEDITORA() {
        return EDITORA;
    }

    public static int getVERSAO() {
        return VERSAO;
    }
}