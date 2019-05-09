package com.apps.wag.lunchbox;

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
        TextView tvNombre;
        TextView tvArtista;

        public crdvwInicioViewHolder(View itemView) {
            super(itemView);
            imgMusica = (ImageView) itemView.findViewById(R.id.img_receta);
            tvNombre = (TextView) itemView.findViewById(R.id.tv_title);
            tvArtista = (TextView) itemView.findViewById(R.id.tv_artista);
        }
    }

    private ArrayList<Recipes> recetas;

    public CardviewInicioAdapter(ArrayList<Recipes> recet) {
        this.recetas = recet;
    }

    @Override
    public crdvwInicioViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new crdvwInicioViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_cardview_inicio, parent, false));
    }

    @Override
    public void onBindViewHolder(crdvwInicioViewHolder holder, int position) {
        Recipes recipe = recetas.get(position);
        holder.imgMusica.setImageResource(recipe.getImage());
        holder.tvNombre.setText(recipe.getTitle());
        holder.tvArtista.setText(String.valueOf(recipe.getRateStars()));
    }

    @Override
    public int getItemCount() {
        return recetas.size();
    }


}