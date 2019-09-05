/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.myfavoritevt.facade;

import edu.myfavoritevt.entity.Mascota;
import java.util.ArrayList;
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
public class MascotaFacade extends AbstractFacade<Mascota> implements MascotaFacadeLocal {

    @PersistenceContext(unitName = "MyfavoriteVtPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MascotaFacade() {
        super(Mascota.class);
    }
    
    @Override
    public List<Mascota> leeListaMascota(){
        Mascota salida = new Mascota();
        try {
            em.getEntityManagerFactory().getCache().evictAll();
            Query q = em.createQuery("SELECT c FROM Mascota c");
            
            return q.getResultList();
        } catch (Exception e) {
        }
        return null;
    }
    
    @Override
    public int registrarMascota(Mascota mascotaIn){
        int salida = 0;
        try {
            Query q = em.createNativeQuery("INSERT INTO `tbl_mascota` "
                    + "(`MAS_Mascota_Beneficiaria`, `MAS_Nombre_Mascota`, `MAS_Sexo_Mascota`, `MAS_Tipo_Mascota`, "
                    + "`MAS_Raza_Mascota`, `MAS_Peso_Mascota`, `MAS_Estatura_Mascota`)"
                    + " VALUES (?, ?, ?, ?, ?, ?, ?);");
            
            q.setParameter(1, mascotaIn.getMASMascotaBeneficiaria());
            q.setParameter(2, mascotaIn.getMASNombreMascota());
            q.setParameter(3, mascotaIn.getMASSexoMascota());
            q.setParameter(4, mascotaIn.getMASTipoMascota());
            q.setParameter(5, mascotaIn.getMASRazaMascota());
            q.setParameter(6, mascotaIn.getMASPesoMascota());
            q.setParameter(7, mascotaIn.getMASEstaturaMascota());
            
            salida = q.executeUpdate();
        } catch (Exception e) {
        }
        return salida;
    }
    
    @Override
    public void cambiarEstadoMas(int pkMascota, String estado) {
        
        try {
            Query q = em.createNativeQuery("UPDATE `tbl_mascota` SET `MAS_Estado` = ? WHERE `tbl_mascota`.`PK_MAS_IdMascota` = ? ");
            q.setParameter(1, estado);
            q.setParameter(2, pkMascota);
           q.executeUpdate();
            
        } catch (Exception e) {
        }
       
    }
    
    @Override
    public int adicionarMascota(int PKusu, int PKmas){
        int salida = 0;
        try {
            Query q = em.createNativeQuery("INSERT INTO `tbl_usuariomascota` (`FK_USU_Id_Usuario`, `FK_MAS_Id_Mascota`) VALUES (?, ?)");
            q.setParameter(1, PKusu);
            q.setParameter(2, PKmas);
            
            salida = q.executeUpdate();
            
        } catch (Exception e) {
            
        }
        return salida;
    }
    
    @Override
    public int removerMascota(int PKusu, int PKmas){
        int salida = 0;
        try {
            Query q = em.createNativeQuery("DELETE FROM `tbl_usuariomascota` WHERE FK_USU_Id_Usuario = ? AND FK_MAS_Id_Mascota = ?");
            q.setParameter(1, PKusu);
            q.setParameter(2, PKmas);
            
            salida = q.executeUpdate();
            
        } catch (Exception e) {
            
        }
        return salida;
    }
    
    @Override
    public List<Mascota> contarMascotas(){
         List<Mascota> salida = new ArrayList<>();
         try {
             Query q = em.createNativeQuery("SELECT masc.PK_MAS_IdMascota, masc.MAS_Tipo_Mascota, masc.MAS_Mascota_Beneficiaria,"
                     + " masc.MAS_Nombre_Mascota, masc.MAS_Sexo_Mascota, masc.MAS_Raza_Mascota, masc.MAS_Peso_Mascota,"
                     + " masc.MAS_Estatura_Mascota FROM tbl_mascota as masc LEFT JOIN tbl_usuariomascota as usum "
                     + "ON usum.FK_MAS_Id_Mascota = masc.PK_MAS_IdMascota WHERE usum.FK_MAS_Id_Mascota IS null",Mascota.class);
             salida = q.getResultList();
         } catch (Exception e) {
         }
         return salida;
     }
    
    @Override
    public long datosGraficoMascota(String tipoConsulta) {
        long salida = 0;
        try {
            Query q = em.createNativeQuery("SELECT COUNT(*) FROM `tbl_mascota` WHERE tbl_mascota.MAS_Tipo_Mascota = ?");
            q.setParameter(1, tipoConsulta);
            salida = (long) q.getSingleResult();

        } catch (Exception e) {
            
        }
        return salida;
    }

    
}
