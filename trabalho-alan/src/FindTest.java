import database.dao.*;
import database.model.*;
import java.util.List;

public class FindTest {
    public static void main(String[] args) {
        DepartamentoDAO departamentoDAO = new DepartamentoDAO();
        CursoDAO cursoDAO = new CursoDAO();
        DisciplinaDAO disciplinaDAO = new DisciplinaDAO();
        ProfessorDAO professorDAO = new ProfessorDAO();
        TurmaDAO turmaDAO = new TurmaDAO();
        GradeCurricularDAO gradeCurricularDAO = new GradeCurricularDAO();
        MatriculaDAO matriculaDAO = new MatriculaDAO();

        Departamento departamento = departamentoDAO.findById(2);
        System.out.println("Departamento: " + departamento.getNome());

        Curso curso = cursoDAO.findById(2);
        System.out.println("Curso: " + curso.getNome());

        Disciplina disciplina = disciplinaDAO.findById(2);
        System.out.println("Disciplina: " + disciplina.getNome());

        Professor professor = professorDAO.findById(2);
        System.out.println("Professor: " + professor.getNome());

        Turma turma = turmaDAO.findById(2);
        System.out.println("Turma: ID " + turma.getId());

        GradeCurricular gradeCurricular = gradeCurricularDAO.findById(2);
        System.out.println("Grade Curricular ID: " + gradeCurricular.getId());

        Matricula matricula = matriculaDAO.findById(2);
        System.out.println("Matrícula: " + matricula.getNome());

        System.out.println("\nTodos Departamentos:");
        List<Departamento> departamentos = departamentoDAO.findAll();
        for (Departamento d : departamentos) System.out.println("- " + d.getNome());

        System.out.println("\nTodos Cursos:");
        List<Curso> cursos = cursoDAO.findAll();
        for (Curso c : cursos) System.out.println("- " + c.getNome());

        System.out.println("\nTodas Disciplinas:");
        List<Disciplina> disciplinas = disciplinaDAO.findAll();
        for (Disciplina d : disciplinas) System.out.println("- " + d.getNome());

        System.out.println("\nTodos Professores:");
        List<Professor> professores = professorDAO.findAll();
        for (Professor p : professores) System.out.println("- " + p.getNome());

        System.out.println("\nTodas Turmas:");
        List<Turma> turmas = turmaDAO.findAll();
        for (Turma t : turmas) System.out.println("- Turma ID: " + t.getId());

        System.out.println("\nTodas Grades Curriculares:");
        List<GradeCurricular> grades = gradeCurricularDAO.findAll();
        for (GradeCurricular g : grades) System.out.println("- Grade ID: " + g.getId());

        System.out.println("\nTodas Matrículas:");
        List<Matricula> matriculas = matriculaDAO.findAll();
        for (Matricula m : matriculas) System.out.println("- " + m.getNome());
    }
}
