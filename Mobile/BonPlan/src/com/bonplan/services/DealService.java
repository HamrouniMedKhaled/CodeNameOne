/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bonplan.services;

import com.bonplan.entities.Categorie;
import com.bonplan.entities.Deal;
import com.bonplan.entities.Enseigne;
import com.bonplan.entities.Image;
import com.bonplan.util.Vars;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.events.ActionListener;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.List;

/**
 *
 * @author Hamrouni
 */
public class DealService {

    public void AjouterDeal(int x, Deal d) {
        ConnectionRequest con = new ConnectionRequest();
        con.setPost(false);
        con.setUrl("http://127.0.0.1:8000/Deal/MesDeals/Ajouterm/");
        con.addArgument("ens", String.valueOf(x));
        con.addArgument("nom", d.getNom());
        con.addArgument("score", String.valueOf(d.getScore()));
        con.addArgument("datefin", new SimpleDateFormat("dd-MM-yyyy").format(d.getDatefin()));
        con.addArgument("datedebut", new SimpleDateFormat("dd-MM-yyyy").format(d.getDatefin()));
        con.addArgument("description", d.getDescription());
        con.addArgument("prix", String.valueOf(d.getPrix()));
        con.addArgument("tred", String.valueOf(d.getTred()));
        con.addArgument("imageurl", d.getImageId().getUrl());

        NetworkManager.getInstance().addToQueueAndWait(con);

    }

    public Deal affichedeal(int x) {
        ConnectionRequest con = new ConnectionRequest();
        con.setPost(false);
        con.setUrl("http://127.0.0.1:8000/Deal/Afficher_Dealm/");
        con.addArgument("id", String.valueOf(x));

        Deal deal = new Deal();
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                String json = new String(con.getResponseData());
                JSONParser j = new JSONParser();

                Map<String, Object> d;
                try {
                    d = j.parseJSON(new CharArrayReader(json.toCharArray()));

                    deal.setId((int) Float.parseFloat(d.get("id").toString()));
                    deal.setNom(d.get("nom").toString());
                    deal.setScore((int) Float.parseFloat(d.get("score").toString()));
                    deal.setPrix(Float.parseFloat(d.get("prix").toString()));
                    deal.setTred((int) Float.parseFloat(d.get("tred").toString()));
                    deal.setVisite((int) Float.parseFloat(d.get("visite").toString()));
                    deal.setDescription(d.get("description").toString());
                    Categorie cat = new Categorie(1);
                    cat.setNom(d.get("categorie").toString());
                    Enseigne ens = new Enseigne((int) Float.parseFloat(d.get("enseigne_id").toString()));
                    ens.setCapacite((int) Float.parseFloat(d.get("enseigne_capacite").toString()));
                    ens.setCategorieId(cat);
                    deal.setEnseigneId(ens);
                    Image img = new Image((int) Float.parseFloat(d.get("image_id").toString()), d.get("image_url").toString(), "png");
                    deal.setImageId(img);

                } catch (IOException ex) {
                    System.out.println(ex);
                }

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);

