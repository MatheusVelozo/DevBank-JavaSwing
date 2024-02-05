import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BankService {
    private JPanel mainPanel;
    public BankService() {
        //CRIAÇÃO DO PAINEL PRINCIPAL - JAVA SWING.
        JFrame jFrame = new JFrame("Dev Bank App");
        jFrame.setVisible(true);
        jFrame.setSize(400, 600);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setLocationRelativeTo(null);
        jFrame.setResizable(false);

        mainPanel = new JPanel(null);
        mainPanel.setBackground(Color.darkGray);

        JPanel backgroundLabel = new JPanel();
        backgroundLabel.setBounds(5,100,375,35);
        backgroundLabel.setBackground(Color.black);

        JLabel mainLabel = new JLabel("<DEV BANK>");
        mainLabel.setForeground(Color.white);

        Font labelFont = mainLabel.getFont();
        mainLabel.setFont(new Font(labelFont.getName(), Font.PLAIN, 20));
        mainLabel.setBounds(145,10,0,0);

        JButton regButton = new JButton();
        regButton.setText("Register");
        regButton.setBounds(40,250,100,25);
        regButton.setBackground(Color.gray);
        regButton.setForeground(Color.black);
        regButton.setBorder(new SoftBevelBorder(BevelBorder.RAISED));
        regButton.setFocusPainted(false);

        JButton logButton = new JButton();
        logButton.setText("Login");
        logButton.setBounds(145,250,100,25);
        logButton.setBackground(Color.gray);
        logButton.setForeground(Color.black);
        logButton.setBorder(new SoftBevelBorder(BevelBorder.RAISED));
        logButton.setFocusPainted(false);

        JButton exButton = new JButton();
        exButton.setText("Exit");
        exButton.setBounds(250,250,100,25);
        exButton.setBackground(Color.gray);
        exButton.setForeground(Color.black);
        exButton.setBorder(new SoftBevelBorder(BevelBorder.RAISED));
        exButton.setFocusPainted(false);

        mainPanel.add(backgroundLabel);
        backgroundLabel.add(mainLabel);
        mainPanel.add(regButton);
        mainPanel.add(logButton);
        mainPanel.add(exButton);

        //BOTÃO QUE CHAMA A TELA DE CADASTRO.
        regButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RegisterForm registerForm = new RegisterForm();
            }
        });

        //BOTÃO QUE CHAMA A TELA DE LOGIN.
        logButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginArea loginArea = new LoginArea();
            }
        });

        //FECHAR O APLICATIVO.
        exButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        jFrame.add(mainPanel);
    }

}
