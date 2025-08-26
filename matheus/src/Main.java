import javax.swing.*;
import java.awt.event.ActionEvent;

public class Main extends JFrame {

    private JTextArea textArea;
    private JScrollPane scrollPane;

    private JButton buttonAZ, buttonZA, buttonRemoverDuplicados, buttonRemoverVazios;
    private JButton buttonAplicarTrim, buttonConverterMaiusculo, buttonConverterMinusculo, buttonConverterCapitalizar;

    public Main() {
        setTitle("Atividade Valendo 3,00 - Listas e Ordenações");
        setSize(530, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(null);
        componentesCriar();
        setVisible(true);
    }

    private void componentesCriar() {

        textArea = new JTextArea();
        scrollPane = new JScrollPane(textArea);
        scrollPane.setBounds(10, 10, 300, 430);
        getContentPane().add(scrollPane);

        buttonAZ = new JButton(new AbstractAction("A-Z") {
            @Override
            public void actionPerformed(ActionEvent e) {
                ordenarAZ();
            }
        });
        buttonAZ.setBounds(320, 10, 170, 25);
        getContentPane().add(buttonAZ);

        buttonZA = new JButton(new AbstractAction("Z-A") {
            @Override
            public void actionPerformed(ActionEvent e) {
                ordenarZA();
            }
        });
        buttonZA.setBounds(320, 40, 170, 25);
        getContentPane().add(buttonZA);

        buttonRemoverDuplicados = new JButton(new AbstractAction("Remover Duplicados") {
            @Override
            public void actionPerformed(ActionEvent e) {
                removerDuplicados();
            }
        });
        buttonRemoverDuplicados.setBounds(320, 70, 170, 25);
        getContentPane().add(buttonRemoverDuplicados);

        buttonRemoverVazios = new JButton(new AbstractAction("Remover Vazios") {
            @Override
            public void actionPerformed(ActionEvent e) {
                removerVazios();
            }
        });
        buttonRemoverVazios.setBounds(320, 100, 170, 25);
        getContentPane().add(buttonRemoverVazios);

        buttonAplicarTrim = new JButton(new AbstractAction("Aplicar Trim ") {
            @Override
            public void actionPerformed(ActionEvent e) {
                aplicarTrim();
            }
        });
        buttonAplicarTrim.setBounds(320, 130, 170, 25);
        getContentPane().add(buttonAplicarTrim);

        buttonConverterMaiusculo = new JButton(new AbstractAction("Converter MAIUSCULO") {
            @Override
            public void actionPerformed(ActionEvent e) {
                converterMaiusculo();
            }
        });
        buttonConverterMaiusculo.setBounds(320, 160, 170, 25);
        getContentPane().add(buttonConverterMaiusculo);

        buttonConverterMinusculo = new JButton(new AbstractAction("Converter minúsculo") {
            @Override
            public void actionPerformed(ActionEvent e) {
                converterMinusculo();
            }
        });
        buttonConverterMinusculo.setBounds(320, 190, 170, 25);
        getContentPane().add(buttonConverterMinusculo);

        buttonConverterCapitalizar = new JButton(new AbstractAction("Converter Capitalizar") {
            @Override
            public void actionPerformed(ActionEvent e) {
                converterCapitalizar();
            }
        });
        buttonConverterCapitalizar.setBounds(320, 220, 170, 25);
        getContentPane().add(buttonConverterCapitalizar);
    }

    private String[] getLinhas() {
        return textArea.getText().split("\\n");
    }

    private void setLinhas(String[] linhas) {
        StringBuilder sb = new StringBuilder();
        for (String s : linhas) {
            if (s != null) sb.append(s).append("\n");
        }
        textArea.setText(sb.toString());
    }

    private void ordenarAZ() {
        String[] linhas = getLinhas();
        for (int i = 0; i < linhas.length - 1; i++) {
            for (int j = 0; j < linhas.length - i - 1; j++) {
                if (linhas[j].compareToIgnoreCase(linhas[j + 1]) > 0) {
                    String temp = linhas[j];
                    linhas[j] = linhas[j + 1];
                    linhas[j + 1] = temp;
                }
            }
        }
        setLinhas(linhas);
    }

    private void ordenarZA() {
        String[] linhas = getLinhas();
        for (int i = 0; i < linhas.length - 1; i++) {
            for (int j = 0; j < linhas.length - i - 1; j++) {
                if (linhas[j].compareToIgnoreCase(linhas[j + 1]) < 0) {
                    String temp = linhas[j];
                    linhas[j] = linhas[j + 1];
                    linhas[j + 1] = temp;
                }
            }
        }
        setLinhas(linhas);
    }

    private void removerDuplicados() {
        String[] linhas = getLinhas();
        String[] resultado = new String[linhas.length];
        int k = 0;
        for (int i = 0; i < linhas.length; i++) {
            boolean duplicado = false;
            for (int j = 0; j < k; j++) {
                if (linhas[i].equalsIgnoreCase(resultado[j])) {
                    duplicado = true;
                    break;
                }
            }
            if (!duplicado) {
                resultado[k++] = linhas[i];
            }
        }
        setLinhas(resultado);
    }

    private void removerVazios() {
        String[] linhas = getLinhas();
        String[] resultado = new String[linhas.length];
        int k = 0;
        for (String s : linhas) {
            if (s != null && !s.trim().isEmpty()) {
                resultado[k++] = s;
            }
        }
        setLinhas(resultado);
    }

    private void aplicarTrim() {
        String[] linhas = getLinhas();
        for (int i = 0; i < linhas.length; i++) {
            linhas[i] = linhas[i].trim();
        }
        setLinhas(linhas);
    }

    private void converterMaiusculo() {
        String[] linhas = getLinhas();
        for (int i = 0; i < linhas.length; i++) {
            linhas[i] = linhas[i].toUpperCase();
        }
        setLinhas(linhas);
    }

    private void converterMinusculo() {
        String[] linhas = getLinhas();
        for (int i = 0; i < linhas.length; i++) {
            linhas[i] = linhas[i].toLowerCase();
        }
        setLinhas(linhas);
    }

    private void converterCapitalizar() {
        converterMinusculo();
        String[] linhas = getLinhas();
        for (int i = 0; i < linhas.length; i++) {
            boolean novaPalavra = true;
            for (int j = 0; j < linhas[i].length(); j++) {
                if (linhas[i].charAt(j) == ' ') {
                    novaPalavra = true;
                    continue;
                }

                if (novaPalavra == true) {
                    char[] c = linhas[i].toCharArray();
                    c[j] = Character.toUpperCase(c[j]);
                    linhas[i] = new String(c);
                    novaPalavra = false;
                }
            }
        }
        setLinhas(linhas);
    }

    public static void main(String[] args) {
        new Main();
    }
}