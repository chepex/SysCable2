/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syscable;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author mmixco
 */
@Entity
@Table(name = "tipoorden")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tipoorden.findAll", query = "SELECT t FROM Tipoorden t"),
    @NamedQuery(name = "Tipoorden.findByIdtipoOrden", query = "SELECT t FROM Tipoorden t WHERE t.idtipoOrden = :idtipoOrden"),
    @NamedQuery(name = "Tipoorden.findByNombre", query = "SELECT t FROM Tipoorden t WHERE t.nombre = :nombre")})
public class Tipoorden implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idtipoOrden")
    private Integer idtipoOrden;
    @Size(max = 45)
    @Column(name = "nombre")
    private String nombre;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tipoOrdenidtipoOrden")
    private List<Ordentrabajo> ordentrabajoList;

    public Tipoorden() {
    }

    public Tipoorden(Integer idtipoOrden) {
        this.idtipoOrden = idtipoOrden;
    }

    public Integer getIdtipoOrden() {
        return idtipoOrden;
    }

    public void setIdtipoOrden(Integer idtipoOrden) {
        this.idtipoOrden = idtipoOrden;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @XmlTransient
    public List<Ordentrabajo> getOrdentrabajoList() {
        return ordentrabajoList;
    }

    public void setOrdentrabajoList(List<Ordentrabajo> ordentrabajoList) {
        this.ordentrabajoList = ordentrabajoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idtipoOrden != null ? idtipoOrden.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tipoorden)) {
            return false;
        }
        Tipoorden other = (Tipoorden) object;
        if ((this.idtipoOrden == null && other.idtipoOrden != null) || (this.idtipoOrden != null && !this.idtipoOrden.equals(other.idtipoOrden))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.syscable.Tipoorden[ idtipoOrden=" + idtipoOrden + " ]";
    }
    
}
