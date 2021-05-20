/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;



/**
 *
 * @author ACER
 */
public class HomeForm extends BaseForm {
    
    Form current;
    public HomeForm(Resources res){
     
        super("Newsfeed",BoxLayout.y());
         
         current =this;
    //    Toolbar tb=new Toolbar(true);
       
    //    setToolbar(tb);
       super.addSideMenu(res);
      getTitleArea().setUIID("Container");
      //  setTitle("Ajouter Publication");
        getContentPane().setScrollVisible(false);
     current=this;
    setTitle("Home");
    setLayout(BoxLayout.y());
    add (new Label("Choose an option"));
    Button btnListResarvation = new Button ("List Reseravtion");
    Button btnAddResarvation = new Button ("Add Reseravtion");
   
    Button btnAddTask = new Button ("Add Medicaments");
    Button btnListTasks = new Button ("List Medicaments");
 
    btnAddTask.addActionListener(e-> new AddMedicamentsForm(res).show());
    btnListTasks.addActionListener(e-> new ListMedicamentsForm(res).show());
    
 btnListResarvation.addActionListener(e-> new AddReservationForm(res).show());
 btnAddResarvation.addActionListener(e-> new ListReservationForm(res).show());

    addAll(btnAddTask,btnListTasks);
    
    
    
    }
    

}


