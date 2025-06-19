package database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import database.ConnectionFactory;
import database.model.Professor;

public class ProfessorDAO {

    public void insert(Professor professor) {
        try (Connection conn = ConnectionFactory.getConnection("localhost", "5432", "postgres", "postgres", "123")) {
            String sql = "INSERT INTO professor (nome, titulacao, id_departamento) VALUES (?, ?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                stmt.setString(1, professor.getNome());
                stmt.setString(2, professor.getTitulacao());
                stmt.setInt(3, professor.getDepartamentoId());

                int affectedRows = stmt.executeUpdate();
                if (affectedRows > 0) {
                    try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                        if (generatedKeys.next()) {
                            professor.setId(generatedKeys.getInt(1));
                        }
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(Professor professor) {
        try (Connection conn = ConnectionFactory.getConnection("localhost", "5432", "postgres", "postgres", "123")) {
            String sql = "UPDATE professor SET nome = ?, titulacao = ?, id_departamento = ? WHERE id_professor = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, professor.getNome());
                stmt.setString(2, professor.getTitulacao());
                stmt.setInt(3, professor.getDepartamentoId());
                stmt.setInt(4, professor.getId());

                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        try (Connection conn = ConnectionFactory.getConnection("localhost", "5432", "postgres", "postgres", "123")) {
            String sql = "DELETE FROM professor WHERE id_professor = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, id);
                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Professor findById(int id) {
        Professor professor = null;
        try (Connection conn = ConnectionFactory.getConnection("localhost", "5432", "postgres", "postgres", "123")) {
            String sql = "SELECT * FROM professor WHERE id_professor = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, id);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        professor = new Professor();
                        professor.setId(rs.getInt("id_professor"));
                        professor.setNome(rs.getString("nome"));
                        professor.setTitulacao(rs.getString("titulacao"));
                        professor.setDepartamentoId(rs.getInt("id_departamento"));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return professor;
    }

    public List<Professor> findAll() {
        List<Professor> professors = new ArrayList<>();
        try (Connection conn = ConnectionFactory.getConnection("localhost", "5432", "postgres", "postgres", "123")) {
            String sql = "SELECT * FROM professor";
            try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
                while (rs.next()) {
                    Professor professor = new Professor();
                    professor.setId(rs.getInt("id_professor"));
                    professor.setNome(rs.getString("nome"));
                    professor.setTitulacao(rs.getString("titulacao"));
                    professor.setDepartamentoId(rs.getInt("id_departamento"));
                    professors.add(professor);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return professors;
    }
}
