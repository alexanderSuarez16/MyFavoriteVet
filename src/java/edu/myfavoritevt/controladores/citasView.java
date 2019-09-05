/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.myfavoritevt.controladores;

import edu.myfavoritevt.entity.Citamedica;
import edu.myfavoritevt.facade.CitamedicaFacadeLocal;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;


/**
 *
 * @author DIEGO BURGOS
 */
@Named(value = "citasView")
@SessionScoped
public class citasView implements Serializable {

 @EJB
    CitamedicaFacadeLocal CitamedicaFacadeLocal;
    private List<Citamedica> listaCitamedica; 
    private Citamedica citasIn;
    /**
     * Creates a new instance of CitamedicaView
     */
     public List<Citamedica> getListaCitamedica() {
        return listaCitamedica;
    }

    public void setListaCitamedica(List<Citamedica> listaCitamedica) {
        this.listaCitamedica = listaCitamedica;
    }
    
    
    public citasView() {
        listaCitamedica = new ArrayList<>();
        citasIn = new Citamedica();
    }

   public List<Citamedica> leeListaCitamedica(){
       listaCitamedica = new ArrayList<>();
       try {
           listaCitamedica = CitamedicaFacadeLocal.findAll();
           return listaCitamedica;
       } catch (Exception e) {
           return listaCitamedica;
       }
   }
   
   public void ingresarCitamedica(){
       try {
           CitamedicaFacadeLocal.create(citasIn);
           
       } catch (Exception e) {
       }
    
}

    public Citamedica getcitasIn() {
        return citasIn;
    }

    public void setcitasIn(Citamedica citasIn) {
        this.citasIn = citasIn;
    }
}
