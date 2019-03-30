/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bonplan.gui;

import com.bonplan.entities.Reservationdeal;
import com.bonplan.services.ReservationdealService;
import com.bonplan.util.Vars;
import com.codename1.components.ImageViewer;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import java.io.IOException;

/**
 *
 * @author yassine
 */
public class AfficherReservation {

    private Form f;
    private Toolbar t;
    private EncodedImage ei;

    public AfficherReservation() {
        f = new Form("Mes Reservation", new FlowLayout(Component.CENTER, Component.TOP));

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
        ReservationdealService rds = new ReservationdealService();
        if (!rds.getReservations().isEmpty()) {
            for (Reservationdeal r : rds.getReservations()) {
                try {
                    ei = EncodedImage.create("/giphy.gif");
                } catch (IOException ex) {

                }
                Image img = URLImage.createToStorage(ei, String.valueOf(r.getDealId().getImageId().getId()), "http://127.0.0.1/bonplan/Projet_pidev/web/uploads/images/" + r.getDealId().getImageId().getUrl()+".png");
                ImageViewer iv = new ImageViewer(img);
                Container c1 = new Container(new BoxLayout(BoxLayout.X_AXIS));
                Container c2 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
                Container c6 = new Container(new BoxLayout(BoxLayout.X_AXIS));
                Container c7 = new Container(new BoxLayout(BoxLayout.X_AXIS));
                Container c8 = new Container(new BoxLayout(BoxLayout.X_AXIS));
                Container c9 = new Container(new BoxLayout(BoxLayout.X_AXIS));
                c1.add(iv);
                Label nom = new Label(r.getDealId().getNom());
                c2.add(nom);
                Label score = new Label(String.valueOf(r.getDealId().getScore()));
                c6.add(new Label("Score:"));
                c6.add(score);

                Label prix = new Label(String.valueOf(r.getDealId().getPrix()));
                Label prixx = new Label(String.valueOf(String.valueOf(r.getDealId().getPrix() - ((r.getDealId().getPrix() * r.getDealId().getTred()) / 100))));
                c7.add(new Label("Prix:"));
                c7.add(prix);
                c7.add(new Label("TND"));
                c7.add(prixx);
                c2.add(c6);
                c2.add(c7);
                c8.add(new Label("Nombre de places:"));
                Label place = new Label(String.valueOf(r.getNbr()));
                c8.add(place);
                c2.add(c8);

                Button payer = new Button("Payer");
                payer.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        Vars.current_reservationdeal = r;
                        rds.Payer(r.getId());
                        AfficherReservation ar = new AfficherReservation();
                        ar.getF().show();

                    }
                });
                Button Anuler = new Button("Annuler");
                Anuler.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {

                        rds.Annuler(r.getId());
                        AfficherReservation ar = new AfficherReservation();
                        ar.getF().show();

                    }
                });
                if ((r.getPayer() == true) || (Vars.current_user.getScore() < (r.getNbr() * r.getDealId().getScore()))) {
                    payer.setHidden(true);
                }
                c9.add(payer);
                c9.add(Anuler);
                c2.add(c9);
                f.add(c1);
                f.add(c2);
            }
        } else {
            f.add(new Label("Aucun deal a afficher"));

        }

    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }

}
