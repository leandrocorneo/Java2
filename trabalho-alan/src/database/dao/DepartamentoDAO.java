package database.dao;

import database.ConnectionFactory;
import database.model.Departamento;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DepartamentoDAO {

    public void insert(Departamento departamento) {
        try (Connection conn = ConnectionFactory.getConnection("localhost", "5432", "postgres", "postgres", "123")) {
            String sql = "INSERT INTO departamento (nome) VALUES (?)";
            try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                stmt.setString(1, departamento.getNome());

                int affectedRows = stmt.executeUpdate();
                if (affectedRows > 0) {
                    try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                        if (generatedKeys.next()) {
                            departamento.setId(generatedKeys.getInt(1));
                        }
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(Departamento departamento) {
        try (Connection conn = ConnectionFactory.getConnection("localhost", "5432", "postgres", "postgres", "123")) {
            String sql = "UPDATE departamento SET nome = ? WHERE id_departamento = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, departamento.getNome());
                stmt.setInt(2, departamento.getId());
                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        try (Connection conn = ConnectionFactory.getConnection("localhost", "5432", "postgres", "postgres", "123")) {
            String sql = "DELETE FROM departamento WHERE id_departamento = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, id);
                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Departamento> findAll() {
        List<Departamento> departamentos = new ArrayList<>();
        try (Connection conn = ConnectionFactory.getConnection("localhost", "5432", "postgres", "postgres", "123")) {
            String sql = "SELECT * FROM departamento";
            try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
                while (rs.next()) {
                    Departamento departamento = new Departamento();
                    departamento.setId(rs.getInt("id_departamento"));
                    departamento.setNome(rs.getString("nome"));
                    departamentos.add(departamento);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return departamentos;
    }

    public Departamento findById(int id) {
        Departamento departamento = null;
        try (Connection conn = ConnectionFactory.getConnection("localhost", "5432", "postgres", "postgres", "123")) {
            String sql = "SELECT * FROM departamento WHERE id_departamento = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, id);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        departamento = new Departamento();
                        departamento.setId(rs.getInt("id_departamento"));
                        departamento.setNome(rs.getString("nome"));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return departamento;
    }
}
