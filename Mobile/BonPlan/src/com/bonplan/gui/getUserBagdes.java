/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bonplan.gui;

import com.bonplan.entities.Badge;
import com.bonplan.services.CommentService;
import com.bonplan.util.Vars;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;

/**
 *
 * @author morrta
 */
public class getUserBagdes {

    Form f;
    SpanLabel lb;
    private Toolbar t;

    public getUserBagdes() {
        f = new Form("Mes badges", new FlowLayout(Component.CENTER, Component.TOP));
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
        lb = new SpanLabel("");
        f.add(lb);
        CommentService serviceComment = new CommentService();
        Container c = new Container(new BoxLayout(BoxLayout.Y_AXIS));

        for (Badge fromAll : serviceComment.getAllBadges()) {
            Container list2 = new Container(new BoxLayout(BoxLayout.X_AXIS));

            if (serviceComment.getBadges2(Vars.current_user.getId()).contains(fromAll)) {

                Label badgeName = new Label(fromAll.getName());

                Label username = new Label(" Débloqué :");

                username.getAllStyles().setFgColor(0x118c8b);
                list2.add(username);
                list2.add(badgeName);

            } else {

                Label badgeName = new Label(fromAll.getName());
                Label username = new Label(" Bloqué :");

                username.getAllStyles().setFgColor(0xf14d39);
                list2.add(username);
                list2.add(badgeName);

                badgeName.addPointerPressedListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        if (fromAll.getName().equals("bronze")) {
                            if (Dialog.show("Bronze", "vous devez atteindre " + 1 + " commentaire pour débloquer ce badge", "ok", null)) {
                                f.showBack();
                            }
                        }
                        if (fromAll.getName().equals("silver")) {
                            if (Dialog.show("Silver", "vous devez atteindre " + 3 + " commentaires pour débloquer ce badge", "ok", null)) {
                                f.showBack();
                            }
                        }
                        if (fromAll.getName().equals("gold")) {
                            if (Dialog.show("Gold", "vous devez atteindre " + 5 + " commentaires pour débloquer ce badge", "ok", null)) {
                                f.showBack();
                            }
                        }

                    }
                });

            }
            c.add(list2);

        }
        f.add(c);
       

    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }

}
