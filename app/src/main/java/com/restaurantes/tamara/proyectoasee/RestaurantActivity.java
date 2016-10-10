package com.restaurantes.tamara.proyectoasee;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.gson.Gson;
import com.restaurantes.tamara.proyectoasee.Models.Restaurantes.Binding;

import java.util.ArrayList;
import java.util.List;


public class RestaurantActivity extends AppCompatActivity {
    static public final String MY_LIST_FAV = "MyListFav";

    private TextView NameTextView;
    private TextView ForksTextView;
    private TextView CapacityTextView;
    private TextView CategoryTextView;
    private TextView EmailTextView;
    private TextView WebTextView;
    private TextView PhoneTextView;
    private RatingBar RatesRatingBar;

    public static List<Review> reviews;
    public static RecyclerView rvReview;
    private RVAdapterReview adapterReview;

    private Double longitud;
    private Double latitud;

    private String id;
    private Binding restaurant;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_view);
        Firebase.setAndroidContext(this);

        // Cargamos los datos
        reviews = new ArrayList<>();

        // Inicializo recyclerViewReview
        rvReview = (RecyclerView) findViewById(R.id.rvReview);
        adapterReview = new RVAdapterReview(reviews);
        rvReview.setAdapter(adapterReview);
        LinearLayoutManager llmR = new LinearLayoutManager(this);
        rvReview.setLayoutManager(llmR);


        NameTextView =      (TextView) findViewById(R.id.textViewName);
        ForksTextView =     (TextView) findViewById(R.id.textViewDataForks);
        CapacityTextView =  (TextView) findViewById(R.id.textViewDataCapacity);
        CategoryTextView =  (TextView) findViewById(R.id.textViewDataCategory);
        EmailTextView =     (TextView) findViewById(R.id.textViewDataEmail);
        WebTextView =       (TextView) findViewById(R.id.textViewDataWeb);
        PhoneTextView =     (TextView) findViewById(R.id.restaurant_phone);
        RatesRatingBar =    (RatingBar) findViewById(R.id.ratingBar);


        // Obteniendo los datos del recyclerview según identificador
        Bundle bundle = getIntent().getExtras();
        id = "-1";
        if (bundle != null) {
            id = bundle.getString("IDRestaurant");
            Log.e("test", "numero " + id);
        }

        // El identificador es la URI, por lo que se recorre la lista entera para dar con el restaurante deseado.
        for(Binding r : MainActivity.listaRestaurantes){
            if(r.getURI().getValue().equals(id.toString())) {
                restaurant = r;
            }
        }


        // Como hay campos nulos en opendata, hacemos la comprobación.
        if(restaurant.getRdfsLabel() != null)                   NameTextView.setText(restaurant.getRdfsLabel().getValue());
        if(restaurant.getOmTenedores() != null)                 ForksTextView.setText(restaurant.getOmTenedores().getValue());
        if(restaurant.getOmCapacidadPersonas() != null)         CapacityTextView.setText(restaurant.getOmCapacidadPersonas().getValue());
        if(restaurant.getOmCategoriaRestaurante() != null)      CategoryTextView.setText(restaurant.getOmCategoriaRestaurante().getValue());
        if(restaurant.getSchemaEmail() != null)                 EmailTextView.setText(restaurant.getSchemaEmail().getValue());
        if(restaurant.getSchemaUrl() != null)                   WebTextView.setText(restaurant.getSchemaUrl().getValue());
        if(restaurant.getSchemaTelephone() != null)             PhoneTextView.setText(restaurant.getSchemaTelephone().getValue());

        // Necesarios para la localización del mapa.
        if(restaurant.getGeoLat()  != null) latitud  = Double.valueOf(restaurant.getGeoLat().getValue());
        if(restaurant.getGeoLong() != null) longitud = Double.valueOf(restaurant.getGeoLong().getValue());


        // Botón para comentar, le pasamos a la ReviewsActivity el Identificador.
        Button Comentar = (Button) findViewById(R.id.buttonComentar);
        Comentar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ReviewsActivity.class);
                intent.putExtra("ID", id.toString());
                startActivity(intent);
            }
        });

        // Botón para realizar una llamada.
        Button Llamar = (Button) findViewById(R.id.buttonLlamar);
        Llamar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String number = "tel:" + PhoneTextView.getText().toString().trim();
                Intent callIntent = new Intent(Intent.ACTION_CALL, Uri.parse(number));
                startActivity(callIntent);
            }
        });

        // Botón para navegar a la ruta del mapa.
        Button ComoLlegar = (Button) findViewById(R.id.buttonComoLlegar);
        ComoLlegar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((latitud == null) || (longitud == null))    // Si las coordenadas no existen, mandamos una notificación
                    Toast.makeText(getApplicationContext(), "No existen las coordenadas",
                            Toast.LENGTH_LONG).show();
                else {
                    Intent intentMpas = new Intent(v.getContext(), MapsActivity.class); // Le pasamos a la activity la latitud y longitud.
                    intentMpas.putExtra("latitud", latitud.toString());
                    intentMpas.putExtra("longitud", longitud.toString());
                    startActivity(intentMpas);
                }
            }
        });

    }

    // Cuando volvemos de la activity ReviweActivity se vuelven a cargar los comentarios del servidor.
    @Override
    protected void onResume() {
        super.onResume();
        leerComentariosDelServidor();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.item, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        Bundle bundle = getIntent().getExtras();
        String aux = "-1";
        if (bundle != null)
            aux = bundle.getString("IDRestaurant");

        // Opciones del menú
        if (id == R.id.home) {                                      // Volver al inicio.
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            return true;

        }// Ordenar los restaurantes por valoración.
        if (id == R.id.ordenar_valoracion) {
            adapterReview.orderByRate();
            return true;
        }else if (id == R.id.cerca_de_mi) {                         // Navegar al mapa.
            Intent intentMpas = new Intent(this, MapsActivity.class);
            intentMpas.putExtra("cercaDeMi", "cercaDeMi");
            startActivity(intentMpas);
            return true;

        }else if (id == R.id.back){                                 // Volvemos a la ventana anterior.
            this.finish();                                          // Finalizamos la activity.

        }else if(id == R.id.eliminar_fav){                          // Eliminar el restaurante de la lista fav.
            MainActivity.listaFavoritos.remove(aux);
            preferencias(aux);
            Toast.makeText(this, "Eliminada de Favoritos!", Toast.LENGTH_LONG).show();

        }
        else if (id == R.id.add_fav) {                              // Añadir el restaurante a la lista de fav.
            if( !existe(aux)) {
                MainActivity.listaFavoritos.add(aux);
                preferencias(aux);
                Toast.makeText(this, "Añadido a Favoritos!", Toast.LENGTH_LONG).show();
            }else
                Toast.makeText(this, "Ya existe.", Toast.LENGTH_LONG).show();   // Si ya existe se lo notificamos.
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    // Shared Preferences: para almacenar la lista de restaurantes favoritos.
    //
    public void preferencias(String aux){
            SharedPreferences sharedPref = getSharedPreferences(MY_LIST_FAV, MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putString("ListaFavoritos", new Gson().toJson(MainActivity.listaFavoritos));
            editor.commit();
    }

    // Comprobamos si el restaurante ya se encuentra en la lista de fav.
    public boolean existe(String aux) {
        for(String i : MainActivity.listaFavoritos){
            if(i.equals(aux)) return true;
        }
        return false;
    }

    // Leemos los datos del servidor, filtrando por id para optimizar la búsqueda.
    protected void leerComentariosDelServidor() {

        Firebase myFirebaseRef = new Firebase("https://restaurantescaceres.firebaseio.com/");
        myFirebaseRef.child("reviews").orderByChild("id").equalTo(id.toString()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                adapterReview.getReviews().clear();         // Limpiamos el adapter.
                float media = 0;                            // Variable para almacenar la valoración media del restaurante.
                int contador = 0;
                for (DataSnapshot reviewSnapshot : dataSnapshot.getChildren()) {
                    Review obj = reviewSnapshot.getValue(Review.class);         // Obtenemos el objeto
                    media += obj.getRates();
                    contador++;
                    adapterReview.getReviews().add(obj);                        // Lo añadimos al adapter.
                }
                media /= contador;
                RatesRatingBar.setRating(media);                                // Mostramos la media por medio de estrellas.
                adapterReview.notifyDataSetChanged();                           // Se notifican los cambios.
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                System.out.println("The read failed: " + firebaseError.getMessage());
            }
        });
    }

}
