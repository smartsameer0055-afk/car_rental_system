package com.carrental.ui;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.carrental.controller.CarRentalController;

public class RentalPanel extends JPanel {

    private static final long serialVersionUID = 1L;

    private CarRentalController controller;

    private JTextField carIdField;
    private JTextField customerIdField;
    private JTextField daysField;

    private JFrame frame;

    public RentalPanel(
            CarRentalController controller) {

        this.controller = controller;

        setLayout(
                new BorderLayout()
        );

        setBorder(
                BorderFactory.createEmptyBorder(
                        30, 40, 30, 40
                )
        );

        createUI();
    }

    private void createUI() {

        JLabel title =
                new JLabel(
                        "RENT A CAR"
                );

        JPanel formPanel =
                new JPanel(
                        new GridLayout(
                                4,
                                2,
                                10,
                                10
                        )
                );

        carIdField =
                new JTextField();

        customerIdField =
                new JTextField();

        daysField =
                new JTextField();

        JButton rentButton =
                new JButton(
                        "RENT CAR"
                );

        formPanel.add(
                new JLabel("Car ID:")
        );

        formPanel.add(carIdField);

        formPanel.add(
                new JLabel("Customer ID:")
        );

        formPanel.add(customerIdField);

        formPanel.add(
                new JLabel("Number of Days:")
        );

        formPanel.add(daysField);

        formPanel.add(
                new JLabel("")
        );

        formPanel.add(rentButton);

        add(
                title,
                BorderLayout.NORTH
        );

        add(
                formPanel,
                BorderLayout.CENTER
        );

        rentButton.addActionListener(
                e -> rentCar()
        );
    }

    private void rentCar() {

        try {

            int carId =
                    Integer.parseInt(
                            carIdField.getText()
                    );

            int customerId =
                    Integer.parseInt(
                            customerIdField.getText()
                    );

            int days =
                    Integer.parseInt(
                            daysField.getText()
                    );

            if (days <= 0) {

                JOptionPane.showMessageDialog(
                        this,
                        "Days must be greater than zero."
                );

                return;
            }

            int rentalId =
                    controller.rentCar(
                            carId,
                            customerId,
                            days
                    );

            JOptionPane.showMessageDialog(
                    this,
                    "Car rented successfully!\n\n"
                            + "Rental ID: "
                            + rentalId
            );

            carIdField.setText("");

            customerIdField.setText("");

            daysField.setText("");

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Enter valid numeric values.",
                    "Input Error",
                    JOptionPane.ERROR_MESSAGE
            );

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    e.getMessage(),
                    "Rental Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    public void showWindow() {

        frame =
                new JFrame(
                        "Rent Car"
                );

        frame.setContentPane(this);

        frame.setSize(550, 400);

        frame.setLocationRelativeTo(null);

        frame.setDefaultCloseOperation(
                JFrame.DISPOSE_ON_CLOSE
        );

        frame.setVisible(true);
    }
}