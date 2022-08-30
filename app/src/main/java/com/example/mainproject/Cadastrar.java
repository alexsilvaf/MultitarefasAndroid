package com.example.mainproject;

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

public class Cadastrar extends AppCompatActivity {
    EditText nome;
    EditText email;
    EditText senha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar);
        nome = findViewById(R.id.etNome);
        email = findViewById(R.id.etEmail);
        senha = findViewById(R.id.etSenha);

        nome.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String nomeTexto = editable.toString();

                if (nomeTexto != null && !nomeTexto.isEmpty()) {
                    int selectionIndex = nome.getSelectionStart();
                    if (nomeTexto.length() == 1) {
                        if (!nomeTexto.matches("[A-Z]")) {
                            nomeTexto = nomeTexto.toUpperCase();
                            nome.setText(nomeTexto);
                        }
                    } else {
                        String[] nomeList = nomeTexto.split(" ");
                        String novoNome = nomeList[0];
                        if (nomeList.length != 0) {
                            boolean alterado = false;
                            for (String obj : nomeList) {
                                String primeiraLetra = obj.substring(0, 1);
                                if (!primeiraLetra.matches("[A-Z]")) {
                                    String nomeSemCabeca = obj.substring(1);
                                    obj = primeiraLetra.toUpperCase() + nomeSemCabeca.toLowerCase();
                                    alterado = true;
                                }
                                if (nomeList[0].equals(obj)) {
                                    novoNome = obj;
                                } else {
                                        novoNome += " " + obj;
                                }
                                if (alterado) {
                                    nome.setText(novoNome);
                                }
                            }
                        }
                    }
                    nome.setSelection(selectionIndex);
                    return;
                }
            }
        });
    }

    public void voltar(View view) {
        finish();
    }

    public void cadastrar(View view) {
        String nomeText = nome.getText().toString();
        String emailText = email.getText().toString();
        String senhaText = senha.getText().toString();

        if (!isFilled(Arrays.asList(nomeText, emailText, senhaText))) {
            Toast.makeText(getApplicationContext(), "Todos os campos devem estar preenchidos.", Toast.LENGTH_LONG).show();
            return;
        }

        if (!validateName(nomeText)) {
            Toast.makeText(getApplicationContext(), "Por favor, informe um nome válido!", Toast.LENGTH_LONG).show();
            return;
        }


        if (!validateEmail(emailText)) {
            Toast.makeText(getApplicationContext(), "Por favor, informe um e-mail válido!", Toast.LENGTH_LONG).show();
            return;
        }

        if (!validatePassword(senhaText)) {
            Toast.makeText(getApplicationContext(), "A senha deve ter pelo menos: \n1 - Caractere Minúsculo [a-z]\n1 - Caractere Maiúsculo [A-Z]\n1 - Caractere Especial (Exemplo: @,#,!)\n1 - Numero que não seja sequencial [1,2,3]", Toast.LENGTH_LONG).show();
            return;
        }

    }

    private boolean validarCaracteres(String texto) {
        return (texto.length() != texto.split("[a-z]").length);
    }

    private boolean validarSetor(String text) {
        return text.equals("") && text.length() > 10 || !this.validarCaracteres(text) && !text.endsWith("[a-z]");
    }

    private boolean isFilled(List<String> fieldList) {
        for (int i = 0; i < fieldList.size(); i++) {
            if (fieldList.get(i).equals("")) {
                return false;
            }
        }
        return true;
    }

    private boolean validateName(String user) {
        Pattern p = Pattern.compile("[^a-zA-Z 0-9]");
        Matcher m = p.matcher(user);
        if (m.find())
            return false;

        return true;
    }

    public void fixName() {
        String nome = this.nome.getText().toString();
        String penultCaractere = "";
        String lastCaractere;

        if (nome.length() >= 2) {
            penultCaractere = nome.substring(nome.length() - 2, 1);
        }
        lastCaractere = nome.substring(nome.length() - 1, 1);

        if (penultCaractere.equals(" ") || nome.length() == 1) {
            lastCaractere = lastCaractere.toUpperCase();
            String finalName = nome.substring(0, nome.length() - 1);
            finalName += lastCaractere;
            this.nome.setText(finalName);
        }

        if (lastCaractere.equals(" ") && nome.length() == 1) {
            this.nome.setText("");
        }
    }

    private boolean validateEmail(String email) {


        if (!email.contains("@")) {
            return false;
        }

        String[] validateEmail = email.split("@");

        if (validateEmail.length != 2 || validateEmail[0].equals("") || validateEmail[1].equals("") || email.endsWith("@")) {
            return false;
        }

        if (!validateEmail[1].contains(".")) {
            return false;
        }

        boolean verificarDpsPonto2 = false;
        boolean verificarDpsPonto3 = false;
        boolean verificarDpsPonto4 = false;

        String[] finalMail = validateEmail[1].split("[.]");

        boolean verificarDominio = finalMail[0].equals("") || finalMail[0].length() < 2 || !this.validarCaracteres(finalMail[0]);
        boolean verificarDpsPonto1 = finalMail[1].equals("") || finalMail[1].length() > 10 || !this.validarCaracteres(finalMail[1]);

        if (finalMail.length > 2)
            verificarDpsPonto2 = validarSetor(finalMail[2]);
        if (finalMail.length > 3)
            verificarDpsPonto3 = validarSetor(finalMail[3]);
        if (finalMail.length > 4)
            verificarDpsPonto4 = validarSetor(finalMail[4]);
        if (finalMail.length == 0 ||
                finalMail.length > 5 ||
                verificarDominio ||
                verificarDpsPonto1 ||
                verificarDpsPonto2 ||
                verificarDpsPonto3 ||
                verificarDpsPonto4) {
            return false;
        }
        return true;
    }

    private boolean validatePassword(String password) {
        Pattern p = Pattern.compile("[A-Z]");
        Matcher m = p.matcher(password);
        if (!m.find())
            return false;


        p = Pattern.compile("[a-z]");
        m = p.matcher(password);
        if (!m.find())
            return false;

        p = Pattern.compile("[0-9]");
        m = p.matcher(password);
        if (!m.find())
            return false;

        p = Pattern.compile("[^a-zA-Z 0-9]");
        m = p.matcher(password);
        if (!m.find())
            return false;

        if (password.length() < 6)
            return false;

        for (int i = 0; i < password.length(); i++) {
            Character currentCharacter = password.charAt(i);
            Character nextCharacter = null;
            if (tryParseToInt(currentCharacter)) {
                int currentCharacterValue = Integer.parseInt(currentCharacter.toString());
                if (i + 1 < password.length()) {
                    nextCharacter = password.charAt(i + 1);
                    if (tryParseToInt(nextCharacter)) {
                        int numeroSeguinte = Integer.parseInt(nextCharacter.toString());
                        if (numeroSeguinte == currentCharacterValue + 1)
                            return false;

                    }
                }
            }
        }
        return true;
    }

    private boolean tryParseToInt(Character c) {
        try {
            Integer.parseInt(c.toString());
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}