/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.myfavoritevt.controladores;

import edu.myfavoritevt.entity.Citamedica;
import edu.myfavoritevt.entity.Historiaclinica;
import edu.myfavoritevt.entity.Mascota;
import edu.myfavoritevt.entity.Procedimiento;
import edu.myfavoritevt.entity.Tipo;
import edu.myfavoritevt.entity.Usuario;
import edu.myfavoritevt.facade.CitamedicaFacadeLocal;
import edu.myfavoritevt.facade.HistoriaclinicaFacadeLocal;
import edu.myfavoritevt.facade.MascotaFacadeLocal;
import edu.myfavoritevt.facade.ProcedimientoFacadeLocal;
import edu.myfavoritevt.facade.TipoFacadeLocal;
import edu.myfavoritevt.facade.UsuarioFacadeLocal;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import org.primefaces.context.RequestContext;

@Named(value = "veterinarioView")
@SessionScoped
public class veterinarioView implements Serializable {

    @EJB
    MascotaFacadeLocal mfl;
    @EJB
    CitamedicaFacadeLocal cfl;
    @EJB
    HistoriaclinicaFacadeLocal Hfl;
    @EJB
    ProcedimientoFacadeLocal procedimientosFacadeLocal;
    @EJB
    TipoFacadeLocal tipoFacadeLocal;
    @EJB
    UsuarioFacadeLocal usuariosFacadeLocal;

    private List<Mascota> listaMascota = new ArrayList<>();

    private Citamedica CitaIn;

    private Historiaclinica historiaIn;

    private List<Historiaclinica> listaHistoriaclinica = new ArrayList<>();

    private List<Citamedica> listaCitamedica = new ArrayList<>();

    private int idMascota;

    private String fechaIn;

    private String horaIn;

    private int idProcedimiento;

    private int idUsuario;
    
    private Date fecha = new Date();

    private int idTipo;

    private Citamedica citaMedTemporal = new Citamedica();

    /**
     * Creates a new instance of veterinarioView
     */
    public veterinarioView() {
        CitaIn = new Citamedica();
        historiaIn = new Historiaclinica();
        idMascota = 0;
        fechaIn = "";
        horaIn = "";
        idProcedimiento = 0;
        idUsuario = 0;
        idTipo = 0;
    }

    public List<Mascota> listMascota() {
        listaMascota = mfl.findAll();
        try {
            return listaMascota;
        } catch (Exception e) {
            return null;
        }
    }

    public List<Mascota> getListaMascota() {
        return listaMascota;
    }

    public void setListaMascota(List<Mascota> listaMascota) {
        this.listaMascota = listaMascota;
    }

    public void CrearCita() {
        RequestContext rc = RequestContext.getCurrentInstance();
        SimpleDateFormat fechaCi = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat horaCi = new SimpleDateFormat("HH:mm");

        try {
            
                Procedimiento procedimientoCita = procedimientosFacadeLocal.find(idProcedimiento);
                Usuario usuarioCita = usuariosFacadeLocal.find(idUsuario);
                Mascota mascotaCita = mfl.find(idMascota);
                Tipo tipoCita = tipoFacadeLocal.find(idTipo);
                CitaIn.setFKPROProcedimiento(procedimientoCita);
                CitaIn.setFKUSUIdUsuario(usuarioCita);
                CitaIn.setFKMASIdMascota(mascotaCita);
                CitaIn.setFKTIPIdTipo(tipoCita);
                CitaIn.setCITFecha(fechaCi.parse(fechaIn));
                CitaIn.setCITHora(horaCi.parse(horaIn));
                listaCitamedica = cfl.listaContarCitas(fechaCi.parse(fechaIn), horaCi.parse(horaIn));
                if (listaCitamedica.size() >0) {                    
                    rc.execute("swal('Ya se agendo una cita para esta hora !','Por favor seleccione otra hora y/o fecha', 'warning');");
                 } else {
                    cfl.crearCita(CitaIn);
                    rc.execute("swal('Registro exitoso','Se ha registrado de forma existosa la cita medica', 'success');");
                    }

            } catch (Exception e) {
            rc.execute("swal('Registro fallido !','La cita medica no se registro con exito', 'warning');");
                }
    }
    
