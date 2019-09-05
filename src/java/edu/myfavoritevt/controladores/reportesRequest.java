/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.myfavoritevt.controladores;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperRunManager;

/**
 *
 * @author DIEGO
 */
@Named(value = "reportesRequest")
@RequestScoped
public class reportesRequest {

  
    public reportesRequest() {
    }
    
    public void reporteActivos(String parametroIN) throws IOException, SQLException, JRException {
        
        parametroIN = "Activo";
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext context = facesContext.getExternalContext();
        HttpServletRequest request = (HttpServletRequest) context.getRequest();
        HttpServletResponse response = (HttpServletResponse) context.getResponse();
        response.setContentType("application/pdf");
        try (ServletOutputStream out = response.getOutputStream()) {
            Map parametro = new HashMap();
            parametro.put("estado", parametroIN);
            parametro.put(JRParameter.REPORT_LOCALE, new Locale("en", "US"));
            Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/myfavoritevt", "root", "");
            byte[] bytes = JasperRunManager.runReportToPdf(new File(request.getRealPath("Administrador/Reportes/activosInactivos.jasper")).getPath(), parametro, conexion);
            response.setContentLength(bytes.length);
            out.write(bytes, 0, bytes.length);
            out.flush();
        }
    }
    
    public void reporteInactivos(String parametroI) throws IOException, SQLException, JRException {
        
        parametroI = "Inactivo";
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext context = facesContext.getExternalContext();
        HttpServletRequest request = (HttpServletRequest) context.getRequest();
        HttpServletResponse response = (HttpServletResponse) context.getResponse();
        response.setContentType("application/pdf");
        try (ServletOutputStream out = response.getOutputStream()) {
            Map parametro = new HashMap();
            parametro.put("estado", parametroI);
            parametro.put(JRParameter.REPORT_LOCALE, new Locale("en", "US"));
            Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/myfavoritevt", "root", "");
            byte[] bytes = JasperRunManager.runReportToPdf(new File(request.getRealPath("Administrador/Reportes/activosInactivos.jasper")).getPath(), parametro, conexion);
            response.setContentLength(bytes.length);
            out.write(bytes, 0, bytes.length);
            out.flush();
        }
    }
    
         public void todosUsuarios() throws IOException, SQLException, JRException {

        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext context = facesContext.getExternalContext();
        HttpServletRequest request = (HttpServletRequest) context.getRequest();
        HttpServletResponse response = (HttpServletResponse) context.getResponse();
        response.setContentType("application/pdf");
        try (ServletOutputStream out = response.getOutputStream()) {
            Map parametro = new HashMap();
            Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/myfavoritevt", "root", "");
            byte[] bytes = JasperRunManager.runReportToPdf(new File(request.getRealPath("Administrador/reportes/reporteUsuarios.jasper")).getPath(), parametro, conexion);
            response.setContentLength(bytes.length);
            out.write(bytes, 0, bytes.length);
            out.flush();
        }
    }
    
}
