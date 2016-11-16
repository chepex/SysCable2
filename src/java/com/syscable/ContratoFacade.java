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
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author mmixco
 */
@Stateless
public class ContratoFacade extends AbstractFacade<Contrato> {
    @PersistenceContext(unitName = "SysCable2PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ContratoFacade() {
        super(Contrato.class);
    }
    
    
   public List<Contrato> findByIdcliente(int vidCliente) {
        TypedQuery<Contrato> q;
        
        q = em.createNamedQuery("Contrato.findByIdcliente",Contrato.class)
                .setParameter("idCliente",  vidCliente);
                
        
        return q.getResultList();
    }    
   
  public String maxId( ){
	 
	   
	
		Query q =  em.createNativeQuery("Select max( idcontrato) from contrato " );		                        
                
                String val = String.valueOf(q.getSingleResult());
        return val;
       
    } 
     
}
