package com.carrental.ui;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import com.carrental.controller.CarRentalController;

public class DashboardFrame extends JFrame {

    private static final long serialVersionUID = 1L;

    private CarRentalController controller;

    public DashboardFrame(
            CarRentalController controller) {

        this.controller = controller;

        setTitle(
                "Car Rental Management System"
        );

        setSize(1100, 700);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(
                JFrame.EXIT_ON_CLOSE
        );

        createUI();
    }

    private void createUI() {

        JLabel header =
                new JLabel(
                        "CAR RENTAL MANAGEMENT SYSTEM",
                        SwingConstants.CENTER
                );

        header.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        26
                )
        );

        add(
                header,
                BorderLayout.NORTH
        );

        DashboardPanel dashboardPanel =
                new DashboardPanel(
                        controller
                );

        add(
                dashboardPanel,
                BorderLayout.CENTER
        );
    }
}