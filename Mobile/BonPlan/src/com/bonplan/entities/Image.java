/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bonplan.entities;

import java.io.Serializable;


/**
 *
 * @author Hamrouni
 */
public class Image implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer id;
    private String url;
    private String alt;
    private Deal deal;
    private Enseigne enseigne;
    private Plan plan;
    private Evenement evenement;

    public Image() {
    }

    public Image(Integer id) {
        this.id = id;
    }

    public Image(Integer id, String url, String alt) {
        this.id = id;
        this.url = url;
        this.alt = alt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAlt() {
        return alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }

    public Deal getDeal() {
        return deal;
    }

    public void setDeal(Deal deal) {
        this.deal = deal;
    }

    public Enseigne getEnseigne() {
        return enseigne;
    }

    public void setEnseigne(Enseigne enseigne) {
        this.enseigne = enseigne;
    }

    public Plan getPlan() {
        return plan;
    }

    public void setPlan(Plan plan) {
        this.plan = plan;
    }

    public Evenement getEvenement() {
        return evenement;
    }

    public void setEvenement(Evenement evenement) {
        this.evenement = evenement;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Image)) {
            return false;
        }
        Image other = (Image) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.bonplan.entities.Image[ id=" + id + " ]";
    }
    
}
