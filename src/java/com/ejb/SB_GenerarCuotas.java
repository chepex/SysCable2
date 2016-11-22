/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ejb;

import com.syscable.Contrato;
import com.syscable.Pago;
import java.util.Calendar;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author mmixco
 */
@Stateless
public class SB_GenerarCuotas {

    @EJB
    private com.syscable.PagoFacade pagoFacade;     
    
    public void generarCuotas(Contrato contrato){
     String vanio ="";
     String vmes ="";
                  
        if(contrato.getPagoList().isEmpty()){
            Calendar cal = Calendar.getInstance();
            cal.setTime(contrato.getFechainicio());        
            vmes= String.valueOf(cal.get(Calendar.MONTH)+1);
            vanio = String.valueOf(cal.get(Calendar.YEAR));
        }else{
            String  idP =pagoFacade.findPago(contrato);
            int idPago = Integer.valueOf(idP);
            Pago p =pagoFacade.find( idPago );      
            vanio = p.getAniomes().substring(0,3);
            vmes = p.getAniomes().substring(4,5);
        }
        
        System.out.println("anio --->"+vanio);
        System.out.println(" mes--->"+vmes);
        
        
    }
}
