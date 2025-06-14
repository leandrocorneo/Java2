package database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import database.ConnectionFactory;
import database.model.Disciplina;

public class DisciplinaDAO {

    public void insert(Disciplina disciplina) throws SQLException {
        String sql = "INSERT INTO tb_disciplinas (fase_id, codigo, dia_semana, qtd_professores) VALUES (?, ?, ?, ?)";

        try (Connection conn = ConnectionFactory.getConnection("localhost", "5432", "postgres", "postgres", "123");
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, disciplina.getFaseId());
            ps.setString(2, disciplina.getCodigo());
            ps.setInt(3, disciplina.getDiaSemana());
            ps.setInt(4, disciplina.getQtdProfessores());

            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    disciplina.setId(rs.getInt(1));
                }
            }
        }
    }

    public Disciplina findById(int id) throws SQLException {
        String sql = "SELECT * FROM tb_disciplinas WHERE id = ?";
        Disciplina disciplina = null;

        try (Connection conn = ConnectionFactory.getConnection("localhost", "5432", "postgres", "postgres", "123");
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    disciplina = new Disciplina();
                    disciplina.setId(rs.getInt("id"));
                    disciplina.setFaseId(rs.getInt("fase_id"));
                    disciplina.setCodigo(rs.getString("codigo"));
                    disciplina.setDiaSemana(rs.getInt("dia_semana"));
                    disciplina.setQtdProfessores(rs.getInt("qtd_professores"));
                }
            }
        }
        return disciplina;
    }

    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM tb_disciplinas WHERE id = ?";

        try (Connection conn = ConnectionFactory.getConnection("localhost", "5432", "postgres", "postgres", "123");
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }
}