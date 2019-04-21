package com.apps.wag.lunchbox;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import java.util.ArrayList;

public class MyRecipeBook extends AppCompatActivity {

    private static final String TAG = "MyRecipeBook";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_recipe_book);

        Log.d(TAG, "onCreate: Started.");
        ListView listaRecetas = (ListView) findViewById(R.id.listMyRecipebook);

        //Recipes r1 = new Recipes(0,"Receta 0", "30:00",  "3 platos", 3, 2, (float) 8.5, 4);

        ArrayList <Recipes> arrayRecetas = new ArrayList<>();

        //Instantiate recipes dynamically
        for (int i=0; i<1000; i++) {
            Recipes recipe = new Recipes(i,"Receta "+i+1, "30:00",
                    "3 platos", 3, 2, (float) 8.5, 4, "drawable://" + R.drawable.fresas);

            arrayRecetas.add(recipe);
        }

        RecipeListAdapter adaptador = new RecipeListAdapter(this, R.layout.layout_adapter_listview, arrayRecetas);
        listaRecetas.setAdapter(adaptador);
    }
}
