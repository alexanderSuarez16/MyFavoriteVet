/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.myfavoritevt.controladores;

import edu.myfavoritevt.facade.MascotaFacadeLocal;
import edu.myfavoritevt.facade.UsuarioFacadeLocal;
import edu.myfavoritevt.entity.Mascota;
import edu.myfavoritevt.entity.Medicamento;
import edu.myfavoritevt.entity.Rol;
import edu.myfavoritevt.entity.Usuario;
import edu.myfavoritevt.facade.MedicamentoFacadeLocal;
import edu.myfavoritevt.facade.RolFacadeLocal;
import edu.myfavoritevt.modelo.Mailer;
import edu.myfavoritevt.modelo.UsuarioExcel;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;


@Named(value = "myFavoriteVetAdminView")
@ViewScoped
public class myFavoriteVetAdminView implements Serializable {

    private List<Usuario> listaUsuario;
    private List<Mascota> listaMascota;
    private Usuario usuarioIn;
    private Usuario usuarioEdit;
    private Mascota mascotaEdit;
    private Mascota mascotaIn;
    private long numDocumento;
    private String asuntoM;
    private String cuerpoM;
    private String fechaIn;
    private Medicamento medicamentoIn;
    private List<Medicamento> listaMedicamento;
    
    private Usuario usurioTemporal = new Usuario();
    private List<Rol> misRoles = new ArrayList<>();
    private List<Rol> rolesDisponibles = new ArrayList<>();
    private List<Mascota> mascotas = new ArrayList<>();
    private List<Mascota> mascotas2 = new ArrayList<>();
    private List<UsuarioExcel> listaExcel = new ArrayList<>();


    @EJB
    private UsuarioFacadeLocal ufl;
    @EJB
    private MascotaFacadeLocal mfl;
    @EJB
    private UsuarioFacadeLocal usuarioFacadeLocal;
    @EJB
    RolFacadeLocal rolFacadeLocal;
    @EJB
    private MascotaFacadeLocal mascotaFacadeLocal;
    @EJB
    private MedicamentoFacadeLocal medicamentoFacadeLocal;

    public myFavoriteVetAdminView() {
        listaUsuario = new ArrayList<>();
        usuarioIn = new Usuario();
        mascotaIn = new Mascota();
        listaMascota = new ArrayList<>();
        medicamentoIn = new Medicamento();
        listaMedicamento = new ArrayList<>();
    }
    
    
    public void cambiarEstado() throws IOException {
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();

        int pkUsuarioIn = Integer.parseInt(params.get("usuPk"));
        Usuario usuTemp = new Usuario();
        usuTemp = usuarioFacadeLocal.find(pkUsuarioIn);
        if (usuTemp.getUSUestado().equals("Activo")) {
            usuarioFacadeLocal.cambiarEstado(pkUsuarioIn, "Inactivo");
        } else {
            usuarioFacadeLocal.cambiarEstado(pkUsuarioIn, "Activo");
        }
        FacesContext ctx = FacesContext.getCurrentInstance();
        ctx.getExternalContext().redirect("consultarUsuarios.xhtml?faces-redirect=true");
    }
    
    
    public void cambiarEstadoMas() throws IOException{
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        
        int pkMascotaIn = Integer.parseInt(params.get("masPk"));
        Mascota masTemp = new Mascota();
        masTemp = mascotaFacadeLocal.find(pkMascotaIn);
        if (masTemp.getMASEstado().equals("Activo")){
            mascotaFacadeLocal.cambiarEstadoMas(pkMascotaIn, "Inactivo");
        }else{
            mascotaFacadeLocal.cambiarEstadoMas(pkMascotaIn, "Activo");
        }
        FacesContext ctx = FacesContext.getCurrentInstance();
        ctx.getExternalContext().redirect("consultarMascotas.xhtml?faces-redirect=true");
    }
    
     
    
    
    
