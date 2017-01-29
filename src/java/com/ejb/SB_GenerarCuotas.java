/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ejb;

import com.syscable.Contrato;
import com.syscable.Cuota;
import com.syscable.CuotaFacade;
import com.syscable.Pago;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import org.apache.commons.lang3.time.DateUtils;

/**
 *
 * @author mmixco
 */
@Stateless
public class SB_GenerarCuotas {

    @EJB
    private com.syscable.PagoFacade pagoFacade; 
    @EJB
    private CuotaFacade cuotaFacade; 
    
    
    public   List<Cuota> generarCuotas(Contrato contrato){
        String vanio ="";
        //String vmes ="";
        List<Cuota> lcuota = new ArrayList<Cuota>();
        Calendar cal = Calendar.getInstance();    
        cal.setTime(contrato.getFechainicio());                    
        vanio = String.valueOf(cal.get(Calendar.YEAR));
        int vvmes= cal.get(Calendar.MONTH)+1;
        String vmes="";
        if(vvmes<10){
              vmes= String.valueOf("0"+vvmes);
        }else{
              vmes= String.valueOf(vvmes);
            }
        List<Cuota> existe_cuota =cuotaFacade.findByAnio(vanio);
        if(existe_cuota.isEmpty()){
            crearCuotas(contrato.getFechainicio());
        }else{
                  if(contrato.getPagoList().isEmpty()){

                      System.out.println("anio:"+vanio);
                      System.out.println("vmes:"+vmes);
                       List<Cuota> lcuota2 = cuotaFacade.findByAnioMes(vanio,vmes); 

                       Cuota cc = lcuota2.get(0);                       
                       lcuota = cuotaFacade.findByAnioMes12(cc.getIdcuota(),cc.getIdcuota()+12);
                       /*recorrer desde este hay mas 12*/
                  }else{
                      String  vcontrato =cuotaFacade.maxPago(contrato.getIdcontrato());                      
                      int vid = Integer.valueOf(vcontrato);
                      System.out.println("id pago>"+vid);
                      lcuota = cuotaFacade.findByAnioMes12(vid+1,vid+12);
                      /*recorrer desde el ultimo pago encontrado hasta 12*/
                  }
        }    
            
     
        
        System.out.println("anio --->"+vanio);
        System.out.println(" mes--->"+vmes);
        System.out.println("lcuota---->"+lcuota);
        for(Cuota cc:lcuota){
            System.out.println("cuota :"+cc.getNombre());
        }
        return lcuota;
        
        
    }
    
    public void crearCuotas(Date fecha){
        
         Date newDate = fecha;
        for(int i =-1; i<150 ; i++){
            String vid = cuotaFacade.maxId();
            int id = Integer.valueOf(vid)+1;
            newDate = DateUtils.addMonths(newDate, 1);
            Calendar cal = Calendar.getInstance();    
            cal.setTime(newDate);        
            
            int vvmes= cal.get(Calendar.MONTH)+1;
            String vmes="";
                    if(vvmes<10){
                          vmes= String.valueOf("0"+vvmes);
                    }else{
                          vmes= String.valueOf(vvmes);
                        }
            
            String vanio = String.valueOf(cal.get(Calendar.YEAR));
            Cuota c = new Cuota(id);
            c.setAnio(vanio);
            c.setMes(vmes);
            c.setNombre(vanio+vmes);
            cuotaFacade.edit(c);
        }
        
    
    }
}
