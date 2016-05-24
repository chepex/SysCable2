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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author mmixco
 */
@Entity
@Table(name = "ordenproducto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ordenproducto.findAll", query = "SELECT o FROM Ordenproducto o"),
    @NamedQuery(name = "Ordenproducto.findByIdordenProducto", query = "SELECT o FROM Ordenproducto o WHERE o.idordenProducto = :idordenProducto"),
    @NamedQuery(name = "Ordenproducto.findByOrdenProductocol", query = "SELECT o FROM Ordenproducto o WHERE o.ordenProductocol = :ordenProductocol"),
    @NamedQuery(name = "Ordenproducto.findByPrecio", query = "SELECT o FROM Ordenproducto o WHERE o.precio = :precio")})
public class Ordenproducto implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idordenProducto")
    private Integer idordenProducto;
    @Size(max = 45)
    @Column(name = "ordenProductocol")
    private String ordenProductocol;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "precio")
    private BigDecimal precio;
    @JoinColumn(name = "producto_idproducto", referencedColumnName = "idproducto")
    @ManyToOne(optional = false)
    private Producto productoIdproducto;
    @JoinColumn(name = "ordenTrabajo_idordenTrabajo", referencedColumnName = "idordenTrabajo")
    @ManyToOne(optional = false)
    private Ordentrabajo ordenTrabajoidordenTrabajo;

    public Ordenproducto() {
    }

    public Ordenproducto(Integer idordenProducto) {
        this.idordenProducto = idordenProducto;
    }

    public Integer getIdordenProducto() {
        return idordenProducto;
    }

    public void setIdordenProducto(Integer idordenProducto) {
        this.idordenProducto = idordenProducto;
    }

    public String getOrdenProductocol() {
        return ordenProductocol;
    }

    public void setOrdenProductocol(String ordenProductocol) {
        this.ordenProductocol = ordenProductocol;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public Producto getProductoIdproducto() {
        return productoIdproducto;
    }

    public void setProductoIdproducto(Producto productoIdproducto) {
        this.productoIdproducto = productoIdproducto;
    }

    public Ordentrabajo getOrdenTrabajoidordenTrabajo() {
        return ordenTrabajoidordenTrabajo;
    }

    public void setOrdenTrabajoidordenTrabajo(Ordentrabajo ordenTrabajoidordenTrabajo) {
        this.ordenTrabajoidordenTrabajo = ordenTrabajoidordenTrabajo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idordenProducto != null ? idordenProducto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ordenproducto)) {
            return false;
        }
        Ordenproducto other = (Ordenproducto) object;
        if ((this.idordenProducto == null && other.idordenProducto != null) || (this.idordenProducto != null && !this.idordenProducto.equals(other.idordenProducto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.syscable.Ordenproducto[ idordenProducto=" + idordenProducto + " ]";
    }
    
}
