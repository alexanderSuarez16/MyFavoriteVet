/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.myfavoritevt.facade;

import edu.myfavoritevt.entity.Usuario;
import edu.myfavoritevt.modelo.UsuarioExcel;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Familia Suárez
 */
@Stateless
public class UsuarioFacade extends AbstractFacade<Usuario> implements UsuarioFacadeLocal {

    @PersistenceContext(unitName = "MyfavoriteVtPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioFacade() {
        super(Usuario.class);
    }
    
    
    @Override
     public int registrarUsuarioExcel(UsuarioExcel usuarioIn) {
        int salida = 0;
        try {
            Query q = em.createNativeQuery("INSERT INTO `tbl_usuarios` (`USU_Nombre`,"
                    + "`USU_Apellido`,"
                    + "`USU_Email`,"
                    + "`USU_TipoDocumento`,"
                    + "`USU_NoDocumento`,"
                    + "`USU_Telefono`"
                    + " VALUES ( ?, ?, ?, ?, ?, ?);");
            q.setParameter(1, usuarioIn.getNombre());
            q.setParameter(2, usuarioIn.getApellido());
            q.setParameter(3, usuarioIn.getCorreo());
            q.setParameter(4, usuarioIn.getTipoDocumento());
            q.setParameter(5, usuarioIn.getNoDocumento());
            q.setParameter(6, usuarioIn.getTelefono());
            salida = q.executeUpdate();

        } catch (Exception e) {
            System.out.println("edu.rolesusu.facade.UsuarioFacade.registrarUsuario()" + e.getMessage());
        }
        return salida;
    }
    
    @Override
    public Usuario validar(String correo, String clave) {
        Usuario login = null;

        try {
            Query q = em.createQuery("SELECT p FROM Usuario p WHERE p.uSUEmail = :correo AND p.uSUClave = :clave AND p.uSUestado = 'Activo'");

            q.setParameter("correo", correo);
            q.setParameter("clave", clave);

            login = (Usuario) q.getSingleResult();
            return login;
        } catch (Exception e) {  
            return login;
        }
    } 
    
      
    @Override
    public List<Usuario> leerTodos() {
        Usuario salida = new Usuario();
        try {
            em.getEntityManagerFactory().getCache().evictAll();
            Query q = em.createQuery("SELECT c FROM Usuario c");
           
            

            return q.getResultList();
        } catch (Exception e) {
        }
        return null;
    }
    
    
    @Override
    public Usuario validarCorreo(String correo) {
        Usuario salida = new Usuario();
        try {
            em.getEntityManagerFactory().getCache().evictAll();
            Query q = em.createQuery("SELECT c FROM Usuario c WHERE c.uSUEmail = :correo");
            q.setParameter("correo", correo);
            salida = (Usuario) q.getSingleResult();

            return salida;
        } catch (Exception e) {
        }
        return salida;
    }
    
  
    
    
    @Override
    public Usuario buscarUsuDocument(long numDocumento){
        Usuario usuEdit = null;
        try {
            Query q = em.createQuery("SELECT p FROM Usuario p WHERE p.uSUNumeroDocumento =:numDocumento");
            
            q.setParameter("numDocumento", numDocumento);
            usuEdit = (Usuario) q.getSingleResult();
            return usuEdit;
        } catch (Exception e) {
            return usuEdit;
        }
    }
    
    
    @Override
    public long datosGraficoRol(String tipoConsulta) {
        long salida = 0;
        try {
            Query q = em.createNativeQuery("SELECT COUNT(*) FROM `tbl_usuariorol` \n"
                    + "INNER JOIN tbl_rol ON tbl_rol.PK_ROL_Id_Rol = tbl_usuariorol.FK_ROL_Id_Rol\n"
                    + "WHERE tbl_rol.ROL_Nombre_Rol = ?");
            q.setParameter(1, tipoConsulta);
            salida = (long) q.getSingleResult();

        } catch (Exception e) {
            
        }
        return salida;
    }

    
    
    @Override
    public int registrarUsuario(Usuario usuarioIn) {
        int salida = 0;
        try {
            Query q = em.createNativeQuery("INSERT INTO `tbl_usuario` (`USU_Nombre`, `USU_Apellido`, `USU_Direccion`,"
                    + " `USU_Email`, `USU_Clave`, `USU_Sexo`, `USU_Fecha_Nacimiento`, `USU_Tipo_Documento`, `USU_Numero_Documento`, `USU_Telefono`)"
                    + " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");
            q.setParameter(1, usuarioIn.getUSUNombre());
            q.setParameter(2, usuarioIn.getUSUApellido());
            q.setParameter(3, usuarioIn.getUSUDireccion());
            q.setParameter(4, usuarioIn.getUSUEmail());
            q.setParameter(5, usuarioIn.getUSUClave());
            q.setParameter(6, usuarioIn.getUSUSexo());
            q.setParameter(7, usuarioIn.getUSUFechaNacimiento());
            q.setParameter(8, usuarioIn.getUSUTipoDocumento());
            q.setParameter(9, usuarioIn.getUSUNumeroDocumento());
            q.setParameter(10, usuarioIn.getUSUTelefono());


            salida = q.executeUpdate();
        } catch (Exception e) {
        }
        return salida;
    }
   
    
    
    
    
    @Override
     public void cambiarEstado(int pkUsuario, String estado) {
        
        try {
            Query q = em.createNativeQuery("UPDATE `tbl_usuario` SET `USU_estado` = ? WHERE `tbl_usuario`.`PK_USU_Id_Usuario` = ? ");
            q.setParameter(1, estado);
            q.setParameter(2, pkUsuario);
           q.executeUpdate();
            
        } catch (Exception e) {
        }
       
    }
     
     
     

    

    
    @Override
    public Usuario leerUno(int PKusu) {
        Usuario salida = new Usuario();
        try {
            em.getEntityManagerFactory().getCache().evictAll();
            Query q = em.createQuery("SELECT c FROM Usuario c WHERE c.pKUSUIdUsuario = :PKusu");
            q.setParameter("PKusu", PKusu);
            salida = (Usuario) q.getSingleResult();

            return salida;
        } catch (Exception e) {
        }
        return salida;
    }

    
    @Override
    public long datosGrafico(String tipo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    

    
    @Override
    public Usuario validarUsuario(String usuCorreo, String usuClave) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
