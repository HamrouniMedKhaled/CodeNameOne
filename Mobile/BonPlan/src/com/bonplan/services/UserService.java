/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bonplan.services;

import com.bonplan.entities.FosUser;
import com.bonplan.util.Vars;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import java.io.IOException;
import java.util.Map;

/**
 *
 * @author Hamrouni
 */
public class UserService {

    public void Login(String username, String password) {
        ConnectionRequest con = new ConnectionRequest();
        con.setPost(false);
        con.setUrl("http://127.0.0.1:8000/loginm/");
        con.addArgument("username", username);
        con.addArgument("password", password);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                String json = new String(con.getResponseData());
                JSONParser j = new JSONParser();
                if (json.compareTo("Failed") > 0) {
                    Map<String, Object> u;
                    try {
                        u = j.parseJSON(new CharArrayReader(json.toCharArray()));

                        Vars.current_user = new FosUser((int) Float.parseFloat(u.get("id").toString()));
                        Vars.current_user.setUsername(u.get("username").toString());
                        Vars.current_user.setEmail(u.get("email").toString());
                        Vars.current_user.setScore((int) Float.parseFloat(u.get("score").toString()));

                        System.out.println(Vars.current_user.getId());
                    } catch (IOException ex) {
                        System.out.println(ex);
                    }
                }
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }

    public void Register(FosUser u) {
        ConnectionRequest con = new ConnectionRequest();
        con.setPost(false);
        con.setUrl("http://127.0.0.1:8000/register/registerm/");
        con.addArgument("email", u.getEmail());
        con.addArgument("username", u.getUsername());
        con.addArgument("password", u.getPassword());
        con.addArgument("roles", u.getRoles());

        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                String json = new String(con.getResponseData());
                JSONParser j = new JSONParser();

                if (json.compareTo("Failed") > 0) {
                    Map<String, Object> u;
                    try {
                        u = j.parseJSON(new CharArrayReader(json.toCharArray()));

                        Vars.current_user = new FosUser((int) Float.parseFloat(u.get("id").toString()));
                        Vars.current_user.setUsername(u.get("username").toString());
                        Vars.current_user.setEmail(u.get("email").toString());
                        Vars.current_user.setScore((int) Float.parseFloat(u.get("score").toString()));

                    } catch (IOException ex) {
                        System.out.println("erreur parsing user");
                    }
                }
            }
        });
        NetworkManager.getInstance().addToQueue(con);

    }
}
