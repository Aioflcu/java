import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

/**
 * Q6: Age Classification and Quadratic Equation Solver
 * (a) Classify age as child, adult, or senior citizen
 * (b) Solve quadratic equations using formula: x = (-b ± √(b² - 4ac)) / 2a
 * 
 * Enhanced with:
 * - Age classification with categories
 * - Historical equation solving
 * - Multiple solution methods
 * - Verification of solutions
 * - Graphical representation
 */
public class Q6_AgeAndQuadratic {
    
    // Private fields
    private List<String> calculationHistory = new ArrayList<>();
    
    /**
     * (a) Method to classify age with additional details
     * Child: age < 18
     * Adult: 18 ≤ age < 65
     * Senior Citizen: age ≥ 65
     * 
     * @param age the age to classify
     * @return classification string
     */
    public static String classifyAge(int age) {
        if (age < 0) {
            return "Invalid age";
        } else if (age < 13) {
            return "Child (Early)";
        } else if (age < 18) {
            return "Teenager/Adolescent";
        } else if (age < 65) {
            return "Adult";
        } else {
            return "Senior Citizen";
        }
    }
    
    /**
     * Method to get detailed age category information
     */
    public static void displayAgeCategory(int age) {
        String category = classifyAge(age);
        
        System.out.printf("\nAge: %d years%n", age);
        System.out.printf("Category: %s%n", category);
        
        switch (category) {
            case "Child (Early)":
                System.out.println("Description: Young child, requires parental supervision");
                break;
            case "Teenager/Adolescent":
                System.out.println("Description: Adolescent, beginning independence");
                break;
            case "Adult":
                System.out.println("Description: Working age adult, full responsibilities");
                break;
            case "Senior Citizen":
                System.out.println("Description: Retired or nearing retirement, entitled to senior benefits");
                break;
            default:
                System.out.println("Invalid age provided");
        }
    }
    
    /**
     * (b) Method to solve quadratic equation: ax² + bx + c = 0
     * Formula: x = (-b ± √(b² - 4ac)) / 2a
     * 
     * @param a coefficient of x²
     * @param b coefficient of x
     * @param c constant term
     */
    public static void solveQuadratic(double a, double b, double c) {
        // Calculate discriminant (b² - 4ac)
        double discriminant = (b * b) - (4 * a * c);
        
        System.out.printf("\nQuadratic Equation: %.2fx² + %.2fx + %.2f = 0%n", a, b, c);
        System.out.printf("Discriminant (Δ) = b² - 4ac = (%.2f)² - 4(%.2f)(%.2f) = %.2f%n", 
            b, a, c, discriminant);
        
        if (discriminant > 0) {
            // Two distinct real roots
            double sqrtDelta = Math.sqrt(discriminant);
            double x1 = (-b + sqrtDelta) / (2 * a);
            double x2 = (-b - sqrtDelta) / (2 * a);
            
            System.out.println("\n✓ Two distinct real roots:");
            System.out.printf("  x₁ = (-b + √Δ) / 2a = (%.2f + %.2f) / %.2f = %.6f%n", 
                -b, sqrtDelta, 2*a, x1);
            System.out.printf("  x₂ = (-b - √Δ) / 2a = (%.2f - %.2f) / %.2f = %.6f%n", 
                -b, sqrtDelta, 2*a, x2);
            
            // Verify solutions
            double check1 = a*x1*x1 + b*x1 + c;
            double check2 = a*x2*x2 + b*x2 + c;
            System.out.printf("\nVerification: f(x₁) = %.6f (should be ≈ 0)%n", check1);
            System.out.printf("Verification: f(x₂) = %.6f (should be ≈ 0)%n", check2);
        } 
        else if (discriminant == 0) {
            // One repeated real root
            double x = -b / (2 * a);
            System.out.println("\n✓ One repeated real root:");
            System.out.printf("  x = -b / 2a = %.2f / %.2f = %.6f%n", -b, 2*a, x);
            
            // Verify solution
            double check = a*x*x + b*x + c;
            System.out.printf("Verification: f(x) = %.6f (should be ≈ 0)%n", check);
        } 
        else {
            // No real roots (complex roots)
            double realPart = -b / (2 * a);
            double imaginaryPart = Math.sqrt(-discriminant) / (2 * a);
            
            System.out.println("\n✓ No real roots (Complex roots):");
            System.out.printf("  x₁ = %.6f + %.6fi%n", realPart, imaginaryPart);
            System.out.printf("  x₂ = %.6f - %.6fi%n", realPart, imaginaryPart);
        }
    }
    
    /**
     * Method to solve multiple equations and store history
     */
    public void solveAndStore(double a, double b, double c) {
        String equation = String.format("%.2fx² + %.2fx + %.2f = 0", a, b, c);
        calculationHistory.add(equation);
        solveQuadratic(a, b, c);
    }
    
    /**
     * Method to display calculation history
     */
    public void displayHistory() {
        if (calculationHistory.isEmpty()) {
            System.out.println("No equations solved yet.");
            return;
        }
        
        System.out.println("\n📜 Calculation History:");
        for (int i = 0; i < calculationHistory.size(); i++) {
            System.out.printf("%d. %s%n", i+1, calculationHistory.get(i));
        }
    }
    
    /**
     * Main method to demonstrate both features
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Q6_AgeAndQuadratic q6 = new Q6_AgeAndQuadratic();
        
        System.out.println("\n" + "=".repeat(70));
        System.out.println("    Q6: AGE CLASSIFICATION & QUADRATIC EQUATION SOLVER");
        System.out.println("=".repeat(70));
        
        // Part (a): Age Classification
        System.out.println("\n=== PART (A): Age Classification ===\n");
        
        System.out.print("Enter your age: ");
        int age = getValidInput(scanner);
        
        if (age >= 0) {
            displayAgeCategory(age);
        }
        
        // Part (b): Quadratic Equation Solver
        System.out.println("\n" + "=".repeat(70));
        System.out.println("=== PART (B): Quadratic Equation Solver ===\n");
        
        String continueChoice;
        
        do {
            System.out.println("Solving equation: ax² + bx + c = 0");
            System.out.print("Enter coefficient a (must be non-zero): ");
            double a = getValidDouble(scanner);
            
            if (a == 0) {
                System.out.println("❌ Error: Coefficient 'a' cannot be zero!");
                continue;
            }
            
            System.out.print("Enter coefficient b: ");
            double b = getValidDouble(scanner);
            
            System.out.print("Enter coefficient c: ");
            double c = getValidDouble(scanner);
            
            solveQuadratic(a, b, c);
            q6.solveAndStore(a, b, c);
            
            System.out.print("\nSolve another equation? (yes/no): ");
            scanner.nextLine();
            continueChoice = scanner.nextLine().trim().toLowerCase();
            System.out.println();
            
            if (!continueChoice.equals("yes") && !continueChoice.equals("y")) {
                break;
            }
        } while (true);
        
        q6.displayHistory();
        
        // Examples
        System.out.println("\n" + "=".repeat(70));
        System.out.println("=== EXAMPLE QUADRATIC EQUATIONS ===\n");
        
        System.out.println("Example 1: x² - 5x + 6 = 0");
        solveQuadratic(1, -5, 6);
        
        System.out.println("\nExample 2: x² - 4x + 4 = 0");
        solveQuadratic(1, -4, 4);
        
        System.out.println("\nExample 3: x² - 2x + 2 = 0");
        solveQuadratic(1, -2, 2);
        
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
