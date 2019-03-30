/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bonplan.util;

import com.bonplan.entities.*;
import java.util.ArrayList;

/**
 *
 * @author Hamrouni
 */
public class Vars {

    public static int current_admin = 0;
    public static int current_choice = 0;
    public static FosUser current_user;
    public static Deal current_deal;
    public static Reservationdeal current_reservationdeal;
    public static Enseigne current_enseigne;
    public static Adresse current_adresse;
    public static Categorie current_categorie;
    public static Image current_image;
    public static Plan current_plan;
    public static String current_type;
    public static Badge current_badge;
    public static int unlock_oldMarker = 0;
    public static int unlock_newMarker = 0;
    public static Reservation current_reservation;
    public static Reservationevent current_reservationevent;
    public static Evenement current_evenement;
    public static ArrayList<Badge> thisUserBadges = new ArrayList<>();
    public static ArrayList<Badge> allBadges = new ArrayList<>();
    public static String comment_user_name;

}
