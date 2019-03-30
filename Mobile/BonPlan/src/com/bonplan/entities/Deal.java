/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bonplan.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


/**
 *
 * @author Hamrouni
 */
public class Deal implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer id;
    private String nom;
    private int score;
    private double prix;
    private int tred;
    private int visite;
    private Date datedebut;
    private Date datefin;
    private boolean active;
    private String description;
    private Image imageId;
    private Enseigne enseigneId;
    private List<Comment> commentList;
    private List<Reservationdeal> reservationdealList;

    public Deal() {
    }

    public Deal(Integer id) {
        this.id = id;
    }

    public Deal(Integer id, String nom, int score, double prix, int tred, int visite, Date datedebut, Date datefin, boolean active, String description) {
        this.id = id;
        this.nom = nom;
        this.score = score;
        this.prix = prix;
        this.tred = tred;
        this.visite = visite;
        this.datedebut = datedebut;
        this.datefin = datefin;
        this.active = active;
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public int getTred() {
        return tred;
    }

    public void setTred(int tred) {
        this.tred = tred;
    }

    public int getVisite() {
        return visite;
    }

    public void setVisite(int visite) {
        this.visite = visite;
    }

    public Date getDatedebut() {
        return datedebut;
    }

    public void setDatedebut(Date datedebut) {
        this.datedebut = datedebut;
    }

    public Date getDatefin() {
        return datefin;
    }

    public void setDatefin(Date datefin) {
        this.datefin = datefin;
    }

    public boolean getActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Image getImageId() {
        return imageId;
    }

    public void setImageId(Image imageId) {
        this.imageId = imageId;
    }

    public Enseigne getEnseigneId() {
        return enseigneId;
    }

    public void setEnseigneId(Enseigne enseigneId) {
        this.enseigneId = enseigneId;
    }

    public List<Comment> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<Comment> commentList) {
        this.commentList = commentList;
    }

    public List<Reservationdeal> getReservationdealList() {
        return reservationdealList;
    }

    public void setReservationdealList(List<Reservationdeal> reservationdealList) {
        this.reservationdealList = reservationdealList;
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
        if (!(object instanceof Deal)) {
            return false;
        }
        Deal other = (Deal) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Deal{" + "id=" + id + ", nom=" + nom + ", score=" + score + ", prix=" + prix + ", tred=" + tred + ", visite=" + visite + ", datedebut=" + datedebut + ", datefin=" + datefin + ", active=" + active + ", description=" + description + ", imageId=" + imageId + ", enseigneId=" + enseigneId + ", commentList=" + commentList + ", reservationdealList=" + reservationdealList + '}';
    }

 
}
