/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syscable;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import sun.nio.cs.HistoricallyNamedCharset;

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
    
    public long findByMaxOrdenId() {
        long ordenId = 0;
        Logger logger = Logger.getAnonymousLogger();
        try {
            StringBuilder jpql = new StringBuilder();
            
            jpql.append("select ifnull(max(idordenTrabajo),0) + 1 id from ordentrabajo");
            
            Query q = em.createNativeQuery(jpql.toString());
            
            ordenId = (long) q.getSingleResult();
        
        } catch (Exception e) {
            logger.log(Level.SEVERE,"Error en findByMaxOrdenId", e.getMessage());
        }
        return ordenId;
    }
    
    public List<Ordentrabajo> findByHistorialOrden(String fecha,String noOrden1, 
            String noOrden2, String noCliente) {
        Logger logger = Logger.getAnonymousLogger();
        List<Ordentrabajo> lsHist = new ArrayList<>();
        try {
            lsHist = em.createNativeQuery("select * from ordenTrabajo\n" +
            "where(date_format(fecha_ing,'%d/%m/%Y') = '"+fecha+"'\n" +
            "or idordenTrabajo between '"+noOrden1+"' and '"+noOrden2+"'\n" +
            "or cliente_idcliente = '"+noCliente+"')\n" +
            "order by fecha_ing desc", Ordentrabajo.class).getResultList();
        } catch (Exception e) {
            logger.log(Level.SEVERE,"Error en findByHistorialOrden", e.getMessage());
        }
        return lsHist;
    }
    
}
