package com.syscable;

import com.ejb.SB_GenerarCuotas;
import com.syscable.util.JsfUtil;
import com.syscable.util.JsfUtil.PersistAction;
import java.io.IOException;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.naming.NamingException;
import net.sf.jasperreports.engine.JRException;
import org.primefaces.event.RowEditEvent;

@ManagedBean(name = "clienteController")
@SessionScoped
public class ClienteController implements Serializable {
    @EJB
    private com.ejb.SB_Reportes reportes;  
    @EJB
    private com.syscable.ClienteFacade ejbFacade;
    @EJB
    private com.syscable.OrdentrabajoFacade ordentrabajoFacade;    
    @EJB
    private com.syscable.ContratoFacade contratoFacade;     
    @EJB
    private com.syscable.ProfesionFacade profesionFacade;  
    @EJB
    private com.syscable.ColoniaFacade coloniaFacade;  
    @EJB
    private com.syscable.NacionalidadFacade nacionalidadFacade; 
    @EJB
    private com.syscable.MunicipioFacade municipioFacade;     
    @EJB
    private com.syscable.DepartamentoFacade departamentoFacade;    
    @EJB
    private com.syscable.PagoFacade pagoFacade; 
    @EJB
    private com.syscable.CompaniaFacade companiaFacade;     
    @EJB
    private SB_GenerarCuotas sb_GenerarCuotas;       
    
    
    
    
    private List<Cliente> items = null;
    private List<Cliente> lclientesbusqueda = null;
    private List<Contrato> lcontrato = null;
    private List<Ordentrabajo> lordenes = null;
    private List<Producto> lproductos = new ArrayList<Producto>();
    private Cliente selected;
    private String vprofesion;
    private String vdocumento;
    private String vcolonia;
    private String vnacionalidad,horaOrden;   
    private List<Municipio> lmunicipios;
    private List<Colonia> lcolonia;    
    private Contrato vcontrato;    
    private Ordentrabajo vordentrabajo;
    private Producto vproducto;
    private String vaniomes;
    private String fcolonia;    
    private String fdireccion;        
    private List<Cuota> lcuota;
    private Cuota vcuota;
    private BigDecimal valorCuota;
    private BigDecimal monto;
    private BigDecimal cambio;
     private String ocultoCrea;
    private BigDecimal cvalorCuota;     
    private BigDecimal ccuotas;  
        private Date cfinicio;  
    
    private String vbuscar;
    @ManagedProperty(value="#{ordentrabajoController}")
    private OrdentrabajoController ordentrabajoController;
    
    
    public ClienteController() {
        
         
    }

    public Date getCfinicio() {
        return cfinicio;
    }

    public void setCfinicio(Date cfinicio) {
        this.cfinicio = cfinicio;
    }
    
    

    public BigDecimal getCcuotas() {
        return ccuotas;
    }

    public void setCcuotas(BigDecimal ccuotas) {
        this.ccuotas = ccuotas;
    }
    

    public BigDecimal getCvalorCuota() {
        return cvalorCuota;
    }

    public void setCvalorCuota(BigDecimal cvalorCuota) {
        this.cvalorCuota = cvalorCuota;
    }

    
    public Producto getVproducto() {
        return vproducto;
    }

    public void setVproducto(Producto vproducto) {
        this.vproducto = vproducto;
    }

    
    
    public List<Producto> getLproductos() {
        return lproductos;
    }

    public void setLproductos(List<Producto> lproductos) {
        this.lproductos = lproductos;
    }

    public void addProducto(){
        lproductos.add(vproducto);
    }
    
    public String getVdocumento() {
        return vdocumento;
    }

    public String getOcultoCrea() {
        return ocultoCrea;
    }

    public void setOcultoCrea(String ocultoCrea) {
        this.ocultoCrea = ocultoCrea;
    }
    
    

