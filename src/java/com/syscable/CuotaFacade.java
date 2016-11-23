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
public class CuotaFacade extends AbstractFacade<Cuota> {

    @PersistenceContext(unitName = "SysCable2PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CuotaFacade() {
        super(Cuota.class);
    }
    
   public String maxId( ){
	 
	    String val ="";
	try{
            Query q =  em.createNativeQuery("Select IFNULL(max( idcuota),0)  from cuota " );
            val= String.valueOf(q.getSingleResult());
        }catch(Exception ex){
              val ="0";
            }
		
        return val;
       
    }  
   public String maxPago(int contrato ){
	 
	    String val ="";
	try{
            Query q =  em.createNativeQuery("Select IFNULL(max( aniomes),0)  from pago where contrato_idcontrato =? " );
            q .setParameter(1, contrato);
            val= String.valueOf(q.getSingleResult());
        }catch(Exception ex){
              val ="0";
            }
		
        return val;
       
    }     

    public List<Cuota> findByAnio( String anio ) {
        TypedQuery<Cuota> q;
        
        q = em.createNamedQuery("Cuota.findByAnio", Cuota.class)                
                .setParameter("anio", anio);                 
        return q.getResultList();
    }     
    public List<Cuota> findByAnioMes( String anio, String mes ) {
        TypedQuery<Cuota> q;
        
        q = em.createNamedQuery("Cuota.findByAnioMes", Cuota.class)                
                .setParameter("anio", anio)
                .setParameter("mes", mes); 
        return q.getResultList();
    }  

    public List<Cuota> findByAnioMes12( int cuota1, int cuota2 ) {
        TypedQuery<Cuota> q;
        
        q = em.createNamedQuery("Cuota.findByAnioMes12", Cuota.class)                
                .setParameter("cuota1", cuota1)
                .setParameter("cuota2", cuota2); 
        return q.getResultList();
    }      
    
}
