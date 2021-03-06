package com.syscable.util;

import java.security.MessageDigest;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.model.SelectItem;

public class JsfUtil {

    public static SelectItem[] getSelectItems(List<?> entities, boolean selectOne) {
        int size = selectOne ? entities.size() + 1 : entities.size();
        SelectItem[] items = new SelectItem[size];
        int i = 0;
        if (selectOne) {
            items[0] = new SelectItem("", "---");
            i++;
        }
        for (Object x : entities) {
            items[i++] = new SelectItem(x, x.toString());
        }
        return items;
    }

    public static boolean isValidationFailed() {
        return FacesContext.getCurrentInstance().isValidationFailed();
    }

    public static void addErrorMessage(Exception ex, String defaultMsg) {
        String msg = ex.getLocalizedMessage();
        if (msg != null && msg.length() > 0) {
            addErrorMessage(msg);
        } else {
            addErrorMessage(defaultMsg);
        }
    }

    public static void addErrorMessages(List<String> messages) {
        for (String message : messages) {
            addErrorMessage(message);
        }
    }

    public static void addErrorMessage(String msg) {
        FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg);
        FacesContext.getCurrentInstance().addMessage(null, facesMsg);
    }

    public static void addSuccessMessage(String msg) {
        FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg);
        FacesContext.getCurrentInstance().addMessage("successInfo", facesMsg);
    }

    public static String getRequestParameter(String key) {
        return FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get(key);
    }

    public static Object getObjectFromRequestParameter(String requestParameterName, Converter converter, UIComponent component) {
        String theId = JsfUtil.getRequestParameter(requestParameterName);
        return converter.getAsObject(FacesContext.getCurrentInstance(), component, theId);
    }

    public static enum PersistAction {
        CREATE,
        DELETE,
        UPDATE
    }
    
    public static String EncriptadorMD5(String txt) throws Exception {
           MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] b = md.digest(txt.getBytes());
            int size = b.length;
            StringBuffer h = new StringBuffer(size);
        try{
         
            // algoritmo y arreglo md5
            for (int i = 0; i < size; i++) {
                int u = b[i] & 255;
                if (u < 16) {
                    h.append("0" + Integer.toHexString(u));
                } else {
                    h.append(Integer.toHexString(u));
                }
            }
        }catch(Exception ex){
        return "error";
        }
        
        // clave encriptada
        return h.toString();
    }      
    
    public static String  stringLike(String valor){
        if(valor!=null){
            if(valor.length()>0){
                valor = "%"+valor+"%";
            }
        }
        return valor;
    }
    
    public static String mesLetras(String mactual) {
        String mes = null;
        
        switch (mactual) {
            case "01" :
                mes = "Enero";
                break;
            case "02" :
                mes = "Febrero";
                break;
            case "03" :
                mes = "Marzo";
                break;
            case "04" :
                mes = "Abril";
                break;
            case "05" :
                mes = "Mayo";
                break;
            case "06" :
                mes = "Junio";
                break;
            case "07" :
                mes = "Julio";
                break;
            case "08" :
                mes = "Agosto";
                break;
            case "09" :
                mes = "Septiembre";
                break;
            case "10" :
                mes = "Octubre";
                break;
            case "11" :
                mes = "Noviembre";
                break;
            case "12" :
                mes = "Diciembre";
                break;
        }
        return mes;
    }
}
