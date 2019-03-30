/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bonplan.services;

import com.bonplan.entities.Badge;
import com.bonplan.entities.Comment;
import com.bonplan.entities.FosUser;
import com.bonplan.util.Vars;
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
 * @author morrta
 */
public class CommentService {
     public ArrayList<Comment> getListTask(String json) {

        ArrayList<Comment> listEtudiants = new ArrayList<>();

        try {
            
            JSONParser j = new JSONParser();

            Map<String, Object> etudiants = j.parseJSON(new CharArrayReader(json.toCharArray()));
            
           
            List<Map<String, Object>> list = (List<Map<String, Object>>) etudiants.get("root");

            for (Map<String, Object> obj : list) {
                Comment e = new Comment();

                
                float id = Float.parseFloat(obj.get("id").toString());
            
                e.setId((int) id);
                
                
                
                e.setContent(obj.get("content").toString());
                FosUser u = new FosUser(1);
                u.setUsername(obj.get("user").toString());
                e.setUserId(u);
                
            
                listEtudiants.add(e);

            }

        } catch (IOException ex) {
        }
        
        return listEtudiants;

    }
    public ArrayList<Badge> getListBadge(String json) {

        ArrayList<Badge> listEtudiants = new ArrayList<>();

        try {
        
            JSONParser j = new JSONParser();

            Map<String, Object> etudiants = j.parseJSON(new CharArrayReader(json.toCharArray()));
            
           
            List<Map<String, Object>> list = (List<Map<String, Object>>) etudiants.get("root");

            for (Map<String, Object> obj : list) {
                Badge e = new Badge();

        
                float id = Float.parseFloat(obj.get("id").toString());
        
                e.setId((int) id);
                
                
        
                e.setName(obj.get("name").toString());
                
        
        
                listEtudiants.add(e);

            }

        } catch (IOException ex) {
        }
        
        return listEtudiants;

    }
    public ArrayList<Badge> listBadges = new ArrayList<>();
     
    
    ArrayList<Comment> listComments = new ArrayList<>();
    
    public ArrayList<Comment> getList2(int id){ 
        
       
        ConnectionRequest con = new ConnectionRequest();
        con.setPost(false);
        con.setUrl("http://127.0.0.1:8000/comment/getAll");
        con.addArgument("bid", String.valueOf(id));
        

        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                CommentService ser = new CommentService();
                listComments = ser.getListTask(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listComments;
    }
    
    public void addComment(Comment c , int bid , int uid) {
        
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://127.0.0.1:8000/comment/commentMobile";
        Url=Url+"?content=" + c.getContent()+ "&uid=" + uid+"&bid="+bid;
        con.setUrl(Url);
        
        
        
        
        



        con.addResponseListener((e) -> {

            CommentService ser = new CommentService();
            
            Vars.thisUserBadges = ser.getListBadge(new String(con.getResponseData()));        
            
            
            for (Badge item : Vars.thisUserBadges) {
            
                System.out.println(item);
            
            }
            
           
        });
        NetworkManager.getInstance().addToQueueAndWait(con);



    }
    
    public ArrayList<Badge> getBadgeForUser(String json) {

        ArrayList<Badge> listBadges = new ArrayList<>();

        try {
            
            JSONParser j = new JSONParser();

            Map<String, Object> badges = j.parseJSON(new CharArrayReader(json.toCharArray()));
            
           
            List<Map<String, Object>> list = (List<Map<String, Object>>) badges.get("root");

            for (Map<String, Object> obj : list) {
                Badge e = new Badge();

            
                float id = Float.parseFloat(obj.get("id").toString());
            
                e.setId((int) id);
                
                
            
                e.setName(obj.get("name").toString());
                //e.setDescription(obj.get("description").toString());
            
                listBadges.add(e);

            }

        } catch (IOException ex) {
        }
        
        return listBadges;

    }
    public ArrayList<Badge> getBadges2(int id){ 
        
       
        ConnectionRequest con = new ConnectionRequest();
        con.setPost(false);
        con.setUrl("http://127.0.0.1:8000/comment/userBadges");
        con.addArgument("uid", String.valueOf(id));
        

        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                CommentService ser = new CommentService();
                Vars.thisUserBadges = ser.getBadgeForUser(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return Vars.thisUserBadges;
    }
    public ArrayList<Badge> getAllBadges(){ 
        
       
        ConnectionRequest con = new ConnectionRequest();
        con.setPost(false);
        con.setUrl("http://127.0.0.1:8000/comment/allBadges");
        
        

        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                CommentService ser = new CommentService();
                Vars.allBadges = ser.getBadgeForUser(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return Vars.allBadges;
    }
    
    public void deleteCOmment(int uid , int id){
        
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://127.0.0.1:8000/comment/delete";
        Url=Url+"?uid=" + uid+"&id="+id;
        con.setUrl(Url);
        
        
        
        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
       

        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    
    }
    
}
