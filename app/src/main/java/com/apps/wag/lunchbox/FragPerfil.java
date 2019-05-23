package com.apps.wag.lunchbox;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.adroitandroid.chipcloud.ChipCloud;
import com.adroitandroid.chipcloud.ChipListener;
import com.adroitandroid.chipcloud.FlowLayout;


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
    private Button btnSavedRecipes;
    private TextView txtUsername;
    private TextView txtUsermail;
    private TextView txtUserinfo;


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


        pref = getActivity().getSharedPreferences("user_details", getActivity().MODE_PRIVATE);
//        Intent receivedIntent = getActivity().getIntent();
//        usuarioSesion = receivedIntent.getStringExtra("codigo");

        Toast.makeText(getContext(), "Usuario en sesión " +
                pref.getString("username", null), Toast.LENGTH_LONG).show();

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

    final String[] prueba = {"Vegano","Vegetariano", "Carnes", "Pastas"};
        ChipCloud chipCloud = (ChipCloud) view.findViewById(R.id.chip_cloud);
        new ChipCloud.Configure()
                .chipCloud(chipCloud)
                .selectedColor(Color.parseColor("#8cff9f"))
                .selectedFontColor(Color.parseColor("#333333"))
                .deselectedColor(Color.parseColor("#ff9f8c"))
                .deselectedFontColor(Color.parseColor("#ffffff"))
                .selectTransitionMS(500)
                .deselectTransitionMS(250)
                .labels(prueba)
                .mode(ChipCloud.Mode.SINGLE)
                .allCaps(false)
                .gravity(ChipCloud.Gravity.LEFT)
                .textSize(getResources().getDimensionPixelSize(R.dimen.default_textsize))
                .verticalSpacing(getResources().getDimensionPixelSize(R.dimen.vertical_spacing))
                .minHorizontalSpacing(getResources().getDimensionPixelSize(R.dimen.min_horizontal_spacing))
                //.typeface(Typeface.createFromAsset(getContext().getAssets(), "RobotoSlab-Regular.ttf"))
                .chipListener(new ChipListener() {
                    @Override
                    public void chipSelected(int index) {
                        //...
                        System.out.println("chip selected: " +  prueba[index]);
                    }
                    @Override
                    public void chipDeselected(int index) {
                        //...
                    }
                })
                .build();
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

        btnSavedRecipes = (Button) view.findViewById(R.id.btn_savedrecipes);
        btnSavedRecipes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println(getActivity());
                Intent inten = new Intent(getActivity(), SavedRecipes.class);
                startActivity(inten);
                System.out.println(getActivity());
            }
        });
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


}
