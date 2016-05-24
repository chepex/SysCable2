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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author mmixco
 */
@Entity
@Table(name = "contratoproducto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Contratoproducto.findAll", query = "SELECT c FROM Contratoproducto c"),
    @NamedQuery(name = "Contratoproducto.findByIdcontratoProducto", query = "SELECT c FROM Contratoproducto c WHERE c.idcontratoProducto = :idcontratoProducto"),
    @NamedQuery(name = "Contratoproducto.findByValor", query = "SELECT c FROM Contratoproducto c WHERE c.valor = :valor")})
public class Contratoproducto implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idcontratoProducto")
    private Integer idcontratoProducto;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "valor")
    private BigDecimal valor;
    @JoinColumn(name = "producto_idproducto", referencedColumnName = "idproducto")
    @ManyToOne(optional = false)
    private Producto productoIdproducto;
    @JoinColumn(name = "contrato_idcontrato", referencedColumnName = "idcontrato")
    @ManyToOne(optional = false)
    private Contrato contratoIdcontrato;

    public Contratoproducto() {
    }

    public Contratoproducto(Integer idcontratoProducto) {
        this.idcontratoProducto = idcontratoProducto;
    }

    public Integer getIdcontratoProducto() {
        return idcontratoProducto;
    }

    public void setIdcontratoProducto(Integer idcontratoProducto) {
        this.idcontratoProducto = idcontratoProducto;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public Producto getProductoIdproducto() {
        return productoIdproducto;
    }

    public void setProductoIdproducto(Producto productoIdproducto) {
        this.productoIdproducto = productoIdproducto;
    }

    public Contrato getContratoIdcontrato() {
        return contratoIdcontrato;
    }

    public void setContratoIdcontrato(Contrato contratoIdcontrato) {
        this.contratoIdcontrato = contratoIdcontrato;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcontratoProducto != null ? idcontratoProducto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Contratoproducto)) {
            return false;
        }
        Contratoproducto other = (Contratoproducto) object;
        if ((this.idcontratoProducto == null && other.idcontratoProducto != null) || (this.idcontratoProducto != null && !this.idcontratoProducto.equals(other.idcontratoProducto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.syscable.Contratoproducto[ idcontratoProducto=" + idcontratoProducto + " ]";
    }
    
}
