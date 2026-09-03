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
import com.carrental.model.Car;

public class CarPanel extends JPanel {

    private static final long serialVersionUID = 1L;

    private CarRentalController controller;

    private DefaultTableModel tableModel;

    private JTextField brandField;
    private JTextField modelField;
    private JTextField yearField;
    private JTextField priceField;

    private JFrame frame;

    public CarPanel(
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

        loadCars();
    }

    private void createUI() {

        String[] columns = {
                "ID",
                "Brand",
                "Model",
                "Year",
                "Price/Day",
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

        JPanel formPanel =
                new JPanel(
                        new GridLayout(
                                2,
                                5,
                                8,
                                8
                        )
                );

        brandField =
                new JTextField();

        modelField =
                new JTextField();

        yearField =
                new JTextField();

        priceField =
                new JTextField();

        JButton addButton =
                new JButton("ADD CAR");

        formPanel.add(
                new JLabel("Brand")
        );

        formPanel.add(
                new JLabel("Model")
        );

        formPanel.add(
                new JLabel("Year")
        );

        formPanel.add(
                new JLabel("Price / Day")
        );

        formPanel.add(
                new JLabel("")
        );

        formPanel.add(brandField);

        formPanel.add(modelField);

        formPanel.add(yearField);

        formPanel.add(priceField);

        formPanel.add(addButton);

        add(
                formPanel,
                BorderLayout.SOUTH
        );

        addButton.addActionListener(
                e -> addCar()
        );
    }

    private void addCar() {

        try {

            String brand =
                    brandField.getText().trim();

            String model =
                    modelField.getText().trim();

            int year =
                    Integer.parseInt(
                            yearField.getText()
                    );

            double price =
                    Double.parseDouble(
                            priceField.getText()
                    );

            Car car =
                    new Car(
                            brand,
                            model,
                            year,
                            price
                    );

            int id =
                    controller.addCar(car);

            JOptionPane.showMessageDialog(
                    this,
                    "Car added successfully!\n"
                            + "Car ID: " + id
            );

            clearFields();

            loadCars();

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Enter valid year and price.",
                    "Input Error",
                    JOptionPane.ERROR_MESSAGE
            );

        } catch (Exception e) {

            showError(e);
        }
    }

    public void loadCars() {

        try {

            tableModel.setRowCount(0);

            List<Car> cars =
                    controller.getCars();

            for (Car car : cars) {

                tableModel.addRow(
                        new Object[]{
                                car.getCarId(),
                                car.getBrand(),
                                car.getModel(),
                                car.getManufactureYear(),
                                car.getPricePerDay(),
                                car.isAvailable()
                                        ? "AVAILABLE"
                                        : "RENTED"
                        }
                );
            }

        } catch (SQLException e) {

            showError(e);
        }
    }

    private void clearFields() {

        brandField.setText("");

        modelField.setText("");

        yearField.setText("");

        priceField.setText("");
    }

    private void showError(Exception e) {

        JOptionPane.showMessageDialog(
                this,
                e.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE
        );
    }

    public void showWindow() {

        frame =
                new JFrame(
                        "Car Management"
                );

        frame.setContentPane(this);

        frame.setSize(900, 600);

        frame.setLocationRelativeTo(null);

        frame.setDefaultCloseOperation(
                JFrame.DISPOSE_ON_CLOSE
        );

        frame.setVisible(true);
    }
}