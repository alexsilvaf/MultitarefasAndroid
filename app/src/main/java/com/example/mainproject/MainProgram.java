package com.example.mainproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainProgram extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_program);
        setTitle("Página Inicial");
        Toast.makeText(getApplicationContext(), "Logado com sucesso!", Toast.LENGTH_LONG).show();
    }

    public void goToCalculadora(View view){
        Intent newWindow = new Intent(getApplicationContext(), Calculadora.class);
        startActivity(newWindow);
        return;
    }

    public void sair(View view){
        finish();
    }
}