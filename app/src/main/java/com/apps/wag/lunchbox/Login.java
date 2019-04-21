package com.apps.wag.lunchbox;

import android.content.Intent;
import android.os.Environment;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Login extends AppCompatActivity {

    private Button logIn;
    private TextInputEditText txtUsuario;
    private EditText txtContra;
    ArrayList<Usuario> listaUsuarioInfo = new ArrayList<>();
    private static final String SD_PATH = Environment.getExternalStorageDirectory().getPath();
    private static final String APP_NAME_PATH = "com.wag.lunchbox/";
    private static final String APP_NAME = "Lunch Box";
    private static final String USERS_FILE = "users.lb";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Array para verificar login
        //listaUsuarioInfo.add(new Usuario("usuario", "usuario2018"));

        logIn = (Button) findViewById(R.id.btnLogin);
        logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cargarUsuarios();
                verificarLogin();
            }
        });
    }

    private void openTabbedMain() {
        Intent inten = new Intent(this, MainActivity.class);
        startActivity(inten);
    }

    public void save(View v) {

        String id = txtUsuario.getText().toString().trim();
        String name = txtContra.getText().toString().trim();

        FileOutputStream fos = null;

        try {
            String fullText = id + ";" + name + ";";

            fos = openFileOutput(APP_NAME, MODE_PRIVATE);
            fos.write(fullText.getBytes());

            txtUsuario.getText().clear();
            txtContra.getText().clear();

            Toast.makeText(this, "Saved to " + SD_PATH + "/Android/data/" +
                            APP_NAME_PATH +  APP_NAME, Toast.LENGTH_LONG).show();


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    /**
     * Cargar los usuarios leyendo desde el archivo de texto
     * @return
     */
    public boolean cargarUsuarios() {

        System.out.println("DIRECTORIO: " + SD_PATH);

        File file = new File(SD_PATH + "/Android/data/" + APP_NAME_PATH + USERS_FILE);
        System.out.println(file);
//        appDirectory.mkdirs();
        if (!file.exists()) {
            return false;
        }

        FileInputStream fis = null;
        try {
            //fis = openFileInput(file);
            fis = new FileInputStream(file);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String lineaFile;

            int cont = 0;
            while ((lineaFile = br.readLine()) != null) {
                String[] userinfo = lineaFile.toString().split(";"); //Separar lineas

                Usuario user = new Usuario(userinfo[0], userinfo[1]);
                listaUsuarioInfo.add(user);
                System.out.println(listaUsuarioInfo.get(cont).getCorreo());
                cont++;
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {

        }
        return true;
    }
    private void verificarLogin() {

        txtUsuario = (TextInputEditText) findViewById(R.id.txtUsuario);
        txtContra = (EditText) findViewById(R.id.txtContra);

        if(!listaUsuarioInfo.isEmpty()) {
            String username = txtUsuario.getText().toString();
            String password = txtContra.getText().toString();

            //Comprueba usuario
            for (Usuario u: listaUsuarioInfo) {
                if(username.equals(u.getCorreo())) {
                    if (u.getContrasenna().equals(password)) {
                        System.out.println("Usuario no registrado");
                        Toast.makeText(this, "El usuario no está registrado", Toast.LENGTH_LONG);
                        openTabbedMain();
                        break;
                    }
                }
            }
            System.out.println("Usuario no registrado");
            Toast.makeText(this, "El usuario no está registrado", Toast.LENGTH_LONG);
        }
    }
//    public void load(View v) {
//
//        FileInputStream fis = null;
//
//        try {
//
//            fis = openFileInput(FILE_NAME);
//            InputStreamReader isr = new InputStreamReader(fis);
//            BufferedReader br = new BufferedReader(isr);
//            StringBuilder sb = new StringBuilder();
//            String lineaFile;
//
//            while ((lineaFile = br.readLine()) != null) {
//                sb.append(lineaFile).append("\n");
//            }
//
//            String[] fullName = sb.toString().split(";");
//
//            txtId.setText(fullName[0]);
//            txtName.setText(fullName[1]);
//            txtSurname.setText(fullName[2]);
//
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            if (fis != null) {
//                try {
//                    fis.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    }

}
