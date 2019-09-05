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
@Table(name = "tbl_medicamento")
@NamedQueries({
    @NamedQuery(name = "Medicamento.findAll", query = "SELECT m FROM Medicamento m")})
public class Medicamento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "PK_MED_IdMedicamento")
    private Integer pKMEDIdMedicamento;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "MED_Medicamento_Donado")
    private String mEDMedicamentoDonado;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "MED_Nombre")
    private String mEDNombre;
    @Basic(optional = false)
    @NotNull
    @Column(name = "MED_Fecha_Vencimiento")
    @Temporal(TemporalType.DATE)
    private Date mEDFechaVencimiento;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "MED_Tipo")
    private String mEDTipo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 35)
    @Column(name = "MED_Cantidad")
    private String mEDCantidad;
    @ManyToMany(mappedBy = "medicamentoCollection")
    private Collection<Receta> recetaCollection;

    public Medicamento() {
    }

    public Medicamento(Integer pKMEDIdMedicamento) {
        this.pKMEDIdMedicamento = pKMEDIdMedicamento;
    }

    public Medicamento(Integer pKMEDIdMedicamento, String mEDMedicamentoDonado, String mEDNombre, Date mEDFechaVencimiento, String mEDTipo, String mEDCantidad) {
        this.pKMEDIdMedicamento = pKMEDIdMedicamento;
        this.mEDMedicamentoDonado = mEDMedicamentoDonado;
        this.mEDNombre = mEDNombre;
        this.mEDFechaVencimiento = mEDFechaVencimiento;
        this.mEDTipo = mEDTipo;
        this.mEDCantidad = mEDCantidad;
    }

    public Integer getPKMEDIdMedicamento() {
        return pKMEDIdMedicamento;
    }

    public void setPKMEDIdMedicamento(Integer pKMEDIdMedicamento) {
        this.pKMEDIdMedicamento = pKMEDIdMedicamento;
    }

    public String getMEDMedicamentoDonado() {
        return mEDMedicamentoDonado;
    }

    public void setMEDMedicamentoDonado(String mEDMedicamentoDonado) {
        this.mEDMedicamentoDonado = mEDMedicamentoDonado;
    }

    public String getMEDNombre() {
        return mEDNombre;
    }

    public void setMEDNombre(String mEDNombre) {
        this.mEDNombre = mEDNombre;
    }

    public Date getMEDFechaVencimiento() {
        return mEDFechaVencimiento;
    }

    public void setMEDFechaVencimiento(Date mEDFechaVencimiento) {
        this.mEDFechaVencimiento = mEDFechaVencimiento;
    }

    public String getMEDTipo() {
        return mEDTipo;
    }

    public void setMEDTipo(String mEDTipo) {
        this.mEDTipo = mEDTipo;
    }

    public String getMEDCantidad() {
        return mEDCantidad;
    }

    public void setMEDCantidad(String mEDCantidad) {
        this.mEDCantidad = mEDCantidad;
    }

    public Collection<Receta> getRecetaCollection() {
        return recetaCollection;
    }

    public void setRecetaCollection(Collection<Receta> recetaCollection) {
        this.recetaCollection = recetaCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pKMEDIdMedicamento != null ? pKMEDIdMedicamento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Medicamento)) {
            return false;
        }
        Medicamento other = (Medicamento) object;
        if ((this.pKMEDIdMedicamento == null && other.pKMEDIdMedicamento != null) || (this.pKMEDIdMedicamento != null && !this.pKMEDIdMedicamento.equals(other.pKMEDIdMedicamento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.myfavoritevt.entity.Medicamento[ pKMEDIdMedicamento=" + pKMEDIdMedicamento + " ]";
    }
    
}
