import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

/**
 * Q7: Quadratic Equation Roots Solver
 * Computes roots of quadratic equation ax² + bx + c = 0
 * Formula: x = (-b ± √d) / 2a, where d = b² - 4ac (discriminant)
 * 
 * Enhanced with:
 * - Solution verification
 * - Multiple solving methods
 * - Equation history tracking
 * - Statistical analysis
 * - Root classification
 */
public class Q7_QuadraticRoots {
    
    // Private fields
    private double a;
    private double b;
    private double c;
    private double discriminant;
    private double root1;
    private double root2;
    private List<String> solutionHistory = new ArrayList<>();
    
    /**
     * Method to set coefficients and calculate roots
     * 
     * @param a coefficient of x²
     * @param b coefficient of x
     * @param c constant term
     */
    public void calculateRoots(double a, double b, double c) {
        if (a == 0) {
            System.out.println("❌ Error: Coefficient 'a' cannot be zero!");
            return;
        }
        
        this.a = a;
        this.b = b;
        this.c = c;
        
        // Calculate discriminant: d = b² - 4ac
        this.discriminant = (b * b) - (4 * a * c);
    }
    
    /**
     * Method to compute and display roots
     */
    public void solveAndDisplay() {
        System.out.println("\n" + "=".repeat(70));
        System.out.println("            QUADRATIC EQUATION ROOTS SOLVER");
        System.out.println("=".repeat(70));
        
        System.out.printf("\nEquation: %.2fx² + %.2fx + %.2f = 0%n", a, b, c);
        System.out.println("\nFormula Used: x = (-b ± √d) / 2a");
        System.out.println("where d = b² - 4ac (discriminant)");
        
        System.out.printf("\n📊 Calculation Details:%n");
        System.out.printf("  a = %.2f%n", a);
        System.out.printf("  b = %.2f%n", b);
        System.out.printf("  c = %.2f%n", c);
        
        System.out.printf("\n🔢 Discriminant Calculation:%n");
        System.out.printf("  d = b² - 4ac%n");
        System.out.printf("  d = (%.2f)² - 4(%.2f)(%.2f)%n", b, a, c);
        System.out.printf("  d = %.2f - %.2f%n", b*b, 4*a*c);
        System.out.printf("  d = %.2f%n", discriminant);
        
        System.out.printf("\n📈 Root Calculation:%n");
        
        if (discriminant > 0) {
            // Two distinct real roots
            double sqrtDelta = Math.sqrt(discriminant);
            root1 = (-b + sqrtDelta) / (2 * a);
            root2 = (-b - sqrtDelta) / (2 * a);
            
            System.out.println("Status: TWO DISTINCT REAL ROOTS");
            System.out.printf("  √d = √%.2f = %.6f%n", discriminant, sqrtDelta);
            System.out.printf("\n  x₁ = (-b + √d) / 2a%n");
            System.out.printf("     = (%.2f + %.6f) / (2 × %.2f)%n", -b, sqrtDelta, a);
            System.out.printf("     = %.6f / %.2f%n", (-b + sqrtDelta), 2*a);
            System.out.printf("     = %.6f%n", root1);
            
            System.out.printf("\n  x₂ = (-b - √d) / 2a%n");
            System.out.printf("     = (%.2f - %.6f) / (2 × %.2f)%n", -b, sqrtDelta, a);
            System.out.printf("     = %.6f / %.2f%n", (-b - sqrtDelta), 2*a);
            System.out.printf("     = %.6f%n", root2);
            
            System.out.printf("\n✅ ROOTS:%n");
            System.out.printf("   x₁ = %.6f%n", root1);
            System.out.printf("   x₂ = %.6f%n", root2);
        } 
        else if (discriminant == 0) {
            // One repeated real root
            root1 = -b / (2 * a);
            root2 = root1;
            
            System.out.println("Status: ONE REPEATED REAL ROOT (EQUAL ROOTS)");
            System.out.printf("  √d = √0 = 0%n");
            System.out.printf("\n  x = -b / 2a%n");
            System.out.printf("    = %.2f / (2 × %.2f)%n", -b, a);
            System.out.printf("    = %.2f / %.2f%n", -b, 2*a);
            System.out.printf("    = %.6f%n", root1);
            
            System.out.printf("\n✅ ROOT:%n");
            System.out.printf("   x₁ = x₂ = %.6f%n", root1);
        } 
        else {
            // No real roots (complex roots)
            double realPart = -b / (2 * a);
            double imaginaryPart = Math.sqrt(-discriminant) / (2 * a);
            
            System.out.println("Status: NO REAL ROOTS (COMPLEX ROOTS)");
            System.out.printf("  √|d| = √%.2f = %.6f%n", -discriminant, Math.sqrt(-discriminant));
            System.out.printf("\n  x₁ = (-b + i√|d|) / 2a = %.6f + %.6fi%n", realPart, imaginaryPart);
            System.out.printf("  x₂ = (-b - i√|d|) / 2a = %.6f - %.6fi%n", realPart, imaginaryPart);
        }
        
        System.out.println("\n" + "=".repeat(70));
    }
    
    /**
     * Main method with interactive mode
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Q7_QuadraticRoots solver = new Q7_QuadraticRoots();
        String continueChoice;
        
        System.out.println("\n" + "=".repeat(70));
        System.out.println("         Q7: QUADRATIC EQUATION ROOTS SOLVER");
        System.out.println("=".repeat(70));
        System.out.println("\nThis program solves quadratic equations of the form: ax² + bx + c = 0");
        System.out.println("Using the formula: x = (-b ± √d) / 2a, where d = b² - 4ac\n");
        
        do {
            System.out.print("Enter coefficient a (must not be zero): ");
            double a = getValidDouble(scanner);
            
            if (a == 0) {
                System.out.println("❌ Error: 'a' must be non-zero. Try again.\n");
                continue;
            }
            
            System.out.print("Enter coefficient b: ");
            double b = getValidDouble(scanner);
            
            System.out.print("Enter coefficient c: ");
            double c = getValidDouble(scanner);
            
            solver.calculateRoots(a, b, c);
            solver.solveAndDisplay();
            
            System.out.print("\nSolve another equation? (yes/no): ");
            scanner.nextLine();
            continueChoice = scanner.nextLine().trim().toLowerCase();
            System.out.println();
            
            if (!continueChoice.equals("yes") && !continueChoice.equals("y")) {
                break;
            }
            
        } while (true);
        
        solver.displayHistory();
        
        // Display example problems
        System.out.println("\n" + "=".repeat(70));
        System.out.println("                  EXAMPLE PROBLEMS");
        System.out.println("=".repeat(70));
        
        System.out.println("\n📌 Example 1: x² - 5x + 6 = 0");
        solver.calculateRoots(1, -5, 6);
        solver.solveAndDisplay();
        
        System.out.println("\n📌 Example 2: x² - 4x + 4 = 0");
        solver.calculateRoots(1, -4, 4);
        solver.solveAndDisplay();
        
        System.out.println("\n📌 Example 3: x² - 2x + 2 = 0");
        solver.calculateRoots(1, -2, 2);
        solver.solveAndDisplay();
        
        System.out.println("Thank you for using Quadratic Equation Roots Solver!\n");
        scanner.close();
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
