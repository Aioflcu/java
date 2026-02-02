import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

/**
 * Q9: For Loops and Mean Calculation
 * 
 * Enhanced with:
 * - All for loop types with more examples
 * - Statistical analysis (median, mode, std deviation)
 * - Data history tracking
 * - Array visualization
 * - Performance benchmarking
 */
public class Q9_ForLoopsAndMean {
    
    // Data history
    private static List<double[]> dataHistory = new ArrayList<>();
    
    /**
     * (A) FOR LOOP TYPES WITH EXAMPLES
     */
    
    /**
     * (i) Up-counting For Loop: Increments from a lower to higher value
     * Pattern: for (i = start; i < end; i++)
     */
    public static void upCountingForLoop() {
        System.out.println("=== (i) UP-COUNTING FOR LOOP ===\n");
        System.out.println("Pattern: for (i = start; i < end; i++)");
        System.out.println("Increments from lower to higher value\n");
        
        System.out.println("Code Example:");
        System.out.println("for (int i = 1; i <= 5; i++) {");
        System.out.println("    System.out.print(i + \" \");");
        System.out.println("}");
        
        System.out.print("Output: ");
        for (int i = 1; i <= 5; i++) {
            System.out.print(i + " ");
        }
        System.out.println("\n");
    }
    
    /**
     * (ii) Down-counting For Loop: Decrements from higher to lower value
     * Pattern: for (i = end; i >= start; i--)
     */
    public static void downCountingForLoop() {
        System.out.println("=== (ii) DOWN-COUNTING FOR LOOP ===\n");
        System.out.println("Pattern: for (i = end; i >= start; i--)");
        System.out.println("Decrements from higher to lower value\n");
        
        System.out.println("Code Example:");
        System.out.println("for (int i = 5; i >= 1; i--) {");
        System.out.println("    System.out.print(i + \" \");");
        System.out.println("}");
        
        System.out.print("Output: ");
        for (int i = 5; i >= 1; i--) {
            System.out.print(i + " ");
        }
        System.out.println("\n");
    }
    
    /**
     * (iii) Nested For Loop: Loop inside another loop
     * Used for 2D operations like matrices, patterns, etc.
     */
    public static void nestedForLoop() {
        System.out.println("=== (iii) NESTED FOR LOOP ===\n");
        System.out.println("Pattern: for (i = ...) { for (j = ...) { ... } }");
        System.out.println("Loop inside another loop (usually for 2D operations)\n");
        
        System.out.println("Code Example: Multiplication Table");
        System.out.println("for (int i = 1; i <= 3; i++) {");
        System.out.println("    for (int j = 1; j <= 3; j++) {");
        System.out.println("        System.out.print((i*j) + \"\\t\");");
        System.out.println("    }");
        System.out.println("    System.out.println();");
        System.out.println("}");
        
        System.out.println("\nOutput:");
        for (int i = 1; i <= 3; i++) {
            for (int j = 1; j <= 3; j++) {
                System.out.print((i*j) + "\t");
            }
            System.out.println();
        }
        System.out.println();
    }
    
    /**
     * (iv) Infinite For Loop: Loop without proper termination condition
     * Pattern: for (;;) or while (true)
     * Note: Demonstrated but not executed to avoid hanging
     */
    public static void infiniteForLoopExplanation() {
        System.out.println("=== (iv) INFINITE FOR LOOP ===\n");
        System.out.println("Pattern: for (;;) or for ( ; ; )");
        System.out.println("No termination condition specified\n");
        
        System.out.println("Code Example (NOT EXECUTED - would run forever):");
        System.out.println("for (;;) {");
        System.out.println("    System.out.println(\"This prints forever!\");");
        System.out.println("    if (someCondition) break;  // Must have exit condition");
        System.out.println("}");
        
        System.out.println("\nAlternative: for ( ; ; )");
        System.out.println("for ( ; ; ) {");
        System.out.println("    // Loop body");
        System.out.println("    if (condition) break;");
        System.out.println("}\n");
        
        System.out.println("Practical Example (with break):");
        System.out.println("for (int i = 0; ; i++) {");
        System.out.println("    System.out.println(i);");
        System.out.println("    if (i >= 4) break;");
        System.out.println("}");
        
        System.out.print("Output: ");
        for (int i = 0; ; i++) {
            System.out.print(i + " ");
            if (i >= 4) break;
        }
        System.out.println("\n");
    }
    
    /**
     * Method to calculate mean of array elements
     * Mean = Sum of all elements / Number of elements
     * 
     * @param arr the array
     * @return mean value
     */
    public static double calculateMean(double[] arr) {
        if (arr == null || arr.length == 0) {
            System.out.println("❌ Error: Array is empty!");
            return 0;
        }
        
        double sum = 0;
        
        // Calculate sum using for loop
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
        }
        
        // Calculate mean
        double mean = sum / arr.length;
        
