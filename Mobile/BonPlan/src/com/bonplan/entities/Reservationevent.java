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

public class Reservationevent implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private Integer id;
    
    private int nombrplaces;
   
    private FosUser userId;
    
    private Evenement evenementId;

    public Reservationevent() {
    }

    public Reservationevent(Integer id) {
        this.id = id;
    }

    public Reservationevent(Integer id, int nombrplaces) {
        this.id = id;
        this.nombrplaces = nombrplaces;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getNombrplaces() {
        return nombrplaces;
    }

    public void setNombrplaces(int nombrplaces) {
        this.nombrplaces = nombrplaces;
    }

    public FosUser getUserId() {
        return userId;
    }

    public void setUserId(FosUser userId) {
        this.userId = userId;
    }

    public Evenement getEvenementId() {
        return evenementId;
    }

    public void setEvenementId(Evenement evenementId) {
        this.evenementId = evenementId;
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
        if (!(object instanceof Reservationevent)) {
            return false;
        }
        Reservationevent other = (Reservationevent) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.bonplan.entities.Reservationevent[ id=" + id + " ]";
    }
    
}
