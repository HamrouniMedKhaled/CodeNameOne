/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bonplan.services;

import com.bonplan.entities.Enseigne;
import com.bonplan.entities.Evenement;
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
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 * @author Hamrouni
 */
public class EventService {

    public Map<String, Object> h = null;

    public void addEvent(int idenseigne, String desc, String nom) {
        getResponse("/mobile/addevent/" + desc + "/" + idenseigne + "/" + nom);

    }

    public void editEvent(int id, int idenseigne, String desc, Date date, String nom) {
        getResponse("/mobile/editevent/" + id + "/" + desc + "/" + idenseigne + "/" + date + "/" + nom);

    }

    public void delEvent(int id) {
        getResponse("/mobile/delevent/" + id);

    }

    public ArrayList<Evenement> getAll() {
        ArrayList<Evenement> evenements = new ArrayList<Evenement>();
        Map<String, Object> m = getResponse("/mobile/showallevent");
        ArrayList a = (ArrayList) m.get("events");
        EnseigneService enseigneService = new EnseigneService();
        for (int i = 0; i < a.size(); i++) {
            Map test = (Map) a.get(i);
            Evenement s = new Evenement();
            Double id = (Double) test.get("id");
            s.setId(id.intValue());

            s.setDescription((String) test.get("desc"));

            Double iden = (Double) test.get("enseigne");
            s.setId(iden.intValue());

            Enseigne enseigne = new Enseigne();
            enseigne = enseigneService.getOneById(iden.intValue());
            s.setEnseigneId(enseigne);

            //s.setEnseigneId( test.get("idenseigne"));
            //serviceEnseigne
            Double idImg = (Double) test.get("img_url");
            //Image img=
            //serviceImage

            s.setNom((String) test.get("nom"));

            System.out.println(s.getId());

            System.out.println(s);

            evenements.add(s);

        }
        System.out.println("eventsssssss " + evenements.toString());
        return evenements;
    }

    public Evenement getOneById(int ide) {
        Evenement s = new Evenement();
        Map<String, Object> m = getResponse("/mobile/showoneevent/" + ide);
        LinkedHashMap test = (LinkedHashMap) m.get("event");

        Double id = (Double) test.get("id");
        s.setId(id.intValue());
        if (id.intValue() != 0) {
            id = (Double) test.get("id");
            s.setId(id.intValue());

            s.setDescription((String) test.get("desc"));
            EnseigneService enseigneService = new EnseigneService();

            Double iden = (Double) test.get("enseigne");
            s.setId(iden.intValue());

            Enseigne enseigne = new Enseigne();
            enseigne = enseigneService.getOneById(iden.intValue());
            s.setEnseigneId(enseigne);
            //serviceEnseigne
            Double idImg = (Double) test.get("img_url");
            //Image img=
            //serviceImage
//            s.setDate((Date) test.get("date"));
            s.setNom((String) test.get("nom"));
            System.out.println(s.getId());

            System.out.println(test.get("kkk") + " " + s.getId());

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
