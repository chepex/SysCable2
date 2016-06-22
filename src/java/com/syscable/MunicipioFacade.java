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
public class MunicipioFacade extends AbstractFacade<Municipio> {
    @PersistenceContext(unitName = "SysCable2PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MunicipioFacade() {
        super(Municipio.class);
    }
    
    public List<Municipio> findByDepartamento(int iddepartamento ) {
        TypedQuery<Municipio> q;
        
        q = em.createNamedQuery("Municipio.findByDepartamento", Municipio.class)                
                .setParameter("iddepartamento", iddepartamento);
                
                
        
        return q.getResultList();
    }        
    
}
