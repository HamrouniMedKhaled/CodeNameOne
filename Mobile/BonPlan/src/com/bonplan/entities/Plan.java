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

public class Plan implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private Integer id;
    
    private String titre;
    
    private String description;
    
    private int score;
   
    private boolean active;
    
    private Date dateAjout;
   
    private double lng;
    
    private double lat;
    
    private int reportnumber;
    
    private boolean reported;
   
    private List<Commentaire> commentaireList;
    
    private Image imageId;
    
    private FosUser userId;
    
    private Categorie categorieId;

    public Plan() {
    }

    public Plan(Integer id) {
        this.id = id;
    }

    public Plan(Integer id, String titre, String description, int score, boolean active, Date dateAjout, double lng, double lat, int reportnumber, boolean reported) {
        this.id = id;
        this.titre = titre;
        this.description = description;
        this.score = score;
        this.active = active;
        this.dateAjout = dateAjout;
        this.lng = lng;
        this.lat = lat;
        this.reportnumber = reportnumber;
        this.reported = reported;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public boolean getActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Date getDateAjout() {
        return dateAjout;
    }

    public void setDateAjout(Date dateAjout) {
        this.dateAjout = dateAjout;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
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

    public List<Commentaire> getCommentaireList() {
        return commentaireList;
    }

    public void setCommentaireList(List<Commentaire> commentaireList) {
        this.commentaireList = commentaireList;
    }

    public Image getImageId() {
        return imageId;
    }

    public void setImageId(Image imageId) {
        this.imageId = imageId;
    }

    public FosUser getUserId() {
        return userId;
    }

    public void setUserId(FosUser userId) {
        this.userId = userId;
    }

    public Categorie getCategorieId() {
        return categorieId;
    }

    public void setCategorieId(Categorie categorieId) {
        this.categorieId = categorieId;
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
        if (!(object instanceof Plan)) {
            return false;
        }
        Plan other = (Plan) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.bonplan.entities.Plan[ id=" + id + " ]";
    }
    
}
