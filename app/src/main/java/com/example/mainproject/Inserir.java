package com.example.mainproject;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Inserir extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_livro);

        Button botao = findViewById(R.id.btCadastrarLivro);

        botao.setOnClickListener(view -> {
            BancoController crud = new BancoController(getBaseContext());
            EditText titulo = findViewById(R.id.etTitulo);
            EditText autor = findViewById(R.id.etAutor);
            EditText editora = findViewById(R.id.etEditora);

            String tituloString = titulo.getText().toString();
            String autorString = autor.getText().toString();
            String editoraString = editora.getText().toString();
            String resultado;

            resultado = crud.inserir(tituloString, autorString, editoraString);
            Toast.makeText(getApplicationContext(), resultado, Toast.LENGTH_LONG).show();
        });
    }
}
