package com.apps.wag.lunchbox;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MyRecipeBook extends AppCompatActivity {
    ListView listita;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_recipe_book);

        listita = (ListView) findViewById(R.id.listViewId);
        ArrayList <String> listaNombres = new ArrayList<>();
        listaNombres.add("Receta 0");
        listaNombres.add("Receta 1");
        listaNombres.add("Receta 2");
        listaNombres.add("Receta 3");
        listaNombres.add("Receta 4");
        listaNombres.add("Receta 5");
        listaNombres.add("Receta 6");
        listaNombres.add("Receta 7");

        ArrayAdapter adaptador = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listaNombres);
        listita.setAdapter(adaptador);
    }
}
