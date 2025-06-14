package database.dao;

import database.ConnectionFactory;
import database.model.Curso;
import java.sql.*;

public class CursoDAO {

    public void insert(Curso curso) throws SQLException {
        String sql = "INSERT INTO tb_cursos (nome, data_processamento, periodo_inicial, periodo_final, sequencia, versao_layout, arquivo_hash) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = ConnectionFactory.getConnection("localhost", "5432", "postgres", "postgres", "123");
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, curso.getNome());
            ps.setDate(2, Date.valueOf(curso.getDataProcessamento()));
            ps.setString(3, curso.getPeriodoInicial());
            ps.setString(4, curso.getPeriodoFinal());
            ps.setInt(5, curso.getSequencia());
            ps.setString(6, curso.getVersaoLayout());
            ps.setString(7, curso.getArquivoHash());

            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    curso.setId(rs.getInt(1));
                }
            }
        }
    }

    public Curso findById(int id) throws SQLException {
        String sql = "SELECT * FROM tb_cursos WHERE id = ?";
        Curso curso = null;

        try (Connection conn = ConnectionFactory.getConnection("localhost", "5432", "postgres", "postgres", "123");
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    curso = new Curso();
                    curso.setId(rs.getInt("id"));
                    curso.setNome(rs.getString("nome"));
                    curso.setDataProcessamento(rs.getDate("data_processamento").toLocalDate());
                    curso.setPeriodoInicial(rs.getString("periodo_inicial"));
                    curso.setPeriodoFinal(rs.getString("periodo_final"));
                    curso.setSequencia(rs.getInt("sequencia"));
                    curso.setVersaoLayout(rs.getString("versao_layout"));
                    curso.setArquivoHash(rs.getString("arquivo_hash"));
                }
            }
        }
        return curso;
    }

    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM tb_cursos WHERE id = ?";

        try (Connection conn = ConnectionFactory.getConnection("localhost", "5432", "postgres", "postgres", "123");
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }
}
