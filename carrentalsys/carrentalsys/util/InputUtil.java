package com.carrental.util;

import java.util.Scanner;

public class InputUtil {

    private InputUtil() {
    }

    public static int readInt(Scanner scanner,
                              String message) {

        while (true) {

            try {
                System.out.print(message);
                return Integer.parseInt(
                        scanner.nextLine());

            } catch (NumberFormatException e) {

                System.out.println(
                        "Invalid input! Please enter a number.");
            }
        }
    }

    public static double readDouble(Scanner scanner,
                                    String message) {

        while (true) {

            try {
                System.out.print(message);
                return Double.parseDouble(
                        scanner.nextLine());

            } catch (NumberFormatException e) {

                System.out.println(
                        "Invalid input! Please enter a valid number.");
            }
        }
    }
}