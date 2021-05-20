/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.FloatingHint;
import com.codename1.ui.Button;

import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entites.Reservation_med;
import com.mycompany.myapp.services.ServiceResrvation;


/**
 *
 * @author Lenovo
 */
public class EditResrvationForm extends BaseForm {
    
    Form current;
    public  EditResrvationForm(Resources res , Reservation_med r) {
         super("Newsfeed",BoxLayout.y()); //herigate men Newsfeed w l formulaire vertical
    
        Toolbar tb = new Toolbar(true);
        current = this ;
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Ajout Medicaments");
        getContentPane().setScrollVisible(false);
        
        
        super.addSideMenu(res);
        
        TextField Nom = new TextField(r.getId_med(), "Nommed" , 20 , TextField.ANY);
     TextField phar = new TextField(r.getId_phar(), "pahramcie" , 20 , TextField.ANY);
     TextField pat = new TextField(String.valueOf(r.getId_patient()), "codePatient" , 20 , TextField.ANY);
   
 //TextField etat = new TextField(String.valueOf(r.getEtat()) , "Etat" , 20 , TextField.ANY);
 
        //etat bch na3mlo comobbox bon lazm admin ya3mlleha approuver mais just chnwarikom ComboBox
        
//        ComboBox etatCombo = new ComboBox();
//        
//        etatCombo.addItem("Non Traiter");
//        
//        etatCombo.addItem("Traiter");
//        
//   
//        
        
        
        
        
     
        Nom.setUIID("NewsTopLine");
       
        
     
        Nom.setSingleLineTextArea(true);
     
        
        Button btnModifier = new Button("Modifier");
       btnModifier.setUIID("Button");
       
       //Event onclick btnModifer
       
       btnModifier.addPointerPressedListener(l ->   { 
           
         
           r.setId_med(Nom.getText());
           r.setId_phar(phar.getText());
            r.setId_patient(pat.getText());
          
           
           
          
      
       
       //appel fonction modfier reclamation men service
       
       if(ServiceResrvation.getInstance().EditMedicaments(r)) { // if true
           new ListReservationForm(res).show();
       }
        });
       Button btnAnnuler = new Button("Annuler");
       btnAnnuler.addActionListener(e -> {
           new ListMedicamentsForm(res).show();
       });
       
       
       Label l2 = new Label("");
       
       Label l3 = new Label("");
       
       Label l4 = new Label("");
       
       Label l5 = new Label("");
       
        Label l1 = new Label();
        
        Container content = BoxLayout.encloseY(
                l1, l2, 
                new FloatingHint(Nom),
                 new FloatingHint(phar),
                 new FloatingHint(pat),
           
                createLineSeparator(),//ligne de séparation
                btnModifier,
                btnAnnuler
                
               
        );
        
        
        add(content);
        show();
    }
}