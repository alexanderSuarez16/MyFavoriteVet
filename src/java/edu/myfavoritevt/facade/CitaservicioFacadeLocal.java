/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.myfavoritevt.facade;

import edu.myfavoritevt.entity.Citaservicio;
import edu.myfavoritevt.entity.Usuario;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Familia Suárez
 */
@Local
public interface CitaservicioFacadeLocal {

    void create(Citaservicio citaservicio);

    void edit(Citaservicio citaservicio);

    void remove(Citaservicio citaservicio);

    Citaservicio find(Object id);

    List<Citaservicio> findAll();

    List<Citaservicio> findRange(int[] range);

    int count();

    int agendarCita(Date fecha, Date hora, int pkCita);

    List<Citaservicio> listaCitasPendientes();

    List<Citaservicio> listaCitasAgendadas();

    List<Citaservicio> listaMisCitas(int PKusu, String fecha);

    void confirmarCita(int pkCitaSer);

    void cancelarCita(int pkCitaSer);

    void solicitarServicio(Citaservicio citaSerIn, String fecha);

    List<Citaservicio> listaContarCitasSer(Date fecha, Date hora);

    List<Citaservicio> contarNumeroCitas(int pKUsu, String fecha);
    
}
