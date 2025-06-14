package database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import database.ConnectionFactory;
import database.model.Professor;

public class ProfessorDAO {

    public void insert(Professor professor) throws SQLException {
        String sql = "INSERT INTO tb_professores (disciplina_id, nome, titulo_docente) VALUES (?, ?, ?)";

        try (Connection conn = ConnectionFactory.getConnection("localhost", "5432", "postgres", "postgres", "123");
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, professor.getDisciplinaId());
            ps.setString(2, professor.getNome());
            ps.setInt(3, professor.getTituloDocente());

            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    professor.setId(rs.getInt(1));
                }
            }
        }
    }

    public Professor findById(int id) throws SQLException {
        String sql = "SELECT * FROM tb_professores WHERE id = ?";
        Professor professor = null;

        try (Connection conn = ConnectionFactory.getConnection("localhost", "5432", "postgres", "postgres", "123");
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    professor = new Professor();
                    professor.setId(rs.getInt("id"));
                    professor.setDisciplinaId(rs.getInt("disciplina_id"));
                    professor.setNome(rs.getString("nome"));
                    professor.setTituloDocente(rs.getInt("titulo_docente"));
                }
            }
        }
        return professor;
    }

    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM tb_professores WHERE id = ?";

        try (Connection conn = ConnectionFactory.getConnection("localhost", "5432", "postgres", "postgres", "123");
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }
}
