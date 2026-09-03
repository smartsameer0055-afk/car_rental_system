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

public class ReturnCarPanel extends JPanel {

    private static final long serialVersionUID = 1L;

    private CarRentalController controller;

    private JTextField rentalIdField;

    private JFrame frame;

    public ReturnCarPanel(
            CarRentalController controller) {

        this.controller = controller;

        setLayout(
                new BorderLayout()
        );

        setBorder(
                BorderFactory.createEmptyBorder(
                        40, 50, 40, 50
                )
        );

        createUI();
    }

    private void createUI() {

        JLabel title =
                new JLabel(
                        "RETURN RENTED CAR"
                );

        JPanel form =
                new JPanel(
                        new GridLayout(
                                2,
                                2,
                                10,
                                10
                        )
                );

        rentalIdField =
                new JTextField();

        JButton returnButton =
                new JButton(
                        "RETURN CAR"
                );

        form.add(
                new JLabel("Rental ID:")
        );

        form.add(
                rentalIdField
        );

        form.add(
                new JLabel("")
        );

        form.add(
                returnButton
        );

        add(
                title,
                BorderLayout.NORTH
        );

        add(
                form,
                BorderLayout.CENTER
        );

        returnButton.addActionListener(
                e -> returnCar()
        );
    }

    private void returnCar() {

        try {

            int rentalId =
                    Integer.parseInt(
                            rentalIdField.getText()
                    );

            double amount =
                    controller.returnCar(
                            rentalId
                    );

            JOptionPane.showMessageDialog(
                    this,
                    String.format(
                            "Car returned successfully!\n\n"
                                    + "Total Amount: ₹%.2f",
                            amount
                    )
            );

            rentalIdField.setText("");

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Enter a valid Rental ID.",
                    "Input Error",
                    JOptionPane.ERROR_MESSAGE
            );

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    e.getMessage(),
                    "Return Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    public void showWindow() {

        frame =
                new JFrame(
                        "Return Car"
                );

        frame.setContentPane(this);

        frame.setSize(500, 350);

        frame.setLocationRelativeTo(null);

        frame.setDefaultCloseOperation(
                JFrame.DISPOSE_ON_CLOSE
        );

        frame.setVisible(true);
    }
}