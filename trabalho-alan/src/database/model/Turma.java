package database.model;

public class Turma {
    private int id;
    private int disciplinaId;
    private int professorId;

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public int getDisciplinaId(){
        return disciplinaId;
    }

    public void setDisciplinaId(int disciplinaId){
        this.disciplinaId = disciplinaId;
    }

    public int getProfessorId(){
        return professorId;
    }

    public void setProfessorId(int professorId){
        this.professorId = professorId;
    }
}
