/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.myfavoritevt.facade;

import edu.myfavoritevt.entity.Procedimiento;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Familia Suárez
 */
@Stateless
public class ProcedimientoFacade extends AbstractFacade<Procedimiento> implements ProcedimientoFacadeLocal {

    @PersistenceContext(unitName = "MyfavoriteVtPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProcedimientoFacade() {
        super(Procedimiento.class);
    }
    
}
