/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bonplan.entities;

import java.io.Serializable;
import java.util.Date;


/**
 *
 * @author Hamrouni
 */
public class Reservation implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer id;
    private int nbplaces;
    private Date datereservation;
    private Enseigne enseigneId;
    private FosUser userId;

    public Reservation() {
    }

    public Reservation(Integer id) {
        this.id = id;
    }

    public Reservation(Integer id, int nbplaces, Date datereservation) {
        this.id = id;
        this.nbplaces = nbplaces;
        this.datereservation = datereservation;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getNbplaces() {
        return nbplaces;
    }

    public void setNbplaces(int nbplaces) {
        this.nbplaces = nbplaces;
    }

    public Date getDatereservation() {
        return datereservation;
    }

    public void setDatereservation(Date datereservation) {
        this.datereservation = datereservation;
    }

    public Enseigne getEnseigneId() {
        return enseigneId;
    }

    public void setEnseigneId(Enseigne enseigneId) {
        this.enseigneId = enseigneId;
    }

    public FosUser getUserId() {
        return userId;
    }

    public void setUserId(FosUser userId) {
        this.userId = userId;
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
        if (!(object instanceof Reservation)) {
            return false;
        }
        Reservation other = (Reservation) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.bonplan.entities.Reservation[ id=" + id + " ]";
    }
    
}
