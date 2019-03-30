/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bonplan.services;

import com.bonplan.entities.Categorie;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Hamrouni
 */
public class CategorieService {

    public ArrayList<Categorie> getListCategorie(String json) {

        ArrayList<Categorie> listCategories = new ArrayList<>();

        try {

            JSONParser j = new JSONParser();

            Map<String, Object> categories = j.parseJSON(new CharArrayReader(json.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) categories.get("root");

            for (Map<String, Object> obj : list) {
                Categorie c = new Categorie();
                c.setNom(obj.get("nom").toString());

                listCategories.add(c);

            }

        } catch (IOException ex) {
        }

        return listCategories;

    }
    ArrayList<Categorie> listCategories = new ArrayList<>();

    public ArrayList<Categorie> getCategories() {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://127.0.0.1:8000/Deal/Get_Categories/");
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                CategorieService cs = new CategorieService();
                listCategories = cs.getListCategorie(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listCategories;
    }

}
