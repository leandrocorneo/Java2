package database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;

import database.ConnectionFactory;
import database.model.Turma;

public class TurmaDAO {
    public void insert(Turma turma) {
        try (Connection conn = ConnectionFactory.getConnection("localhost", "5432", "postgres", "postgres", "123")) {
            String sql = "INSERT INTO turma (id_disciplina, id_professor) VALUES (?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                stmt.setInt(1, turma.getDisciplinaId());
                stmt.setInt(2, turma.getProfessorId());
                int affectedRows = stmt.executeUpdate();
                if (affectedRows > 0) {
                    try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                        if (generatedKeys.next()) {
                            turma.setId(generatedKeys.getInt(1));
                        }
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(Turma turma) {
        try (Connection conn = ConnectionFactory.getConnection("localhost", "5432", "postgres", "postgres", "123")) {
            String sql = "UPDATE turma SET id_disciplina = ?, id_professor = ? WHERE id_turma = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, turma.getDisciplinaId());
                stmt.setInt(2, turma.getProfessorId());
                stmt.setInt(3, turma.getId());
                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        try (Connection conn = ConnectionFactory.getConnection("localhost", "5432", "postgres", "postgres", "123")) {
            String sql = "DELETE FROM turma WHERE id_turma = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, id);
                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Turma findById(int id) {
        Turma turma = null;
        try (Connection conn = ConnectionFactory.getConnection("localhost", "5432", "postgres", "postgres", "123")) {
            String sql = "SELECT * FROM turma WHERE id_turma = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, id);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        turma = new Turma();
                        turma.setId(rs.getInt("id_turma"));
                        turma.setDisciplinaId(rs.getInt("id_disciplina"));
                        turma.setProfessorId(rs.getInt("id_professor"));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return turma;
    }

    public java.util.List<Turma> findAll() {
        java.util.List<Turma> turmas = new java.util.ArrayList<>();
        try (Connection conn = ConnectionFactory.getConnection("localhost", "5432", "postgres", "postgres", "123")) {
            String sql = "SELECT * FROM turma";
            try (PreparedStatement stmt = conn.prepareStatement(sql);
                 ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Turma turma = new Turma();
                    turma.setId(rs.getInt("id_turma"));
                    turma.setDisciplinaId(rs.getInt("id_disciplina"));
                    turma.setProfessorId(rs.getInt("id_professor"));
                    turmas.add(turma);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return turmas;
    }
}
