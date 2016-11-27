package com.syscable;

import com.syscable.util.JsfUtil;
import com.syscable.util.JsfUtil.PersistAction;
import java.io.Serializable;
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
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@ManagedBean(name = "ordentrabajoController")
@SessionScoped
public class OrdentrabajoController implements Serializable {
    
    //<editor-fold defaultstate="collapsed" desc="Variables">
    @EJB
    private com.syscable.OrdentrabajoFacade ejbFacade;
    @EJB
    private com.ejb.SB_Reportes reportes;
    private List<Ordentrabajo> items =  new ArrayList<>();
    List<Ordentrabajo> lordenes =  new ArrayList<>();
    private Ordentrabajo selected;
    //</editor-fold>

    public OrdentrabajoController() {
    }

    //<editor-fold defaultstate="collapsed" desc="Getter & Setter">
    
    public List<Ordentrabajo> getLordenes() {
        return lordenes;
    }

    public void setLordenes(List<Ordentrabajo> lordenes) {
        this.lordenes = lordenes;
    }
    
    public Ordentrabajo getSelected() {
        return selected;
    }

    public void setSelected(Ordentrabajo selected) {
        this.selected = selected;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Eventos Controlador">
    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private OrdentrabajoFacade getFacade() {
        return ejbFacade;
    }

    public Ordentrabajo prepareCreate(Cliente vcliente) {
     
        selected = new Ordentrabajo();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("OrdentrabajoCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("OrdentrabajoUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("OrdentrabajoDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Ordentrabajo> getItems() {
        /*if (items == null) {
            items = getFacade().findAll();
        }*/
        return items;
    }

    
    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
                    getFacade().edit(selected);
                } else {
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
                    JsfUtil.addErrorMessage(msg);
                } else {
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            }
        }
    }

    public List<Ordentrabajo> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Ordentrabajo> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Ordentrabajo.class)
    public static class OrdentrabajoControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            OrdentrabajoController controller = (OrdentrabajoController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "ordentrabajoController");
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
            if (object instanceof Ordentrabajo) {
                Ordentrabajo o = (Ordentrabajo) object;
                return getStringKey(o.getIdordenTrabajo());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Ordentrabajo.class.getName()});
                return null;
            }
        }

    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Metodos">
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Reportes">
    /**
     * Imprime el reporte de orden de trabajo por cliente.
     * @return msj de ejecución correcta. 
     */
    public String rptOrdenTrabajo() {
        try {
            if (selected != null) {
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss a");
                HashMap params = new HashMap();
                System.out.println("orden trabajo " + selected.getIdordenTrabajo());
                System.out.println("fecha " + sdf.format(new Date()));
                System.out.println("hora " + dateFormat.format(new Date()));
                params.put("idorden",selected.getIdordenTrabajo());
                params.put("fecha",sdf.format(new Date()));
                params.put("hora",dateFormat.format(new Date()));
                System.out.println("idOrden*-->"+selected.getIdordenTrabajo());
                reportes.GenerarReporte("/ordentrabajo/reporte/ordenTrabajo.jasper", params);
            } else{
                JsfUtil.addErrorMessage("Seleccion una orden de trabajo.");
                return " ";
            }
        } catch (Exception e) {
            System.out.println("que paso!!!");
            e.printStackTrace();
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage("Error", new FacesMessage("Error en rptOrdenTrabajo " + e.getMessage()));
        }
        return "Ok";
    }
    //</editor-fold>
    
}
