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

import java.util.HashMap;
import java.util.Map;

import ems.Controller.CustomerController;
import ems.Controller.OrganizerController;
import ems.views.Login;

public class SignUp extends JFrame implements ActionListener {
    JPanel Left, Right, TopButtons, Close, RolePanel, genderPanel;
    JLabel Right1, SignUp, FName, LName, Email, Phone, TopLeft, Password, BelowPassword, BelowPasswordField, BottomLeft, BottomRight;
    JTextField Email1, FNameT, LNameT, PhoneT;
    JPasswordField Password2;
    JButton GetStarted, minimizeButton, maximizeButton, exitButton, loginBtn;
    JRadioButton userRadio, organizerRadio, maleRadio, femaleRadio;
    ButtonGroup roleGroup, genderGroup;

    public SignUp() {
        SignUp = new JLabel();
        SignUp.setText("Sign Up");
        SignUp.setFont(new Font("Forum", Font.BOLD, 15));
        SignUp.setBounds(110, 120, 200, 30);

        FName = new JLabel();
        FName.setText("<html>First Name<sup>*</sup></html>");
        FName.setFont(new Font("Times New Roman", Font.BOLD, 12));
        FName.setBounds(110, 150, 200, 30);

        FNameT = new JTextField();
        FNameT.setBounds(110, 180, 250, 30);
        FNameT.setBorder(new EtchedBorder());

        LName = new JLabel();
        LName.setText("<html>Last Name<sup>*</sup></html>");
        LName.setFont(new Font("Times New Roman", Font.BOLD, 12));
        LName.setBounds(110, 210, 200, 30);

        LNameT = new JTextField();
        LNameT.setBounds(110, 240, 250, 30);
        LNameT.setBorder(new EtchedBorder());

        Email = new JLabel();
        Email.setText("<html>Email<sup>*</sup></html>");
        Email.setFont(new Font("Times New Roman", Font.BOLD, 12));
        Email.setBounds(110, 270, 200, 30);

        Email1 = new JTextField();
        Email1.setBounds(110, 300, 250, 30);
        Email1.setBorder(new EtchedBorder());

        Phone = new JLabel();
        Phone.setText("<html>Phone<sup>*</sup></html>");
        Phone.setFont(new Font("Times New Roman", Font.BOLD, 12));
        Phone.setBounds(110, 330, 200, 30);

        PhoneT = new JTextField();
        PhoneT.setBounds(110, 360, 250, 30);
        PhoneT.setBorder(new EtchedBorder());

        Password = new JLabel();
        Password.setText("<html>Password<sup>*</sup></html>");
        Password.setFont(new Font("Times New Roman", Font.BOLD, 12));
        Password.setBounds(110, 390, 200, 30);

        Password2 = new JPasswordField();
        Password2.setBounds(110, 420, 250, 30);
        Password2.setBorder(new EtchedBorder());

        BelowPassword = new JLabel("Must be at least 8 characters");
        BelowPassword.setBounds(110, 440, 180, 30);
        BelowPassword.setFont(new Font("Verdana", Font.PLAIN, 9));

        GetStarted = new JButton();
        GetStarted.setBorder(new EtchedBorder());
        GetStarted.setText("Get Started");
        GetStarted.setBackground(Color.BLACK);
        GetStarted.setForeground(Color.white);
        GetStarted.setFont(new Font("sans serif", Font.BOLD, 12));
        GetStarted.setFocusable(false);
        GetStarted.setBounds(110, 550, 250, 30);
        GetStarted.addActionListener(this);
        GetStarted.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        BelowPasswordField = new JLabel("<html>Already have an account?<strong>Login</strong></html>");
        BelowPasswordField.setFont(new Font("sans serif", Font.PLAIN, 8));
        BelowPasswordField.setBounds(140, 570, 150, 40);
        BelowPasswordField.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        BelowPasswordField.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose(); // Close the current SignUp window
                SwingUtilities.invokeLater(() -> {
                    Login login = new Login();
                    login.setVisible(true);
                });
            }
        });

        BottomLeft = new JLabel("© Inity 2024");
        BottomLeft.setFont(new Font("Calibri", Font.BOLD, 9));
        BottomLeft.setBounds(40, 620, 150, 30);

        BottomRight = new JLabel("✉ info@Maestroagency");
        BottomRight.setFont(new Font("Deja Vu Sans", Font.BOLD, 9));
        BottomRight.setBounds(340, 620, 150, 30);

        femaleRadio = new JRadioButton("F");
        femaleRadio.setFocusable(false);
        maleRadio = new JRadioButton("M");
        maleRadio.setFocusable(false);
        genderGroup = new ButtonGroup();
        genderGroup.add(femaleRadio);
        genderGroup.add(maleRadio);
        genderPanel = new JPanel(new GridLayout(1, 2));
        genderPanel.add(femaleRadio);
        genderPanel.add(maleRadio);
        genderPanel.setBounds(110, 470, 250, 30);
        genderPanel.setBackground(new Color(255, 212, 193, 255));

        userRadio = new JRadioButton("User");
        userRadio.setFocusable(false);
        organizerRadio = new JRadioButton("Organizer");
        organizerRadio.setFocusable(false);
        roleGroup = new ButtonGroup();
        roleGroup.add(userRadio);
        roleGroup.add(organizerRadio);
        RolePanel = new JPanel(new GridLayout(1, 2));
        RolePanel.add(userRadio);
        RolePanel.add(organizerRadio);
        RolePanel.setBounds(110, 510, 250, 30);
        RolePanel.setBackground(new Color(255, 212, 193, 255));

        Left = new JPanel();
        Left.setPreferredSize(new Dimension(450, 700));
        Left.setBackground(new Color(221, 218, 238));
        Left.setLayout(null);
        Left.add(FName);
        Left.add(FNameT);
        Left.add(LName);
        Left.add(LNameT);
        Left.add(Email);
        Left.add(Email1);
        Left.add(Phone);
        Left.add(PhoneT);
        Left.add(Password);
        Left.add(Password2);
        Left.add(BelowPassword);
        Left.add(GetStarted);
        Left.add(BelowPasswordField);
        Left.add(BottomLeft);
        Left.add(BottomRight);
        Left.add(SignUp);
        Left.add(RolePanel);
        Left.add(genderPanel);

        ImageIcon Icon = new ImageIcon("img/logo3.png");
        Right1 = new JLabel();
        Right1.setIcon(Icon);
        Right1.setBorder(BorderFactory.createEmptyBorder());
        Right1.setBackground(new Color(63,63,63,255));

        Right = new JPanel();
        Right.setLayout(new BorderLayout(0, 0));
        Right.setPreferredSize(new Dimension(450, 700));
        Right.setBackground(new Color(63, 63, 63, 255));
        Right.add(Right1, BorderLayout.SOUTH);

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
        if (e.getSource() == GetStarted) {
            if (validateForm()) {
                // If validation succeeds, create dictionary and send to controller
                String firstName = FNameT.getText();
                String lastName = LNameT.getText();
                String email = Email1.getText();
                String phone = PhoneT.getText();
                String password = new String(Password2.getPassword());
                String role = userRadio.isSelected() ? "User" : "Organizer" ;
                String gender = femaleRadio.isSelected() ? "F" : "M";

                // Create a dictionary with user data
                HashMap<String, String> userData = new HashMap<>();
                userData.put("FirstName", firstName);
                userData.put("LastName", lastName);
                userData.put("Email", email);
                userData.put("Phone", phone);
                userData.put("Password", password);
                userData.put("Gender", gender);

                if(role == "User"){
                    // check if user exists
                    if (CustomerController.getCustomer(email) != null) {
                        JOptionPane.showMessageDialog(null, "User already exists");
                        // Close the current SignUp window
                        this.dispose();

                        // Navigate to Login view
                        Login loginView = new Login();
                        loginView.setVisible(true);
                    }
                    else{
                        // Send to user controller
                        boolean success = CustomerController.createCustomer(userData);

                        if (success) {
                            // Registration successful, navigate to log in view or show success message
                            JOptionPane.showMessageDialog(this, "Registration successful. Please log in.");
                            // Close the current SignUp window
                            this.dispose();

                            // Navigate to Login view
                            Login loginView = new Login();
                            loginView.setVisible(true);
                        } else {
                            // Registration failed, show error message
                            JOptionPane.showMessageDialog(this, "Registration failed. Please try again.");
                        }
                    }
                }
                else{
                    // check if user exists
                    if (OrganizerController.getOrganizer(email) != null) {
                        JOptionPane.showMessageDialog(null, "User already exists");
                        // Close the current SignUp window
                        this.dispose();

                        // Navigate to Login view
                        Login loginView = new Login();
                        loginView.setVisible(true);
                    }
                    else{
                        // Send to user controller
                        boolean success = OrganizerController.createOrganizer(userData);

                        if (success) {
                            // Registration successful, navigate to log in view or show success message
                            JOptionPane.showMessageDialog(this, "Registration successful. Please log in.");
                            // Close the current SignUp window
                            this.dispose();

                            // Navigate to Login view
                            Login loginView = new Login();
                            loginView.setVisible(true);
                        } else {
                            // Registration failed, show error message
                            JOptionPane.showMessageDialog(this, "Registration failed. Please try again.");
                        }
                    }
                }
            }
        }
    }

    private boolean validateForm() {
        if (FNameT.getText().isEmpty() || LNameT.getText().isEmpty() ||
            Email1.getText().isEmpty() || PhoneT.getText().isEmpty() ||
            Password2.getPassword().length == 0) {
            JOptionPane.showMessageDialog(this, "Please fill in all required fields.");
            return false;
        }
        return true;
    }
}
