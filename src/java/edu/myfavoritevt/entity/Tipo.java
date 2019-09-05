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
@Table(name = "tbl_tipo")
@NamedQueries({
    @NamedQuery(name = "Tipo.findAll", query = "SELECT t FROM Tipo t")})
public class Tipo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "PK_TIP_Id_Tipo")
    private Integer pKTIPIdTipo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "TIP_Nombre")
    private String tIPNombre;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fKTIPIdTipo")
    private Collection<Citamedica> citamedicaCollection;

    public Tipo() {
    }

    public Tipo(Integer pKTIPIdTipo) {
        this.pKTIPIdTipo = pKTIPIdTipo;
    }

    public Tipo(Integer pKTIPIdTipo, String tIPNombre) {
        this.pKTIPIdTipo = pKTIPIdTipo;
        this.tIPNombre = tIPNombre;
    }

    public Integer getPKTIPIdTipo() {
        return pKTIPIdTipo;
    }

    public void setPKTIPIdTipo(Integer pKTIPIdTipo) {
        this.pKTIPIdTipo = pKTIPIdTipo;
    }

    public String getTIPNombre() {
        return tIPNombre;
    }

    public void setTIPNombre(String tIPNombre) {
        this.tIPNombre = tIPNombre;
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
        hash += (pKTIPIdTipo != null ? pKTIPIdTipo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tipo)) {
            return false;
        }
        Tipo other = (Tipo) object;
        if ((this.pKTIPIdTipo == null && other.pKTIPIdTipo != null) || (this.pKTIPIdTipo != null && !this.pKTIPIdTipo.equals(other.pKTIPIdTipo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.myfavoritevt.entity.Tipo[ pKTIPIdTipo=" + pKTIPIdTipo + " ]";
    }
    
}
