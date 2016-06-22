/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syscable;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author mmixco
 */
@Entity
@Table(name = "modulo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Modulo.findAll", query = "SELECT m FROM Modulo m"),
    @NamedQuery(name = "Modulo.findByIdmodulo", query = "SELECT m FROM Modulo m WHERE m.idmodulo = :idmodulo"),
    @NamedQuery(name = "Modulo.findByModulo", query = "SELECT m FROM Modulo m WHERE m.modulo = :modulo"),
    @NamedQuery(name = "Modulo.findByActivo", query = "SELECT m FROM Modulo m WHERE m.activo = :activo"),
    @NamedQuery(name = "Modulo.findByUserCreate", query = "SELECT m FROM Modulo m WHERE m.userCreate = :userCreate"),
    @NamedQuery(name = "Modulo.findByDateCreate", query = "SELECT m FROM Modulo m WHERE m.dateCreate = :dateCreate"),
    @NamedQuery(name = "Modulo.findByUserMod", query = "SELECT m FROM Modulo m WHERE m.userMod = :userMod"),
    @NamedQuery(name = "Modulo.findByDateMod", query = "SELECT m FROM Modulo m WHERE m.dateMod = :dateMod")})
public class Modulo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idmodulo")
    private Integer idmodulo;
    @Size(max = 45)
    @Column(name = "modulo")
    private String modulo;
    @Column(name = "activo")
    private Boolean activo;
    @Size(max = 45)
    @Column(name = "user_create")
    private String userCreate;
    @Column(name = "date_create")
    @Temporal(TemporalType.DATE)
    private Date dateCreate;
    @Size(max = 45)
    @Column(name = "user_mod")
    private String userMod;
    @Column(name = "date_mod")
    @Temporal(TemporalType.DATE)
    private Date dateMod;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "moduloIdmodulo")
    private List<Menu> menuList;

    public Modulo() {
    }

    public Modulo(Integer idmodulo) {
        this.idmodulo = idmodulo;
    }

    public Integer getIdmodulo() {
        return idmodulo;
    }

    public void setIdmodulo(Integer idmodulo) {
        this.idmodulo = idmodulo;
    }

    public String getModulo() {
        return modulo;
    }

    public void setModulo(String modulo) {
        this.modulo = modulo;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public String getUserCreate() {
        return userCreate;
    }

    public void setUserCreate(String userCreate) {
        this.userCreate = userCreate;
    }

    public Date getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(Date dateCreate) {
        this.dateCreate = dateCreate;
    }

    public String getUserMod() {
        return userMod;
    }

    public void setUserMod(String userMod) {
        this.userMod = userMod;
    }

    public Date getDateMod() {
        return dateMod;
    }

    public void setDateMod(Date dateMod) {
        this.dateMod = dateMod;
    }

    @XmlTransient
    public List<Menu> getMenuList() {
        return menuList;
    }

    public void setMenuList(List<Menu> menuList) {
        this.menuList = menuList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idmodulo != null ? idmodulo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Modulo)) {
            return false;
        }
        Modulo other = (Modulo) object;
        if ((this.idmodulo == null && other.idmodulo != null) || (this.idmodulo != null && !this.idmodulo.equals(other.idmodulo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.syscable.Modulo[ idmodulo=" + idmodulo + " ]";
    }
    
}
