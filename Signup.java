package electricity.biling.system;

import javax.swing.*;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Image;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class Signup extends JFrame implements ActionListener {
    Choice loginAs;
    TextField meterText, EmployerText, userNameText, nameText, passwordText;
    JButton create, back;

    Signup() {
        super("Signup Page");
        getContentPane().setBackground(new Color(168, 203, 255));
        JLabel createAs = new JLabel("Create Account As");
        createAs.setBounds(30, 50, 125, 20);
        add(createAs);
        loginAs = new Choice();
        loginAs.add("Admin");
        loginAs.add("Customer");
        loginAs.setBounds(170, 50, 120, 20);
        add(loginAs);

        JLabel meterNo = new JLabel("Meter Number");
        meterNo.setBounds(30, 100, 125, 20);
        meterNo.setVisible(false);
        add(meterNo);
        meterText = new TextField();
        meterText.setBounds(170, 100, 125, 20);
        meterText.setVisible(false);
        add(meterText);

        JLabel Employer = new JLabel("Employer ID");
        Employer.setBounds(30, 100, 125, 20);
        Employer.setVisible(true);
        add(Employer);
        EmployerText = new TextField();
        EmployerText.setBounds(170, 100, 125, 20);
        EmployerText.setVisible(true);
        add(EmployerText);

        JLabel userName = new JLabel("UserName");
        userName.setBounds(30, 140, 125, 20);
        add(userName);
        userNameText = new TextField();
        userNameText.setBounds(170, 140, 125, 20);
        add(userNameText);

        JLabel name = new JLabel("Name");
        name.setBounds(30, 180, 125, 20);
        add(name);
        nameText = new TextField();
        nameText.setBounds(170, 180, 125, 20);
        add(nameText);

        JLabel password = new JLabel("Password");
        password.setBounds(30, 220, 125, 20);
        add(password);
        passwordText = new TextField();
        passwordText.setBounds(170, 220, 125, 20);
        add(passwordText);

        loginAs.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                String user = loginAs.getSelectedItem();
                if (user.equals("Customer")) {
                    Employer.setVisible(false);
                    EmployerText.setVisible(false);
                    meterNo.setVisible(true);
                    meterText.setVisible(true);
                } else {
                    Employer.setVisible(true);
                    EmployerText.setVisible(true);
                    meterNo.setVisible(false);
                    meterText.setVisible(false);
                }
            }
        });

        create = new JButton("Create");
        create.setBackground(new Color(66, 127, 219));
        create.setForeground(Color.BLACK);// text ka color
        create.setBounds(50, 285, 100, 25);
        create.addActionListener(this);
        add(create);

        back = new JButton("Back");
        back.setBackground(new Color(66, 127, 219));
        back.setForeground(Color.BLACK);// text ka color
        back.setBounds(180, 285, 100, 25);
        back.addActionListener(this);
        add(back);

        ImageIcon boyIcon = new ImageIcon(ClassLoader.getSystemResource("electricity/biling/system/register.png"));
        Image boyImg = boyIcon.getImage().getScaledInstance(250, 250, Image.SCALE_DEFAULT);
        ImageIcon boyIcon2 = new ImageIcon(boyImg);
        JLabel boyLabel = new JLabel(boyIcon2);
        boyLabel.setBounds(305, 30, 250, 250);
        add(boyLabel);

        setSize(600, 380);
        setLocation(500, 200);
        setLayout(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == create) {
            @SuppressWarnings("unused")
            String sloginAs = loginAs.getSelectedItem();
            String susername = userNameText.getText();
            String sname = nameText.getText();
            String spassword = passwordText.getText();
            String smeter = meterText.getText();
            try {
                database c = new database();
                String query = null;
                query = "insert into Signup values('" + smeter + "','" + susername + "','" + sname + "','" + spassword
                        + "','" + sloginAs + "')";
                c.statement.execute(query);
                JOptionPane.showMessageDialog(null, "Account Created");
                setVisible(false);
                new Login();
            } catch (Exception E) {
                E.printStackTrace();
            }
        } else if (e.getSource() == back) {
            setVisible(false);
            new Login();
        }
    }

    public static void main(String[] args) {
        new Signup();
    }
}