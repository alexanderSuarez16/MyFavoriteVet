/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.myfavoritevt.controladores;

import edu.myfavoritevt.entity.Usuario;
import edu.myfavoritevt.facade.UsuarioFacadeLocal;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.Map;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;


/**
 *
 * @author andrea lugo
 */
@Named(value = "usuariosSession")
@SessionScoped
public class usuariosSession implements Serializable {

    @EJB
    private UsuarioFacadeLocal usuariosFacadeLocal;
    private String correo;
    private String clave;
    private String correoIn;
 int contador;

    public int getContador() {
        return contador;
    }

    public void setContador(int contador) {
        this.contador = contador;
    }
    /**
     * Creates a new instance of usuariosSession
     */
    public usuariosSession() {
    contador =0;
    }
    
    public void consultaNotificaciones(){
    this.contador ++;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String validar() {
        Usuario usuLog = usuariosFacadeLocal.validar(correo, clave);
        if (usuLog == null) {
            return "";
        }
        else{
          return "usuario/index.xhtml";  
        }

    }
    
   

    public String getCorreoIn() {
        return correoIn;
    }

    public void setCorreoIn(String correoIn) {
        this.correoIn = correoIn;
    }

}
