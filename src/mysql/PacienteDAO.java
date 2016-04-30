/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mysql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Andrea Itzhel
 */
public class PacienteDAO {
    
     public void agregarPaciente(Paciente pa) {
        DBConec conn = new DBConec();
        
        try {
            Statement consulta = conn.getConnection().createStatement();
            String sql = "INSERT INTO pacientes VALUES('" + pa.getId() + "', '" +
                    pa.getNombre() + "', '" + pa.getCurp() + "', '" + pa.getDomicilio() + "', " +
                    pa.getTel() + ")";
            consulta.executeUpdate(sql);
            JOptionPane.showMessageDialog(null,"Se ha registrado el paciente");
            consulta.close();
            conn.desconectar();
        } catch(SQLException e) {
            JOptionPane.showMessageDialog(null, "No se registro la persona " + e);
        }
     }
        
    /**
     *
     * @return
     */
    public ArrayList<Paciente> obtenPaciente() {
        ArrayList <Paciente> arrPac = new ArrayList<Paciente>();
        DBConec conn = new DBConec();
        String sql = "SELECT * FROM pacientes";
        
        try {
            PreparedStatement st = conn.getConnection().prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            
            while(rs.next()) {
                Paciente pa = new Paciente(
                    rs.getString("id"),
                    rs.getString("nombre"),
                    rs.getString("curp"),
                    rs.getString("domicilio"),
                    rs.getString("tel")
                );
                arrPac.add(pa);
            }
            rs.close();
            st.close();
            conn.desconectar();
            
        } catch(Exception e) {
            
        }
        return arrPac;
    }
    
}
