/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bonplan.gui;

import com.bonplan.entities.Evenement;
import com.bonplan.services.EventService;
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
public class EvenementForm extends Form {

    public EvenementForm() {
        setTitle("Evenements");
        init();
        getToolbar().addCommandToLeftBar("Retour", null, (evt) -> {
            new EnseigneForm().show();
        });
        getToolbar().addCommandToOverflowMenu("nouveau", null, (evt) -> {
            new addEvenementForm().show();
        });
        getToolbar().addCommandToSideMenu("State", null, (u) -> {
            new StateForm();
        });
    }

    private void init() {
        //TODO parcour des evenements
        EventService evs = new EventService();
        for (Evenement evenement : evs.getAll()) {
            Label nom = new Label(evenement.getNom());
            Label description = new Label();
            if (evenement.getDescription().length() > 13) {
                description = new Label(evenement.getDescription().substring(0, 13) + "...");

            } else {
                description = new Label(evenement.getDescription());

            }
            Container cx = new Container(BoxLayout.x());
            Container cy = new Container(BoxLayout.y());
            Container cf = new Container(BoxLayout.y());

            Button ev = new Button("Details");
            ev.addActionListener((evt) -> {
                new EvenementShowForm(evenement).show();
            });
            Button res = new Button ("Reservation");
            res.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    Vars.current_evenement=evenement;
               AjouterResEvent are = new AjouterResEvent();
               are.getF().show();
                }
            });
            cy.addComponent(nom);
            cy.addComponent(description);

            cx.addComponent(cy);
            cf.addComponent(ev);
            cf.add(res);
            cx.add(cf);
            add(cx);
        }
    }

}
