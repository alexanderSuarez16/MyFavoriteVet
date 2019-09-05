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
@Table(name = "tbl_citamedica")
@NamedQueries({
    @NamedQuery(name = "Citamedica.findAll", query = "SELECT c FROM Citamedica c")})
public class Citamedica implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "PK_CIT_IdCita")
    private Integer pKCITIdCita;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CIT_Fecha")
    @Temporal(TemporalType.DATE)
    private Date cITFecha;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CIT_Hora")
    @Temporal(TemporalType.TIME)
    private Date cITHora;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "CIT_Estado")
    private String cITEstado;
    @JoinColumn(name = "FK_PRO_Procedimiento", referencedColumnName = "PK_PRO_Procedimiento")
    @ManyToOne(optional = false)
    private Procedimiento fKPROProcedimiento;
    @JoinColumn(name = "FK_USU_IdUsuario", referencedColumnName = "PK_USU_Id_Usuario")
    @ManyToOne(optional = false)
    private Usuario fKUSUIdUsuario;
    @JoinColumn(name = "FK_TIP_IdTipo", referencedColumnName = "PK_TIP_Id_Tipo")
    @ManyToOne(optional = false)
    private Tipo fKTIPIdTipo;
    @JoinColumn(name = "FK_MAS_IdMascota", referencedColumnName = "PK_MAS_IdMascota")
    @ManyToOne(optional = false)
    private Mascota fKMASIdMascota;

    public Citamedica() {
    }

    public Citamedica(Integer pKCITIdCita) {
        this.pKCITIdCita = pKCITIdCita;
    }

    public Citamedica(Integer pKCITIdCita, Date cITFecha, Date cITHora, String cITEstado) {
        this.pKCITIdCita = pKCITIdCita;
        this.cITFecha = cITFecha;
        this.cITHora = cITHora;
        this.cITEstado = cITEstado;
    }

    public Integer getPKCITIdCita() {
        return pKCITIdCita;
    }

    public void setPKCITIdCita(Integer pKCITIdCita) {
        this.pKCITIdCita = pKCITIdCita;
    }

    public Date getCITFecha() {
        return cITFecha;
    }

    public void setCITFecha(Date cITFecha) {
        this.cITFecha = cITFecha;
    }

    public Date getCITHora() {
        return cITHora;
    }

    public void setCITHora(Date cITHora) {
        this.cITHora = cITHora;
    }

    public String getCITEstado() {
        return cITEstado;
    }

    public void setCITEstado(String cITEstado) {
        this.cITEstado = cITEstado;
    }

    public Procedimiento getFKPROProcedimiento() {
        return fKPROProcedimiento;
    }

    public void setFKPROProcedimiento(Procedimiento fKPROProcedimiento) {
        this.fKPROProcedimiento = fKPROProcedimiento;
    }

    public Usuario getFKUSUIdUsuario() {
        return fKUSUIdUsuario;
    }

    public void setFKUSUIdUsuario(Usuario fKUSUIdUsuario) {
        this.fKUSUIdUsuario = fKUSUIdUsuario;
    }

    public Tipo getFKTIPIdTipo() {
        return fKTIPIdTipo;
    }

    public void setFKTIPIdTipo(Tipo fKTIPIdTipo) {
        this.fKTIPIdTipo = fKTIPIdTipo;
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
        hash += (pKCITIdCita != null ? pKCITIdCita.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Citamedica)) {
            return false;
        }
        Citamedica other = (Citamedica) object;
        if ((this.pKCITIdCita == null && other.pKCITIdCita != null) || (this.pKCITIdCita != null && !this.pKCITIdCita.equals(other.pKCITIdCita))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.myfavoritevt.entity.Citamedica[ pKCITIdCita=" + pKCITIdCita + " ]";
    }
    
}
