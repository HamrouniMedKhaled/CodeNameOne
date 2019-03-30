/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bonplan.gui;

import com.bonplan.services.EnseigneService;
import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;

/**
 *
 * @author Hamrouni
 */
public class addEnseigneForm extends Form {
    public addEnseigneForm() {
        setTitle("add enseigne");
        init();
        getToolbar().addCommandToLeftBar("back",null,(evt) -> {
                new EnseigneForm().show();
        });
    }
    private void init(){
        TextField nom = new TextField ("","Nom");
        TextField capacite= new TextField("","capacité");
        Button insert = new Button("insert");
        insert.addActionListener((evt) -> {
            if (nom.getText().length()<1){
                return;
            }
            if (capacite.getText().length()<1){
                return;
            }
            float cap = Float.parseFloat(capacite.getText());
            EnseigneService ens = new EnseigneService();
            //ens.
            
            new EnseigneForm().show();
        });
        addComponent(nom);
        addComponent(capacite);
        addComponent(insert);
   }
}
