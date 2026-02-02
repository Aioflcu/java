/**
 * Q8: Java Operators, Arithmetic Operations, and Code Analysis
 * 
 * (a) Operators explanation with examples:
 *     (i)   //   - Line comment
 *     (ii)  ||   - Logical OR operator
 *     (iii) &&   - Logical AND operator
 *     (iv)  ^    - Bitwise XOR (exclusive OR)
 *     (v)   \t   - Tab character escape sequence
 * 
 * (b) Arithmetic operations: Real Division, Integer Division, Modulo Division
 * 
 * (c) Code snippet analysis with corrections
 * 
 * Enhanced with:
 * - Truth tables for all operators
 * - More examples and use cases
 * - Visual demonstrations
 * - Performance tips
 */
public class Q8_OperatorsAndArithmetic {
    
    /**
     * (A) OPERATORS EXPLANATION WITH EXAMPLES
     */
    
    // (i) // - Line Comment Operator
    // This comment explains what a line comment does
    // Everything after // on the same line is ignored by the compiler
    
    /**
     * Example (i): Line Comment
     * Line comments start with // and extend to the end of the line
     */
    public static void exampleLineComment() {
        System.out.println("=== (i) LINE COMMENT (//) ===\n");
        
        int x = 5; // This is a line comment explaining the variable x
        int y = 10; // Another line comment
        // System.out.println("This line is commented out and won't execute");
        
        System.out.println("Line comments are useful for explaining code inline.");
        System.out.println("Everything after // is ignored by the compiler.\n");
    }
    
    /**
     * Example (ii): Logical OR Operator (||)
     * Returns true if at least one condition is true
     */
    public static void exampleLogicalOR() {
        System.out.println("=== (ii) LOGICAL OR OPERATOR (||) ===\n");
        
        int age = 20;
        boolean hasLicense = false;
        
        // || means if either condition is true, result is true
        if (age > 18 || hasLicense) {
            System.out.println("Condition: age > 18 OR hasLicense");
            System.out.println("age > 18 = " + (age > 18));
            System.out.println("hasLicense = " + hasLicense);
            System.out.println("Result: " + ((age > 18) || hasLicense) + " (true because age > 18)\n");
        }
        
        System.out.println("Truth Table for ||:");
        System.out.println("true || true = " + (true || true));
        System.out.println("true || false = " + (true || false));
        System.out.println("false || true = " + (false || true));
        System.out.println("false || false = " + (false || false) + "\n");
    }
    
    /**
     * Example (iii): Logical AND Operator (&&)
     * Returns true only if both conditions are true
     */
    public static void exampleLogicalAND() {
        System.out.println("=== (iii) LOGICAL AND OPERATOR (&&) ===\n");
        
        int score = 75;
        int attendance = 80;
        
        // && means both conditions must be true
        if (score >= 60 && attendance >= 75) {
            System.out.println("Condition: score >= 60 AND attendance >= 75");
            System.out.println("score >= 60 = " + (score >= 60));
            System.out.println("attendance >= 75 = " + (attendance >= 75));
            System.out.println("Result: " + ((score >= 60) && (attendance >= 75)) + " (true because both are true)\n");
        }
        
        System.out.println("Truth Table for &&:");
        System.out.println("true && true = " + (true && true));
        System.out.println("true && false = " + (true && false));
        System.out.println("false && true = " + (false && true));
        System.out.println("false && false = " + (false && false) + "\n");
    }
    
    /**
     * Example (iv): Bitwise XOR Operator (^)
     * Returns true if bits are different
     */
    public static void exampleBitwiseXOR() {
        System.out.println("=== (iv) BITWISE XOR OPERATOR (^) ===\n");
        
        int a = 5;    // Binary: 0101
        int b = 3;    // Binary: 0011
        int result = a ^ b;
        
        System.out.println("Bitwise XOR (^):");
        System.out.println("a = " + a + " (Binary: " + Integer.toBinaryString(a) + ")");
        System.out.println("b = " + b + " (Binary: " + Integer.toBinaryString(b) + ")");
        System.out.println("a ^ b = " + result + " (Binary: " + Integer.toBinaryString(result) + ")");
        System.out.println("\nBitwise comparison:");
        System.out.println("0 ^ 0 = 0  (same bits → 0)");
        System.out.println("0 ^ 1 = 1  (different bits → 1)");
        System.out.println("1 ^ 0 = 1  (different bits → 1)");
        System.out.println("1 ^ 1 = 0  (same bits → 0)\n");
        
        // Boolean XOR example
        boolean x = true;
        boolean y = false;
        System.out.println("Boolean XOR: " + x + " ^ " + y + " = " + (x ^ y) + "\n");
    }
    
