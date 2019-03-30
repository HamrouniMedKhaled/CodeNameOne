/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bonplan.services;

import com.bonplan.entities.Enseigne;
import com.bonplan.entities.FosUser;
import com.codename1.components.InfiniteProgress;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionListener;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Hamrouni
 */
public class EnseigneService {

    public Map<String, Object> h = null;

    public Enseigne getOneById(int ide) {
        Enseigne s = new Enseigne();
        Map<String, Object> m = getResponse("/mobile/showoneenseigne/" + ide);
        LinkedHashMap test = (LinkedHashMap) m.get("enseigne");

        Double id = (Double) test.get("id");
        s.setId(id.intValue());
        if (id.intValue() != 0) {
            id = (Double) test.get("id");
            s.setId(id.intValue());

            s.setDescription((String) test.get("desc"));
            EnseigneService enseigneService = new EnseigneService();

            Double capacite = (Double) test.get("capacite");
            System.out.println(capacite);

            s.setCapacite(capacite.intValue());

            s.setNom((String) test.get("nom"));
            System.out.println(s.getId());

            System.out.println(test.get("kkk") + " " + s.getId());

            return s;
        }
        return null;
    }

    public ArrayList<Enseigne> getList() {
        ArrayList<Enseigne> listEnseigne = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        // url = "http://localhost/Projet_pidev/web/app_dev.php"+url;

        con.setUrl("http://127.0.0.1:8000/mobile/showallenseigne");
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                try {
                    String json = new String(con.getResponseData());
                    System.out.println(json);
                    JSONParser j = new JSONParser();
                    Map<String, Object> lostAnimals = j.parseJSON(new CharArrayReader(json.toCharArray()));
                    List<Map<String, Object>> list = (List<Map<String, Object>>) lostAnimals.get("enseignes");

                    // System.out.println(list);
                    for (Map<String, Object> obj : list) {
                        Enseigne En = new Enseigne();

                        // System.out.println(obj.get("id"));
                        float id = Float.parseFloat(obj.get("id").toString());
                        //System.out.println(id);
                        En.setId((int) id);
//                float id_user = Float.parseFloat(obj.get("user_id").toString());

                        // En.setUserId(obj.get("user"));
                        En.setDescription(obj.get("desc").toString());
                        En.setNom(obj.get("nom").toString());
                        if (obj.get("active") == "true") {
                            En.setActive(true);
                        } else {
                            En.setActive(false);
                        }
                        float capacite = Float.parseFloat(obj.get("capacite").toString());
                        En.setCapacite((int) capacite);
                        float categorie = Float.parseFloat(obj.get("categorie").toString());
                        //En.setCategorieId((int) categorie);
                        FosUser us = new FosUser((int)Float.parseFloat(obj.get("user").toString()));
                        En.setUserId(us);
                        listEnseigne.add(En);

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("erreur de parsing");
                }
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        //System.out.println(listLostAnimal.toString());
        return listEnseigne;
    }

    public Map<String, Object> getResponse(String url) {
        url = "http://127.0.0.1:8000" + url;
        System.out.println(url);
        ConnectionRequest r = new ConnectionRequest();
        r.setUrl(url);
        //r.setPost(false);

        InfiniteProgress prog = new InfiniteProgress();
        Dialog dlg = prog.showInifiniteBlocking();
        r.setDisposeOnCompletion(dlg);
        r.addResponseListener((evt) -> {
            try {
                //Logger.getLogger(MyApplication.class.getName()).log(Level.SEVERE, null, ex);

                JSONParser p = new JSONParser();
                Reader targetReader = new InputStreamReader(new ByteArrayInputStream(r.getResponseData()));
                h = p.parseJSON(targetReader);
            } catch (IOException ex) {
                ex.printStackTrace();
            }

        });
        NetworkManager.getInstance().addToQueueAndWait(r);
        return h;
    }

}
