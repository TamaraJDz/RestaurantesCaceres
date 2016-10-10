
package com.restaurantes.tamara.proyectoasee.Models.Restaurantes;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class SchemaAddressLocality {

    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("xml:lang")
    @Expose
    private String xmlLang;
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
     *     The xmlLang
     */
    public String getXmlLang() {
        return (xmlLang == null) ? xmlLang : "";
    }

    /**
     * 
     * @param xmlLang
     *     The xml:lang
     */
    public void setXmlLang(String xmlLang) {
        this.xmlLang = xmlLang;
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
