/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bonplan.entities;


import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;


/**
 *
 * @author Hamrouni
 */
public class Evenement implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer id;
    private String nom;
    private String description;
    private Date date;
    private List<Reservationevent> reservationeventList;
    private Image imageId;
    private Enseigne enseigneId;

    public Evenement() {
    }

    public Evenement(Integer id) {
        this.id = id;
    }

    public Evenement(Integer id, String nom, String description, Date date) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.date = date;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<Reservationevent> getReservationeventList() {
        return reservationeventList;
    }

    public void setReservationeventList(List<Reservationevent> reservationeventList) {
        this.reservationeventList = reservationeventList;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Evenement)) {
            return false;
        }
        Evenement other = (Evenement) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.bonplan.entities.Evenement[ id=" + id + " ]";
    }
    
}
