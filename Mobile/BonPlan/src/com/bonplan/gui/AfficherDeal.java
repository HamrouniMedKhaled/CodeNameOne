/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bonplan.gui;

import com.bonplan.services.DealService;
import com.bonplan.services.ReservationdealService;
import com.bonplan.util.Vars;
import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
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
 * @author Hamrouni
 */
public class AfficherDeal {

    private Form f;
    private Toolbar t;
    private EncodedImage ei;
    private final ImageViewer iv;
    private final Image img;
    Container c1, c2, c3, c4, c5, c6, c7, c8;
    private int nbnb = 0;

    public AfficherDeal(int x) {

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

        Vars.current_deal = ds.affichedeal(x);
        try {
            ei = EncodedImage.create("/giphy.gif");
        } catch (IOException ex) {

        }
        img = URLImage.createToStorage(ei, String.valueOf(Vars.current_deal.getImageId().getId()), "http://127.0.0.1/bonplan/Projet_pidev/web/uploads/images/" + Vars.current_deal.getImageId().getUrl());
        iv = new ImageViewer(img);
        c1 = new Container(new BoxLayout(BoxLayout.X_AXIS));
        c2 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        c3 = new Container(new BoxLayout(BoxLayout.X_AXIS));
        c4 = new Container(new BoxLayout(BoxLayout.X_AXIS));
        c5 = new Container(new BoxLayout(BoxLayout.X_AXIS));
        c6 = new Container(new BoxLayout(BoxLayout.X_AXIS));
        c7 = new Container(new BoxLayout(BoxLayout.X_AXIS));
        c8 = new Container(new BoxLayout(BoxLayout.X_AXIS));

        Label nom = new Label(Vars.current_deal.getNom());
        Label nbrvisite = new Label(String.valueOf(Vars.current_deal.getVisite()));
        Label cat = new Label(Vars.current_deal.getEnseigneId().getCategorieId().getNom());
        c2.add(nom);
        c4.add(new Label("Nombre de visites:"));
        c4.add(nbrvisite);
        c5.add(new Label("Categorie:"));
        c5.add(cat);
        Label score = new Label(String.valueOf(Vars.current_deal.getScore()));
        c6.add(new Label("Score:"));
        c6.add(score);

        Label prix = new Label(String.valueOf(Vars.current_deal.getPrix()));
        Label prixx = new Label(String.valueOf(String.valueOf(Vars.current_deal.getPrix() - ((Vars.current_deal.getPrix() * Vars.current_deal.getTred()) / 100))));
        c7.add(new Label("Prix:"));
        c7.add(prix);
        c7.add(new Label("TND"));
        c7.add(prixx);

        Button plus = new Button("+");
        Label n = new Label("00");
        Button moins = new Button("-");
        plus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if (nbnb < Vars.current_deal.getEnseigneId().getCapacite()) {
                    nbnb++;
                }
                if (nbnb < 10) {
                    n.setText("0" + String.valueOf(nbnb));
                } else {
                    n.setText(String.valueOf(nbnb));
                }
            }
        });
        moins.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if (nbnb > 0) {
                    nbnb--;
                }
                if (nbnb < 10) {
                    n.setText("0" + String.valueOf(nbnb));
                } else {
                    n.setText(String.valueOf(nbnb));
                }
            }
        });

        c8.add(plus);
        c8.add(n);
        c8.add(moins);
        Button reserver = new Button("Reserver");
        reserver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if (nbnb > 0) {
                    ReservationdealService rds = new ReservationdealService();
                    rds.Reserver(nbnb);
                    AfficherReservation ar = new AfficherReservation();
                    ar.getF().show();
                } else {
                    if (Dialog.show("Error", "Nombre de place doit être superieur a zéro", "ok", null)) {
                        f.showBack();
                    }
                }

            }
        });
        c2.add(c4);
        c2.add(c5);
        c2.add(c6);
        c2.add(c7);
        Label ll = new Label("Nombres de places:");
        c2.add(ll);
        c2.add(c8);
        c2.add(reserver);
        Label des = new Label("Description");
        SpanLabel tx = new SpanLabel(Vars.current_deal.getDescription());
        c2.add(des);
        c2.add(tx);

        Button suprimer = new Button("suprimer");
        Button modifier = new Button("modifier");
        if (Vars.current_choice == 2) {
            c8.setHidden(true);
            ll.setHidden(true);
            reserver.setHidden(true);
        } else {
            c3.setHidden(true);
        }
        c3.add(suprimer);
        suprimer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                ds.SupprimerDeal(Vars.current_deal.getId());
                AfficherDeals ads = new AfficherDeals();
                ads.getF().show();
            }
        });
        modifier.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {

                ModifierForm mf = new ModifierForm();
                mf.getF().show();
            }
        });

        c3.add(modifier);
        Button com = new Button("Liste des commentaires");
        com.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                commentForm cf = new commentForm();
                cf.getF().show();
            }
        });
        c1.add(iv);
        f.add(c1);
        f.add(c2);
        f.add(c3);
        f.add(com);

    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }

}
