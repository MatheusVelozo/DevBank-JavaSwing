import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class LoginArea {
    private JPanel mainPanel;
    private JTextField userName;
    private JPasswordField passwordField;

    public LoginArea(boolean online) {

    }
    public LoginArea() {
        //CRIAÇÃO DA TELA DE LOGIN - JAVA SWING.
        JFrame jFrame = new JFrame("Dev Bank App");
        jFrame.setVisible(true);
        jFrame.setSize(400, 300);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setLocationRelativeTo(null);
        jFrame.setResizable(false);

        mainPanel = new JPanel(null);
        mainPanel.setBackground(Color.darkGray);

        JLabel mainLabel = new JLabel("< Enter your Account >");
        mainLabel.setForeground(Color.white);

        Font labelFont = mainLabel.getFont();
        mainLabel.setFont(new Font(labelFont.getName(), Font.PLAIN, 15));
        mainLabel.setBounds(120, -100, 200, 300);

        JLabel userLabel = new JLabel("User name: ");
        userLabel.setBounds(50, 100, 100, 25);
        userLabel.setForeground(Color.white);

        JLabel passwordLabel = new JLabel("Password: ");

        passwordLabel.setBounds(50, 150, 100, 25);
        passwordLabel.setForeground(Color.white);

        userName = new JTextField();
        userName.setBounds(130, 100, 190, 25);
        userName.setBorder(null);

        passwordField = new JPasswordField();
        passwordField.setBounds(130,150,190,25);
        passwordField.setBorder(null);

        JButton loginButton = new JButton("Login");
        loginButton.setBounds(80, 190, 100,25);
        loginButton.setBackground(Color.darkGray);
        loginButton.setForeground(Color.white);
        loginButton.setBorder(null);
        loginButton.setFocusPainted(false);
        loginButton.setBorder(new SoftBevelBorder(BevelBorder.RAISED));

        JButton cancelButton = new JButton("Cancel");
        cancelButton.setBounds(200, 190, 100,25);
        cancelButton.setBackground(Color.darkGray);
        cancelButton.setForeground(Color.red);
        cancelButton.setBorder(null);
        cancelButton.setFocusPainted(false);
        cancelButton.setBorder(new SoftBevelBorder(BevelBorder.RAISED));

        mainPanel.add(mainLabel);
        mainPanel.add(userLabel);
        mainPanel.add(userName);
        mainPanel.add(passwordLabel);
        mainPanel.add(passwordField);
        mainPanel.add(loginButton);
        mainPanel.add(cancelButton);

        jFrame.add(mainPanel);

        //BOTÃO PARA REALIZAR O LOGIN.
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //VALIDAÇÃO PARA OS DADOS INSERIDOS NOS CAMPOS DE USER E PASSWORD.
                //O APP ATRIBUI A UMA VARIÁVEL O NOME E SENHA INSERIDOS.
                String nameToSearch = userName.getText();
                char[] passwordToSearch = passwordField.getPassword();

                //DENTRO DESTE MÉTODO OS O NOME E A SENHA SERÃO USADOS COMO PARÂMETROS.
                Client foundClient = RegisterForm.getClientByNameAndPassword(nameToSearch, passwordToSearch);
                //CASO ESTE CLIENTE SEJA ENCONTRADO, E ATRIBUIDO TRUE AO STATUS DELE.
                foundClient.setStatus(true);
                boolean statusSearch = foundClient.getStatus();
                //PROCURA O CLIENTE COM STATUS ONLINE, E SENHA CORRESPONDENTE A INSERIDA NO CAMPO.
                Client foundClientOn = getClientOnLine(statusSearch, passwordToSearch);

                //CASO O CLIENTE SEJA ENCONTRADO APÓS A BUSCA, E VALIDAÇÃO, É ABERTO A ÁREA DO CLIENTE.
                if (foundClient != null) {
                    if (foundClientOn != null) {

                        JOptionPane loadingPane = new JOptionPane("Welcome to < DEV BANK >", JOptionPane.INFORMATION_MESSAGE);
                        JDialog loadingDialog = loadingPane.createDialog("Loading...");

                        Timer timer = new Timer(1000, new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                loadingDialog.dispose();
                            }
                        });
                        timer.setRepeats(false);
                        timer.start();
                        loadingDialog.setVisible(true);
                        jFrame.dispose();
                        ClientArea clientArea = new ClientArea();
                    }
                }else {
                    JOptionPane loadingPane = new JOptionPane("Invalid Account.", JOptionPane.INFORMATION_MESSAGE);
                    JDialog loadingDialog = loadingPane.createDialog("User or password invalid.");

                    Timer timer = new Timer(1000, new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            loadingDialog.dispose();
                        }
                    });
                    timer.setRepeats(false);
                    timer.start();
                    loadingDialog.setVisible(true);
                    jFrame.dispose();
                }
            }
        });

        //CANCELAR TELA DE LOGIN.
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame.dispose();
            }
        });

    }
    //MÉTODO PARA PESQUISAR O CLIENTE QUE ESTA COM O STATUS ONLINE, E O PASSWORD É O MESMO INFORMADO NO LOGIN.
     public Client getClientOnLine(Boolean online, char[] password) {
         java.util.List<Client> clientList = RegisterForm.getClientList();

         if (clientList != null) {
             for (Client client : clientList) {
                 if (client.getStatus() == online && Arrays.equals(client.getPassword(), password)) {
                     System.out.println("Cliente logado: " + client);
                     return client;
                 }
             }
         }
        return null;
    }
    public JPasswordField getPasswordField() {
        return passwordField;
    }
}
