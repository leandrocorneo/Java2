package database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import database.ConnectionFactory;
import database.model.Fase;

public class FaseDAO {

    public void insert(Fase fase) throws SQLException {
        String sql = "INSERT INTO tb_fases (curso_id, fase, qtd_disciplinas, qtd_professores) VALUES (?, ?, ?, ?)";

        try (Connection conn = ConnectionFactory.getConnection("localhost", "5432", "postgres", "postgres", "123");
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, fase.getCursoId());
            ps.setString(2, fase.getFase());
            ps.setInt(3, fase.getQtdDisciplinas());
            ps.setInt(4, fase.getQtdProfessores());

            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    fase.setId(rs.getInt(1));
                }
            }
        }
    }

    public Fase findById(int id) throws SQLException {
        String sql = "SELECT * FROM tb_fases WHERE id = ?";
        Fase fase = null;

        try (Connection conn = ConnectionFactory.getConnection("localhost", "5432", "postgres", "postgres", "123");
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    fase = new Fase();
                    fase.setId(rs.getInt("id"));
                    fase.setCursoId(rs.getInt("curso_id"));
                    fase.setFase(rs.getString("fase"));
                    fase.setQtdDisciplinas(rs.getInt("qtd_disciplinas"));
                    fase.setQtdProfessores(rs.getInt("qtd_professores"));
                }
            }
        }
        return fase;
    }

    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM tb_fases WHERE id = ?";

        try (Connection conn = ConnectionFactory.getConnection("localhost", "5432", "postgres", "postgres", "123");
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }
}