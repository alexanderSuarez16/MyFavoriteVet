/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.myfavoritevt.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author DIEGO
 */
@Entity
@Table(name = "tbl_usuario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u")
    , @NamedQuery(name = "Usuario.findByPKUSUIdUsuario", query = "SELECT u FROM Usuario u WHERE u.pKUSUIdUsuario = :pKUSUIdUsuario")
    , @NamedQuery(name = "Usuario.findByUSUNombre", query = "SELECT u FROM Usuario u WHERE u.uSUNombre = :uSUNombre")
    , @NamedQuery(name = "Usuario.findByUSUApellido", query = "SELECT u FROM Usuario u WHERE u.uSUApellido = :uSUApellido")
    , @NamedQuery(name = "Usuario.findByUSUDireccion", query = "SELECT u FROM Usuario u WHERE u.uSUDireccion = :uSUDireccion")
    , @NamedQuery(name = "Usuario.findByUSUEmail", query = "SELECT u FROM Usuario u WHERE u.uSUEmail = :uSUEmail")
    , @NamedQuery(name = "Usuario.findByUSUestado", query = "SELECT u FROM Usuario u WHERE u.uSUestado = :uSUestado")
    , @NamedQuery(name = "Usuario.findByUSUClave", query = "SELECT u FROM Usuario u WHERE u.uSUClave = :uSUClave")
    , @NamedQuery(name = "Usuario.findByUSUSexo", query = "SELECT u FROM Usuario u WHERE u.uSUSexo = :uSUSexo")
    , @NamedQuery(name = "Usuario.findByUSUFechaNacimiento", query = "SELECT u FROM Usuario u WHERE u.uSUFechaNacimiento = :uSUFechaNacimiento")
    , @NamedQuery(name = "Usuario.findByUSUTipoDocumento", query = "SELECT u FROM Usuario u WHERE u.uSUTipoDocumento = :uSUTipoDocumento")
    , @NamedQuery(name = "Usuario.findByUSUNumeroDocumento", query = "SELECT u FROM Usuario u WHERE u.uSUNumeroDocumento = :uSUNumeroDocumento")
    , @NamedQuery(name = "Usuario.findByUSUTelefono", query = "SELECT u FROM Usuario u WHERE u.uSUTelefono = :uSUTelefono")})
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "PK_USU_Id_Usuario")
    private Integer pKUSUIdUsuario;
    @Size(max = 50)
    @Column(name = "USU_Nombre")
    private String uSUNombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "USU_Apellido")
    private String uSUApellido;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "USU_Direccion")
    private String uSUDireccion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "USU_Email")
    private String uSUEmail;
    @Size(max = 50)
    @Column(name = "USU_estado")
    private String uSUestado;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "USU_Clave")
    private String uSUClave;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "USU_Sexo")
    private String uSUSexo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "USU_Fecha_Nacimiento")
    @Temporal(TemporalType.DATE)
    private Date uSUFechaNacimiento;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "USU_Tipo_Documento")
    private String uSUTipoDocumento;
    @Basic(optional = false)
    @NotNull
    @Column(name = "USU_Numero_Documento")
    private long uSUNumeroDocumento;
    @Basic(optional = false)
    @NotNull
    @Column(name = "USU_Telefono")
    private long uSUTelefono;
    @JoinTable(name = "tbl_usuariomascota", joinColumns = {
        @JoinColumn(name = "FK_USU_Id_Usuario", referencedColumnName = "PK_USU_Id_Usuario")}, inverseJoinColumns = {
        @JoinColumn(name = "FK_MAS_Id_Mascota", referencedColumnName = "PK_MAS_IdMascota")})
    @ManyToMany
    private Collection<Mascota> mascotaCollection;
    @JoinTable(name = "tbl_usuariorol", joinColumns = {
        @JoinColumn(name = "FK_USU_Id_Usuario", referencedColumnName = "PK_USU_Id_Usuario")}, inverseJoinColumns = {
        @JoinColumn(name = "FK_ROL_Id_Rol", referencedColumnName = "PK_ROL_Id_Rol")})
    @ManyToMany
    private Collection<Rol> rolCollection;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "fKUSUIdUsuario")
    private Citaservicio citaservicio;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fKUSUIdUsuario")
    private Collection<Citamedica> citamedicaCollection;

    public Usuario() {
    }

    public Usuario(Integer pKUSUIdUsuario) {
        this.pKUSUIdUsuario = pKUSUIdUsuario;
    }

    public Usuario(Integer pKUSUIdUsuario, String uSUApellido, String uSUDireccion, String uSUEmail, String uSUClave, String uSUSexo, Date uSUFechaNacimiento, String uSUTipoDocumento, long uSUNumeroDocumento, long uSUTelefono) {
        this.pKUSUIdUsuario = pKUSUIdUsuario;
        this.uSUApellido = uSUApellido;
        this.uSUDireccion = uSUDireccion;
        this.uSUEmail = uSUEmail;
        this.uSUClave = uSUClave;
        this.uSUSexo = uSUSexo;
        this.uSUFechaNacimiento = uSUFechaNacimiento;
        this.uSUTipoDocumento = uSUTipoDocumento;
        this.uSUNumeroDocumento = uSUNumeroDocumento;
        this.uSUTelefono = uSUTelefono;
    }

    public Integer getPKUSUIdUsuario() {
        return pKUSUIdUsuario;
    }

    public void setPKUSUIdUsuario(Integer pKUSUIdUsuario) {
        this.pKUSUIdUsuario = pKUSUIdUsuario;
    }

    public String getUSUNombre() {
        return uSUNombre;
    }

    public void setUSUNombre(String uSUNombre) {
        this.uSUNombre = uSUNombre;
    }

    public String getUSUApellido() {
        return uSUApellido;
    }

    public void setUSUApellido(String uSUApellido) {
        this.uSUApellido = uSUApellido;
    }

    public String getUSUDireccion() {
        return uSUDireccion;
    }

    public void setUSUDireccion(String uSUDireccion) {
        this.uSUDireccion = uSUDireccion;
    }

    public String getUSUEmail() {
        return uSUEmail;
    }

    public void setUSUEmail(String uSUEmail) {
        this.uSUEmail = uSUEmail;
    }

    public String getUSUestado() {
        return uSUestado;
    }

    public void setUSUestado(String uSUestado) {
        this.uSUestado = uSUestado;
    }

    public String getUSUClave() {
        return uSUClave;
    }

    public void setUSUClave(String uSUClave) {
        this.uSUClave = uSUClave;
    }

    public String getUSUSexo() {
        return uSUSexo;
    }

    public void setUSUSexo(String uSUSexo) {
        this.uSUSexo = uSUSexo;
    }

    public Date getUSUFechaNacimiento() {
        return uSUFechaNacimiento;
    }

    public void setUSUFechaNacimiento(Date uSUFechaNacimiento) {
        this.uSUFechaNacimiento = uSUFechaNacimiento;
    }

    public String getUSUTipoDocumento() {
        return uSUTipoDocumento;
    }

    public void setUSUTipoDocumento(String uSUTipoDocumento) {
        this.uSUTipoDocumento = uSUTipoDocumento;
    }

    public long getUSUNumeroDocumento() {
        return uSUNumeroDocumento;
    }

    public void setUSUNumeroDocumento(long uSUNumeroDocumento) {
        this.uSUNumeroDocumento = uSUNumeroDocumento;
    }

    public long getUSUTelefono() {
        return uSUTelefono;
    }

    public void setUSUTelefono(long uSUTelefono) {
        this.uSUTelefono = uSUTelefono;
    }

    @XmlTransient
    public Collection<Mascota> getMascotaCollection() {
        return mascotaCollection;
    }

    public void setMascotaCollection(Collection<Mascota> mascotaCollection) {
        this.mascotaCollection = mascotaCollection;
    }

    @XmlTransient
    public Collection<Rol> getRolCollection() {
        return rolCollection;
    }

    public void setRolCollection(Collection<Rol> rolCollection) {
        this.rolCollection = rolCollection;
    }

    public Citaservicio getCitaservicio() {
        return citaservicio;
    }

    public void setCitaservicio(Citaservicio citaservicio) {
        this.citaservicio = citaservicio;
    }

    @XmlTransient
    public Collection<Citamedica> getCitamedicaCollection() {
        return citamedicaCollection;
    }

    public void setCitamedicaCollection(Collection<Citamedica> citamedicaCollection) {
        this.citamedicaCollection = citamedicaCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pKUSUIdUsuario != null ? pKUSUIdUsuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        if ((this.pKUSUIdUsuario == null && other.pKUSUIdUsuario != null) || (this.pKUSUIdUsuario != null && !this.pKUSUIdUsuario.equals(other.pKUSUIdUsuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.myfavoritevt.entity.Usuario[ pKUSUIdUsuario=" + pKUSUIdUsuario + " ]";
    }

    
    
}
