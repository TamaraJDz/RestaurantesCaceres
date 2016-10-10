
package com.restaurantes.tamara.proyectoasee.Models.Restaurantes;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.List;


public class Results {


    @Expose
    private List<Binding> bindings = new ArrayList<Binding>();


    /**
     * 
     * @return
     *     The bindings
     */
    public List<Binding> getBindings() {
        return bindings;
    }

    /**
     * 
     * @param bindings
     *     The bindings
     */
    public void setBindings(List<Binding> bindings) {
        this.bindings = bindings;
    }

}
