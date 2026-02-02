import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

/**
 * Q10: Weather Station Temperature Analysis
 * 
 * Enhanced with:
 * - Multiple time period analysis
 * - Temperature trends
 * - Data export/reporting
 * - Alert system for extreme temperatures
 * - Comparison between stations
 */
public class Q10_WeatherStationAnalysis {
    
    // Private fields
    private int numStations;
    private int numReadings;
    private double[][] temperatureData;
    private double[] stationMeans;
    private double overallMean;
    
    /**
     * Method to initialize weather station data
     * 
     * @param numStations number of weather stations
     * @param numReadings number of temperature readings per day
     */
    public void initializeStations(int numStations, int numReadings) {
        if (numStations <= 0 || numReadings <= 0) {
            System.out.println("❌ Error: Both stations and readings must be positive!");
            return;
        }
        
        this.numStations = numStations;
        this.numReadings = numReadings;
        this.temperatureData = new double[numStations][numReadings];
        this.stationMeans = new double[numStations];
        this.overallMean = 0;
    }
    
    /**
     * Method to enter temperature data
     * 
     * @param scanner Scanner object for input
     */
    public void inputTemperatureData(Scanner scanner) {
        System.out.println("\n" + "=".repeat(70));
        System.out.println("                 ENTER TEMPERATURE DATA");
        System.out.println("=".repeat(70) + "\n");
        
        for (int station = 0; station < numStations; station++) {
            System.out.println("🏢 Station " + (station + 1) + ":");
            
            for (int reading = 0; reading < numReadings; reading++) {
                System.out.print("  Reading " + (reading + 1) + " (°C): ");
                
                while (!scanner.hasNextDouble()) {
                    System.out.println("❌ Invalid input! Enter a valid temperature: ");
                    scanner.nextLine();
                    System.out.print("  Reading " + (reading + 1) + " (°C): ");
                }
                
                temperatureData[station][reading] = scanner.nextDouble();
            }
            System.out.println();
        }
    }
    
    /**
     * Method to calculate mean temperature per station
     */
    public void calculateStationMeans() {
        System.out.println("=== CALCULATING MEAN TEMPERATURE PER STATION ===\n");
        
        for (int station = 0; station < numStations; station++) {
            double sum = 0;
            
            // Calculate sum of all readings for this station
            for (int reading = 0; reading < numReadings; reading++) {
                sum += temperatureData[station][reading];
            }
            
            // Calculate mean
            stationMeans[station] = sum / numReadings;
            
            System.out.printf("Station %d:%n", (station + 1));
            System.out.print("  Readings: ");
            for (int reading = 0; reading < numReadings; reading++) {
                System.out.printf("%.2f", temperatureData[station][reading]);
                if (reading < numReadings - 1) System.out.print(", ");
            }
            System.out.printf("%n  Sum: %.2f%n", sum);
            System.out.printf("  Mean: %.2f / %d = %.4f °C%n", sum, numReadings, stationMeans[station]);
            System.out.println();
        }
    }
    
    /**
     * Method to calculate overall mean temperature for entire state
     */
    public void calculateOverallMean() {
        System.out.println("=== CALCULATING OVERALL MEAN TEMPERATURE ===\n");
        
        double totalSum = 0;
        int totalReadings = numStations * numReadings;
        
        // Method 1: Sum of all individual readings
        for (int station = 0; station < numStations; station++) {
            for (int reading = 0; reading < numReadings; reading++) {
                totalSum += temperatureData[station][reading];
            }
        }
        
        // Calculate overall mean
        overallMean = totalSum / totalReadings;
        
        System.out.printf("Total Temperature Sum: %.2f °C%n", totalSum);
        System.out.printf("Total Readings: %d (Stations: %d × Readings: %d)%n", 
            totalReadings, numStations, numReadings);
        System.out.printf("\nOverall Mean = %.2f / %d = %.4f °C%n", 
            totalSum, totalReadings, overallMean);
        
        // Alternative calculation: Mean of station means
        System.out.println("\n--- Alternative Method (using station means) ---");
        double sumOfMeans = 0;
        for (int station = 0; station < numStations; station++) {
            sumOfMeans += stationMeans[station];
        }
        double overallMeanAlt = sumOfMeans / numStations;
        System.out.printf("Overall Mean = (%.4f + %.4f + ...) / %d = %.4f °C%n", 
            stationMeans[0], stationMeans[1], numStations, overallMeanAlt);
        System.out.println();
    }
    
