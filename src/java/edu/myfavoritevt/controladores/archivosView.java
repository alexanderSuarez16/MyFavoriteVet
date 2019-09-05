/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.myfavoritevt.controladores;

import edu.myfavoritevt.facade.UsuarioFacadeLocal;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.servlet.http.Part;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;

/**
 *
 * @author josarta
 */
@Named(value = "archivosView")
@ViewScoped
public class archivosView implements Serializable {

    @EJB
    UsuarioFacadeLocal usuarioFacadeLocal;
    
    private Part file;
    private String fileContent;


    /**
     * Creates a new instance of archivosView
     */
    public archivosView() {
    }

    public void cargaArchivo() {
        RequestContext rc = RequestContext.getCurrentInstance();
        try {
            if (file.getSize() <= 1024) {
                if (file.getContentType().equals("text/plain")) {
                    fileContent = new Scanner(file.getInputStream()).useDelimiter("\\A").next();
                    rc.execute("swal('Este es su archivo !','Cargado sin error', 'success');");
                } else {
                    rc.execute("swal('Archivo " + file.getName() + " !','no es valido', 'warning');");
                }

            } else {
                rc.execute("swal('El archivo no se puede cargar porque es superior a  !','54 bytes', 'warning');");
            }

        } catch (Exception e) {
        }
    }


    public Part getFile() {
        return file;
    }

    public void setFile(Part file) {
        this.file = file;
    }

    public String getFileContent() {
        return fileContent;
    }

    public void setFileContent(String fileContent) {
        this.fileContent = fileContent;
    }


}
