package com.apps.wag.lunchbox;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MyRecipeBook extends AppCompatActivity {

    private static final String TAG = "MyRecipeBook";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_recipe_book);

        Log.d(TAG, "onCreate: Started.");
        ListView listaRecetas = (ListView) findViewById(R.id.listMyRecipebook);

        Recipes r1 = new Recipes(0,"Receta 0", "30:00",  "3 platos", 3, 2, (float) 8.5, 4, "drawable://" + R.drawable.fresas);
        Recipes r2 = new Recipes(1,"Receta 1", "30:00",  "3 platos", 3, 2, (float) 8.5, 4, "drawable://" + R.drawable.Carne);
        Recipes r3 = new Recipes(2,"Receta 2", "30:00",  "3 platos", 3, 2, (float) 8.5, 4, "drawable://" + R.drawable.sopa);
        Recipes r4 = new Recipes(3,"Receta 3", "30:00",  "3 platos", 3, 2, (float) 8.5, 4, "drawable://" + R.drawable.fideos);
        Recipes r5 = new Recipes(4,"Receta 4", "30:00",  "3 platos", 3, 2, (float) 8.5, 4, "drawable://" + R.drawable.fresas);
        Recipes r6 = new Recipes(5,"Receta 5", "30:00",  "3 platos", 3, 2, (float) 8.5, 4, "drawable://" + R.drawable.Carne);
        Recipes r7 = new Recipes(6,"Receta 6", "30:00",  "3 platos", 3, 2, (float) 8.5, 4, "drawable://" + R.drawable.sopa);
        Recipes r8 = new Recipes(7,"Receta 7", "30:00",  "3 platos", 3, 2, (float) 8.5, 4, "drawable://" + R.drawable.fideos);
        Recipes r9 = new Recipes(8,"Receta 8", "30:00",  "3 platos", 3, 2, (float) 8.5, 4, "drawable://" + R.drawable.fresas);

        ArrayList <Recipes> arrayRecetas = new ArrayList<>();
        arrayRecetas.add(r1);
        arrayRecetas.add(r2);
        arrayRecetas.add(r3);
        arrayRecetas.add(r4);
        arrayRecetas.add(r5);
        arrayRecetas.add(r6);
        arrayRecetas.add(r7);
        arrayRecetas.add(r8);
        arrayRecetas.add(r9);

        RecipeListAdapter adaptador = new RecipeListAdapter(this, R.layout.layout_adapter_listview, arrayRecetas);
        listaRecetas.setAdapter(adaptador);
    }
}