    public void calculaRoles(Usuario usuIn) {
        this.usurioTemporal = usuIn;
        this.misRoles = (List<Rol>) usuIn.getRolCollection();
        this.rolesDisponibles = rolFacadeLocal.findAll();

        for (Rol rol : misRoles) {
            rolesDisponibles.remove(rol);
        }

    }
    public void adicionarRol(int PKrol) {
        try {
            rolFacadeLocal.adicionarRol(usurioTemporal.getPKUSUIdUsuario(), PKrol);
            usurioTemporal = usuarioFacadeLocal.leerUno(usurioTemporal.getPKUSUIdUsuario());

            calculaRoles(usurioTemporal);

        } catch (Exception e) {

        }
    }
    
    public void removerRol(int PKrol) {
        try {
            rolFacadeLocal.removerRol(usurioTemporal.getPKUSUIdUsuario(), PKrol);
            usurioTemporal = usuarioFacadeLocal.leerUno(usurioTemporal.getPKUSUIdUsuario());

            calculaRoles(usurioTemporal);

        } catch (Exception e) {

        }

    }
    
    public void calculaMascotas(Usuario usuIn) {
        try {
            this.usurioTemporal = usuIn;
        this.mascotas.clear();
        this.mascotas2.clear();
        this.mascotas.addAll(usuIn.getMascotaCollection());
        this.mascotas2.addAll(mfl.contarMascotas());

        mascotas.forEach((mascota) -> {
            mascotas2.remove(mascota);
        });

        } catch (Exception e) {
        }
        
    }
    
    public void adicionarMascota(int PKmas){
        try {
            mfl.adicionarMascota(usurioTemporal.getPKUSUIdUsuario(), PKmas);
            usurioTemporal = usuarioFacadeLocal.leerUno(usurioTemporal.getPKUSUIdUsuario());
            calculaMascotas(usurioTemporal);
        } catch (Exception e) {
        }
    }
    
    public void removerMascota(int PKmas){
        try {
            mfl.removerMascota(usurioTemporal.getPKUSUIdUsuario(), PKmas);
            usurioTemporal = usuarioFacadeLocal.leerUno(usurioTemporal.getPKUSUIdUsuario());
            calculaMascotas(usurioTemporal);
        } catch (Exception e) {
        }
    }
  
    public List<Usuario> getListaUsuario() {
        return listaUsuario;
    }

    public void setListaUsuario(List<Usuario> listaUsuario) {
        this.listaUsuario = listaUsuario;
    }

    public Usuario getUsuarioIn() {
        return usuarioIn;
    }

    public void setUsuarioIn(Usuario usuarioIn) {
        this.usuarioIn = usuarioIn;
    }

    public List<Usuario> leeListaUsuario() {
        listaUsuario = new ArrayList<>();
        try {
            listaUsuario = ufl.leerTodos();
            return listaUsuario;
        } catch (Exception e) {
            return listaUsuario;
        }
    }
    
    public void buscarUsuario(){
        usuarioEdit = ufl.buscarUsuDocument(numDocumento);
        
        try {
            ufl.create(usuarioIn);
        } catch (Exception e) {
        }
    }

    public List<Mascota> leeListaMascota() {

        try {
            listaMascota = mfl.leeListaMascota();
            return listaMascota;
        } catch (Exception e) {
            return listaMascota;
        }
    }
    
    public List<Medicamento> leeListaMedicamento(){
        try {
            listaMedicamento = medicamentoFacadeLocal.findAll();
            return listaMedicamento;
        } catch (Exception e) {
            return listaMedicamento;
        }
    }

    public void registrarUsuario() {
        RequestContext rc = RequestContext.getCurrentInstance();
        SimpleDateFormat fechaU = new SimpleDateFormat("yyyy-MM-dd");
        
        try {

            usuarioIn.setUSUFechaNacimiento(fechaU.parse(fechaIn));

            ufl.registrarUsuario(usuarioIn);
            rc.execute("swal('Registro exitoso','Se ha registrado de forma existosa el usuario', 'success');");
        } catch (Exception e) {
            rc.execute("swal('Registro fallido !','El usuario no se registro con exito', 'warning');");
        }
    }

