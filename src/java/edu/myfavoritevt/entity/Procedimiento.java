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
import javax.persistence.Lob;
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
@Table(name = "tbl_procedimiento")
@NamedQueries({
    @NamedQuery(name = "Procedimiento.findAll", query = "SELECT p FROM Procedimiento p")})
public class Procedimiento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "PK_PRO_Procedimiento")
    private Integer pKPROProcedimiento;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "PRO_Nombre")
    private String pRONombre;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "PRO_Descripcion")
    private String pRODescripcion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fKPROProcedimiento")
    private Collection<Citamedica> citamedicaCollection;

    public Procedimiento() {
    }

    public Procedimiento(Integer pKPROProcedimiento) {
        this.pKPROProcedimiento = pKPROProcedimiento;
    }

    public Procedimiento(Integer pKPROProcedimiento, String pRONombre, String pRODescripcion) {
        this.pKPROProcedimiento = pKPROProcedimiento;
        this.pRONombre = pRONombre;
        this.pRODescripcion = pRODescripcion;
    }

    public Integer getPKPROProcedimiento() {
        return pKPROProcedimiento;
    }

    public void setPKPROProcedimiento(Integer pKPROProcedimiento) {
        this.pKPROProcedimiento = pKPROProcedimiento;
    }

    public String getPRONombre() {
        return pRONombre;
    }

    public void setPRONombre(String pRONombre) {
        this.pRONombre = pRONombre;
    }

    public String getPRODescripcion() {
        return pRODescripcion;
    }

    public void setPRODescripcion(String pRODescripcion) {
        this.pRODescripcion = pRODescripcion;
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
        hash += (pKPROProcedimiento != null ? pKPROProcedimiento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Procedimiento)) {
            return false;
        }
        Procedimiento other = (Procedimiento) object;
        if ((this.pKPROProcedimiento == null && other.pKPROProcedimiento != null) || (this.pKPROProcedimiento != null && !this.pKPROProcedimiento.equals(other.pKPROProcedimiento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.myfavoritevt.entity.Procedimiento[ pKPROProcedimiento=" + pKPROProcedimiento + " ]";
    }
    
}
