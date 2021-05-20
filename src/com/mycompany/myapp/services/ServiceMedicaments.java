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
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 *
 * @author ACER
 */
public class ServiceMedicaments {

    public ArrayList<Medicaments> medicaments;
    public static ServiceMedicaments instance;
    public static boolean resultOk;
    private ConnectionRequest req;

    public ServiceMedicaments() {
        req = new ConnectionRequest();
    }

    public static ServiceMedicaments getInstance() {
        if (instance == null) {
            instance = new ServiceMedicaments();
        }
        return instance;
    }

    public boolean addMedicaments(Medicaments m) {
   // String url = Statics.BASE_URL + "/medicaments/new?nom=\""+ m.getNom() + "\"&description=\"" + m.getDescription() + 
//"\"&prix=" + m.getPrix() + "&quantity=" + m.getQuantity() + "&img=\"" + m.getImg()+"\"&id_pharmacie=\""+m.getId_pharmacie()+"\"";
      String url = Statics.BASE_URL + "/medicaments/new/"+ m.getNom() + "/" + m.getDescription() + "/" + m.getPrix() + "/" + m.getQuantity() + "/" + m.getImg()+"/"+m.getId_pharmacie();
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

    public ArrayList<Medicaments> parseMedicaments(String jsonText){
        try {
            medicaments = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> medicamentsListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String,Object>> list = (List<Map<String, Object>>) medicamentsListJson.get("root");
            for (Map<String,Object> obj : list) {
            Medicaments m = new Medicaments();
            float id = Float.parseFloat(obj.get("id").toString());
            m.setId((int) id);
            m.setNom(obj.get("nom").toString());
            m.setDescription(obj.get("description").toString());
         float prix = Float.parseFloat(obj.get("prix").toString());
         m.setPrix((float)prix); 
         float quantity = Float.parseFloat(obj.get("quantity").toString());
         m.setQuantity((int)quantity);
         m.setImg(obj.get("img").toString());
     //   m.setId_pharmacie(obj.get("id_pharmacie").toString());
           medicaments.add(m);

            }
         
        } catch (IOException ex) {
            
            
        }
         return medicaments;
    }  


    public ArrayList<Medicaments> getAllMedicaments() {
        String url = Statics.BASE_URL+"/medicaments/";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {

          
                    medicaments = parseMedicaments(new String(req.getResponseData()));
                    req.removeResponseListener(this);
              
              
            }
        });

        NetworkManager.getInstance().addToQueueAndWait(req);
        return medicaments;
    }
    
    public void MedicamentsDetails( int id  ){
        String url=Statics.BASE_URL+"/medicaments/"+id;
        req.setUrl(url);
        String str= new String(req.getResponseData());
       req.addResponseListener((evet->{
       try{
       JSONParser json= new JSONParser();
       
         Map<String, Object> medicamentsListJson = json.parseJSON(new CharArrayReader(new String(str).toCharArray()));
       
       }catch(IOException ex){
           System.out.println("error related to sql:("+ex.getMessage());
       }
       System.out.println("data==="+str);
       
             
       }));
    }
    
    
  public boolean deletePublication(int id){

        String url=Statics.BASE_URL+"/medicaments/delete/"+id;
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
//    public boolean editMedicaments(Medicaments m ,int id ){
//        String url=Statics.BASE_URL+"/medicaments{id}/edit/"+ m.getNom() + "/" + m.getDescription() + "/" + m.getPrix() + "/" + m.getQuantity() + "/" + m.getImg()+"/"+m.getId_pharmacie();
//        System.out.println(url);
//            req.setUrl(url);
//        req.setPost(false);
//         req.addResponseListener(new ActionListener<NetworkEvent>() {
//            @Override
//            public void actionPerformed(NetworkEvent evt) {
//                resultOk = req.getResponseCode() == 200;
//                req.removeResponseListener(this);
//            }
//    });
//               NetworkManager.getInstance().addToQueueAndWait(req);   
//        return resultOk;
//               
//    }
public boolean EditMedicaments(Medicaments m){

        String url=Statics.BASE_URL+"/medicaments/"+m.getId()+"/edit/"+m.getNom()+"/"+m.getDescription()+"/"+m.getPrix()+"/"+m.getQuantity()+"/"+m.getImg()+"/"+m.getId_pharmacie();
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

}

