
package com.restaurantes.tamara.proyectoasee.Models.Restaurantes;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Restaurante {


    @SerializedName("results")
    @Expose
    private Results results;


    /**
     * 
     * @return
     *     The results
     */
    public Results getResults() {
        return results;
    }

    /**
     * 
     * @param results
     *     The results
     */
    public void setResults(Results results) {
        this.results = results;
    }

}
