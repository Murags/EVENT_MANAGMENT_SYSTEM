package ems.views;

import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import ems.Controller.AuthController;
import ems.Controller.CustomerController;
import ems.views.CustomerView;
import ems.Controller.OrganizerController;
import ems.views.OrganizerView;

import ems.Models.Organizer;
import ems.Models.Customer;

public class Login extends JFrame implements ActionListener {
    JPanel Left, Right, TopButtons, Close, RolePanel;
    JLabel Login, Email, Password, BelowPasswordField, BottomLeft, BottomRight;
    JTextField Email1;
    JPasswordField Password2;
    JRadioButton userRadio, organizerRadio;
    JButton LoginButton, minimizeButton, maximizeButton, exitButton, signUpBtn;
    ButtonGroup roleGroup;

    public Login() {
        Login = new JLabel();
        Login.setText("Login");
        Login.setFont(new Font("Forum", Font.BOLD, 15));
        Login.setBounds(110, 120, 200, 30);

        Email = new JLabel();
        Email.setText("<html>Email<sup>*</sup></html>");
        Email.setFont(new Font("Times New Roman", Font.BOLD, 12));
        Email.setBounds(110, 150, 200, 30);

        Email1 = new JTextField();
        Email1.setBounds(110, 180, 250, 30);
        Email1.setBorder(new EtchedBorder());

        Password = new JLabel();
        Password.setText("<html>Password<sup>*</sup></html>");
        Password.setFont(new Font("Times New Roman", Font.BOLD, 12));
        Password.setBounds(110, 210, 200, 30);

        Password2 = new JPasswordField();
        Password2.setBounds(110, 240, 250, 30);
        Password2.setBorder(new EtchedBorder());

        userRadio = new JRadioButton("User");
        organizerRadio = new JRadioButton("Organizer");
        roleGroup = new ButtonGroup();
        roleGroup.add(userRadio);
        roleGroup.add(organizerRadio);
        RolePanel = new JPanel(new GridLayout(1, 2));
        RolePanel.add(userRadio);
        RolePanel.add(organizerRadio);
        RolePanel.setBounds(110, 280, 250, 30);
        RolePanel.setBackground(new Color(255, 212, 193, 255));

        LoginButton = new JButton();
        LoginButton.setBorder(new EtchedBorder());
        LoginButton.setText("Login");
        LoginButton.setBackground(Color.BLACK);
        LoginButton.setForeground(Color.white);
        LoginButton.setFont(new Font("sans serif", Font.BOLD, 12));
        LoginButton.setFocusable(false);
        LoginButton.setBounds(110, 320, 250, 30);
        LoginButton.addActionListener(this);

        BelowPasswordField = new JLabel("Don't have an account?");
        BelowPasswordField.setFont(new Font("sans serif", Font.PLAIN, 8));
        BelowPasswordField.setBounds(140, 340, 150, 40);
        BelowPasswordField.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        BelowPasswordField.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose(); // Close the current Login window
                SwingUtilities.invokeLater(() -> {//GUI threading
                    SignUp signUp = new SignUp();
                    signUp.setVisible(true);
                });
            }
        });

        BottomLeft = new JLabel("© Inity 2024");
        BottomLeft.setFont(new Font("Calibri", Font.BOLD, 9));
        BottomLeft.setBounds(40, 370, 150, 30);

        BottomRight = new JLabel("✉ info@Maestroagency");
        BottomRight.setFont(new Font("Deja Vu Sans", Font.BOLD, 9));
        BottomRight.setBounds(340, 370, 150, 30);

        Left = new JPanel();
        Left.setPreferredSize(new Dimension(450, 700));
        Left.setBackground(new Color(255, 212, 193, 255));
        Left.setLayout(null);
        Left.add(Email);
        Left.add(Email1);
        Left.add(Password);
        Left.add(Password2);
        Left.add(LoginButton);
        Left.add(BelowPasswordField);
        Left.add(BottomLeft);
        Left.add(BottomRight);
        Left.add(Login);
        Left.add(RolePanel);

        Right = new JPanel();
        Right.setLayout(new BorderLayout(0, 0));
        Right.setPreferredSize(new Dimension(450, 700));
        Right.setBackground(new Color(63, 63, 63, 255));

        exitButton = new JButton();
        ImageIcon icon = new ImageIcon("img/RED BUTTON.jpg");
        exitButton.setIcon(icon);
        exitButton.setFocusable(false);
        exitButton.setContentAreaFilled(false);
        exitButton.setBackground(new Color(50, 50, 47, 255));
        exitButton.setBorder(BorderFactory.createEmptyBorder());
        exitButton.addActionListener(e -> System.exit(0));

        maximizeButton = new JButton();
        maximizeButton.setBackground(new Color(50, 50, 47, 255));
        maximizeButton.setContentAreaFilled(false);
        maximizeButton.setPreferredSize(new Dimension(40, 40));
        ImageIcon icon1 = new ImageIcon("img/Orange colour.jpg");
        maximizeButton.setIcon(icon1);
        maximizeButton.setFocusable(false);
        maximizeButton.setBorder(BorderFactory.createEmptyBorder());

        minimizeButton = new JButton();
        minimizeButton.setBackground(new Color(50, 50, 47, 255));
        ImageIcon icon3 = new ImageIcon("img/Minimize Button.jpg");
        minimizeButton.setIcon(icon3);
        minimizeButton.setFocusable(false);
        minimizeButton.setContentAreaFilled(false);
        minimizeButton.setBorder(BorderFactory.createEmptyBorder());
        minimizeButton.addActionListener(ae -> setState(ICONIFIED));

        TopButtons = new JPanel();
        TopButtons.setLayout(new GridLayout(1, 3, 0, 0));
        TopButtons.setPreferredSize(new Dimension(70, 20));
        TopButtons.setBackground(new Color(50, 50, 47, 255));
        TopButtons.setBorder(BorderFactory.createEmptyBorder());
        TopButtons.add(minimizeButton);
        TopButtons.add(maximizeButton);
        TopButtons.add(exitButton);

        Close = new JPanel();
        Close.setPreferredSize(new Dimension(450, 20));
        Close.setLayout(new BorderLayout(0, 0));
        Close.setBackground(new Color(50, 50, 47, 255));
        Close.add(TopButtons, BorderLayout.EAST);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("EVENT MANAGEMENT");
        this.setLayout(new BorderLayout());
        this.setSize(900, 680);
        this.setUndecorated(true);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);
        add(Left, BorderLayout.WEST);
        add(Right, BorderLayout.EAST);
        add(Close, BorderLayout.NORTH);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == LoginButton) {
            if (validateForm()) {
                // If validation succeeds, authenticate user
                String email = Email1.getText();
                String password = new String(Password2.getPassword());
                String role = userRadio.isSelected() ? "User" : "Organizer" ;

                boolean success = AuthController.login(email, password, role);
                if (success) {
                    // Redirect to the main application page
                    JOptionPane.showMessageDialog(this, "Login successful!");
                    this.dispose();
                    if(role == "User"){
                        Customer customer = CustomerController.getCustomer(email);
                        CustomerView customerView = new CustomerView(customer);
                        customerView.setVisible(true);
                    }
                    else{
                        Organizer organizer = OrganizerController.getOrganizer(email);
                        OrganizerView organizerView = new OrganizerView(organizer);
                        organizerView.setVisible(true);
                    }

                } else {
                    JOptionPane.showMessageDialog(this, "Invalid credentials. Please try again.");
                }
            }
        }
    }

    private boolean validateForm() {
        if (Email1.getText().isEmpty() || Password2.getPassword().length == 0) {
            JOptionPane.showMessageDialog(this, "Please fill in all required fields.");
            return false;
        }
        return true;
    }
}
