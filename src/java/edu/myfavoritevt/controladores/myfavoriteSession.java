/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.myfavoritevt.controladores;

import edu.myfavoritevt.entity.Citamedica;
import edu.myfavoritevt.entity.Citaservicio;
import edu.myfavoritevt.entity.Mascota;
import edu.myfavoritevt.entity.Servicio;
import edu.myfavoritevt.entity.Usuario;
import edu.myfavoritevt.facade.CitamedicaFacadeLocal;
import edu.myfavoritevt.facade.CitaservicioFacadeLocal;
import edu.myfavoritevt.facade.MascotaFacadeLocal;
import edu.myfavoritevt.facade.ServicioFacadeLocal;

import edu.myfavoritevt.facade.UsuarioFacadeLocal;
import edu.myfavoritevt.modelo.Mailer;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Diego
 */
@Named(value = "myfavoriteSession")
@SessionScoped
public class myfavoriteSession implements Serializable {

    private String correo;
    private String clave;
    private Usuario usuLogin;
    private String correoIn;
    private List<Citaservicio> misServicios = new ArrayList<>();
    private List<Citamedica> misCitasMedicas = new ArrayList<>();
    private int miMascota;
    private int idMiMascota;
    private int idServicio;
    private String fechaIn;
    private String horaIn;
    private Date fechaActual = new Date();
    private Servicio servicioTemporal = new Servicio();
    private Citaservicio citaSerIn = new Citaservicio();
    private List<Servicio> listaServicios = new ArrayList<>();
    private List<Citaservicio> listaCitaServicio = new ArrayList<>();
    
    @EJB
    private ServicioFacadeLocal sfl;
    @EJB
    private CitaservicioFacadeLocal csfl;
    @EJB
    private UsuarioFacadeLocal ufl;
    @EJB
    private CitamedicaFacadeLocal cmfl;
    @EJB
    private MascotaFacadeLocal mfl;

    public myfavoriteSession() {
        usuLogin = new Usuario();
    }

    public String validarUsuario() {

        usuLogin = ufl.validar(correo, clave);
        RequestContext rc = RequestContext.getCurrentInstance();
        if (usuLogin != null) {
            return "Usuario/Index.xhtml?faces-redirect=true";
        } else {
            rc.execute("swal('Usuario no encontrado !','Los datos ingresados son incorrectos', 'warning');");
            return "";
        }
    }

    public void validarCorreo() {
        try {

            Map<String, String> params;
            FacesContext fc = FacesContext.getCurrentInstance();
            params = fc.getExternalContext().getRequestParameterMap();
            Usuario c = ufl.validarCorreo(correoIn);
            RequestContext rc = RequestContext.getCurrentInstance();

            if (c.getUSUEmail() != null) {
                String nuevaClave = "" + (int) (Math.random() * 1000000);
                c.setUSUClave(nuevaClave);
                ufl.edit(c);
                Mailer enviaCorreo = new Mailer();
                enviaCorreo.sendClaves(c.getUSUEmail(), c.getUSUNombre() + " " + c.getUSUApellido(), c.getUSUEmail(), c.getUSUClave());

                rc.execute("swal('Usuario !','Se envio su clave al correo  electronico', 'success');");
            } else {
                rc.execute("swal('Usuario !','correo electronico ingresado no existe', 'warning');");
            }

        } catch (Exception e) {
        }

    }

    public void editarPerfil() {
        RequestContext rc = RequestContext.getCurrentInstance();
        try {
            ufl.edit(usuLogin);
            rc.execute("swal('Datos actualizados','Se han actualizado de forma correcta los datos', 'success');");
        } catch (Exception e) {
            rc.execute("swal('Actualizacion fallida !','No se actualizaron los datos', 'warning');");
        }
    }

    public void cerrarSession() throws IOException {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        FacesContext.getCurrentInstance().getExternalContext().redirect("../index.xhtml?faces-redirect=true");
    }

    public void permission() throws IOException {

        if (usuLogin.getPKUSUIdUsuario() == null) {
            System.out.println("*** The user has no permission to visit this page. *** ");
            FacesContext.getCurrentInstance().getExternalContext().redirect("../index.xhtml?faces-redirect=true");
        } else {
            System.out.println("*** The session is still active. User is logged in. *** ");
        }
    }

    public List<Citaservicio> misServicios() {
        SimpleDateFormat fechaAct = new SimpleDateFormat("yyyy-MM-dd");
        String fechaActu = fechaAct.format(new Date());
        misServicios = csfl.listaMisCitas(usuLogin.getPKUSUIdUsuario(), fechaActu);
        try {
            return misServicios;
        } catch (Exception e) {
            return null;
        }
    }

    public void confirmarCitaServicio() throws IOException {
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();

        int pkCitaSerIn = Integer.parseInt(params.get("citaSerPk"));
        Citaservicio citaSerTemp = new Citaservicio();
        citaSerTemp = csfl.find(pkCitaSerIn);
        try {
            csfl.confirmarCita(pkCitaSerIn);
        } catch (Exception e) {
        }
        FacesContext ctx = FacesContext.getCurrentInstance();
        ctx.getExternalContext().redirect("citasServicios.xhtml?faces-redirect=true");
    }

    public void cancelarCitaServicio() throws IOException {
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();

        int pkCitaSerIn = Integer.parseInt(params.get("citaServPk"));
        Citaservicio citaSerTempo = new Citaservicio();
        citaSerTempo = csfl.find(pkCitaSerIn);
        try {
            csfl.cancelarCita(pkCitaSerIn);
        } catch (Exception e) {
        }
        FacesContext ctx = FacesContext.getCurrentInstance();
        ctx.getExternalContext().redirect("citasServicios.xhtml?faces-redirect=true");
    }

