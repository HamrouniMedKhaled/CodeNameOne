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
public class Commentaire implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer id;
    private String contenu;
    private int reportnumber;
    private boolean reported;
    private Date datecreation;
    private FosUser userId;
    private Plan planId;

    public Commentaire() {
    }

    public Commentaire(Integer id) {
        this.id = id;
    }

    public Commentaire(Integer id, String contenu, int reportnumber, boolean reported, Date datecreation) {
        this.id = id;
        this.contenu = contenu;
        this.reportnumber = reportnumber;
        this.reported = reported;
        this.datecreation = datecreation;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public int getReportnumber() {
        return reportnumber;
    }

    public void setReportnumber(int reportnumber) {
        this.reportnumber = reportnumber;
    }

    public boolean getReported() {
        return reported;
    }

    public void setReported(boolean reported) {
        this.reported = reported;
    }

    public Date getDatecreation() {
        return datecreation;
    }

    public void setDatecreation(Date datecreation) {
        this.datecreation = datecreation;
    }

    public FosUser getUserId() {
        return userId;
    }

    public void setUserId(FosUser userId) {
        this.userId = userId;
    }

    public Plan getPlanId() {
        return planId;
    }

    public void setPlanId(Plan planId) {
        this.planId = planId;
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
        if (!(object instanceof Commentaire)) {
            return false;
        }
        Commentaire other = (Commentaire) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.bonplan.entities.Commentaire[ id=" + id + " ]";
    }
    
}
