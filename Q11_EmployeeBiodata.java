import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

/**
 * Q11: Employee Biodata and Retirement/Pay Calculation
 * 
 * Enhanced with:
 * - Age calculation from birth year
 * - Employee database storage
 * - Tax calculation details
 * - Salary slip generation
 * - Data validation and error handling
 * - Employee records management
 */
public class Q11_EmployeeBiodata {
    
    // Static employee registry
    private static List<Q11_EmployeeBiodata> employeeRegistry = new ArrayList<>();
    private static int employeeCounter = 0;
    
    // Constants
    private static final int GROSS_PAY = 100000;
    private static final int RETIREMENT_AGE = 65;
    private static final double TAX_RATE = 0.125;           // 12.5%
    private static final double PENSION_RATE = 0.075;       // 7.5%
    private static final double HEALTH_INSURANCE_RATE = 0.05; // 5%
    private static final double HOUSING_RATE = 0.05;        // 5%
    private static final int CURRENT_YEAR = LocalDate.now().getYear();
    
    /**
     * Method to add employee to registry
     */
    private void addToRegistry() {
        employeeCounter++;
        employeeRegistry.add(this);
    }
    
    /**
     * Static method to display all employees
     */
    public static void displayAllEmployees() {
        if (employeeRegistry.isEmpty()) {
            System.out.println("No employees registered yet.");
            return;
        }
        
        System.out.println("\n" + "=".repeat(70));
        System.out.println("                    EMPLOYEE REGISTRY");
        System.out.println("=".repeat(70));
        System.out.printf("%-5s%-20s%-20s%-15s%n", "ID", "Name", "DoB", "Retirement Year");
        System.out.println("-".repeat(70));
        
        for (int i = 0; i < employeeRegistry.size(); i++) {
            Q11_EmployeeBiodata emp = employeeRegistry.get(i);
            System.out.printf("%-5d%-20s%-20s%-15d%n", 
                i+1, emp.getFullName(), emp.yearOfBirth, emp.retirementYear);
        }
        System.out.println("=".repeat(70) + "\n");
    }
    
    /**
     * Static method to display payroll summary
     */
    public static void displayPayrollSummary() {
        if (employeeRegistry.isEmpty()) {
            System.out.println("No employees in payroll.");
            return;
        }
        
        System.out.println("\n" + "=".repeat(70));
        System.out.println("                   PAYROLL SUMMARY");
        System.out.println("=".repeat(70));
        
        double totalGross = 0;
        double totalNetPay = 0;
        double totalDeductions = 0;
        
        for (Q11_EmployeeBiodata emp : employeeRegistry) {
            totalGross += emp.grossPay;
            totalNetPay += emp.netPay;
            totalDeductions += (emp.taxDeduction + emp.pensionDeduction + 
                              emp.healthInsuranceDeduction + emp.housingDeduction);
        }
        
        System.out.printf("Total Employees: %d%n", employeeRegistry.size());
        System.out.printf("Total Gross Pay: ₦%.2f%n", totalGross);
        System.out.printf("Total Deductions: ₦%.2f%n", totalDeductions);
        System.out.printf("Total Net Pay: ₦%.2f%n", totalNetPay);
        System.out.printf("Average Net Pay: ₦%.2f%n", totalNetPay / employeeRegistry.size());
        System.out.println("=".repeat(70) + "\n");
    }
    
