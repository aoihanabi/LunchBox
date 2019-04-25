package com.apps.wag.lunchbox;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;

import java.util.ArrayList;

public class RecipeListAdapter extends ArrayAdapter<Recipes> {

    private static final String TAG = "RecipeListAdapter";
    private Context myContext;
    private int myResource;

    //ViewHolder Pattern class
    static class ViewHolder {
        TextView recipeTitle;
        ImageView recipePhoto;
    }

    public RecipeListAdapter (Context context, int resource, ArrayList<Recipes> objects) {
        super(context, resource, objects);
        myContext = context;
        myResource = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        //Setup Image loader
        setImageLoader();

        int cod = getItem(position).getCod();
        String title = getItem(position).getTitle();
        String duration = getItem(position).getDuration();
        String servings = getItem(position).getServings();
        int keenOnCount = getItem(position).getKeenOnCount();
        int madeCount = getItem(position).getMadeCount();
        float rateAverage = getItem(position).getRateAverage();
        int rateStars = getItem(position).getRateStars();
        //String imgURL = getItem(position).getImage();
        int imgURL = getItem(position).getImage();

        Recipes laReceta = new Recipes(cod, title, duration, servings, keenOnCount, madeCount,
                rateAverage, rateStars, imgURL);


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
            viewHolder.recipePhoto = (ImageView) convertView.findViewById(R.id.imgVw_recipePhoto);

            convertView.setTag(viewHolder);
        }
        //ViewHolder is in memory, no need to reload it
        else {
            //... just get it from memory (Tag)
            viewHolder = (ViewHolder) convertView.getTag();
        }

        //Obtener imagen default para cuando no ha cargado o no puede cargar la imagen
        int defaultImage = myContext.getResources().getIdentifier("@drawable/image_failed",
                null, myContext.getPackageName());
        // Download and display bitmap on ImageView
        ImageLoader imageLoader = ImageLoader.getInstance();
        DisplayImageOptions options = new DisplayImageOptions.Builder().cacheInMemory(true)
                .cacheOnDisc(true).resetViewBeforeLoading(true)
                .showImageForEmptyUri(defaultImage)
                .showImageOnFail(defaultImage)
                .showImageOnLoading(defaultImage).build();

        //download and display image from url
        imageLoader.displayImage( String.valueOf(imgURL), viewHolder.recipePhoto, options);

        //Set ViewHolder's attributes
        viewHolder.recipeTitle.setText(title);

        return convertView;
    }


    private void setImageLoader() {
        // UNIVERSAL IMAGE LOADER SETUP
        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
                .cacheOnDisc(true).cacheInMemory(true)
                .imageScaleType(ImageScaleType.EXACTLY)
                .displayer(new FadeInBitmapDisplayer(300)).build();

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
                myContext)
                .defaultDisplayImageOptions(defaultOptions)
                .memoryCache(new WeakMemoryCache())
                .discCacheSize(100 * 1024 * 1024).build();

        ImageLoader.getInstance().init(config);
        // END - UNIVERSAL IMAGE LOADER SETUP
    }
}
