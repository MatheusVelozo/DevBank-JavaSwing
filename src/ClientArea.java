import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClientArea {
    private JPanel mainPanel;
    private JButton deposit;
    private JButton withdraw;
    private JPanel background;
    private JLabel infoLabel;
    private JLabel infoDeposit, infoWithdraw;
    private JTextField depositField, withdrawField;
    private JButton depositButton, withButton;
    private JButton cancelButton, celButton;

    public ClientArea() {
        //CRIAÇÃO DA TELA PRINCIPAL NA ÁREA DO CLIENTE - JAVA SWING.
        JFrame jFrame = new JFrame("Dev Bank App");
        jFrame.setVisible(true);
        jFrame.setSize(400, 600);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setLocationRelativeTo(null);
        jFrame.setResizable(false);


        mainPanel = new JPanel(null);
        mainPanel.setBackground(Color.darkGray);

        JPanel backgroundLabel = new JPanel();
        backgroundLabel.setBounds(5,10,375,35);
        backgroundLabel.setBackground(Color.black);

        JLabel mainLabel = new JLabel("< DEV BANK >");
        mainLabel.setForeground(Color.white);

        Font labelFont = mainLabel.getFont();
        mainLabel.setFont(new Font(labelFont.getName(), Font.PLAIN, 20));
        mainLabel.setBounds(145,10,0,0);

        JPanel backgroundInfo = new JPanel(new FlowLayout(FlowLayout.LEFT));
        backgroundInfo.setBounds(5,50,375,120);
        backgroundInfo.setBackground(Color.gray);

        background = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(5, 10, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        background.setBounds(5, 175, 375,295);
        background.setBackground(Color.gray);

        JPanel backgroundActions = new JPanel();
        backgroundActions.setLayout(new GridLayout(1, 3));
        backgroundActions.setBounds(5,480,375,30);
        backgroundActions.setBackground(Color.gray);

        JPanel underbar = new JPanel();
        underbar.setBounds(5,520,375,35);
        underbar.setBackground(Color.black);

        infoLabel = new JLabel();
        infoLabel.setForeground(Color.black);

        LoginArea loginArea = new LoginArea(true);
        Client clientOnLine = loginArea.getClientOnLine(true, Client.getPassword());

        //O CLIENTE ENCONTRADO COM O STATUS ONLINE É RETORNADO E SEUS DADOS EXIBIDOS NA TELA DE INFORMAÇÕES.
        if (clientOnLine != null) {
            Font newFont = new Font(null, Font.PLAIN, 16);
            infoLabel.setFont(newFont);
            infoLabel.setText("<html> Client Name: " + clientOnLine.getName() +"<br>" +
                    "Date of birth: "+ clientOnLine.getDateOfBirth() +"<br>" +
                    "Account type: " + clientOnLine.getAccountType() +"<br>" +
                    "Account number: " + clientOnLine.getAccountNumber() +"<br>" +
                    "Balance $: " + clientOnLine.getBalance() + "</html>");
        }

        withdraw = new JButton("Withdraw");
        withdraw.setBounds(25,0,100,25);
        withdraw.setBackground(Color.lightGray);
        withdraw.setBorder(null);
        withdraw.setFocusPainted(false);
        withdraw.setBorder(new SoftBevelBorder(BevelBorder.RAISED));

        deposit = new JButton("Deposit");
        deposit.setBounds(25,0,100,25);
        deposit.setBackground(Color.lightGray);
        deposit.setBorder(null);
        deposit.setFocusPainted(false);
        deposit.setBorder(new SoftBevelBorder(BevelBorder.RAISED));

        JButton exit = new JButton("Exit");
        exit.setBounds(25,0,80,25);
        exit.setBackground(Color.lightGray);
        exit.setBorder(null);
        exit.setFocusPainted(false);
        exit.setBorder(new SoftBevelBorder(BevelBorder.RAISED));

        mainPanel.add(backgroundLabel);
        backgroundLabel.add(mainLabel);
        mainPanel.add(background);
        mainPanel.add(backgroundInfo);
        backgroundInfo.add(infoLabel);
        mainPanel.add(backgroundActions);
        backgroundActions.add(withdraw);
        backgroundActions.add(deposit);
        backgroundActions.add(exit);
        mainPanel.add(underbar);


        jFrame.add(mainPanel);

        //=============================== DEPOSIT BUTTON SETTINGS==============================================/

        //AO SER CLICLADO NO BOTÃO PARA DEPÓSITO A OPÇÃO É MOSTRTADA NA TELA PRINCIPAL PARA SER REALIZADA A OPERAÇÃO.
        deposit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!backgroundContainsContent()) {
                    background.removeAll();

                    infoDeposit = new JLabel("What amount do you want to deposit?");
                    depositField = new JTextField();
                    depositButton = new JButton("Deposit");
                    cancelButton = new JButton("Cancel");

                    infoDeposit.setForeground(Color.black);
                    gbc.gridy = 0;
                    background.add(infoDeposit, gbc);

                    depositField.setPreferredSize(new Dimension(200, 25));
                    depositField.setBorder(null);
                    gbc.gridy = 1;
                    background.add(depositField, gbc);

                    gbc.gridwidth = 1;
                    depositButton.setPreferredSize(new Dimension(200, 25));
                    depositButton.setBackground(Color.black);
                    depositButton.setForeground(Color.white);
                    depositButton.setBorder(null);
                    depositButton.setFocusPainted(false);
                    depositButton.setBorder(new SoftBevelBorder(BevelBorder.RAISED));
                    gbc.gridy = 2;
                    background.add(depositButton, gbc);


                    gbc.gridy = 3;
                    cancelButton.setPreferredSize(new Dimension(200, 25));
                    cancelButton.setBackground(Color.red.darker());
                    cancelButton.setForeground(Color.black);
                    cancelButton.setBorder(null);
                    cancelButton.setFocusPainted(false);
                    cancelButton.setBorder(new SoftBevelBorder(BevelBorder.RAISED));
                    background.add(cancelButton, gbc);

                    addDepositListeners();

                    deposit.setEnabled(false);
                    withdraw.setEnabled(true);
                    background.revalidate();
                    background.repaint();
                }
            }
            //MÉTODO PARA ACEITAR APENAS NÚMEROS INTEIROS E PONTO NO JTEXTFIELD.
            private void addDepositListeners() {
                depositField.addKeyListener(new java.awt.event.KeyAdapter() {
                    @Override
                    public void keyTyped(java.awt.event.KeyEvent evt) {
                        char c = evt.getKeyChar();
                        if (!(Character.isDigit(c) || c == '.')) {
                            evt.consume();
                        }
                    }
                });

                //MÉTODO PARA REALIZAR O DEPÓSITO NA CONTA DO CLIENTE.
                depositButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        double dep = Double.parseDouble(depositField.getText());
                        clientOnLine.setBalance(dep + clientOnLine.getBalance());
                        System.out.println("Novo saldo: " + clientOnLine.getBalance());
                        updateInfoLabel();
                        depositField.setText("");

                        background.revalidate();
                        background.repaint();
                    }
                });

                //CANCELAR A OPERAÇÃO.
                cancelButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        background.removeAll();
                        background.revalidate();
                        background.repaint();
                        deposit.setEnabled(true);
                    }
                });
            }
            //MÉTODO PARA QUE AO SER REALIZADA A OPERAÇÃO, O PAINEL COM AS INFORMAÇÕES DO CLIENTE SEJAM ATUALIZADAS COM O NOVO SALDO.
            private void updateInfoLabel() {
                Font newFont = new Font(null, Font.PLAIN, 16);
                infoLabel.setFont(newFont);
                infoLabel.setText("<html> Client Name: " + clientOnLine.getName() + "<br>" +
                        "Date of birth: " + clientOnLine.getDateOfBirth() + "<br>" +
                        "Account type: " + clientOnLine.getAccountType() + "<br>" +
                        "Account number: " + clientOnLine.getAccountNumber() + "<br>" +
                        "Balance $: " + clientOnLine.getBalance() + "</html>");

                infoLabel.revalidate();
                infoLabel.repaint();
            }
            //MÉTODO QUE VERIFICA SE HÁ ALGUM COMPONENTE NO PAINEL DAS OPERAÇÕES, CASO HAJA, ELE É REMOVIDO QUANDO O BOTÃO
            //DE DEPÓSITO PRINCIPAL É CLICADO PARA QUE SEJA IMPRESSO A OPÇÃO ESCOLHIDA.
            private boolean backgroundContainsContent() {
                Component[] components = background.getComponents();
                for (Component component : components) {
                    if (component.equals(infoDeposit) || component.equals(depositField) || component.equals(depositButton) || component.equals(cancelButton)) {
                        return true;
                    }
                }
                return false;
            }

        });

        //=============================== WITHDRAW BUTTON SETTINGS==============================================/

        //AO SER CLICLADO NO BOTÃO PARA SAQUE A OPÇÃO É MOSTRTADA NA TELA PRINCIPAL PARA SER REALIZADA A OPERAÇÃO.
        withdraw.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!backgroundWithdrawContainsContent()) {
                    background.removeAll();

                    infoWithdraw = new JLabel("What amount do you want to withdraw?");
                    withdrawField = new JTextField();
                    withButton = new JButton("Withdraw");
                    celButton = new JButton("Cancel");

                    infoWithdraw.setForeground(Color.black);
                    gbc.gridy = 0;
                    background.add(infoWithdraw, gbc);

                    withdrawField.setPreferredSize(new Dimension(200, 25));
                    withdrawField.setBorder(null);
                    gbc.gridy = 1;
                    background.add(withdrawField, gbc);

                    withButton.setPreferredSize(new Dimension(200, 25));
                    withButton.setBackground(Color.black);
                    withButton.setForeground(Color.white);
                    withButton.setBorder(null);
                    withButton.setFocusPainted(false);
                    withButton.setBorder(new SoftBevelBorder(BevelBorder.RAISED));
                    gbc.gridy = 2;
                    background.add(withButton, gbc);

                    gbc.gridy = 3;
                    celButton.setPreferredSize(new Dimension(200, 25));
                    celButton.setBackground(Color.red.darker());
                    celButton.setForeground(Color.black);
                    celButton.setBorder(null);
                    celButton.setFocusPainted(false);
                    celButton.setBorder(new SoftBevelBorder(BevelBorder.RAISED));
                    background.add(celButton, gbc);

                    addWithdrawListener();

                    withdraw.setEnabled(false);
                    deposit.setEnabled(true);
                    background.revalidate();
                    background.repaint();
                }
            }
            //MÉTODO PARA ACEITAR APENAS NÚMEROS INTEIROS E PONTO NO JTEXTFIELD.
            private void addWithdrawListener() {
                withdrawField.addKeyListener(new java.awt.event.KeyAdapter() {
                    @Override
                    public void keyTyped(java.awt.event.KeyEvent evt) {
                        char c = evt.getKeyChar();
                        if (!(Character.isDigit(c) || c == '.')) {
                            evt.consume();
                        }
                    }
                });

                //MÉTODO PARA REALIZAR O SAQUE NA CONTA DO CLIENTE.
                withButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        double wit = Double.parseDouble(withdrawField.getText());
                        clientOnLine.setBalance(clientOnLine.getBalance() - wit);
                        System.out.println("Novo Saldo: " + clientOnLine.getBalance());
                        updateWithInfoLabel();
                        withdrawField.setText("");
                    }
                });

                //CANCELAR A OPERAÇÃO.
                celButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        background.removeAll();
                        background.revalidate();
                        background.repaint();
                        withdraw.setEnabled(true);
                    }
                });
            }

            //MÉTODO PARA ATUALIZAR AS INFORMAÇÕES DO CLIENTE APÓS A OPERAÇÃO SER REALIZADA.
            private void updateWithInfoLabel() {
                Font newFont = new Font(null, Font.PLAIN, 16);
                infoLabel.setFont(newFont);
                infoLabel.setText("<html> Client Name: " + clientOnLine.getName() + "<br>" +
                        "Date of birth: " + clientOnLine.getDateOfBirth() + "<br>" +
                        "Account type: " + clientOnLine.getAccountType() + "<br>" +
                        "Account number: " + clientOnLine.getAccountNumber() + "<br>" +
                        "Balance $: " + clientOnLine.getBalance() + "</html>");

                infoLabel.revalidate();
                infoLabel.repaint();
            }
            //MÉTODO QUE VERIFICA SE HÁ ALGUM COMPONENTE NO PAINEL DAS OPERAÇÕES, CASO HAJA, ELE É REMOVIDO QUANDO O BOTÃO
            //DE DEPÓSITO PRINCIPAL É CLICADO PARA QUE SEJA IMPRESSO A OPÇÃO ESCOLHIDA.
            private boolean backgroundWithdrawContainsContent() {
                Component[] components = background.getComponents();
                for (Component component : components) {
                    if (component.equals(infoWithdraw) || component.equals(withdrawField) || component.equals(withButton) || component.equals(celButton)) {
                        return true;
                    }
                }
                return false;
            }
        });

        //BOTÃO PARA SAIR DA ÁREA DO CLIENTE, E RETORNAR A TELA PRINCIPAL DO APLICATIVO.
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame.dispose();
            }
        });
    }
/*
    public JLabel getInfoLabel() {
        return infoLabel;
    }

    public void setInfoLabel(JLabel infoLabel) {
        this.infoLabel = infoLabel;
    }

    public JButton getDeposit() {
        return deposit;
    }

    public void setDeposit(JButton deposit) {
        this.deposit = deposit;
    }

    public JButton getWithdraw() {
        return withdraw;
    }

    public void setWithdraw(JButton withdraw) {
        this.withdraw = withdraw;
    }

    public JPanel getBackground() {
        return background;
    }

    public void setBackground(JPanel background) {
        this.background = background;
    }

 */
}



