/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.myfavoritevt.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Familia Suárez
 */
@Entity
@Table(name = "tbl_servicio")
@NamedQueries({
    @NamedQuery(name = "Servicio.findAll", query = "SELECT s FROM Servicio s")})
public class Servicio implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "PK_SER_Id_Servicio")
    private Integer pKSERIdServicio;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 35)
    @Column(name = "SER_Nombre_Servicio")
    private String sERNombreServicio;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "SER_Descripcion")
    private String sERDescripcion;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "fKSERIdServicio")
    private Citaservicio citaservicio;

    public Servicio() {
    }

    public Servicio(Integer pKSERIdServicio) {
        this.pKSERIdServicio = pKSERIdServicio;
    }

    public Servicio(Integer pKSERIdServicio, String sERNombreServicio, String sERDescripcion) {
        this.pKSERIdServicio = pKSERIdServicio;
        this.sERNombreServicio = sERNombreServicio;
        this.sERDescripcion = sERDescripcion;
    }

    public Integer getPKSERIdServicio() {
        return pKSERIdServicio;
    }

    public void setPKSERIdServicio(Integer pKSERIdServicio) {
        this.pKSERIdServicio = pKSERIdServicio;
    }

    public String getSERNombreServicio() {
        return sERNombreServicio;
    }

    public void setSERNombreServicio(String sERNombreServicio) {
        this.sERNombreServicio = sERNombreServicio;
    }

    public String getSERDescripcion() {
        return sERDescripcion;
    }

    public void setSERDescripcion(String sERDescripcion) {
        this.sERDescripcion = sERDescripcion;
    }

    public Citaservicio getCitaservicio() {
        return citaservicio;
    }

    public void setCitaservicio(Citaservicio citaservicio) {
        this.citaservicio = citaservicio;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pKSERIdServicio != null ? pKSERIdServicio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Servicio)) {
            return false;
        }
        Servicio other = (Servicio) object;
        if ((this.pKSERIdServicio == null && other.pKSERIdServicio != null) || (this.pKSERIdServicio != null && !this.pKSERIdServicio.equals(other.pKSERIdServicio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.myfavoritevt.entity.Servicio[ pKSERIdServicio=" + pKSERIdServicio + " ]";
    }
    
}
