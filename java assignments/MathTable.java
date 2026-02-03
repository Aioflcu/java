import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * A MathTable class to generate a mathematical table showing N and its various calculations.
 * The table displays: N, N², √N, N³, and ∛N for a dynamic range of numbers.
 * 
 * Class features:
 * - No explicit constructor (uses default constructor)
 * - Private fields for storing calculated values
 * - Object-based approach for table generation
 * - Formatted output with proper column alignment
 * - Support for dynamic range (not just 1-5)
 * - Data caching and statistical analysis
 * - Input validation and error handling
 */
public class MathTable {
    
    // Private fields
    private int n;
    private long nSquared;
    private double nSquareRoot;
    private long nCube;
    private double nCubeRoot;
    private List<TableRow> tableRows = new ArrayList<>();
    
    /**
     * Method to set the value of n and calculate all related values
     * 
     * @param number the value of n to calculate for
     */
    public void setNumber(int number) {
        this.n = number;
        calculateValues();
    }
    
    /**
     * Private method to calculate all mathematical values for n
     */
    private void calculateValues() {
        // Calculate n squared
        this.nSquared = (long) n * n;
        
        // Calculate square root of n
        this.nSquareRoot = Math.sqrt(n);
        
        // Calculate n cube
        this.nCube = (long) n * n * n;
        
        // Calculate cube root of n
        this.nCubeRoot = Math.cbrt(n);
    }
    
    /**
     * Getter method for n
     * 
     * @return the value of n
     */
    public int getN() {
        return n;
    }
    
    /**
     * Getter method for n squared
     * 
     * @return n²
     */
    public long getNSquared() {
        return nSquared;
    }
    
    /**
     * Getter method for square root of n
     * 
     * @return √n
     */
    public double getNSquareRoot() {
        return nSquareRoot;
    }
    
    /**
     * Getter method for n cube
     * 
     * @return n³
     */
    public long getNCube() {
        return nCube;
    }
    
    /**
     * Getter method for cube root of n
     * 
     * @return ∛n
     */
    public double getNCubeRoot() {
        return nCubeRoot;
    }
    
    /**
     * Inner class to store a row of table data
     */
    private static class TableRow {
        int n;
        long nSquared;
        double nSquareRoot;
        long nCube;
        double nCubeRoot;
        
        TableRow(int n, long nSquared, double nSquareRoot, long nCube, double nCubeRoot) {
            this.n = n;
            this.nSquared = nSquared;
            this.nSquareRoot = nSquareRoot;
            this.nCube = nCube;
            this.nCubeRoot = nCubeRoot;
        }
    }
    
    /**
     * Method to display the table header
     */
    private static void displayHeader() {
        System.out.println("\n" + "=".repeat(70));
        System.out.println("              MATHEMATICAL TABLE: N, N², √N, N³, ∛N");
        System.out.println("=".repeat(70));
        System.out.println();
        
        // Header row with proper formatting
        System.out.printf("%-8s%-12s%-15s%-12s%-15s%n", "N", "Nsq", "NSqRoot", "NCube", "NCubeRoot");
        System.out.println("-".repeat(70));
    }
    
    /**
     * Method to display a single row of the table
     */
    public void displayRow() {
        System.out.printf("%-8d%-12d%-15.2f%-12d%-15.2f%n", 
            n, nSquared, nSquareRoot, nCube, nCubeRoot);
        
        // Cache the row data
        tableRows.add(new TableRow(n, nSquared, nSquareRoot, nCube, nCubeRoot));
    }
    
