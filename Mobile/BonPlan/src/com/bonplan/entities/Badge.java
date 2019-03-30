/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bonplan.entities;

import java.io.Serializable;
import java.util.List;


/**
 *
 * @author Hamrouni
 */

public class Badge implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private Integer id;
   
    private String name;
    
    private String actionName;
   
    private int actionCount;
   
    private List<BadgeUnlock> badgeUnlockList;

    public Badge() {
    }

    public Badge(Integer id) {
        this.id = id;
    }

    public Badge(Integer id, String name, String actionName, int actionCount) {
        this.id = id;
        this.name = name;
        this.actionName = actionName;
        this.actionCount = actionCount;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getActionName() {
        return actionName;
    }

    public void setActionName(String actionName) {
        this.actionName = actionName;
    }

    public int getActionCount() {
        return actionCount;
    }

    public void setActionCount(int actionCount) {
        this.actionCount = actionCount;
    }

    public List<BadgeUnlock> getBadgeUnlockList() {
        return badgeUnlockList;
    }

    public void setBadgeUnlockList(List<BadgeUnlock> badgeUnlockList) {
        this.badgeUnlockList = badgeUnlockList;
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
        if (!(object instanceof Badge)) {
            return false;
        }
        Badge other = (Badge) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.bonplan.entities.Badge[ id=" + id + " ]";
    }
    
}
