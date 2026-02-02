import java.util.Scanner;

/**
 * A Combinatorial class to solve nCr (combinations) problems.
 * Uses the formula: nCr = n! / ((n - r)! * r!)
 * where n! = n × (n-1) × (n-2) × ... × 2 × 1
 * 
 * Class features:
 * - No explicit constructor (uses default constructor)
 * - Private fields for n and r values
 * - Static method to calculate factorial with caching
 * - Efficient method to calculate nCr value
 * - Input validation and error handling
 */
public class Combinatorial {
    
    // Private fields
    private int n;
    private int r;
    private long nCrValue;
    private long nFactorial;
    private long rFactorial;
    private long nMinusRFactorial;
    
    // Static cache for factorial calculations
    private static final java.util.Map<Integer, Long> factorialCache = new java.util.HashMap<>();
    
    /**
     * Method to validate input values
     * n must be >= r, and both must be non-negative
     * 
     * @param n total number of items
     * @param r number of items to choose
     * @return true if valid, false otherwise
     */
    public boolean isValid(int n, int r) {
        if (n < 0 || r < 0) {
            System.out.println("❌ Error: n and r must be non-negative integers!");
            return false;
        }
        if (n < r) {
            System.out.println("❌ Error: n must be greater than or equal to r!");
            return false;
        }
        return true;
    }
    
    /**
     * Method to set the values for n and r
     * 
     * @param n total number of items
     * @param r number of items to choose
     * @return true if values are set successfully
     */
    public boolean setValues(int n, int r) {
        if (isValid(n, r)) {
            this.n = n;
            this.r = r;
            return true;
        }
        return false;
    }
    
    /**
     * Static method to calculate factorial of a number with caching
     * Factorial: n! = n × (n-1) × (n-2) × ... × 2 × 1
     * Uses caching to avoid recalculating factorials
     * 
     * @param num the number to calculate factorial for
     * @return factorial value, or 1 if num is 0
     */
    private static long calculateFactorial(int num) {
        if (num == 0 || num == 1) {
            return 1;
        }
        
        // Check cache first
        if (factorialCache.containsKey(num)) {
            return factorialCache.get(num);
        }
        
        long factorial = 1;
        for (int i = 2; i <= num; i++) {
            factorial *= i;
        }
        
        // Store in cache
        factorialCache.put(num, factorial);
        return factorial;
    }
    
    /**
     * Method to calculate nCr value using the formula:
     * nCr = n! / ((n - r)! * r!)
     * Stores intermediate factorial values for efficient display
     * 
     * @return the nCr value
     */
    public long calculateNCr() {
        if (isValid(n, r)) {
            nFactorial = calculateFactorial(n);
            nMinusRFactorial = calculateFactorial(n - r);
            rFactorial = calculateFactorial(r);
            
            nCrValue = nFactorial / (nMinusRFactorial * rFactorial);
            return nCrValue;
        }
        return -1;
    }
    
    /**
     * Method to calculate nCr using optimized formula to avoid large factorials:
     * nCr = n × (n-1) × ... × (n-r+1) / r!
     * This method is more efficient for large values
     * 
     * @return the optimized nCr value
     */
    public long calculateNCrOptimized() {
        if (!isValid(n, r)) {
            return -1;
        }
        
        // Optimize: use nCr = nC(n-r) if r > n-r to minimize calculations
        int iterations = r > (n - r) ? (n - r) : r;
        
        nFactorial = calculateFactorial(n);
        nMinusRFactorial = calculateFactorial(n - r);
        rFactorial = calculateFactorial(r);
        
        long result = 1;
        for (int i = 0; i < iterations; i++) {
            result *= (n - i);
            result /= (i + 1);
        }
        
        nCrValue = result;
        return result;
    }
    
