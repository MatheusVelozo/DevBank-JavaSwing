import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class RegisterForm {
    private JTextField nameTextField;
    private JTextField dateTextField;
    private JTextField cpfTextField;
    private JPasswordField passwordField;
    private JTextField accountText;
    private JPanel mainPanel;
    private static List<Client> clientList = new ArrayList<>();

    public RegisterForm() {
        //CRIAÇÃO DA TELA DE CADASTRO DO CLIENTE.
        JFrame jFrame = new JFrame("Dev Bank App");
        jFrame.setVisible(true);
        jFrame.setSize(400, 600);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setLocationRelativeTo(null);
        jFrame.setResizable(false);

        mainPanel = new JPanel(null);
        mainPanel.setBackground(Color.gray);

        JLabel mainLabel = new JLabel("<Register NOW!>");
        mainLabel.setForeground(Color.white);

        Font labelFont = mainLabel.getFont();
        mainLabel.setFont(new Font(labelFont.getName(), Font.PLAIN, 15));
        mainLabel.setBounds(135, -100, 200, 300);

        JLabel nameLabel = new JLabel("First Name: ");
        nameLabel.setBounds(50, 100, 100, 25);
        nameLabel.setBackground(Color.lightGray);
        nameLabel.setForeground(Color.black);

        JLabel dateLabel = new JLabel("Date of Birth: ");

        dateLabel.setBounds(50, 150, 100, 25);
        dateLabel.setBackground(Color.lightGray);
        dateLabel.setForeground(Color.black);

        JLabel cpfLabel = new JLabel("CPF: ");

        cpfLabel.setBounds(50, 200, 100, 25);
        cpfLabel.setBackground(Color.lightGray);
        cpfLabel.setForeground(Color.black);

        JLabel passwordLabel = new JLabel("Password: ");

        passwordLabel.setBounds(50, 250, 100, 25);
        passwordLabel.setBackground(Color.lightGray);
        passwordLabel.setForeground(Color.black);

        nameTextField = new JTextField();
        nameTextField.setBounds(130, 100, 190, 25);
        nameTextField.setBorder(null);

        dateTextField = new JTextField();
        dateTextField.setBounds(130, 150, 190, 25);
        dateTextField.setBorder(null);

        cpfTextField = new JTextField();
        cpfTextField.setBounds(130, 200, 190, 25);
        cpfTextField.setBorder(null);

        passwordField = new JPasswordField();
        passwordField.setBounds(130, 250, 190, 25);
        passwordField.setBorder(null);

        JLabel accountLabel = new JLabel("What account do you want to open at Dev Bank?");
        accountLabel.setBounds(50, 280, 350, 25);
        accountLabel.setForeground(Color.black);

        JLabel account = new JLabel("Saving Account or Checking Account");
        account.setBounds(70, 300, 300, 25);
        account.setForeground(Color.black);

        accountText = new JTextField();
        accountText.setBounds(70, 330, 250, 20);
        accountText.setBorder(null);

        JButton sendButton = new JButton("Register");
        sendButton.setBounds(80, 370, 100, 25);
        sendButton.setBackground(Color.darkGray);
        sendButton.setForeground(Color.white);
        sendButton.setBorder(null);
        sendButton.setFocusPainted(false);
        sendButton.setBorder(new SoftBevelBorder(BevelBorder.RAISED));

        JButton cancelButton = new JButton("Cancel");
        cancelButton.setBounds(200, 370, 100, 25);
        cancelButton.setBackground(Color.darkGray);
        cancelButton.setForeground(Color.red);
        cancelButton.setBorder(null);
        cancelButton.setFocusPainted(false);
        cancelButton.setBorder(new SoftBevelBorder(BevelBorder.RAISED));

        mainPanel.add(mainLabel);
        mainPanel.add(nameLabel);
        mainPanel.add(dateLabel);
        mainPanel.add(cpfLabel);
        mainPanel.add(passwordLabel);
        mainPanel.add(nameTextField);
        mainPanel.add(dateTextField);
        mainPanel.add(cpfTextField);
        mainPanel.add(passwordField);
        mainPanel.add(accountLabel);
        mainPanel.add(account);
        mainPanel.add(accountText);
        mainPanel.add(sendButton);
        mainPanel.add(cancelButton);

        jFrame.add(mainPanel);

        //BOTÃO QUE CRIA O NOVO CLIENTE E ATRIBUI A ELE AS INFORMAÇÕES PASSADAS NA TELA DE CADASTRO.
        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Client client = new Client();
                client.setName(getNameTextField().getText());
                client.setDateOfBirth(getDateTextField().getText());
                client.setCpf(getCpfTextField().getText());
                client.setAccountType(getAccountText().getText());
                client.setPassword(getPasswordField().getPassword());
                client.setAccountNumber(generateUniqueNumber());
                client.setBalance(0.0);
                addToClientList(client);

                //MENSAGEM DE CONFIRMAÇÃO DOS DADOS E CONTA CRIADA.
                JOptionPane.showMessageDialog(null, "Name: " + client.getName() + "\n"
                                + "Date of birth: " + client.getDateOfBirth() + "\n"
                                + "CPF: " + client.getCpf() + "\n"
                                + "Account Type: " + client.getAccountType(),
                        "Account created successfully!", JOptionPane.INFORMATION_MESSAGE);
                jFrame.dispose();
            }
        });

        //CANCELAR OPERAÇÃO
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame.dispose();
            }
        });

    }

    //MÉTODO QUE ADICIONA ESTE NOVO CLIENTE A UMA LISTA DE CLIENTES CADASTRADOS.
    public void addToClientList(Client client) {
        clientList.add(client);
        //OS SYSOUT SÃO APENAS PARA ACOMPANHAR AS OPERAÇÕES ACONTECENDO PELO CONSOLE.
        System.out.println("Cliente adicionado à lista: " + client.getName());
        System.out.println("Número de clientes cadastrados: " + clientList.size());
        System.out.println("Clientes: " + clientList);
        System.out.println("Numero da conta gerado: " + client.getAccountNumber());
        System.out.println("Saldo: " + client.getBalance());
        //System.out.println("Senha: " + new String(client.getPassword()));
    }

    //MÉTODO QUE BUSCA O CLIENTE QUANDO CHAMADO NA ÁREA DE LOGIN, ATRÁVES DOS PARÂMENTROS DE NOME E SENHA INSERIDOS.
    public static Client getClientByNameAndPassword(String name, char[] password) {
        for (Client client : clientList) {
            if (client.getName().equals(name) && Arrays.equals(client.getPassword(), password)) {
                return client;
            }
        }
        return null;
    }

    public static List<Client> getClientList() {
        return clientList;
    }

    public JTextField getNameTextField() {
        return nameTextField;
    }

    public JTextField getDateTextField() {
        return dateTextField;
    }

    public JTextField getCpfTextField() {
        return cpfTextField;
    }

    public JPasswordField getPasswordField() {
        return passwordField;
    }

    public JTextField getAccountText() {
        return accountText;
    }

    private static final Random random = new Random();

    //MÉTODO QUE GERA UM NÚMERO DE CONTA PARA O CLIENTE DE 05 POSIÇÕES, CADA CLIENTE RECEBE UM NÚMERO ÚNICO.
    private Integer generateUniqueNumber() {
        int min = 10000;
        int max = 99999;
        return random.nextInt((max - min) + 1) + min;
    }
}
