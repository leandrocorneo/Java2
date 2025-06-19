package database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import database.ConnectionFactory;
import database.model.GradeCurricular;

public class GradeCurricularDAO {

    public void insert(GradeCurricular gradeCurricular) {
        try(Connection conn = ConnectionFactory.getConnection("localhost", "5432", "postgres", "postgres", "123")) {
            String sql = "INSERT INTO gradecurricular (id_curso, id_disciplina, semestre_ideal) VALUES (?, ?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                stmt.setInt(1, gradeCurricular.getCursoId());
                stmt.setInt(2, gradeCurricular.getDisciplinaId());
                stmt.setInt(3, gradeCurricular.getSemestre());

                int affectedRows = stmt.executeUpdate();
                if (affectedRows > 0) {
                    try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                        if (generatedKeys.next()) {
                            gradeCurricular.setId(generatedKeys.getInt(1));
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update(GradeCurricular gradeCurricular) {
        try (Connection conn = ConnectionFactory.getConnection("localhost", "5432", "postgres", "postgres", "123")) {
            String sql = "UPDATE gradecurricular SET id_curso = ?, id_disciplina = ?, semestre_ideal = ? WHERE id_grade = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, gradeCurricular.getCursoId());
                stmt.setInt(2, gradeCurricular.getDisciplinaId());
                stmt.setInt(3, gradeCurricular.getSemestre());
                stmt.setInt(4, gradeCurricular.getId());
                stmt.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        try (Connection conn = ConnectionFactory.getConnection("localhost", "5432", "postgres", "postgres", "123")) {
            String sql = "DELETE FROM gradecurricular WHERE id_grade = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, id);
                stmt.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public GradeCurricular findById(int id) {
        GradeCurricular gradeCurricular = null;
        try (Connection conn = ConnectionFactory.getConnection("localhost", "5432", "postgres", "postgres", "123")) {
            String sql = "SELECT * FROM gradecurricular WHERE id_grade = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, id);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        gradeCurricular = new GradeCurricular();
                        gradeCurricular.setId(rs.getInt("id_grade"));
                        gradeCurricular.setCursoId(rs.getInt("id_curso"));
                        gradeCurricular.setDisciplinaId(rs.getInt("id_disciplina"));
                        gradeCurricular.setSemestre(rs.getInt("semestre_ideal"));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return gradeCurricular;
    }

    public List<GradeCurricular> findAll() {
        List<GradeCurricular> gradeCurriculares = new ArrayList<>();
        try (Connection conn = ConnectionFactory.getConnection("localhost", "5432", "postgres", "postgres", "123")) {
            String sql = "SELECT * FROM gradecurricular";
            try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
                while (rs.next()) {
                    GradeCurricular gradeCurricular = new GradeCurricular();
                    gradeCurricular.setId(rs.getInt("id_grade"));
                    gradeCurricular.setCursoId(rs.getInt("id_curso"));
                    gradeCurricular.setDisciplinaId(rs.getInt("id_disciplina"));
                    gradeCurricular.setSemestre(rs.getInt("semestre_ideal"));
                    gradeCurriculares.add(gradeCurricular);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return gradeCurriculares;
    }
}
