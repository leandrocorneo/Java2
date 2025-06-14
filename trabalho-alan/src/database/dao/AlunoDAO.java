package database.dao;

import database.ConnectionFactory;
import database.model.Aluno;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AlunoDAO {

    public void insert(Aluno aluno) {
        String sql = "INSERT INTO tb_alunos (nome, matricula) VALUES (?, ?)";
        try (Connection conn = ConnectionFactory.getConnection("localhost", "5432", "postgres", "postgres", "123");
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, aluno.getNome());

            int affectedRows = stmt.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        aluno.setId(rs.getInt(1));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(Aluno aluno) {
        String sql = "UPDATE tb_alunos SET nome = ?, matricula = ? WHERE id = ?";
        try (Connection conn = ConnectionFactory.getConnection("localhost", "5432", "postgres", "postgres", "123");
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, aluno.getNome());
            stmt.setInt(3, aluno.getId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        String sql = "DELETE FROM tb_alunos WHERE id = ?";
        try (Connection conn = ConnectionFactory.getConnection("localhost", "5432", "postgres", "postgres", "123");
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Aluno findById(int id) {
        Aluno aluno = null;
        String sql = "SELECT * FROM tb_alunos WHERE id = ?";
        try (Connection conn = ConnectionFactory.getConnection("localhost", "5432", "postgres", "postgres", "123");
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    aluno = new Aluno();
                    aluno.setId(rs.getInt("id"));
                    aluno.setNome(rs.getString("nome"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return aluno;
    }

    public List<Aluno> findAll() {
        List<Aluno> alunos = new ArrayList<>();
        String sql = "SELECT * FROM tb_alunos";
        try (Connection conn = ConnectionFactory.getConnection("localhost", "5432", "postgres", "postgres", "123");
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Aluno aluno = new Aluno();
                aluno.setId(rs.getInt("id"));
                aluno.setNome(rs.getString("nome"));
                alunos.add(aluno);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return alunos;
    }
}
