package com.apps.wag.lunchbox;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class RecipeListAdapter extends ArrayAdapter<Recipes> {

    private static final String TAG = "RecipeListaAdapter";
    private Context myContext;
    private int myResource;

    //ViewHolder Pattern class
    static class ViewHolder {
        TextView recipeTitle;
    }

    public RecipeListAdapter (Context context, int resource, ArrayList<Recipes> objects) {
        super(context, resource, objects);
        myContext = context;
        myResource = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        int cod = getItem(position).getCod();
        String title = getItem(position).getTitle();
        String duration = getItem(position).getDuration();
        String servings = getItem(position).getServings();
        int keenOnCount = getItem(position).getKeenOnCount();
        int madeCount = getItem(position).getMadeCount();
        float rateAverage = getItem(position).getRateAverage();
        int rateStars = getItem(position).getRateStars();

        Recipes laReceta = new Recipes(cod, title, duration, servings, keenOnCount, madeCount, rateAverage, rateStars);

        //ViewHolder instance
        ViewHolder viewHolder;

        //ViewHolder Pattern
        //ViewHolder isn't in memory
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(myContext);
            convertView = inflater.inflate(myResource, parent, false);

            //... so we'll load it
            viewHolder = new ViewHolder();
            viewHolder.recipeTitle = (TextView) convertView.findViewById(R.id.txtv_recipeName);
            convertView.setTag(viewHolder);
        }
        //ViewHolder is in memory, no need to reload it
        else {
            //... just get it from memory (Tag)
            viewHolder = (ViewHolder) convertView.getTag();
        }

        //Set ViewHolder's attributes
        viewHolder.recipeTitle.setText(title);

        return convertView;
    }
}
