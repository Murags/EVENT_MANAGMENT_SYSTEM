package ems.views;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Home extends JFrame implements ActionListener
{
    JPanel Panel1;
    JLabel Label1;
    JButton SignUp,Login1;

    public Home()
    {

        Login1 = new JButton("LOGIN");
        Login1.setForeground(Color.WHITE);
        Login1.setFont(new Font("Deja Vu Sans",Font.BOLD,12));
        Login1.setBounds(165,370,180,40);
        //Login.setContentAreaFilled(false);
        Login1.setFocusable(false);
        Login1.setRolloverEnabled(false);
        Login1.setBorder(new EtchedBorder());
        Login1.addActionListener(this);
        Login1.setBackground(new Color(63,63,63,255));

        SignUp = new JButton("SIGNUP");
        SignUp.setFont(new Font("Deja Vu Sans",Font.BOLD,12));
        //SignUp.setContentAreaFilled(false);
        SignUp.setForeground(Color.WHITE);
        SignUp.setFocusable(false);
        SignUp.addActionListener(this);
        SignUp.setBounds(165,420,180,40);
        SignUp.setBorder(new EtchedBorder());
        SignUp.setBackground(new Color(63,63,63,255));


        Label1 = new JLabel();
        ImageIcon Icon = new ImageIcon("C:\\Users\\User\\Desktop\\It projects\\EMS\\EVENT_MANAGMENT_SYSTEM\\img\\logo3.png");
        Label1.setIcon(Icon);
        Label1.add(SignUp);
        Label1.add(Login1);

        Panel1 = new JPanel();
        Panel1.setLayout(new BorderLayout());
        Panel1.add(Label1,BorderLayout.CENTER);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500,500);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);
        this.add(Panel1);
        this.add(Label1);
        

    }

    @Override
    public void actionPerformed(ActionEvent e)
    {


        if (e.getSource() == SignUp)
        {
            SignUp a= new SignUp();
            a.setVisible(true);
            this.setVisible(false);
        }

        if (e.getSource() == Login1)
        {
            Login b = new Login();
            b.setVisible(true);
            this.setVisible(false);
        }
    }

}

