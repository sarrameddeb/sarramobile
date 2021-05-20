/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.ToastBar;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import static com.codename1.ui.Component.BOTTOM;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.TextField;
import com.codename1.ui.animations.CommonTransitions;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entites.Medicaments;
import com.mycompany.myapp.entites.Reservation_med;
import com.mycompany.myapp.services.ServiceResrvation;

/**
 *
 * @author ACER
 */
public class AddReservationForm extends Form{
    public AddReservationForm(Resources res){
        setTitle("Add Resrevation");
        setLayout(BoxLayout.y());
        TextField tfnom= new TextField("","NomMedicaments");
        TextField tfphar= new TextField("","Pharamcie");
        TextField tfpat= new TextField("","CodeClient");
       
        Button btnValider = new Button("Add Reservation");
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if (tfnom.getText().length()==0||(tfphar.getText().length()<0)||(tfpat.getText().length()==0))
                    Dialog.show("Alert", "Veuillez remplir tous les champs", new Command("Ok"));
                else {
                    
                    Reservation_med r = new Reservation_med((tfnom.getText()), (tfphar.getText()),(tfpat.getText()));
               //     if (new ServiceResrvation().addReservation(r))
                    {
                       Dialog.show("Success","Reservation Ajoutee", new Command("OK")) ;
                    }
                  //  else 
        Dialog.show("ERROR", "Server Error",new Command("Ok"));
       ToastBar.Status status = ToastBar.getInstance().createStatus();
       status.setShowProgressIndicator(true);
     status.setIcon(res.getImage("m1.jpg").scaledSmallerRatio(Display.getInstance().getDisplayWidth()/10, Display.getInstance().getDisplayWidth()/15));
         status.setMessage("Votre reservation est bien enregistrer ");
            status.setExpires(10000);  // only show the status for 3 seconds, then have it automatically clear

                      status.show();
               //  iDialog.dispose(); //NAHIW LOADING BAED AJOUT
                                   new ListMedicamentsForm(res).show();
                
            }
            }
                
        });
    addAll(tfnom,tfphar,tfpat,btnValider);
  

       
  getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK , e->new HomeForm(res).show());
    // previous.showBack());$
    
    
    }

}
    


   

