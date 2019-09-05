/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.myfavoritevt.facade;

import edu.myfavoritevt.entity.Citamedica;
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
public class CitamedicaFacade extends AbstractFacade<Citamedica> implements CitamedicaFacadeLocal {

    @PersistenceContext(unitName = "MyfavoriteVtPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CitamedicaFacade() {
        super(Citamedica.class);
    }
    
    @Override
    public int crearCita(Citamedica CitaIn) {
        int salida = 0;
        try {
            Query q = em.createNativeQuery("INSERT INTO `tbl_citamedica` (`FK_USU_IdUsuario`, `FK_MAS_IdMascota`,  `FK_PRO_Procedimiento`,"
                    + " `FK_TIP_IdTipo`, `CIT_Fecha`, `CIT_Hora`)"
                    + " VALUES (?, ?, ?, ?, ?, ?);");
            q.setParameter(1, CitaIn.getFKUSUIdUsuario().getPKUSUIdUsuario());
            q.setParameter(2, CitaIn.getFKMASIdMascota().getPKMASIdMascota());
            q.setParameter(3, CitaIn.getFKPROProcedimiento().getPKPROProcedimiento());
            q.setParameter(4, CitaIn.getFKTIPIdTipo().getPKTIPIdTipo());
            q.setParameter(5, CitaIn.getCITFecha());
            q.setParameter(6, CitaIn.getCITHora());

            salida = q.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return salida;
    }
    
    @Override
    public int agendarCitaRechazada(Date fecha, Date hora, int pkCita){
        int salida = 0;
        try {
            Query q = em.createNativeQuery("UPDATE `tbl_citamedica` SET `CIT_Fecha` = ?, `CIT_Hora` = ?, `CIT_Estado` = 'Agendada' WHERE `tbl_citamedica`.`PK_CIT_IdCita` = ?");
            q.setParameter(1, fecha);
            q.setParameter(2, hora);
            q.setParameter(3, pkCita);
            
            q.executeUpdate();
        } catch (Exception e) {
        }
        return salida;
    }
    
    @Override
    public List<Citamedica> listaMisCitas(int PKusu){
        List<Citamedica> salida = new ArrayList<>();
        try {
            em.getEntityManagerFactory().getCache().evictAll();
            Query q = em.createQuery("SELECT c FROM Citamedica c WHERE c.fKUSUIdUsuario.pKUSUIdUsuario = :PKusu AND C.cITEstado = 'En progreso' ");
            q.setParameter("PKusu", PKusu);
            salida = q.getResultList();
        } catch (Exception e) {
        }
        return salida;
    }
    
    @Override
    public List<Citamedica> listaCitasRechazadas(){
        List<Citamedica> salida = new ArrayList<>();
        try {
            em.getEntityManagerFactory().getCache().evictAll();
            Query q = em.createQuery("SELECT c FROM Citamedica c WHERE c.cITEstado ='Cancelada' ");
            salida = q.getResultList();
        } catch (Exception e) {
        }
        return salida;
    }
    
    @Override
    public List<Citamedica> listaContarCitas(Date fecha, Date hora){
        List<Citamedica> salida = new ArrayList<>();
        try {
            em.getEntityManagerFactory().getCache().evictAll();
            Query q = em.createQuery("SELECT c FROM Citamedica c WHERE c.cITFecha = :fecha AND c.cITHora = :hora ");
            q.setParameter("fecha", fecha);
            q.setParameter("hora", hora);
            salida = q.getResultList();
        } catch (Exception e) {
        }
        return salida;
    }
    
    @Override
    public List<Citamedica> listaContarParaHoy(Date fecha){
        List<Citamedica> salida = new ArrayList<>();
        try {
            em.getEntityManagerFactory().getCache().evictAll();
            Query q = em.createQuery("SELECT c FROM Citamedica c WHERE c.cITFecha = :fecha ");
            q.setParameter("fecha", fecha);
            salida = q.getResultList();
        } catch (Exception e) {
        }
        return salida;
    }
    
    @Override
    public void confirmarCita(int pkCitaMed) {
        
        try {
            Query q = em.createNativeQuery("UPDATE `tbl_citamedica` SET `CIT_Estado` = 'Aceptada' WHERE `tbl_citamedica`.`PK_CIT_IdCita` = ? ");
            q.setParameter(1, pkCitaMed);
           q.executeUpdate();
            
        } catch (Exception e) {
        }
       
    }
    
    @Override
    public void cancelarCita(int pkCitaMed) {
        
        try {
            Query q = em.createNativeQuery("UPDATE `tbl_citamedica` SET `CIT_Estado` = 'Cancelada' WHERE `tbl_citamedica`.`PK_CIT_IdCita` = ? ");
            q.setParameter(1, pkCitaMed);
           q.executeUpdate();
            
        } catch (Exception e) {
        }
       
    }
    
}
