/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.syscable;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "cuota")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cuota.findAll", query = "SELECT c FROM Cuota c"),
    @NamedQuery(name = "Cuota.findByIdcuota", query = "SELECT c FROM Cuota c WHERE c.idcuota = :idcuota"),
    @NamedQuery(name = "Cuota.findByAnio", query = "SELECT c FROM Cuota c WHERE c.anio = :anio"),
    @NamedQuery(name = "Cuota.findByAnioMes", query = "SELECT c FROM Cuota c WHERE c.anio = :anio and c.mes = :mes"),
    @NamedQuery(name = "Cuota.findByAnioMes12", query = "SELECT c FROM Cuota c WHERE c.idcuota between  :cuota1 and  :cuota2 order by c.idcuota"),    
    @NamedQuery(name = "Cuota.findByMes", query = "SELECT c FROM Cuota c WHERE c.mes = :mes"),
    @NamedQuery(name = "Cuota.findByNombre", query = "SELECT c FROM Cuota c WHERE c.nombre = :nombre")})
public class Cuota implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idcuota")
    private Integer idcuota;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 4)
    @Column(name = "anio")
    private String anio;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "mes")
    private String mes;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 6)
    @Column(name = "nombre")
    private String nombre;

    public Cuota() {
    }

    public Cuota(Integer idcuota) {
        this.idcuota = idcuota;
    }

    public Cuota(Integer idcuota, String anio, String mes, String nombre) {
        this.idcuota = idcuota;
        this.anio = anio;
        this.mes = mes;
        this.nombre = nombre;
    }

    public Integer getIdcuota() {
        return idcuota;
    }

    public void setIdcuota(Integer idcuota) {
        this.idcuota = idcuota;
    }

    public String getAnio() {
        return anio;
    }

    public void setAnio(String anio) {
        this.anio = anio;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcuota != null ? idcuota.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cuota)) {
            return false;
        }
        Cuota other = (Cuota) object;
        if ((this.idcuota == null && other.idcuota != null) || (this.idcuota != null && !this.idcuota.equals(other.idcuota))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entities.Cuota[ idcuota=" + idcuota + " ]";
    }
    
}
