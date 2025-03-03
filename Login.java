package electricity.biling.system;

import java.awt.Choice;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

import javax.swing.*;

public class Login extends JFrame implements ActionListener {
    JTextField userText, passwordText;
    Choice loginChoice;
    JButton loginButton, cancelButton, signupButton;

    Login() {
        super("Login"); // super hamesa constructor ka first statement hoga
        // getContentPane().setBackground(250);
        JLabel username = new JLabel("Username");
        username.setBounds(300, 60, 100, 20);
        add(username);
        userText = new JTextField();
        userText.setBounds(400, 60, 150, 20);
        add(userText);

        JLabel password = new JLabel("Password");
        password.setBounds(300, 100, 100, 20);
        add(password);
        passwordText = new JTextField();
        passwordText.setBounds(400, 100, 150, 20);
        add(passwordText);

        JLabel loggin = new JLabel("Loggin in As");
        loggin.setBounds(300, 140, 100, 20);
        add(loggin);
        loginChoice = new Choice();
        loginChoice.add("Admin");
        loginChoice.add("Customer");
        loginChoice.setBounds(400, 140, 150, 20);
        add(loginChoice);

        loginButton = new JButton("Login");
        loginButton.setBounds(330, 180, 100, 20);
        loginButton.addActionListener(this);
        add(loginButton);

        cancelButton = new JButton("Cancel");
        cancelButton.setBounds(460, 180, 100, 20);
        cancelButton.addActionListener(this);
        add(cancelButton);

        signupButton = new JButton("Signup");
        signupButton.setBounds(400, 215, 100, 20);
        signupButton.addActionListener(this);
        add(signupButton);

        ImageIcon profileOne = new ImageIcon(ClassLoader.getSystemResource("electricity/biling/system/next.jpg"));
        Image profileTwo = profileOne.getImage().getScaledInstance(250, 250, Image.SCALE_DEFAULT);
        ImageIcon fprofile = new ImageIcon(profileTwo);
        JLabel profileLable = new JLabel(fprofile);
        profileLable.setBounds(5, 5, 250, 250);
        add(profileLable);

        setSize(640, 300);
        setLocation(400, 200);
        setLayout(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            String susername = userText.getText();
            String spassword = passwordText.getText();
            String user = loginChoice.getSelectedItem();

            try {
                database c = new database();
                String query = "select*from Signup where username='" + susername + "'and password='" + spassword
                        + "'and usertype='" + user + "'";
                ResultSet resultSet = c.statement.executeQuery(query);

                if (resultSet.next()) {
                    setVisible(false);
                    new main_class();
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid Login");
                }

            } catch (Exception E) {
                E.printStackTrace();
            }
        } else if (e.getSource() == cancelButton) {
            setVisible(false);
        } else if (e.getSource() == signupButton) {
            setVisible(false);
            new Signup();
        }
    }

    public static void main(String[] args) {
        new Login();
    }
}