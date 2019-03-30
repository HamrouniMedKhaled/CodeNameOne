/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bonplan.services;

import com.bonplan.entities.Reservation;
import com.bonplan.util.Vars;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.events.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author yassine
 */
public class ReservationService {

    public void Reserver(int x, Date d) {
        ConnectionRequest con = new ConnectionRequest();
        con.setPost(false);
        con.setUrl("http://127.0.0.1:8000/reservation/Createm/");
        con.addArgument("id", String.valueOf(Vars.current_enseigne.getId()));
        con.addArgument("user", String.valueOf(Vars.current_user.getId()));
        con.addArgument("date", new SimpleDateFormat("dd-MM-yyyy").format(d));
        con.addArgument("nbr", String.valueOf(x));

        NetworkManager.getInstance().addToQueueAndWait(con);

    }

    public void update(int x, Date d) {
        ConnectionRequest con = new ConnectionRequest();
        con.setPost(false);
        con.setUrl("http://127.0.0.1:8000/reservation/Updatem/");
        con.addArgument("id", String.valueOf(Vars.current_reservation.getId()));
        con.addArgument("date", new SimpleDateFormat("dd-MM-yyyy").format(d));
        con.addArgument("nbr", String.valueOf(x));

        NetworkManager.getInstance().addToQueueAndWait(con);
    }

    public void delete(int x) {
        ConnectionRequest con = new ConnectionRequest();
        con.setPost(false);
        con.setUrl("http://127.0.0.1:8000/reservation/Deletem/");
        con.addArgument("id", String.valueOf(x));

        NetworkManager.getInstance().addToQueueAndWait(con);
    }

    public ArrayList<Reservation> getListReservation(String json) {

        ArrayList<Reservation> listreservations = new ArrayList<>();

        try {
            JSONParser j = new JSONParser();

            Map<String, Object> reservations = j.parseJSON(new CharArrayReader(json.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) reservations.get("root");

            for (Map<String, Object> obj : list) {
                Reservation r = new Reservation();

                r.setId((int) Float.parseFloat(obj.get("id").toString()));
                String s = obj.get("date").toString();
                r.setNbplaces((int) Float.parseFloat(obj.get("nbr").toString()));

                int position = s.indexOf("timestamp");
                int pos1 = s.length();
                String sousChaine = s.substring(position + 10, pos1 - 1);
                double d = Double.parseDouble(sousChaine);
                long batch_date = (long) d;
                Date dt = new Date(batch_date * 1000);
                SimpleDateFormat sfd = new SimpleDateFormat("dd-MM-yyyy");
                String g = sfd.format(dt);
                try {
                    r.setDatereservation(sfd.parse(g));
                } catch (ParseException ex) {
                }

                listreservations.add(r);
            }

        } catch (IOException ex) {
        }

        return listreservations;

    }
    ArrayList<Reservation> listReservations = new ArrayList<>();

    public ArrayList<Reservation> getReservations() {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://127.0.0.1:8000/reservation/Affichem");

        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ReservationService rs = new ReservationService();
                listReservations = rs.getListReservation(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listReservations;
    }

}
