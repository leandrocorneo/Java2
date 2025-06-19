import database.dao.*;
import database.model.*;

public class UpdateTest {
    public static void main(String[] args) {
        DepartamentoDAO departamentoDAO = new DepartamentoDAO();
        CursoDAO cursoDAO = new CursoDAO();
        DisciplinaDAO disciplinaDAO = new DisciplinaDAO();
        ProfessorDAO professorDAO = new ProfessorDAO();
        TurmaDAO turmaDAO = new TurmaDAO();
        GradeCurricularDAO gradeCurricularDAO = new GradeCurricularDAO();
        MatriculaDAO matriculaDAO = new MatriculaDAO();

        Departamento departamento = departamentoDAO.findById(1);
        if (departamento != null) {
            departamento.setNome("Departamento Atualizado");
            departamentoDAO.update(departamento);
            System.out.println("Departamento atualizado: " + departamento.getNome());
        }

        Curso curso = cursoDAO.findById(1);
        if (curso != null) {
            curso.setNome("Curso Atualizado");
            cursoDAO.update(curso);
            System.out.println("Curso atualizado: " + curso.getNome());
        }

        Disciplina disciplina = disciplinaDAO.findById(1);
        if (disciplina != null) {
            disciplina.setNome("Disciplina Atualizada");
            disciplinaDAO.update(disciplina);
            System.out.println("Disciplina atualizada: " + disciplina.getNome());
        }

        Professor professor = professorDAO.findById(1);
        if (professor != null) {
            professor.setNome("Professor Atualizado");
            professor.setTitulacao("Mestre");
            professorDAO.update(professor);
            System.out.println("Professor atualizado: " + professor.getNome());
        }

        Turma turma = turmaDAO.findById(1);
        if (turma != null) {
            turma.setDisciplinaId(2);
            turma.setProfessorId(2);
            turmaDAO.update(turma);
            System.out.println("Turma atualizada: ID " + turma.getId());
        }

        GradeCurricular gradeCurricular = gradeCurricularDAO.findById(1);
        if (gradeCurricular != null) {
            gradeCurricular.setCursoId(2);
            gradeCurricular.setDisciplinaId(2);
            gradeCurricular.setSemestre(3);
            gradeCurricularDAO.update(gradeCurricular);
            System.out.println("Grade curricular atualizada: ID " + gradeCurricular.getId());
        }

        Matricula matricula = matriculaDAO.findById(1);
        if (matricula != null) {
            matricula.setNome("Aluno Atualizado");
            matricula.setCpf("12312312345");
            matricula.setStatus("Ativo");
            matriculaDAO.update(matricula);
            System.out.println("Matrícula atualizada: " + matricula.getNome());
        }
    }
}
