/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syscable;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author mmixco
 */
@Stateless
public class OrdentrabajoFacade extends AbstractFacade<Ordentrabajo> {
    @PersistenceContext(unitName = "SysCable2PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public OrdentrabajoFacade() {
        super(Ordentrabajo.class);
    }
    
    public List<Ordentrabajo> findByOrdenId(String ordenId) {
        TypedQuery<Ordentrabajo> q;
        
        q = em.createNamedQuery("Ordentrabajo.findByIdordenTrabajo",Ordentrabajo.class)
                .setParameter("idordenTrabajo", ordenId);
        
        return q.getResultList();
    }
    
    public List<Ordentrabajo> findByCliente(Integer clienteId) {
        TypedQuery<Ordentrabajo> q;
        
        q = em.createNamedQuery("Ordentrabajo.findByIdCliente", Ordentrabajo.class)
                .setParameter("idcliente", clienteId);
        
        return q.getResultList();
    }
    
    public int findByMaxOrdenId() {
        int ordenId = 0;
        
        try {
            StringBuilder jpql = new StringBuilder();
            
            jpql.append("Select idordentrabajo  from syscable.ordentrabajo");
            
            Query q = em.createNativeQuery(jpql.toString());
            
            ordenId = (int) q.getSingleResult();
        
        } catch (Exception e) {
            
            System.out.println("id malo "+e);
            ordenId =1;
        }
        return ordenId;
    }
    
}
