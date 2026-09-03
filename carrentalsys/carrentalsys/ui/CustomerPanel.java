package com.carrental.ui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.sql.SQLException;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.carrental.controller.CarRentalController;
import com.carrental.model.Customer;

public class CustomerPanel extends JPanel {

    private static final long serialVersionUID = 1L;

    private CarRentalController controller;

    private DefaultTableModel tableModel;

    private JTextField nameField;
    private JTextField phoneField;
    private JTextField emailField;

    private JFrame frame;

    public CustomerPanel(
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

        loadCustomers();
    }

    private void createUI() {

        String[] columns = {
                "ID",
                "Name",
                "Phone",
                "Email"
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

        JPanel formPanel =
                new JPanel(
                        new GridLayout(
                                2,
                                4,
                                8,
                                8
                        )
                );

        nameField =
                new JTextField();

        phoneField =
                new JTextField();

        emailField =
                new JTextField();

        JButton addButton =
                new JButton(
                        "ADD CUSTOMER"
                );

        formPanel.add(
                new JLabel("Name")
        );

        formPanel.add(
                new JLabel("Phone")
        );

        formPanel.add(
                new JLabel("Email")
        );

        formPanel.add(
                new JLabel("")
        );

        formPanel.add(nameField);

        formPanel.add(phoneField);

        formPanel.add(emailField);

        formPanel.add(addButton);

        add(
                formPanel,
                BorderLayout.SOUTH
        );

        addButton.addActionListener(
                e -> addCustomer()
        );
    }

    private void addCustomer() {

        try {

            String name =
                    nameField.getText().trim();

            String phone =
                    phoneField.getText().trim();

            String email =
                    emailField.getText().trim();

            if (name.isEmpty()
                    || phone.isEmpty()) {

                JOptionPane.showMessageDialog(
                        this,
                        "Name and phone are required."
                );

                return;
            }

            Customer customer =
                    new Customer(
                            name,
                            phone,
                            email
                    );

            int id =
                    controller.addCustomer(
                            customer
                    );

            JOptionPane.showMessageDialog(
                    this,
                    "Customer added successfully!\n"
                            + "Customer ID: " + id
            );

            nameField.setText("");

            phoneField.setText("");

            emailField.setText("");

            loadCustomers();

        } catch (Exception e) {

            showError(e);
        }
    }

    public void loadCustomers() {

        try {

            tableModel.setRowCount(0);

            List<Customer> customers =
                    controller.getCustomers();

            for (Customer customer :
                    customers) {

                tableModel.addRow(
                        new Object[]{
                                customer.getCustomerId(),
                                customer.getName(),
                                customer.getPhone(),
                                customer.getEmail()
                        }
                );
            }

        } catch (SQLException e) {

            showError(e);
        }
    }

    private void showError(Exception e) {

        JOptionPane.showMessageDialog(
                this,
                e.getMessage(),
                "Database Error",
                JOptionPane.ERROR_MESSAGE
        );
    }

    public void showWindow() {

        frame =
                new JFrame(
                        "Customer Management"
                );

        frame.setContentPane(this);

        frame.setSize(800, 550);

        frame.setLocationRelativeTo(null);

        frame.setDefaultCloseOperation(
                JFrame.DISPOSE_ON_CLOSE
        );

        frame.setVisible(true);
    }
}