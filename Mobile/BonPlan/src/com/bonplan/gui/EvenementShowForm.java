/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bonplan.gui;

import com.bonplan.entities.Evenement;
import com.codename1.components.ImageViewer;
import com.codename1.components.ShareButton;
import com.codename1.io.FileSystemStorage;
import com.codename1.io.Log;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.ImageIO;
import java.io.IOException;
import java.io.OutputStream;

/**
 *
 * @author Hamrouni
 */
public class EvenementShowForm extends Form {

    Evenement e;

    public EvenementShowForm(Evenement evenement) {
        e = evenement;
        setTitle("details");
        getToolbar().addCommandToLeftBar("Retour", null, (evt) -> {
            new EvenementForm().show();
        });

        init();
    }

    private void init() {
        Label nom = new Label(e.getNom());
        Label description = new Label(e.getDescription());
        ImageViewer imgv = new ImageViewer();
        try {
            imgv = new ImageViewer(Image.createImage("http://127.0.0.1/bonplan/Projet_pidev/web/uploads/images/"+e.getImageId().getUrl()+".png"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        Container cy = new Container(BoxLayout.y());
        ShareButton sb = new ShareButton();
        sb.setText("Share Screenshot");

        Image screenshot = Image.createImage(getWidth(), getHeight());
        revalidate();
        setVisible(true);
        paintComponent(screenshot.getGraphics(), true);

        String imageFile = FileSystemStorage.getInstance().getAppHomePath() + "screenshot.png";
        try (OutputStream os = FileSystemStorage.getInstance().openOutputStream(imageFile)) {
            ImageIO.getImageIO().save(screenshot, os, ImageIO.FORMAT_PNG, 1);
        } catch (IOException err) {
            Log.e(err);
        }
        sb.setImageToShare(imageFile, "image/png");
        cy.addComponent(nom);
        cy.addComponent(description);
        cy.addComponent(sb);
        removeAll();
        addComponent(cy);
        addComponent(imgv);
    }

}
