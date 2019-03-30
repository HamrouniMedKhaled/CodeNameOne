/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bonplan.gui;

import com.bonplan.entities.FosUser;
import com.bonplan.services.DealService;
import com.bonplan.services.UserService;
import com.bonplan.util.Vars;
import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import static com.codename1.ui.TextArea.PASSWORD;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;

/**
 *
 * @author Hamrouni
 */
public class RegisterForm {

    private TextField username, password, password1, mail;
    private final Button register;
    private Form f;
    private Container c1;
    private Toolbar t;

    public RegisterForm() {
        f = new Form("Connexion", new FlowLayout(Component.CENTER, Component.CENTER));
        t = f.getToolbar();
        t.addMaterialCommandToSideMenu("Connexion", FontImage.MATERIAL_WEB, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                LoginForm lf = new LoginForm();
                lf.getF().show();
            }
        });
        t.addMaterialCommandToSideMenu("Inscription", FontImage.MATERIAL_WEB, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                RegisterForm rf = new RegisterForm();
                rf.getF().show();
            }
        });
        username = new TextField("", "votre nom");
        mail = new TextField("", "votre.mail@mail.com");
        password = new TextField("", "Password", 20, PASSWORD);
        password1 = new TextField("", "Password", 20, PASSWORD);
        CheckBox roles = new CheckBox("Responsable");

        register = new Button("inscription");

        c1 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        register.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {

                if ((username.getText().length() <= 0)) {
                    if (Dialog.show("Error", "Username invalide", "ok", null)) {
                        f.showBack();
                    }
                } else {
                    String get = mail.getText().toString();
                    char a = '@';

                    int count = 0;
                    int atposition = 0, dotposition = 0, flag = 0;

                    for (int i = 0; i < get.length(); i++) {
                        if (get.charAt(i) == a) {
                            count++;
                            atposition = i;
                            if (count >= 2) {
                                flag = 1;
                                break;
                            }
                        }

                        if (get.charAt(i) == '.') {
                            dotposition = i;
                        }
                    }
                    if (atposition < 1 || dotposition < atposition + 2 || dotposition + 2 >= get.length() || flag == 1) {
                        if (Dialog.show("Error", "E-Mail invalide", "ok", null)) {
                            f.showBack();
                        }
                    } else {
                        if ((password.getText().length() <= 0) || (!(password.getText().equals(password1.getText())))) {
                            if (Dialog.show("Error", "Mot de passe incorrect", "ok", null)) {
                                f.showBack();
                            }
                        } else {
                            FosUser user = new FosUser(1);
                            user.setEmail(mail.getText());
                            user.setUsername(username.getText());
                            user.setPassword(password.getText());
                            if (roles.isSelected()) {
                                user.setRoles("ROLE_OWNER");
                            } else {
                                user.setRoles("ROLE_USER");
                            }
                            UserService us = new UserService();
                            us.Register(user);
                            if (Vars.current_user == null) {
                                if (Dialog.show("Error", "Username ou mail existant déja", "ok", null)) {
                                    f.showBack();
                                }
                            } else {
                                DealService ds = new DealService();
                                ds.actualiser();
                                AcceuilForm af = new AcceuilForm();
                                af.getF().show();
                            }
                        }
                    }

                }

            }
        });

        c1.add(username);
        c1.add(mail);
        c1.add(password);
        c1.add(password1);
        c1.add(roles);
        c1.add(register);

        f.add(c1);

    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }

}
