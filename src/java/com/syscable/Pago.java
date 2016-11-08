/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syscable;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "pago")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pago.findAll", query = "SELECT p FROM Pago p"),
    @NamedQuery(name = "Pago.findByIdpagos", query = "SELECT p FROM Pago p WHERE p.idpagos = :idpagos"),
    @NamedQuery(name = "Pago.findByIdContrato", query = "SELECT p FROM Pago p WHERE p.contratoIdcontrato.idcontrato = :idcontrato"),
    @NamedQuery(name = "Pago.findByFecha", query = "SELECT p FROM Pago p WHERE p.fecha = :fecha"),
    @NamedQuery(name = "Pago.findByValor", query = "SELECT p FROM Pago p WHERE p.valor = :valor"),
    @NamedQuery(name = "Pago.findByNumCuota", query = "SELECT p FROM Pago p WHERE p.numCuota = :numCuota"),
    @NamedQuery(name = "Pago.findByUserCreate", query = "SELECT p FROM Pago p WHERE p.userCreate = :userCreate"),
    @NamedQuery(name = "Pago.findByDateCreate", query = "SELECT p FROM Pago p WHERE p.dateCreate = :dateCreate"),
    @NamedQuery(name = "Pago.findByUserMod", query = "SELECT p FROM Pago p WHERE p.userMod = :userMod"),
    @NamedQuery(name = "Pago.findByDateMod", query = "SELECT p FROM Pago p WHERE p.dateMod = :dateMod"),
    @NamedQuery(name = "Pago.findByDescripcion", query = "SELECT p FROM Pago p WHERE p.descripcion = :descripcion"),
    @NamedQuery(name = "Pago.findByDescuento", query = "SELECT p FROM Pago p WHERE p.descuento = :descuento"),
    @NamedQuery(name = "Pago.findByTotal", query = "SELECT p FROM Pago p WHERE p.total = :total")})
public class Pago implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idpagos")
    private Integer idpagos;
    @Size(max = 45)
    @Column(name = "fecha")
    private String fecha;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "valor")
    private BigDecimal valor;
    @Column(name = "num_cuota")
    private Integer numCuota;
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
    @Size(max = 145)
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "descuento")
    private BigDecimal descuento;
    @Column(name = "total")
    private BigDecimal total;
    @JoinColumn(name = "ordenTrabajo_idordenTrabajo", referencedColumnName = "idordenTrabajo")
    @ManyToOne(optional = true)
    private Ordentrabajo ordenTrabajoidordenTrabajo;
    @JoinColumn(name = "contrato_idcontrato", referencedColumnName = "idcontrato")
    @ManyToOne(optional = false)
    private Contrato contratoIdcontrato;
    @JoinColumn(name = "cliente_idcliente", referencedColumnName = "idcliente")
    @ManyToOne(optional = false)
    private Cliente clienteIdcliente;

    public Pago() {
    }

    public Pago(Integer idpagos) {
        this.idpagos = idpagos;
    }

    public Integer getIdpagos() {
        return idpagos;
    }

    public void setIdpagos(Integer idpagos) {
        this.idpagos = idpagos;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public Integer getNumCuota() {
        return numCuota;
    }

    public void setNumCuota(Integer numCuota) {
        this.numCuota = numCuota;
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public BigDecimal getDescuento() {
        return descuento;
    }

    public void setDescuento(BigDecimal descuento) {
        this.descuento = descuento;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public Ordentrabajo getOrdenTrabajoidordenTrabajo() {
        return ordenTrabajoidordenTrabajo;
    }

    public void setOrdenTrabajoidordenTrabajo(Ordentrabajo ordenTrabajoidordenTrabajo) {
        this.ordenTrabajoidordenTrabajo = ordenTrabajoidordenTrabajo;
    }

    public Contrato getContratoIdcontrato() {
        return contratoIdcontrato;
    }

    public void setContratoIdcontrato(Contrato contratoIdcontrato) {
        this.contratoIdcontrato = contratoIdcontrato;
    }

    public Cliente getClienteIdcliente() {
        return clienteIdcliente;
    }

    public void setClienteIdcliente(Cliente clienteIdcliente) {
        this.clienteIdcliente = clienteIdcliente;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idpagos != null ? idpagos.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pago)) {
            return false;
        }
        Pago other = (Pago) object;
        if ((this.idpagos == null && other.idpagos != null) || (this.idpagos != null && !this.idpagos.equals(other.idpagos))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.syscable.Pago[ idpagos=" + idpagos + " ]";
    }
    
}

