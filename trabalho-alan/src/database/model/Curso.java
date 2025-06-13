package database.model;

import java.time.LocalDate;

public class Curso {
    private int id;
    private String nome;
    private LocalDate dataProcessamento;
    private String periodoInicial;
    private String periodoFinal;
    private int sequencia;
    private String versaoLayout;
    private String arquivoHash;

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public LocalDate getDataProcessamento() { return dataProcessamento; }
    public void setDataProcessamento(LocalDate dataProcessamento) { this.dataProcessamento = dataProcessamento; }

    public String getPeriodoInicial() { return periodoInicial; }
    public void setPeriodoInicial(String periodoInicial) { this.periodoInicial = periodoInicial; }

    public String getPeriodoFinal() { return periodoFinal; }
    public void setPeriodoFinal(String periodoFinal) { this.periodoFinal = periodoFinal; }

    public int getSequencia() { return sequencia; }
    public void setSequencia(int sequencia) { this.sequencia = sequencia; }

    public String getVersaoLayout() { return versaoLayout; }
    public void setVersaoLayout(String versaoLayout) { this.versaoLayout = versaoLayout; }

    public String getArquivoHash() { return arquivoHash; }
    public void setArquivoHash(String arquivoHash) { this.arquivoHash = arquivoHash; }
}
