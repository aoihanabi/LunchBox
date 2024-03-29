package com.apps.wag.lunchbox;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import org.apache.http.NameValuePair;
import org.apache.http.io.SessionOutputBuffer;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SavedRecipes extends AppCompatActivity {

    ListView listaRecetas;
    SharedPreferences userPref;

    //ESPECIFICOS PARA EL PARSEO
    // JSON Node names
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_RECIPES = "savedrecipes";
    JSONArray recipes = null;
    private ProgressDialog pDialog;
    JSONParser jParser = new JSONParser();
    ArrayList<Recipes> recetas = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_recipes);

        listaRecetas = (ListView) findViewById(R.id.listMyRecipebook);
        //SESIÓN ACTIVA
        userPref = getSharedPreferences("user_details", MODE_PRIVATE);

        new LoadAllRecipes(this, listaRecetas).execute();
    }

    /**
     * Background Async Task to Load all recipes by making HTTP Request
     * */
    class LoadAllRecipes extends AsyncTask<String, String, String> {

        Activity myContext;
        ListView listView;

        public LoadAllRecipes(Activity context, ListView listview) {
            this.myContext = context;
            this.listView = listview;
        }
        /**
         * Before starting background thread Show Progress Dialog
         * */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(myContext);
            pDialog.setMessage("Cargando recetas. Por favor espere...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();
        }

        /**
         * getting All recipes from url
         * */
        protected String doInBackground(String... args) {
            // Building Parameters
            List<NameValuePair> params = new ArrayList<NameValuePair>();

            try {
                //OBTENER EL CODIGO DEL USUARIO EN SESIÓN
                String codUsuario = userPref.getString("codigo", null);
                System.out.println("EL CODIGO DE USUARIO A ENVIAR ES: " + codUsuario);
                //Enviarle el código al php
                params.add(new BasicNameValuePair("cod", codUsuario));
                // getting JSON string from URL
                JSONObject json = jParser.makeHttpRequest(GlobalLinks.url_get_savedrecipes,"POST", params);

                // Check your log cat for JSON reponse
                Log.d("All Recipes: ", json.toString());

                // Checking for SUCCESS TAG
                int success = json.getInt(TAG_SUCCESS);


                if (success == 1) {
                    // recipes found
                    // Getting Array of Users
                    recipes = json.getJSONArray(TAG_RECIPES);

                    // looping through All Recipes
                    System.out.println("SUCCESS - CANTIDAD RECETAS" + recipes.length());
                    for (int i = 0; i < recipes.length(); i++) {

                        JSONObject c = recipes.getJSONObject(i);

                        Usuario usuario = new Usuario(c.getString("cod_users"),
                                c.getString("name"), c.getString("email"),
                                c.getString("password"), c.getString("description"));

                        Recipes recipe = new Recipes(c.getInt("cod"),
                                c.getString("title"), c.getString("duration"),
                                c.getString("servings"), c.getInt("keenOnCount"),
                                (float) c.getDouble("rateAverage"),c.getInt("rateStars"),
                                R.drawable.fresas, usuario, c.getString("ingredient"),
                                c.getString("steps"));

                        recetas.add(recipe);
                    }
                }
                System.out.println("+++++++++++++++++++++++++++");
                for (int i=0; i<recetas.size(); i++) {
                    System.out.println(recetas.get(i));
                }
                System.out.println("+++++++++++++++++++++++++++");
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

                    System.out.println("POST EXECUTE");
                    System.out.println("LISTA RECETAS ++++++++++ " + recetas.size());
                    //Actualizar ListView
                    RecipeListAdapter adaptador =
                            new RecipeListAdapter(SavedRecipes.this,
                                    R.layout.layout_recipebook_adapter_listview, recetas);

                    listaRecetas.setAdapter(adaptador);

                }
            });


        }

    }
}