    public List<Citamedica> listaCistasDeHoy(){
        listaCitamedica = cfl.listaContarParaHoy(fecha);
        try {
            return listaCitamedica;
        } catch (Exception e) {
            return null;
        }
    }

    public void agendarCitaRechaza() {
        RequestContext rc = RequestContext.getCurrentInstance();

        try {
            cfl.agendarCitaRechazada(citaMedTemporal.getCITFecha(), citaMedTemporal.getCITHora(), citaMedTemporal.getPKCITIdCita());
            rc.execute("swal('Agendamiento exitoso','Se ha agendado con exito la cita', 'success');");
        } catch (Exception e) {
            rc.execute("swal('Agendamiento fallido !','Ocurrio un error al agendar esta cita', 'warning');");
        }

    }

    public Citamedica getCitaIn() {
        return CitaIn;
    }

    public void setCitaIn(Citamedica CitaIn) {
        this.CitaIn = CitaIn;
    }

    public CitamedicaFacadeLocal getcfl() {
        return cfl;
    }

    public void setcfl(CitamedicaFacadeLocal citaIn) {
        this.cfl = citaIn;
    }

    public void crearHistoria() {
        RequestContext rc = RequestContext.getCurrentInstance();
        try {

            Mascota miMascota = mfl.find(idMascota);
            historiaIn.setFKMASIdMascota(miMascota);

            Hfl.ingresarHistoria(historiaIn);
            rc.execute("swal('Registro exitoso','Se ha registrado de forma existosa la historia clinica', 'success');");
        } catch (Exception e) {
            rc.execute("swal('Registro fallido !','La historia clinica no se registro con exito', 'warning');");
        }
    }

    public Historiaclinica getHistoriaIn() {
        return historiaIn;
    }

    public void setHistoriaIn(Historiaclinica historiaIn) {
        this.historiaIn = historiaIn;
    }

    public HistoriaclinicaFacadeLocal getHfl() {
        return Hfl;
    }

    public void setHfl(HistoriaclinicaFacadeLocal Hfl) {
        this.Hfl = Hfl;
    }

    public List<Historiaclinica> listHistoriaclinica() {
        listaHistoriaclinica = Hfl.listaHistoriasClinicas();
        try {
            return listaHistoriaclinica;
        } catch (Exception e) {
            return null;
        }
    }

    public CitamedicaFacadeLocal getCfl() {
        return cfl;
    }

    public void setCfl(CitamedicaFacadeLocal cfl) {
        this.cfl = cfl;
    }

    public List<Citamedica> listaCitamedica() {
        listaCitamedica = cfl.findAll();
        try {
            return listaCitamedica;
        } catch (Exception e) {
            return null;
        }
    }

    public List<Citamedica> listaCitamedicaRechazada() {
        listaCitamedica = cfl.listaCitasRechazadas();
        try {
            return listaCitamedica;
        } catch (Exception e) {
            return null;
        }
    }

    public List<Usuario> listaUsuario() {
        return usuariosFacadeLocal.findAll();
    }

    public List<Procedimiento> listaProcedimiento() {
        return procedimientosFacadeLocal.findAll();
    }

    public List<Tipo> listaTipo() {
        return tipoFacadeLocal.findAll();
    }

    public int getIdMascota() {
        return idMascota;
    }

    public void setIdMascota(int idMascota) {
        this.idMascota = idMascota;
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

    public int getIdProcedimiento() {
        return idProcedimiento;
    }

    public void setIdProcedimiento(int idProcedimiento) {
        this.idProcedimiento = idProcedimiento;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdTipo() {
        return idTipo;
    }

    public void setIdTipo(int idTipo) {
        this.idTipo = idTipo;
    }

    public Citamedica getCitaMedTemporal() {
        return citaMedTemporal;
    }

    public void setCitaMedTemporal(Citamedica citaMedTemporal) {
        this.citaMedTemporal = citaMedTemporal;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

}
