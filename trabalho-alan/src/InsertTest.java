import database.dao.*;
import database.model.*;

import java.util.List;

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

        Departamento dep = new Departamento();
        dep.setNome("Engenharia");
        departamentoDAO.insert(dep);
        System.out.println("Departamento inserido: " + dep.getId());

        Curso curso = new Curso();
        curso.setNome("Engenharia Civil");
        curso.setDuracao(10);
        curso.setDepartamentoId(dep.getId());
        cursoDAO.insert(curso);
        System.out.println("Curso inserido: " + curso.getId());

        Disciplina disciplina = new Disciplina();
        disciplina.setNome("Mecânica dos Solos");
        disciplinaDAO.insert(disciplina);
        System.out.println("Disciplina inserida: " + disciplina.getId());

        Professor prof = new Professor();
        prof.setNome("Carlos Silva");
        prof.setTitulacao("Doutor");
        prof.setDepartamentoId(dep.getId());
        professorDAO.insert(prof);
        System.out.println("Professor inserido: " + prof.getId());

        Turma turma = new Turma();
        turma.setDisciplinaId(disciplina.getId());
        turma.setProfessorId(prof.getId());
        turmaDAO.insert(turma);
        System.out.println("Turma inserida: " + turma.getId());

        GradeCurricular grade = new GradeCurricular();
        grade.setCursoId(curso.getId());
        grade.setDisciplinaId(disciplina.getId());
        grade.setSemestre(1);
        gradeDAO.insert(grade);
        System.out.println("Grade curricular inserida: " + grade.getId());

        Matricula matricula = new Matricula();
        matricula.setNome("Ana Maria");
        matricula.setCpf("12345678900");
        matricula.setStatus("Ativa");
        matricula.setCursoId(curso.getId());
        matriculaDAO.insert(matricula);
        System.out.println("Matrícula inserida: " + matricula.getId());

        MatriculaTurma matriculaTurma = new MatriculaTurma();
        matriculaTurma.setMatriculaId(matricula.getId());
        matriculaTurma.setTurmaId(turma.getId());
        matriculaTurmaDAO.insert(matriculaTurma);
        System.out.println("Matrícula na turma inserida.");

        List<Curso> cursos = cursoDAO.findAll();
        System.out.println("Cursos cadastrados:");
        for (Curso c : cursos) {
            System.out.println(" - " + c.getNome());
        }

        List<Professor> professores = professorDAO.findAll();
        System.out.println("Professores cadastrados:");
        for (Professor p : professores) {
            System.out.println(" - " + p.getNome() + ", " + p.getTitulacao());
        }
    }
}
