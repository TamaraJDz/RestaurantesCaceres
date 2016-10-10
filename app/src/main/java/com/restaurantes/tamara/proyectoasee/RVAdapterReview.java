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

import org.apache.commons.collections4.bag.TreeBag;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentSkipListMap;

/**
 * Created by Tamara on 01/11/2015.
 */
public class RVAdapterReview extends RecyclerView.Adapter<RVAdapterReview.ReviewViewHolder>{

    List<Review> reviews;

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    RVAdapterReview(List<Review> reviews){
        this.reviews = reviews;
    }

    public static class ReviewViewHolder extends RecyclerView.ViewHolder {
        CardView cv2;

        TextView reviewID;
        TextView reviewName;
        TextView reviewText;
        RatingBar reviewRates;

        public ReviewViewHolder(View itemView){
            super(itemView);
            cv2 = (CardView)itemView.findViewById(R.id.cv2);
            reviewID = (TextView)itemView.findViewById(R.id.review_id);
            reviewName = (TextView)itemView.findViewById(R.id.reviewName);
            reviewText = (TextView)itemView.findViewById(R.id.reviewText);
            reviewRates = (RatingBar)itemView.findViewById(R.id.ratingBarReviews);
        }
        public void bindReview(Review review) {
            try {
                reviewID.setText(review.getId());
                reviewName.setText(review.getName());
                reviewText.setText(review.getReview());
                reviewRates.setRating(review.getRates());
            } catch(NullPointerException e) { }
        }
    }


    @Override
    public int getItemCount() {
        return reviews.size();
    }


    @Override
    public ReviewViewHolder onCreateViewHolder(ViewGroup viewGroup,int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_item_review, viewGroup, false);
        ReviewViewHolder rvh = new ReviewViewHolder(v);

        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        return rvh;
    }

    @Override
    public void onBindViewHolder(ReviewViewHolder reviewviewHolder, int i) {
        if(i < reviews.size())
            reviewviewHolder.bindReview(reviews.get(i));
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public void orderByRate(){
        Collections.sort(reviews, new Comparator<Review>() {
            @Override
            public int compare(Review lhs, Review rhs) {
                if(lhs.getRates() < rhs.getRates()) return 1;
                if(lhs.getRates() == rhs.getRates()) return 0;
                return -1;
            }
        });
        this.notifyDataSetChanged();
    }
}