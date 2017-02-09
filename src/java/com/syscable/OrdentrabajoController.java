package com.syscable;

import com.syscable.util.JsfUtil;
import com.syscable.util.JsfUtil.PersistAction;
import java.io.Serializable;
import java.math.BigDecimal;
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
import org.primefaces.event.RowEditEvent;

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
    private Date fecha;
    private String numOrden1,numOrden2,noCliente;
    private int totalOrden = 0;
    //</editor-fold>

    public OrdentrabajoController() {
    }

    //<editor-fold defaultstate="collapsed" desc="Getter & Setter">

    public int getTotalOrden() {
        return totalOrden;
    }

    public void setTotalOrden(int totalOrden) {
        this.totalOrden = totalOrden;
    }

    public String getNoCliente() {
        return noCliente;
    }

    public void setNoCliente(String noCliente) {
        this.noCliente = noCliente;
    }

    public String getNumOrden1() {
        return numOrden1;
    }

    public void setNumOrden1(String numOrden1) {
        this.numOrden1 = numOrden1;
    }

    public String getNumOrden2() {
        return numOrden2;
    }

    public void setNumOrden2(String numOrden2) {
        this.numOrden2 = numOrden2;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    
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
    public void limpiar() {
        fecha = null;
        numOrden1 = null;
        numOrden2 = null;
        noCliente = null;
        items = new ArrayList<>();
        selected = null;
        totalOrden = 0;
    }
    
    public String mostrarHistorialOrden() {
        if (fecha == null) {
            fecha = new Date();
        }
        if (numOrden2 == null || numOrden2.isEmpty()) {
            numOrden2 = numOrden1;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String ssfecha = sdf.format(fecha);
        items = ejbFacade.findByHistorialOrden(ssfecha, numOrden1, numOrden2, noCliente);
        if (items.isEmpty()) {
            JsfUtil.addErrorMessage("No se encontraron registros.");
            return "";
        } else {
            totalOrden = 0;
            totalOrden = items.size();
        }
        return "Ok";
    }
    
    public void onRowEdit(RowEditEvent event) {
        Ordentrabajo ot = (Ordentrabajo) event.getObject();
        
        System.out.println("Orden trabajo N° " + ot.getIdordenTrabajo());
        
        ot.setTecnicoIdtecnico(ot.getTecnicoIdtecnico());
        ot.setEstado(ot.getEstado());
        ot.setDescripcionSolucion(ot.getDescripcionSolucion());
        
        ejbFacade.edit(ot);
        
        JsfUtil.addSuccessMessage("Orden Actualizada.");
        
    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Reportes">
    /**
     * Imprime el reporte de orden de trabajo por cliente.
     * @return msj de ejecución correcta. 
     */
    public String despacharOrden() {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss a");
            HashMap params = new HashMap();
            params.put("idorden",selected.getIdordenTrabajo());
            params.put("fecha",sdf.format(new Date()));
            params.put("hora",dateFormat.format(new Date()));
            reportes.GenerarReporte("/ordentrabajo/reportes/ordenTrabajo.jasper", params);
        } catch (Exception e) {
            e.printStackTrace();
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage("Error", new FacesMessage("Error en rptOrdenTrabajo " + e.getMessage()));
        }
        return "Ok";
    }
    //</editor-fold>
    
}
