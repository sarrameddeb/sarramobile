/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entites.Medicaments;
import com.mycompany.myapp.entites.Reservation_med;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;



/**
 *
 * @author ACER
 */
public class ServiceResrvation {
    
    
    public ArrayList<Reservation_med> reservation;
    public static ServiceResrvation instance;
    public boolean resultOk;
    private ConnectionRequest req;

    public ServiceResrvation () {
        req = new ConnectionRequest();
    }

    public static ServiceResrvation getInstance(){
        if (instance==null){
            instance= new ServiceResrvation();
        }
        return instance;
    
    }
    public boolean addReservation(Medicaments r) {
 

String url = Statics.BASE_URL + "/reservation/med/new/"+ r.getId()   + "/3/3";
        System.out.println(url);
        req.setUrl(url);
        req.setPost(false);  
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOk = req.getResponseCode() == 200;
            }

        });

        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOk;

    }

    public ArrayList<Reservation_med> parseReservation(String jsonText){
        try {
           reservation = new ArrayList<>();
           JSONParser j = new JSONParser();
            Map<String, Object> reservationListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String,Object>> list = (List<Map<String, Object>>) reservationListJson.get("root");
            for (Map<String,Object> obj : list) {
                
            Reservation_med  r = new Reservation_med();
             float id = Float.parseFloat(obj.get("id").toString());
           r.setId((int) id);
        // r.setId_med(obj.get("id_med").toString());
         // r.setId_phar(obj.get("id_phar").toString());
        //  r.setId_patient(obj.get("id_patient").toString());
          
          
            r.setId_med(obj.get("idMed").toString());
          
         r.setId_phar(obj.get("idPhar").toString());
          r.setId_patient(obj.get("idPatient").toString());
       
    reservation.add(r);

            }
         
        } catch (IOException ex) {
            System.err.println("TEST5"+ex);
            
            
        }
             NetworkManager.getInstance().addToQueueAndWait(req);
         return reservation;
    }  
 public ArrayList<Reservation_med> getAllResrvation(int id) {
        String url = Statics.BASE_URL+"/reservation/med/";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
          reservation = parseReservation(new String (req.getResponseData()));
          req.removeResponseListener(this);
           
            }
        });

        NetworkManager.getInstance().addToQueueAndWait(req);
        return reservation;
    }
 
 
 
 
   public boolean deleteResrvation(int id){

        String url=Statics.BASE_URL+"/reservation/med/delete/"+id;
        req.setUrl(url);
        req.setPost(false);
      req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
         req.removeResponseCodeListener(this);
            }
        });
      NetworkManager.getInstance().addToQueueAndWait(req);
       
      return resultOk;
    }
   public boolean EditMedicaments(Reservation_med r){

        String url=Statics.BASE_URL+"/reservation/med/"+r.getId()+"/edit/"+ r.getId_med() + "/" + r.getId_phar() + "/" + r.getId_patient();
        req.setUrl(url);
        req.setPost(false);
      req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
        resultOk =req.getResponseCode()==200;
         req.removeResponseCodeListener(this);
            }
        });
      NetworkManager.getInstance().addToQueueAndWait(req);
      return resultOk;
    }
   public ArrayList<Reservation_med>AfficherProduit(){
        
        ArrayList<Reservation_med> result =new ArrayList();
        req.setUrl(Statics.BASE_URL+"/reservation/med/");
            req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>(){
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp;
                jsonp =new JSONParser();
                try{
                    Map<String,Object>mapPublication=jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                    List<Map<String,Object>> listOfMaps=(List<Map<String,Object>>) mapPublication.get("root");
             
                    for(Map<String,Object> obj : listOfMaps){
                 Reservation_med pub =new Reservation_med();
                    float id =Float.parseFloat(obj.get("id").toString());
                     
                    String idUser=obj.get("idMed").toString();
                     String nom_user=obj.get("idPhar").toString();
                     String prenom_user=obj.get("idPatient").toString();
                       
                // 
                  pub.setId((int)id);
                  pub.setId_med(idUser);
                   
              pub.setId_phar(nom_user);
                  pub.setId_patient(prenom_user);
                
result.add(pub);
                       
                    }
                }catch(Exception ex){
                ex.printStackTrace();
                   System.err.println("TEST5"+ex);
                }
            System.err.println("TEST6");
            
            }
            
        });
         NetworkManager.getInstance().addToQueueAndWait(req);//exec te3 req (dima n7outouha )
         System.err.println("TEST8");
         return result;
    }
}