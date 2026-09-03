package com.carrental.ui;

import java.awt.BorderLayout;
import java.sql.SQLException;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.carrental.controller.CarRentalController;
import com.carrental.model.Rental;

public class RentalHistoryPanel extends JPanel {

    private static final long serialVersionUID = 1L;

    private CarRentalController controller;

    private DefaultTableModel tableModel;

    private JFrame frame;

    public RentalHistoryPanel(
            CarRentalController controller) {

        this.controller = controller;

        setLayout(
                new BorderLayout(10, 10)
        );

        setBorder(
                BorderFactory.createEmptyBorder(
                        15, 15, 15, 15
                )
        );

        createUI();

        loadRentals();
    }

    private void createUI() {

        String[] columns = {
                "Rental ID",
                "Car ID",
                "Customer ID",
                "Rental Date",
                "Return Date",
                "Amount",
                "Status"
        };

        tableModel =
                new DefaultTableModel(
                        columns,
                        0
                ) {

                    private static final long serialVersionUID = 1L;

                    @Override
                    public boolean isCellEditable(
                            int row,
                            int column) {

                        return false;
                    }
                };

        JTable table =
                new JTable(tableModel);

        add(
                new JScrollPane(table),
                BorderLayout.CENTER
        );

        JButton refreshButton =
                new JButton(
                        "REFRESH"
                );

        JPanel buttonPanel =
                new JPanel();

        buttonPanel.add(
                refreshButton
        );

        add(
                buttonPanel,
                BorderLayout.SOUTH
        );

        refreshButton.addActionListener(
                e -> loadRentals()
        );
    }

    public void loadRentals() {

        try {

            tableModel.setRowCount(0);

            List<Rental> rentals =
                    controller.getRentals();

            for (Rental rental :
                    rentals) {

                tableModel.addRow(
                        new Object[]{
                                rental.getRentalId(),
                                rental.getCarId(),
                                rental.getCustomerId(),
                                rental.getRentalDate(),
                                rental.getReturnDate(),
                                rental.getTotalAmount(),
                                rental.getStatus()
                        }
                );
            }

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(
                    this,
                    e.getMessage(),
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    public void showWindow() {

        frame =
                new JFrame(
                        "Rental History"
                );

        frame.setContentPane(this);

        frame.setSize(950, 550);

        frame.setLocationRelativeTo(null);

        frame.setDefaultCloseOperation(
                JFrame.DISPOSE_ON_CLOSE
        );

        frame.setVisible(true);
    }
}