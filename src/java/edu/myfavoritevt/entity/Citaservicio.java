/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.myfavoritevt.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Familia Suárez
 */
@Entity
@Table(name = "tbl_citaservicio")
@NamedQueries({
    @NamedQuery(name = "Citaservicio.findAll", query = "SELECT c FROM Citaservicio c")})
public class Citaservicio implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "CIS_Estado")
    private String cISEstado;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "CIS_FechaEnLaQueSeAgendo")
    private String cISFechaEnLaQueSeAgendo;

    @Column(name = "CIS_Fecha")
    @Temporal(TemporalType.DATE)
    private Date cISFecha;
    @Column(name = "CIS_Hora")
    @Temporal(TemporalType.TIME)
    private Date cISHora;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "PK_CIS_Citaservicio")
    private Integer pKCISCitaservicio;
    @JoinColumn(name = "FK_USU_IdUsuario", referencedColumnName = "PK_USU_Id_Usuario")
    @OneToOne(optional = false)
    private Usuario fKUSUIdUsuario;
    @JoinColumn(name = "FK_SER_IdServicio", referencedColumnName = "PK_SER_Id_Servicio")
    @OneToOne(optional = false)
    private Servicio fKSERIdServicio;
    @JoinColumn(name = "FK_MAS_IdMascota", referencedColumnName = "PK_MAS_IdMascota")
    @ManyToOne(optional = false)
    private Mascota fKMASIdMascota;

    public Citaservicio() {
    }

    public Citaservicio(Integer pKCISCitaservicio) {
        this.pKCISCitaservicio = pKCISCitaservicio;
    }

    public Citaservicio(Integer pKCISCitaservicio, Date cISFecha, Date cISHora, String cISEstado) {
        this.pKCISCitaservicio = pKCISCitaservicio;
        this.cISFecha = cISFecha;
        this.cISHora = cISHora;
        this.cISEstado = cISEstado;
    }

    public Integer getPKCISCitaservicio() {
        return pKCISCitaservicio;
    }

    public void setPKCISCitaservicio(Integer pKCISCitaservicio) {
        this.pKCISCitaservicio = pKCISCitaservicio;
    }

    public Date getCISFecha() {
        return cISFecha;
    }

    public void setCISFecha(Date cISFecha) {
        this.cISFecha = cISFecha;
    }

    public Date getCISHora() {
        return cISHora;
    }

    public void setCISHora(Date cISHora) {
        this.cISHora = cISHora;
    }

    public String getCISEstado() {
        return cISEstado;
    }

    public void setCISEstado(String cISEstado) {
        this.cISEstado = cISEstado;
    }

    public Usuario getFKUSUIdUsuario() {
        return fKUSUIdUsuario;
    }

    public void setFKUSUIdUsuario(Usuario fKUSUIdUsuario) {
        this.fKUSUIdUsuario = fKUSUIdUsuario;
    }

    public Servicio getFKSERIdServicio() {
        return fKSERIdServicio;
    }

    public void setFKSERIdServicio(Servicio fKSERIdServicio) {
        this.fKSERIdServicio = fKSERIdServicio;
    }

    public Mascota getFKMASIdMascota() {
        return fKMASIdMascota;
    }

    public void setFKMASIdMascota(Mascota fKMASIdMascota) {
        this.fKMASIdMascota = fKMASIdMascota;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pKCISCitaservicio != null ? pKCISCitaservicio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Citaservicio)) {
            return false;
        }
        Citaservicio other = (Citaservicio) object;
        if ((this.pKCISCitaservicio == null && other.pKCISCitaservicio != null) || (this.pKCISCitaservicio != null && !this.pKCISCitaservicio.equals(other.pKCISCitaservicio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.myfavoritevt.entity.Citaservicio[ pKCISCitaservicio=" + pKCISCitaservicio + " ]";
    }


    public void setCISFechaEnLaQueSeAgendo(String cISFechaEnLaQueSeAgendo) {
        this.cISFechaEnLaQueSeAgendo = cISFechaEnLaQueSeAgendo;
    }

    

    

    

    
    
}
