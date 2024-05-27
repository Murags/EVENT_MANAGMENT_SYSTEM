package eventmanagement;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class SignUP extends JFrame implements ActionListener
{
    JPanel Left,Right,TopButtons,Close;
    JLabel Right1,SignUp,Name1,Email,Password,BelowPassword,ConfirmPassword,BelowPassword2,BelowPasswordField,BottomLeft,BottomRight;
    JTextField Email1,Name;
    JPasswordField Password2,ConfirmPassword2;
    CheckboxGroup radioGroup;
    Checkbox radio1;
    Checkbox radio2;
    JButton GetStarted,minimizeButton,maximizeButton,exitButton,Login;
    SignUP()
    {
        SignUp = new JLabel();
        SignUp.setText("Sign Up");
        SignUp.setFont(new Font("Helvetica",Font.BOLD,15));
        SignUp.setBounds(110,120,200,30);

        Name1 = new JLabel();
        Name1.setText("<html>Name<sup>*</sup></html>");
        Name1.setFont(new Font("Helvetica",Font.BOLD,12));
        Name1.setBounds(110,150,200,30);

        Name=new JTextField();
        Name.setBounds(110,190,250,30);
        Name.setBorder(new EtchedBorder());

        Email = new JLabel();
        Email.setText("<html>Email<sup>*</sup></html>");
        Email.setFont(new Font("Helvetica",Font.BOLD,12));
        Email.setBounds(110,230,200,30);

        Email1 = new JTextField();
        Email1.setBounds(110,270,250,30);
        Email1.setBorder(new EtchedBorder());


        Password = new JLabel();
        Password.setText("<html>Enter a Password</html>");
        Password.setFont(new Font("Helvetica",Font.BOLD,12));
        Password.setBounds(110,310,200,30);

        Password2 = new JPasswordField();
        Password2.setBounds(110,340,250,30);
        Password2.setBorder(new EtchedBorder());

        BelowPassword = new JLabel("Must be at least 8 characters");
        BelowPassword.setBounds(110,370,180,30);
        BelowPassword.setFont(new Font("Helvetica",Font.PLAIN|Font.ITALIC,12));

        ConfirmPassword = new JLabel();
        ConfirmPassword.setText("Confirm Password");
        ConfirmPassword.setFont(new Font("Helvetica",Font.BOLD,12));
        ConfirmPassword.setBounds(110,410,200,30);

        ConfirmPassword2 = new JPasswordField();
        ConfirmPassword2.setBounds(110,440,250,30);
        ConfirmPassword2.setBorder(new EtchedBorder());

        BelowPassword2 = new JLabel("Must be at least 8 characters");
        BelowPassword2.setBounds(110,470,180,30);
        BelowPassword2.setFont(new Font("Helvetica",Font.PLAIN|Font.ITALIC,12));

        radioGroup = new CheckboxGroup();
        radio1 = new Checkbox("User",radioGroup,false);
        radio2 = new Checkbox("Organizer", radioGroup, false);
        radio1.setBounds(110,500,70,30);
        radio2.setBounds(190,500,70,30);

        GetStarted = new JButton();
        GetStarted.setBorder(new EtchedBorder());
        GetStarted.setText("Get Started");
        GetStarted.setBackground(new Color(43, 41, 57));
        GetStarted.setForeground(Color.white);
        GetStarted.setFont(new Font("Helvetica",Font.BOLD,12));
        GetStarted.setFocusable(false);
        GetStarted.setBounds(110,550,250,30);

        BelowPasswordField = new JLabel("Already have an account?");
        BelowPasswordField.setFont(new Font("sans serif",Font.PLAIN,8));
        BelowPasswordField.setBounds(140,570,150,40);

        BottomLeft = new JLabel("© Inity 2024");
        BottomLeft.setFont(new Font("Calibri",Font.BOLD,9));
        BottomLeft.setBounds(40,620,150,30);

        BottomRight = new JLabel("✉ info@Maestroagency");
        BottomRight.setFont(new Font("Deja Vu Sans",Font.BOLD,9));
        BottomRight.setBounds(340,620,150,30);

        Login = new JButton("Login");
        Login.setFont(new Font("Deja Vu Sans",Font.BOLD,9));
        Login.setBounds(230,570,50,40);
        Login.setBackground(Color.white);
        Login.setFocusable(false);
        Login.setContentAreaFilled(false);
        Login.setBorder(BorderFactory.createEmptyBorder());
        Login.addActionListener(this);



        Left = new JPanel();
        Left.setPreferredSize(new Dimension(450,650));
        Left.setBackground(new Color(221, 218, 238));
        Left.setLayout(null);
        Left.add(SignUp);
        Left.add(Name1);
        Left.add(Name);
        Left.add(Email);
        Left.add(Email1);
        Left.add(Password);
        Left.add(Password2);
        Left.add(ConfirmPassword);
        Left.add(ConfirmPassword2);
        Left.add(BelowPassword2);
        Left.add(radio1);
        Left.add(radio2);
        Left.add(BelowPassword);
        Left.add(GetStarted);
        Left.add(BelowPasswordField);
        Left.add(BottomLeft);
        Left.add(BottomRight);
        Left.add(Login);


        ImageIcon Icon = new ImageIcon("logo3.png");
        Right1 = new JLabel();
        Right1.setIcon(Icon);
        Right1.setBorder(BorderFactory.createEmptyBorder());
        Right1.setBackground(new Color(63,63,63,255));

        Right = new JPanel();
        Right.setLayout(new BorderLayout(0,0));
        Right.setPreferredSize(new Dimension(450,700));
        Right.setBackground(new Color(63,63,63,255));
        Right.add(Right1,BorderLayout.SOUTH);

        exitButton = new JButton();
        ImageIcon icon = new ImageIcon("RED BUTTON.jpg");
        exitButton.setIcon(icon);
        exitButton.setFocusable(false);
        exitButton.setContentAreaFilled(false);
        exitButton.setBackground(new Color(50, 50, 47, 255));
        exitButton.setBorder(BorderFactory.createEmptyBorder());
        //METHOD OVERRIDING(Polymorphism)
        exitButton.addActionListener(e -> System.exit(0));

        //Customized JButton for the Maximize Button
        maximizeButton = new JButton();
        maximizeButton.setBackground(new Color(50, 50, 47, 255));
        maximizeButton.setContentAreaFilled(false);
        maximizeButton.setPreferredSize(new Dimension(40, 40));
        ImageIcon icon1 = new ImageIcon("Orange colour.jpg");
        maximizeButton.setIcon(icon1);
        maximizeButton.setFocusable(false);
        maximizeButton.setBorder(BorderFactory.createEmptyBorder());
        /*
        //METHOD OVERRIDING(Polymorphism)
        maximizeButton.addActionListener(e ->
        {
            if (getExtendedState() == NORMAL)
                setExtendedState(MAXIMIZED_BOTH);
            else
                setExtendedState(NORMAL);
        });

         */

        //Customized JButton for the Minimize Button
        minimizeButton = new JButton();
        minimizeButton.setBackground(new Color(50, 50, 47, 255));
        ImageIcon icon3 = new ImageIcon("Minimize Button.jpg");
        minimizeButton.setIcon(icon3);
        minimizeButton.setFocusable(false);
        minimizeButton.setContentAreaFilled(false);
        minimizeButton.setBorder(BorderFactory.createEmptyBorder());
        minimizeButton.addActionListener(ae -> setState(ICONIFIED));



        TopButtons = new JPanel();
        TopButtons.setLayout(new GridLayout(1,3,0,0));
        TopButtons.setPreferredSize(new Dimension(70,20));
        TopButtons.setBackground(new Color(50, 50, 47, 255));
        TopButtons.setBorder(BorderFactory.createEmptyBorder());
        TopButtons.add(minimizeButton);
        TopButtons.add(maximizeButton);
        TopButtons.add(exitButton);

        Close = new JPanel();
        Close.setPreferredSize(new Dimension(450,20));
        Close.setLayout(new BorderLayout(0,0));
        Close.setBackground(new Color(50, 50, 47, 255));
        Close.add(TopButtons, BorderLayout.EAST);


        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.setSize(900,680);
        this.setUndecorated(true);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);
        add(Left, BorderLayout.WEST);
        add(Right, BorderLayout.EAST);
        add(Close, BorderLayout.NORTH);

    }

   @Override
    public void actionPerformed(ActionEvent e)
    {
        String s = e.getActionCommand();

        if (s.equals("Login"))
        {
            Email1.setText("Welcome");
        }
    }


}

