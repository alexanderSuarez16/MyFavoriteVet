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
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
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
@Table(name = "tbl_historiaclinica")
@NamedQueries({
    @NamedQuery(name = "Historiaclinica.findAll", query = "SELECT h FROM Historiaclinica h")})
public class Historiaclinica implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "PK_HIS_IdHistoriaClinica")
    private Integer pKHISIdHistoriaClinica;
    @Basic(optional = false)
    @NotNull
    @Column(name = "HIS_Fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date hISFecha;
    @Basic(optional = false)
    @NotNull
    @Column(name = "HIS_Hora")
    @Temporal(TemporalType.TIMESTAMP)
    private Date hISHora;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "HIS_DescripcionHistoriaClinica")
    private String hISDescripcionHistoriaClinica;
    @JoinTable(name = "tbl_historiareceta", joinColumns = {
        @JoinColumn(name = "FK_HIS_IdHistoriaClinica", referencedColumnName = "PK_HIS_IdHistoriaClinica")}, inverseJoinColumns = {
        @JoinColumn(name = "FK_REC_IdReceta", referencedColumnName = "PK_REC_IdReceta")})
    @ManyToMany
    private Collection<Receta> recetaCollection;
    @JoinColumn(name = "FK_MAS_IdMascota", referencedColumnName = "PK_MAS_IdMascota")
    @ManyToOne(optional = false)
    private Mascota fKMASIdMascota;

    public Historiaclinica() {
    }

    public Historiaclinica(Integer pKHISIdHistoriaClinica) {
        this.pKHISIdHistoriaClinica = pKHISIdHistoriaClinica;
    }

    public Historiaclinica(Integer pKHISIdHistoriaClinica, Date hISFecha, Date hISHora, String hISDescripcionHistoriaClinica) {
        this.pKHISIdHistoriaClinica = pKHISIdHistoriaClinica;
        this.hISFecha = hISFecha;
        this.hISHora = hISHora;
        this.hISDescripcionHistoriaClinica = hISDescripcionHistoriaClinica;
    }

    public Integer getPKHISIdHistoriaClinica() {
        return pKHISIdHistoriaClinica;
    }

    public void setPKHISIdHistoriaClinica(Integer pKHISIdHistoriaClinica) {
        this.pKHISIdHistoriaClinica = pKHISIdHistoriaClinica;
    }

    public Date getHISFecha() {
        return hISFecha;
    }

    public void setHISFecha(Date hISFecha) {
        this.hISFecha = hISFecha;
    }

    public Date getHISHora() {
        return hISHora;
    }

    public void setHISHora(Date hISHora) {
        this.hISHora = hISHora;
    }

    public String getHISDescripcionHistoriaClinica() {
        return hISDescripcionHistoriaClinica;
    }

    public void setHISDescripcionHistoriaClinica(String hISDescripcionHistoriaClinica) {
        this.hISDescripcionHistoriaClinica = hISDescripcionHistoriaClinica;
    }

    public Collection<Receta> getRecetaCollection() {
        return recetaCollection;
    }

    public void setRecetaCollection(Collection<Receta> recetaCollection) {
        this.recetaCollection = recetaCollection;
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
        hash += (pKHISIdHistoriaClinica != null ? pKHISIdHistoriaClinica.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Historiaclinica)) {
            return false;
        }
        Historiaclinica other = (Historiaclinica) object;
        if ((this.pKHISIdHistoriaClinica == null && other.pKHISIdHistoriaClinica != null) || (this.pKHISIdHistoriaClinica != null && !this.pKHISIdHistoriaClinica.equals(other.pKHISIdHistoriaClinica))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.myfavoritevt.entity.Historiaclinica[ pKHISIdHistoriaClinica=" + pKHISIdHistoriaClinica + " ]";
    }
    
}
