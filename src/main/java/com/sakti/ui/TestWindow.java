package com.sakti.ui;

import javax.swing.*;
import java.awt.*;

public class TestWindow {

    public static void main(String[] args) {

        JFrame frame = new JFrame("LOGIN SAKTI");
        frame.setSize(500,350);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10,10,10,10);

        JLabel title = new JLabel("SISTEM SAKTI");
        title.setFont(new Font("Arial", Font.BOLD, 24));

        JLabel lblUser = new JLabel("Username");
        JTextField txtUser = new JTextField(15);

        JLabel lblPass = new JLabel("Password");
        JPasswordField txtPass = new JPasswordField(15);

        JButton btnLogin = new JButton("LOGIN");

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(title, gbc);

        gbc.gridwidth = 1;

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(lblUser, gbc);

        gbc.gridx = 1;
        panel.add(txtUser, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(lblPass, gbc);

        gbc.gridx = 1;
        panel.add(txtPass, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        panel.add(btnLogin, gbc);

        frame.add(panel);

        frame.setVisible(true);
    }
}