    /**
     * Method to calculate and display statistical information about the table
     */
    private void displayStatistics() {
        if (tableRows.isEmpty()) {
            System.out.println("No data to analyze.");
            return;
        }
        
        double avgSquareRoot = tableRows.stream()
            .mapToDouble(row -> row.nSquareRoot)
            .average()
            .orElse(0.0);
        
        double avgCubeRoot = tableRows.stream()
            .mapToDouble(row -> row.nCubeRoot)
            .average()
            .orElse(0.0);
        
        long maxSquare = tableRows.stream()
            .mapToLong(row -> row.nSquared)
            .max()
            .orElse(0);
        
        long maxCube = tableRows.stream()
            .mapToLong(row -> row.nCube)
            .max()
            .orElse(0);
        
        System.out.println("\n" + "=".repeat(70));
        System.out.println("                         STATISTICAL ANALYSIS");
        System.out.println("=".repeat(70));
        System.out.printf("  • Average Square Root: %.4f%n", avgSquareRoot);
        System.out.printf("  • Average Cube Root: %.4f%n", avgCubeRoot);
        System.out.printf("  • Maximum Square Value: %d%n", maxSquare);
        System.out.printf("  • Maximum Cube Value: %d%n", maxCube);
        System.out.printf("  • Total Rows Generated: %d%n", tableRows.size());
        System.out.println("=".repeat(70));
    }
    
    /**
     * Method to clear cached data
     */
    public void clearData() {
        tableRows.clear();
    }
    
    /**
     * Method to display the footer
     */
    private static void displayFooter() {
        System.out.println("-".repeat(70));
        System.out.println("=".repeat(70) + "\n");
    }
    
    /**
     * Helper method to validate input range
     * 
     * @param start starting number
     * @param end ending number
     * @return true if valid, false otherwise
     */
    private static boolean isValidRange(int start, int end) {
        if (start <= 0 || end <= 0) {
            System.out.println("❌ Error: Both start and end must be positive integers!");
            return false;
        }
        if (start > end) {
            System.out.println("❌ Error: Start must be less than or equal to end!");
            return false;
        }
        if (end - start > 100) {
            System.out.println("❌ Error: Range cannot exceed 100 numbers!");
            return false;
        }
        return true;
    }
    
    /**
     * Method to generate table for a custom range
     * 
     * @param start starting number
     * @param end ending number
     */
    public void generateTable(int start, int end) {
        if (!isValidRange(start, end)) {
            return;
        }
        
        displayHeader();
        
        for (int i = start; i <= end; i++) {
            setNumber(i);
            displayRow();
        }
        
        displayFooter();
    }
    
    /**
     * Main method to demonstrate the MathTable class with interactive mode
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        MathTable table = new MathTable();
        String continueChoice;
        
        System.out.println("\n" + "=".repeat(70));
        System.out.println("           WELCOME TO MATHEMATICAL TABLE GENERATOR");
        System.out.println("=".repeat(70));
        System.out.println("\nThis program generates mathematical tables showing:");
        System.out.println("  • N: Natural number");
        System.out.println("  • Nsq: Square of N (N²)");
        System.out.println("  • NSqRoot: Square root of N (√N)");
        System.out.println("  • NCube: Cube of N (N³)");
        System.out.println("  • NCubeRoot: Cube root of N (∛N)\n");
        
        do {
            System.out.print("Enter starting number: ");
            int start = getValidInput(scanner);
            
            System.out.print("Enter ending number: ");
            int end = getValidInput(scanner);
            
            table.clearData();
            table.generateTable(start, end);
            table.displayStatistics();
            
            System.out.print("\n✓ Table generated successfully!\n");
            System.out.print("Generate another table? (yes/no): ");
            continueChoice = scanner.next().trim().toLowerCase();
            System.out.println();
            
            if (!continueChoice.equals("yes") && !continueChoice.equals("y")) {
                break;
            }
            
        } while (true);
        
        System.out.println("=".repeat(70));
        System.out.println("Thank you for using Mathematical Table Generator!");
        System.out.println("=".repeat(70) + "\n");
        
        scanner.close();
    }
    
    /**
     * Helper method to get valid integer input from user
     * 
     * @param scanner Scanner object for user input
     * @return valid integer value
     */
    private static int getValidInput(Scanner scanner) {
        while (!scanner.hasNextInt()) {
            System.out.println("❌ Invalid input! Please enter a valid integer: ");
            scanner.nextLine();
        }
        return scanner.nextInt();
    }
}
