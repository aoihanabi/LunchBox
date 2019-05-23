package com.apps.wag.lunchbox;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


///**
// * A simple {@link Fragment} subclass.
// * Activities that contain this fragment must implement the
// * {@link FragInicio.OnFragmentInteractionListener} interface
// * to handle interaction events.
// * Use the {@link FragInicio#newInstance} factory method to
// * create an instance of this fragment.
// */
public class FragInicio extends Fragment {

    private RecyclerView rvwRecipeIni;
    private CardviewInicioAdapter adapter;
    private Button btnKeen;

    //ESPECIFICOS PARA EL PARSEO
    // JSON Node names
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_RECIPES = "recipes";
    // recipes JSONArray
    JSONArray recipes = null;
    // Progress Dialog
    private ProgressDialog pDialog;
    // Creating JSON Parser object
    JSONParser jParser = new JSONParser();
    ArrayList<HashMap<String, String>> hashListRecipe;
    ArrayList<Recipes> recetas = new ArrayList<>();
    // url to get all products list
    //private static String url_all_recipes = "https://darkreaperto.000webhostapp.com/lb_files/get_all_recipes.php";
    private static String url_all_recipes = GlobalLinks.url_all_recipes;

//    // TODO: Rename parameter arguments, choose names that match
//    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//    private static final String ARG_PARAM1 = "param1";
//    private static final String ARG_PARAM2 = "param2";
//
//    // TODO: Rename and change types of parameters
//    private String mParam1;
//    private String mParam2;
//
//    private OnFragmentInteractionListener mListener;
//
//    public FragInicio() {
//        // Required empty public constructor
//    }
//
//    /**
//     * Use this factory method to create a new instance of
//     * this fragment using the provided parameters.
//     *
//     * @param param1 Parameter 1.
//     * @param param2 Parameter 2.
//     * @return A new instance of fragment FragInicio.
//     */
//    // TODO: Rename and change types and number of parameters
//    public static FragInicio newInstance(String param1, String param2) {
//        FragInicio fragment = new FragInicio();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
//        return fragment;
//    }
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.frag2_inicio, container, false);

        rvwRecipeIni = (RecyclerView) rootView.findViewById(R.id.rvw_inicio);
        rvwRecipeIni.setLayoutManager(new LinearLayoutManager(getActivity()));

//        adapter = new CardviewInicioAdapter(getActivity(), dataSet());
//        rvwRecipeIni.setAdapter(adapter);
        new LoadAllRecipes(getActivity(), rvwRecipeIni).execute();


        return rootView;
    }

    @Override
    @Nullable
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        btnKeen = (Button) view.findViewById(R.id.btn_keenOnReceta);
        btnKeen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Llamar procedimiento que actualice la cantidad de me gusta de la receta

            }
        });
    }
//    private ArrayList<Recipes> dataSet() {
//
////        ArrayList<Recipes> data = new ArrayList<>();
////        data.add(new Recipes(1, "Fresa", "30 min", "4 platos", 90, 10, 3.7f, 4, R.drawable.fresas));
////        data.add(new Recipes(2, "Frese", "30 min", "4 platos", 90, 10, 3.7f, 4, R.drawable.fresas));
////        data.add(new Recipes(3, "Fresi", "30 min", "4 platos", 90, 10, 3.7f, 4, R.drawable.fresas));
////        data.add(new Recipes(4, "Freso", "30 min", "4 platos", 90, 10, 3.7f, 4, R.drawable.fresas));
////        data.add(new Recipes(5, "Fresu", "30 min", "4 platos", 90, 10, 3.7f, 4, R.drawable.fresas));
////        data.add(new Recipes(6, "Fresa", "30 min", "4 platos", 90, 10, 3.7f, 4, R.drawable.fresas));
////        data.add(new Recipes(7, "Frese", "30 min", "4 platos", 90, 10, 3.7f, 4, R.drawable.fresas));
////        data.add(new Recipes(8, "Fresi", "30 min", "4 platos", 90, 10, 3.7f, 4, R.drawable.fresas));
////        data.add(new Recipes(9, "Freso", "30 min", "4 platos", 90, 10, 3.7f, 4, R.drawable.fresas));
//
//        return data;
//    }

    // TODO: Rename method, update argument and hook method into UI event
//    public void onButtonPressed(Uri uri) {
//        if (mListener != null) {
//            mListener.onFragmentInteraction(uri);
//        }
//    }


