/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bonplan.gui;

/**
 *
 * @author Hamrouni
 */
import com.bonplan.entities.Deal;
import com.bonplan.services.DealService;
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

public class AfficherDeals {

    private Form f;
    private Toolbar t;
    private EncodedImage ei;

    public AfficherDeals() {
        f = new Form("Affichage", new FlowLayout(Component.CENTER, Component.TOP));

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
        DealService ds = new DealService();
        if (!ds.getDeals().isEmpty()) {
            for (Deal d : ds.getDeals()) {
                try {
                    ei = EncodedImage.create("/giphy.gif");
                } catch (IOException ex) {

                }
                Image img = URLImage.createToStorage(ei, String.valueOf(d.getImageId().getId()), "http://127.0.0.1/bonplan/Projet_pidev/web/uploads/images/" + d.getImageId().getUrl()+".png");
                ImageViewer iv = new ImageViewer(img);
                Container c1 = new Container(new BoxLayout(BoxLayout.X_AXIS));
                Container c2 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
                Container c6 = new Container(new BoxLayout(BoxLayout.X_AXIS));
                Container c7 = new Container(new BoxLayout(BoxLayout.X_AXIS));
                c1.add(iv);
                Label nom = new Label(d.getNom());
                c2.add(nom);
                Label score = new Label(String.valueOf(d.getScore()));
                c6.add(new Label("Score:"));
                c6.add(score);

                Label prix = new Label(String.valueOf(d.getPrix()));
                Label prixx = new Label(String.valueOf(String.valueOf(d.getPrix() - ((d.getPrix() * d.getTred()) / 100))));
                c7.add(new Label("Prix:"));
                c7.add(prix);
                c7.add(new Label("TND"));
                c7.add(prixx);
                c2.add(c6);
                c2.add(c7);
                Button aff = new Button("Afficher");
                aff.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {

                        AfficherDeal ad = new AfficherDeal(d.getId());
                        ad.getF().show();
                    }
                });

                c2.add(aff);
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
