/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.myfavoritevt.entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Familia Suárez
 */
@Entity
@Table(name = "tbl_mascota")
@NamedQueries({
    @NamedQuery(name = "Mascota.findAll", query = "SELECT m FROM Mascota m")})
public class Mascota implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "PK_MAS_IdMascota")
    private Integer pKMASIdMascota;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "MAS_Mascota_Beneficiaria")
    private String mASMascotaBeneficiaria;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "MAS_Nombre_Mascota")
    private String mASNombreMascota;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "MAS_Sexo_Mascota")
    private String mASSexoMascota;
    @Size(max = 25)
    @Column(name = "MAS_Tipo_Mascota")
    private String mASTipoMascota;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "MAS_Raza_Mascota")
    private String mASRazaMascota;
    @Basic(optional = false)
    @NotNull
    @Column(name = "MAS_Peso_Mascota")
    private short mASPesoMascota;
    @Basic(optional = false)
    @NotNull
    @Column(name = "MAS_Estatura_Mascota")
    private double mASEstaturaMascota;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "MAS_Estado")
    private String mASEstado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fKMASIdMascota")
    private Collection<Historiaclinica> historiaclinicaCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fKMASIdMascota")
    private Collection<Citaservicio> citaservicioCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fKMASIdMascota")
    private Collection<Citamedica> citamedicaCollection;

    public Mascota() {
    }

    public Mascota(Integer pKMASIdMascota) {
        this.pKMASIdMascota = pKMASIdMascota;
    }

    public Mascota(Integer pKMASIdMascota, String mASMascotaBeneficiaria, String mASNombreMascota, String mASSexoMascota, String mASRazaMascota, short mASPesoMascota, double mASEstaturaMascota, String mASEstado) {
        this.pKMASIdMascota = pKMASIdMascota;
        this.mASMascotaBeneficiaria = mASMascotaBeneficiaria;
        this.mASNombreMascota = mASNombreMascota;
        this.mASSexoMascota = mASSexoMascota;
        this.mASRazaMascota = mASRazaMascota;
        this.mASPesoMascota = mASPesoMascota;
        this.mASEstaturaMascota = mASEstaturaMascota;
        this.mASEstado = mASEstado;
    }

    public Integer getPKMASIdMascota() {
        return pKMASIdMascota;
    }

    public void setPKMASIdMascota(Integer pKMASIdMascota) {
        this.pKMASIdMascota = pKMASIdMascota;
    }

    public String getMASMascotaBeneficiaria() {
        return mASMascotaBeneficiaria;
    }

    public void setMASMascotaBeneficiaria(String mASMascotaBeneficiaria) {
        this.mASMascotaBeneficiaria = mASMascotaBeneficiaria;
    }

    public String getMASNombreMascota() {
        return mASNombreMascota;
    }

    public void setMASNombreMascota(String mASNombreMascota) {
        this.mASNombreMascota = mASNombreMascota;
    }

    public String getMASSexoMascota() {
        return mASSexoMascota;
    }

    public void setMASSexoMascota(String mASSexoMascota) {
        this.mASSexoMascota = mASSexoMascota;
    }

    public String getMASTipoMascota() {
        return mASTipoMascota;
    }

    public void setMASTipoMascota(String mASTipoMascota) {
        this.mASTipoMascota = mASTipoMascota;
    }

    public String getMASRazaMascota() {
        return mASRazaMascota;
    }

    public void setMASRazaMascota(String mASRazaMascota) {
        this.mASRazaMascota = mASRazaMascota;
    }

    public short getMASPesoMascota() {
        return mASPesoMascota;
    }

    public void setMASPesoMascota(short mASPesoMascota) {
        this.mASPesoMascota = mASPesoMascota;
    }

    public double getMASEstaturaMascota() {
        return mASEstaturaMascota;
    }

    public void setMASEstaturaMascota(double mASEstaturaMascota) {
        this.mASEstaturaMascota = mASEstaturaMascota;
    }

    public String getMASEstado() {
        return mASEstado;
    }

    public void setMASEstado(String mASEstado) {
        this.mASEstado = mASEstado;
    }

    public Collection<Historiaclinica> getHistoriaclinicaCollection() {
        return historiaclinicaCollection;
    }

    public void setHistoriaclinicaCollection(Collection<Historiaclinica> historiaclinicaCollection) {
        this.historiaclinicaCollection = historiaclinicaCollection;
    }

    public Collection<Citaservicio> getCitaservicioCollection() {
        return citaservicioCollection;
    }

    public void setCitaservicioCollection(Collection<Citaservicio> citaservicioCollection) {
        this.citaservicioCollection = citaservicioCollection;
    }

    public Collection<Citamedica> getCitamedicaCollection() {
        return citamedicaCollection;
    }

    public void setCitamedicaCollection(Collection<Citamedica> citamedicaCollection) {
        this.citamedicaCollection = citamedicaCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pKMASIdMascota != null ? pKMASIdMascota.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Mascota)) {
            return false;
        }
        Mascota other = (Mascota) object;
        if ((this.pKMASIdMascota == null && other.pKMASIdMascota != null) || (this.pKMASIdMascota != null && !this.pKMASIdMascota.equals(other.pKMASIdMascota))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.myfavoritevt.entity.Mascota[ pKMASIdMascota=" + pKMASIdMascota + " ]";
    }

    public Object getMASestado() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
