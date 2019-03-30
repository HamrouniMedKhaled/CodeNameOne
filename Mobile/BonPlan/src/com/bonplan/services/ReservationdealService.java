/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bonplan.services;

import com.bonplan.entities.Deal;
import com.bonplan.entities.Enseigne;
import com.bonplan.entities.FosUser;
import com.bonplan.entities.Image;
import com.bonplan.entities.Reservationdeal;
import com.bonplan.util.Vars;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.messaging.Message;
import com.codename1.ui.Display;
import com.codename1.ui.events.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Hamrouni
 */
public class ReservationdealService {

    public void Reserver(int x) {
        ConnectionRequest con = new ConnectionRequest();
        con.setPost(false);
        con.setUrl("http://127.0.0.1:8000/Deal/Reserver_Dealm/");
        con.addArgument("deal", String.valueOf(Vars.current_deal.getId()));
        con.addArgument("user", String.valueOf(Vars.current_user.getId()));
        con.addArgument("place", String.valueOf(x));
        NetworkManager.getInstance().addToQueueAndWait(con);

    }

    public void Payer(int x) {

        ConnectionRequest con = new ConnectionRequest();
        con.setPost(false);
        con.setUrl("http://127.0.0.1:8000/Deal/Payer_Dealm/");
        con.addArgument("id", String.valueOf(x));
        con.addArgument("user", String.valueOf(Vars.current_user.getId()));

        NetworkManager.getInstance().addToQueueAndWait(con);
        Message m = new Message("Bonsoir Mr " + Vars.current_reservationdeal.getDealId().getEnseigneId().getUserId().getUsername() + ". Mr " + Vars.current_reservationdeal.getUserId().getUsername() + " a reservé " + Vars.current_reservationdeal.getNbr() + " place dans votre deal " + Vars.current_reservationdeal.getDealId().getNom() + " et il les a payé à l'aide de son score");
        Display.getInstance().sendMessage(new String[]{Vars.current_reservationdeal.getDealId().getEnseigneId().getUserId().getEmail()}, "A propos de votre deal", m);
    }

    public void Annuler(int x) {
        ConnectionRequest con = new ConnectionRequest();
        con.setPost(false);
        con.setUrl("http://127.0.0.1:8000/Deal/Supprimer_Reserverm/");
        con.addArgument("id", String.valueOf(x));
        con.addArgument("user", String.valueOf(Vars.current_user.getId()));

        NetworkManager.getInstance().addToQueueAndWait(con);
    }

    public ArrayList<Reservationdeal> getListReservation(String json) {

        ArrayList<Reservationdeal> listreservations = new ArrayList<>();

        try {
            JSONParser j = new JSONParser();

            Map<String, Object> reservations = j.parseJSON(new CharArrayReader(json.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) reservations.get("root");

            for (Map<String, Object> obj : list) {
                Reservationdeal r = new Reservationdeal();

                r.setId((int) Float.parseFloat(obj.get("rid").toString()));
                Deal d = new Deal((int) Float.parseFloat(obj.get("did").toString()));
                d.setNom(obj.get("dnom").toString());
                d.setPrix((int) Float.parseFloat(obj.get("dprix").toString()));
                d.setTred((int) Float.parseFloat(obj.get("dtred").toString()));
                d.setScore((int) Float.parseFloat(obj.get("dscore").toString()));
                Image img = new Image((int) Float.parseFloat(obj.get("dimage_id").toString()), obj.get("dimage_url").toString(), "png");
                d.setImageId(img);
                Enseigne ens = new Enseigne((int) Float.parseFloat(obj.get("denseigne_id").toString()));
                ens.setCapacite((int) Float.parseFloat(obj.get("denseigne_capacite").toString()));
                FosUser user = new FosUser((int) Float.parseFloat(obj.get("duser_id").toString()));
                user.setUsername(obj.get("duser_name").toString());
                user.setEmail(obj.get("duser_mail").toString());
                ens.setUserId(user);
                d.setEnseigneId(ens);
                r.setDealId(d);
                r.setPayer(Boolean.valueOf(obj.get("rid").toString()));
                Vars.current_user.setScore((int) Float.parseFloat(obj.get("uscore").toString()));
                r.setUserId(Vars.current_user);
                r.setNbr((int) Float.parseFloat(obj.get("rplace").toString()));
                listreservations.add(r);
            }

        } catch (IOException ex) {
        }

        return listreservations;

    }
    ArrayList<Reservationdeal> listReservations = new ArrayList<>();

    public ArrayList<Reservationdeal> getReservations() {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://127.0.0.1:8000/Deal/Afficher_Reserverm/");
        con.addArgument("user", String.valueOf(Vars.current_user.getId()));
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ReservationdealService rds = new ReservationdealService();
                listReservations = rds.getListReservation(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listReservations;
    }
}
