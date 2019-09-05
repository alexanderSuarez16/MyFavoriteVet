/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.myfavoritevt.facade;

import edu.myfavoritevt.entity.Usuario;
import edu.myfavoritevt.modelo.UsuarioExcel;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Familia Suárez
 */
@Local
public interface UsuarioFacadeLocal {

    void create(Usuario usuario);

    void edit(Usuario usuario);

    void remove(Usuario usuario);

    Usuario find(Object id);

    List<Usuario> findAll();

    List<Usuario> findRange(int[] range);

    int count();

    Usuario validar(String correo, String clave);

    List<Usuario> leerTodos();

    Usuario validarCorreo(String correo);


    Usuario buscarUsuDocument(long numDocumento);

    long datosGraficoRol(String tipoConsulta);

    int registrarUsuario(Usuario usuarioIn);

    void cambiarEstado(int pkUsuario, String estado);

    Usuario leerUno(int PKusu);

    long datosGrafico(String tipo);

    Usuario validarUsuario(String usuCorreo, String usuClave);

     int registrarUsuarioExcel(UsuarioExcel filaUsuario);
    
}
