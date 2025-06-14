package database.dao;

import database.ConnectionFactory;
import database.model.Matricula;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MatriculaDAO {

    public void insert(Matricula matricula) {
        String sql = "INSERT INTO tb_matriculas (aluno_id, curso_id, data_matricula) VALUES (?, ?, ?)";
        try (Connection conn = ConnectionFactory.getConnection("localhost", "5432", "postgres", "postgres", "123");
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1, matricula.getAlunoId());

            int affectedRows = stmt.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        matricula.setId(rs.getInt(1));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(Matricula matricula) {
        String sql = "UPDATE tb_matriculas SET aluno_id = ?, curso_id = ?, data_matricula = ? WHERE id = ?";
        try (Connection conn = ConnectionFactory.getConnection("localhost", "5432", "postgres", "postgres", "123");
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, matricula.getAlunoId());
            stmt.setInt(4, matricula.getId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        String sql = "DELETE FROM tb_matriculas WHERE id = ?";
        try (Connection conn = ConnectionFactory.getConnection("localhost", "5432", "postgres", "postgres", "123");
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Matricula findById(int id) {
        Matricula matricula = null;
        String sql = "SELECT * FROM tb_matriculas WHERE id = ?";
        try (Connection conn = ConnectionFactory.getConnection("localhost", "5432", "postgres", "postgres", "123");
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    matricula = new Matricula();
                    matricula.setId(rs.getInt("id"));
                    matricula.setAlunoId(rs.getInt("aluno_id"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return matricula;
    }

    public List<Matricula> findAll() {
        List<Matricula> matriculas = new ArrayList<>();
        String sql = "SELECT * FROM tb_matriculas";
        try (Connection conn = ConnectionFactory.getConnection("localhost", "5432", "postgres", "postgres", "123");
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

                


            while (rs.next()) {
                Matricula matricula = new Matricula();
                matricula.setId(rs.getInt("id"));
                matricula.setAlunoId(rs.getInt("aluno_id"));
                matriculas.add(matricula);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return matriculas;
    }
}
