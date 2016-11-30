/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syscable;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author mmixco
 */
@Entity
@Table(name = "compania")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Compania.findAll", query = "SELECT c FROM Compania c"),
    @NamedQuery(name = "Compania.findByIdcompania", query = "SELECT c FROM Compania c WHERE c.idcompania = :idcompania"),
    @NamedQuery(name = "Compania.findByNombre", query = "SELECT c FROM Compania c WHERE c.nombre = :nombre"),
    @NamedQuery(name = "Compania.findByNombreComercial", query = "SELECT c FROM Compania c WHERE c.nombreComercial = :nombreComercial"),
    @NamedQuery(name = "Compania.findByDireccion", query = "SELECT c FROM Compania c WHERE c.direccion = :direccion"),
    @NamedQuery(name = "Compania.findByTelefono", query = "SELECT c FROM Compania c WHERE c.telefono = :telefono"),
    @NamedQuery(name = "Compania.findByNit", query = "SELECT c FROM Compania c WHERE c.nit = :nit"),
    @NamedQuery(name = "Compania.findByGiro", query = "SELECT c FROM Compania c WHERE c.giro = :giro"),
    @NamedQuery(name = "Compania.findByRegistroIva", query = "SELECT c FROM Compania c WHERE c.registroIva = :registroIva"),
    @NamedQuery(name = "Compania.findByIva", query = "SELECT c FROM Compania c WHERE c.iva = :iva")})
public class Compania implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idcompania")
    private Integer idcompania;
    @Size(max = 80)
    @Column(name = "nombre")
    private String nombre;
    @Size(max = 80)
    @Column(name = "nombre_comercial")
    private String nombreComercial;
    @Size(max = 80)
    @Column(name = "direccion")
    private String direccion;
    @Size(max = 80)
    @Column(name = "telefono")
    private String telefono;
    @Size(max = 80)
    @Column(name = "nit")
    private String nit;
    @Size(max = 80)
    @Column(name = "giro")
    private String giro;
    @Size(max = 80)
    @Column(name = "registro_iva")
    private String registroIva;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "iva")
    private BigDecimal iva;
    @Column(name = "cescc")
    private BigDecimal cescc;    
       
    public Compania() {
    }
    
    public BigDecimal getCescc() {
        return cescc;
    }
    public void setCescc(BigDecimal cescc) {
        this.cescc = cescc;
    }
    
    public Compania(Integer idcompania) {
        this.idcompania = idcompania;
    }

    public Integer getIdcompania() {
        return idcompania;
    }

    public void setIdcompania(Integer idcompania) {
        this.idcompania = idcompania;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombreComercial() {
        return nombreComercial;
    }

    public void setNombreComercial(String nombreComercial) {
        this.nombreComercial = nombreComercial;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getGiro() {
        return giro;
    }

    public void setGiro(String giro) {
        this.giro = giro;
    }

    public String getRegistroIva() {
        return registroIva;
    }

    public void setRegistroIva(String registroIva) {
        this.registroIva = registroIva;
    }

    public BigDecimal getIva() {
        return iva;
    }

    public void setIva(BigDecimal iva) {
        this.iva = iva;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcompania != null ? idcompania.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Compania)) {
            return false;
        }
        Compania other = (Compania) object;
        if ((this.idcompania == null && other.idcompania != null) || (this.idcompania != null && !this.idcompania.equals(other.idcompania))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entities.Compania[ idcompania=" + idcompania + " ]";
    }
    
}
