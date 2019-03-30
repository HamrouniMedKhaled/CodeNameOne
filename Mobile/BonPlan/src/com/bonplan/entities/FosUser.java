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
public class FosUser implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer id;
    private String username;
    private String usernameCanonical;
    private String email;
    private String emailCanonical;
    private boolean enabled;
    private String salt;
    private String password;
    private Date lastLogin;
    private String confirmationToken;
    private Date passwordRequestedAt;
    private String roles;
    private int score;
    private int report;
    private List<BadgeUnlock> badgeUnlockList;
    private List<Notification> notificationList;
    private List<Reservationevent> reservationeventList;
    
    private List<Enseigne> enseigneList;
    private List<Reservation> reservationList;
    private List<Comment> commentList;
    private List<Commentaire> commentaireList;
    private List<Plan> planList;
    private List<Reservationdeal> reservationdealList;
    private String telephone ;

    public FosUser() {
    }

    public FosUser(Integer id) {
        this.id = id;
    }
     public FosUser(Integer id,String username) {
        this.id = id;
        this.username = username;
    }
    public FosUser (Integer id,String username , String telephone){
        this.id = id;
        this.username = username;
        this.telephone = telephone;
    
    }

    public FosUser(Integer id, String username, String usernameCanonical, String email, String emailCanonical, boolean enabled, String password, String roles, int score, int report) {
        this.id = id;
        this.username = username;
        this.usernameCanonical = usernameCanonical;
        this.email = email;
        this.emailCanonical = emailCanonical;
        this.enabled = enabled;
        this.password = password;
        this.roles = roles;
        this.score = score;
        this.report = report;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsernameCanonical() {
        return usernameCanonical;
    }

    public void setUsernameCanonical(String usernameCanonical) {
        this.usernameCanonical = usernameCanonical;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmailCanonical() {
        return emailCanonical;
    }

    public void setEmailCanonical(String emailCanonical) {
        this.emailCanonical = emailCanonical;
    }

    public boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    public String getConfirmationToken() {
        return confirmationToken;
    }

    public void setConfirmationToken(String confirmationToken) {
        this.confirmationToken = confirmationToken;
    }

    public Date getPasswordRequestedAt() {
        return passwordRequestedAt;
    }

    public void setPasswordRequestedAt(Date passwordRequestedAt) {
        this.passwordRequestedAt = passwordRequestedAt;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getReport() {
        return report;
    }

    public void setReport(int report) {
        this.report = report;
    }

    public List<BadgeUnlock> getBadgeUnlockList() {
        return badgeUnlockList;
    }

    public void setBadgeUnlockList(List<BadgeUnlock> badgeUnlockList) {
        this.badgeUnlockList = badgeUnlockList;
    }

    public List<Notification> getNotificationList() {
        return notificationList;
    }

    public void setNotificationList(List<Notification> notificationList) {
        this.notificationList = notificationList;
    }

    public List<Reservationevent> getReservationeventList() {
        return reservationeventList;
    }

    public void setReservationeventList(List<Reservationevent> reservationeventList) {
        this.reservationeventList = reservationeventList;
    }

    public List<Enseigne> getEnseigneList() {
        return enseigneList;
    }

    public void setEnseigneList(List<Enseigne> enseigneList) {
        this.enseigneList = enseigneList;
    }

    public List<Reservation> getReservationList() {
        return reservationList;
    }

    public void setReservationList(List<Reservation> reservationList) {
        this.reservationList = reservationList;
    }

    public List<Comment> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<Comment> commentList) {
        this.commentList = commentList;
    }

    public List<Commentaire> getCommentaireList() {
        return commentaireList;
    }

    public void setCommentaireList(List<Commentaire> commentaireList) {
        this.commentaireList = commentaireList;
    }

    public List<Plan> getPlanList() {
        return planList;
    }

    public void setPlanList(List<Plan> planList) {
        this.planList = planList;
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
        if (!(object instanceof FosUser)) {
            return false;
        }
        FosUser other = (FosUser) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.bonplan.entities.FosUser[ id=" + id + " ]";
    }
    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
    
}
