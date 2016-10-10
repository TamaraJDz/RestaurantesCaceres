package com.restaurantes.tamara.proyectoasee;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import com.firebase.client.Firebase;

public class ReviewsActivity extends AppCompatActivity {
    private TextView UserEditText;
    private TextView ReviewEditText;
    private String id;
    private RatingBar RatingBarReviews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Firebase.setAndroidContext(this);
        setContentView(R.layout.activity_reviews);


        UserEditText = (TextView) findViewById(R.id.editTextNameUser);
        ReviewEditText = (TextView) findViewById(R.id.editTextReview);
        RatingBarReviews = (RatingBar)findViewById(R.id.ratingBarReviews);

        // Obteniendo el ID del restaurante
        Bundle bundleR = getIntent().getExtras();
        id = "-1";
        if (bundleR != null) {
            id = bundleR.getString("ID");
        }

        // Botón enviar para enviar el comentario.
        Button Enviar = (Button) findViewById(R.id.buttonSend);
        Enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Review obj_review = new Review();               // Nuevo objeto Review en el que insertamos los datos.
                obj_review.setId(id.toString());
                obj_review.setName(UserEditText.getText().toString());
                obj_review.setReview(ReviewEditText.getText().toString());
                obj_review.setRates(RatingBarReviews.getRating());
                RestaurantActivity.reviews.add(obj_review);     // Añadimos el objeto a la lista de Review.

                Firebase myFirebaseRef = new Firebase("https://restaurantescaceres.firebaseio.com/");
                myFirebaseRef.child("reviews").push().setValue(obj_review);         // Almacenamos el nuevo comentario en el servidor.

                finish();       // Finalizamos la activity.
            }
        });

        // Botón cancelar, no hace nada, simplemente finaliza la activity.
        Button Cancelar = (Button) findViewById(R.id.buttonCancel);
        Cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
