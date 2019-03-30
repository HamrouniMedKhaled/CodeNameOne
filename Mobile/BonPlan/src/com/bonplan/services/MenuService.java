/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bonplan.services;

import com.bonplan.entities.Menu;
import com.codename1.components.InfiniteProgress;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Dialog;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 * @author Hamrouni
 */
public class MenuService {

    public Map<String, Object> h = null;

    public void addMenu(int idenseigne, String contenu, double prix) {
        getResponse("/mobile/addmenu/" + contenu + "/" + idenseigne + "/" + prix);

    }

    public void editMenu(int id, int idenseigne, String contenu, double prix) {
        getResponse("/mobile/editmenu/" + id + "/" + contenu + "/" + idenseigne + "/" + prix);

    }

    public void delMenu(int id) {
        getResponse("/mobile/delmenu/" + id);

    }

    public ArrayList<Menu> getAll() {
        ArrayList<Menu> menus = new ArrayList<Menu>();
        Map<String, Object> m = getResponse("/mobile/showallmenu");
        ArrayList a = (ArrayList) m.get("menus");

        for (int i = 0; i < a.size(); i++) {
            Map test = (Map) a.get(i);
            Menu s = new Menu();
            Double id = (Double) test.get("id");
            s.setId(id.intValue());

            s.setContenu((String) test.get("contenu"));

            //s.setEnseigneId( test.get("idenseigne"));
            Double prix = (Double) test.get("prix");

            s.setPrix(prix);

            System.out.println(s.getId());

            System.out.println(s);

            menus.add(s);

        }
        System.out.println(menus.toString());
        return menus;
    }

    public Menu getOneById(int ide) {
        Menu s = new Menu();
        Map<String, Object> m = getResponse("/mobile/showonemenu/" + ide);
        LinkedHashMap test = (LinkedHashMap) m.get("menu");

        Double id = (Double) test.get("id");
        s.setId(id.intValue());
        if (id.intValue() != 0) {
            id = (Double) test.get("id");
            s.setId(id.intValue());

            s.setContenu((String) test.get("contenu"));

            //s.setEnseigneId( test.get("idenseigne"));
            Double prix = (Double) test.get("prix");

            s.setPrix(prix);
            System.out.println(s.getId());

            System.out.println(test.get("nom_cabinet") + " " + s.getId());

            return s;
        }
        return null;
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
