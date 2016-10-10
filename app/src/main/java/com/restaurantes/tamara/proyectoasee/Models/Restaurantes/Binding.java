
package com.restaurantes.tamara.proyectoasee.Models.Restaurantes;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Binding {

//com.restaurantes.tamara.proyectoasee.Models.Restaurantes.
    @Override
    public String toString() {
        String result = null;
        try {
            result = "URI=" + URI.getValue() +
                    ", rdfsLabel=" + rdfsLabel.getValue() +
                    ", schemaAddressCountry=" + schemaAddressCountry.getValue() +
                    ", schemaStreetAddress=" + schemaStreetAddress.getValue() +
                    ", schemaPostalCode=" + schemaPostalCode.getValue() +
                    ", schemaAddressLocality=" + schemaAddressLocality.getValue() +
                    ", schemaTelephone=" + schemaTelephone.getValue() +
                    ", schemaEmail=" + schemaEmail.getValue() +
                    ", schemaUrl=" + schemaUrl.getValue() +
                    ", omCategoriaRestaurante=" + omCategoriaRestaurante.getValue() +
                    ", omTenedores=" + omTenedores.getValue() +
                    ", omCapacidadPersonas=" + omCapacidadPersonas.getValue() +
                    ", geoLat=" + geoLat.getValue() +
                    ", geoLong=" + geoLong.getValue();
        }catch(NullPointerException e) { }

        return result;

                /*"Binding{" +
                "URI=" + URI.getValue() +
                ", rdfsLabel=" + rdfsLabel.getValue() +
                ", schemaAddressCountry=" + schemaAddressCountry.getValue() +
                ", schemaStreetAddress=" + schemaStreetAddress.getValue() +
                ", schemaPostalCode=" + schemaPostalCode.getValue() +
                ", schemaAddressLocality=" + schemaAddressLocality.getValue() +
                ", schemaTelephone=" + schemaTelephone.getValue() +
                ", schemaEmail=" + schemaEmail.getValue() +
                ", schemaUrl=" + schemaUrl.getValue() +
                ", omCategoriaRestaurante=" + omCategoriaRestaurante.getValue() +
                ", omTenedores=" + omTenedores.getValue() +
                ", omCapacidadPersonas=" + omCapacidadPersonas.getValue() +
                ", geoLat=" + geoLat.getValue() +
                ", geoLong=" + geoLong.getValue() +
                '}';*/
    }

    @SerializedName("URI")
    @Expose
    private URI URI;

    @SerializedName("rdfs_label")
    @Expose
    private RdfsLabel rdfsLabel;
    @SerializedName("schema_addressCountry")
    @Expose
    private SchemaAddressCountry schemaAddressCountry;
    @SerializedName("schema_streetAddress")
    @Expose
    private SchemaStreetAddress schemaStreetAddress;
    @SerializedName("schema_postalCode")
    @Expose
    private SchemaPostalCode schemaPostalCode;
    @SerializedName("schema_addressLocality")
    @Expose
    private SchemaAddressLocality schemaAddressLocality;
    @SerializedName("schema_telephone")
    @Expose
    private SchemaTelephone schemaTelephone;
    @SerializedName("schema_email")
    @Expose
    private SchemaEmail schemaEmail;
    @SerializedName("schema_url")
    @Expose
    private SchemaUrl schemaUrl;
    @SerializedName("om_categoriaRestaurante")
    @Expose
    private OmCategoriaRestaurante omCategoriaRestaurante;
    @SerializedName("om_tenedores")
    @Expose
    private OmTenedores omTenedores;
    @SerializedName("om_capacidadPersonas")
    @Expose
    private OmCapacidadPersonas omCapacidadPersonas;
    @SerializedName("geo_lat")
    @Expose
    private GeoLat geoLat;
    @SerializedName("geo_long")
    @Expose
    private GeoLong geoLong;

    /**
     * 
     * @return
     *     The URI     com.restaurantes.tamara.proyectoasee.Models.Restaurantes.
     */
    public com.restaurantes.tamara.proyectoasee.Models.Restaurantes.URI getURI() {
        return URI;
    }

    /**
     * 
     * @param URI
     *     The URI
     */
    public void setURL(com.restaurantes.tamara.proyectoasee.Models.Restaurantes.URI URI) {
        this.URI = URI;
    }

    /**
     * 
     * @return
     *     The rdfsLabel
     */
    public RdfsLabel getRdfsLabel() {
        return rdfsLabel;
    }

    /**
     * 
     * @param rdfsLabel
     *     The rdfs_label
     */
    public void setRdfsLabel(RdfsLabel rdfsLabel) {
        this.rdfsLabel = rdfsLabel;
    }

    /**
     * 
     * @return
     *     The schemaAddressCountry
     */
    public SchemaAddressCountry getSchemaAddressCountry() {
        return schemaAddressCountry;
    }

    /**
     * 
     * @param schemaAddressCountry
     *     The schema_addressCountry
     */
    public void setSchemaAddressCountry(SchemaAddressCountry schemaAddressCountry) {
        this.schemaAddressCountry = schemaAddressCountry;
    }

    /**
     * 
     * @return
     *     The schemaStreetAddress
     */
    public SchemaStreetAddress getSchemaStreetAddress() {
        return schemaStreetAddress;
    }

    /**
     * 
     * @param schemaStreetAddress
     *     The schema_streetAddress
     */
    public void setSchemaStreetAddress(SchemaStreetAddress schemaStreetAddress) {
        this.schemaStreetAddress = schemaStreetAddress;
    }

    /**
     * 
     * @return
     *     The schemaPostalCode
     */
    public SchemaPostalCode getSchemaPostalCode() {
        return schemaPostalCode;
    }

    /**
     * 
     * @param schemaPostalCode
     *     The schema_postalCode
     */
    public void setSchemaPostalCode(SchemaPostalCode schemaPostalCode) {
        this.schemaPostalCode = schemaPostalCode;
    }

    /**
     * 
     * @return
     *     The schemaAddressLocality
     */
    public SchemaAddressLocality getSchemaAddressLocality() {
        return schemaAddressLocality;
    }

    /**
     * 
     * @param schemaAddressLocality
     *     The schema_addressLocality
     */
    public void setSchemaAddressLocality(SchemaAddressLocality schemaAddressLocality) {
        this.schemaAddressLocality = schemaAddressLocality;
    }

    /**
     * 
     * @return
     *     The schemaTelephone
     */
    public SchemaTelephone getSchemaTelephone() {
        return schemaTelephone;
    }

    /**
     * 
     * @param schemaTelephone
     *     The schema_telephone
     */
    public void setSchemaTelephone(SchemaTelephone schemaTelephone) {
        this.schemaTelephone = schemaTelephone;
    }

    /**
     * 
     * @return
     *     The schemaEmail
     */
    public SchemaEmail getSchemaEmail() {
        return schemaEmail;
    }

    /**
     * 
     * @param schemaEmail
     *     The schema_email
     */
    public void setSchemaEmail(SchemaEmail schemaEmail) {
        this.schemaEmail = schemaEmail;
    }

    /**
     * 
     * @return
     *     The schemaUrl
     */
    public SchemaUrl getSchemaUrl() {
        return schemaUrl;
    }

    /**
     * 
     * @param schemaUrl
     *     The schema_url
     */
    public void setSchemaUrl(SchemaUrl schemaUrl) {
        this.schemaUrl = schemaUrl;
    }

    /**
     * 
     * @return
     *     The omCategoriaRestaurante
     */
    public OmCategoriaRestaurante getOmCategoriaRestaurante() {
        return omCategoriaRestaurante;
    }

    /**
     * 
     * @param omCategoriaRestaurante
     *     The om_categoriaRestaurante
     */
    public void setOmCategoriaRestaurante(OmCategoriaRestaurante omCategoriaRestaurante) {
        this.omCategoriaRestaurante = omCategoriaRestaurante;
    }

    /**
     * 
     * @return
     *     The omTenedores
     */
    public OmTenedores getOmTenedores() {
        return omTenedores;
    }

    /**
     * 
     * @param omTenedores
     *     The om_tenedores
     */
    public void setOmTenedores(OmTenedores omTenedores) {
        this.omTenedores = omTenedores;
    }

    /**
     * 
     * @return
     *     The omCapacidadPersonas
     */
    public OmCapacidadPersonas getOmCapacidadPersonas() {
        return omCapacidadPersonas;
    }

    /**
     * 
     * @param omCapacidadPersonas
     *     The om_capacidadPersonas
     */
    public void setOmCapacidadPersonas(OmCapacidadPersonas omCapacidadPersonas) {
        this.omCapacidadPersonas = omCapacidadPersonas;
    }

    /**
     * 
     * @return
     *     The geoLat
     */
    public GeoLat getGeoLat() {
        return geoLat;
    }

    /**
     * 
     * @param geoLat
     *     The geo_lat
     */
    public void setGeoLat(GeoLat geoLat) {
        this.geoLat = geoLat;
    }

    /**
     * 
     * @return
     *     The geoLong
     */
    public GeoLong getGeoLong() {
        return geoLong;
    }

    /**
     * 
     * @param geoLong
     *     The geo_long
     */
    public void setGeoLong(GeoLong geoLong) {
        this.geoLong = geoLong;
    }

}