    /**
     * Method to capture employee biodata
     */
    public void captureEmployeeBiodata(Scanner scanner) {
        System.out.println("\n" + "=".repeat(70));
        System.out.println("            EMPLOYEE BIODATA REGISTRATION");
        System.out.println("=".repeat(70) + "\n");
        
        System.out.print("Enter First Name: ");
        this.firstName = scanner.nextLine().trim();
        
        System.out.print("Enter Last Name: ");
        this.lastName = scanner.nextLine().trim();
        
        System.out.print("Enter Year of Birth: ");
        while (!scanner.hasNextInt()) {
            System.out.println("❌ Invalid input! Enter a valid year: ");
            scanner.nextLine();
            System.out.print("Enter Year of Birth: ");
        }
        this.yearOfBirth = scanner.nextInt();
        scanner.nextLine();
        
        // Validate year of birth
        if (yearOfBirth < 1930 || yearOfBirth > CURRENT_YEAR - 18) {
            System.out.println("⚠️  Warning: Invalid birth year. Please try again.");
            return;
        }
        
        System.out.print("Enter Next of Kin: ");
        this.nextOfKin = scanner.nextLine().trim();
        
        System.out.print("Enter Residential Address: ");
        this.residentialAddress = scanner.nextLine().trim();
        
        // Initialize gross pay
        this.grossPay = GROSS_PAY;
        
        // Calculate retirement year and deductions
        calculateRetirementYear();
        calculateDeductions();
        
        // Add to registry
        addToRegistry();
    }
    
    /**
     * Method to calculate age
     */
    public int getAge() {
        return CURRENT_YEAR - yearOfBirth;
    }
    
    /**
     * Method to display detailed salary slip
     */
    public void displaySalarySlip() {
        System.out.println("\n" + "=".repeat(70));
        System.out.println("                     SALARY SLIP");
        System.out.println("=".repeat(70));
        System.out.printf("Employee ID: %d%n", employeeRegistry.size());
        System.out.printf("Name: %s%n", getFullName());
        System.out.printf("Month/Year: %s%n", CURRENT_YEAR);
        System.out.println("-".repeat(70));
        System.out.printf("Gross Pay: ₦%.2f%n", grossPay);
        System.out.println("\nDeductions:");
        System.out.printf("  Tax (12.5%%): ₦%.2f%n", taxDeduction);
        System.out.printf("  Pension (7.5%%): ₦%.2f%n", pensionDeduction);
        System.out.printf("  Health Insurance (5%%): ₦%.2f%n", healthInsuranceDeduction);
        System.out.printf("  Housing (5%%): ₦%.2f%n", housingDeduction);
        double totalDed = taxDeduction + pensionDeduction + healthInsuranceDeduction + housingDeduction;
        System.out.printf("  Total Deductions: ₦%.2f%n", totalDed);
        System.out.println("-".repeat(70));
        System.out.printf("NET PAY: ₦%.2f%n", netPay);
        System.out.println("=".repeat(70));
    }
    
    /**
     * Method to calculate retirement year
     * Retirement Age = 65 years
     */
    private void calculateRetirementYear() {
        this.retirementYear = yearOfBirth + RETIREMENT_AGE;
    }
    
    /**
     * Method to calculate all deductions and net pay
     */
    private void calculateDeductions() {
        // Calculate each deduction
        this.taxDeduction = grossPay * TAX_RATE;
        this.pensionDeduction = grossPay * PENSION_RATE;
        this.healthInsuranceDeduction = grossPay * HEALTH_INSURANCE_RATE;
        this.housingDeduction = grossPay * HOUSING_RATE;
        
        // Calculate total deductions
        double totalDeductions = taxDeduction + pensionDeduction + 
                                healthInsuranceDeduction + housingDeduction;
        
        // Calculate net pay
        this.netPay = grossPay - totalDeductions;
    }
    
