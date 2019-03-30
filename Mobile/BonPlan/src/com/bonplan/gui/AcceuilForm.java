/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bonplan.gui;

import com.bonplan.util.Vars;
import com.codename1.components.ImageViewer;
import com.codename1.ui.Component;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.FlowLayout;
import java.io.IOException;

/**
 *
 * @author Hamrouni
 */
public class AcceuilForm {

    private Toolbar t;
    private Form f;
    private ImageViewer iv;
    private Image img;

    public AcceuilForm() {
        f = new Form("Acceuil", new BorderLayout());
        t = f.getToolbar();
        if (Vars.current_user == null) {
            t.addMaterialCommandToSideMenu("Connexion", FontImage.MATERIAL_WEB, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    Vars.current_choice = 0;
                    LoginForm lf = new LoginForm();
                    lf.getF().show();
                }
            });
            t.addMaterialCommandToSideMenu("Inscription", FontImage.MATERIAL_WEB, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    Vars.current_choice = 0;
                    RegisterForm rf = new RegisterForm();
                    rf.getF().show();
                }
            });
        } else {
            t.addMaterialCommandToSideMenu("Acceuil", FontImage.MATERIAL_WEB, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    Vars.current_choice = 0;
                    AcceuilForm af = new AcceuilForm();
                    af.getF().show();
                }
            });
            t.addMaterialCommandToSideMenu("Plan", FontImage.MATERIAL_WEB, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    Vars.current_choice = 0;
                }
            });
            t.addMaterialCommandToSideMenu("Deal", FontImage.MATERIAL_WEB, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    Vars.current_choice = 1;
                    AfficherDeals ads = new AfficherDeals();
                    ads.getF().show();

                }
            });
            t.addMaterialCommandToSideMenu("Enseigne", FontImage.MATERIAL_WEB, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    Vars.current_choice = 0;
                    new EnseigneForm().show();
                }
            });
            t.addMaterialCommandToSideMenu("Reservation", FontImage.MATERIAL_WEB, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {

                    Vars.current_choice = 0;
                    ReservationForm rf = new ReservationForm();
                    rf.getF().show();
                }
            });
            t.addMaterialCommandToSideMenu("Rechercher Deal", FontImage.MATERIAL_WEB, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    Vars.current_choice = 0;
                    RechercheForm rf = new RechercheForm();
                    rf.getF().show();
                }
            });
            t.addMaterialCommandToSideMenu("Mes Badges", FontImage.MATERIAL_WEB, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    Vars.current_choice = 0;
                    getUserBagdes b = new getUserBagdes();
                    b.getF().show();

                }
            });
            t.addMaterialCommandToSideMenu("Deconnexion", FontImage.MATERIAL_WEB, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    Vars.current_choice = 0;
                    Vars.current_user = null;
                    AcceuilForm af = new AcceuilForm();
                    af.getF().show();
                }
            });

        }
        try {
            img = URLImage.create("/aceuil.png");
        } catch (IOException ex) {
            System.out.println(ex);
        }
        iv = new ImageViewer(img);
        f.add(BorderLayout.CENTER,iv);
    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }

}
