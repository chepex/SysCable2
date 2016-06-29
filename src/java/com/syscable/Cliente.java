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
@Table(name = "cliente")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cliente.findAll", query = "SELECT c FROM Cliente c"),
    @NamedQuery(name = "Cliente.findByIdcliente", query = "SELECT c FROM Cliente c WHERE c.idcliente = :idcliente"),
    @NamedQuery(name = "Cliente.findByNombres", query = "SELECT c FROM Cliente c WHERE c.nombres like :nombres or c.apellidos like :apellidos or c.dui like :dui or c.nit like :nit"),
    @NamedQuery(name = "Cliente.findByApellidos", query = "SELECT c FROM Cliente c WHERE c.apellidos = :apellidos"),
    @NamedQuery(name = "Cliente.findByDui", query = "SELECT c FROM Cliente c WHERE c.dui = :dui"),
    @NamedQuery(name = "Cliente.findByNit", query = "SELECT c FROM Cliente c WHERE c.nit = :nit"),
    @NamedQuery(name = "Cliente.findByTelefono", query = "SELECT c FROM Cliente c WHERE c.telefono = :telefono"),
    @NamedQuery(name = "Cliente.findByLugarTrabajo", query = "SELECT c FROM Cliente c WHERE c.lugarTrabajo = :lugarTrabajo"),
    @NamedQuery(name = "Cliente.findByDirTrabajo", query = "SELECT c FROM Cliente c WHERE c.dirTrabajo = :dirTrabajo"),
    @NamedQuery(name = "Cliente.findByTelTrabajo", query = "SELECT c FROM Cliente c WHERE c.telTrabajo = :telTrabajo"),
    @NamedQuery(name = "Cliente.findByNombreConyugue", query = "SELECT c FROM Cliente c WHERE c.nombreConyugue = :nombreConyugue"),
    @NamedQuery(name = "Cliente.findByTrabajoConyugue", query = "SELECT c FROM Cliente c WHERE c.trabajoConyugue = :trabajoConyugue"),
    @NamedQuery(name = "Cliente.findByTelTrabajoConyugue", query = "SELECT c FROM Cliente c WHERE c.telTrabajoConyugue = :telTrabajoConyugue"),
    @NamedQuery(name = "Cliente.findByDirInstalacion", query = "SELECT c FROM Cliente c WHERE c.dirInstalacion = :dirInstalacion"),
    @NamedQuery(name = "Cliente.findByFechaNacimiento", query = "SELECT c FROM Cliente c WHERE c.fechaNacimiento = :fechaNacimiento"),
    @NamedQuery(name = "Cliente.findByEstado", query = "SELECT c FROM Cliente c WHERE c.estado = :estado"),
    @NamedQuery(name = "Cliente.findByEmail", query = "SELECT c FROM Cliente c WHERE c.email = :email"),
    @NamedQuery(name = "Cliente.findByUserCreate", query = "SELECT c FROM Cliente c WHERE c.userCreate = :userCreate"),
    @NamedQuery(name = "Cliente.findByDateCreate", query = "SELECT c FROM Cliente c WHERE c.dateCreate = :dateCreate"),
    @NamedQuery(name = "Cliente.findByUserMod", query = "SELECT c FROM Cliente c WHERE c.userMod = :userMod"),
    @NamedQuery(name = "Cliente.findByDateMod", query = "SELECT c FROM Cliente c WHERE c.dateMod = :dateMod"),
    @NamedQuery(name = "Cliente.findByDirTrabajoConyugue", query = "SELECT c FROM Cliente c WHERE c.dirTrabajoConyugue = :dirTrabajoConyugue")})
