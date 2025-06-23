import java.sql.*;

import database.ConnectionFactory;

public class RelatorioConsultas {

    public static void main(String[] args) {
        try (Connection conn = ConnectionFactory.getConnection("localhost", "5432", "postgres", "postgres", "123")) {
            relatorioProfessores(conn);
            relatorioCursos(conn);
            relatorioAlunosAtivos(conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void relatorioProfessores(Connection conn) throws SQLException {
        System.out.println("\n==== RELATÓRIO DE PROFESSORES E TURMAS ====");
        String sql = """
            SELECT
                P.nome AS NomeProfessor,
                P.titulacao AS TitulacaoProfessor,
                D.nome AS NomeDepartamento,
                COUNT(T.id_professor) AS TotalTurmasLecionadas
            FROM Professor AS P
            JOIN Departamento AS D ON P.id_departamento = D.id_departamento
            LEFT JOIN Turma AS T ON P.id_professor = T.id_professor
            GROUP BY P.id_professor, P.nome, P.titulacao, D.nome
            ORDER BY D.nome, P.nome
        """;
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            System.out.printf("%-30s %-20s %-30s %-10s%n", "NomeProfessor", "Titulacao", "Departamento", "Turmas");
            while (rs.next()) {
                System.out.printf(
                    "%-30s %-20s %-30s %-10d%n",
                    rs.getString("NomeProfessor"),
                    rs.getString("TitulacaoProfessor"),
                    rs.getString("NomeDepartamento"),
                    rs.getInt("TotalTurmasLecionadas")
                );
            }
        }
    }

    private static void relatorioCursos(Connection conn) throws SQLException {
        System.out.println("\n==== RELATÓRIO DE CURSOS E GRADE ====");
        String sql = """
            SELECT
                C.nome AS NomeCurso,
                C.duracao AS DuracaoCurso,
                COUNT(DISTINCT GC.id_disciplina) AS NumeroDisciplinasNoCurso,
                AVG(C.duracao) OVER (PARTITION BY D.nome) AS MediaDuracaoCursosNoDepartamento
            FROM Curso AS C
            JOIN GradeCurricular AS GC ON C.id_curso = GC.id_curso
            JOIN Departamento AS D ON C.id_departamento = D.id_departamento
            GROUP BY C.id_curso, C.nome, C.duracao, D.nome
            ORDER BY D.nome, C.nome
        """;
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            System.out.printf("%-30s %-10s %-25s %-25s%n", "Curso", "Duração", "Disciplinas", "Média por Dep.");
            while (rs.next()) {
                System.out.printf(
                    "%-30s %-10d %-25d %-25.2f%n",
                    rs.getString("NomeCurso"),
                    rs.getInt("DuracaoCurso"),
                    rs.getInt("NumeroDisciplinasNoCurso"),
                    rs.getDouble("MediaDuracaoCursosNoDepartamento")
                );
            }
        }
    }

    private static void relatorioAlunosAtivos(Connection conn) throws SQLException {
        System.out.println("\n==== RELATÓRIO DE ALUNOS ATIVOS E DISCIPLINAS ====");
        String sql = """
            SELECT
                M.nome AS NomeAluno,
                M.cpf AS CPFAluno,
                C.nome AS NomeCurso,
                STRING_AGG(D.nome, ', ' ORDER BY D.nome) AS DisciplinasMatriculadas
            FROM Matricula AS M
            JOIN Curso AS C ON M.id_curso = C.id_curso
            JOIN MatriculaTurma AS MT ON M.id_matricula = MT.id_matricula
            JOIN Turma AS T ON MT.id_turma = T.id_turma
            JOIN Disciplina AS D ON T.id_disciplina = D.id_disciplina
            WHERE M.status = 'Ativa'
            GROUP BY M.id_matricula, M.nome, M.cpf, C.nome
            ORDER BY C.nome, M.nome
        """;
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            System.out.printf("%-25s %-15s %-30s %-50s%n", "Aluno", "CPF", "Curso", "Disciplinas");
            while (rs.next()) {
                System.out.printf(
                    "%-25s %-15s %-30s %-50s%n",
                    rs.getString("NomeAluno"),
                    rs.getString("CPFAluno"),
                    rs.getString("NomeCurso"),
                    rs.getString("DisciplinasMatriculadas")
                );
            }
        }
    }
}
