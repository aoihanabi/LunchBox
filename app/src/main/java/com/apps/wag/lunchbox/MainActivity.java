package com.apps.wag.lunchbox;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private Button goRecipebook;
    private Button logIn;
    private TextInputEditText txtusuario;
    private EditText txtContra;
    ArrayList<String> listaUserInfo = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Array para verificar login
        listaUserInfo.add("usuario");
        listaUserInfo.add("usuario2018");


        //CLICK DE BOTONES
        goRecipebook = (Button) findViewById(R.id.btnGoRecipeBook);
        goRecipebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openRecipeBook();
            }
        });
        logIn = (Button) findViewById(R.id.logIn);
        logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verificarLogin();
            }
        });
    }

    //ABRIR ACTIVITIES
    private void openRecipeBook() {
        Intent inten = new Intent(this, MyRecipeBook.class);
        startActivity(inten);
    }
    private void openTabbedMain() {
        Intent inten = new Intent(this, principal.class);
        startActivity(inten);
    }

    private void verificarLogin() {

        txtusuario = (TextInputEditText) findViewById(R.id.txtUser);
        txtContra = (EditText) findViewById(R.id.txtContra);

        if(!listaUserInfo.isEmpty()) {
            String username = txtusuario.getText().toString();
            String password = txtContra.getText().toString();

            //Comprueba usuario
            if(username.equals(listaUserInfo.get(0)) &&
                    password.equals(listaUserInfo.get(1))) {
                openTabbedMain();
            } else {
                System.out.println("INCORRECTO USUARIO");
            }
        }


    }
}
