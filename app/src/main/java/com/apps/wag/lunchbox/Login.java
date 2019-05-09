package com.apps.wag.lunchbox;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Environment;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Login extends AppCompatActivity {

    private Button logIn;
    private TextInputEditText txtUsuario;
    private EditText txtContra;
    ArrayList<Usuario> listaUsuarioInfo = new ArrayList<>();
    SharedPreferences sesionPref;
    Intent intent;

    private static final String SD_PATH = Environment.getExternalStorageDirectory().getPath();
    private static final String APP_NAME_PATH = "com.wag.lunchbox/";
    private static final String APP_NAME = "Lunch Box";
    private static final String USERS_FILE = "users.lb";


    // Progress Dialog
    private ProgressDialog pDialog;
    // Creating JSON Parser object
    JSONParser jParser = new JSONParser();
    ArrayList<HashMap<String, String>> usersList;

    // url to get all products list
    private static String url_all_users = "https://darkreaperto.000webhostapp.com/lb_files/get_all_users.php";

    // JSON Node names
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_USERS = "users";
    private static final String TAG_COD = "cod";
    private static final String TAG_NAME = "name";

    // users JSONArray
    JSONArray users = null;

    private String codUsu = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        cargarUsuarios();
        //Array para verificar login
        //listaUsuarioInfo.add(new Usuario("usuario", "usuario2018"));

        sesionPref = getSharedPreferences("user_details", MODE_PRIVATE);

        logIn = (Button) findViewById(R.id.btnLogin);
        logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                verificarLogin();
            }
        });

    }

    private void openTabbedMain() {
        Intent inten = new Intent(Login.this, MainActivity.class);
        //inten.putExtra("correo", codUsu);//Agregar extra al intent para pasar el dato a otras activity

        if(sesionPref.contains("username") && sesionPref.contains("password")){
            startActivity(inten);
        }
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

        // Hashmap for ListView
        usersList = new ArrayList<HashMap<String, String>>();
        // Loading users in Background Thread
        new LoadAllUsers().execute();

//        System.out.println("DIRECTORIO: " + SD_PATH);
//
//        File file = new File(SD_PATH + "/Android/data/" + APP_NAME_PATH + USERS_FILE);
//        System.out.println(file);
////        appDirectory.mkdirs();
//        if (!file.exists()) {
//            return false;
//        }
//
//        FileInputStream fis = null;
//        try {
//            //fis = openFileInput(file);
//            fis = new FileInputStream(file);
//            InputStreamReader isr = new InputStreamReader(fis);
//            BufferedReader br = new BufferedReader(isr);
//            StringBuilder sb = new StringBuilder();
//            String lineaFile;
//
//            int cont = 0;
//            while ((lineaFile = br.readLine()) != null) {
//                String[] userinfo = lineaFile.toString().split(";"); //Separar lineas
//
//                Usuario user = new Usuario(userinfo[0], userinfo[1]);
//                listaUsuarioInfo.add(user);
//                System.out.println(listaUsuarioInfo.get(cont).getCorreo());
//                cont++;
//            }
//
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        } finally {
//
//        }
        return true;
    }
    private void verificarLogin() {
        boolean unregistered = true;
        txtUsuario = (TextInputEditText) findViewById(R.id.txtUsuario);
        txtContra = (EditText) findViewById(R.id.txtContra);

        if(!listaUsuarioInfo.isEmpty()) {
            String usercorreo = txtUsuario.getText().toString().trim();
            String password = txtContra.getText().toString();


            //Comprueba usuario
            for (Usuario u: listaUsuarioInfo) {
                if(usercorreo.equals(u.getCorreo())) {
                    if (u.getContrasenna().equals(password)) {
                        //SESSION THING
                        SharedPreferences.Editor editor = sesionPref.edit();
                        editor.putString("codigo", u.getCodigo());
                        editor.putString("usermail", usercorreo);
                        editor.putString("password", password);
                        editor.putString("username", u.getNomUsuario());
                        editor.putString("descripcion", u.getDescripcion());
                        editor.commit();

//                        Toast.makeText(this, "BIENVENIDO " + username,
//                                Toast.LENGTH_LONG).show();
                        openTabbedMain();
                        unregistered = false;
                        break;
                    }
                }
            }
            if(unregistered) {
                System.out.println("USUARIO NO REGISTRADO");
                Toast.makeText(this, "El usuario no está registrado",
                        Toast.LENGTH_LONG).show();
                Intent i = new Intent(getApplicationContext(), NewUserActivity.class);
                //Closing all previous activities
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
            }

        }
    }
    /**
     * Background Async Task to Load all users by making HTTP Request
     * */
    class LoadAllUsers extends AsyncTask<String, String, String> {

        /**
         * Before starting background thread Show Progress Dialog
         * */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(Login.this);
            pDialog.setMessage("Cargando usuarios. Por favor espere...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();
        }

        /**
         * getting All users from url
         * */
        protected String doInBackground(String... args) {
            // Building Parameters
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            // getting JSON string from URL
            JSONObject json = jParser.makeHttpRequest(url_all_users, "GET", params);

            // Check your log cat for JSON reponse
            Log.d("All Users: ", json.toString());

            try {
                // Checking for SUCCESS TAG
                int success = json.getInt(TAG_SUCCESS);

                if (success == 1) {
                    // users found
                    // Getting Array of Users
                    users = json.getJSONArray(TAG_USERS);

                    // looping through All Products
                    for (int i = 0; i < users.length(); i++) {
                        JSONObject c = users.getJSONObject(i);

                        // Storing each json item in variable
                        String id = c.getString(TAG_COD);
                        String name = c.getString(TAG_NAME);

                        // creating new HashMap
                        HashMap<String, String> map = new HashMap<String, String>();

                        // adding each child node to HashMap key => value
                        map.put(TAG_COD, id);
                        map.put(TAG_NAME, name);

                        // adding HashList to ArrayList
                        usersList.add(map);

                        Usuario user = new Usuario(c.getString("cod"),
                                c.getString("name"), c.getString("email"),
                                c.getString("password"), c.getString("description"));
                        listaUsuarioInfo.add(user);
                        System.out.println("USER: " +user.getCorreo()+" "+user.getContrasenna());
                    }
                } else {
                    // no users found
                    // Launch Add New user Activity
                    Intent i = new Intent(getApplicationContext(),
                            NewUserActivity.class);
                    // Closing all previous activities
                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(i);
                }

                System.out.println("===========================");
                System.out.println("===========================");
                System.out.println("===========================");
                for (int i=0; i<usersList.size(); i++) {
                    System.out.println(usersList.get(i));
                }
                System.out.println("===========================");
                System.out.println("===========================");
                System.out.println("===========================");
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        /**
         * After completing background task Dismiss the progress dialog
         * **/
        protected void onPostExecute(String file_url) {
            // dismiss the dialog after getting all products
            pDialog.dismiss();
            // updating UI from Background Thread
            runOnUiThread(new Runnable() {
                public void run() {
                    /**
                     * Updating parsed JSON data into ListView
                     * */
//                    ListAdapter adapter = new SimpleAdapter(
//                            AllUsersActivity.this, productsList,
//                            R.layout.list_item, new String[] { TAG_PID,
//                            TAG_NAME},
//                            new int[] { R.id.pid, R.id.name });
//                    // updating listview
//                    setListAdapter(adapter);
                }
            });

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