    /**
     * Example (v): Tab Character Escape Sequence (\t)
     * Inserts a horizontal tab for formatting
     */
    public static void exampleTabCharacter() {
        System.out.println("=== (v) TAB CHARACTER ESCAPE SEQUENCE (\\t) ===\n");
        
        System.out.println("Without tab:");
        System.out.println("Name | Age | City");
        System.out.println("John | 25 | Lagos");
        
        System.out.println("\nWith tab (\\t) for better alignment:");
        System.out.println("Name\t| Age\t| City");
        System.out.println("John\t| 25\t| Lagos");
        System.out.println("Sarah\t| 30\t| Ibadan");
        System.out.println("Ahmed\t| 28\t| Kano\n");
    }
    
    /**
     * (B) ARITHMETIC OPERATIONS
     */
    
    /**
     * Real Division: Result is a floating-point number
     */
    public static void realDivision() {
        System.out.println("=== REAL DIVISION (/): Floating-Point Result ===\n");
        
        double numerator = 15.0;
        double denominator = 4.0;
        double result = numerator / denominator;
        
        System.out.println("double result = 15.0 / 4.0;");
        System.out.println("Result: " + result);
        System.out.println("Explanation: Real division preserves decimal places.\n");
        
        // Another example
        int a = 10;
        int b = 3;
        double realDiv = (double) a / b;  // Cast to double for real division
        
        System.out.println("int a = 10, b = 3;");
        System.out.println("double realDiv = (double) a / b;");
        System.out.println("Result: " + realDiv + "\n");
    }
    
    /**
     * Integer Division: Result discards decimal places
     */
    public static void integerDivision() {
        System.out.println("=== INTEGER DIVISION (/): Discards Decimal Places ===\n");
        
        int numerator = 15;
        int denominator = 4;
        int result = numerator / denominator;
        
        System.out.println("int result = 15 / 4;");
        System.out.println("Result: " + result);
        System.out.println("Explanation: Integer division returns only the whole number part.\n");
        
        // Another example
        int a = 23;
        int b = 5;
        int intDiv = a / b;
        
        System.out.println("int a = 23, b = 5;");
        System.out.println("int intDiv = a / b;");
        System.out.println("Result: " + intDiv + " (decimal part discarded)\n");
    }
    
    /**
     * Modulo Division: Result is the remainder
     */
    public static void moduloDivision() {
        System.out.println("=== MODULO DIVISION (%): Returns Remainder ===\n");
        
        int dividend = 15;
        int divisor = 4;
        int remainder = dividend % divisor;
        
        System.out.println("int remainder = 15 % 4;");
        System.out.println("Result: " + remainder);
        System.out.println("Explanation: 15 ÷ 4 = 3 remainder 1\n");
        
        // Another example
        int a = 23;
        int b = 5;
        int mod = a % b;
        
        System.out.println("int a = 23, b = 5;");
        System.out.println("int mod = a % b;");
        System.out.println("Result: " + mod + " (remainder when 23 is divided by 5)\n");
        
        // Practical example: checking even/odd
        System.out.println("Practical use: Check if number is even or odd");
        System.out.println("if (num % 2 == 0) → number is even");
        System.out.println("if (num % 2 == 1) → number is odd\n");
    }
    
