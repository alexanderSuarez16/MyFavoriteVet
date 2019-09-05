/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.myfavoritevt.facade;

import edu.myfavoritevt.entity.Citamedica;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Familia Suárez
 */
@Local
public interface CitamedicaFacadeLocal {

    void create(Citamedica citamedica);

    void edit(Citamedica citamedica);

    void remove(Citamedica citamedica);

    Citamedica find(Object id);

    List<Citamedica> findAll();

    List<Citamedica> findRange(int[] range);

    int count();

    int crearCita(Citamedica CitaIn);

    List<Citamedica> listaMisCitas(int PKusu);

    void confirmarCita(int pkCitaMed);

    void cancelarCita(int pkCitaMed);

    List<Citamedica> listaCitasRechazadas();

    int agendarCitaRechazada(Date fecha, Date hora, int pkCita);

    List<Citamedica> listaContarCitas(Date fecha, Date hora);

    List<Citamedica> listaContarParaHoy(Date fecha);

    
}
