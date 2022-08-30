package com.example.mainproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Login");
    }

    public void entrar(View view){
        EditText textLogin = findViewById(R.id.txLogin);
        EditText textSenha = findViewById(R.id.txSenha);

        if(textLogin.getText().toString().equals("") || textSenha.getText().toString().equals("")) {
            Toast.makeText(getApplicationContext(), "O campo login e senha devem estar preenchidos", Toast.LENGTH_LONG).show();
            return;
        }

        if(textLogin.getText().toString().equals("admin"))
            if(textSenha.getText().toString().equals("123")) {
                Intent newWindow = new Intent(getApplicationContext(), MainProgram.class);
                startActivity(newWindow);
                return;
            }
        Toast.makeText(getApplicationContext(), "Login ou Senha Incorretos", Toast.LENGTH_LONG).show();
    }

    public void cadastrar(View view){
        Intent newWindow = new Intent(getApplicationContext(), Cadastrar.class);
        startActivity(newWindow);
    }
}