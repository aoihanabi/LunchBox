package com.apps.wag.lunchbox;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


///**
// * A simple {@link Fragment} subclass.
// * Activities that contain this fragment must implement the
// * {@link FragPerfil.OnFragmentInteractionListener} interface
// * to handle interaction events.
// * Use the {@link FragPerfil#newInstance} factory method to
// * create an instance of this fragment.
// */
public class FragPerfil extends Fragment {
//    // TODO: Rename parameter arguments, choose names that match
//    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//    private static final String ARG_PARAM1 = "param1";
//    private static final String ARG_PARAM2 = "param2";
//
    //ESPECIFICOS DE ACTIVITY
    SharedPreferences pref;
    private Button btnRcpbook;
    private TextView txtUsername;
    private TextView txtUsermail;
    private TextView txtUserinfo;

//    private String usuarioSesion;

    //ESPECIFICOS PARA EL PARSEO
    private static final String APP_NAME_PATH = "com.wag.lunchbox/";
    private static final String APP_NAME = "Lunch Box";
    // JSON Node names
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_USERS = "users";
    private static final String TAG_COD = "cod";
    private static final String TAG_NAME = "name";
    // users JSONArray
    JSONArray users = null;
    // Progress Dialog
    private ProgressDialog pDialog;
    // Creating JSON Parser object
    JSONParser jParser = new JSONParser();
    ArrayList<HashMap<String, String>> hashListUser;
    // url to get all products list
    private static String url_all_users = "https://darkreaperto.000webhostapp.com/lb_files/get_all_users.php";

//    // TODO: Rename and change types of parameters
//    private String mParam1;
//    private String mParam2;
//
//    private OnFragmentInteractionListener mListener;
//
//    public FragPerfil() {
//        // Required empty public constructor
//    }
//
//    /**
//     * Use this factory method to create a new instance of
//     * this fragment using the provided parameters.
//     *
//     * @param param1 Parameter 1.
//     * @param param2 Parameter 2.
//     * @return A new instance of fragment FragPerfil.
//     */
//    // TODO: Rename and change types and number of parameters
//    public static FragPerfil newInstance(String param1, String param2) {
//        FragPerfil fragment = new FragPerfil();
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

        // Hashmap for ListView
        hashListUser = new ArrayList<HashMap<String, String>>();
        // Loading users in Background Thread
        new FragPerfil.LoadAllUsers().execute();

        pref = getActivity().getSharedPreferences("user_details", getActivity().MODE_PRIVATE);
//        Intent receivedIntent = getActivity().getIntent();
//        usuarioSesion = receivedIntent.getStringExtra("codigo");

        Toast.makeText(getContext(), "Perfil NAME " + pref.getString("username", null), Toast.LENGTH_LONG).show();

        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.frag3_perfil, container, false);
        return rootView;
    }

    @Override
    @Nullable
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        //CLICK DE BOTONES
        txtUsername = (TextView) view.findViewById(R.id.txtUsername);
        txtUsermail = (TextView) view.findViewById(R.id.txtUsermail);
        txtUserinfo = (TextView) view.findViewById(R.id.txtUserinfo);

        pref = getActivity().getSharedPreferences("user_details", getActivity().MODE_PRIVATE);
        txtUsername.setText(pref.getString("username", "Nombre usuario"));
        txtUsermail.setText(pref.getString("usermail", "Correo usuario"));
        txtUserinfo.setText(pref.getString("descripcion", "Breve descripción del usuario"));


        btnRcpbook = (Button) view.findViewById(R.id.btn_recipebook);
        btnRcpbook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println(getActivity());
                Intent inten = new Intent(getActivity(), MyRecipeBook.class);
                startActivity(inten);
                System.out.println(getActivity());
            }
        });
    }
    public void cargarUsuarios() {
        // Hashmap for ListView
        hashListUser = new ArrayList<HashMap<String, String>>();
        // Loading users in Background Thread
        new FragPerfil.LoadAllUsers().execute();
    }
//    // TODO: Rename method, update argument and hook method into UI event
//    public void onButtonPressed(Uri uri) {
//        if (mListener != null) {
//            mListener.onFragmentInteraction(uri);
//        }
//    }
//
//    @Override
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
     * Background Async Task to Load all users by making HTTP Request
     * */
    class LoadAllUsers extends AsyncTask<String, String, String> {

        /**
         * Before starting background thread Show Progress Dialog
         * */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(FragPerfil.this.getContext());
            pDialog.setMessage("Cargando usuario. Por favor espere...");
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
                        hashListUser.add(map);

                    }
                } else {
                    // no users found
                    // Launch Add New user Activity
//                    Intent i = new Intent(getApplicationContext(),
//                            NewUserActivity.class);
//                    // Closing all previous activities
//                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                    startActivity(i);
                }

                System.out.println("===========================");
                System.out.println("===========================");
                System.out.println("===========================");
                for (int i=0; i<hashListUser.size(); i++) {
                    System.out.println(hashListUser.get(i));
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


            getActivity().runOnUiThread(new Runnable() {
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
            // dismiss the dialog after getting all products
            pDialog.dismiss();
            // updating UI from Background Thread

        }

    }
}