    /**
     * (C) CODE SNIPPET ANALYSIS AND CORRECTIONS
     */
    public static void codeAnalysis() {
        System.out.println("=== (C) CODE SNIPPET ANALYSIS ===\n");
        
        System.out.println("ORIGINAL CODE (WITH ERRORS):");
        System.out.println("-".repeat(60));
        System.out.println("int sum = 0; i, j = 2;                 // Line 1");
        System.out.println("for (i = 10; i >= 0; i – = 2);  {      // Line 2");
        System.out.println("    sum += j%i;                        // Line 3");
        System.out.println("    j += 3;                            // Line 4");
        System.out.println("}                                       // Line 5");
        System.out.println("System.out.print(\"Total accumulated Sum = \", sum);");
        
        System.out.println("\n\nERRORS IDENTIFIED:");
        System.out.println("-".repeat(60));
        
        System.out.println("SYNTAX ERRORS (2):");
        System.out.println("1. Line 1: 'int sum = 0; i, j = 2;'");
        System.out.println("   ✗ Cannot declare multiple variables without 'int' keyword");
        System.out.println("   ✓ CORRECTION: int sum = 0, i, j = 2;");
        System.out.println("   (Use comma to separate, not semicolon)\n");
        
        System.out.println("2. Line 1: 'System.out.print(\"Total accumulated Sum = \", sum);'");
        System.out.println("   ✗ print() doesn't accept multiple arguments with comma");
        System.out.println("   ✓ CORRECTION: System.out.println(\"Total accumulated Sum = \" + sum);");
        System.out.println("   (Use '+' for concatenation or println)\n");
        
        System.out.println("LOGICAL ERROR (1):");
        System.out.println("1. Line 2: 'for (i = 10; i >= 0; i – = 2);'");
        System.out.println("   ✗ Semicolon after the loop condition creates an empty loop");
        System.out.println("   ✗ Also: i – = 2 should be i -= 2");
        System.out.println("   ✗ Issue: When i = 0, j % i causes division by zero error");
        System.out.println("   ✓ CORRECTION: for (i = 10; i > 0; i -= 2) {");
        System.out.println("   (Remove semicolon and use i > 0 to avoid j%0)\n");
        
        System.out.println("CORRECTED CODE:");
        System.out.println("-".repeat(60));
        System.out.println("int sum = 0, i, j = 2;");
        System.out.println("for (i = 10; i > 0; i -= 2) {");
        System.out.println("    sum += j % i;");
        System.out.println("    j += 3;");
        System.out.println("}");
        System.out.println("System.out.println(\"Total accumulated Sum = \" + sum);");
        
        System.out.println("\n\nSUMMARY OF WHAT THE SNIPPET DOES:");
        System.out.println("-".repeat(60));
        System.out.println("The program:");
        System.out.println("1. Initializes sum = 0, i = 10, j = 2");
        System.out.println("2. Loops from i = 10 down to i = 2 (step -2)");
        System.out.println("3. In each iteration:");
        System.out.println("   - Calculates j % i (remainder of j divided by i)");
        System.out.println("   - Adds the remainder to sum");
        System.out.println("   - Increments j by 3");
        System.out.println("4. Prints the total accumulated sum\n");
        
        System.out.println("EXECUTION TRACE (Corrected Code):");
        System.out.println("-".repeat(60));
        
        int sum = 0, i, j = 2;
        for (i = 10; i > 0; i -= 2) {
            int remainder = j % i;
            sum += remainder;
            System.out.printf("i=%2d, j=%2d, j%%i=%2d, sum=%d%n", i, j, remainder, sum);
            j += 3;
        }
        
        System.out.println("\nFinal Output: Total accumulated Sum = " + sum + "\n");
    }
    
    /**
     * Main method demonstrating all concepts
     */
    public static void main(String[] args) {
        System.out.println("\n" + "=".repeat(70));
        System.out.println("       Q8: OPERATORS, ARITHMETIC OPERATIONS & CODE ANALYSIS");
        System.out.println("=".repeat(70));
        
        // Part (A): Operators
        System.out.println("\n" + "=".repeat(70));
        System.out.println("           PART (A): JAVA OPERATORS WITH EXAMPLES");
        System.out.println("=".repeat(70) + "\n");
        
        exampleLineComment();
        exampleLogicalOR();
        exampleLogicalAND();
        exampleBitwiseXOR();
        exampleTabCharacter();
        
        // Part (B): Arithmetic Operations
        System.out.println("=".repeat(70));
        System.out.println("        PART (B): ARITHMETIC OPERATIONS IN JAVA");
        System.out.println("=".repeat(70) + "\n");
        
        realDivision();
        integerDivision();
        moduloDivision();
        
        // Part (C): Code Analysis
        System.out.println("=".repeat(70) + "\n");
        codeAnalysis();
        
        System.out.println("=".repeat(70) + "\n");
    }
}
