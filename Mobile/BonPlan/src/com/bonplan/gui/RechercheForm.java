/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bonplan.gui;

import com.bonplan.entities.Categorie;
import com.bonplan.services.CategorieService;
import com.bonplan.util.Vars;
import com.codename1.components.ImageViewer;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.spinner.Picker;

/**
 *
 * @author Hamrouni
 */
public class RechercheForm {

    private Toolbar t;
    private Form f;
    private ImageViewer iv;
    private Image img;

    public RechercheForm() {
        f = new Form("Rechercher", new FlowLayout(Component.TOP, Component.TOP));
        t = f.getToolbar();
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

        Container c = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        Button mesd = new Button("Mes Deals");
        Button score = new Button("Afficher par Score");
        TextField ville = new TextField("", "Entrer une ville");
        Button vil = new Button("Rechercher par ville");
        Picker categories = new Picker();
        CategorieService cs = new CategorieService();
        String[] s = new String[cs.getCategories().size()];
        int i = 0;
        for (Categorie cc : cs.getCategories()) {

            s[i] = cc.getNom();
            i++;
        }
        categories.setStrings(s);
        mesd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                Vars.current_choice = 2;
                AfficherDeals ads = new AfficherDeals();
                ads.getF().show();
            }
        });

        score.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                Vars.current_choice = 3;
                AfficherDeals ads = new AfficherDeals();
                ads.getF().show();

            }
        });
        vil.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                Vars.current_choice = 5;
                Vars.current_type = ville.getText();
                AfficherDeals ads = new AfficherDeals();
                ads.getF().show();
            }
        });
        categories.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                Vars.current_choice = 4;
                Vars.current_type = categories.getSelectedString();
                AfficherDeals ads = new AfficherDeals();
                ads.getF().show();
            }
        });
        Button Afficherr = new Button("Afficher Mes Reservation");
        Afficherr.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                AfficherReservation ar = new AfficherReservation();
                ar.getF().show();
            }
        });

        c.add(mesd);
        c.add(score);
        c.add(categories);
        c.add(ville);
        c.add(vil);
        c.add(Afficherr);
        f.add(c);
    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }

}
