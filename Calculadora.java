import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Color;
import java.awt.event.*; 

public class Calculadora {

    JFrame janelaPrincipal;
    JTextField visor;

    private double numero1;
    private char operacao;
    private boolean novoNumero;

    private final int MAX_DIGITOS = 15;

    public Calculadora() {
        janelaPrincipal = new JFrame("Calculadora (Contraste Azul)");
        visor = new JTextField();
        novoNumero = true; 

        Color corFundo = new Color(20, 30, 48); 
        Color corVisor = Color.BLACK; 
        Color corBotaoNum = new Color(50, 60, 80); 
        Color corBotaoOp = new Color(0, 128, 255); 
        Color corBotaoClear = new Color(255, 90, 90); 
        Color corTexto = Color.WHITE; 
        Color corTextoVisor = Color.WHITE; 
        Color corBotaoFuncao = new Color(30, 45, 70); 

        janelaPrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janelaPrincipal.setSize(340, 600);
        janelaPrincipal.setLayout(new BorderLayout(8, 8)); 
        janelaPrincipal.getContentPane().setBackground(corFundo);
        janelaPrincipal.setResizable(false); 
        
        visor.setEditable(false);
        visor.setHorizontalAlignment(JTextField.RIGHT);
        visor.setFont(new Font("Segoe UI", Font.PLAIN, 38)); 
        visor.setBackground(corVisor);
        visor.setForeground(corTextoVisor);
        visor.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(10, 10, 10)), 
            BorderFactory.createEmptyBorder(15, 15, 15, 15)
        )); 

        
        JPanel painelBotoes = new JPanel(new GridBagLayout());
        painelBotoes.setBackground(corFundo);
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.fill = GridBagConstraints.BOTH; 
        gbc.weightx = 1.0; 
        gbc.weighty = 1.0; 
        gbc.insets = new Insets(4, 4, 4, 4); 

        JButton botaoClear = new JButton("C");
        configurarBotaoVisual(botaoClear, corBotaoClear, corTexto);
        botaoClear.addActionListener(e -> processarClear());

        JButton botaoBackspace = new JButton("<-");
        configurarBotaoVisual(botaoBackspace, corBotaoFuncao, corTexto); 
        botaoBackspace.addActionListener(e -> processarBackspace());

        JButton botaoPrimo = new JButton("Primo");
        configurarBotaoVisual(botaoPrimo, corBotaoFuncao, corTexto); 
        botaoPrimo.addActionListener(e -> processarPrimo());
        
        JButton btn7 = AdicionarEventoBotao("7", corBotaoNum, corTexto);
        JButton btn8 = AdicionarEventoBotao("8", corBotaoNum, corTexto);
        JButton btn9 = AdicionarEventoBotao("9", corBotaoNum, corTexto);
        JButton btnDiv = AdicionarEventoBotao("/", corBotaoOp, corTexto);

        JButton btn4 = AdicionarEventoBotao("4", corBotaoNum, corTexto);
        JButton btn5 = AdicionarEventoBotao("5", corBotaoNum, corTexto);
        JButton btn6 = AdicionarEventoBotao("6", corBotaoNum, corTexto);
        JButton btnMult = AdicionarEventoBotao("*", corBotaoOp, corTexto);

        JButton btn1 = AdicionarEventoBotao("1", corBotaoNum, corTexto);
        JButton btn2 = AdicionarEventoBotao("2", corBotaoNum, corTexto);
        JButton btn3 = AdicionarEventoBotao("3", corBotaoNum, corTexto);
        JButton btnSub = AdicionarEventoBotao("-", corBotaoOp, corTexto);

        JButton btn0 = AdicionarEventoBotao("0", corBotaoNum, corTexto);
        JButton btnPonto = AdicionarEventoBotao(".", corBotaoNum, corTexto);
        
        JButton btnIgual = AdicionarEventoBotao("=", corBotaoOp, corTexto);
        JButton btnSoma = AdicionarEventoBotao("+", corBotaoOp, corTexto);

        
        addComp(painelBotoes, gbc, botaoClear,     0, 0, 1, 1); 
        addComp(painelBotoes, gbc, botaoBackspace, 3, 0, 1, 1); 

        addComp(painelBotoes, gbc, btn7,     0, 1, 1, 1);
        addComp(painelBotoes, gbc, btn8,     1, 1, 1, 1);
        addComp(painelBotoes, gbc, btn9,     2, 1, 1, 1);
        addComp(painelBotoes, gbc, btnDiv,   3, 1, 1, 1);
        
        addComp(painelBotoes, gbc, btn4,     0, 2, 1, 1);
        addComp(painelBotoes, gbc, btn5,     1, 2, 1, 1);
        addComp(painelBotoes, gbc, btn6,     2, 2, 1, 1);
        addComp(painelBotoes, gbc, btnMult,  3, 2, 1, 1);

        addComp(painelBotoes, gbc, btn1,     0, 3, 1, 1);
        addComp(painelBotoes, gbc, btn2,     1, 3, 1, 1);
        addComp(painelBotoes, gbc, btn3,     2, 3, 1, 1);
        addComp(painelBotoes, gbc, btnSub,   3, 3, 1, 1);

        addComp(painelBotoes, gbc, btnPonto, 0, 4, 1, 1); 
        addComp(painelBotoes, gbc, btn0,     1, 4, 1, 1);
        addComp(painelBotoes, gbc, btnIgual, 2, 4, 1, 1);
        addComp(painelBotoes, gbc, btnSoma,  3, 4, 1, 1);
        
        addComp(painelBotoes, gbc, botaoPrimo, 0, 5, 4, 1); 
        
        
        janelaPrincipal.add(visor, BorderLayout.NORTH);
        janelaPrincipal.add(painelBotoes, BorderLayout.CENTER); 

        janelaPrincipal.setFocusable(true);
        janelaPrincipal.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                char keyChar = e.getKeyChar();
                int keyCode = e.getKeyCode();

                if (keyChar >= '0' && keyChar <= '9') {
                    processarNumero(String.valueOf(keyChar));
                } else if (keyChar == '.') {
                    processarDecimal();
                } else if (keyChar == '+') {
                    processarOperador("+");
                } else if (keyChar == '-') {
                    processarOperador("-");
                } else if (keyChar == '*') {
                    processarOperador("*");
                } else if (keyChar == '/') {
                    processarOperador("/");
                } else if (keyCode == KeyEvent.VK_ENTER || keyChar == '=') {
                    processarIgual();
                } else if (keyCode == KeyEvent.VK_BACK_SPACE) {
                    processarBackspace();
                } else if (keyCode == KeyEvent.VK_ESCAPE) {
                    processarClear();
                } else if (keyChar == 'p' || keyChar == 'P') {
                    processarPrimo();
                }
            }
        });

        janelaPrincipal.setVisible(true);
        janelaPrincipal.requestFocusInWindow(); 
    }
    
    private void addComp(JPanel panel, GridBagConstraints gbc, java.awt.Component comp, int x, int y, int w, int h) {
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.gridwidth = w;
        gbc.gridheight = h;
        panel.add(comp, gbc);
    }

    private String formatarResultado(double numero) {
        if (numero == (long) numero) {
            return String.valueOf((long) numero);
        } else {
            return String.valueOf(numero);
        }
    }

    private void configurarBotaoVisual(JButton botao, Color corFundo, Color corTexto) {
        botao.setFont(new Font("Segoe UI", Font.BOLD, 20)); 
        botao.setBackground(corFundo);
        botao.setForeground(corTexto);
        botao.setOpaque(true);
        botao.setBorder(BorderFactory.createLineBorder(new Color(35, 45, 65))); 
    }

    public JButton AdicionarEventoBotao(String texto, Color corFundo, Color corTexto){
        JButton botao = new JButton(texto);
        configurarBotaoVisual(botao, corFundo, corTexto);
        
        botao.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                String textoBotao = botao.getText();
                
                if(textoBotao.matches("[0-9]")){
                    processarNumero(textoBotao);
                } else if (textoBotao.equals(".")) {
                    processarDecimal();
                } else if (textoBotao.matches("[+\\-*/]")) {
                    processarOperador(textoBotao);
                } else if (textoBotao.equals("=")) {
                    processarIgual();
                }
            }
        });
        return botao; 
    }

    private void processarNumero(String texto) {
        if (novoNumero) {
            visor.setText(texto);
            novoNumero = false;
        } else {
            if (visor.getText().length() < MAX_DIGITOS) {
                visor.setText(visor.getText() + texto);
            }
        }
    }

    private void processarDecimal() {
        if (novoNumero) {
            visor.setText("0.");
            novoNumero = false;
        } else {
            if (!visor.getText().contains(".") && visor.getText().length() < MAX_DIGITOS) {
                visor.setText(visor.getText() + ".");
            }
        }
    }

    private void processarBackspace() {
        String textoAtual = visor.getText();
        if (textoAtual.length() > 0) {
            visor.setText(textoAtual.substring(0, textoAtual.length() - 1));
            if (visor.getText().isEmpty()) {
                novoNumero = true;
            }
        }
    }

    private void processarClear() {
        visor.setText("");
        numero1 = 0;
        operacao = '\0';
        novoNumero = true;
    }

    private void processarOperador(String texto) {
        if (visor.getText().isEmpty()) return; 

        try {
            if (!novoNumero && operacao != '\0') {
                double numero2 = Double.parseDouble(visor.getText());
                switch (operacao) {
                    case '+': numero1 = numero1 + numero2; break;
                    case '-': numero1 = numero1 - numero2; break;
                    case '*': numero1 = numero1 * numero2; break;
                    case '/':
                        if (numero2 == 0) {
                            visor.setText("Erro");
                            novoNumero = true;
                            return;
                        }
                        numero1 = numero1 / numero2;
                        break;
                }
                
            } else {
                numero1 = Double.parseDouble(visor.getText());
            }
            
            operacao = texto.charAt(0);
            novoNumero = true;

        } catch (NumberFormatException ex) {
            visor.setText("Erro");
            novoNumero = true;
        }
    }

    private void processarIgual() {
        if (visor.getText().isEmpty() || operacao == '\0') return;

        try {
            if (operacao != '\0') {
                double numero2 = Double.parseDouble(visor.getText());
                double resultado = 0;

                switch (operacao) {
                    case '+': resultado = numero1 + numero2; break;
                    case '-': resultado = numero1 - numero2; break;
                    case '*': resultado = numero1 * numero2; break;
                    case '/':
                        if (numero2 == 0) {
                            visor.setText("Erro");
                            novoNumero = true;
                            return; 
                        }
                        resultado = numero1 / numero2;
                        break;
                }
                
                visor.setText(formatarResultado(resultado));
                numero1 = resultado;
                operacao = '\0';
                novoNumero = true;
            }
        } catch (NumberFormatException ex) {
            visor.setText("Erro");
            novoNumero = true;
        }
    }

    private boolean isPrimo(long n) {
        if (n < 2) return false;
        if (n == 2) return true;
        if (n % 2 == 0) return false;
        for (long i = 3; i * i <= n; i += 2) {
            if (n % i == 0) return false;
        }
        return true;
    }

    private void processarPrimo() {
        if (visor.getText().isEmpty() || visor.getText().equals("Erro")) {
            return;
        }
        try {
            double valor = Double.parseDouble(visor.getText());
            
            if (valor != (long) valor) {
                JOptionPane.showMessageDialog(janelaPrincipal, 
                        "O teste de primaridade funciona apenas para números inteiros.", 
                        "Não é Inteiro", 
                        JOptionPane.WARNING_MESSAGE);
                janelaPrincipal.requestFocusInWindow();
                return;
            }

            long numeroParaTestar = (long) valor;
            String mensagem;

            if (isPrimo(numeroParaTestar)) {
                mensagem = numeroParaTestar + " é um número PRIMO.";
            } else {
                mensagem = numeroParaTestar + " NÃO é um número primo.";
            }

            JOptionPane.showMessageDialog(janelaPrincipal, 
                    mensagem, 
                    "Teste se o número é Primo", 
                    JOptionPane.INFORMATION_MESSAGE);
            
            janelaPrincipal.requestFocusInWindow();

        } catch (NumberFormatException ex) {
            visor.setText("Erro");
            novoNumero = true;
        }
    }

    public static void main(String[] args) {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
        }

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Calculadora();
            }
        });
    }
}