    public void confirmarCitaMedica() throws IOException {
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();

        int pkCitaMedIn = Integer.parseInt(params.get("citaMedPk"));
        Citamedica citaMedTemp = new Citamedica();
        citaMedTemp = cmfl.find(pkCitaMedIn);
        try {
            cmfl.confirmarCita(pkCitaMedIn);
        } catch (Exception e) {
        }
        FacesContext ctx = FacesContext.getCurrentInstance();
        ctx.getExternalContext().redirect("citas.xhtml?faces-redirect=true");
    }

    public void cancelarCitaMedica() throws IOException {
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();

        int pkCitaMedIn = Integer.parseInt(params.get("citaMedPk"));
        Citamedica citaMedTemp = new Citamedica();
        citaMedTemp = cmfl.find(pkCitaMedIn);
        try {
            cmfl.cancelarCita(pkCitaMedIn);
        } catch (Exception e) {
        }
        FacesContext ctx = FacesContext.getCurrentInstance();
        ctx.getExternalContext().redirect("citas.xhtml?faces-redirect=true");
    }

    public List<Servicio> mostrarServicios() {
        try {
            return listaServicios = sfl.findAll();
        } catch (Exception e) {
            return null;
        }

    }

    public void solicitarCitaSer() {
        RequestContext rc = RequestContext.getCurrentInstance();
        SimpleDateFormat fechaCis = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat horaCis = new SimpleDateFormat("HH:mm");
        SimpleDateFormat fechaAct = new SimpleDateFormat("yyyy-MM-dd");
        try {

            Usuario usuarioCitaSer = ufl.find(usuLogin.getPKUSUIdUsuario());
            Mascota mascotaCitaSer = mfl.find(miMascota);

            citaSerIn.setFKUSUIdUsuario(usuarioCitaSer);
            citaSerIn.setFKMASIdMascota(mascotaCitaSer);
            citaSerIn.setFKSERIdServicio(servicioTemporal);
            citaSerIn.setCISFecha(fechaCis.parse(fechaIn));
            citaSerIn.setCISHora(horaCis.parse(horaIn));
            listaCitaServicio = csfl.listaContarCitasSer(fechaCis.parse(fechaIn), horaCis.parse(horaIn));
            String fechaActu = fechaAct.format(new Date());
            misServicios = csfl.contarNumeroCitas(usuLogin.getPKUSUIdUsuario(), fechaActu);
            if (listaCitaServicio.size() > 0) {
                rc.execute("swal('Ya se agendo un servicio para esta hora !','Por favor seleccione otra hora y/o fecha', 'warning');");
            } else if (misServicios.size() >= 3) {
                rc.execute("swal('Ya has agendado muchos servicios por hoy !','Por favor intentelo de nuevo mañana', 'warning');");
            } else {
                csfl.solicitarServicio(citaSerIn, fechaActu);
                rc.execute("swal('Solicitud exitosa','Se ha solicitado de forma exitosa el servicio', 'success');");
            }

        } catch (Exception e) {
            rc.execute("swal('Solicitud fallida !','La solicitud no se registro con exito', 'warning');");
        }
    }

    public List<Citamedica> misCitasM() {
        misCitasMedicas = cmfl.listaMisCitas(usuLogin.getPKUSUIdUsuario());
        try {
            return misCitasMedicas;
        } catch (Exception e) {
            return null;
        }
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Usuario getUsuLogin() {
        return usuLogin;
    }

    public void setUsuLogin(Usuario usuLogin) {
        this.usuLogin = usuLogin;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getCorreoIn() {
        return correoIn;
    }

    public void setCorreoIn(String correoIn) {
        this.correoIn = correoIn;
    }

    public List<Citaservicio> getMisServicios() {
        return misServicios;
    }

    public void setMisServicios(List<Citaservicio> misServicios) {
        this.misServicios = misServicios;
    }

    public List<Citamedica> getMisCitasMedicas() {
        return misCitasMedicas;
    }

    public void setMisCitasMedicas(List<Citamedica> misCitasMedicas) {
        this.misCitasMedicas = misCitasMedicas;
    }

    public int getMiMascota() {
        return miMascota;
    }

    public void setMiMascota(int miMascota) {
        this.miMascota = miMascota;
    }

    public int getIdMiMascota() {
        return idMiMascota;
    }

    public void setIdMiMascota(int idMiMascota) {
        this.idMiMascota = idMiMascota;
    }

    public int getIdServicio() {
        return idServicio;
    }

    public void setIdServicio(int idServicio) {
        this.idServicio = idServicio;
    }

    public Citaservicio getCitaSerIn() {
        return citaSerIn;
    }

    public void setCitaSerIn(Citaservicio citaSerIn) {
        this.citaSerIn = citaSerIn;
    }

    public List<Servicio> getListaServicios() {
        return listaServicios;
    }

    public void setListaServicios(List<Servicio> listaServicios) {
        this.listaServicios = listaServicios;
    }

    public Servicio getServicioTemporal() {
        return servicioTemporal;
    }

    public void setServicioTemporal(Servicio servicioTemporal) {
        this.servicioTemporal = servicioTemporal;
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

    public List<Citaservicio> getListaCitaServicio() {
        return listaCitaServicio;
    }

    public void setListaCitaServicio(List<Citaservicio> listaCitaServicio) {
        this.listaCitaServicio = listaCitaServicio;
    }

    public Date getFechaActual() {
        return fechaActual;
    }

    public void setFechaActual(Date fechaActual) {
        this.fechaActual = fechaActual;
    }

}
