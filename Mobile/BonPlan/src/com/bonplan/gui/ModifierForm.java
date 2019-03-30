/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bonplan.gui;

import com.bonplan.services.DealService;
import com.bonplan.util.Vars;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextArea;
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
 * @author Hamrouni
 */
public class ModifierForm {

    private Toolbar t;
    private Form f;

    public ModifierForm() {
        f = new Form("Modifier deal", new FlowLayout(Component.TOP, Component.TOP));
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

        TextField nom = new TextField(Vars.current_deal.getNom(), "");
        TextField prix = new TextField(String.valueOf(Vars.current_deal.getPrix()), "");
        TextField tred = new TextField(String.valueOf(Vars.current_deal.getTred()), "");
        Date dt = new Date();
        Picker p = new Picker();
        p.setDate(Vars.current_deal.getDatedebut());
        Picker p1 = new Picker();
        p1.setDate(Vars.current_deal.getDatefin());
        TextField score = new TextField(String.valueOf(Vars.current_deal.getScore()), "");
        TextArea desc = new TextArea(Vars.current_deal.getDescription());
        Button ajouter = new Button("Modifier");
        ajouter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if (nom.getText().length() == 0) {
                    if (Dialog.show("Error", "Nom de deal unvalide", "ok", null)) {
                        f.showBack();
                    }
                } else {
                    if ((prix.getText().length() == 0) || (Float.parseFloat(prix.getText()) < 0)) {
                        if (Dialog.show("Error", "Prix de deal unvalide", "ok", null)) {
                            f.showBack();
                        }
                    } else {
                        if ((tred.getText().length() == 0) || ((int) Float.parseFloat(tred.getText()) < 0) || ((int) Float.parseFloat(tred.getText()) > 100)) {
                            if (Dialog.show("Error", "Taux de reduction unvalide", "ok", null)) {
                                f.showBack();
                            }
                        } else {
                            if ((score.getText().length() == 0) || ((int) Float.parseFloat(score.getText()) < 0)) {
                                if (Dialog.show("Error", "Score de deal unvalide", "ok", null)) {
                                    f.showBack();
                                }
                            } else {
                                if ((dt.getTime() - p.getDate().getTime()) > 0) {
                                    if (Dialog.show("Error", "Date depart de deal unvalide", "ok", null)) {
                                        f.showBack();
                                    }
                                } else {
                                    if ((p.getDate().getTime() - p1.getDate().getTime()) > 0) {
                                        if (Dialog.show("Error", "Date fin de deal unvalide", "ok", null)) {
                                            f.showBack();
                                        }
                                    } else {

                                        Vars.current_deal.setNom(nom.getText());
                                        Vars.current_deal.setPrix(Float.parseFloat(prix.getText()));
                                        Vars.current_deal.setTred((int) Float.parseFloat(tred.getText()));
                                        Vars.current_deal.setScore((int) Float.parseFloat(score.getText()));
                                        Vars.current_deal.setDatedebut(p.getDate());
                                        Vars.current_deal.setDatefin(p1.getDate());
                                        Vars.current_deal.setDescription(desc.getText());
                                        DealService ds = new DealService();
                                        ds.ModifierDeal(Vars.current_deal.getId());
                                        Vars.current_choice = 2;
                                        AfficherDeals ad = new AfficherDeals();
                                        ad.getF().show();

                                    }
                                }
                            }
                        }
                    }

                }

            }
        });

        Container c1 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        c1.add(nom);
        c1.add(score);
        c1.add(prix);
        c1.add(tred);
        c1.add(p);
        c1.add(p1);
        c1.add(desc);
        c1.add(ajouter);
        f.add(c1);
    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }

}
