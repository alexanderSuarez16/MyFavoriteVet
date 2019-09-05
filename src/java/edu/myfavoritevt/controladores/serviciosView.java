/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.myfavoritevt.controladores;

import edu.myfavoritevt.entity.Citaservicio;
import edu.myfavoritevt.entity.Servicio;
import edu.myfavoritevt.entity.Usuario;
import edu.myfavoritevt.facade.CitaservicioFacadeLocal;
import edu.myfavoritevt.facade.ServicioFacadeLocal;
import java.io.IOException;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Competidor
 */
@Named(value = "serviciosView")
@ViewScoped
public class serviciosView implements Serializable {

    private List<Servicio> listaServicio;
    private List<Citaservicio> listaCitaServicios = new ArrayList<>();
    private List<Citaservicio> listaCitasAgendadas = new ArrayList<>();
    private List<Citaservicio> misServicios = new ArrayList<>();
    private String fechaIn;
    private String horaIn;
    private Citaservicio citaIn;
    private Citaservicio citaTemporal = new Citaservicio();
    private String fechaCita;
    private String horaCita;
    Usuario usuTemporal = new Usuario();

    @EJB
    private ServicioFacadeLocal sfl;
    @EJB
    private CitaservicioFacadeLocal csfl;

    /**
     * Creates a new instance of serviciosView
     */
    public serviciosView() {
        listaServicio = new ArrayList<>();
        fechaIn = "";
        horaIn = "";
        citaIn = new Citaservicio();
    }

    public List<Servicio> getListaServicio() {
        return listaServicio;
    }

    public void setListaServicio(List<Servicio> listaServicio) {
        this.listaServicio = listaServicio;
    }

    public List<Servicio> mostrarServicio() {
        try {
            return listaServicio = sfl.findAll();
        } catch (Exception e) {
            return null;
        }

    }
    
//    public List<Citaservicio> misServicios() {
//        try {
//            return misServicios = csfl.listaMisCitas(usuTemporal.getPKUSUIdUsuario());
//        } catch (Exception e) {
//            return null;
//        }
//    }

    public List<Citaservicio> mostrarCita() {
        listaCitaServicios = csfl.listaCitasPendientes();
        try {
            return listaCitaServicios;
        } catch (Exception e) {
            return null;
        }
    }
    
        public List<Citaservicio> mostrarCitasAgendadas() {
            listaCitasAgendadas = csfl.listaCitasAgendadas();
        try {
            return listaCitasAgendadas;
        } catch (Exception e) {
            return null;
        }
    }

    

    public void agendarServicio() {
        RequestContext rc = RequestContext.getCurrentInstance();        
        try {            
            csfl.agendarCita(citaTemporal.getCISFecha(), citaTemporal.getCISHora(), citaTemporal.getPKCISCitaservicio());            
            rc.execute("swal('Agendamiento exitoso','Se ha agendado con exito la cita', 'success');");
        } catch (Exception e) {
            rc.execute("swal('Agendamiento fallido !','Ocurrio un error al agendar esta cita', 'warning');");
        }

    }

   

    public String getFechaIn() {
        return fechaIn;
    }

    public void setFechaIn(String fechaIn) {
        this.fechaIn = fechaIn;
    }

    public String getHoraIn() {
        return horaIn;
    }

    public void setHoraIn(String horaIn) {
        this.horaIn = horaIn;
    }

    public Citaservicio getCitaIn() {
        return citaIn;
    }

    public void setCitaIn(Citaservicio citaIn) {
        this.citaIn = citaIn;
    }



    public String getFechaCita() {
        return fechaCita;
    }

    public void setFechaCita(String fechaCita) {
        this.fechaCita = fechaCita;
    }

    public String getHoraCita() {
        return horaCita;
    }

    public void setHoraCita(String horaCita) {
        this.horaCita = horaCita;
    }

    public List<Citaservicio> getListaCitaServicios() {
        return listaCitaServicios;
    }

    public void setListaCitaServicios(List<Citaservicio> listaCitaServicios) {
        this.listaCitaServicios = listaCitaServicios;
    }

    public List<Citaservicio> getListaCitasAgendadas() {
        return listaCitasAgendadas;
    }

    public void setListaCitasAgendadas(List<Citaservicio> listaCitasAgendadas) {
        this.listaCitasAgendadas = listaCitasAgendadas;
    }

    public List<Citaservicio> getMisServicios() {
        return misServicios;
    }

    public void setMisServicios(List<Citaservicio> misServicios) {
        this.misServicios = misServicios;
    }


    public Citaservicio getCitaTemporal() {
        return citaTemporal;
    }

    public void setCitaTemporal(Citaservicio citaTemporal) {
        this.citaTemporal = citaTemporal;
    }


}
