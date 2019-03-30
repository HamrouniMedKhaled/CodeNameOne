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

public class BadgeUnlock implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private Integer id;
  
    private FosUser userId;
  
    private Badge badgeId;

    public BadgeUnlock() {
    }

    public BadgeUnlock(Integer id) {
        this.id = id;
    }
     public BadgeUnlock( Badge badgeId,FosUser userId) {
        this.userId = userId;
        this.badgeId = badgeId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public FosUser getUserId() {
        return userId;
    }

    public void setUserId(FosUser userId) {
        this.userId = userId;
    }

    public Badge getBadgeId() {
        return badgeId;
    }

    public void setBadgeId(Badge badgeId) {
        this.badgeId = badgeId;
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
        if (!(object instanceof BadgeUnlock)) {
            return false;
        }
        BadgeUnlock other = (BadgeUnlock) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.bonplan.entities.BadgeUnlock[ id=" + id + " ]";
    }
    
}