    /**
     * Method to display the calculation process with detailed breakdown
     */
    public void displayCalculation() {
        if (isValid(n, r)) {
            System.out.println("\n" + "=".repeat(50));
            System.out.println("           nCr (COMBINATIONS) CALCULATION");
            System.out.println("=".repeat(50));
            
            System.out.println("\n📋 Formula: nCr = n! / ((n - r)! × r!)");
            System.out.println("   This calculates the number of ways to choose r items from n items");
            System.out.println("   where order does NOT matter (combinations)");
            
            System.out.println("\n📊 Given Values:");
            System.out.println("   • n = " + n + " (total number of items)");
            System.out.println("   • r = " + r + " (number of items to choose)");
            
            System.out.println("\n🔢 Factorial Calculations:");
            System.out.println("   • " + n + "! = " + formatFactorial(n) + " = " + nFactorial);
            System.out.println("   • (" + n + " - " + r + ")! = " + (n - r) + "! = " + formatFactorial(n - r) + " = " + nMinusRFactorial);
            System.out.println("   • " + r + "! = " + formatFactorial(r) + " = " + rFactorial);
            
            System.out.println("\n📐 Calculation Process:");
            System.out.println("   " + n + "C" + r + " = " + nFactorial + " / (" + nMinusRFactorial + " × " + rFactorial + ")");
            System.out.println("   " + n + "C" + r + " = " + nFactorial + " / " + (nMinusRFactorial * rFactorial));
            
            System.out.println("\n✅ RESULT:");
            System.out.println("   " + n + "C" + r + " = " + nCrValue);
            System.out.println("\n   There are " + nCrValue + " ways to choose " + r + " items from " + n + " items.");
            System.out.println("=".repeat(50) + "\n");
        }
    }
    
    /**
     * Helper method to format factorial representation
     * Example: formatFactorial(5) returns "5 × 4 × 3 × 2 × 1"
     * 
     * @param num the number to format
     * @return formatted factorial string
     */
    private String formatFactorial(int num) {
        if (num == 0 || num == 1) {
            return "1";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = num; i >= 1; i--) {
            sb.append(i);
            if (i > 1) {
                sb.append(" × ");
            }
        }
        return sb.toString();
    }
    
    /**
     * Getter method for n value
     * 
     * @return the value of n
     */
    public int getN() {
        return n;
    }
    
    /**
     * Getter method for r value
     * 
     * @return the value of r
     */
    public int getR() {
        return r;
    }
    
    /**
     * Getter method for nCr value
     * 
     * @return the calculated nCr value
     */
    public long getNCrValue() {
        return nCrValue;
    }
    
    /**
     * Method to clear the factorial cache (useful for memory management)
     */
    public static void clearCache() {
        factorialCache.clear();
        System.out.println("Factorial cache cleared.");
    }
    
    /**
     * Method to display some example calculations
     */
    public static void showExamples() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("              EXAMPLE nCr CALCULATIONS");
        System.out.println("=".repeat(50));
        System.out.println("\n📌 Common Examples:");
        System.out.println("   • 5C2 = 10  (Choose 2 from 5 items)");
        System.out.println("   • 5C3 = 10  (Choose 3 from 5 items)");
        System.out.println("   • 10C3 = 120 (Choose 3 from 10 items)");
        System.out.println("   • 52C5 = 2,598,960 (Poker hands)");
        System.out.println("\n💡 Real-world Applications:");
        System.out.println("   • Lottery numbers, team selection, card games");
        System.out.println("   • Probability calculations, statistics");
        System.out.println("=".repeat(50) + "\n");
    }
    
    /**
     * Main method to demonstrate the Combinatorial class with enhanced UI
     */
    public static void main(String[] args) {
        Combinatorial comb = new Combinatorial();
        Scanner scanner = new Scanner(System.in);
        String continueChoice;
        
        // Display welcome message
        System.out.println("\n" + "=".repeat(50));
        System.out.println("       WELCOME TO nCr COMBINATIONS CALCULATOR");
        System.out.println("=".repeat(50));
        showExamples();
        
        int calculationCount = 0;
        
        do {
            System.out.print("Enter value of n (total items): ");
            int n = getValidInput(scanner);
            
            System.out.print("Enter value of r (items to choose): ");
            int r = getValidInput(scanner);
            
            if (comb.setValues(n, r)) {
                comb.calculateNCr();
                comb.displayCalculation();
                calculationCount++;
            }
            
            System.out.print("Do you want to calculate another nCr? (yes/no): ");
            continueChoice = scanner.next().trim().toLowerCase();
            
            if (!continueChoice.equals("yes") && !continueChoice.equals("y")) {
                break;
            }
            System.out.println();
            
        } while (true);
        
        // Display summary
        System.out.println("=".repeat(50));
        System.out.println("Thank you for using Combinatorial Calculator!");
        System.out.println("Total calculations performed: " + calculationCount);
        System.out.println("=".repeat(50) + "\n");
        
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
            scanner.nextLine(); // Clear invalid input
        }
        return scanner.nextInt();
    }
}
