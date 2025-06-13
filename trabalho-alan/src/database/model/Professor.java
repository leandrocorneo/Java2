package database.model;

public class Professor {
    private int id;
    private int disciplinaId; 
    private String nome;
    private int tituloDocente;

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getDisciplinaId() { return disciplinaId; }
    public void setDisciplinaId(int disciplinaId) { this.disciplinaId = disciplinaId; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public int getTituloDocente() { return tituloDocente; }
    public void setTituloDocente(int tituloDocente) { this.tituloDocente = tituloDocente; }
}
