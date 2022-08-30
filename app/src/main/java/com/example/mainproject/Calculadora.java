package com.example.mainproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Calculadora extends AppCompatActivity {
    EditText operacao;
    boolean pediuIgual = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculadora);
        this.operacao = findViewById(R.id.etOperacao);
    }

    public void esconderTeclado(View view) {
        View vw = this.getCurrentFocus();
        if (vw != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(vw.getWindowToken(), 0);
        }
    }

    public void digitoUm(View view) {
        if (pediuIgual) {
            operacao.setText("");
            pediuIgual = false;
        }
        this.addDigito("1");
    }

    public void digitoDois(View view) {
        if (pediuIgual) {
            operacao.setText("");
            pediuIgual = false;
        }
        this.addDigito("2");
    }

    public void digitoTres(View view) {
        if (pediuIgual) {
            operacao.setText("");
            pediuIgual = false;
        }
        this.addDigito("3");
    }

    public void digitoQuatro(View view) {
        if (pediuIgual) {
            operacao.setText("");
            pediuIgual = false;
        }
        this.addDigito("4");
    }

    public void digitoCinco(View view) {
        if (pediuIgual) {
            operacao.setText("");
            pediuIgual = false;
        }
        this.addDigito("5");
    }

    public void digitoSeis(View view) {
        if (pediuIgual) {
            operacao.setText("");
            pediuIgual = false;
        }
        this.addDigito("6");
    }

    public void digitoSete(View view) {
        if (pediuIgual) {
            operacao.setText("");
            pediuIgual = false;
        }
        this.addDigito("7");
    }

    public void digitoOito(View view) {
        if (pediuIgual) {
            operacao.setText("");
            pediuIgual = false;
        }
        this.addDigito("8");
    }

    public void digitoNove(View view) {
        if (pediuIgual) {
            operacao.setText("");
            pediuIgual = false;
        }
        this.addDigito("9");
    }

    public void digitoPonto(View view) {
        this.validarPonto();
        if (this.operacao.getText().toString().matches("[^0-9]"))
            this.calculoPrevio();
        this.addDigito(".");
    }

    public void digitoSoma(View view) {
        validarOperador();
        String operacaoText = this.operacao.getText().toString();
        if (operacaoText.contains("*") ||
                operacaoText.contains("/") ||
                operacaoText.contains("+") ||
                operacaoText.contains("-"))
            this.calculoPrevio();
        this.addDigito("+");

    }

    public void digitoSubtracao(View view) {
        validarOperador();
        String operacaoText = this.operacao.getText().toString();
        if (operacaoText.contains("*") ||
                operacaoText.contains("/") ||
                operacaoText.contains("+") ||
                operacaoText.contains("-"))
            this.calculoPrevio();
        this.addDigito("-");
    }

    public void digitoDivisao(View view) {
            validarOperador();
            String operacaoText = this.operacao.getText().toString();
            if (operacaoText.contains("*") ||
                    operacaoText.contains("/") ||
                    operacaoText.contains("+") ||
                    operacaoText.contains("-"))
                this.calculoPrevio();
            this.addDigito("/");
    }

    public void digitoMultiplicacao(View view) {
        validarOperador();
        String operacaoText = this.operacao.getText().toString();
        if (operacaoText.contains("*") ||
                operacaoText.contains("/") ||
                operacaoText.contains("+") ||
                operacaoText.contains("-"))
            this.calculoPrevio();
        this.addDigito("*");
    }

    public void calcular(View view) {
        if(this.validarIgual()){
            this.calculoPrevio();
            pediuIgual = true;
        }
    }

    private void calculoPrevio() {
        String operacaoText = operacao.getText().toString();
        String[] numerosTexto = operacaoText.split("[^.0-9]");

        String[] notNumber = operacaoText.split("[.0-9]");
        List<String> operadorList = Arrays.stream(notNumber).filter(x -> !x.equals("")).collect(Collectors.toList());
        String operador = operadorList.get(0);

        double[] numeros = new double[2];
        for (int i = 0; i < 2; i++) {
            numeros[i] = Double.parseDouble(numerosTexto[i]);
        }

        double result = 0;
        String resultText = "";

        switch (operador) {
            case "+":
                result = numeros[0] + numeros[1];
                resultText = this.returnResult(result);
                operacao.setText(resultText);
                break;
            case "-":
                result = numeros[0] - numeros[1];
                resultText = this.returnResult(result);
                operacao.setText(resultText);
                break;
            case "*":
                result = numeros[0] * numeros[1];
                resultText = this.returnResult(result);
                operacao.setText(resultText);
                break;
            case "/":
                if(this.validarDivisao()) {
                    result = numeros[0] / numeros[1];
                    resultText = this.returnResult(result);
                    operacao.setText(resultText);
                }
                else{
                    operacao.setText("ERRO");
                }
                break;
        }

    }

    private String returnResult(double valor) {
        String valorTexto = String.valueOf(valor);
        String[] valores = valorTexto.split("[.]");
        if (valores[1].equals("0"))
            return valores[0];
        else
            return valorTexto;
    }


    public void clear(View view) {
        String novaLinha = operacao.getText().toString();
        if (novaLinha.length() > 0)
            novaLinha = novaLinha.substring(0, novaLinha.length() - 1);
        operacao.setText(novaLinha);
    }


    public void voltar(View view) {
        finish();
    }

    private void addDigito(String valor) {
        if (operacao.getText().equals("ERRO"))
            operacao.setText(valor);
        operacao.setText(operacao.getText() + valor);
    }

    private void validarOperador() {
        String operacaoText = operacao.getText().toString();

        if (operacaoText.equals(""))
            operacao.setText("0");
        else if (operacaoText.indexOf("+") >= 0 && operacaoText.indexOf("+") == operacaoText.length() - 1 ||
                operacaoText.indexOf("-") >= 0 && operacaoText.indexOf("+") == operacaoText.length() - 1 ||
                operacaoText.indexOf("/") >= 0 && operacaoText.indexOf("+") == operacaoText.length() - 1 ||
                operacaoText.indexOf("*") >= 0 && operacaoText.indexOf("+") == operacaoText.length() - 1) {
            String operacaoSemCabeca = operacaoText.substring(0, operacaoText.length() - 1);
            operacao.setText(operacaoSemCabeca);
        }
    }

    private void validarPonto(){
        String operacaoText = operacao.getText().toString();
        String ultimaLetra = String.valueOf(operacaoText.charAt(operacaoText.length() - 1));

        if(operacaoText.equals("") ||
        !ultimaLetra.matches("[0-9]")){
            this.addDigito("0");
        }
    }

    private boolean validarIgual(){
        String operacaoText = operacao.getText().toString();
        String[] numerosTexto = operacaoText.split("[^.0-9]");

        String[] notNumber = operacaoText.split("[.0-9]");
        List<String> operadorList = Arrays.stream(notNumber).filter(x -> !x.equals("")).collect(Collectors.toList());

        if(numerosTexto.length == 2 && operadorList.size() == 1)
            return true;
        else
            return false;
    }

    private boolean validarDivisao(){
        String operacaoText = operacao.getText().toString();
        String[] numerosTexto = operacaoText.split("[^.0-9]");

        if(numerosTexto[1].equals("0")){
            return false;
        }
        else {
            return true;
        }
    }


}