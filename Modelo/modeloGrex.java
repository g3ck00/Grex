/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import Vista.*;
import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Bryantcore
 */
public class modeloGrex {
    
    public static Connection conexion;
    
    public static void conectarABD(){ //Conecta a la BD
        try{
            conexion=DriverManager.getConnection("jdbc:mysql://68.64.164.116:19905/db_pa61_bescalante","bescalante","Bescalante78.");
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Error al intentar conectarse a la base de datos."+e.getMessage());
        }

        if (conexion!=null){
            JOptionPane.showMessageDialog(null,"Conexión exitosa a la base de datos.");
        }
    }
    
    public static void conectarABDSilencioso(){ //Conecta a la BD (silenciosamente)
        try{
                conexion=DriverManager.getConnection("jdbc:mysql://68.64.164.116:19905/db_pa61_bescalante","bescalante","Bescalante78.");
            }catch (SQLException e){
                JOptionPane.showMessageDialog(null, "Error al intentar conectarse a la base de datos."+e.getMessage());
            }
    }
    
    public static ResultSet ListarTabla(String consulta) throws SQLException{ //Conecta a tabla MySQL
        Connection conexion=DriverManager.getConnection("jdbc:mysql://68.64.164.116:19905/db_pa61_bescalante"
                ,"bescalante","Bescalante78.");
        Statement sql;
        ResultSet datos=null;
        try{
            sql=conexion.createStatement();
            datos=sql.executeQuery(consulta);
        } catch (Exception e){
            System.out.println(e);
        }
        return datos;
    }
    
