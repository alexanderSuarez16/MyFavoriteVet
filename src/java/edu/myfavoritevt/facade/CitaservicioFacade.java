/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.myfavoritevt.facade;

import edu.myfavoritevt.entity.Citaservicio;
import edu.myfavoritevt.entity.Usuario;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Familia Suárez
 */
@Stateless
public class CitaservicioFacade extends AbstractFacade<Citaservicio> implements CitaservicioFacadeLocal {

    @PersistenceContext(unitName = "MyfavoriteVtPU")
    private EntityManager em;
    

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CitaservicioFacade() {
        super(Citaservicio.class);
    }
    
    @Override
    public int agendarCita(Date fecha, Date hora, int pkCita){
        int salida = 0;
        try {
            Query q = em.createNativeQuery("UPDATE `tbl_citaservicio` SET `CIS_Fecha` = ?, `CIS_Hora` = ?, `CIS_Estado` = 'Agendada' WHERE `tbl_citaservicio`.`PK_CIS_Citaservicio` = ?");
            q.setParameter(1, fecha);
            q.setParameter(2, hora);
            q.setParameter(3, pkCita);
            
            q.executeUpdate();
        } catch (Exception e) {
        }
        return salida;
    }
    
    
    @Override
    public List<Citaservicio> listaCitasPendientes(){
        List<Citaservicio> salida = new ArrayList<>();
        try {
            em.getEntityManagerFactory().getCache().evictAll();
            Query q = em.createQuery("SELECT c FROM Citaservicio c WHERE c.cISEstado = 'En progreso' OR c.cISEstado ='Cancelada' ");
            salida = q.getResultList();
        } catch (Exception e) {
        }
        return salida;
    }
    
    @Override
    public List<Citaservicio> listaCitasAgendadas(){
        List<Citaservicio> salida = new ArrayList<>();
        try {
            em.getEntityManagerFactory().getCache().evictAll();
            Query q = em.createQuery("SELECT c FROM Citaservicio c WHERE c.cISEstado = 'Agendada' ");
            salida = q.getResultList();
        } catch (Exception e) {
        }
        return salida;
    }
    
    @Override
    public List<Citaservicio> listaMisCitas(int PKusu, String fecha){
        List<Citaservicio> salida = new ArrayList<>();
        try {
            em.getEntityManagerFactory().getCache().evictAll();
            
            Query q = em.createNativeQuery("SELECT * FROM `tbl_citaservicio` WHERE tbl_citaservicio.FK_USU_IdUsuario = ? AND tbl_citaservicio.CIS_Fecha = ? AND tbl_citaservicio.CIS_Estado = \"Agendada\"", Citaservicio.class);
            q.setParameter(1, PKusu);
            q.setParameter(2, fecha);
            salida = q.getResultList();
        } catch (Exception e) {
        }
        return salida;
    }
    
    @Override
    public List<Citaservicio> listaContarCitasSer(Date fecha, Date hora){
        List<Citaservicio> salida = new ArrayList<>();
        try {
            em.getEntityManagerFactory().getCache().evictAll();
            Query q = em.createQuery("SELECT c FROM Citaservicio c WHERE c.cISFecha = :fecha AND c.cISHora = :hora");
            q.setParameter("fecha", fecha);
            q.setParameter("hora", hora);
            salida = q.getResultList();
        } catch (Exception e) {
        }
        return salida;
    }
    
    
    @Override
    public List<Citaservicio> contarNumeroCitas(int pKUsu, String fecha){
        List<Citaservicio> salida = new ArrayList<>();
        try {
            em.getEntityManagerFactory().getCache().evictAll();
            Query q = em.createNativeQuery("SELECT * FROM `tbl_citaservicio` WHERE tbl_citaservicio.FK_USU_IdUsuario = ? AND tbl_citaservicio.CIS_FechaEnLaQueSeAgendo = ?", Citaservicio.class);
            q.setParameter(1, pKUsu);
            q.setParameter(2, fecha);
            salida = q.getResultList();
        } catch (Exception e) {
        }
        return salida;
    }
    
    
        
    
    
    @Override
    public void confirmarCita(int pkCitaSer) {
        
        try {
            Query q = em.createNativeQuery("UPDATE `tbl_citaservicio` SET `CIS_Estado` = 'Aceptada' WHERE `tbl_citaservicio`.`PK_CIS_Citaservicio` = ? ");
            q.setParameter(1, pkCitaSer);
           q.executeUpdate();
            
        } catch (Exception e) {
        }
       
    }
    
    @Override
    public void cancelarCita(int pkCitaSer) {
        
        try {
            Query q = em.createNativeQuery("UPDATE `tbl_citaservicio` SET `CIS_Estado` = 'Cancelada' WHERE `tbl_citaservicio`.`PK_CIS_Citaservicio` = ? ");
            q.setParameter(1, pkCitaSer);
           q.executeUpdate();
            
        } catch (Exception e) {
        }
       
    }
    
    
    
    @Override
    public void solicitarServicio(Citaservicio citaSerIn, String fecha) {
        
        try {
            Query q = em.createNativeQuery("INSERT INTO `tbl_citaservicio` (`FK_USU_IdUsuario`, `FK_MAS_IdMascota`, `FK_SER_IdServicio`, `CIS_Fecha`, `CIS_Hora`, `CIS_Estado`, `CIS_FechaEnLaQueSeAgendo`) "
                    + "VALUES (?, ?, ?, ?, ?, 'Agendada', ?);");
            q.setParameter(1, citaSerIn.getFKUSUIdUsuario().getPKUSUIdUsuario());
            q.setParameter(2, citaSerIn.getFKMASIdMascota().getPKMASIdMascota());
            q.setParameter(3, citaSerIn.getFKSERIdServicio().getPKSERIdServicio());
            q.setParameter(4, citaSerIn.getCISFecha());
            q.setParameter(5, citaSerIn.getCISHora());
            q.setParameter(6, fecha);

            q.executeUpdate();
        } catch (Exception e) {
        }
        
    }
    
    
}
