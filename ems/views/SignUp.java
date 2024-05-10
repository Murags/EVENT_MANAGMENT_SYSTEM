import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SignUp extends JFrame implements ActionListener {
    JPanel Left, Right, TopButtons, Close, RolePanel;
    JLabel Right1, SignUp, FName, LName, Email, Phone, TopLeft, Password, BelowPassword, BelowPasswordField, BottomLeft, BottomRight;
    JTextField Email1, FNameT, LNameT, PhoneT;
    JPasswordField Password2;
    JButton GetStarted, minimizeButton, maximizeButton, exitButton, loginBtn;
    JRadioButton userRadio, organizerRadio;
    ButtonGroup roleGroup;

    SignUp() {
        SignUp = new JLabel();
        SignUp.setText("Sign Up");
        SignUp.setFont(new Font("Forum", Font.BOLD, 15));
        SignUp.setBounds(110, 140, 200, 30);

        FName = new JLabel();
        FName.setText("<html>First Name<sup>*</sup></html>");
        FName.setFont(new Font("Times New Roman", Font.BOLD, 12));
        FName.setBounds(110, 170, 200, 30);

        FNameT = new JTextField();
        FNameT.setBounds(110, 200, 250, 30);
        FNameT.setBorder(new EtchedBorder());

        LName = new JLabel();
        LName.setText("<html>Last Name<sup>*</sup></html>");
        LName.setFont(new Font("Times New Roman", Font.BOLD, 12));
        LName.setBounds(110, 230, 200, 30);

        LNameT = new JTextField();
        LNameT.setBounds(110, 260, 250, 30);
        LNameT.setBorder(new EtchedBorder());

        Email = new JLabel();
        Email.setText("<html>Email<sup>*</sup></html>");
        Email.setFont(new Font("Times New Roman", Font.BOLD, 12));
        Email.setBounds(110, 290, 200, 30);

        Email1 = new JTextField();
        Email1.setBounds(110, 320, 250, 30);
        Email1.setBorder(new EtchedBorder());

        Phone = new JLabel();
        Phone.setText("<html>Phone<sup>*</sup></html>");
        Phone.setFont(new Font("Times New Roman", Font.BOLD, 12));
        Phone.setBounds(110, 350, 200, 30);

        PhoneT = new JTextField();
        PhoneT.setBounds(110, 380, 250, 30);
        PhoneT.setBorder(new EtchedBorder());

        Password = new JLabel();
        Password.setText("<html>Password<sup>*</sup></html>");
        Password.setFont(new Font("Times New Roman", Font.BOLD, 12));
        Password.setBounds(110, 410, 200, 30);

        Password2 = new JPasswordField();
        Password2.setBounds(110, 440, 250, 30);
        Password2.setBorder(new EtchedBorder());

        BelowPassword = new JLabel("Must be at least 8 characters");
        BelowPassword.setBounds(110, 470, 180, 30);
        BelowPassword.setFont(new Font("Verdana", Font.PLAIN, 9));

        GetStarted = new JButton();
        GetStarted.setBorder(new EtchedBorder());
        GetStarted.setText("Get Started");
        GetStarted.setBackground(Color.BLACK);
        GetStarted.setForeground(Color.white);
        GetStarted.setFont(new Font("sans serif", Font.BOLD, 12));
        GetStarted.setFocusable(false);
        GetStarted.setBounds(110, 510, 250, 30);
        GetStarted.addActionListener(this);

        BelowPasswordField = new JLabel("Already have an account?");
        BelowPasswordField.setFont(new Font("sans serif", Font.PLAIN, 8));
        BelowPasswordField.setBounds(140, 530, 150, 40);

        BottomLeft = new JLabel("© Inity 2024");
        BottomLeft.setFont(new Font("Calibri", Font.BOLD, 9));
        BottomLeft.setBounds(40, 620, 150, 30);

        BottomRight = new JLabel("✉ info@Maestroagency");
        BottomRight.setFont(new Font("Deja Vu Sans", Font.BOLD, 9));
        BottomRight.setBounds(340, 620, 150, 30);

        userRadio = new JRadioButton("User");
        organizerRadio = new JRadioButton("Organizer");
        roleGroup = new ButtonGroup();
        roleGroup.add(userRadio);
        roleGroup.add(organizerRadio);
        RolePanel = new JPanel(new GridLayout(1, 2));
        RolePanel.add(userRadio);
        RolePanel.add(organizerRadio);
        RolePanel.setBounds(110, 470, 250, 30);
        RolePanel.setBackground(new Color(255, 212, 193, 255));

        Left = new JPanel();
        Left.setPreferredSize(new Dimension(450, 650));
        Left.setBackground(new Color(255, 212, 193, 255));
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
        if (e.getSource() == GetStarted) {
            String username = FNameT.getText() + " " + LNameT.getText();
            String email = Email1.getText();
            String password = new String(Password2.getPassword());
            String role = userRadio.isSelected() ? "User" : "Organizer";
            createDictionary(username, email, password, role);
        }
    }

    // Function to create a dictionary
    public void createDictionary(String username, String email, String password, String role) {
        // Create dictionary here or perform any other action
        System.out.println("Username: " + username);
        System.out.println("Email: " + email);
        System.out.println("Password: " + password);
        System.out.println("Role: " + role);
    }
}
