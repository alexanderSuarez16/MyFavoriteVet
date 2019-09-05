/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.myfavoritevt.entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Familia Suárez
 */
@Entity
@Table(name = "tbl_rol")
@NamedQueries({
    @NamedQuery(name = "Rol.findAll", query = "SELECT r FROM Rol r")})
public class Rol implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "PK_ROL_Id_Rol")
    private Integer pKROLIdRol;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "ROL_Nombre_Rol")
    private String rOLNombreRol;
    @ManyToMany(mappedBy = "rolCollection")
    private Collection<Usuario> usuarioCollection;

    public Rol() {
    }

    public Rol(Integer pKROLIdRol) {
        this.pKROLIdRol = pKROLIdRol;
    }

    public Rol(Integer pKROLIdRol, String rOLNombreRol) {
        this.pKROLIdRol = pKROLIdRol;
        this.rOLNombreRol = rOLNombreRol;
    }

    public Integer getPKROLIdRol() {
        return pKROLIdRol;
    }

    public void setPKROLIdRol(Integer pKROLIdRol) {
        this.pKROLIdRol = pKROLIdRol;
    }

    public String getROLNombreRol() {
        return rOLNombreRol;
    }

    public void setROLNombreRol(String rOLNombreRol) {
        this.rOLNombreRol = rOLNombreRol;
    }

    public Collection<Usuario> getUsuarioCollection() {
        return usuarioCollection;
    }

    public void setUsuarioCollection(Collection<Usuario> usuarioCollection) {
        this.usuarioCollection = usuarioCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pKROLIdRol != null ? pKROLIdRol.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Rol)) {
            return false;
        }
        Rol other = (Rol) object;
        if ((this.pKROLIdRol == null && other.pKROLIdRol != null) || (this.pKROLIdRol != null && !this.pKROLIdRol.equals(other.pKROLIdRol))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.myfavoritevt.entity.Rol[ pKROLIdRol=" + pKROLIdRol + " ]";
    }
    
}
