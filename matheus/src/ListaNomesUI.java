import javax.swing.*;
import java.awt.*;
import java.util.*;

public class ListaNomesUI extends JFrame {
    private JTextArea areaLista;
    private java.util.List<String> nomes;

    public ListaNomesUI() {
        setTitle("Manipulação de Lista de Nomes");
        setSize(700, 450);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        nomes = new ArrayList<>();

        JPanel painel = new JPanel(new BorderLayout(10, 10));
        painel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        areaLista = new JTextArea();
        JScrollPane scroll = new JScrollPane(areaLista);
        painel.add(scroll, BorderLayout.CENTER);

        JPanel painelBotoes = new JPanel(new GridLayout(3, 3, 5, 5));

        JButton btnAZ = new JButton("Ordenar A-Z");
        JButton btnZA = new JButton("Ordenar Z-A");
        JButton btnInverter = new JButton("Inverter");
        JButton btnDuplicados = new JButton("Remover Duplicados");
        JButton btnRemoverVazios = new JButton("Remover Vazios");
        JButton btnTrim = new JButton("Aplicar Trim");
        JButton btnMaiusculo = new JButton("MAIÚSCULO");
        JButton btnMinusculo = new JButton("minúsculo");
        JButton btnCapitalizar = new JButton("Capitalizar");

        painelBotoes.add(btnAZ);
        painelBotoes.add(btnZA);
        painelBotoes.add(btnInverter);
        painelBotoes.add(btnDuplicados);
        painelBotoes.add(btnRemoverVazios);
        painelBotoes.add(btnTrim);
        painelBotoes.add(btnMaiusculo);
        painelBotoes.add(btnMinusculo);
        painelBotoes.add(btnCapitalizar);

        painel.add(painelBotoes, BorderLayout.SOUTH);

        btnAZ.addActionListener(e -> {
            carregarNomes();
            Collections.sort(nomes, String.CASE_INSENSITIVE_ORDER);
            atualizarLista();
        });

        btnZA.addActionListener(e -> {
            carregarNomes();
            Collections.sort(nomes, String.CASE_INSENSITIVE_ORDER);
            Collections.reverse(nomes);
            atualizarLista();
        });

        btnInverter.addActionListener(e -> {
            carregarNomes();
            Collections.reverse(nomes);
            atualizarLista();
        });

        btnDuplicados.addActionListener(e -> {
            carregarNomes();
            Set<String> set = new LinkedHashSet<>(nomes);
            nomes = new ArrayList<>(set);
            atualizarLista();
        });

        btnRemoverVazios.addActionListener(e -> {
            carregarNomes();
            nomes.removeIf(n -> n.trim().isEmpty());
            atualizarLista();
        });

        btnTrim.addActionListener(e -> {
            carregarNomes();
            for (int i = 0; i < nomes.size(); i++) {
                nomes.set(i, nomes.get(i).trim().replaceAll("\\s+", " ")); 
            }
            atualizarLista();
        });

        btnMaiusculo.addActionListener(e -> {
            carregarNomes();
            for (int i = 0; i < nomes.size(); i++) {
                nomes.set(i, nomes.get(i).toUpperCase());
            }
            atualizarLista();
        });

        btnMinusculo.addActionListener(e -> {
            carregarNomes();
            for (int i = 0; i < nomes.size(); i++) {
                nomes.set(i, nomes.get(i).toLowerCase());
            }
            atualizarLista();
        });

        btnCapitalizar.addActionListener(e -> {
            carregarNomes();
            for (int i = 0; i < nomes.size(); i++) {
                String n = nomes.get(i);
                if (!n.isEmpty()) {
                    nomes.set(i, n.substring(0, 1).toUpperCase() + n.substring(1).toLowerCase());
                }
            }
            atualizarLista();
        });

        add(painel);
    }

    private void carregarNomes() {
        nomes.clear();
        String texto = areaLista.getText();
        String[] linhas = texto.split("\n");
        for (String linha : linhas) {
            nomes.add(linha);
        }
    }

    private void atualizarLista() {
        StringBuilder sb = new StringBuilder();
        for (String n : nomes) {
            sb.append(n).append("\n");
        }
        areaLista.setText(sb.toString());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new ListaNomesUI().setVisible(true);
        });
    }
}
