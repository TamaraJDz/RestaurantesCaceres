package com.restaurantes.tamara.proyectoasee;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.gson.Gson;
import com.restaurantes.tamara.proyectoasee.Controller.openDataService;
import com.restaurantes.tamara.proyectoasee.Models.Restaurantes.Binding;
import com.restaurantes.tamara.proyectoasee.Models.Restaurantes.Restaurante;

import java.util.ArrayList;
import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    Context mContext;
    public static List<Binding> listaRestaurantes;
    public static List<String> listaFavoritos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        mContext = this;
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        // listas de Binding
        listaRestaurantes = new ArrayList<>();
        listaFavoritos = new ArrayList<>();

        // Obteniendo los datos de OpenData...
        Retrofit restAdapter = new Retrofit.Builder()
                .baseUrl("http://opendata.caceres.es")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        openDataService service = restAdapter.create(openDataService.class);

        Call<Restaurante> restauranteCall = service.getRestaurantes();
        restauranteCall.enqueue(new Callback<Restaurante>() {
            @Override
            public void onResponse(Response<Restaurante> response) {
                // Get result Repo from response.body()
                Restaurante restaurante = response.body();
                // Se almacenan los datos en la lista de restaurantes.
                listaRestaurantes.addAll(restaurante.getResults().getBindings());
                startRecyclerView();
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    // Llamamos al Recycler View Adapter y le pasamos la lista de restaurantes
    public void startRecyclerView (){
        RecyclerView rv = (RecyclerView)findViewById(R.id.rv);
        RVAdapter adapter = new RVAdapter(listaRestaurantes);
        rv.setAdapter(adapter);
        LinearLayoutManager llm = new LinearLayoutManager(mContext);
        rv.setLayoutManager(llm);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    // Creación del menú
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    // Opciones del menú.
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();


        // Navegamos al mapa y muestra los restaurantes más cercanos a la ubicación del usuario.
        if (id == R.id.cerca_de_mi) {
            Intent intentMpas = new Intent(this, MapsActivity.class);
            intentMpas.putExtra("cercaDeMi", "cercaDeMi");
            startActivity(intentMpas);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    // Opciones del NavigationItemSelected
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_inicio) {        // Volvemos al inicio, dónde tenemos la vista de todos los restaurantes.
            RVAdapter adapter = NavegationDrawerSelector();
            adapter.setRestaurants();       // Volvemos a la lista de restaurantes.

        } else if (id == R.id.nav_mapa) {   // Navegamos a la activity del mapa y se muestran todos los restaurantes señalados.
            Intent intentMap = new Intent(this, MapsActivity.class);
            startActivity(intentMap);

        } else if (id == R.id.nav_Favoritos) {  // Se muestran sólo los restaurantes que cada usuario tiene como favoritos.
            SharedPreferences sharedPref = getSharedPreferences(RestaurantActivity.MY_LIST_FAV, MODE_PRIVATE );
            MainActivity.listaFavoritos = new Gson().fromJson(sharedPref.getString("ListaFavoritos", ""), List.class);

            if( !MainActivity.listaFavoritos.isEmpty() ){   // Si la lista no está vacía se muestran.
                RVAdapter adapter = NavegationDrawerSelector();
                adapter.setFavorite();                      // Cambiamos la lista por la de favoritos.
            }else                                           // Y si está vacía le mandamos una notificación.
                Toast.makeText(this, "Lista de Favoritos vacía.", Toast.LENGTH_LONG).show();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public RVAdapter NavegationDrawerSelector(){
        RecyclerView rv = (RecyclerView)findViewById(R.id.rv);
        RVAdapter adapter = (RVAdapter)rv.getAdapter();
        return adapter;
    }
}
