
package com.restaurantes.tamara.proyectoasee.Models.Restaurantes;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class GeoLat {

    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("datatype")
    @Expose
    private String datatype;
    @SerializedName("value")
    @Expose
    private String value;

    /**
     * 
     * @return
     *     The type
     */
    public String getType() {
        return type;
    }

    /**
     * 
     * @param type
     *     The type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * 
     * @return
     *     The datatype
     */
    public String getDatatype() {
        return datatype;
    }

    /**
     * 
     * @param datatype
     *     The datatype
     */
    public void setDatatype(String datatype) {
        this.datatype = datatype;
    }

    /**
     * 
     * @return
     *     The value
     */
    public String getValue() {
        return value;
    }

    /**
     * 
     * @param value
     *     The value
     */
    public void setValue(String value) {
        this.value = value;
    }

}
