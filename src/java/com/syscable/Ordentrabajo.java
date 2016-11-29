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
@Table(name = "ordentrabajo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ordentrabajo.findAll", query = "SELECT o FROM Ordentrabajo o"),
    @NamedQuery(name = "Ordentrabajo.findByIdordenTrabajo", query = "SELECT o FROM Ordentrabajo o WHERE o.idordenTrabajo = :idordenTrabajo"),
    @NamedQuery(name = "Ordentrabajo.findByIdCliente", query = "SELECT o FROM Ordentrabajo o WHERE o.clienteIdcliente.idcliente  = :idcliente"),
    @NamedQuery(name = "Ordentrabajo.findByDescripcion", query = "SELECT o FROM Ordentrabajo o WHERE o.descripcion = :descripcion"),
    @NamedQuery(name = "Ordentrabajo.findByFechaIng", query = "SELECT o FROM Ordentrabajo o WHERE o.fechaIng = :fechaIng"),
    @NamedQuery(name = "Ordentrabajo.findByFechaFin", query = "SELECT o FROM Ordentrabajo o WHERE o.fechaFin = :fechaFin"),
    @NamedQuery(name = "Ordentrabajo.findByDescripcionSolucion", query = "SELECT o FROM Ordentrabajo o WHERE o.descripcionSolucion = :descripcionSolucion"),
    @NamedQuery(name = "Ordentrabajo.findByEstado", query = "SELECT o FROM Ordentrabajo o WHERE o.estado = :estado"),
    @NamedQuery(name = "Ordentrabajo.findByUserCreate", query = "SELECT o FROM Ordentrabajo o WHERE o.userCreate = :userCreate"),
    @NamedQuery(name = "Ordentrabajo.findByDateCreate", query = "SELECT o FROM Ordentrabajo o WHERE o.dateCreate = :dateCreate"),
    @NamedQuery(name = "Ordentrabajo.findByUserMod", query = "SELECT o FROM Ordentrabajo o WHERE o.userMod = :userMod"),
    @NamedQuery(name = "Ordentrabajo.findByDateMod", query = "SELECT o FROM Ordentrabajo o WHERE o.dateMod = :dateMod"),
    @NamedQuery(name = "Ordentrabajo.findByValor", query = "SELECT o FROM Ordentrabajo o WHERE o.valor = :valor")})
public class Ordentrabajo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idordenTrabajo")
    private Integer idordenTrabajo;
    @Size(max = 150)
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "fecha_ing")
    @Temporal(TemporalType.DATE)
    private Date fechaIng;
    @Column(name = "fecha_fin")
    @Temporal(TemporalType.DATE)
    private Date fechaFin;
    @Size(max = 150)
    @Column(name = "descripcionSolucion")
    private String descripcionSolucion;
    @Size(max = 45)
    @Column(name = "estado")
    private String estado;
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
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "valor")
    private BigDecimal valor;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ordenTrabajoidordenTrabajo")
    private List<Pago> pagoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ordenTrabajoidordenTrabajo")
    private List<Ordenproducto> ordenproductoList;
    @JoinColumn(name = "tipoOrden_idtipoOrden", referencedColumnName = "idtipoOrden")
    @ManyToOne(optional = false)
    private Tipoorden tipoOrdenidtipoOrden;
    @JoinColumn(name = "tecnico_idtecnico", referencedColumnName = "idtecnico")
    @ManyToOne(optional = false)
    private Tecnico tecnicoIdtecnico;
    @JoinColumn(name = "cliente_idcliente", referencedColumnName = "idcliente")
    @ManyToOne(optional = false)
    private Cliente clienteIdcliente;

    public Ordentrabajo() {
    }

    public Ordentrabajo(Integer idordenTrabajo) {
        this.idordenTrabajo = idordenTrabajo;
    }

    public Integer getIdordenTrabajo() {
        return idordenTrabajo;
    }

    public void setIdordenTrabajo(Integer idordenTrabajo) {
        this.idordenTrabajo = idordenTrabajo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFechaIng() {
        return fechaIng;
    }

    public void setFechaIng(Date fechaIng) {
        this.fechaIng = fechaIng;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getDescripcionSolucion() {
        return descripcionSolucion;
    }

    public void setDescripcionSolucion(String descripcionSolucion) {
        this.descripcionSolucion = descripcionSolucion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
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

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    @XmlTransient
    public List<Pago> getPagoList() {
        return pagoList;
    }

    public void setPagoList(List<Pago> pagoList) {
        this.pagoList = pagoList;
    }

    @XmlTransient
    public List<Ordenproducto> getOrdenproductoList() {
        return ordenproductoList;
    }

    public void setOrdenproductoList(List<Ordenproducto> ordenproductoList) {
        this.ordenproductoList = ordenproductoList;
    }

    public Tipoorden getTipoOrdenidtipoOrden() {
        return tipoOrdenidtipoOrden;
    }

    public void setTipoOrdenidtipoOrden(Tipoorden tipoOrdenidtipoOrden) {
        this.tipoOrdenidtipoOrden = tipoOrdenidtipoOrden;
    }

    public Tecnico getTecnicoIdtecnico() {
        return tecnicoIdtecnico;
    }

    public void setTecnicoIdtecnico(Tecnico tecnicoIdtecnico) {
        this.tecnicoIdtecnico = tecnicoIdtecnico;
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
        hash += (idordenTrabajo != null ? idordenTrabajo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ordentrabajo)) {
            return false;
        }
        Ordentrabajo other = (Ordentrabajo) object;
        if ((this.idordenTrabajo == null && other.idordenTrabajo != null) || (this.idordenTrabajo != null && !this.idordenTrabajo.equals(other.idordenTrabajo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.syscable.Ordentrabajo[ idordenTrabajo=" + idordenTrabajo + " ]";
    }
    
}