//    public void onAttach(Context context) {
//        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
//    }
//
//    @Override
//    public void onDetach() {
//        super.onDetach();
//        mListener = null;
//    }
//
//    /**
//     * This interface must be implemented by activities that contain this
//     * fragment to allow an interaction in this fragment to be communicated
//     * to the activity and potentially other fragments contained in that
//     * activity.
//     * <p>
//     * See the Android Training lesson <a href=
//     * "http://developer.android.com/training/basics/fragments/communicating.html"
//     * >Communicating with Other Fragments</a> for more information.
//     */
//    public interface OnFragmentInteractionListener {
//        // TODO: Update argument type and name
//        void onFragmentInteraction(Uri uri);
//    }

    /**
     * Background Async Task to Load all recipes by making HTTP Request
     * */
    class LoadAllRecipes extends AsyncTask<String, String, String> {

        Activity myContext;
        RecyclerView recyclerView;

        public LoadAllRecipes(Activity context, RecyclerView rview) {
            this.myContext = context;
            this.recyclerView = rview;
        }
        /**
         * Before starting background thread Show Progress Dialog
         * */
        @Override
        protected void onPreExecute() {

            super.onPreExecute();
            System.out.println("En PREexecute, SALE ALGO");
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
            // getting JSON string from URL
            JSONObject json = jParser.makeHttpRequest(url_all_recipes, "GET", params);

            // Check your log cat for JSON reponse
            Log.d("All Recipes: ", json.toString());

            try {
                // Checking for SUCCESS TAG
                int success = json.getInt(TAG_SUCCESS);

                if (success == 1) {
                    // recipes found
                    // Getting Array of Users
                    recipes = json.getJSONArray(TAG_RECIPES);

                    // looping through All Products
                    for (int i = 0; i < recipes.length(); i++) {
                        JSONObject c = recipes.getJSONObject(i);

//                        // Storing each json item in variable
//                        String id = c.getString(TAG_COD);
//                        String name = c.getString(TAG_NAME);
//
//                        // creating new HashMap
//                        HashMap<String, String> map = new HashMap<String, String>();
//
//                        // adding each child node to HashMap key => value
//                        map.put(TAG_COD, id);
//                        map.put(TAG_NAME, name);
//
//                        // adding HashList to ArrayList
//                        hashListRecipe.add(map);

                        Usuario usuario = new Usuario(c.getString("cod_user"), c.getString("name"),
                                c.getString("email"), c.getString("password"),
                                c.getString("description"));

                        Recipes recipe = new Recipes(c.getInt("cod"),
                                c.getString("title"), c.getString("duration"),
                                c.getString("servings"), c.getInt("keenOnCount"),
                                (float) c.getDouble("rateAverage"),
                                c.getInt("rateStars"), R.drawable.fresas, usuario,
                                c.getString("ingredient"), c.getString("steps"));

                        System.out.println("RECETAS WHERE ARE YOU?" + c.getString("title")  + " y " + c.getString("ingredient"));
                        recetas.add(recipe);
                    }
                }

                System.out.println("+++++++++++++++++++++++++++");
                System.out.println("+++++++++++++++++++++++++++");
                System.out.println("+++++++++++++++++++++++++++");
                for (int i=0; i<recetas.size(); i++) {
                    System.out.println(recetas.get(i));
                }
                System.out.println("+++++++++++++++++++++++++++");
                System.out.println("+++++++++++++++++++++++++++");
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
            getActivity().runOnUiThread(new Runnable() {
                public void run() {

                    System.out.println("POST EXECUTE");
                    //Actualizar ListView
                    adapter = new CardviewInicioAdapter(getActivity(), recetas);
                    rvwRecipeIni.setAdapter(adapter);
                }
            });


        }

    }

    /**
     * Background Async Task to Add a keenOn count to a recipe by making HTTP Request
     * */
    class AddKeenOnCount extends AsyncTask<String, String, String> {

        Activity myContext;
        RecyclerView recyclerView;

        public AddKeenOnCount(Activity context, RecyclerView rview) {
            this.myContext = context;
            this.recyclerView = rview;
        }
        /**
         * Before starting background thread Show Progress Dialog
         * */
        @Override
        protected void onPreExecute() {

            super.onPreExecute();
            System.out.println("En PREexecute, SALE ALGO");
            pDialog = new ProgressDialog(myContext);
            pDialog.setMessage("Se está agregando su voto. Por favor espere...");
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
            params.add(new BasicNameValuePair("cod", codUsuario));

            // getting JSON string from URL
            JSONObject json = jParser.makeHttpRequest(GlobalLinks.url_add_keenOnCount, "POST", params);

            // Check your log cat for JSON reponse
            Log.d("Add keenOnCount: ", json.toString());

            try {
                // Checking for SUCCESS TAG
                int success = json.getInt(TAG_SUCCESS--);

                if (success == 1) {
                    // recipe's keenOnCount updated
                    // Getting Array of Users
                    recipes = json.getJSONArray(TAG_RECIPES);

                    // looping through All Products
                    for (int i = 0; i < recipes.length(); i++) {
                        JSONObject c = recipes.getJSONObject(i);

                        Usuario usuario = new Usuario(c.getString("cod_user"), c.getString("name"),
                                c.getString("email"), c.getString("password"),
                                c.getString("description"));

                        Recipes recipe = new Recipes(c.getInt("cod"),
                                c.getString("title"), c.getString("duration"),
                                c.getString("servings"), c.getInt("keenOnCount"),
                                (float) c.getDouble("rateAverage"),
                                c.getInt("rateStars"), R.drawable.fresas, usuario,
                                c.getString("ingredient"), c.getString("steps"));

                        System.out.println("RECETAS WHERE ARE YOU?" + c.getString("title")  + " y " + c.getString("ingredient"));
                        recetas.add(recipe);
                    }
                }

                System.out.println("+++++++++++++++++++++++++++");
                System.out.println("+++++++++++++++++++++++++++");
                System.out.println("+++++++++++++++++++++++++++");
                for (int i=0; i<recetas.size(); i++) {
                    System.out.println(recetas.get(i));
                }
                System.out.println("+++++++++++++++++++++++++++");
                System.out.println("+++++++++++++++++++++++++++");
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
            getActivity().runOnUiThread(new Runnable() {
                public void run() {

                    System.out.println("POST EXECUTE");
                    //Actualizar ListView
                    adapter = new CardviewInicioAdapter(getActivity(), recetas);
                    rvwRecipeIni.setAdapter(adapter);
                }
            });


        }

    }
}