    public void eliminarUsuario(Usuario usuarioEliminar) {
        try {
            ufl.remove(usuarioEliminar);
        } catch (Exception e) {
        }
    }

    
    public void registrarMascota() {
        RequestContext rc = RequestContext.getCurrentInstance();
        try {
            mfl.registrarMascota(mascotaIn);
            rc.execute("swal('Registro exitoso','Se ha registrado de forma existosa la mascota', 'success');");
        } catch (Exception e) {
            rc.execute("swal('Registro fallido !','La mascota no se registro con exito', 'warning');");
        }
    }

    public void eliminarMascota(Mascota mascotaEliminar) {
        try {
            mfl.remove(mascotaEliminar);
        } catch (Exception e) {
        }
    }

    public Usuario getUsuarioEdit() {
        return usuarioEdit;
    }

    public void setUsuarioEdit(Usuario usuarioEdit) {
        this.usuarioEdit = usuarioEdit;
    }

    public List<Mascota> getListaMascota() {
        return listaMascota;
    }

    public void setListaMascota(List<Mascota> listaMascota) {
        this.listaMascota = listaMascota;
    }

    public Mascota getMascotaEdit() {
        return mascotaEdit;
    }

    public void setMascotaEdit(Mascota mascotaEdit) {
        this.mascotaEdit = mascotaEdit;
    }

    public Mascota getMascotaIn() {
        return mascotaIn;
    }

    public void setMascotaIn(Mascota mascotaIn) {
        this.mascotaIn = mascotaIn;
    }

    public long getNumDocumento() {
        return numDocumento;
    }

    public void setNumDocumento(long numDocumento) {
        this.numDocumento = numDocumento;
    }
    
    public void actualizarUsuario(){
        RequestContext rc = RequestContext.getCurrentInstance();
        try {
            ufl.edit(usuarioEdit);
            rc.execute("swal('Datos actualizados','Se han actualizado de forma correcta los datos', 'success');");
        } catch (Exception e) {
            rc.execute("swal('Actualizacion fallida!','No se actualizaron los datos', 'warning');");
        }
    }
    
    public long graficoRol(String tipo){
        try {
            return  ufl.datosGraficoRol(tipo);
        } catch (Exception e) {
        }
        return 0;
    }
    
    public long graficoMascota(String tipo){
        try {
            return  mfl.datosGraficoMascota(tipo);
        } catch (Exception e) {
        }
        return 0;
    }
    
 public void archivoCargaExcel(FileUploadEvent event) {
        try {
            InputStream input = event.getFile().getInputstream();

            XSSFWorkbook workBook = new XSSFWorkbook(input);
            XSSFSheet hssfSheet = workBook.getSheetAt(0);
            Iterator rowIterator = hssfSheet.rowIterator();
            rowIterator.next();
            listaExcel.clear();
            while (rowIterator.hasNext()) {
                XSSFRow hssfRow = (XSSFRow) rowIterator.next();
                Iterator iterator = hssfRow.cellIterator();
                UsuarioExcel filaUsuario = new UsuarioExcel();
                XSSFCell hssfCell = (XSSFCell) iterator.next();
                filaUsuario.setTipoDocumento(hssfCell.getStringCellValue());
                hssfCell = (XSSFCell) iterator.next();
                int documento = (int) hssfCell.getNumericCellValue();
                filaUsuario.setNoDocumento(Long.parseLong("" + documento));
                hssfCell = (XSSFCell) iterator.next();
                filaUsuario.setNombre(hssfCell.getStringCellValue());
                hssfCell = (XSSFCell) iterator.next();
                filaUsuario.setApellido(hssfCell.getStringCellValue());
                hssfCell = (XSSFCell) iterator.next();
                filaUsuario.setCorreo(hssfCell.getStringCellValue());
                filaUsuario.setTelefono(ufl.registrarUsuarioExcel(filaUsuario));
                listaExcel.add(filaUsuario);
            }
        } catch (Exception e) {
        }
    }
 