    public static void formatearTabla(){ //Formatea la JTable en función de la tabla MySQL
        DefaultTableModel md=new DefaultTableModel();
        ResultSet rs = null;
        try {
            rs = ListarTabla("select * from Trabajadores");
        } catch (SQLException ex) {
            Logger.getLogger(GestionEmpleados.class.getName()).log(Level.SEVERE, null, ex);
        }
        md.setColumnIdentifiers(new Object[]{"ID","Primer nombre","Segundo nombre",
        "Primer apellido","Segundo apellido","Sexo",
        "Rango","Salario (en USD)",
        "Observaciones","Celular","Correo",
        "Enlace al CV","Fecha de contratación"});
        try{
            while(rs.next()){
                md.addRow(new Object[]{rs.getInt("IdTrabajadores"),
                rs.getString("PrimerNombre"),
                rs.getString("SegundoNombre"),
                rs.getString("PrimerApellido"),
                rs.getString("SegundoApellido"),
                rs.getString("Sexo"),
                rs.getString("Rango"),
                rs.getInt("Salario"),
                rs.getString("Observaciones"),
                rs.getString("Celular"),
                rs.getString("Correo"),
                rs.getString("EnlaceCV"),
                rs.getString("FechaContratacion")});
                    
                Vista.GestionEmpleados.tablaPrincipal.setModel(md);
            }
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public static void BuscarEmpleado(){ //Localiza un empleado por su ID y lo lista en la tabla principal.
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                DefaultTableModel md=new DefaultTableModel();
                ResultSet rs = null;
                String idBuscar=Vista.BuscarEmplados.campoIDBuscar.getText();
                try {
                    rs=ConsultarPlantilla.ListarTabla("select * from Trabajadores where IdTrabajadores="+idBuscar);
                } catch (SQLException ex) {
                    Logger.getLogger(ConsultarPlantilla.class.getName()).log(Level.SEVERE, null, ex);
                }
                    md.setColumnIdentifiers(new Object[]{"ID","Primer nombre","Segundo nombre",
                "Primer apellido","Segundo apellido","Sexo",
                "Rango","Salario (en USD)",
                "Observaciones","Celular","Correo",
                "Enlace al CV","Fecha de contratación"});
            try{
                while(rs.next()){
                    md.addRow(new Object[]{rs.getInt("IdTrabajadores"),
                        rs.getString("PrimerNombre"),
                        rs.getString("SegundoNombre"),
                        rs.getString("PrimerApellido"),
                        rs.getString("SegundoApellido"),
                        rs.getString("Sexo"),
                        rs.getString("Rango"),
                        rs.getInt("Salario"),
                        rs.getString("Observaciones"),
                        rs.getString("Celular"),
                        rs.getString("Correo"),
                        rs.getString("EnlaceCV"),
                        rs.getString("FechaContratacion")});
                    
            Vista.GestionEmpleados.tablaPrincipal.setModel(md);
            Vista.GestionEmpleados.tablaPrincipal.revalidate();
                }
            }catch (Exception e){
                System.out.println(e);
            }
            }
        });
    }
    
    public static void BuscarEmpleadoAModificar(){
        new ModificarEmpleado().setVisible(true); //Muestra la ventana ModificarEmpleado
       
        String id=Vista.BuscarEmpleadoAModificar.campoIDBuscar.getText();

        String sentenciaBuscar="select * from Trabajadores where IdTrabajadores="+id;

        Modelo.modeloGrex.conectarABDSilencioso();
        try{
            Statement st=Modelo.modeloGrex.conexion.createStatement();
            ResultSet rs=st.executeQuery(sentenciaBuscar);
            if(rs.next()){
                ModificarEmpleado.campoID.setText(rs.getString("IdTrabajadores"));
                ModificarEmpleado.campoPrimerNombre.setText(rs.getString("PrimerNombre"));
                ModificarEmpleado.campoSegundoNombre.setText(rs.getString("SegundoNombre"));
                ModificarEmpleado.campoPrimerApellido.setText(rs.getString("PrimerApellido"));
                ModificarEmpleado.campoSegundoApellido.setText(rs.getString("SegundoApellido"));
                ModificarEmpleado.CampoSexo.setText(rs.getString("Sexo"));
                ModificarEmpleado.campoRango.setText(rs.getString("Rango"));
                ModificarEmpleado.campoSalario.setText(rs.getString("Salario"));
                ModificarEmpleado.campoObservaciones.setText(rs.getString("Observaciones"));
                ModificarEmpleado.campoCelular.setText(rs.getString("Celular"));
                ModificarEmpleado.campoCorreo.setText(rs.getString("Correo"));
                ModificarEmpleado.CampoEnlaceCV.setText(rs.getString("EnlaceCV"));
                ModificarEmpleado.campoFechaContratacion.setText(rs.getString("FechaContratacion"));
            } else {
                Vista.BuscarEmpleadoAModificar.campoIDBuscar.setText(rs.getString(""));
            }
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public static void BuscarEmpleadoAEliminar(){
        new EliminarEmpleado().setVisible(true); //Muestra la ventana ModificarEmpleado
        
        String id=Vista.BuscarEmpleadoAEliminar.campoIDBuscar.getText();

        String sentenciaEliminar="select * from Trabajadores where IdTrabajadores="+id;

        Modelo.modeloGrex.conectarABDSilencioso();
        try{
            Statement st=Modelo.modeloGrex.conexion.createStatement();
            ResultSet rs=st.executeQuery(sentenciaEliminar);
            if(rs.next()){
                EliminarEmpleado.campoID.setText(rs.getString("IdTrabajadores"));
                EliminarEmpleado.campoPrimerNombre.setText(rs.getString("PrimerNombre"));
                EliminarEmpleado.campoSegundoNombre.setText(rs.getString("SegundoNombre"));
                EliminarEmpleado.campoPrimerApellido.setText(rs.getString("PrimerApellido"));
                EliminarEmpleado.campoSegundoApellido.setText(rs.getString("SegundoApellido"));
                EliminarEmpleado.CampoSexo.setText(rs.getString("Sexo"));
                EliminarEmpleado.campoRango.setText(rs.getString("Rango"));
                EliminarEmpleado.campoSalario.setText(rs.getString("Salario"));
                EliminarEmpleado.campoCelular.setText(rs.getString("Celular"));
                EliminarEmpleado.campoCorreo.setText(rs.getString("Correo"));
                EliminarEmpleado.CampoEnlaceCV.setText(rs.getString("EnlaceCV"));
                EliminarEmpleado.campoFechaContratacion.setText(rs.getString("FechaContratacion"));
            } else {
                Vista.BuscarEmpleadoAEliminar.campoIDBuscar.setText(rs.getString(""));
            }
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
}