public class Cliente implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idcliente")
    private Integer idcliente;
    @Size(max = 45)
    @Column(name = "nombres")
    private String nombres;
    @Size(max = 45)
    @Column(name = "apellidos")
    private String apellidos;
     @Size(max = 14)
    @Column(name = "dui")
    private String dui;
     @Size(max = 14)
    @Column(name = "nit")
    private String nit;
    @Size(max = 45)
    @Column(name = "telefono")
    private String telefono;
    @Size(max = 45)
    @Column(name = "lugar_trabajo")
    private String lugarTrabajo;
    @Size(max = 45)
    @Column(name = "dirTrabajo")
    private String dirTrabajo;
    @Size(max = 45)
    @Column(name = "tel_trabajo")
    private String telTrabajo;
    @Size(max = 45)
    @Column(name = "nombre_conyugue")
    private String nombreConyugue;
    @Size(max = 45)
    @Column(name = "trabajo_conyugue")
    private String trabajoConyugue;
    @Size(max = 45)
    @Column(name = "tel_trabajo_conyugue")
    private String telTrabajoConyugue;
    @Size(max = 250)
    @Column(name = "dirInstalacion")
    private String dirInstalacion;
    @Column(name = "fechaNacimiento")
    @Temporal(TemporalType.DATE)
    private Date fechaNacimiento;
    @Column(name = "estado")
    private Boolean estado;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 45)
    @Column(name = "email")
    private String email;
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
    @Column(name = "dir_trabajo_conyugue")
    private String dirTrabajoConyugue;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "clienteIdcliente")
    private List<Pago> pagoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "clienteIdcliente")
    private List<Contrato> contratoList;
    @JoinColumn(name = "profesion_idprofesion", referencedColumnName = "idprofesion")
    @ManyToOne(optional = false)
    private Profesion profesionIdprofesion;
    @JoinColumn(name = "nacionalidad_idnacionalidad", referencedColumnName = "idnacionalidad")
    @ManyToOne(optional = false)
    private Nacionalidad nacionalidadIdnacionalidad;
    @JoinColumn(name = "municipio_idmunicipio", referencedColumnName = "idmunicipio")
    @ManyToOne(optional = false)
    private Municipio municipioIdmunicipio;
    @JoinColumn(name = "departamento_iddepartamento", referencedColumnName = "iddepartamento")
    @ManyToOne(optional = false)
    private Departamento departamentoIddepartamento;
    @JoinColumn(name = "colonia_idcolonia", referencedColumnName = "idcolonia")
    @ManyToOne(optional = false)
    private Colonia coloniaIdcolonia;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "clienteIdcliente")
    private List<Ordentrabajo> ordentrabajoList;

    public Cliente() {
    }

    public Cliente(Integer idcliente) {
        this.idcliente = idcliente;
    }

    public Integer getIdcliente() {
        return idcliente;
    }

    public void setIdcliente(Integer idcliente) {
        this.idcliente = idcliente;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDui() {
        return dui;
    }

    public void setDui(String dui) {
        this.dui = dui;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }





 

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getLugarTrabajo() {
        return lugarTrabajo;
    }

    public void setLugarTrabajo(String lugarTrabajo) {
        this.lugarTrabajo = lugarTrabajo;
    }

    public String getDirTrabajo() {
        return dirTrabajo;
    }

    public void setDirTrabajo(String dirTrabajo) {
        this.dirTrabajo = dirTrabajo;
    }

    public String getTelTrabajo() {
        return telTrabajo;
    }

    public void setTelTrabajo(String telTrabajo) {
        this.telTrabajo = telTrabajo;
    }

    public String getNombreConyugue() {
        return nombreConyugue;
    }

    public void setNombreConyugue(String nombreConyugue) {
        this.nombreConyugue = nombreConyugue;
    }

    public String getTrabajoConyugue() {
        return trabajoConyugue;
    }

    public void setTrabajoConyugue(String trabajoConyugue) {
        this.trabajoConyugue = trabajoConyugue;
    }

    public String getTelTrabajoConyugue() {
        return telTrabajoConyugue;
    }

    public void setTelTrabajoConyugue(String telTrabajoConyugue) {
        this.telTrabajoConyugue = telTrabajoConyugue;
    }

    public String getDirInstalacion() {
        return dirInstalacion;
    }

    public void setDirInstalacion(String dirInstalacion) {
        this.dirInstalacion = dirInstalacion;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getDirTrabajoConyugue() {
        return dirTrabajoConyugue;
    }

    public void setDirTrabajoConyugue(String dirTrabajoConyugue) {
        this.dirTrabajoConyugue = dirTrabajoConyugue;
    }

    @XmlTransient
    public List<Pago> getPagoList() {
        return pagoList;
    }

    public void setPagoList(List<Pago> pagoList) {
        this.pagoList = pagoList;
    }

    @XmlTransient
    public List<Contrato> getContratoList() {
        return contratoList;
    }

    public void setContratoList(List<Contrato> contratoList) {
        this.contratoList = contratoList;
    }

    public Profesion getProfesionIdprofesion() {
        return profesionIdprofesion;
    }

    public void setProfesionIdprofesion(Profesion profesionIdprofesion) {
        this.profesionIdprofesion = profesionIdprofesion;
    }

    public Nacionalidad getNacionalidadIdnacionalidad() {
        return nacionalidadIdnacionalidad;
    }

    public void setNacionalidadIdnacionalidad(Nacionalidad nacionalidadIdnacionalidad) {
        this.nacionalidadIdnacionalidad = nacionalidadIdnacionalidad;
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

    public Colonia getColoniaIdcolonia() {
        return coloniaIdcolonia;
    }

    public void setColoniaIdcolonia(Colonia coloniaIdcolonia) {
        this.coloniaIdcolonia = coloniaIdcolonia;
    }

    @XmlTransient
    public List<Ordentrabajo> getOrdentrabajoList() {
        return ordentrabajoList;
    }

    public void setOrdentrabajoList(List<Ordentrabajo> ordentrabajoList) {
        this.ordentrabajoList = ordentrabajoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcliente != null ? idcliente.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cliente)) {
            return false;
        }
        Cliente other = (Cliente) object;
        if ((this.idcliente == null && other.idcliente != null) || (this.idcliente != null && !this.idcliente.equals(other.idcliente))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.syscable.Cliente[ idcliente=" + idcliente + " ]";
    }
    
}
