/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entites;

/**
 *
 * @author ACER
 */
public class Medicaments {
     private int id;
 private String nom;
 private String description ; 
 private float prix;
 private int quantity ; 
 private String img ;
private String id_pharmacie;

    public Medicaments(int id, String nom, String description, float prix, int quantity, String img, String id_pharmacie) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.prix = prix;
        this.quantity = quantity;
        this.img = img;
        this.id_pharmacie = id_pharmacie;
    }




    public Medicaments() {
        
    }

    public Medicaments(String nom, String description, float prix, int quantity, String img, String id_pharmacie) {
        this.nom = nom;
        this.description = description;
        this.prix = prix;
        this.quantity = quantity;
        this.img = img;
        this.id_pharmacie = id_pharmacie;
    }

    public String getId_pharmacie() {
        return id_pharmacie;
    }

    public void setId_pharmacie(String id_pharmacie) {
        this.id_pharmacie = id_pharmacie;
    }

  
    



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

  

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    @Override
    public String toString() {
        return "Medicaments{" + "id=" + id + ", nom=" + nom + ", description=" + description + ", prix=" + prix + ", quantity=" + quantity + ", img=" + img + ", id_pharmacie=" + id_pharmacie + '}';
    }




    
}