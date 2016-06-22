package com.syscable;

import com.syscable.util.JsfUtil;
import com.syscable.util.JsfUtil.PersistAction;

import java.io.Serializable;
import java.util.Date;

import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@ManagedBean(name = "clienteController")
@SessionScoped
public class ClienteController implements Serializable {

    @EJB
    private com.syscable.ClienteFacade ejbFacade;
    @EJB
    private com.syscable.ProfesionFacade profesionFacade;  
    @EJB
    private com.syscable.ColoniaFacade coloniaFacade;  
    @EJB
    private com.syscable.NacionalidadFacade nacionalidadFacade; 
    @EJB
    private com.syscable.MunicipioFacade municipioFacade;     
    private List<Cliente> items = null;
    private Cliente selected;
    private String vprofesion;
    private String vcolonia;
    private String vnacionalidad;   
    private List<Municipio> lmunicipios;
    private List<Colonia> lcolonia;    
    private Contrato vcontrato;

    public ClienteController() {
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
        selected.setDepartamentoIddepartamento(d);
        System.out.println("departamento-->"+d);
        initializeEmbeddableKey();
        return selected;
    }

    public void mas(){
        System.out.println("--<<>>");
    }
    
    
    
    public void createColonia() {       
        Colonia c = new Colonia(0);
        c.setNombre(vcolonia);
        c.setDepartamentoIddepartamento(selected.getDepartamentoIddepartamento());
        c.setMunicipioIdmunicipio(selected.getMunicipioIdmunicipio());
        coloniaFacade.edit(c);
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

    public void create() {
        System.out.println("---------1-----");
        System.out.println("------------------");
        System.out.println("---------------------");
        long vid = this.ejbFacade.GenerateId();
        this.selected.setIdcliente((int)vid);
        
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("ClienteCreated"));
        System.out.println("select --->"+selected);
        System.out.println("select --->"+selected.getIdcliente());
      if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
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
        Contrato c= new Contrato(0);
        c.setClienteIdcliente(selected);
        Date d = new Date();
        c.setFecha(d);
        c.setFechainicio(d);
        this.vcontrato = c;
        
    
    }
    
    public void creaContrato(){
        
    
    }

}