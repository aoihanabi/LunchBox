package com.apps.wag.lunchbox;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;


///**
// * A simple {@link Fragment} subclass.
// * Activities that contain this fragment must implement the
// * {@link FragBuscar.OnFragmentInteractionListener} interface
// * to handle interaction events.
// * Use the {@link FragBuscar#newInstance} factory method to
// * create an instance of this fragment.
// */
public class FragBuscar extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//    private static final String ARG_PARAM1 = "param1";
//    private static final String ARG_PARAM2 = "param2";
    private Button btnRcpbook;
    private Spinner cats1;
    private Spinner cats2;
    private Spinner cats3;
//
//    // TODO: Rename and change types of parameters
//    private String mParam1;
//    private String mParam2;
//
//    private OnFragmentInteractionListener mListener;
//
//    public FragBuscar() {
//        // Required empty public constructor
//    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
//     * @param param1 Parameter 1.
//     * @param param2 Parameter 2.
     * @return A new instance of fragment FragBuscar.
     */
    // TODO: Rename and change types and number of parameters
//    public static FragBuscar newInstance(String param1, String param2) {
//        FragBuscar fragment = new FragBuscar();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
//        return fragment;
//    }

//   @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//
//       //CLICK DE BOTONES
//       btnRcpbook = (Button) getView().findViewById(R.id.btn_recipebook);
//       btnRcpbook.setOnClickListener(new View.OnClickListener() {
//           @Override
//           public void onClick(View v) {
//               openRecipeBook();
//           }
//       });
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.frag1_buscar, container, false);
        return rootView;

    }

    @Override
    @Nullable
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        addItemsOnSpinner2();
    }

    // TODO: Rename method, update argument and hook method into UI event
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

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
//    public interface OnFragmentInteractionListener {
//        // TODO: Update argument type and name
//        void onFragmentInteraction(Uri uri);
//    }

    private void openRecipeBook() {
        Intent inten = new Intent(getActivity(), MyRecipeBook.class);
        startActivity(inten);
    }

    // add items into spinner dynamically
    public void addItemsOnSpinner2() {

        cats1 = (Spinner) getView().findViewById(R.id.spnCategoria1);
        cats2 = (Spinner) getView().findViewById(R.id.spnCategoria2);
        cats3 = (Spinner) getView().findViewById(R.id.spnCategoria3);

        String subCats[] = {"a", "b", "c"};
        ArrayList <String> listCats1 = new ArrayList<String>();
        ArrayList <String> listCats2 = new ArrayList<String>();
        ArrayList <String> listCats3 = new ArrayList<String>();

        for (int i=0; i<subCats.length; i++) {
            listCats1.add("Categoría "+(i+1)+subCats[i]);
            listCats2.add("Categoría "+(i+1)+subCats[i]);
            listCats3.add("Categoría "+(i+1)+subCats[i]);
        }

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, listCats1);
        //dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        cats1.setAdapter(dataAdapter);

        dataAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, listCats2);
        //dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        cats2.setAdapter(dataAdapter);

        dataAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, listCats3);
        //dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        cats3.setAdapter(dataAdapter);
    }
}
