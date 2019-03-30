/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bonplan.gui;

import com.bonplan.services.MenuService;
import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;

/**
 *
 * @author Hamrouni
 */
public class addMenuForm extends Form {

    public addMenuForm() {
        setTitle("new menu");
        getToolbar().addCommandToLeftBar("back", null, (evt) -> {
            new MenuForm().show();
        });
        init();
    }

    private void init() {
        TextField nom = new TextField("", "Nom");
        TextField price = new TextField("", "price");

        Button insert = new Button("insert");
        insert.addActionListener((evt) -> {
            MenuService ms = new MenuService();
            if (nom.getText().length() < 1) {
                return;
            }
            if (price.getText().length() < 1) {
                return;
            }
            float p = Float.parseFloat(price.getText());

            ms.addMenu(2, nom.getText(), (int) p);
            new MenuForm().show();
        });
        addComponent(nom);
        addComponent(price);
        addComponent(insert);
    }
}
