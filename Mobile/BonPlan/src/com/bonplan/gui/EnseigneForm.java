/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bonplan.gui;

import com.bonplan.entities.Enseigne;
import com.bonplan.services.EnseigneService;
import com.bonplan.util.Vars;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;

/**
 *
 * @author Hamrouni
 */
public class EnseigneForm extends Form {

    public EnseigneForm() {
        init();
        setTitle("Enseignes");
       
        getToolbar().addCommandToOverflowMenu("ajouter", null,(evt) -> {
            new addEnseigneForm().show();
        });
        getToolbar().addCommandToSideMenu("Acceuil", null, (u) -> {
            Vars.current_choice = 0;
                AcceuilForm af = new AcceuilForm();
                af.getF().show();
        });
        getToolbar().addCommandToSideMenu("Enseigne", null, (u) -> {
            new EnseigneForm().show();
        });
        getToolbar().addCommandToSideMenu("Menu", null, (u) -> {
            new MenuForm().show();
        });
        getToolbar().addCommandToSideMenu("State", null, (u) -> {
            new StateForm();
        });
    }

    private void init() {
        EnseigneService ens = new EnseigneService();

        for (Enseigne en : ens.getList()) {

            Label nom = new Label(en.getNom());
            Label description = new Label();
            if (en.getDescription().length() > 13) {
                description = new Label(en.getDescription().substring(0, 13) + "...");

            } else {
                description = new Label(en.getDescription());

            }
            Label capacite = new Label(String.valueOf(en.getCapacite()));
            Button deal = new Button("Deal");
            deal.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    Vars.current_enseigne=en;
                   AjouterForm af=new AjouterForm();
                   af.getF().show();
                }
            });
            
            if(en.getUserId().getId()!=Vars.current_user.getId())
            {
                deal.setVisible(false);
            }
            Button res = new Button ("Reservation");
            res.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                     Vars.current_enseigne=en;
                    AjouterResEns are =new AjouterResEns();
                    are.getF().show();
                }
            });
            Button ev = new Button("Events");
            ev.addActionListener((evt) -> {
                new EvenementForm().show();
            });
            Container cx = new Container();
            Container cy = new Container(BoxLayout.y());
            Container cy1 = new Container(BoxLayout.y());

            cy.addComponent(nom);
            cy.addComponent(description);
            cy1.addComponent(capacite);
            cy1.addComponent(deal);
            cy1.addComponent(res);
            cy1.addComponent(ev);

            cx.setLayout(BoxLayout.x());
//            Button ev = new Button("Events");
//            ev.addActionListener((evt) -> {
//                new EvenementForm().show();
//            });
            cx.add(cy);
            cx.add(cy1);
//            cx.add(ev);
            add(cx);

        }

    }
}
