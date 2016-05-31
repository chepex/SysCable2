/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syscable;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author chepe
 */
@Entity
@Table(name = "contrato")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Contrato.findAll", query = "SELECT c FROM Contrato c"),
    @NamedQuery(name = "Contrato.findByIdcontrato", query = "SELECT c FROM Contrato c WHERE c.idcontrato = :idcontrato"),
    @NamedQuery(name = "Contrato.findByFecha", query = "SELECT c FROM Contrato c WHERE c.fecha = :fecha"),
    @NamedQuery(name = "Contrato.findByEstado", query = "SELECT c FROM Contrato c WHERE c.estado = :estado"),
    @NamedQuery(name = "Contrato.findByFechainicio", query = "SELECT c FROM Contrato c WHERE c.fechainicio = :fechainicio"),
    @NamedQuery(name = "Contrato.findByFechafin", query = "SELECT c FROM Contrato c WHERE c.fechafin = :fechafin"),
    @NamedQuery(name = "Contrato.findByValorTotal", query = "SELECT c FROM Contrato c WHERE c.valorTotal = :valorTotal"),
    @NamedQuery(name = "Contrato.findByValorCuota", query = "SELECT c FROM Contrato c WHERE c.valorCuota = :valorCuota"),
    @NamedQuery(name = "Contrato.findByCuotas", query = "SELECT c FROM Contrato c WHERE c.cuotas = :cuotas"),
    @NamedQuery(name = "Contrato.findBySaldo", query = "SELECT c FROM Contrato c WHERE c.saldo = :saldo"),
    @NamedQuery(name = "Contrato.findByCuotasPagadas", query = "SELECT c FROM Contrato c WHERE c.cuotasPagadas = :cuotasPagadas"),
    @NamedQuery(name = "Contrato.findByUltimoPago", query = "SELECT c FROM Contrato c WHERE c.ultimoPago = :ultimoPago"),
    @NamedQuery(name = "Contrato.findByUserCreate", query = "SELECT c FROM Contrato c WHERE c.userCreate = :userCreate"),
    @NamedQuery(name = "Contrato.findByDateCreate", query = "SELECT c FROM Contrato c WHERE c.dateCreate = :dateCreate"),
    @NamedQuery(name = "Contrato.findByUserMod", query = "SELECT c FROM Contrato c WHERE c.userMod = :userMod"),
    @NamedQuery(name = "Contrato.findByDateMod", query = "SELECT c FROM Contrato c WHERE c.dateMod = :dateMod")})
public class Contrato implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idcontrato")
    private Integer idcontrato;
    @Size(max = 45)
    @Column(name = "fecha")
    private String fecha;
    @Size(max = 45)
    @Column(name = "estado")
    private String estado;
    @Column(name = "fechainicio")
    @Temporal(TemporalType.DATE)
    private Date fechainicio;
    @Column(name = "fechafin")
    @Temporal(TemporalType.DATE)
    private Date fechafin;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "valor_total")
    private BigDecimal valorTotal;
    @Column(name = "valor_cuota")
    private BigDecimal valorCuota;
    @Column(name = "cuotas")
    private BigDecimal cuotas;
    @Column(name = "saldo")
    private BigDecimal saldo;
    @Column(name = "cuotas_pagadas")
    private BigDecimal cuotasPagadas;
    @Column(name = "ultimoPago")
    @Temporal(TemporalType.DATE)
    private Date ultimoPago;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "contratoIdcontrato")
    private List<Pago> pagoList;
    @JoinColumn(name = "cliente_idcliente", referencedColumnName = "idcliente")
    @ManyToOne(optional = false)
    private Cliente clienteIdcliente;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "contratoIdcontrato")
    private List<Contratoproducto> contratoproductoList;

    public Contrato() {
    }

    public Contrato(Integer idcontrato) {
        this.idcontrato = idcontrato;
    }

    public Integer getIdcontrato() {
        return idcontrato;
    }

    public void setIdcontrato(Integer idcontrato) {
        this.idcontrato = idcontrato;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Date getFechainicio() {
        return fechainicio;
    }

    public void setFechainicio(Date fechainicio) {
        this.fechainicio = fechainicio;
    }

    public Date getFechafin() {
        return fechafin;
    }

    public void setFechafin(Date fechafin) {
        this.fechafin = fechafin;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public BigDecimal getValorCuota() {
        return valorCuota;
    }

    public void setValorCuota(BigDecimal valorCuota) {
        this.valorCuota = valorCuota;
    }

    public BigDecimal getCuotas() {
        return cuotas;
    }

    public void setCuotas(BigDecimal cuotas) {
        this.cuotas = cuotas;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    public BigDecimal getCuotasPagadas() {
        return cuotasPagadas;
    }

    public void setCuotasPagadas(BigDecimal cuotasPagadas) {
        this.cuotasPagadas = cuotasPagadas;
    }

    public Date getUltimoPago() {
        return ultimoPago;
    }

    public void setUltimoPago(Date ultimoPago) {
        this.ultimoPago = ultimoPago;
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
    public List<Pago> getPagoList() {
        return pagoList;
    }

    public void setPagoList(List<Pago> pagoList) {
        this.pagoList = pagoList;
    }

    public Cliente getClienteIdcliente() {
        return clienteIdcliente;
    }

    public void setClienteIdcliente(Cliente clienteIdcliente) {
        this.clienteIdcliente = clienteIdcliente;
    }

    @XmlTransient
    public List<Contratoproducto> getContratoproductoList() {
        return contratoproductoList;
    }

    public void setContratoproductoList(List<Contratoproducto> contratoproductoList) {
        this.contratoproductoList = contratoproductoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcontrato != null ? idcontrato.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Contrato)) {
            return false;
        }
        Contrato other = (Contrato) object;
        if ((this.idcontrato == null && other.idcontrato != null) || (this.idcontrato != null && !this.idcontrato.equals(other.idcontrato))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entities.Contrato[ idcontrato=" + idcontrato + " ]";
    }
    
}
