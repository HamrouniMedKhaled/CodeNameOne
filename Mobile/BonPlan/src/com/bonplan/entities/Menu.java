/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bonplan.entities;


import java.io.Serializable;
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
public class Menu implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer id;
    private String contenu;
    private double prix;
    private Enseigne enseigneId;

    public Menu() {
    }

    

   

    public Menu(Integer id) {
        this.id = id;
    }

    public Menu(Integer id, String contenu, double prix) {
        this.id = id;
        this.contenu = contenu;
        this.prix = prix;
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

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
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
        if (!(object instanceof Menu)) {
            return false;
        }
        Menu other = (Menu) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.bonplan.entities.Menu[ id=" + id + " ]";
    }
    
}
