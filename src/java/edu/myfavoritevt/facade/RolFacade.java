/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.myfavoritevt.facade;

import edu.myfavoritevt.entity.Rol;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Familia Suárez
 */
@Stateless
public class RolFacade extends AbstractFacade<Rol> implements RolFacadeLocal {

    @PersistenceContext(unitName = "MyfavoriteVtPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RolFacade() {
        super(Rol.class);
    }
    
    @Override
    public int adicionarRol(int PKusu, int PKrol){
        int salida = 0;
        try {
            Query q = em.createNativeQuery("INSERT INTO `tbl_usuariorol` (`FK_USU_Id_Usuario`, `FK_ROL_Id_Rol`) VALUES (?, ?)");
            q.setParameter(1, PKusu);
            q.setParameter(2, PKrol);
            
            salida = q.executeUpdate();
            
        } catch (Exception e) {
            
        }
        return salida;
    }
    
    @Override
    public int removerRol(int PKusu, int PKrol){
        int salida = 0;
        try {
            Query q = em.createNativeQuery("DELETE FROM `tbl_usuariorol` WHERE FK_USU_Id_Usuario = ? AND FK_ROL_Id_Rol = ?");
            q.setParameter(1, PKusu);
            q.setParameter(2, PKrol);
            
            salida = q.executeUpdate();
            
        } catch (Exception e) {
            
        }
        return salida;
    }
    
}
