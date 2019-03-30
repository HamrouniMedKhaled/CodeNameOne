/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bonplan.gui;

import com.bonplan.entities.Comment;
import com.bonplan.services.CommentService;
import com.bonplan.util.Vars;
import com.codename1.components.SpanLabel;
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

/**
 *
 * @author morrta
 */
public class commentForm {

    Form f;
    TextField content;

    Button btnajt;
    private Toolbar t;
    SpanLabel lb;

    public commentForm() {

        f = new Form("Commentaires", new FlowLayout(Component.CENTER, Component.TOP));
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

        content = new TextField("", "Votre commentaire");
        btnajt = new Button("Commenter");

        CommentService serviceComment = new CommentService();
        Container cc = new Container(new BoxLayout(BoxLayout.Y_AXIS));

        for (Comment comment : serviceComment.getList2(Vars.current_deal.getId())) {

            Container list = new Container(new BoxLayout(BoxLayout.X_AXIS));

            SpanLabel content = new SpanLabel(comment.getContent());

            Label username = new Label(comment.getUserId().getUsername() + " :");

            username.getAllStyles().setFgColor(0x1f2a7e);
            list.add(username);

            Button b = new Button("X");
            b.getAllStyles().setFgColor(0x1f2a7e);

            list.add(content);

            if (Vars.current_user.getUsername().equals(comment.getUserId().getUsername())) {
                list.add(b);

            }
            b.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    CommentService ser = new CommentService();
                    ser.deleteCOmment(comment.getUserId().getId(), comment.getId());

                    commentForm a = new commentForm();
                    a.getF().show();
                }
            });

            cc.add(list);
        }
        

        

        btnajt.addActionListener((e) -> {
            CommentService ser = new CommentService();

            if (content.getText().length() != 0) {
                Comment c = new Comment(content.getText(), Vars.current_user, Vars.current_deal);
                ser.addComment(c, Vars.current_deal.getId(), Vars.current_user.getId());
                commentForm a = new commentForm();
                a.getF().show();
            } else {
                if (Dialog.show("Alert", "Commentaire vide", "ok", null)) {
                    f.showBack();
                }
            }

        });
        Container c = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        c.add(cc);    
        f.add(content);
        f.add(btnajt);
        f.add(c);
    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }

    public TextField getTnom() {
        return content;
    }

    public void setTnom(TextField tnom) {
        this.content = tnom;
    }

}
