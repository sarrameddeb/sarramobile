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
import com.mycompany.myapp.entites.Medicaments;
import com.mycompany.myapp.services.ServiceMedicaments;


/**
 *
 * @author Lenovo
 */
public class EditMedicamentsForm extends BaseForm {
    
    Form current;
    public EditMedicamentsForm(Resources res , Medicaments r) {
         super("Newsfeed",BoxLayout.y()); //herigate men Newsfeed w l formulaire vertical
    
        Toolbar tb = new Toolbar(true);
        current = this ;
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Ajout Medicaments");
        getContentPane().setScrollVisible(false);
        
        
        super.addSideMenu(res);
        
        TextField Nom = new TextField(r.getNom(), "Nom" , 20 , TextField.ANY);
     TextField description = new TextField(r.getDescription(), "Description" , 20 , TextField.ANY);
     TextField prix = new TextField(String.valueOf(r.getPrix()), "Prix" , 20 , TextField.ANY);
    TextField quantity = new TextField(String.valueOf(r.getQuantity()), "Quantity" , 20 , TextField.ANY);
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
           
         
           r.setNom(Nom.getText());
           r.setDescription(description.getText());
           r.setPrix(Float.parseFloat(prix.getText()));
            r.setPrix(Integer.parseInt(quantity.getText()));
           
           
          
      
       
       //appel fonction modfier reclamation men service
       
       if(ServiceMedicaments.getInstance().EditMedicaments(r)) { // if true
           new ListMedicamentsForm(res).show();
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
                 new FloatingHint(description),
                 new FloatingHint(prix),
               new FloatingHint(quantity),
                createLineSeparator(),//ligne de séparation
                btnModifier,
                btnAnnuler
                
               
        );
        
        
        add(content);
        show();
    }
}