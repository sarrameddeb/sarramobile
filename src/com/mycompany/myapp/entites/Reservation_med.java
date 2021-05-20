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
public class Reservation_med {
   private int id;
 private String id_med;
 private String id_phar ; 
  private String id_patient;

    public Reservation_med(int id, String id_med, String id_phar, String id_patient) {
        this.id = id;
        this.id_med = id_med;
        this.id_phar = id_phar;
        this.id_patient = id_patient;
    }

    public Reservation_med(String id_med, String id_phar, String id_patient) {
        this.id_med = id_med;
        this.id_phar = id_phar;
        this.id_patient = id_patient;
    }

    public Reservation_med() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getId_med() {
        return id_med;
    }

    public void setId_med(String id_med) {
        this.id_med = id_med;
    }

    public String getId_phar() {
        return id_phar;
    }

    public void setId_phar(String id_phar) {
        this.id_phar = id_phar;
    }

    public String getId_patient() {
        return id_patient;
    }

    public void setId_patient(String id_patient) {
        this.id_patient = id_patient;
    }

    @Override
    public String toString() {
        return "Reservation_med{" + "id=" + id + ", id_med=" + id_med + ", id_phar=" + id_phar + ", id_patient=" + id_patient + '}';
    }
  
 
    
    
}
