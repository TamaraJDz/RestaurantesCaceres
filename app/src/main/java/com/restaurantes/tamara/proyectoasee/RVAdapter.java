package com.restaurantes.tamara.proyectoasee;

import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import com.restaurantes.tamara.proyectoasee.Models.Restaurantes.Binding;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Tamara on 25/10/2015.
 */
public class RVAdapter extends RecyclerView.Adapter<RVAdapter.RestaurantViewHolder> {
    List<Binding> restaurants;

    public static class RestaurantViewHolder extends RecyclerView.ViewHolder{
        CardView cv;
        TextView restaurantName;
        TextView restaurantAddress;
        TextView restaurantID;

        public RestaurantViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.cv);
            restaurantName = (TextView)itemView.findViewById(R.id.restaurant_name);
            restaurantAddress = (TextView)itemView.findViewById(R.id.restaurant_address);
            restaurantID = (TextView)itemView.findViewById(R.id.restaurant_id);

        }
        public void bindRestaurant(Binding binding) {
            try {
                restaurantName.setText(binding.getRdfsLabel().getValue());
                restaurantAddress.setText(binding.getSchemaStreetAddress().getValue());
                restaurantID.setText(binding.getURI().getValue());
            } catch(NullPointerException e) { }
        }

   }

    RVAdapter(List<Binding> restaurants){
        this.restaurants = restaurants;
    }

    @Override
    public int getItemCount() {
        return restaurants.size();
    }

    @Override
    public RestaurantViewHolder onCreateViewHolder(ViewGroup viewGroup,int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_item, viewGroup, false);
        final RestaurantViewHolder rvh = new RestaurantViewHolder(v);

        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){

                Intent intent = new Intent(v.getContext(), RestaurantActivity.class);
                intent.putExtra("IDRestaurant", rvh.restaurantID.getText().toString());
                v.getContext().startActivity(intent);

            }
        });
        return rvh;
    }

    @Override
    public void onBindViewHolder(RestaurantViewHolder restaurantViewHolder, int i) {
        if(i < restaurants.size())
            restaurantViewHolder.bindRestaurant(restaurants.get(i));
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public void setFavorite(){
        List<Binding> listFav = new ArrayList<>();
        for(String id : MainActivity.listaFavoritos){
            for( Binding uri : MainActivity.listaRestaurantes){
                if(id.equals(uri.getURI().getValue()))
                    listFav.add(uri);
            }
        }
        restaurants = listFav;
        this.notifyDataSetChanged();
    }

    public void setRestaurants(){
        restaurants = MainActivity.listaRestaurantes;
        this.notifyDataSetChanged();
    }

}