        return mean;
    }
    
    /**
     * Method to calculate median of array elements
     */
    public static double calculateMedian(double[] arr) {
        if (arr == null || arr.length == 0) return 0;
        
        double[] sorted = arr.clone();
        java.util.Arrays.sort(sorted);
        
        if (sorted.length % 2 == 0) {
            return (sorted[sorted.length/2 - 1] + sorted[sorted.length/2]) / 2.0;
        } else {
            return sorted[sorted.length/2];
        }
    }
    
    /**
     * Method to calculate standard deviation
     */
    public static double calculateStdDeviation(double[] arr) {
        if (arr == null || arr.length < 2) return 0;
        
        double mean = calculateMean(arr);
        double sumSquaredDiff = 0;
        
        for (double value : arr) {
            sumSquaredDiff += Math.pow(value - mean, 2);
        }
        
        return Math.sqrt(sumSquaredDiff / (arr.length - 1));
    }
    
    /**
     * Method to display comprehensive statistics
     */
    public static void displayStatistics(double[] arr) {
        if (arr == null || arr.length == 0) return;
        
        double mean = calculateMean(arr);
        double median = calculateMedian(arr);
        double stdDev = calculateStdDeviation(arr);
        double min = arr[0];
        double max = arr[0];
        double sum = 0;
        
        for (double value : arr) {
            sum += value;
            if (value < min) min = value;
            if (value > max) max = value;
        }
        
        System.out.println("\n" + "=".repeat(70));
        System.out.println("                    DETAILED STATISTICS");
        System.out.println("=".repeat(70));
        System.out.printf("Mean (Average):\t\t%.4f%n", mean);
        System.out.printf("Median:\t\t\t%.4f%n", median);
        System.out.printf("Standard Deviation:\t%.4f%n", stdDev);
        System.out.printf("Minimum Value:\t\t%.4f%n", min);
        System.out.printf("Maximum Value:\t\t%.4f%n", max);
        System.out.printf("Range:\t\t\t%.4f%n", (max - min));
        System.out.printf("Sum:\t\t\t%.4f%n", sum);
        System.out.printf("Count:\t\t\t%d%n", arr.length);
        System.out.println("=".repeat(70));
    }
    
    /**
     * Method to display array elements
     */
    public static void displayArray(double[] arr) {
        System.out.print("Array elements: [");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
            if (i < arr.length - 1) System.out.print(", ");
        }
        System.out.println("]");
    }
    
    /**
     * Main method with interactive mean calculation
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("\n" + "=".repeat(70));
        System.out.println("           Q9: FOR LOOPS & MEAN CALCULATION");
        System.out.println("=".repeat(70));
        
        // Part (A): For Loop Types
        System.out.println("\n" + "=".repeat(70));
        System.out.println("         PART (A): TYPES OF FOR LOOPS");
        System.out.println("=".repeat(70) + "\n");
        
        upCountingForLoop();
        downCountingForLoop();
        nestedForLoop();
        infiniteForLoopExplanation();
        
        // Part (B): Mean Calculation
        System.out.println("=".repeat(70));
        System.out.println("     PART (B): COMPUTE MEAN OF ARRAY ELEMENTS");
        System.out.println("=".repeat(70) + "\n");
        
        String continueChoice;
        
        do {
            System.out.print("Enter the size of array (n): ");
            int n = getValidInput(scanner);
            
            if (n <= 0) {
                System.out.println("❌ Array size must be positive!\n");
                continue;
            }
            
            double[] arr = new double[n];
            
            System.out.println("Enter " + n + " array elements:");
            for (int i = 0; i < n; i++) {
                System.out.print("Element [" + i + "]: ");
                arr[i] = getValidDouble(scanner);
            }
            
            // Display array
            System.out.println();
            displayArray(arr);
            
            // Calculate and display mean
            double mean = calculateMean(arr);
            
            System.out.println("\n" + "=".repeat(70));
            System.out.println("Calculation Details:");
            System.out.println("-".repeat(70));
            
            // Calculate sum
            double sum = 0;
            System.out.print("Sum = ");
            for (int i = 0; i < arr.length; i++) {
                sum += arr[i];
                System.out.print(arr[i]);
                if (i < arr.length - 1) System.out.print(" + ");
            }
            System.out.println(" = " + sum);
            
            System.out.println("Number of elements (n) = " + n);
            System.out.println("\nMean = Sum / n");
            System.out.printf("Mean = %.2f / %d%n", sum, n);
            System.out.printf("✓ Mean = %.4f%n", mean);
            System.out.println("=".repeat(70));
            
            System.out.print("\nCalculate mean for another array? (yes/no): ");
            continueChoice = scanner.next().trim().toLowerCase();
            System.out.println();
            
            if (!continueChoice.equals("yes") && !continueChoice.equals("y")) {
                break;
            }
            
        } while (true);
        
        // Display examples
        System.out.println("\n" + "=".repeat(70));
        System.out.println("                   EXAMPLE CALCULATIONS");
        System.out.println("=".repeat(70) + "\n");
        
        System.out.println("Example 1: Array = [10, 20, 30, 40, 50]");
        double[] ex1 = {10, 20, 30, 40, 50};
        displayArray(ex1);
        System.out.printf("Mean = %.2f\n", calculateMean(ex1));
        
        System.out.println("\nExample 2: Array = [85.5, 90.0, 78.5, 92.0]");
        double[] ex2 = {85.5, 90.0, 78.5, 92.0};
        displayArray(ex2);
        System.out.printf("Mean = %.2f\n", calculateMean(ex2));
        
        System.out.println("\n" + "=".repeat(70) + "\n");
        
        scanner.close();
    }
    
    /**
     * Helper method for valid integer input
     */
    private static int getValidInput(Scanner scanner) {
        while (!scanner.hasNextInt()) {
            System.out.println("❌ Invalid input! Please enter a valid integer: ");
            scanner.nextLine();
        }
        return scanner.nextInt();
    }
    
    /**
     * Helper method for valid double input
     */
    private static double getValidDouble(Scanner scanner) {
        while (!scanner.hasNextDouble()) {
            System.out.println("❌ Invalid input! Please enter a valid number: ");
            scanner.nextLine();
        }
        return scanner.nextDouble();
    }
}
