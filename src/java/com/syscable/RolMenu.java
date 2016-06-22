/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syscable;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author mmixco
 */
@Entity
@Table(name = "rol_menu")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RolMenu.findAll", query = "SELECT r FROM RolMenu r"),
    @NamedQuery(name = "RolMenu.findByIdrolMenu", query = "SELECT r FROM RolMenu r WHERE r.rolIdrol.idrol = :rol order by r.menuIdmenu.submenu , r.menuIdmenu.correlativo "),
    @NamedQuery(name = "RolMenu.findByActivo", query = "SELECT r FROM RolMenu r WHERE r.activo = :activo"),
    @NamedQuery(name = "RolMenu.findByUserCreate", query = "SELECT r FROM RolMenu r WHERE r.userCreate = :userCreate"),
    @NamedQuery(name = "RolMenu.findByDateCreate", query = "SELECT r FROM RolMenu r WHERE r.dateCreate = :dateCreate"),
    @NamedQuery(name = "RolMenu.findByUserMod", query = "SELECT r FROM RolMenu r WHERE r.userMod = :userMod"),
    @NamedQuery(name = "RolMenu.findByDateMod", query = "SELECT r FROM RolMenu r WHERE r.dateMod = :dateMod")})
public class RolMenu implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idrol_menu")
    private Integer idrolMenu;
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
    @JoinColumn(name = "rol_idrol", referencedColumnName = "idrol")
    @ManyToOne(optional = false)
    private Rol rolIdrol;
    @JoinColumn(name = "menu_idmenu", referencedColumnName = "idmenu")
    @ManyToOne(optional = false)
    private Menu menuIdmenu;

    public RolMenu() {
    }

    public RolMenu(Integer idrolMenu) {
        this.idrolMenu = idrolMenu;
    }

    public Integer getIdrolMenu() {
        return idrolMenu;
    }

    public void setIdrolMenu(Integer idrolMenu) {
        this.idrolMenu = idrolMenu;
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

    public Rol getRolIdrol() {
        return rolIdrol;
    }

    public void setRolIdrol(Rol rolIdrol) {
        this.rolIdrol = rolIdrol;
    }

    public Menu getMenuIdmenu() {
        return menuIdmenu;
    }

    public void setMenuIdmenu(Menu menuIdmenu) {
        this.menuIdmenu = menuIdmenu;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idrolMenu != null ? idrolMenu.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RolMenu)) {
            return false;
        }
        RolMenu other = (RolMenu) object;
        if ((this.idrolMenu == null && other.idrolMenu != null) || (this.idrolMenu != null && !this.idrolMenu.equals(other.idrolMenu))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.syscable.RolMenu[ idrolMenu=" + idrolMenu + " ]";
    }
    
}
