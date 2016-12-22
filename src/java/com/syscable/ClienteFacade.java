/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syscable;

import com.syscable.util.JsfUtil;
import java.math.BigDecimal;
import java.math.BigInteger;
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
public class ClienteFacade extends AbstractFacade<Cliente> {
    @PersistenceContext(unitName = "SysCable2PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ClienteFacade() {
        super(Cliente.class);
    }
    
    public  long GenerateId(){	 
	     long val = ((Number)em.createNativeQuery("select max(idcliente) from syscable.cliente")
                    .getSingleResult()).longValue();
                
        return val;
    
    }   

    public List<Cliente> findByNombres(String valor, String colonia, String direccion) {
        TypedQuery<Cliente> q;
          valor = JsfUtil.stringLike(valor);
          colonia = JsfUtil.stringLike(colonia);
          direccion = JsfUtil.stringLike(direccion);
          System.out.println("valor-->"+valor);
          System.out.println("colonia-->"+colonia);
          System.out.println("direccion-->"+direccion);
        q = em.createNamedQuery("Cliente.findByNombres",Cliente.class)
                .setParameter("nombres", valor)
                .setParameter("apellidos",  valor)
                .setParameter("dui", valor)
                .setParameter("tel", valor)
                .setParameter("colonia",colonia)
                .setParameter("direccion", direccion)                
                .setParameter("nit", valor);
        
        return q.getResultList();
    }

    public List<Cliente> findByDui(String valor) {
        TypedQuery<Cliente> q;        
        q = em.createNamedQuery("Cliente.findByDui",Cliente.class)                
                .setParameter("dui",  valor);
        return q.getResultList();
    }    

    public List<Cliente> findByTel(String valor) {
        TypedQuery<Cliente> q;        
        q = em.createNamedQuery("Cliente.findByTelefono",Cliente.class)                
                .setParameter("telefono",  valor);
        return q.getResultList();
    }   

    public List<Cliente> findByNit(String valor) {
        TypedQuery<Cliente> q;        
        q = em.createNamedQuery("Cliente.findByNit",Cliente.class)                
                .setParameter("nit",  valor);
        return q.getResultList();
    }      
    
}
