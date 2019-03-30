/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bonplan.gui;

import com.bonplan.entities.Menu;
import com.bonplan.services.MenuService;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;

/**
 *
 * @author Hamrouni
 */
public class MenuForm extends Form {

    public MenuForm() {
        setTitle("Menus");
        init();
        getToolbar().addCommandToSideMenu("Enseigne", null, (u) -> {
            new EnseigneForm().show();
        });
        getToolbar().addCommandToSideMenu("Menu", null, (u) -> {
            new MenuForm().show();
        });
        getToolbar().addCommandToOverflowMenu("nouveau", null, (evt) -> {
            new addMenuForm().show();
        });
        getToolbar().addCommandToSideMenu("State", null, (u) -> {
            new StateForm();
        });
    }

    private void init() {
        MenuService ms = new MenuService();
        for (Menu m : ms.getAll()) {
            Label nom = new Label(m.getContenu(), BorderLayout.WEST);
            Label price = new Label(String.valueOf(m.getPrix()), BorderLayout.EAST);
            Container cx = new Container(BoxLayout.x());

            cx.addComponent(nom);
            cx.addComponent(price);

            add(cx);
        }
    }

}
