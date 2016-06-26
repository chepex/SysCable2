package com.syscable;

import com.syscable.util.JsfUtil;
import com.syscable.util.JsfUtil.PersistAction;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
    private List<Ordentrabajo> items = null;
    private Ordentrabajo selected;
    private Cliente cliente;
    private Date fechaOrden;
    private String ordenId,falla,horaOrden,vitacora;
    //</editor-fold>

    public OrdentrabajoController() {
    }

    //<editor-fold defaultstate="collapsed" desc="Getter & Setter">

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    
    public String getVitacora() {
        return vitacora;
    }

    public void setVitacora(String vitacora) {
        this.vitacora = vitacora;
    }
    
    public Date getFechaOrden() {
        return fechaOrden;
    }

    public void setFechaOrden(Date fechaOrden) {
        this.fechaOrden = fechaOrden;
    }

    public String getHoraOrden() {
        return horaOrden;
    }

    public void setHoraOrden(String horaOrden) {
        this.horaOrden = horaOrden;
    }

    public String getOrdenId() {
        return ordenId;
    }

    public void setOrdenId(String ordenId) {
        this.ordenId = ordenId;
    }

    public String getFalla() {
        return falla;
    }

    public void setFalla(String falla) {
        this.falla = falla;
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

    public Ordentrabajo prepareCreate() {
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
    /**
     * Limpia la información en pantalla.
     */
    public void limpiar() {
        selected = null;
        cliente = null;
        ordenId = null;
        falla = null;
        vitacora = null;
        items = new ArrayList<>();
    }
    
    /**
     * Muestra el historial de orden de trabajo por cliente.
     */
    public void historialOrden() {
        items = new ArrayList<>();
        if (cliente != null) {
            DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss a");
            items = ejbFacade.findByCliente(String.valueOf(cliente.getIdcliente()));
            if (items.isEmpty()) {
                JsfUtil.addErrorMessage("No se encontraron registros.");
            } else {
                for (Ordentrabajo item : items) {
                    this.setFechaOrden(item.getFechaIng());
                    horaOrden = dateFormat.format(item.getFechaIng());
                    this.setHoraOrden(horaOrden);
                    cliente.setIdcliente(item.getClienteIdcliente().getIdcliente());
                    cliente.setNombres(item.getClienteIdcliente().getNombres());
                    cliente.setDirInstalacion(item.getClienteIdcliente().getDirInstalacion());
                    cliente.setTelefono(item.getClienteIdcliente().getTelefono());
                    this.setFalla(item.getDescripcion());
                    this.setVitacora(item.getDescripcionSolucion());
                }
            }
        } else {
            JsfUtil.addErrorMessage("No se encuentro un cliente valido, no se puede ver el historial.");
        }
    }
    
    /**
     * Asigna el valor de la vitacora del cliente.
     */
    public void vitocora_x_historialCliente() {
        if (selected != null) {
            this.setVitacora(selected.getDescripcionSolucion());
        }
    }
    
    /**
     * Guarda la vitacora del cliente.
     */
    public void guardarVitacoraCliente() {
        if (vitacora != null && !vitacora.isEmpty()) {
            selected.setDescripcionSolucion(vitacora);
            ejbFacade.edit(selected);
        } else {
            JsfUtil.addErrorMessage("No tiene informacion disponible en la vitacora para guardar.");
        }
    }
    
    /**
     * Guarda la orden de trabajo a despachar del cliente.
     */
    public void guardarOrdenTrabajo() {
        try {
            DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss a");
            fechaOrden = new Date();
            Ordentrabajo o = new Ordentrabajo();
            ordenId = String.valueOf(ejbFacade.findByMaxOrdenId());
            o.setIdordenTrabajo(Integer.parseInt(ordenId));
            o.setFechaIng(fechaOrden);
            this.setFechaOrden(fechaOrden);
            this.setHoraOrden(dateFormat.format(fechaOrden));
            o.getClienteIdcliente().setIdcliente(cliente.getIdcliente());
            o.getClienteIdcliente().setNombres(cliente.getNombres());
            o.getClienteIdcliente().setDirInstalacion(cliente.getDirInstalacion());
            o.getClienteIdcliente().setTelefono(cliente.getTelefono());
            o.setDescripcion(falla);
            o.setEstado("P");
            o.setDateCreate(new Date());
            
            ejbFacade.edit(o);
            
            JsfUtil.addSuccessMessage("Orden de trabajo " + ordenId + " guardada correctamente!!!");
            
            items = ejbFacade.findByOrdenId(ordenId);
            
        } catch (Exception e) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage("Error", new FacesMessage("Error en guardarOrdenTrabajo " + e.getMessage()));
        }
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Reportes">
    /**
     * Imprime el reporte de orden de trabajo por cliente.
     * @return msj de ejecución correcta. 
     */
    public String rptOrdenTrabajo() {
        try {
            String url = "/ordentrabajo/reporte/ordenTrabajo.jasper";
        } catch (Exception e) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage("Error", new FacesMessage("Error en rptOrdenTrabajo " + e.getMessage()));
        }
        return "Ok";
    }
    //</editor-fold>
    
}
