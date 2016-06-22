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
public class ColoniaFacade extends AbstractFacade<Colonia> {
    @PersistenceContext(unitName = "SysCable2PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ColoniaFacade() {
        super(Colonia.class);
    }
    
    public List<Colonia> findByDeptoMuni(int iddepartamento, int idmunicipio ) {
        TypedQuery<Colonia> q;
        
        q = em.createNamedQuery("Colonia.findByDeptoMuni", Colonia.class)                
                .setParameter("iddepartamento", iddepartamento)
                .setParameter("idmunicipio", idmunicipio);
                
                
        
        return q.getResultList();
    }      
    
}
