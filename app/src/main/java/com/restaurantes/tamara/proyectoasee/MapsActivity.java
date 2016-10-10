package com.restaurantes.tamara.proyectoasee;

import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.restaurantes.tamara.proyectoasee.Models.Restaurantes.Binding;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private String banderaCerca;
    private Double latitud;
    private Double longitud;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    // Para obtener la ubicación actual del usuario.
    // Creamos un nuevo marker con la posición actual, añadimos un icono diferente y ponemos como titulo "Mi Ubicación"
    // Lo dividimos en dos partes:
    //      - Una para la opción "Cerca de mi" dónde aumentamos el zoom.
    //      - Otra para la vista normal de todos los restaurantes dónde el zoom es menor.
    private GoogleMap.OnMyLocationChangeListener myLocationChangeListener = new GoogleMap.OnMyLocationChangeListener() {
        @Override
        public void onMyLocationChange(Location location) {
            LatLng loc = new LatLng(location.getLatitude(), location.getLongitude());
            mMap.addMarker(new MarkerOptions().position(loc).icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_location)).title("Mi ubicación"));
            if (mMap != null) {
                if(banderaCerca != null && banderaCerca.equals("cercaDeMi"))
                    mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(loc, 16.5f));
                else
                    mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(loc, 12));
                mMap.getUiSettings().setMyLocationButtonEnabled(true);
                mMap.getUiSettings().setZoomControlsEnabled(true);
            }
            mMap.setMyLocationEnabled(false);
            setComoLlegar();        // Llamada al método cómo llegar que nos traza la ruta.
        }
    };

    // Se muestra el mapa con el punto del restaurante marcado.
    public void setComoLlegar(){
        Intent intentComoLlegar = getIntent();
        // Se recogen las coordenadas.
        try {
            latitud = Double.valueOf(intentComoLlegar.getStringExtra("latitud"));
            longitud = Double.valueOf(intentComoLlegar.getStringExtra("longitud"));
        }catch (NullPointerException e) {}
        if(latitud != null && longitud != null) {
            LatLng localizacion = new LatLng(latitud, longitud);
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(localizacion, 20));
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Pulsa sobre Cerca de mi y se cambia el tamaño del mapa.
        Intent intent = getIntent();
        banderaCerca = intent.getStringExtra("cercaDeMi");

        // Hbilitamos la localización
        mMap.setMyLocationEnabled(true);
        mMap.setOnMyLocationChangeListener(myLocationChangeListener);

        // Inserta todos los marker de la lista de OpenData
        for (Binding i : MainActivity.listaRestaurantes) {
            LatLng x = new LatLng((Double.valueOf(i.getGeoLat().getValue())), Double.valueOf(i.getGeoLong().getValue()));
            mMap.addMarker(new MarkerOptions().position(x).title(i.getRdfsLabel().getValue()).snippet(i.getURI().getValue()));
            // De momento hago el apaño de meter el identificador en snippet ¡ CAMBIAR !
        }


        // Si hacemos click sobre cualquier marker nos muestra su ventana de información.
        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                marker.showInfoWindow();
                return false;
            }
        });

        // Si hacemos click sobre la ventana de información nos lleva a la activity del restaurante.
        mMap.setOnInfoWindowClickListener(
                new GoogleMap.OnInfoWindowClickListener() {
                    public void onInfoWindowClick(Marker marker) {
                        Intent nextScreen = new Intent(MapsActivity.this, RestaurantActivity.class);
                        nextScreen.putExtra("IDRestaurant", marker.getSnippet().toString());
                        startActivity(nextScreen);
                    }
                }
        );
    }
}
