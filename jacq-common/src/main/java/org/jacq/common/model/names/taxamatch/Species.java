package org.jacq.common.model.names.taxamatch;

import java.util.ArrayList;
import java.util.List;

public class Species {

    private String name;
    private Long distance;
    private Double ratio;
    private String taxon;
    private Long taxonID;
    private String syn;
    private Long synID;
    private List<Object> commonNames = new ArrayList<Object>();
    private String family;

    /**
     *
     * @return The name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return The distance
     */
    public Long getDistance() {
        return distance;
    }

    /**
     *
     * @param distance The distance
     */
    public void setDistance(Long distance) {
        this.distance = distance;
    }

    /**
     *
     * @return The ratio
     */
    public Double getRatio() {
        return ratio;
    }

    /**
     *
     * @param ratio The ratio
     */
    public void setRatio(Double ratio) {
        this.ratio = ratio;
    }

    /**
     *
     * @return The taxon
     */
    public String getTaxon() {
        return taxon;
    }

    /**
     *
     * @param taxon The taxon
     */
    public void setTaxon(String taxon) {
        this.taxon = taxon;
    }

    /**
     *
     * @return The taxonID
     */
    public Long getTaxonID() {
        return taxonID;
    }

    /**
     *
     * @param taxonID The taxonID
     */
    public void setTaxonID(Long taxonID) {
        this.taxonID = taxonID;
    }

    /**
     *
     * @return The syn
     */
    public String getSyn() {
        return syn;
    }

    /**
     *
     * @param syn The syn
     */
    public void setSyn(String syn) {
        this.syn = syn;
    }

    /**
     *
     * @return The synID
     */
    public Long getSynID() {
        return synID;
    }

    /**
     *
     * @param synID The synID
     */
    public void setSynID(Long synID) {
        this.synID = synID;
    }

    /**
     *
     * @return The commonNames
     */
    public List<Object> getCommonNames() {
        return commonNames;
    }

    /**
     *
     * @param commonNames The commonNames
     */
    public void setCommonNames(List<Object> commonNames) {
        this.commonNames = commonNames;
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }
}
