package org.example.BDEmpleados;

import org.example.BDJuguetes.DTOJuguete;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DAODepartamento {
    private final Connection conn;

    public DAODepartamento() throws SQLException {
        conn = DB.getConnection();
    }

    public ArrayList<DTODepartamento> selectAllDepartamentos() throws SQLException {
        ArrayList<DTODepartamento> listaDepartamentos = new ArrayList<>();
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM departamento");

        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            int id = rs.getInt(1);
            String nombre = rs.getString(2);

            DTODepartamento departamento = new DTODepartamento(id, nombre);
            listaDepartamentos.add(departamento);

        }
        return listaDepartamentos;
    }

    public DTODepartamento selectDepartamentoById(int id) throws SQLException {
        DTODepartamento departamento = null;

        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM departamento WHERE ID = ?");
        stmt.setInt(1, id);

        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            int idDepartamento = rs.getInt(1);
            String nombre = rs.getString(2);

            departamento = new DTODepartamento(idDepartamento, nombre);
        }
        return departamento;
    }

    public DTODepartamento selectDepartamentoByName(String nombre) throws SQLException {
        DTODepartamento departamento = null;

        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM departamento WHERE Nombre = ?");
        stmt.setString(1, nombre);

        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            int idDepartamento = rs.getInt(1);
            String nombreDepartamento = rs.getString(2);

            departamento = new DTODepartamento(idDepartamento, nombreDepartamento);
        }
        return departamento;
    }

    public int addDepartamento(DTODepartamento departamento) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO departamento (ID, Nombre) VALUES (?, ?)");
        stmt.setInt(1, departamento.getId());
        stmt.setString(2, departamento.getNombre());

        return stmt.executeUpdate();
    }

    public int updateDepartamento(DTODepartamento departamento) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("UPDATE departamento SET Nombre = ? WHERE ID = ?");
        stmt.setString(1, departamento.getNombre());
        stmt.setInt(2, departamento.getId());

        return stmt.executeUpdate();
    }

    public int deleteDepartamentoById(int id) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("DELETE FROM departamento WHERE ID = ?");
        stmt.setInt(1, id);

        return stmt.executeUpdate();
    }

    public int deleteDepartamentoByName(String nombre) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("DELETE FROM departamento WHERE Nombre = ?");
        stmt.setString(1, nombre);

        return stmt.executeUpdate();
    }

    public int deleteAllDepartamentos() throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("DELETE FROM departamento");

        return stmt.executeUpdate();
    }
}