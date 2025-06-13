package database.model;

public class Fase {
    private int id;
    private int cursoId;
    private String fase;
    private int qtdDisciplinas;
    private int qtdProfessores;

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getCursoId() { return cursoId; }
    public void setCursoId(int cursoId) { this.cursoId = cursoId; }

    public String getFase() { return fase; }
    public void setFase(String fase) { this.fase = fase; }

    public int getQtdDisciplinas() { return qtdDisciplinas; }
    public void setQtdDisciplinas(int qtdDisciplinas) { this.qtdDisciplinas = qtdDisciplinas; }

    public int getQtdProfessores() { return qtdProfessores; }
    public void setQtdProfessores(int qtdProfessores) { this.qtdProfessores = qtdProfessores; }
}
