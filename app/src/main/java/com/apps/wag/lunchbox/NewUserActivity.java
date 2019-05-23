package com.apps.wag.lunchbox;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class NewUserActivity extends Activity {

    // Progress Dialog
    private ProgressDialog pDialog;

    JSONParser jsonParser = new JSONParser();
//    public EditText inputName;
//    EditText inputEmail;
//    EditText inputPassword;
//    EditText inputDescripcion;

    String inputName;
    String inputEmail;
    String inputPassword;
    String inputDescripcion;

    // JSON Node names
    private static final String TAG_SUCCESS = "success";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.add_user);

        // Edit Text
//        inputName = (EditText) findViewById(R.id.inputName);
//        inputEmail = (EditText) findViewById(R.id.inputEmail);
//        inputDescripcion = (EditText) findViewById(R.id.inputDescripcion);
        inputName = "Ana";
        inputEmail = "ana@gmail.com";
        inputPassword = "1234";
        inputDescripcion = "Ana la anita";

        new CreateNewUser().execute();
        // Create button
//        Button btnCreateProduct = (Button) findViewById(R.id.btnCreateProduct);

        // button click event
//        btnCreateProduct.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View view) {
//                // creating new user in background thread
//                new CreateNewUser().execute();
//            }
//        });
    }

    /**
     * Background Async Task to Create new user
     * */
    class CreateNewUser extends AsyncTask<String, String, String> {

        /**
         * Before starting background thread Show Progress Dialog
         * */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        /**
         * Creating user
         * */
        protected String doInBackground(String... args) {
            String name = inputName;//.getText().toString();
            String email = inputEmail;//.getText().toString();
            String password = inputPassword;//.getText().toString();
            String description = inputDescripcion;//.getText().toString();

            // Building Parameters
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            params.add(new BasicNameValuePair("name", name));
            params.add(new BasicNameValuePair("email", email));
            params.add(new BasicNameValuePair("password", password));
            params.add(new BasicNameValuePair("description", description));

            // getting JSON Object
            // Note that create user url accepts POST method
            JSONObject json = jsonParser.makeHttpRequest(GlobalLinks.url_create_user,
                    "POST", params);

            // check log cat for response
            Log.d("Create Response", json.toString());

            // check for success tag
            try {
                int success = json.getInt(TAG_SUCCESS);

                if (success == 1) {
                    // successfully created user
                    Intent i = new Intent(getApplicationContext(), Login.class);//AllUsersActivity.class ir al login luego de crear el nuevo usuario
                    startActivity(i);

                    System.out.println("NwUsrAct: User creation succeded");
                    // closing this screen
                    finish();
                } else {
                    // failed to create user
                    System.out.println("NwUsrAct: User creation failed");
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
//            pDialog.dismiss();
        }

    }
}