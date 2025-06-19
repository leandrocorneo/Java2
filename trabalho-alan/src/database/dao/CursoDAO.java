package database.dao;

import database.ConnectionFactory;
import database.model.Curso;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CursoDAO {

    public void insert(Curso curso) {
        try (Connection conn = ConnectionFactory.getConnection("localhost", "5432", "postgres", "postgres", "123")) {
            String sql = "INSERT INTO curso (nome, duracao, id_departamento) VALUES (?, ?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                stmt.setString(1, curso.getNome());
                stmt.setInt(2, curso.getDuracao());
                stmt.setInt(3, curso.getDepartamentoId());

                int affectedRows = stmt.executeUpdate();
                if (affectedRows > 0) {
                    try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                        if (generatedKeys.next()) {
                            curso.setId(generatedKeys.getInt(1));
                        }
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(Curso curso) {
        try (Connection conn = ConnectionFactory.getConnection("localhost", "5432", "postgres", "postgres", "123")) {
            String sql = "UPDATE curso SET nome = ?, duracao = ?, id_departamento = ? WHERE id_curso = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, curso.getNome());
                stmt.setInt(2, curso.getDuracao());
                stmt.setInt(3, curso.getDepartamentoId());
                stmt.setInt(4, curso.getId());
                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        try (Connection conn = ConnectionFactory.getConnection("localhost", "5432", "postgres", "postgres", "123")) {
            String sql = "DELETE FROM curso WHERE id_curso = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, id);
                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Curso findById(int id) {
        Curso curso = null;
        try (Connection conn = ConnectionFactory.getConnection("localhost", "5432", "postgres", "postgres", "123")) {
            String sql = "SELECT * FROM curso WHERE id_curso = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, id);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        curso = new Curso();
                        curso.setId(rs.getInt("id_curso"));
                        curso.setNome(rs.getString("nome"));
                        curso.setDuracao(rs.getInt("duracao"));
                        curso.setDepartamentoId(rs.getInt("id_departamento"));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return curso;
    }

    public List<Curso> findAll() {
        List<Curso> cursos = new ArrayList<>();
        try (Connection conn = ConnectionFactory.getConnection("localhost", "5432", "postgres", "postgres", "123")) {
            String sql = "SELECT * FROM curso";
            try (PreparedStatement stmt = conn.prepareStatement(sql);
                 ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Curso curso = new Curso();
                    curso.setId(rs.getInt("id_curso"));
                    curso.setNome(rs.getString("nome"));
                    curso.setDuracao(rs.getInt("duracao"));
                    curso.setDepartamentoId(rs.getInt("id_departamento"));
                    cursos.add(curso);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cursos;
    }
}