    public void setVdocumento(String vdocumento) {
        this.vdocumento = vdocumento;
    }


    
    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }

    public BigDecimal getCambio() {
        if(valorCuota!=null && monto != null){
            cambio =  this.monto.subtract(valorCuota);   
            System.out.println("Cambio--->"+cambio);
        }
        
        
       return cambio;
    }

    public void setCambio(BigDecimal cambio) {
        this.cambio = cambio;
    }

    
    
    public List<Ordentrabajo> getLordenes() {
        return lordenes;
    }

    public void setLordenes(List<Ordentrabajo> lordenes) {
        this.lordenes = lordenes;
    }

    
    
    public String getFcolonia() {
        return fcolonia;
    }

    public void setFcolonia(String fcolonia) {
        this.fcolonia = fcolonia;
    }

    public String getFdireccion() {
        return fdireccion;
    }

    public void setFdireccion(String fdireccion) {
        this.fdireccion = fdireccion;
    }

    
    
    public BigDecimal getValorCuota() {
        return valorCuota;
    }

    public void setValorCuota(BigDecimal valorCuota) {
        this.valorCuota = valorCuota;
    }

    
    public Cuota getVcuota() {
        return vcuota;
    }

    public void setVcuota(Cuota vcuota) {
        this.vcuota = vcuota;
    }

 
    
    
    public List<Cuota> getLcuota() {
        return lcuota;
    }

    public void setLcuota(List<Cuota> lcuota) {
        this.lcuota = lcuota;
    }

  

    
    
    public String getVaniomes() {
        return vaniomes;
    }

    public void setVaniomes(String vaniomes) {
        this.vaniomes = vaniomes;
    }

    
    
    public String getHoraOrden() {
        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss a");
        horaOrden = dateFormat.format(new Date());
        return horaOrden;
    }

    public void setHoraOrden(String horaOrden) {
        this.horaOrden = horaOrden;
    }

    public Ordentrabajo getVordentrabajo() {
        return vordentrabajo;
    }

    public void setVordentrabajo(Ordentrabajo vordentrabajo) {
        this.vordentrabajo = vordentrabajo;
    }

    
    
    public List<Contrato> getLcontrato() {
        if(selected!=null){
        lcontrato=   contratoFacade.findByIdcliente(this.selected.getIdcliente());
        }
        
        return lcontrato;
    }

    public void setLcontrato(List<Contrato> lcontrato) {
        this.lcontrato = lcontrato;
    }
    
    

    public List<Cliente> getLclientesbusqueda() {
        return lclientesbusqueda;
    }

    public void setLclientesbusqueda(List<Cliente> lclientesbusqueda) {
        this.lclientesbusqueda = lclientesbusqueda;
    }

    
    
    public String getVbuscar() {
        return vbuscar;
    }

    public void setVbuscar(String vbuscar) {
        this.vbuscar = vbuscar;
    }

    
    public OrdentrabajoController getOrdentrabajoController() {
        return ordentrabajoController;
    }

    public void setOrdentrabajoController(OrdentrabajoController ordentrabajoController) {
        this.ordentrabajoController = ordentrabajoController;
    }

    
    
    public Contrato getVcontrato() {
        return vcontrato;
    }

    public void setVcontrato(Contrato vcontrato) {
        this.vcontrato = vcontrato;
    }

    
    public List<Colonia> getLcolonia() {
        return lcolonia;
    }

    public void setLcolonia(List<Colonia> lcolonia) {
        this.lcolonia = lcolonia;
    }

    public List<Municipio> getLmunicipios() {   
        return lmunicipios;
    }

    public void setLmunicipios(List<Municipio> lmunicipios) {
        this.lmunicipios = lmunicipios;
    }
    

    public String getVprofesion() {
        return vprofesion;
    }

    public void setVprofesion(String vprofesion) {
        this.vprofesion = vprofesion;
    }

    public String getVcolonia() {
        return vcolonia;
    }

    public void setVcolonia(String vcolonia) {
        this.vcolonia = vcolonia;
    }

    public String getVnacionalidad() {
        return vnacionalidad;
    }

    public void setVnacionalidad(String vnacionalidad) {
        this.vnacionalidad = vnacionalidad;
    }

    
    
    
    public Cliente getSelected() {
        return selected;
    }

    public void setSelected(Cliente selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private ClienteFacade getFacade() {
        return ejbFacade;
    }

    public Cliente prepareCreate() {
       Departamento d = new Departamento(10);
       
        System.out.println("precreate-->");
        System.out.println("precreate-->");
        System.out.println("precreate-->");
        
        selected = new Cliente();
        selected.setIdcliente(0);
        ocultoCrea = "E";
        selected.setDepartamentoIddepartamento(d);
        System.out.println("departamento-->"+d);
        initializeEmbeddableKey();
        return selected;
    }

    public void mas(){
        System.out.println("--<<>>");
    }
    
    public void buscar() {
        this.lclientesbusqueda= this.ejbFacade.findByNombres(this.vbuscar,this.fcolonia, this.fdireccion);
        
        if (lclientesbusqueda.isEmpty()) {
            JsfUtil.addErrorMessage("No se encontraron registros. Favor de intentar de nuevo.");
        }
        
        vbuscar = null;
        fcolonia = null;
        fdireccion = null;
    }
    
    public void selecionar(){
    
        if(ordentrabajoFacade.findByCliente(selected.getIdcliente())!=null){
            ordentrabajoController.setLordenes(ordentrabajoFacade.findByCliente(selected.getIdcliente()));
        }
        
        Departamento d = departamentoFacade.find(selected.getDepartamentoIddepartamento().getIddepartamento());
        Municipio m= municipioFacade.find(selected.getMunicipioIdmunicipio().getIdmunicipio());
        selected.setDepartamentoIddepartamento(d);
        selected.setMunicipioIdmunicipio(m);
        System.out.println("municipio --->"+selected.getMunicipioIdmunicipio());
        lordenes = this.ordentrabajoFacade.findByCliente(selected.getIdcliente());
         lmunicipios  =  municipioFacade.findByDepartamento(selected.getDepartamentoIddepartamento().getIddepartamento());
           this.lcolonia  =  coloniaFacade.findByDeptoMuni(selected.getDepartamentoIddepartamento().getIddepartamento(),selected.getMunicipioIdmunicipio().getIdmunicipio());
        System.out.println("lordenes--->"+ordentrabajoController.lordenes);
    
    }
    
    public void createColonia() {       
        Colonia c = new Colonia(0);
        c.setNombre(vcolonia);
        c.setDepartamentoIddepartamento(selected.getDepartamentoIddepartamento());
        c.setMunicipioIdmunicipio(selected.getMunicipioIdmunicipio());
        coloniaFacade.edit(c);
        actualizaColonia();
        this.selected.setColoniaIdcolonia(c);
        JsfUtil.addSuccessMessage("Colonia creada correctament");   
    }
    
    public void createNacionalidad() {       
        Nacionalidad n = new Nacionalidad(0);
        n.setNombre(vnacionalidad);
        nacionalidadFacade.edit(n);
        this.selected.setNacionalidadIdnacionalidad(n);
        JsfUtil.addSuccessMessage("Nacionalidad creada correctament");   
    }    
    
    public void createProfesion() {       
        Profesion p = new Profesion(0);
        p.setNombre(vprofesion);
        profesionFacade.edit(p);
        this.selected.setProfesionIdprofesion(p);
        JsfUtil.addSuccessMessage("Profesion creada correctament");   
    }

    public String  create() {
        try{
            if(selected.getIdcliente()==0){
                    
                List<Cliente> lc = this.ejbFacade.findByDui(this.selected.getDui());
                if(!lc.isEmpty()){
                    JsfUtil.addErrorMessage("Dui ya esta registrado favor verificar"); 
                    return "error";
                }
                List<Cliente> lc2 = this.ejbFacade.findByTel(this.selected.getTelefono());
                if(!lc2.isEmpty()){
                    JsfUtil.addErrorMessage("Telefono ya esta registrado favor verificar"); 
                    return "error";
                }
                List<Cliente> lc3 = this.ejbFacade.findByNit(this.selected.getNit());
                if(!lc3.isEmpty()){
                    JsfUtil.addErrorMessage("Nit ya esta registrado favor verificar"); 
                    return "error";
                }                
                
                
                long vid = this.ejbFacade.GenerateId();
                this.selected.setIdcliente((int)vid);
                ocultoCrea = null;
                
            }
            
            
            persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("ClienteCreated"));
        }catch(Exception ex){
              JsfUtil.addSuccessMessage("Surgio un error "+ex);   
        }
        
    
      if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
      return "ok";
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("ClienteUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("ClienteDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Cliente> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
                    getFacade().edit(selected);
                }               
                else {
                    getFacade().remove(selected);
                }
                JsfUtil.addSuccessMessage(successMessage);
            } catch (EJBException ex) {
                String msg = "";
                Throwable cause = ex.getCause();
                if (cause != null) {
                    msg = cause.getLocalizedMessage();
                }
                if (msg.length() > 0) {
                    JsfUtil.addErrorMessage("Surgio un error grave  "+msg);
                } else {
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            }
        }
    }

    public List<Cliente> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Cliente> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Cliente.class)
    public static class ClienteControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ClienteController controller = (ClienteController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "clienteController");
            return controller.getFacade().find(getKey(value));
        }

        java.lang.Integer getKey(String value) {
            java.lang.Integer key;
            key = Integer.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Integer value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Cliente) {
                Cliente o = (Cliente) object;
                return getStringKey(o.getIdcliente());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Cliente.class.getName()});
                return null;
            }
        }

    }
    
    
   public void limpiar() {
       selected = null; // Remove selection
       items = null;    // Invalidate list of items to trigger re-query.
       vbuscar = null;
       fcolonia = null;
       fdireccion = null;
       lclientesbusqueda =null;
       ocultoCrea = null;
    }    

    public void actualizaMunicipio(){
        if(selected!=null){
              lmunicipios  =  municipioFacade.findByDepartamento(selected.getDepartamentoIddepartamento().getIddepartamento());
          }
    }
    
    public void actualizaColonia(){
        if(selected!=null){
              this.lcolonia  =  coloniaFacade.findByDeptoMuni(selected.getDepartamentoIddepartamento().getIddepartamento(),selected.getMunicipioIdmunicipio().getIdmunicipio());
          }
    }    
    
    
    public void precreateContrato(){
        
        String vid= this.contratoFacade.maxId();
        int id  =  Integer.parseInt(vid)+1;

        Contrato c= new Contrato(id);
        c.setClienteIdcliente(selected);
        Date d = new Date();
        c.setFecha(d);
        c.setFechainicio(d);        
        this.vcontrato = c;
        
    
    }
    
    public void prepareCreateOrden( ) {
        vordentrabajo = new Ordentrabajo();
        vordentrabajo.setIdordenTrabajo(Integer.parseInt(String.valueOf(ordentrabajoFacade.findByMaxOrdenId())));
        vordentrabajo.setClienteIdcliente(selected);
        Date fechaOrden = new Date();
        vordentrabajo.setFechaIng(fechaOrden);
    }    
    
    public void crearOrden(){
        try {
            LoginBean lb = new LoginBean();
            if (vordentrabajo.getDescripcion() != null && !vordentrabajo.getDescripcion().isEmpty()) {
                vordentrabajo.setDescripcion(vordentrabajo.getDescripcion());
            } else {
                vordentrabajo.setDescripcion("No se reporto falla");
            }
            vordentrabajo.setUserCreate(lb.ssuser());
            vordentrabajo.setEstado("P");
            ordentrabajoFacade.edit(vordentrabajo);
            selected.getOrdentrabajoList().add(vordentrabajo);            
             lordenes = this.ordentrabajoFacade.findByCliente(selected.getIdcliente());
            JsfUtil.addSuccessMessage("Contrato almacenado correctamente");
        } catch(Exception ex) {
            JsfUtil.addErrorMessage("Surgio un error " +ex);
        }
    }
    
    public void onRowEdit(RowEditEvent event) {
        Ordentrabajo ot = (Ordentrabajo) event.getObject();
        
        System.out.println("Orden trabajo N° " + ot.getIdordenTrabajo());
        
        ot.setTecnicoIdtecnico(ot.getTecnicoIdtecnico());
        ot.setEstado(ot.getEstado());
        ot.setDescripcionSolucion(ot.getDescripcionSolucion());
        
        ordentrabajoFacade.edit(ot);
        
        JsfUtil.addSuccessMessage("Orden Actualizada.");
    }
    
    
    
    public void creaContrato(){
        try{
            vcontrato.setEstado("A");
            vcontrato.setCuotasPagadas(BigDecimal.ZERO);
            vcontrato.setSaldo(vcontrato.getCuotas().multiply(vcontrato.getValorCuota()));
            contratoFacade.edit(vcontrato);
            selected.getContratoList().add(vcontrato);
            JsfUtil.addSuccessMessage("Contrato almacenado correctamente");   
        }catch(Exception ex){
            JsfUtil.addErrorMessage("Surgio un error "+ex);   
        }
    }
    
    public void crearPago(){
        System.out.println("realizar pago --!");
        try{
            Date d = new Date();
            BigDecimal pagadas = new BigDecimal(1);
            
            System.out.println("vcontrato"+vcontrato);
            System.out.println("cuotas"+vcontrato.getCuotasPagadas());
            System.out.println("p-->0");
            pagadas = pagadas.add(vcontrato.getCuotasPagadas());
            System.out.println("p-->01");
            String id = pagoFacade.maxId();
            int vid =Integer.parseInt(id)+1;
            Pago p = new Pago(vid);
            System.out.println("p-->1");
            p.setClienteIdcliente(selected);
            System.out.println("p-->2");
            p.setContratoIdcontrato(vcontrato);

             System.out.println("p-->3");
            p.setFecha(d);
            p.setAniomes(this.vcuota.getIdcuota());
             System.out.println("p-->4");

            p.setNumCuota(pagadas.intValue());
            p.setDescripcion("Pago cuota :"+pagadas.intValue()+" contrato #"+vcontrato.getIdcontrato());
            p.setValor(valorCuota);
            Compania comp =companiaFacade.find(1);
            System.out.println("compania ---->"+comp);
            System.out.println("iva ---->"+comp.getIva());
            System.out.println("Cescc ---->"+comp.getCescc());
            BigDecimal iva  = valorCuota.multiply(comp.getIva());
            BigDecimal cescc  = valorCuota.multiply(comp.getCescc());
            p.setIva(iva);
            p.setCescc(cescc);            
            p.setTotal(valorCuota.add(cescc).add(iva));           
            System.out.println("iva ---->"+iva);
             System.out.println("cescc ---->"+cescc);
             System.out.println("total ---->"+p.getTotal());
            this.vcontrato.getPagoList().add(p);
            vcontrato.setCuotasPagadas(pagadas);
            vcontrato.setUltimoPago(new Date());
            vcontrato.setSaldo(vcontrato.getSaldo().subtract(valorCuota));
               p.setCuota(vcuota);
            System.out.println("////"+vdocumento);
            p.setNumPreimpreso(vdocumento);
            this.contratoFacade.edit(vcontrato);
         
            pagoFacade.edit(p);  
            
            consultaPago();
            JsfUtil.addSuccessMessage("Pago realizado correctamente");   
                  reciboPago(p) ;
        }catch(Exception ex){
            System.out.println("error-->"+ex.getMessage());
             System.out.println("error-->"+ex.getCause());
            JsfUtil.addErrorMessage("Surgio un error "+ex);   
        }
        
        
  
    }
    
    public void consultaPago(){
        valorCuota = vcontrato.getValorCuota();
       vcontrato.setPagoList(pagoFacade.findByIdContrato(vcontrato));  
       
       lcuota = sb_GenerarCuotas.generarCuotas(vcontrato);

       
       vcuota =null;
       
       

    }
     
    public String actualizaValor(){
        valorCuota = vcontrato.getValorCuota();
         lcuota = sb_GenerarCuotas.generarCuotas(vcontrato);
        return "ok";
    }
    
    
    public String reciboPago(Pago p)  throws NamingException, SQLException, JRException, IOException{  
        
        HashMap params = new HashMap(); 
       // params.put("SUBREPORT_DIR", "/reportes/");
        params.put("idPago",p.getIdpagos() ); 
        System.out.println("idPago*-->"+p.getIdpagos());
        reportes.GenerarReporte("/reportes/reciboPago.jasper", params);
        
        return "";           
    } 
    
    public String mesPago(String v1,String v2) {
        return JsfUtil.mesLetras(v2)+"/"+v1;
    }
    
 public String imprimirContrato() {
        try {
           
            HashMap params = new HashMap();
            params.put("idContrato",vcontrato.getIdcontrato());
             
            reportes.GenerarReporte("/ordentrabajo/reportes/contrato.jasper", params);
        } catch (Exception e) {
            e.printStackTrace();
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage("Error", new FacesMessage("Error en rptOrdenTrabajo " + e.getMessage()));
        }
        return "Ok";
    }      
}

