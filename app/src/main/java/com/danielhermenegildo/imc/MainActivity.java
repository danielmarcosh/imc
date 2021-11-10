package com.danielhermenegildo.imc;

import android.content.Context;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.core.content.ContextCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.danielhermenegildo.imc.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private EditText pesoTxt;
    private EditText alturaTxt;
    private TextView resultadoTxtView;
    private Button calcularBtn;
    private Button limparBtn;
    private LinearLayout blocoResultado;
    private LinearLayout background;
    private double resultado = 0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pesoTxt = findViewById(R.id.peso);
        alturaTxt = findViewById(R.id.altura);
        resultadoTxtView = findViewById(R.id.resultado);

        calcularBtn = findViewById(R.id.calcular);
        limparBtn = findViewById(R.id.limpar);

        blocoResultado = findViewById(R.id.blocoResultado);
        background = findViewById(R.id.caixa);

        initialize();
    }

    private void initialize() {
        calcularBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calcularIMC();
                blocoResultado.setVisibility(View.VISIBLE);
                modificarBackground();
            }
        });
        limparBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removerConteudo();
                blocoResultado.setVisibility(View.INVISIBLE);
                background.setBackgroundColor(ContextCompat.getColor(MainActivity.this, R.color.cor_1));
            }
        });
    }

    private void calcularIMC() {
        double peso = recuperarValor(pesoTxt);
        double altura = recuperarValor(alturaTxt);
        double resultado = peso / Math.pow(altura, 2.0);
        this.resultado = resultado;
        resultadoTxtView.setText(String.format("%.1f", resultado));
    }

    private double recuperarValor(EditText campo) {
        String numbText = campo.getText().toString();

        if (numbText == null || numbText.isEmpty()) {
            return 1.0;
        } else {
            try {
                return Double.parseDouble(numbText);
            } catch (NumberFormatException e) {
                return 1.0;
            }
        }
    }

    private void removerConteudo() {
        pesoTxt.setText("");
        alturaTxt.setText("");
        resultadoTxtView.setText("");
        this.resultado = 0.0;
    }

    private void modificarBackground() {
        if (this.resultado < 16.0) {
            background.setBackgroundColor(ContextCompat.getColor(MainActivity.this, R.color.cor_1));
        } else if (this.resultado < 16.9) {
            background.setBackgroundColor(ContextCompat.getColor(MainActivity.this, R.color.cor_2));
        } else if (this.resultado < 18.4) {
            background.setBackgroundColor(ContextCompat.getColor(MainActivity.this, R.color.cor_3));
        } else if (this.resultado < 24.9) {
            background.setBackgroundColor(ContextCompat.getColor(MainActivity.this, R.color.cor_4));
        } else if (this.resultado < 29.9) {
            background.setBackgroundColor(ContextCompat.getColor(MainActivity.this, R.color.cor_5));
        } else if (this.resultado < 34.9) {
            background.setBackgroundColor(ContextCompat.getColor(MainActivity.this, R.color.cor_6));
        } else if (this.resultado < 39.9) {
            background.setBackgroundColor(ContextCompat.getColor(MainActivity.this, R.color.cor_7));
        } else if (this.resultado >= 40.0) {
            background.setBackgroundColor(ContextCompat.getColor(MainActivity.this, R.color.cor_8));
        } else {
            background.setBackgroundColor(ContextCompat.getColor(MainActivity.this, R.color.cor_1));
        }
    }
}