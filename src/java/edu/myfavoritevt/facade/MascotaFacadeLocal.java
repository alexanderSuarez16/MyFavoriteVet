/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.myfavoritevt.facade;

import edu.myfavoritevt.entity.Mascota;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Familia Suárez
 */
@Local
public interface MascotaFacadeLocal {

    void create(Mascota mascota);

    void edit(Mascota mascota);

    void remove(Mascota mascota);

    Mascota find(Object id);

    List<Mascota> findAll();

    List<Mascota> findRange(int[] range);

    int count();

    int registrarMascota(Mascota mascotaIn);

    int adicionarMascota(int PKusu, int PKmas);

    int removerMascota(int PKusu, int PKmas);
    
    void cambiarEstadoMas(int pkMas, String estado);

    List<Mascota> contarMascotas();

    List<Mascota> leeListaMascota();

    long datosGraficoMascota(String tipoConsulta);
    
}
