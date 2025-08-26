import database.dao.*;
import database.model.*;

public class InsertTest {
    public static void main(String[] args) {
        DepartamentoDAO departamentoDAO = new DepartamentoDAO();
        CursoDAO cursoDAO = new CursoDAO();
        DisciplinaDAO disciplinaDAO = new DisciplinaDAO();
        ProfessorDAO professorDAO = new ProfessorDAO();
        TurmaDAO turmaDAO = new TurmaDAO();
        GradeCurricularDAO gradeDAO = new GradeCurricularDAO();
        MatriculaDAO matriculaDAO = new MatriculaDAO();
        MatriculaTurmaDAO matriculaTurmaDAO = new MatriculaTurmaDAO();

        Departamento[] departamentos = new Departamento[3];
        for (int i = 0; i < 3; i++) {
            departamentos[i] = new Departamento();
            departamentos[i].setNome("Departamento " + (i + 1));
            departamentoDAO.insert(departamentos[i]);
        }

        Curso[] cursos = new Curso[3];
        for (int i = 0; i < 3; i++) {
            cursos[i] = new Curso();
            cursos[i].setNome("Curso " + (i + 1));
            cursos[i].setDuracao(8 + i);
            cursos[i].setDepartamentoId(departamentos[i % 3].getId());
            cursoDAO.insert(cursos[i]);
        }

        Disciplina[] disciplinas = new Disciplina[3];
        for (int i = 0; i < 3; i++) {
            disciplinas[i] = new Disciplina();
            disciplinas[i].setNome("Disciplina " + (i + 1));
            disciplinaDAO.insert(disciplinas[i]);
        }

        Professor[] professores = new Professor[3];
        for (int i = 0; i < 3; i++) {
            professores[i] = new Professor();
            professores[i].setNome("Professor " + (i + 1));
            professores[i].setTitulacao("Mestre");
            professores[i].setDepartamentoId(departamentos[i % 3].getId());
            professorDAO.insert(professores[i]);
        }

        Turma[] turmas = new Turma[3];
        for (int i = 0; i < 3; i++) {
            turmas[i] = new Turma();
            turmas[i].setDisciplinaId(disciplinas[i].getId());
            turmas[i].setProfessorId(professores[i].getId());
            turmaDAO.insert(turmas[i]);
        }

        for (int i = 0; i < 3; i++) {
            GradeCurricular grade = new GradeCurricular();
            grade.setCursoId(cursos[i].getId());
            grade.setDisciplinaId(disciplinas[i].getId());
            grade.setSemestre(i + 1);
            gradeDAO.insert(grade);
        }

        for (int i = 0; i < 3; i++) {
            Matricula matricula = new Matricula();
            matricula.setNome("Aluno " + (i + 1));
            matricula.setCpf("0000000000" + i);
            matricula.setStatus("Ativo");
            matricula.setCursoId(cursos[i].getId());
            matriculaDAO.insert(matricula);

            MatriculaTurma matriculaTurma = new MatriculaTurma();
            matriculaTurma.setMatriculaId(matricula.getId());
            matriculaTurma.setTurmaId(turmas[i].getId());
            matriculaTurmaDAO.insert(matriculaTurma);
        }

        System.out.println("Inserção de 3 registros por DAO concluída.");
    }
}
