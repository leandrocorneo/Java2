import javax.swing.*;
import java.awt.*;
import java.util.List;

public class CrudApp extends JFrame {

    private JComboBox<String> tabelaCombo;
    private JComboBox<String> operacaoCombo;
    private JButton executarBtn;
    private JTextArea resultadoArea;

    public CrudApp() {
        setTitle("CRUD App");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        tabelaCombo = new JComboBox<>(new String[] {
            "Departamento", "Curso", "Disciplina", "Professor", "Turma", "GradeCurricular", "Matricula"
        });

        operacaoCombo = new JComboBox<>(new String[] {
            "Inserir", "Atualizar", "Deletar", "Buscar"
        });

        executarBtn = new JButton("Executar");
        executarBtn.addActionListener(e -> executarAcao());

        JPanel topo = new JPanel();
        topo.add(new JLabel("Tabela:"));
        topo.add(tabelaCombo);
        topo.add(new JLabel("Operação:"));
        topo.add(operacaoCombo);
        topo.add(executarBtn);

        resultadoArea = new JTextArea();
        resultadoArea.setEditable(false);

        add(topo, BorderLayout.NORTH);
        add(new JScrollPane(resultadoArea), BorderLayout.CENTER);
    }

    private void executarAcao() {
        String tabela = (String) tabelaCombo.getSelectedItem();
        String operacao = (String) operacaoCombo.getSelectedItem();

        try {
            switch (tabela) {
                case "Departamento": executarDepartamento(operacao); break;
                case "Curso": executarCurso(operacao); break;
                case "Disciplina": executarDisciplina(operacao); break;
                case "Professor": executarProfessor(operacao); break;
                case "Turma": executarTurma(operacao); break;
                case "GradeCurricular": executarGradeCurricular(operacao); break;
                case "Matricula": executarMatricula(operacao); break;
                default: resultadoArea.setText("Tabela não implementada."); break;
            }
        } catch (Exception e) {
            resultadoArea.setText("Erro: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void executarDepartamento(String operacao) throws Exception {
        database.dao.DepartamentoDAO dao = new database.dao.DepartamentoDAO();
        switch (operacao) {
            case "Inserir":
                var d = new database.model.Departamento();
                d.setNome(JOptionPane.showInputDialog("Nome do Departamento:"));
                dao.insert(d);
                resultadoArea.setText("Departamento inserido ID: " + d.getId());
                break;
            case "Atualizar":
                int idUpdDep = Integer.parseInt(JOptionPane.showInputDialog("ID do Departamento para atualizar:"));
                var dUpd = dao.findById(idUpdDep);
                if (dUpd != null) {
                    dUpd.setNome(JOptionPane.showInputDialog("Novo nome:", dUpd.getNome()));
                    dao.update(dUpd);
                    resultadoArea.setText("Departamento atualizado.");
                } else resultadoArea.setText("Departamento não encontrado.");
                break;
            case "Deletar":
                int idDelDep = Integer.parseInt(JOptionPane.showInputDialog("ID do Departamento para deletar:"));
                dao.delete(idDelDep);
                resultadoArea.setText("Departamento deletado.");
                break;
            case "Buscar":
                List<database.model.Departamento> deps = dao.findAll();
                StringBuilder sbDep = new StringBuilder("Departamentos:\n");
                for (var dep : deps) sbDep.append(dep.getId()).append(" - ").append(dep.getNome()).append("\n");
                resultadoArea.setText(sbDep.toString());
                break;
        }
    }

    private void executarCurso(String operacao) throws Exception {
        database.dao.CursoDAO dao = new database.dao.CursoDAO();
        switch (operacao) {
            case "Inserir":
                var c = new database.model.Curso();
                c.setNome(JOptionPane.showInputDialog("Nome do Curso:"));
                c.setDuracao(Integer.parseInt(JOptionPane.showInputDialog("Duração do Curso (semestres):")));
                c.setDepartamentoId(Integer.parseInt(JOptionPane.showInputDialog("ID do Departamento:")));
                dao.insert(c);
                resultadoArea.setText("Curso inserido ID: " + c.getId());
                break;
            case "Atualizar":
                int idUpdC = Integer.parseInt(JOptionPane.showInputDialog("ID do Curso para atualizar:"));
                var cUpd = dao.findById(idUpdC);
                if (cUpd != null) {
                    cUpd.setNome(JOptionPane.showInputDialog("Novo nome:", cUpd.getNome()));
                    cUpd.setDuracao(Integer.parseInt(JOptionPane.showInputDialog("Nova duração:", cUpd.getDuracao())));
                    cUpd.setDepartamentoId(Integer.parseInt(JOptionPane.showInputDialog("Novo ID Departamento:", cUpd.getDepartamentoId())));
                    dao.update(cUpd);
                    resultadoArea.setText("Curso atualizado.");
                } else resultadoArea.setText("Curso não encontrado.");
                break;
            case "Deletar":
                int idDelC = Integer.parseInt(JOptionPane.showInputDialog("ID do Curso para deletar:"));
                dao.delete(idDelC);
                resultadoArea.setText("Curso deletado.");
                break;
            case "Buscar":
                List<database.model.Curso> cursos = dao.findAll();
                StringBuilder sbC = new StringBuilder("Cursos:\n");
                for (var curso : cursos) sbC.append(curso.getId()).append(" - ").append(curso.getNome()).append(" - Duração: ").append(curso.getDuracao()).append("\n");
                resultadoArea.setText(sbC.toString());
                break;
        }
    }

    private void executarDisciplina(String operacao) throws Exception {
        database.dao.DisciplinaDAO dao = new database.dao.DisciplinaDAO();
        switch (operacao) {
            case "Inserir":
                var d = new database.model.Disciplina();
                d.setNome(JOptionPane.showInputDialog("Nome da Disciplina:"));
                dao.insert(d);
                resultadoArea.setText("Disciplina inserida ID: " + d.getId());
                break;
            case "Atualizar":
                int idUpdD = Integer.parseInt(JOptionPane.showInputDialog("ID da Disciplina para atualizar:"));
                var dUpd = dao.findById(idUpdD);
                if (dUpd != null) {
                    dUpd.setNome(JOptionPane.showInputDialog("Novo nome:", dUpd.getNome()));
                    dao.update(dUpd);
                    resultadoArea.setText("Disciplina atualizada.");
                } else resultadoArea.setText("Disciplina não encontrada.");
                break;
            case "Deletar":
                int idDelD = Integer.parseInt(JOptionPane.showInputDialog("ID da Disciplina para deletar:"));
                dao.delete(idDelD);
                resultadoArea.setText("Disciplina deletada.");
                break;
            case "Buscar":
                List<database.model.Disciplina> disciplinas = dao.findAll();
                StringBuilder sbD = new StringBuilder("Disciplinas:\n");
                for (var disc : disciplinas) sbD.append(disc.getId()).append(" - ").append(disc.getNome()).append("\n");
                resultadoArea.setText(sbD.toString());
                break;
        }
    }

    private void executarProfessor(String operacao) throws Exception {
        database.dao.ProfessorDAO dao = new database.dao.ProfessorDAO();
        switch (operacao) {
            case "Inserir":
                var p = new database.model.Professor();
                p.setNome(JOptionPane.showInputDialog("Nome do Professor:"));
                p.setTitulacao(JOptionPane.showInputDialog("Titulação do Professor:"));
                p.setDepartamentoId(Integer.parseInt(JOptionPane.showInputDialog("ID do Departamento:")));
                dao.insert(p);
                resultadoArea.setText("Professor inserido ID: " + p.getId());
                break;
            case "Atualizar":
                int idUpdP = Integer.parseInt(JOptionPane.showInputDialog("ID do Professor para atualizar:"));
                var pUpd = dao.findById(idUpdP);
                if (pUpd != null) {
                    pUpd.setNome(JOptionPane.showInputDialog("Novo nome:", pUpd.getNome()));
                    pUpd.setTitulacao(JOptionPane.showInputDialog("Nova titulação:", pUpd.getTitulacao()));
                    pUpd.setDepartamentoId(Integer.parseInt(JOptionPane.showInputDialog("Novo ID Departamento:", pUpd.getDepartamentoId())));
                    dao.update(pUpd);
                    resultadoArea.setText("Professor atualizado.");
                } else resultadoArea.setText("Professor não encontrado.");
                break;
            case "Deletar":
                int idDelP = Integer.parseInt(JOptionPane.showInputDialog("ID do Professor para deletar:"));
                dao.delete(idDelP);
                resultadoArea.setText("Professor deletado.");
                break;
            case "Buscar":
                List<database.model.Professor> profs = dao.findAll();
                StringBuilder sbP = new StringBuilder("Professores:\n");
                for (var prof : profs) sbP.append(prof.getId()).append(" - ").append(prof.getNome()).append(" - ").append(prof.getTitulacao()).append("\n");
                resultadoArea.setText(sbP.toString());
                break;
        }
    }

    private void executarTurma(String operacao) throws Exception {
        database.dao.TurmaDAO dao = new database.dao.TurmaDAO();
        switch (operacao) {
            case "Inserir":
                var t = new database.model.Turma();
                t.setDisciplinaId(Integer.parseInt(JOptionPane.showInputDialog("ID da Disciplina:")));
                t.setProfessorId(Integer.parseInt(JOptionPane.showInputDialog("ID do Professor:")));
                dao.insert(t);
                resultadoArea.setText("Turma inserida.");
                break;
            case "Atualizar":
                int idUpdT = Integer.parseInt(JOptionPane.showInputDialog("ID da Turma para atualizar:"));
                var tUpd = dao.findById(idUpdT);
                if (tUpd != null) {
                    tUpd.setDisciplinaId(Integer.parseInt(JOptionPane.showInputDialog("Novo ID Disciplina:", tUpd.getDisciplinaId())));
                    tUpd.setProfessorId(Integer.parseInt(JOptionPane.showInputDialog("Novo ID Professor:", tUpd.getProfessorId())));
                    dao.update(tUpd);
                    resultadoArea.setText("Turma atualizada.");
                } else resultadoArea.setText("Turma não encontrada.");
                break;
            case "Deletar":
                int idDelT = Integer.parseInt(JOptionPane.showInputDialog("ID da Turma para deletar:"));
                dao.delete(idDelT);
                resultadoArea.setText("Turma deletada.");
                break;
            case "Buscar":
                List<database.model.Turma> turmas = dao.findAll();
                StringBuilder sbT = new StringBuilder("Turmas:\n");
                for (var turma : turmas) sbT.append(turma.getId()).append(" - Disciplina ID: ").append(turma.getDisciplinaId()).append(" - Professor ID: ").append(turma.getProfessorId()).append("\n");
                resultadoArea.setText(sbT.toString());
                break;
        }
    }

    private void executarGradeCurricular(String operacao) throws Exception {
        database.dao.GradeCurricularDAO dao = new database.dao.GradeCurricularDAO();
        switch (operacao) {
            case "Inserir":
                var g = new database.model.GradeCurricular();
                g.setCursoId(Integer.parseInt(JOptionPane.showInputDialog("ID do Curso:")));
                g.setDisciplinaId(Integer.parseInt(JOptionPane.showInputDialog("ID da Disciplina:")));
                g.setSemestre(Integer.parseInt(JOptionPane.showInputDialog("Semestre ideal:")));
                dao.insert(g);
                resultadoArea.setText("Grade Curricular inserida ID: " + g.getId());
                break;
            case "Atualizar":
                int idUpdG = Integer.parseInt(JOptionPane.showInputDialog("ID da Grade Curricular para atualizar:"));
                var gUpd = dao.findById(idUpdG);
                if (gUpd != null) {
                    gUpd.setCursoId(Integer.parseInt(JOptionPane.showInputDialog("Novo ID Curso:", gUpd.getCursoId())));
                    gUpd.setDisciplinaId(Integer.parseInt(JOptionPane.showInputDialog("Novo ID Disciplina:", gUpd.getDisciplinaId())));
                    gUpd.setSemestre(Integer.parseInt(JOptionPane.showInputDialog("Novo semestre ideal:", gUpd.getSemestre())));
                    dao.update(gUpd);
                    resultadoArea.setText("Grade Curricular atualizada.");
                } else resultadoArea.setText("Grade Curricular não encontrada.");
                break;
            case "Deletar":
                int idDelG = Integer.parseInt(JOptionPane.showInputDialog("ID da Grade Curricular para deletar:"));
                dao.delete(idDelG);
                resultadoArea.setText("Grade Curricular deletada.");
                break;
            case "Buscar":
                List<database.model.GradeCurricular> grades = dao.findAll();
                StringBuilder sbG = new StringBuilder("Grades Curriculares:\n");
                for (var grade : grades) sbG.append(grade.getId()).append(" - Curso ID: ").append(grade.getCursoId()).append(" - Disciplina ID: ").append(grade.getDisciplinaId()).append(" - Semestre: ").append(grade.getSemestre()).append("\n");
                resultadoArea.setText(sbG.toString());
                break;
        }
    }

    private void executarMatricula(String operacao) throws Exception {
        database.dao.MatriculaDAO dao = new database.dao.MatriculaDAO();
        switch (operacao) {
            case "Inserir":
                var m = new database.model.Matricula();
                m.setNome(JOptionPane.showInputDialog("Nome do aluno:"));
                m.setCpf(JOptionPane.showInputDialog("CPF do aluno:"));
                m.setStatus(JOptionPane.showInputDialog("Status do aluno (ex: Ativo):"));
                m.setCursoId(Integer.parseInt(JOptionPane.showInputDialog("ID do Curso:")));
                dao.insert(m);
                resultadoArea.setText("Matrícula inserida ID: " + m.getId());
                break;
            case "Atualizar":
                int idUpdM = Integer.parseInt(JOptionPane.showInputDialog("ID da Matrícula para atualizar:"));
                var mUpd = dao.findById(idUpdM);
                if (mUpd != null) {
                    mUpd.setNome(JOptionPane.showInputDialog("Novo nome:", mUpd.getNome()));
                    mUpd.setCpf(JOptionPane.showInputDialog("Novo CPF:", mUpd.getCpf()));
                    mUpd.setStatus(JOptionPane.showInputDialog("Novo Status:", mUpd.getStatus()));
                    mUpd.setCursoId(Integer.parseInt(JOptionPane.showInputDialog("Novo ID Curso:", mUpd.getCursoId())));
                    dao.update(mUpd);
                    resultadoArea.setText("Matrícula atualizada.");
                } else resultadoArea.setText("Matrícula não encontrada.");
                break;
            case "Deletar":
                int idDelM = Integer.parseInt(JOptionPane.showInputDialog("ID da Matrícula para deletar:"));
                dao.delete(idDelM);
                resultadoArea.setText("Matrícula deletada.");
                break;
            case "Buscar":
                List<database.model.Matricula> matriculas = dao.findAll();
                StringBuilder sbM = new StringBuilder("Matrículas:\n");
                for (var mat : matriculas) sbM.append(mat.getId()).append(" - ").append(mat.getNome()).append(" - CPF: ").append(mat.getCpf()).append(" - Status: ").append(mat.getStatus()).append("\n");
                resultadoArea.setText(sbM.toString());
                break;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CrudApp().setVisible(true));
    }
}
