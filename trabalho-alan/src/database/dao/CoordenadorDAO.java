package database.dao;

import database.ConnectionFactory;
import database.model.Coordenador;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CoordenadorDAO {

    public void insert(Coordenador coordenador) {
        String sql = "INSERT INTO tb_coordenadores (departamento_id, nome) VALUES (?, ?)";
        try (Connection conn = ConnectionFactory.getConnection("localhost", "5432", "postgres", "postgres", "123");
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(2, coordenador.getNome());
            
            int affectedRows = stmt.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        coordenador.setId(rs.getInt(1));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(Coordenador coordenador) {
        String sql = "UPDATE tb_coordenadores SET departamento_id = ?, nome = ? WHERE id = ?";
        try (Connection conn = ConnectionFactory.getConnection("localhost", "5432", "postgres", "postgres", "123");
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(2, coordenador.getNome());
            stmt.setInt(3, coordenador.getId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        String sql = "DELETE FROM tb_coordenadores WHERE id = ?";
        try (Connection conn = ConnectionFactory.getConnection("localhost", "5432", "postgres", "postgres", "123");
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Coordenador findById(int id) {
        Coordenador coordenador = null;
        String sql = "SELECT * FROM tb_coordenadores WHERE id = ?";
        try (Connection conn = ConnectionFactory.getConnection("localhost", "5432", "postgres", "postgres", "123");
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    coordenador = new Coordenador();
                    coordenador.setId(rs.getInt("id"));
                    coordenador.setNome(rs.getString("nome"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return coordenador;
    }

    public List<Coordenador> findAll() {
        List<Coordenador> coordenadores = new ArrayList<>();
        String sql = "SELECT * FROM tb_coordenadores";
        try (Connection conn = ConnectionFactory.getConnection("localhost", "5432", "postgres", "postgres", "123");
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Coordenador coordenador = new Coordenador();
                coordenador.setId(rs.getInt("id"));
                coordenador.setNome(rs.getString("nome"));
                coordenadores.add(coordenador);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return coordenadores;
    }
}
