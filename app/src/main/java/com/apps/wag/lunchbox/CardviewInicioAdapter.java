package com.apps.wag.lunchbox;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CardviewInicioAdapter extends RecyclerView.Adapter<CardviewInicioAdapter.crdvwInicioViewHolder> {

    class crdvwInicioViewHolder extends RecyclerView.ViewHolder{

        ImageView imgMusica;
        TextView txtTituloReceta;
        TextView txtDescripReceta;

        public crdvwInicioViewHolder(View itemView) {
            super(itemView);
            imgMusica = (ImageView) itemView.findViewById(R.id.img_receta);
            txtTituloReceta = (TextView) itemView.findViewById(R.id.txt_tituloReceta);
            txtDescripReceta = (TextView) itemView.findViewById(R.id.txt_descripcionReceta);
        }
    }

    private ArrayList<Recipes> recetas;
    private Activity context;

    public CardviewInicioAdapter(Activity context, ArrayList<Recipes> recet) {
        this.recetas = recet;
        this.context = context;
    }


    public crdvwInicioViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new crdvwInicioViewHolder(LayoutInflater.from(context).inflate(
                R.layout.layout_cardview_inicio, parent, false));
    }

    @Override
    public void onBindViewHolder(crdvwInicioViewHolder holder, int position) {
        Recipes recipe = recetas.get(position);
        holder.imgMusica.setImageResource(recipe.getImage());
        holder.txtTituloReceta.setText(recipe.getTitle());
        holder.txtDescripReceta.setText(String.valueOf(recipe.getDuration()));
    }

    @Override
    public int getItemCount() {
        if(recetas == null) {
            return 0;
        } else {
            return recetas.size();
        }
    }


}