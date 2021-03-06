/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syscable;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author mmixco
 */
@Stateless
public class RolMenuFacade extends AbstractFacade<RolMenu> {
    @PersistenceContext(unitName = "SysCable2PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RolMenuFacade() {
        super(RolMenu.class);
    }
    
    
    public List<RolMenu> findByIdrolMenu(int rol ) {
        TypedQuery<RolMenu> q;
        
        q = em.createNamedQuery("RolMenu.findByIdrolMenu", RolMenu.class)                
                .setParameter("rol", rol);
                
                
        
        return q.getResultList();
    }     
}
