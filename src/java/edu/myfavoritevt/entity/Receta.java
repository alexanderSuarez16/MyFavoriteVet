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
@Table(name = "tbl_receta")
@NamedQueries({
    @NamedQuery(name = "Receta.findAll", query = "SELECT r FROM Receta r")})
public class Receta implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "PK_REC_IdReceta")
    private Integer pKRECIdReceta;
    @Basic(optional = false)
    @NotNull
    @Column(name = "REC_Fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date rECFecha;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "REC_Descripcion")
    private String rECDescripcion;
    @JoinTable(name = "tbl_recetamedicamento", joinColumns = {
        @JoinColumn(name = "FK_REC_Id_Receta", referencedColumnName = "PK_REC_IdReceta")}, inverseJoinColumns = {
        @JoinColumn(name = "FK_MED_IdMedicamento", referencedColumnName = "PK_MED_IdMedicamento")})
    @ManyToMany
    private Collection<Medicamento> medicamentoCollection;
    @ManyToMany(mappedBy = "recetaCollection")
    private Collection<Historiaclinica> historiaclinicaCollection;

    public Receta() {
    }

    public Receta(Integer pKRECIdReceta) {
        this.pKRECIdReceta = pKRECIdReceta;
    }

    public Receta(Integer pKRECIdReceta, Date rECFecha, String rECDescripcion) {
        this.pKRECIdReceta = pKRECIdReceta;
        this.rECFecha = rECFecha;
        this.rECDescripcion = rECDescripcion;
    }

    public Integer getPKRECIdReceta() {
        return pKRECIdReceta;
    }

    public void setPKRECIdReceta(Integer pKRECIdReceta) {
        this.pKRECIdReceta = pKRECIdReceta;
    }

    public Date getRECFecha() {
        return rECFecha;
    }

    public void setRECFecha(Date rECFecha) {
        this.rECFecha = rECFecha;
    }

    public String getRECDescripcion() {
        return rECDescripcion;
    }

    public void setRECDescripcion(String rECDescripcion) {
        this.rECDescripcion = rECDescripcion;
    }

    public Collection<Medicamento> getMedicamentoCollection() {
        return medicamentoCollection;
    }

    public void setMedicamentoCollection(Collection<Medicamento> medicamentoCollection) {
        this.medicamentoCollection = medicamentoCollection;
    }

    public Collection<Historiaclinica> getHistoriaclinicaCollection() {
        return historiaclinicaCollection;
    }

    public void setHistoriaclinicaCollection(Collection<Historiaclinica> historiaclinicaCollection) {
        this.historiaclinicaCollection = historiaclinicaCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pKRECIdReceta != null ? pKRECIdReceta.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Receta)) {
            return false;
        }
        Receta other = (Receta) object;
        if ((this.pKRECIdReceta == null && other.pKRECIdReceta != null) || (this.pKRECIdReceta != null && !this.pKRECIdReceta.equals(other.pKRECIdReceta))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.myfavoritevt.entity.Receta[ pKRECIdReceta=" + pKRECIdReceta + " ]";
    }
    
}
