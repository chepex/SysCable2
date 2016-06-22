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
@Table(name = "nacionalidad")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Nacionalidad.findAll", query = "SELECT n FROM Nacionalidad n"),
    @NamedQuery(name = "Nacionalidad.findByIdnacionalidad", query = "SELECT n FROM Nacionalidad n WHERE n.idnacionalidad = :idnacionalidad"),
    @NamedQuery(name = "Nacionalidad.findByNombre", query = "SELECT n FROM Nacionalidad n WHERE n.nombre = :nombre")})
public class Nacionalidad implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idnacionalidad")
    private Integer idnacionalidad;
    @Size(max = 45)
    @Column(name = "nombre")
    private String nombre;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "nacionalidadIdnacionalidad")
    private List<Cliente> clienteList;

    public Nacionalidad() {
    }

    public Nacionalidad(Integer idnacionalidad) {
        this.idnacionalidad = idnacionalidad;
    }

    public Integer getIdnacionalidad() {
        return idnacionalidad;
    }

    public void setIdnacionalidad(Integer idnacionalidad) {
        this.idnacionalidad = idnacionalidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @XmlTransient
    public List<Cliente> getClienteList() {
        return clienteList;
    }

    public void setClienteList(List<Cliente> clienteList) {
        this.clienteList = clienteList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idnacionalidad != null ? idnacionalidad.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Nacionalidad)) {
            return false;
        }
        Nacionalidad other = (Nacionalidad) object;
        if ((this.idnacionalidad == null && other.idnacionalidad != null) || (this.idnacionalidad != null && !this.idnacionalidad.equals(other.idnacionalidad))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.syscable.Nacionalidad[ idnacionalidad=" + idnacionalidad + " ]";
    }
    
}
