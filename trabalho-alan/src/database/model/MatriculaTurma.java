package database.model;

public class MatriculaTurma {
    private int id;
    private int matriculaId;
    private int turmaId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMatriculaId() {
        return matriculaId;
    }

    public void setMatriculaId(int matriculaId) {
        this.matriculaId = matriculaId;
    }

    public int getTurmaId() {
        return turmaId;
    }

    public void setTurmaId(int turmaId) {
        this.turmaId = turmaId;
    }
}
