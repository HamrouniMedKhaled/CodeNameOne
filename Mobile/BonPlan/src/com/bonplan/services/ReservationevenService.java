/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bonplan.services;

import com.bonplan.entities.Evenement;
import com.bonplan.entities.Reservationevent;
import com.bonplan.util.Vars;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.events.ActionListener;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.types.FacebookType;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author yassine
 */
public class ReservationevenService {

    public void Reserver(int x) {
        ConnectionRequest con = new ConnectionRequest();
        con.setPost(false);
        con.setUrl("http://127.0.0.1:8000/reservation/CreateReservationEvent/");
        con.addArgument("id", String.valueOf(Vars.current_evenement.getId()));
        con.addArgument("user", String.valueOf(Vars.current_user.getId()));
        con.addArgument("nbr", String.valueOf(x));

        NetworkManager.getInstance().addToQueueAndWait(con);

        String accessToken = "EAACEdEose0cBAFOgNqGqCeCAzdicjpBNIqwjMpIBH6vLZCjJjnRiut0h72wMCl2JCrfuGuenm5c2QhEshGqbd5PrLzOYKUiil2RAaaUsZCilYvBjzF65gU3ZCtcK8ji5uQvAMe7eH2BXm6eWO3GYGeBTwC1ZCaKY3YWFLTrZAlAXdQ6JSpiNRBlZA9YROUlJfC5ZAxpqXZAkSwZDZD";
        FacebookClient fbClient = new DefaultFacebookClient(accessToken);
        FacebookType response = fbClient.publish("me/feed", FacebookType.class, Parameter.with("voila une nouvelle Reservation via ", Vars.current_user.getUsername()), Parameter.with("link", "http://127.0.0.1:8000/"));
        System.out.println("fb.com/" + response.getId());
    }

    public void update(int x) {
        ConnectionRequest con = new ConnectionRequest();
        con.setPost(false);
        con.setUrl("http://127.0.0.1:8000/reservation/UpdateReservationEventm/");
        con.addArgument("id", String.valueOf(Vars.current_reservationevent.getId()));
        con.addArgument("nbr", String.valueOf(x));

        NetworkManager.getInstance().addToQueueAndWait(con);
    }

    public void delete(int x) {
        ConnectionRequest con = new ConnectionRequest();
        con.setPost(false);
        con.setUrl("http://127.0.0.1:8000/reservation/DeleteReservationEvent/");
        con.addArgument("id", String.valueOf(x));

        NetworkManager.getInstance().addToQueueAndWait(con);
    }

    public ArrayList<Reservationevent> getListReservation(String json) {

        ArrayList<Reservationevent> listreservations = new ArrayList<>();

        try {
            JSONParser j = new JSONParser();

            Map<String, Object> reservations = j.parseJSON(new CharArrayReader(json.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) reservations.get("root");

            for (Map<String, Object> obj : list) {
                Reservationevent r = new Reservationevent();

                r.setId((int) Float.parseFloat(obj.get("id").toString()));
                String s = obj.get("date").toString();
                r.setNombrplaces((int) Float.parseFloat(obj.get("nbr").toString()));
                Evenement even = new Evenement(1);
                even.setNom(obj.get("nomev").toString());
                int position = s.indexOf("timestamp");
                int pos1 = s.length();
                String sousChaine = s.substring(position + 10, pos1 - 1);
                double d = Double.parseDouble(sousChaine);
                long batch_date = (long) d;
                Date dt = new Date(batch_date * 1000);
                SimpleDateFormat sfd = new SimpleDateFormat("dd-MM-yyyy");
                String g = sfd.format(dt);
                try {
                    even.setDate(sfd.parse(g));
                } catch (ParseException ex) {
                }
                r.setEvenementId(even);

                listreservations.add(r);
            }

        } catch (IOException ex) {
        }

        return listreservations;

    }
    ArrayList<Reservationevent> listReservations = new ArrayList<>();

    public ArrayList<Reservationevent> getReservations() {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://127.0.0.1:8000/reservation/AfficheReservationEventm");

        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ReservationevenService rs = new ReservationevenService();
                listReservations = rs.getListReservation(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listReservations;
    }

}
