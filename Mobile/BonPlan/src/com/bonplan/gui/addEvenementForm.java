/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bonplan.gui;

import com.bonplan.services.EventService;
import com.codename1.capture.Capture;
import com.codename1.components.InfiniteProgress;
import com.codename1.io.MultipartRequest;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import java.io.IOException;
import java.util.Date;

/**
 *
 * @author Hamrouni
 */
public class addEvenementForm extends Form{
    public addEvenementForm() {
         setTitle("new event");
        init();
        getToolbar().addCommandToLeftBar("back",null,(evt) -> {
                new EvenementForm().show();
        });
    }
    private void init(){
        TextField nom = new TextField ("","Nom");
        TextField description= new TextField("","Description");
        
        Button insert = new Button("insert");
       
       
        Button upload = new Button("upload");
        upload.addActionListener((U) -> {
            if (U != null && U.getSource() != null) {
                   
                       
                        
                    try {
                         String fileNameInServer = "";
                        MultipartRequest cr = new MultipartRequest();
                        String filePath = Capture.capturePhoto(-1, -1);
                        System.out.println(filePath);
                      
                       
                        cr.setUrl("http://127.0.0.1/bonplan/Projet_pidev/web/uploads/images/uploadimage.php");
                        cr.setPost(true);
                        String mime = "image/jpeg";
                        cr.addData("file", filePath, mime);
                         String out = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
                         cr.setFilename("file","mobile_"+ out + ".png");
                        
                        fileNameInServer +="mobile_"+ out ;
                        
                        System.err.println("path2 =" + fileNameInServer.toString());
                        //la.setImageUrl("uploads/"+fileNameInServer);
                        InfiniteProgress prog = new InfiniteProgress();
                        Dialog dlg = prog.showInifiniteBlocking();
                        cr.setDisposeOnCompletion(dlg);
                        cr.addResponseListener((a)->{
                            System.out.println(new String (cr.getResponseData()));
                        });
                        NetworkManager.getInstance().addToQueueAndWait(cr);

                    } catch (IOException ex) {
                        System.out.println("exception dans l'upload img");
                    }
                        
                    
                    
                }
        });
         insert.addActionListener((evt) -> {
            if (nom.getText().length()<1){
                return;
            }
            if (description.getText().length()<1){
                return;
            }
            EventService evs = new EventService();
            evs.addEvent(2, description.getText(),nom.getText());
            new EvenementForm().show();
        });
       
        removeAll();
        addComponent(nom);
        addComponent(description);
        addComponent(upload);
        addComponent(insert);
   }
}