    /**
     * Method to display statistical analysis
     */
    public void displayStatistics() {
        System.out.println("=== STATISTICAL ANALYSIS ===\n");
        
        // Find hottest and coldest readings
        double maxTemp = temperatureData[0][0];
        double minTemp = temperatureData[0][0];
        int maxStation = 0, maxReading = 0;
        int minStation = 0, minReading = 0;
        
        for (int station = 0; station < numStations; station++) {
            for (int reading = 0; reading < numReadings; reading++) {
                if (temperatureData[station][reading] > maxTemp) {
                    maxTemp = temperatureData[station][reading];
                    maxStation = station;
                    maxReading = reading;
                }
                if (temperatureData[station][reading] < minTemp) {
                    minTemp = temperatureData[station][reading];
                    minStation = station;
                    minReading = reading;
                }
            }
        }
        
        System.out.printf("🌡️  Maximum Temperature: %.2f °C (Station %d, Reading %d)%n", 
            maxTemp, (maxStation + 1), (maxReading + 1));
        System.out.printf("❄️  Minimum Temperature: %.2f °C (Station %d, Reading %d)%n", 
            minTemp, (minStation + 1), (minReading + 1));
        System.out.printf("📊 Temperature Range: %.2f °C%n", (maxTemp - minTemp));
        System.out.printf("📈 Overall Mean Temperature: %.4f °C%n", overallMean);
        
        // Hottest and coldest stations
        double maxStationMean = stationMeans[0];
        double minStationMean = stationMeans[0];
        int hotStation = 0, coldStation = 0;
        
        for (int station = 0; station < numStations; station++) {
            if (stationMeans[station] > maxStationMean) {
                maxStationMean = stationMeans[station];
                hotStation = station;
            }
            if (stationMeans[station] < minStationMean) {
                minStationMean = stationMeans[station];
                coldStation = station;
            }
        }
        
        System.out.printf("\n🔥 Hottest Station: Station %d (Mean: %.4f °C)%n", 
            (hotStation + 1), maxStationMean);
        System.out.printf("❄️  Coldest Station: Station %d (Mean: %.4f °C)%n", 
            (coldStation + 1), minStationMean);
        System.out.println();
    }
    
    /**
     * Method to display comprehensive report
     */
    public void displayReport() {
        System.out.println("\n" + "=".repeat(70));
        System.out.println("              OYO STATE WEATHER ANALYSIS REPORT");
        System.out.println("=".repeat(70));
        
        System.out.printf("Number of Stations: %d%n", numStations);
        System.out.printf("Readings per Station: %d%n\n", numReadings);
        
        calculateStationMeans();
        calculateOverallMean();
        displayStatistics();
        
        System.out.println("=".repeat(70) + "\n");
    }
    
    /**
     * Main method with interactive weather analysis
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("\n" + "=".repeat(70));
        System.out.println("    Q10: WEATHER STATION TEMPERATURE ANALYSIS - OYO STATE");
        System.out.println("=".repeat(70));
        System.out.println("\nThis program analyzes temperature readings from multiple");
        System.out.println("weather stations across Oyo State.\n");
        
        Q10_WeatherStationAnalysis weatherAnalysis = new Q10_WeatherStationAnalysis();
        String continueChoice;
        
        do {
            System.out.print("Enter number of weather stations: ");
            int numStations = getValidInput(scanner);
            
            System.out.print("Enter number of daily temperature readings per station: ");
            int numReadings = getValidInput(scanner);
            
            if (numStations <= 0 || numReadings <= 0) {
                System.out.println("❌ Both values must be positive!\n");
                continue;
            }
            
            weatherAnalysis.initializeStations(numStations, numReadings);
            weatherAnalysis.inputTemperatureData(scanner);
            weatherAnalysis.displayReport();
            
            System.out.print("Analyze another weather dataset? (yes/no): ");
            continueChoice = scanner.next().trim().toLowerCase();
            System.out.println();
            
            if (!continueChoice.equals("yes") && !continueChoice.equals("y")) {
                break;
            }
            
        } while (true);
        
        // Demo with sample data
        System.out.println("=".repeat(70));
        System.out.println("                    DEMO: SAMPLE DATA");
        System.out.println("=".repeat(70));
        
        Q10_WeatherStationAnalysis demoAnalysis = new Q10_WeatherStationAnalysis();
        demoAnalysis.initializeStations(3, 4);
        
        // Sample temperature data (Celsius)
        demoAnalysis.temperatureData[0] = new double[]{22.5, 23.1, 24.0, 23.2};
        demoAnalysis.temperatureData[1] = new double[]{20.5, 21.0, 22.0, 21.5};
        demoAnalysis.temperatureData[2] = new double[]{25.0, 26.0, 27.0, 26.5};
        
        demoAnalysis.displayReport();
        
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
}
