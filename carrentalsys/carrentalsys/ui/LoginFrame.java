package com.carrental.ui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.carrental.controller.CarRentalController;

public class LoginFrame extends JFrame {

    private static final long serialVersionUID = 1L;

    private JTextField usernameField;
    private JPasswordField passwordField;

    private CarRentalController controller;

    public LoginFrame(CarRentalController controller) {

        this.controller = controller;

        setTitle("Car Rental System - Login");

        setSize(500, 400);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        createUI();
    }

    private void createUI() {

        JPanel mainPanel = new JPanel(new BorderLayout());

        mainPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        30, 40, 30, 40
                )
        );

        JLabel title = new JLabel(
                "CAR RENTAL SYSTEM",
                SwingConstants.CENTER
        );

        title.setFont(
                new Font("Arial", Font.BOLD, 28)
        );

        mainPanel.add(
                title,
                BorderLayout.NORTH
        );

        JPanel formPanel =
                new JPanel(new GridBagLayout());

        GridBagConstraints gbc =
                new GridBagConstraints();

        gbc.insets =
                new Insets(10, 10, 10, 10);

        gbc.fill =
                GridBagConstraints.HORIZONTAL;

        JLabel usernameLabel =
                new JLabel("Username:");

        JLabel passwordLabel =
                new JLabel("Password:");

        usernameField =
                new JTextField(20);

        passwordField =
                new JPasswordField(20);

        JButton loginButton =
                new JButton("LOGIN");

        gbc.gridx = 0;
        gbc.gridy = 0;

        formPanel.add(
                usernameLabel,
                gbc
        );

        gbc.gridx = 1;

        formPanel.add(
                usernameField,
                gbc
        );

        gbc.gridx = 0;
        gbc.gridy = 1;

        formPanel.add(
                passwordLabel,
                gbc
        );

        gbc.gridx = 1;

        formPanel.add(
                passwordField,
                gbc
        );

        gbc.gridx = 1;
        gbc.gridy = 2;

        formPanel.add(
                loginButton,
                gbc
        );

        loginButton.addActionListener(
                e -> login()
        );

        passwordField.addActionListener(
                e -> login()
        );

        mainPanel.add(
                formPanel,
                BorderLayout.CENTER
        );

        add(mainPanel);
    }

    private void login() {

        String username =
                usernameField.getText().trim();

        String password =
                new String(
                        passwordField.getPassword()
                );

        if (username.equals("admin")
                && password.equals("admin")) {

            DashboardFrame dashboard =
                    new DashboardFrame(
                            controller
                    );

            dashboard.setVisible(true);

            dispose();

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "Invalid username or password",
                    "Login Failed",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }
}