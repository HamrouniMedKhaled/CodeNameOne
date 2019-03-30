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

public class Reservationdeal implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private Integer id;
   
    private boolean payer;
    
    private int nbr;
    
    private FosUser userId;
   
    private Deal dealId;

    public Reservationdeal() {
    }

    public Reservationdeal(Integer id) {
        this.id = id;
    }

    public Reservationdeal(Integer id, boolean payer, int nbr) {
        this.id = id;
        this.payer = payer;
        this.nbr = nbr;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean getPayer() {
        return payer;
    }

    public void setPayer(boolean payer) {
        this.payer = payer;
    }

    public int getNbr() {
        return nbr;
    }

    public void setNbr(int nbr) {
        this.nbr = nbr;
    }

    public FosUser getUserId() {
        return userId;
    }

    public void setUserId(FosUser userId) {
        this.userId = userId;
    }

    public Deal getDealId() {
        return dealId;
    }

    public void setDealId(Deal dealId) {
        this.dealId = dealId;
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
        if (!(object instanceof Reservationdeal)) {
            return false;
        }
        Reservationdeal other = (Reservationdeal) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.bonplan.entities.Reservationdeal[ id=" + id + " ]";
    }
    
}