    public void registrarMedicamento(){
        RequestContext rc = RequestContext.getCurrentInstance();
        SimpleDateFormat fechaMed = new SimpleDateFormat("yyyy-MM-dd");
        
        try {

            medicamentoIn.setMEDFechaVencimiento(fechaMed.parse(fechaIn));

            medicamentoFacadeLocal.create(medicamentoIn);
            rc.execute("swal('Registro exitoso','Se ha registrado de forma existosa el medicamento', 'success');");
        } catch (Exception e) {
            rc.execute("swal('Registro fallido !','El medicamento no se registro con exito', 'warning');");
        }
    }

    
    public void envioMasivo() {
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        this.asuntoM = params.get("asuntoM");
        this.cuerpoM = params.get("cuerpoM");
        
        
        Mailer correoMasivo = new Mailer();
        RequestContext rc = RequestContext.getCurrentInstance();
        
        for (Usuario usuIter : usuarioFacadeLocal.findAll()) {
            try {
                correoMasivo.send(usuIter.getUSUEmail(), asuntoM, cuerpoM);
            } catch (Exception e) {
                rc.execute("swal('Problemas enviando a : !'," + usuIter.getUSUEmail() + ", 'warning');");

            }

        }
        rc.execute("swal('Correos !','Tu correo masivo se ha enviado exitosamente !', 'success');");

    }
    
    

    public String getAsuntoM() {
        return asuntoM;
    }

    public void setAsuntoM(String asuntoM) {
        this.asuntoM = asuntoM;
    }

    public String getCuerpoM() {
        return cuerpoM;
    }

    public void setCuerpoM(String cuerpoM) {
        this.cuerpoM = cuerpoM;
    }

    public String getFechaIn() {
        return fechaIn;
    }

    public void setFechaIn(String fechaIn) {
        this.fechaIn = fechaIn;
    }

    public Usuario getUsurioTemporal() {
        return usurioTemporal;
    }

    public void setUsurioTemporal(Usuario usurioTemporal) {
        this.usurioTemporal = usurioTemporal;
    }

    public List<Rol> getMisRoles() {
        return misRoles;
    }

    public void setMisRoles(List<Rol> misRoles) {
        this.misRoles = misRoles;
    }

    public List<Rol> getRolesDisponibles() {
        return rolesDisponibles;
    }

    public void setRolesDisponibles(List<Rol> rolesDisponibles) {
        this.rolesDisponibles = rolesDisponibles;
    }

    public List<Mascota> getMascotas() {
        return mascotas;
    }

    public void setMascotas(List<Mascota> mascotas) {
        this.mascotas = mascotas;
    }

    public List<Mascota> getMascotas2() {
        return mascotas2;
    }

    public void setMascotas2(List<Mascota> mascotas2) {
        this.mascotas2 = mascotas2;
    }

    public Medicamento getMedicamentoIn() {
        return medicamentoIn;
    }

    public void setMedicamentoIn(Medicamento medicamentoIn) {
        this.medicamentoIn = medicamentoIn;
    }

    public List<Medicamento> getListaMedicamento() {
        return listaMedicamento;
    }

    public void setListaMedicamento(List<Medicamento> listaMedicamento) {
        this.listaMedicamento = listaMedicamento;
    }

    public List<UsuarioExcel> getListaExcel() {
        return listaExcel;
    }

    public void setListaExcel(List<UsuarioExcel> listaExcel) {
        this.listaExcel = listaExcel;
    }

    private static class listaUsuarioExcel {

        private static void add(UsuarioExcel filaUsuario) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        private static void clear() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        public listaUsuarioExcel() {
        }
    }
    

}