        return deal;
    }

    public void SupprimerDeal(int x) {
        ConnectionRequest con = new ConnectionRequest();
        con.setPost(false);
        con.setUrl("http://127.0.0.1:8000/Deal/MesDeals/Supprimer_Dealm/");
        con.addArgument("id", String.valueOf(x));
        con.addArgument("user", String.valueOf(Vars.current_user.getId()));

        NetworkManager.getInstance().addToQueueAndWait(con);

    }

    public void ModifierDeal(int x) {
        ConnectionRequest con = new ConnectionRequest();
        con.setPost(false);
        con.setUrl("http://127.0.0.1:8000/Deal/MesDeals/Modifier_Dealm/");
        con.addArgument("id", Integer.toString(x));
        con.addArgument("nom", Vars.current_deal.getNom());
        con.addArgument("score", String.valueOf(Vars.current_deal.getScore()));
        con.addArgument("datefin", new SimpleDateFormat("dd-MM-yyyy").format(Vars.current_deal.getDatefin()));
        con.addArgument("datedebut", new SimpleDateFormat("dd-MM-yyyy").format(Vars.current_deal.getDatefin()));
        con.addArgument("description", Vars.current_deal.getDescription());
        con.addArgument("prix", String.valueOf(Vars.current_deal.getPrix()));
        con.addArgument("tred", String.valueOf(Vars.current_deal.getTred()));
        con.addArgument("user", String.valueOf(Vars.current_user.getId()));

        NetworkManager.getInstance().addToQueueAndWait(con);

    }

    public ArrayList<Deal> getListDeal(String json) {

        ArrayList<Deal> listdeals = new ArrayList<>();

        try {

            JSONParser j = new JSONParser();

            Map<String, Object> deals = j.parseJSON(new CharArrayReader(json.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) deals.get("root");

            for (Map<String, Object> obj : list) {
                Deal d = new Deal();

                d.setId((int) Float.parseFloat(obj.get("id").toString()));
                d.setNom(obj.get("nom").toString());
                d.setScore((int) Float.parseFloat(obj.get("score").toString()));
                d.setPrix(Float.parseFloat(obj.get("prix").toString()));
                d.setTred((int) Float.parseFloat(obj.get("tred").toString()));
                d.setVisite((int) Float.parseFloat(obj.get("visite").toString()));
                d.setDescription(obj.get("description").toString());
                Enseigne ens = new Enseigne((int) Float.parseFloat(obj.get("enseigne_id").toString()));
                ens.setCapacite((int) Float.parseFloat(obj.get("enseigne_capacite").toString()));
                d.setEnseigneId(ens);
                Image img = new Image((int) Float.parseFloat(obj.get("image_id").toString()), obj.get("image_url").toString(), "png");
                d.setImageId(img);
                listdeals.add(d);

            }

        } catch (IOException ex) {
        }

        return listdeals;

    }
    ArrayList<Deal> listDeals = new ArrayList<>();

    public ArrayList<Deal> getDeals() {
        ConnectionRequest con = new ConnectionRequest();
        if (Vars.current_choice == 1) {
            con.setUrl("http://127.0.0.1:8000/Deal/Afficher_Dealsm/");
            con.addArgument("user", String.valueOf(Vars.current_user.getId()));
        } else {
            if (Vars.current_choice == 2) {
                con.setUrl("http://127.0.0.1:8000/Deal/MesDeals/Afficherm/");
                con.addArgument("user", String.valueOf(Vars.current_user.getId()));
            } else {
                if (Vars.current_choice == 3) {
                    con.setUrl("http://127.0.0.1:8000/Deal/Recherche_Score_Dealm/");
                    con.addArgument("user", String.valueOf(Vars.current_user.getId()));
                } else {
                    if (Vars.current_choice == 4) {
                        con.setUrl("http://127.0.0.1:8000/Deal/Recherche_Categorie_Dealm/");
                        con.addArgument("categorie", Vars.current_type);
                        con.addArgument("id", String.valueOf(Vars.current_user.getId()));

                    } else {
                        con.setUrl("http://127.0.0.1:8000/Deal/Recherche_Ville_Dealm/");
                        con.addArgument("ville", Vars.current_type);
                        con.addArgument("id", String.valueOf(Vars.current_user.getId()));
                    }
                }
            }
        }
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                DealService ds = new DealService();
                listDeals = ds.getListDeal(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listDeals;
    }

    public void actualiser() {

        ConnectionRequest con = new ConnectionRequest();
        con.setPost(false);
        con.setUrl("http://127.0.0.1:8000/Deal/MesDeals/Supprimer_Dealmm/");
        con.addArgument("dd", String.valueOf(new SimpleDateFormat("dd-MM-yyyy").format(new Date())));
    }

}
