package com.apps.wag.lunchbox;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.app.ProgressDialog;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class NewRecipe extends AppCompatActivity {

    TextInputLayout inputRecipeName;
    EditText inputIngredientes;
    EditText inputPreparacion;
    Button createRecipe;
    SharedPreferences userPref;

    // Progress Dialog
    private ProgressDialog pDialog;
    private JSONParser jsonParser = new JSONParser();
    private static final String TAG_SUCCESS = "success";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_recipe);

        //SESIÓN ACTIVA
        userPref = getSharedPreferences("user_details", MODE_PRIVATE);
        inputRecipeName = (TextInputLayout) findViewById(R.id.txt_recipename);
        inputIngredientes = (EditText) findViewById(R.id.edtxt_ingredientes);
        inputPreparacion = (EditText) findViewById(R.id.edtxt_preparacion);

        createRecipe = (Button) findViewById(R.id.btnCreateRecipe);
        createRecipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new CreateNewRecipe().execute();
            }
        });
    }

    /**
     * Background Async Task to Create new recipe
     * */
    class CreateNewRecipe extends AsyncTask<String, String, String> {

        /**
         * Before starting background thread Show Progress Dialog
         * */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            pDialog = new ProgressDialog(NewRecipe.this);
            pDialog.setMessage("Cargando usuarios. Por favor espere...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();

        }

        /**
         * Creating user
         * */
        protected String doInBackground(String... args) {

            String recipeName = inputRecipeName.getEditText().getText().toString();
            String[] ingredientes = inputIngredientes.getText().toString().split("[\\r\\n]+");
            String[] preparacion = inputPreparacion.getText().toString().split("[\\r\\n]+");

            //OBTENER EL CODIGO DEL USUARIO EN SESIÓN
            String codUsuario = userPref.getString("codigo", null);
            // Building Parameters
            /*
                $_POST['title'], $_POST['duration'], $_POST['servings'],
                $_POST['keenOnCount'], $_POST['madeCount'],
                $_POST['rateAverage'], $_POST['rateStars'] {
             */
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("title", recipeName));
            params.add(new BasicNameValuePair("duration", "3 min"));
            params.add(new BasicNameValuePair("servings", "6"));
            params.add(new BasicNameValuePair("keenOnCount", "0"));
            params.add(new BasicNameValuePair("rateAverage", "0"));
            params.add(new BasicNameValuePair("rateStars", "0"));
            params.add(new BasicNameValuePair("ingredient", "No ingredients needed"));
            params.add(new BasicNameValuePair("steps" , "Nothing to do"));


            // check for success tag
            try {

                // getting JSON Object
                // Note that create recipe url accepts POST method
                JSONObject json = jsonParser.makeHttpRequest(GlobalLinks.url_create_recipe,
                        "POST", params);

                // check log cat for response
                Log.d("Create Response", json.toString());

                int success = json.getInt(TAG_SUCCESS);
                int last_id = json.getInt("last_id");

                if (success == 1) {

                    System.out.println("NwUsrAct: Recipe creation succeded");

                    // Crear ingredientes para la receta
                    /*
                        $_POST['cod_recipe']) && isset($_POST['nom']
                    */
//                    for (String ing: ingredientes) {
//                        params = new ArrayList<NameValuePair>();
//                        params.add(new BasicNameValuePair("cod_recipe", String.valueOf(last_id)));
//                        params.add(new BasicNameValuePair("nom", ing));
//
//                        json = jsonParser.makeHttpRequest(GlobalLinks.url_create_ingredient,
//                                "POST", params);
//                    }

                    // Crear instrucciones para la receta
                    /*
                        $_POST['cod_recipe'], $_POST['order'], $_POST['description']
                    */
//                    for (int i=0; i<preparacion.length; i++) {
//                        params = new ArrayList<NameValuePair>();
//                        params.add(new BasicNameValuePair("cod_recipe", String.valueOf(last_id)));
//                        params.add(new BasicNameValuePair("order", String.valueOf(i+1)));
//                        params.add(new BasicNameValuePair("description", preparacion[i]));
//
//                        json = jsonParser.makeHttpRequest(GlobalLinks.url_create_step,
//                                "POST", params);
//                    }


                    //Crear myRecipe
                    params = new ArrayList<NameValuePair>();
                    params.add(new BasicNameValuePair("cod_user", codUsuario));
                    params.add(new BasicNameValuePair("cod_recipe", String.valueOf(last_id)));
                    json = jsonParser.makeHttpRequest(GlobalLinks.url_create_myRecipe,
                            "POST", params);

                    // closing this screen
                    finish();
                } else {
                    // failed to create recipe
                    System.out.println("NwUsrAct: Recipe creation failed");
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        /**
         * After completing background task Dismiss the progress dialog
         * **/
        protected void onPostExecute(String file_url) {
            // dismiss the dialog once done
            pDialog.dismiss();
        }

    }
}
