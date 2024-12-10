package org.example.BDEmpleados;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DAOEmpleado {
    private Connection conn;

    public DAOEmpleado() throws SQLException {
        conn = DB.getConnection();
    }

    public ArrayList<DTOEmpleado> selectAll() throws SQLException {
        ArrayList<DTOEmpleado> listaEmpleados = new ArrayList<>();

        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM empleados");

        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            int id = rs.getInt("id");
            String nombre = rs.getString("nombre");
            int edad = rs.getInt("edad");
            int dptoId = rs.getInt("dpto_id");

            DTOEmpleado empleado = new DTOEmpleado(id, nombre, edad, dptoId);
            listaEmpleados.add(empleado);
        }
        return listaEmpleados;
    }

    public ArrayList<DTOEmpleado> selectAllByIdDpto(int idDpto) throws SQLException {
        ArrayList<DTOEmpleado> listaEmpleados = new ArrayList<>();

        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM empleados WHERE dpto_id = ?");
        stmt.setInt(1, idDpto);

        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            int id = rs.getInt("id");
            String nombre = rs.getString("nombre");
            int edad = rs.getInt("edad");
            int dptoId = rs.getInt("dpto_id");

            DTOEmpleado empleado = new DTOEmpleado(id, nombre, edad, dptoId);
            listaEmpleados.add(empleado);
        }
        return listaEmpleados;
    }

    public DTOEmpleado selectEmpleadoById(int id) throws SQLException {
        DTOEmpleado empleado = null;

        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM empleados WHERE id = ?");
        stmt.setInt(1, id);

        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            int idEmpleado = rs.getInt("id");
            String nombre = rs.getString("nombre");
            int edad = rs.getInt("edad");
            int dptoId = rs.getInt("dpto_id");

            empleado = new DTOEmpleado(idEmpleado, nombre, edad, dptoId);
        }
        return empleado;
    }

    public DTOEmpleado selectEmpleadoByNombre(String nombre) throws SQLException {
        DTOEmpleado empleado = null;

        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM empleados WHERE nombre = ?");
        stmt.setString(1, nombre);

        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            int id = rs.getInt("id");
            String nombreEmpleado = rs.getString("nombre");
            int edad = rs.getInt("edad");
            int dptoId = rs.getInt("dpto_id");

            empleado = new DTOEmpleado(id, nombreEmpleado, edad, dptoId);
        }
        return empleado;
    }

    public int addEmpleado(DTOEmpleado empleado) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO empleados (id, nombre, edad, dpto_id) VALUES (?, ?, ?, ?)");
        stmt.setInt(1, empleado.getId());
        stmt.setString(2, empleado.getNombre());
        stmt.setInt(3, empleado.getEdad());
        stmt.setInt(4, empleado.getDptoId());

        return stmt.executeUpdate();
    }

    public int updateEmpleado(DTOEmpleado empleado) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("UPDATE empleados SET nombre = ?, edad = ?, dpto_id = ? WHERE id = ?");
        stmt.setString(1, empleado.getNombre());
        stmt.setInt(2, empleado.getEdad());
        stmt.setInt(3, empleado.getDptoId());
        stmt.setInt(4, empleado.getId());

        return stmt.executeUpdate();
    }

    public int deleteEmpleadoById(int id) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("DELETE FROM empleados WHERE id = ?");
        stmt.setInt(1, id);

        return stmt.executeUpdate();
    }

    public int deleteEmpleadoByName(String nombre) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("DELETE FROM empleados WHERE nombre = ?");
        stmt.setString(1, nombre);

        return stmt.executeUpdate();
    }

    public int deleteAllEmpleados() throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("DELETE FROM empleados");

        return stmt.executeUpdate();
    }
}
