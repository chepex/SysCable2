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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "menu")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Menu.findAll", query = "SELECT m FROM Menu m"),
    @NamedQuery(name = "Menu.findByIdmenu", query = "SELECT m FROM Menu m WHERE m.idmenu = :idmenu"),
    @NamedQuery(name = "Menu.findByMenu", query = "SELECT m FROM Menu m WHERE m.menu = :menu"),
    @NamedQuery(name = "Menu.findByActivo", query = "SELECT m FROM Menu m WHERE m.activo = :activo"),
    @NamedQuery(name = "Menu.findByUserCreate", query = "SELECT m FROM Menu m WHERE m.userCreate = :userCreate"),
    @NamedQuery(name = "Menu.findByDateCreate", query = "SELECT m FROM Menu m WHERE m.dateCreate = :dateCreate"),
    @NamedQuery(name = "Menu.findByUserMod", query = "SELECT m FROM Menu m WHERE m.userMod = :userMod"),
    @NamedQuery(name = "Menu.findByDateMod", query = "SELECT m FROM Menu m WHERE m.dateMod = :dateMod"),
    @NamedQuery(name = "Menu.findByUrl", query = "SELECT m FROM Menu m WHERE m.url = :url"),
    @NamedQuery(name = "Menu.findBySubmenu", query = "SELECT m FROM Menu m WHERE m.submenu = :submenu"),
    @NamedQuery(name = "Menu.findByCorrelativo", query = "SELECT m FROM Menu m WHERE m.correlativo = :correlativo"),
    @NamedQuery(name = "Menu.findByPrincipal", query = "SELECT m FROM Menu m WHERE m.principal = :principal")})
public class Menu implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idmenu")
    private Integer idmenu;
    @Size(max = 45)
    @Column(name = "menu")
    private String menu;
    @Size(max = 45)
    @Column(name = "activo")
    private String activo;
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
    @Size(max = 45)
    @Column(name = "url")
    private String url;
    @Column(name = "submenu")
    private Integer submenu;
    @Column(name = "correlativo")
    private Integer correlativo;
    @Column(name = "principal")
    private Boolean principal;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "menuIdmenu")
    private List<RolMenu> rolMenuList;
    @JoinColumn(name = "modulo_idmodulo", referencedColumnName = "idmodulo")
    @ManyToOne(optional = false)
    private Modulo moduloIdmodulo;

    public Menu() {
    }

    public Menu(Integer idmenu) {
        this.idmenu = idmenu;
    }

    public Integer getIdmenu() {
        return idmenu;
    }

    public void setIdmenu(Integer idmenu) {
        this.idmenu = idmenu;
    }

    public String getMenu() {
        return menu;
    }

    public void setMenu(String menu) {
        this.menu = menu;
    }

    public String getActivo() {
        return activo;
    }

    public void setActivo(String activo) {
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getSubmenu() {
        return submenu;
    }

    public void setSubmenu(Integer submenu) {
        this.submenu = submenu;
    }

    public Integer getCorrelativo() {
        return correlativo;
    }

    public void setCorrelativo(Integer correlativo) {
        this.correlativo = correlativo;
    }

    public Boolean getPrincipal() {
        return principal;
    }

    public void setPrincipal(Boolean principal) {
        this.principal = principal;
    }

    @XmlTransient
    public List<RolMenu> getRolMenuList() {
        return rolMenuList;
    }

    public void setRolMenuList(List<RolMenu> rolMenuList) {
        this.rolMenuList = rolMenuList;
    }

    public Modulo getModuloIdmodulo() {
        return moduloIdmodulo;
    }

    public void setModuloIdmodulo(Modulo moduloIdmodulo) {
        this.moduloIdmodulo = moduloIdmodulo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idmenu != null ? idmenu.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Menu)) {
            return false;
        }
        Menu other = (Menu) object;
        if ((this.idmenu == null && other.idmenu != null) || (this.idmenu != null && !this.idmenu.equals(other.idmenu))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.syscable.Menu[ idmenu=" + idmenu + " ]";
    }
    
}
