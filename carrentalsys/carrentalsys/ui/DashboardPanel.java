package com.carrental.ui;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.carrental.controller.CarRentalController;

public class DashboardPanel extends JPanel {

    private static final long serialVersionUID = 1L;

    private CarRentalController controller;

    public DashboardPanel(
            CarRentalController controller) {

        this.controller = controller;

        setLayout(
                new BorderLayout(15, 15)
        );

        setBorder(
                BorderFactory.createEmptyBorder(
                        30, 30, 30, 30
                )
        );

        createUI();
    }

    private void createUI() {

        JLabel welcome =
                new JLabel(
                        "Welcome to Car Rental System",
                        SwingConstants.CENTER
                );

        add(
                welcome,
                BorderLayout.NORTH
        );

        JPanel buttonPanel =
                new JPanel(
                        new GridLayout(
                                3,
                                2,
                                20,
                                20
                        )
                );

        JButton carButton =
                new JButton("CAR MANAGEMENT");

        JButton customerButton =
                new JButton("CUSTOMER MANAGEMENT");

        JButton rentalButton =
                new JButton("RENT A CAR");

        JButton returnButton =
                new JButton("RETURN CAR");

        JButton historyButton =
                new JButton("RENTAL HISTORY");

        JButton exitButton =
                new JButton("EXIT");

        buttonPanel.add(carButton);

        buttonPanel.add(customerButton);

        buttonPanel.add(rentalButton);

        buttonPanel.add(returnButton);

        buttonPanel.add(historyButton);

        buttonPanel.add(exitButton);

        add(
                buttonPanel,
                BorderLayout.CENTER
        );

        carButton.addActionListener(e -> {

            new CarPanel(
                    controller
            ).showWindow();

        });

        customerButton.addActionListener(e -> {

            new CustomerPanel(
                    controller
            ).showWindow();

        });

        rentalButton.addActionListener(e -> {

            new RentalPanel(
                    controller
            ).showWindow();

        });

        returnButton.addActionListener(e -> {

            new ReturnCarPanel(
                    controller
            ).showWindow();

        });

        historyButton.addActionListener(e -> {

            new RentalHistoryPanel(
                    controller
            ).showWindow();

        });

        exitButton.addActionListener(e -> {

            System.exit(0);

        });
    }
}