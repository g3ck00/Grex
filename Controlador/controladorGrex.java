/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Vista.*;
import java.sql.*;
import javax.swing.JOptionPane;

/**
 *
 * @author Bryantcore
 */
public class controladorGrex {
    public static void AgregarEmpleado(){
        String id=Vista.AgregarEmpleados.campoID.getText();
        String primerNombre=Vista.AgregarEmpleados.campoPrimerNombre.getText();
        String segundoNombre=Vista.AgregarEmpleados.campoSegundoNombre.getText();
        String primerApellido=Vista.AgregarEmpleados.campoPrimerApellido.getText();
        String segundoApellido=Vista.AgregarEmpleados.campoSegundoApellido.getText();
        String sexo=Vista.AgregarEmpleados.CampoSexo.getText();
        String rango=Vista.AgregarEmpleados.campoRango.getText();
        String observaciones=Vista.AgregarEmpleados.campoObservaciones.getText();
        String salario=Vista.AgregarEmpleados.campoSalario.getText();
        String celular=Vista.AgregarEmpleados.campoCelular.getText();
        String correo=Vista.AgregarEmpleados.campoCorreo.getText();
        String enlaceCV=Vista.AgregarEmpleados.CampoEnlaceCV.getText();
        String fechaContratacion=Vista.AgregarEmpleados.campoFechaContratacion.getText();

        String sentenciaInsertar="insert into Trabajadores (IdTrabajadores,PrimerNombre,SegundoNombre,"
        + "PrimerApellido,SegundoApellido,Sexo,"
        + "Rango,Observaciones, Salario,"
        + "Celular,Correo,EnlaceCV,FechaContratacion) values ("+id+",'"+primerNombre+"','"+segundoNombre+"','"
        +primerApellido+"','"+segundoApellido+"','"+sexo+"','"
        +rango+"','"+observaciones+"',"+salario+","
        +celular+",'"+correo+"','"+enlaceCV+"','"+fechaContratacion+"');";
        Modelo.modeloGrex.conectarABDSilencioso();
        try {
            int confirmarAgregar=JOptionPane.showConfirmDialog(null, "¿Está seguro de que desea agregar este registro?", "Confirmar nuevo registro",JOptionPane.YES_NO_OPTION);
            if (confirmarAgregar==JOptionPane.YES_OPTION){
                Statement st = Modelo.modeloGrex.conexion.createStatement();
                st.executeUpdate(sentenciaInsertar);
                JOptionPane.showMessageDialog(null,"Registros agregados correctamente.","Registro con éxito",JOptionPane.INFORMATION_MESSAGE);
                Modelo.modeloGrex.formatearTabla();
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public static void ModificarEmpleado(){
        String idOriginal=BuscarEmpleadoAModificar.campoIDBuscar.getText();
        
        String id=Vista.ModificarEmpleado.campoID.getText();
        String primerNombre=Vista.ModificarEmpleado.campoPrimerNombre.getText();
        String segundoNombre=Vista.ModificarEmpleado.campoSegundoNombre.getText();
        String primerApellido=Vista.ModificarEmpleado.campoPrimerApellido.getText();
        String segundoApellido=Vista.ModificarEmpleado.campoSegundoApellido.getText();
        String sexo=Vista.ModificarEmpleado.CampoSexo.getText();
        String rango=Vista.ModificarEmpleado.campoRango.getText();
        String observaciones=Vista.ModificarEmpleado.campoObservaciones.getText();
        String salario=Vista.ModificarEmpleado.campoSalario.getText();
        String celular=Vista.ModificarEmpleado.campoCelular.getText();
        String correo=Vista.ModificarEmpleado.campoCorreo.getText();
        String enlaceCV=Vista.ModificarEmpleado.CampoEnlaceCV.getText();
        String fechaContratacion=Vista.ModificarEmpleado.campoFechaContratacion.getText();

        String sentenciaModificar="update Trabajadores set IdTrabajadores="+id+",PrimerNombre='"+primerNombre+"',SegundoNombre='"+segundoNombre+
                "',PrimerApellido='"+primerApellido+"',SegundoApellido='"+segundoApellido+"',Sexo='"+sexo+
                "',Rango='"+rango+"',Salario="+salario+",Observaciones='"+observaciones+"',Celular="
                +celular+",Correo='"+correo+"',EnlaceCV='"+enlaceCV
                +"',FechaContratacion='"+fechaContratacion+"' where IdTrabajadores="+idOriginal;
        try {
            int confirmarModificar=JOptionPane.showConfirmDialog(null, "¿Está seguro de que desea modificar los datos de este empleado?", "Confirmar modificación",JOptionPane.YES_NO_OPTION);
            if (confirmarModificar==JOptionPane.YES_OPTION){
                Statement st = Modelo.modeloGrex.conexion.createStatement();
                st.executeUpdate(sentenciaModificar);
                JOptionPane.showMessageDialog(null,"Los datos del empleado fueron modificados con éxito.","Datos modificados con éxito",JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public static void EliminarEmpleado(){
        String id=Vista.EliminarEmpleado.campoID.getText();
        String primerNombre=Vista.EliminarEmpleado.campoPrimerNombre.getText();
        String segundoNombre=Vista.EliminarEmpleado.campoSegundoNombre.getText();
        String primerApellido=Vista.EliminarEmpleado.campoPrimerApellido.getText();
        String segundoApellido=Vista.EliminarEmpleado.campoSegundoApellido.getText();
        String sexo=Vista.EliminarEmpleado.CampoSexo.getText();
        String rango=Vista.EliminarEmpleado.campoRango.getText();
        String observaciones=Vista.EliminarEmpleado.campoObservaciones.getText();
        String salario=Vista.EliminarEmpleado.campoSalario.getText();
        String celular=Vista.EliminarEmpleado.campoCelular.getText();
        String correo=Vista.EliminarEmpleado.campoCorreo.getText();
        String enlaceCV=Vista.EliminarEmpleado.CampoEnlaceCV.getText();
        String fechaContratacion=Vista.EliminarEmpleado.campoFechaContratacion.getText();

        String sentenciaEliminar="delete from Trabajadores where IdTrabajadores="+id;
        try {
            int confirmarEliminar=JOptionPane.showConfirmDialog(null, "¿Está seguro de que desea eliminar los registros de este empleado? La información se perderá para siempre.", "Confirmar eliminación",JOptionPane.YES_NO_OPTION);
            if (confirmarEliminar==JOptionPane.YES_OPTION){
                Statement st = Modelo.modeloGrex.conexion.createStatement();
                st.executeUpdate(sentenciaEliminar);
                JOptionPane.showMessageDialog(null,"Registros eliminados.","",JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    //C(R)UD para tabla Aspirantes
    
    /* In many ways, they'll miss the good old days
    Someday
    Someday */
}
