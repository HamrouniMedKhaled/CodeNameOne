/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bonplan.gui;

import com.bonplan.services.DealService;
import com.bonplan.services.UserService;
import com.bonplan.util.Vars;
import com.codename1.ui.Button;
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
public class LoginForm {

    private Form f;
    private Toolbar t;
    private Container c1, c2;
    private TextField username, password;
    private Button login, register;

    public LoginForm() {
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
        password = new TextField("", "Password", 20, PASSWORD);
        login = new Button("Connecter");
        register = new Button("Inscription");

        c1 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        c2 = new Container(new BoxLayout(BoxLayout.X_AXIS));

        c1.add(username);
        c1.add(password);
        c2.add(login);
        c2.add(register);
        f.add(c1);
        f.add(c2);

        login.addActionListener((e) -> {
            UserService us = new UserService();
            us.Login(username.getText(), password.getText());
            if (Vars.current_user == null) {
                if (Dialog.show("Error", "Username ou mot de passe incorrect", "ok", null)) {

                    f.showBack();
                }
            } else {
                DealService ds = new DealService();
                ds.actualiser();
                AcceuilForm af = new AcceuilForm();
                af.getF().show();

            }

        });
        register.addActionListener((e) -> {
            RegisterForm rf = new RegisterForm();
            rf.getF().show();

        });

    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }

    public TextField getUsername() {
        return username;
    }

    public TextField getPassword() {
        return password;
    }

}
