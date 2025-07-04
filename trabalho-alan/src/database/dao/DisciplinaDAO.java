package database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import database.ConnectionFactory;
import database.model.Disciplina;

public class DisciplinaDAO {

    public void insert(Disciplina disciplina) {
        try (Connection conn = ConnectionFactory.getConnection("localhost", "5432", "postgres", "postgres", "123")) {
            String sql = "INSERT INTO disciplina (nome) VALUES (?)";
            try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                stmt.setString(1, disciplina.getNome());

                int affectedRows = stmt.executeUpdate();
                if (affectedRows > 0) {
                    try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                        if (generatedKeys.next()) {
                            disciplina.setId(generatedKeys.getInt(1));
                        }
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(Disciplina disciplina) {
        try (Connection conn = ConnectionFactory.getConnection("localhost", "5432", "postgres", "postgres", "123")) {
            String sql = "UPDATE disciplina SET nome = ? WHERE id_disciplina = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, disciplina.getNome());
                stmt.setInt(2, disciplina.getId());
                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        try (Connection conn = ConnectionFactory.getConnection("localhost", "5432", "postgres", "postgres", "123")) {
            String sql = "DELETE FROM disciplina WHERE id_disciplina = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, id);
                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Disciplina findById(int id) {
        Disciplina disciplina = null;
        try (Connection conn = ConnectionFactory.getConnection("localhost", "5432", "postgres", "postgres", "123")) {
            String sql = "SELECT * FROM disciplina WHERE id_disciplina = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, id);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        disciplina = new Disciplina();
                        disciplina.setId(rs.getInt("id_disciplina"));
                        disciplina.setNome(rs.getString("nome"));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return disciplina;
    }

    public List<Disciplina> findAll() {
        List<Disciplina> disciplinas = new ArrayList<>();
        try (Connection conn = ConnectionFactory.getConnection("localhost", "5432", "postgres", "postgres", "123")) {
            String sql = "SELECT * FROM disciplina";
            try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
                while (rs.next()) {
                    Disciplina disciplina = new Disciplina();
                    disciplina.setId(rs.getInt("id_disciplina"));
                    disciplina.setNome(rs.getString("nome"));
                    disciplinas.add(disciplina);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return disciplinas;
    }
}
