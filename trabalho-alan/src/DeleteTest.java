import database.dao.*;

public class DeleteTest {
    public static void main(String[] args) {
        DepartamentoDAO departamentoDAO = new DepartamentoDAO();
        CursoDAO cursoDAO = new CursoDAO();
        DisciplinaDAO disciplinaDAO = new DisciplinaDAO();
        ProfessorDAO professorDAO = new ProfessorDAO();
        TurmaDAO turmaDAO = new TurmaDAO();
        GradeCurricularDAO gradeCurricularDAO = new GradeCurricularDAO();
        MatriculaDAO matriculaDAO = new MatriculaDAO();
        MatriculaTurmaDAO matriculaTurmaDAO = new MatriculaTurmaDAO();

        matriculaTurmaDAO.deleteAll();

        int matriculaId = 1;
        int turmaId = 1;
        int gradeId = 1;
        int professorId = 1;
        int disciplinaId = 1;
        int cursoId = 1;
        int departamentoId = 1;

        matriculaDAO.delete(matriculaId);
        turmaDAO.delete(turmaId);
        gradeCurricularDAO.delete(gradeId);
        professorDAO.delete(professorId);
        disciplinaDAO.delete(disciplinaId);
        cursoDAO.delete(cursoId);
        departamentoDAO.delete(departamentoId);

        System.out.println("Deletes realizados.");
    }
}
