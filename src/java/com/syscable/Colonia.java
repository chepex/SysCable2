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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "colonia")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Colonia.findAll", query = "SELECT c FROM Colonia c"),
    @NamedQuery(name = "Colonia.findByIdcolonia", query = "SELECT c FROM Colonia c WHERE c.idcolonia = :idcolonia"),
    @NamedQuery(name = "Colonia.findByNombre", query = "SELECT c FROM Colonia c WHERE c.nombre = :nombre")})
public class Colonia implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idcolonia")
    private Integer idcolonia;
    @Size(max = 45)
    @Column(name = "nombre")
    private String nombre;
    @JoinColumn(name = "municipio_idmunicipio", referencedColumnName = "idmunicipio")
    @ManyToOne(optional = false)
    private Municipio municipioIdmunicipio;
    @JoinColumn(name = "departamento_iddepartamento", referencedColumnName = "iddepartamento")
    @ManyToOne(optional = false)
    private Departamento departamentoIddepartamento;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "coloniaIdcolonia")
    private List<Cliente> clienteList;

    public Colonia() {
    }

    public Colonia(Integer idcolonia) {
        this.idcolonia = idcolonia;
    }

    public Integer getIdcolonia() {
        return idcolonia;
    }

    public void setIdcolonia(Integer idcolonia) {
        this.idcolonia = idcolonia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Municipio getMunicipioIdmunicipio() {
        return municipioIdmunicipio;
    }

    public void setMunicipioIdmunicipio(Municipio municipioIdmunicipio) {
        this.municipioIdmunicipio = municipioIdmunicipio;
    }

    public Departamento getDepartamentoIddepartamento() {
        return departamentoIddepartamento;
    }

    public void setDepartamentoIddepartamento(Departamento departamentoIddepartamento) {
        this.departamentoIddepartamento = departamentoIddepartamento;
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
        hash += (idcolonia != null ? idcolonia.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Colonia)) {
            return false;
        }
        Colonia other = (Colonia) object;
        if ((this.idcolonia == null && other.idcolonia != null) || (this.idcolonia != null && !this.idcolonia.equals(other.idcolonia))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.syscable.Colonia[ idcolonia=" + idcolonia + " ]";
    }
    
}
