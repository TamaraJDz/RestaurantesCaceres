package com.restaurantes.tamara.proyectoasee.Controller;

import com.restaurantes.tamara.proyectoasee.Models.Restaurantes.Restaurante;

import retrofit.Call;
import retrofit.http.GET;

/**
 * Created by Tamara on 14/11/2015.
 */


public interface openDataService {
    @GET("/sparql/?default-graph-uri=http%3A%2F%2Fopendata.caceres.es%2Frecurso%2Fturismo%2Festablecimientos%2FRestaurante%2F&query=select+%3FURI+%3Frdfs_label+%3Fschema_addressCountry+%3Fschema_streetAddress+%3Fschema_postalCode+%3Fschema_addressLocality+%3Fschema_telephone+%3Fschema_email+%3Fschema_url+%3Fom_categoriaRestaurante+%3Fom_tenedores+%3Fom_capacidadPersonas+%3Fgeo_lat+%3Fgeo_long%0D%0Awhere%7B%0D%0A%3FURI+a+om%3ARestaurante.%0D%0AOPTIONAL+%7B+%3FURI+rdfs%3Alabel+%3Frdfs_label.%7D%0D%0A%3FURI+schema%3Aaddress+%3Fschema_address.%0D%0AOPTIONAL+%7B+%3Fschema_address+schema%3AaddressCountry+%3Fschema_addressCountry.%7D%0D%0AOPTIONAL+%7B+%3Fschema_address+schema%3AstreetAddress+%3Fschema_streetAddress.%7D%0D%0AOPTIONAL+%7B+%3Fschema_address+schema%3ApostalCode+%3Fschema_postalCode.%7D%0D%0AOPTIONAL+%7B+%3Fschema_address+schema%3AaddressLocality+%3Fschema_addressLocality.%7D%0D%0AOPTIONAL+%7B+%3FURI+schema%3Atelephone+%3Fschema_telephone.%7D%0D%0AOPTIONAL+%7B+%3FURI+schema%3Aemail+%3Fschema_email.%7D%0D%0AOPTIONAL+%7B+%3FURI+schema%3Aurl+%3Fschema_url.%7D%0D%0AOPTIONAL+%7B+%3FURI+om%3AcategoriaRestaurante+%3Fom_categoriaRestaurante.%7D%0D%0AOPTIONAL+%7B+%3FURI+om%3Atenedores+%3Fom_tenedores.%7D%0D%0AOPTIONAL+%7B+%3FURI+om%3AcapacidadPersonas+%3Fom_capacidadPersonas.%7D%0D%0AOPTIONAL+%7B+%3FURI+geo%3Alat+%3Fgeo_lat.%7D%0D%0AOPTIONAL+%7B+%3FURI+geo%3Along+%3Fgeo_long.%7D%0D%0A%7D&format=application%2Fsparql-results%2Bjson&timeout=0&debug=on")
    Call<Restaurante> getRestaurantes();
}