    /**
     * Method to display complete employee information report
     */
    public void displayEmployeeReport() {
        System.out.println("\n" + "=".repeat(70));
        System.out.println("           EMPLOYEE INFORMATION & COMPENSATION REPORT");
        System.out.println("=".repeat(70));
        
        // Personal Information
        System.out.println("\n📋 PERSONAL INFORMATION:");
        System.out.println("-".repeat(70));
        System.out.printf("Full Name:\t\t%s %s%n", firstName, lastName);
        System.out.printf("Year of Birth:\t\t%d%n", yearOfBirth);
        System.out.printf("Age (Current Year %d):\t%d years%n", CURRENT_YEAR, (CURRENT_YEAR - yearOfBirth));
        System.out.printf("Next of Kin:\t\t%s%n", nextOfKin);
        System.out.printf("Residential Address:\t%s%n", residentialAddress);
        
        // Retirement Information
        System.out.println("\n🎓 RETIREMENT INFORMATION:");
        System.out.println("-".repeat(70));
        System.out.printf("Retirement Age:\t\t%d years%n", RETIREMENT_AGE);
        System.out.printf("Retirement Year:\t%d%n", retirementYear);
        System.out.printf("Years to Retirement:\t%d years%n", (retirementYear - CURRENT_YEAR));
        
        // Compensation Details
        System.out.println("\n💰 MONTHLY COMPENSATION DETAILS:");
        System.out.println("-".repeat(70));
        System.out.printf("Gross Pay:\t\t₦%.2f%n", grossPay);
        
        // Deductions breakdown
        System.out.println("\nDEDUCTIONS:");
        System.out.printf("  Tax (12.5%%):\t\t₦%.2f%n", taxDeduction);
        System.out.printf("  Pension (7.5%%):\t₦%.2f%n", pensionDeduction);
        System.out.printf("  Health Insurance (5%%):\t₦%.2f%n", healthInsuranceDeduction);
        System.out.printf("  Housing Contribution (5%%):\t₦%.2f%n", housingDeduction);
        
        // Total deductions
        double totalDeductions = taxDeduction + pensionDeduction + 
                                healthInsuranceDeduction + housingDeduction;
        System.out.printf("  Total Deductions:\t₦%.2f%n", totalDeductions);
        
        // Net pay
        System.out.println("\n" + "=".repeat(70));
        System.out.printf("✓ NET MONTHLY PAY:\t₦%.2f%n", netPay);
        System.out.println("=".repeat(70));
        
        // Annual calculation
        double annualGross = grossPay * 12;
        double annualNetPay = netPay * 12;
        System.out.printf("\nANNUAL FIGURES:%n");
        System.out.printf("Annual Gross Pay:\t₦%.2f%n", annualGross);
        System.out.printf("Annual Net Pay:\t\t₦%.2f%n", annualNetPay);
        System.out.println();
    }
    
    /**
     * Getter methods
     */
    public String getFullName() {
        return firstName + " " + lastName;
    }
    
    public int getRetirementYear() {
        return retirementYear;
    }
    
    public double getNetPay() {
        return netPay;
    }
    
    /**
     * Main method with interactive employee registration
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("\n" + "=".repeat(70));
        System.out.println("          KOLESSION EMPLOYEE MANAGEMENT SYSTEM");
        System.out.println("          Q11: EMPLOYEE BIODATA & COMPENSATION CALCULATOR");
        System.out.println("=".repeat(70));
        System.out.println("\nWelcome to KolesSolution HR Department");
        System.out.println("This system captures employee biodata and calculates compensation.\n");
        
        String continueChoice;
        boolean running = true;
        
        while (running) {
            System.out.println("\n=== MAIN MENU ===");
            System.out.println("1. Register new employee");
            System.out.println("2. View all employees");
            System.out.println("3. View payroll summary");
            System.out.println("4. Exit");
            System.out.print("Select option (1-4): ");
            
            String choice = scanner.nextLine().trim();
            
            switch (choice) {
                case "1":
                    Q11_EmployeeBiodata employee = new Q11_EmployeeBiodata();
                    employee.captureEmployeeBiodata(scanner);
                    employee.displayEmployeeReport();
                    employee.displaySalarySlip();
                    break;
                    
                case "2":
                    displayAllEmployees();
                    break;
                    
                case "3":
                    displayPayrollSummary();
                    break;
                    
                case "4":
                    running = false;
                    break;
                    
                default:
                    System.out.println("❌ Invalid option. Please try again.");
            }
        }
        
        // Final summary
        System.out.println("\n" + "=".repeat(70));
        System.out.println("                      REGISTRATION SUMMARY");
        System.out.println("=".repeat(70));
        System.out.printf("Total Employees Registered: %d%n", employeeRegistry.size());
        System.out.println("Thank you for using KolesSolution HR System!");
        System.out.println("=".repeat(70) + "\n");
        
        scanner.close();
    }
}
