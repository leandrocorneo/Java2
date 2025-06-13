package database.model;

public class Disciplina {
    private int id;
    private int faseId; 
    private String codigo;
    private int diaSemana;
    private int qtdProfessores;

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getFaseId() { return faseId; }
    public void setFaseId(int faseId) { this.faseId = faseId; }

    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }

    public int getDiaSemana() { return diaSemana; }
    public void setDiaSemana(int diaSemana) { this.diaSemana = diaSemana; }

    public int getQtdProfessores() { return qtdProfessores; }
    public void setQtdProfessores(int qtdProfessores) { this.qtdProfessores = qtdProfessores; }
}
