/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bonplan.gui;

import com.bonplan.services.ReservationService;
import com.bonplan.util.Vars;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.spinner.Picker;
import java.util.Date;

/**
 *
 * @author yassine
 */
public class AjouterResEns {

    private Toolbar t;
    private Form f;

    public AjouterResEns() {
        f = new Form("Reserver", new FlowLayout(Component.TOP, Component.TOP));
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
        Container c1 = new Container(new BoxLayout(BoxLayout.X_AXIS));
        Container c2 = new Container(new BoxLayout(BoxLayout.X_AXIS));
        c1.add(new Label("Nombre de place :"));
        TextField nbr = new TextField("", "Nombre de places");
        c1.add(nbr);
        c2.add(new Label("Date Reservation :"));
        Date dt = new Date();
        Picker p = new Picker();
        p.setDate(dt);
        c2.add(p);
        Button reserver = new Button("Reserver");
        reserver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((dt.getTime() - p.getDate().getTime()) > 0) {
                    if (Dialog.show("Error", "Date reservation unvalide", "ok", null)) {
                        f.showBack();
                    }
                } else {
                    if ((nbr.getText().length() == 0) || (Float.parseFloat(nbr.getText()) < 0)) {
                        if (Dialog.show("Error", "Nombre de place unvalide", "ok", null)) {
                            f.showBack();
                        }
                    } else {
                        ReservationService rs = new ReservationService();
                        rs.Reserver((int) Float.parseFloat(nbr.getText()), p.getDate());
                        ReservationEnseigne re = new ReservationEnseigne();
                        re.getF().show();
                    }
                }
            }
        });
        f.add(c1);
        f.add(c2);
        f.add(reserver);
    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }

}
