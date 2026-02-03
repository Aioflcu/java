import java.util.Scanner;
import java.util.InputMismatchException;

/**
 * A simple Calculator class that performs basic arithmetic operations.
 * Supports addition (+), subtraction (-), multiplication (*), division (/), and modulo (%).
 * Users can perform multiple calculations in succession.
 */
public class Calculator {
    private double number1;
    private double number2;
    private char operator;
    private double result;
    private Scanner scanner;
    private static final String VALID_OPERATORS = "+-*/%";

    /**
     * Constructor to initialize the calculator
     */
    public Calculator() {
        this.number1 = 0;
        this.number2 = 0;
        this.operator = ' ';
        this.result = 0;
        this.scanner = new Scanner(System.in);
    }

    /**
     * Method to get input from user with validation
     */
    public void getInput() {
        try {
            System.out.print("Enter the first number: ");
            this.number1 = getValidNumber();

            System.out.print("Enter the operator (+, -, *, /, %): ");
            this.operator = getValidOperator();

            System.out.print("Enter the second number: ");
            this.number2 = getValidNumber();

        } catch (InputMismatchException e) {
            System.out.println("Error: Invalid input! Please enter valid numbers.");
            scanner.nextLine(); // Clear invalid input
        }
    }

    /**
     * Helper method to validate and get a number from user
     * @return valid double number
     */
    private double getValidNumber() {
        while (!scanner.hasNextDouble()) {
            System.out.println("Invalid input! Please enter a valid number: ");
            scanner.nextLine(); // Clear invalid input
        }
        return scanner.nextDouble();
    }

    /**
     * Helper method to validate and get an operator from user
     * @return valid arithmetic operator
     */
    private char getValidOperator() {
        while (true) {
            String input = scanner.next().trim();
            if (input.length() == 1 && VALID_OPERATORS.contains(input)) {
                return input.charAt(0);
            }
            System.out.println("Invalid operator! Please enter one of: +, -, *, /, % ");
        }
    }

    /**
     * Method to perform calculation using switch statement
     */
    public void calculate() {
        switch (operator) {
            case '+':
                result = number1 + number2;
                displayResult("+");
                break;

            case '-':
                result = number1 - number2;
                displayResult("-");
                break;

            case '*':
                result = number1 * number2;
                displayResult("*");
                break;

            case '/':
                if (number2 != 0) {
                    result = number1 / number2;
                    displayResult("/");
                } else {
                    System.out.println("\n❌ Error: Division by zero is not allowed!");
                }
                break;

            case '%':
                if (number2 != 0) {
                    result = number1 % number2;
                    displayResult("%");
                } else {
                    System.out.println("\n❌ Error: Modulo by zero is not allowed!");
                }
                break;

            default:
                System.out.println("Invalid operator!");
        }
    }

    /**
     * Helper method to display calculation result
     * @param op the operator symbol
     */
    private void displayResult(String op) {
        System.out.println("\n✓ Result: " + number1 + " " + op + " " + number2 + " = " + result);
    }

    /**
     * Getter method for the result
     * @return the result of the last calculation
     */
    public double getResult() {
        return result;
    }

    /**
     * Method to close the scanner resource
     */
    public void close() {
        if (scanner != null) {
            scanner.close();
        }
    }

    /**
     * Main method with loop for multiple calculations
     */
    public static void main(String[] args) {
        Calculator calc = new Calculator();
        String continueChoice;

        System.out.println("========== SIMPLE CALCULATOR ==========\n");

        do {
            calc.getInput();
            calc.calculate();

            System.out.print("\nDo you want to perform another calculation? (yes/no): ");
            continueChoice = calc.scanner.next().trim().toLowerCase();

            if (!continueChoice.equals("yes") && !continueChoice.equals("y")) {
                break;
            }
            System.out.println();
        } while (true);

        System.out.println("\n========== Thank you for using Calculator ==========");
        calc.close();
    }
}
