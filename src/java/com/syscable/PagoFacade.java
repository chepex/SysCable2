/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syscable;

import java.math.BigDecimal;
import java.util.List;
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
public class PagoFacade extends AbstractFacade<Pago> {
    @PersistenceContext(unitName = "SysCable2PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PagoFacade() {
        super(Pago.class);
    }
    
  public String maxId( ){
	 
	   
	
		Query q =  em.createNativeQuery("Select max( idpagos) from pago " );		                        
                
                String val = String.valueOf(q.getSingleResult());
        return val;
       
    } 
  
    public List<Pago> findByIdContrato( Contrato contrato ) {
        TypedQuery<Pago> q;
        
        q = em.createNamedQuery("Pago.findByIdContrato", Pago.class)                
                .setParameter("idcontrato", contrato.getIdcontrato());
                
                
                
        
        return q.getResultList();
    }   
    
}
