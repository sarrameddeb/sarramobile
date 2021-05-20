/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.components.ToastBar;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entites.Medicaments;
import com.mycompany.myapp.services.ServiceMedicaments;


/**
 *
 * @author ACER
 */
public class AddMedicamentsForm extends Form{
    public AddMedicamentsForm(Resources res){
        setTitle("Add new Medicaments");
        setLayout(BoxLayout.y());
        TextField tfnom= new TextField("","NomMedicaments");
        TextField tfdesc= new TextField("","Description");
        TextField tfprix= new TextField("","Prix");
        TextField tfqte= new TextField("","quantite");
        TextField tfimg= new TextField("","Image");
        TextField tfidphar= new TextField("","idpharmacie");
   
        Button btnValider = new Button("Add medicaments");
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if (tfnom.getText().length()==0||(tfprix.getText().length()<0)||(tfdesc.getText().length()==0))
                    Dialog.show("Alert", "Veuillez remplir tous les champs", new Command("Ok"));
                else {
                    try{
                    Medicaments m = new Medicaments((tfnom.getText()), (tfdesc.getText()), Float.parseFloat(tfprix.getText()), Integer.parseInt(tfqte.getText()), (tfimg.getText()),(tfidphar.getText()));
                    if (new ServiceMedicaments().addMedicaments(m))
                    {
                       Dialog.show("Success","Medicament Ajoutee avec succee", new Command("OK")) ;
                    }
                    else 
                       Dialog.show("ERROR", "Server Error",new Command("Ok"));
           ToastBar.Status status = ToastBar.getInstance().createStatus();
          status.setShowProgressIndicator(true);
         status.setIcon(res.getImage("m1.jpg").scaledSmallerRatio(Display.getInstance().getDisplayWidth()/10, Display.getInstance().getDisplayWidth()/15));
               status.setMessage("Medicaments ajoutee avec succseee ");
                    status.setExpires(10000);  // only show the status for 3 seconds, then have it automatically clear

                      status.show();
               //  iDialog.dispose(); //NAHIW LOADING BAED AJOUT
                                   new ListMedicamentsForm(res).show();
                    
                    }catch(NumberFormatException e){
                        Dialog.show("ERROR", "le prix doit etre positive",new Command("Ok"));
                }
                
            }
            }
                
        });
    addAll(tfnom,tfdesc,tfprix,tfqte,tfimg,tfidphar,btnValider);
        
  getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK , e->new HomeForm(res).show());
    // previous.showBack());
    }



   
    }

