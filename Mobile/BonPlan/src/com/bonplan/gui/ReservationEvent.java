/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bonplan.gui;

import com.bonplan.entities.Reservationevent;
import com.bonplan.services.ReservationevenService;
import com.bonplan.util.Vars;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
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
 * @author yassine
 */
public class ReservationEvent {

    private Form f;
    private Toolbar t;

    public ReservationEvent() {
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
        ReservationevenService rs = new ReservationevenService();
        if (!rs.getReservations().isEmpty()) {
            Container c4 = new Container(new BoxLayout(BoxLayout.X_AXIS));
            c4.add(new Label("Nom Event"));
            c4.add(new Label("Date"));
            c4.add(new Label("Nbre Place "));

            Container c2 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
            for (Reservationevent r : rs.getReservations()) {
                Container c1 = new Container(new BoxLayout(BoxLayout.X_AXIS));
                Container c3 = new Container(new BoxLayout(BoxLayout.X_AXIS));
                Label nom = new Label(r.getEvenementId().getNom());
                Label date = new Label(new SimpleDateFormat("dd-MM-yyyy").format(r.getEvenementId().getDate()));
                Label nbr = new Label(String.valueOf(r.getNombrplaces()));
                Button modifier = new Button("Modifier");
                Button supprimer = new Button("Supprimer");
                c1.add(nom);
                c1.add(date);
                c1.add(nbr);
                modifier.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        Vars.current_reservationevent = r;
                        ModifierResEvent mre = new ModifierResEvent();
                        mre.getF().show();
                    }
                });
                supprimer.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        ReservationevenService res = new ReservationevenService();
                        res.delete(r.getId());
                        ReservationEvent re = new ReservationEvent();
                        re.getF().show();
                    }
                });
                c3.add(modifier);
                c3.add(supprimer);

                c2.add(c1);
                c2.add(c3);

            }
            f.add(c4);
            f.add(c2);
            f.show();
        } else {
            f.add(new Label("Aucune Reservation a afficher"));

        }
    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }
}
