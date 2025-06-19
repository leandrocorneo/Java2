package database.dao;

import database.ConnectionFactory;
import database.model.Matricula;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MatriculaDAO {

    public void insert(Matricula matricula) {
        try (Connection conn = ConnectionFactory.getConnection("localhost", "5432", "postgres", "postgres", "123")) {
            String sql = "INSERT INTO matricula (nome, cpf, status, id_curso) VALUES (?, ?, ?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                stmt.setString(1, matricula.getNome());
                stmt.setString(2, matricula.getCpf());
                stmt.setString(3, matricula.getStatus());
                stmt.setInt(4, matricula.getCursoId());

                int affectedRows = stmt.executeUpdate();
                if (affectedRows > 0) {
                    try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                        if (generatedKeys.next()) {
                            matricula.setId(generatedKeys.getInt(1));
                        }
                    }
                }
            }  
        } catch (SQLException e) {
            e.printStackTrace();  
        }
    }
    
    public void update(Matricula matricula) {
        try (Connection conn = ConnectionFactory.getConnection("localhost", "5432", "postgres", "postgres", "123")) {
            String sql = "UPDATE matricula SET nome = ?, cpf = ?, status = ?, id_curso = ? WHERE id_matricula = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, matricula.getNome());
                stmt.setString(2, matricula.getCpf());
                stmt.setString(3, matricula.getStatus());
                stmt.setInt(4, matricula.getCursoId());
                stmt.setInt(5, matricula.getId());

                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        try (Connection conn = ConnectionFactory.getConnection("localhost", "5432", "postgres", "postgres", "123")) {
            String sql = "DELETE FROM matricula WHERE id_matricula = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, id);
                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }   

    public Matricula findById(int id) {
        Matricula matricula = null;
        try (Connection conn = ConnectionFactory.getConnection("localhost", "5432", "postgres", "postgres", "123")) {
            String sql = "SELECT * FROM matricula WHERE id_matricula = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, id);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        matricula = new Matricula();
                        matricula.setId(rs.getInt("id_matricula"));
                        matricula.setNome(rs.getString("nome"));
                        matricula.setCpf(rs.getString("cpf"));
                        matricula.setStatus(rs.getString("status"));
                        matricula.setCursoId(rs.getInt("id_curso"));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();    
        }
        return matricula;
    }

    public List<Matricula> findAll() {
        List<Matricula> matriculas = new ArrayList<>();
        try (Connection conn = ConnectionFactory.getConnection("localhost", "5432", "postgres", "postgres", "123")) {
            String sql = "SELECT * FROM matricula";
            try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
                while (rs.next()) {
                    Matricula matricula = new Matricula();
                    matricula.setId(rs.getInt("id_matricula"));
                    matricula.setNome(rs.getString("nome"));
                    matricula.setCpf(rs.getString("cpf"));
                    matricula.setStatus(rs.getString("status"));
                    matricula.setCursoId(rs.getInt("id_curso"));
                    matriculas.add(matricula);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();    
        }
        return matriculas;
    }
}
