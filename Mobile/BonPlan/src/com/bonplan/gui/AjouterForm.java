/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bonplan.gui;

import com.bonplan.entities.Deal;
import com.bonplan.entities.Image;
import com.bonplan.services.DealService;
import com.bonplan.util.Vars;
import com.codename1.capture.Capture;
import com.codename1.components.InfiniteProgress;
import com.codename1.io.MultipartRequest;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.SimpleDateFormat;
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
import java.io.IOException;
import java.util.Date;

/**
 *
 * @author Hamrouni
 */
public class AjouterForm {

    private Toolbar t;
    private Form f;

    public AjouterForm() {
        f = new Form("Ajouter deal", new FlowLayout(Component.TOP, Component.TOP));
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

        TextField nom = new TextField("", "Nom de deal");
        TextField prix = new TextField("", "Prix de deal");
        TextField tred = new TextField("", "Taux de reduction");
        Date dt = new Date();
        Picker p = new Picker();
        p.setDate(dt);
        Picker p1 = new Picker();
        p.setDate(dt);
        TextField score = new TextField("", "Score de deal");
        TextArea desc = new TextArea("Description");
        Button ajouter = new Button("Ajouter");
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
                                        if (Vars.current_deal.getImageId() == null) {
                                            if (Dialog.show("Error", "Un deal doit avoir une image", "ok", null)) {
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
                                            ds.AjouterDeal(Vars.current_enseigne.getId(), Vars.current_deal);
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

            }
        });

        Button btnaj = new Button("Upload");

        btnaj.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                try {
                    String fileNameInServer = "";
                    MultipartRequest cr = new MultipartRequest();
                    String filepath = Capture.capturePhoto(-1, -1);
                    //t7ot l url ta3 l fichier php
                    cr.setUrl("http://127.0.0.1/bonplan/Projet_pidev/web/uploads/images/uploadimage.php");
                    cr.setPost(true);
                    String mime = "image/jpeg";
                    cr.addData("file", filepath, mime);
                    String out = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
                    cr.setFilename("file", out + ".png");//any unique name you want
//filenameinserver héyal variable l jdida ta3 l l'img
                    fileNameInServer += out + ".png";
                    System.err.println("path2 =" + fileNameInServer);
                    //ta3mel l set mta3 l 'image
                    Vars.current_deal = new Deal(1);
                    Image im = new Image(1, out, "png");
                    Vars.current_deal.setImageId(im);
                    InfiniteProgress prog = new InfiniteProgress();
                    Dialog dlg = prog.showInifiniteBlocking();
                    cr.setDisposeOnCompletion(dlg);
                    NetworkManager.getInstance().addToQueueAndWait(cr);

                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
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
        c1.add(btnaj);
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
