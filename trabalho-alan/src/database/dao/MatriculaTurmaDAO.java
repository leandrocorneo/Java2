package database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import database.ConnectionFactory;
import database.model.MatriculaTurma;

public class MatriculaTurmaDAO {
    public void insert(MatriculaTurma matriculaTurma){
        try(Connection conn = ConnectionFactory.getConnection("localhost", "5432", "postgres", "postgres", "123")){
            String sql = "INSERT INTO matriculaturma (id_matricula, id_turma) VALUES (?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, matriculaTurma.getMatriculaId());
                stmt.setInt(2, matriculaTurma.getTurmaId());
                
                stmt.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delete(MatriculaTurma matriculaTurma) {
        try (Connection conn = ConnectionFactory.getConnection("localhost", "5432", "postgres", "postgres", "123")) {
            String sql = "DELETE FROM matriculaturma WHERE id_matricula = ? AND id_turma = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, matriculaTurma.getMatriculaId());
                stmt.setInt(2, matriculaTurma.getTurmaId());
                
                stmt.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteByMatriculaId(int matriculaId) {
        try (Connection conn = ConnectionFactory.getConnection("localhost", "5432", "postgres", "postgres", "123")) {
            String sql = "DELETE FROM matriculaturma WHERE id_matricula = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, matriculaId);
                
                stmt.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteByTurmaId(int turmaId) {
        try (Connection conn = ConnectionFactory.getConnection("localhost", "5432", "postgres", "postgres", "123")) {
            String sql = "DELETE FROM matriculaturma WHERE id_turma = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, turmaId);
                
                stmt.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteAll() {
        try (Connection conn = ConnectionFactory.getConnection("localhost", "5432", "postgres", "postgres", "123")) {
            String sql = "DELETE FROM matriculaturma";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
