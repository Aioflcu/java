import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;
import java.util.Comparator;
import java.util.Arrays;
import java.util.Scanner;
import java.io.PrintWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class NMSRainfall {
    
    // Constants
    private static final double FLOODING_THRESHOLD = 10.0;
    private static final String SEPARATOR = "================================================================";
    private static final String EXPORT_FILE = "NMS_Rainfall_Report.csv";
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        
        // Display data structure diagram
        displayDataDiagram();
        
        printHeader();
        
        // Get user input for rainfall data
        HashMap<String, int[]> rainfallData = getUserRainfallData();
        
        // Perform analysis
        analyzeRainfallData(rainfallData);
        
        // Export results to CSV
        try {
            exportToCSV(rainfallData);
            System.out.println("\n✓ Report exported to: " + EXPORT_FILE);
        } catch (IOException e) {
            System.err.println("⚠ Error exporting report: " + e.getMessage());
        }
        
        scanner.close();
    }
    
    /**
     * Get rainfall data from user input
     */
    private static HashMap<String, int[]> getUserRainfallData() {
        HashMap<String, int[]> rainfallData = new HashMap<>();
        
        System.out.println("\n" + SEPARATOR);
        System.out.println("RAINFALL DATA INPUT");
        System.out.println(SEPARATOR);
        
        System.out.print("\nEnter number of states: ");
        int numStates = getValidInteger();
        
        for (int i = 0; i < numStates; i++) {
            System.out.print("\n--- State " + (i + 1) + " ---");
            System.out.print("\nEnter state name: ");
            String stateName = scanner.nextLine().trim();
            
            if (stateName.isEmpty()) {
                System.out.println("⚠ State name cannot be empty. Skipping...");
                i--;
                continue;
            }
            
            System.out.print("Enter number of weather stations for " + stateName + ": ");
            int numStations = getValidInteger();
            
            int[] readings = new int[numStations];
            System.out.println("Enter daily rainfall readings (in mm) for " + numStations + " stations:");
            
            for (int j = 0; j < numStations; j++) {
                System.out.print("  Station " + (j + 1) + " rainfall (mm): ");
                readings[j] = getValidInteger();
            }
            
            rainfallData.put(stateName, readings);
        }
        
        if (rainfallData.isEmpty()) {
            System.out.println("\n⚠ No data entered. Using sample data instead...");
            return getDefaultSampleData();
        }
        
        return rainfallData;
    }
    
    /**
     * Get valid integer input from user
     */
    private static int getValidInteger() {
        while (true) {
            try {
                String input = scanner.nextLine().trim();
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.print("⚠ Invalid input. Please enter a valid integer: ");
            }
        }
    }
    
    /**
     * Get default sample data if user doesn't input any
     */
    private static HashMap<String, int[]> getDefaultSampleData() {
        HashMap<String, int[]> rainfallData = new HashMap<>();
        
        rainfallData.put("Lagos", new int[]{12, 15, 9, 14});
        rainfallData.put("Rivers", new int[]{18, 20, 22, 19});
        rainfallData.put("Bayelsa", new int[]{22, 20, 21, 23});
        rainfallData.put("Kano", new int[]{3, 4, 2, 5});
        rainfallData.put("Enugu", new int[]{11, 12, 10, 13});
        
        return rainfallData;
    }
    
    /**
     * Analyze rainfall data and generate reports
     */
    private static void analyzeRainfallData(HashMap<String, int[]> rainfallData) {
        // Store results
        List<StateRainfallInfo> stateInfoList = new ArrayList<>();
        List<String> floodWarningStates = new ArrayList<>();
        Map<String, List<StateRainfallInfo>> regionMap = new HashMap<>();
        
        int totalRainfallNigeria = 0;
        int totalStationsNigeria = 0;

        // Process each state
        for (Map.Entry<String, int[]> entry : rainfallData.entrySet()) {
            String state = entry.getKey();
            int[] readings = entry.getValue();
            
            double mor = calculateMOR(readings);
            double stdDev = calculateStandardDeviation(readings, mor);
            int minRain = findMin(readings);
            int maxRain = findMax(readings);
            double median = calculateMedian(readings);
            boolean requiresWarning = mor > FLOODING_THRESHOLD;
            
            StateRainfallInfo info = new StateRainfallInfo(
                state, mor, readings.length, requiresWarning,
                stdDev, minRain, maxRain, median, readings
            );
            stateInfoList.add(info);
            
            if (requiresWarning) {
                floodWarningStates.add(state);
            }
            
            // Group by region
            String region = getRegion(state);
            regionMap.computeIfAbsent(region, k -> new ArrayList<>()).add(info);
            
            totalRainfallNigeria += sumArray(readings);
            totalStationsNigeria += readings.length;
        }
        
        // Generate reports
        generateReport1(stateInfoList);
        generateReport2(floodWarningStates);
        generateReport3(totalRainfallNigeria, totalStationsNigeria, stateInfoList);
        generateReport4(stateInfoList);
        generateReport5(regionMap);
        generateReport6(stateInfoList);
    }
    
    /**
     * Calculate Mean Outrageous Rainfall (MOR)
     */
    private static double calculateMOR(int[] readings) {
        if (readings.length == 0) return 0;
        return (double) sumArray(readings) / readings.length;
    }
    
    /**
     * Calculate standard deviation
     */
    private static double calculateStandardDeviation(int[] readings, double mean) {
        if (readings.length == 0) return 0;
        double sumSquaredDiffs = 0;
        for (int val : readings) {
            sumSquaredDiffs += Math.pow(val - mean, 2);
        }
        return Math.sqrt(sumSquaredDiffs / readings.length);
    }
    
    /**
     * Find minimum value
     */
    private static int findMin(int[] arr) {
        int min = arr[0];
        for (int val : arr) {
            if (val < min) min = val;
        }
        return min;
    }
    
    /**
     * Find maximum value
     */
    private static int findMax(int[] arr) {
        int max = arr[0];
        for (int val : arr) {
            if (val > max) max = val;
        }
        return max;
    }
    
    /**
     * Calculate median value
     */
    private static double calculateMedian(int[] arr) {
        int[] sorted = arr.clone();
        Arrays.sort(sorted);
        if (sorted.length % 2 == 0) {
            return (sorted[sorted.length / 2 - 1] + sorted[sorted.length / 2]) / 2.0;
        }
        return sorted[sorted.length / 2];
    }
    
    /**
     * Get region for a state
     */
    private static String getRegion(String state) {
        return switch(state) {
            case "Lagos", "Rivers", "Bayelsa", "Delta", "Edo", "Cross River", "Akwa Ibom", "Calabar" 
                -> "Southern Region";
            case "Enugu", "Ebonyi", "Anambra", "Imo", "Abia" 
                -> "Eastern Region";
            case "Oyo", "Ogun", "Ondo", "Osun", "Ekiti" 
                -> "South-Western Region";
            case "Kaduna", "Kogi", "Kwara", "Nasarawa", "Niger", "Plateau", "Benue" 
                -> "Central Region";
            case "Kano", "Katsina", "Jigawa", "Kebbi", "Sokoto", "Zamfara" 
                -> "Northern Region";
            case "Borno", "Yobe", "Adamawa", "Gombe", "Taraba" 
                -> "North-Eastern Region";
            case "FCT" 
                -> "Federal Capital Territory";
            default -> "Unknown Region";
        };
    }
    
    /**
     * Sum all elements in array
     */
    private static int sumArray(int[] arr) {
        int sum = 0;
        for (int val : arr) {
            sum += val;
        }
        return sum;
    }
    
    /**
     * Report 1: MOR for each State (sorted alphabetically)
     */
    private static void generateReport1(List<StateRainfallInfo> stateInfoList) {
        System.out.println("\n" + SEPARATOR);
        System.out.println("REPORT 1: MEAN OUTRAGEOUS RAINFALL (MOR) FOR EACH STATE");
        System.out.println(SEPARATOR);
        System.out.printf("%-18s %-10s %-10s %-10s %-12s %s\n", 
            "STATE", "MOR (mm)", "Min", "Max", "Std Dev", "STATUS");
        System.out.println(SEPARATOR);
        
        stateInfoList.stream()
            .sorted(Comparator.comparing(s -> s.state))
            .forEach(info -> {
                String status = info.requiresWarning ? "⚠ WARNING" : "✓ OK";
                System.out.printf("%-18s %-10.2f %-10d %-10d %-12.2f %s\n", 
                    info.state, info.mor, info.minRain, info.maxRain, info.stdDev, status);
            });
    }
    
    /**
     * Report 2: States requiring WPF (sorted by MOR descending)
     */
    private static void generateReport2(List<String> floodWarningStates) {
        System.out.println("\n" + SEPARATOR);
        System.out.println("REPORT 2: STATES WITH WARNING OF POSSIBLE FLOODING (WPF)");
        System.out.println(SEPARATOR);
        
        if (floodWarningStates.isEmpty()) {
            System.out.println("✓ No states require flooding warning.");
        } else {
            System.out.println("⚠ CRITICAL: " + floodWarningStates.size() + " states have MOR > " + FLOODING_THRESHOLD + "mm\n");
            floodWarningStates.stream()
                .sorted()
                .forEach((i) -> System.out.println("  🔴 " + i));
        }
    }
    
    /**
     * Report 3: Overall Nigeria MOR
     */
    private static void generateReport3(int totalRainfall, int totalStations, List<StateRainfallInfo> stateInfoList) {
        double nigeriaMOR = (double) totalRainfall / totalStations;
        double avgStateMOR = stateInfoList.stream()
            .mapToDouble(s -> s.mor)
            .average()
            .orElse(0);
        
        double maxMOR = stateInfoList.stream()
            .mapToDouble(s -> s.mor)
            .max()
            .orElse(0);
        
        double minMOR = stateInfoList.stream()
            .mapToDouble(s -> s.mor)
            .min()
            .orElse(0);
        
        System.out.println("\n" + SEPARATOR);
        System.out.println("REPORT 3: OVERALL NIGERIA MEAN OUTRAGEOUS RAINFALL");
        System.out.println(SEPARATOR);
        System.out.printf("Total Rainfall (all stations): %d mm\n", totalRainfall);
        System.out.printf("Total Number of Stations: %d\n", totalStations);
        System.out.printf("Number of States: %d\n", stateInfoList.size());
        System.out.printf("OVERALL NIGERIA MOR PER DAY: %.2f mm\n", nigeriaMOR);
        System.out.printf("Average State MOR: %.2f mm\n", avgStateMOR);
        System.out.printf("Maximum State MOR: %.2f mm\n", maxMOR);
        System.out.printf("Minimum State MOR: %.2f mm\n", minMOR);
        System.out.printf("MOR Range: %.2f mm\n", maxMOR - minMOR);
    }
    
    /**
     * Report 4: Top and Bottom States by MOR
     */
    private static void generateReport4(List<StateRainfallInfo> stateInfoList) {
        System.out.println("\n" + SEPARATOR);
        System.out.println("REPORT 4: TOP AND BOTTOM PERFORMERS");
        System.out.println(SEPARATOR);
        
        List<StateRainfallInfo> sorted = new ArrayList<>(stateInfoList);
        sorted.sort(Comparator.comparingDouble((StateRainfallInfo s) -> s.mor).reversed());
        
        System.out.println("\n🔴 TOP 5 STATES WITH HIGHEST RAINFALL:");
        sorted.stream().limit(5).forEach(s -> 
            System.out.printf("  %2d. %-18s %.2f mm (Range: %d-%d mm)\n", 
                sorted.indexOf(s) + 1, s.state, s.mor, s.minRain, s.maxRain));
        
        System.out.println("\n🔵 BOTTOM 5 STATES WITH LOWEST RAINFALL:");
        sorted.stream().skip(Math.max(0, sorted.size() - 5)).forEach(s -> 
            System.out.printf("  %2d. %-18s %.2f mm (Range: %d-%d mm)\n", 
                sorted.size() - sorted.indexOf(s), s.state, s.mor, s.minRain, s.maxRain));
    }
    
    /**
     * Report 5: Regional Analysis
     */
    private static void generateReport5(Map<String, List<StateRainfallInfo>> regionMap) {
        System.out.println("\n" + SEPARATOR);
        System.out.println("REPORT 5: REGIONAL RAINFALL ANALYSIS");
        System.out.println(SEPARATOR);
        
        regionMap.entrySet().stream()
            .sorted(Comparator.comparing(Map.Entry::getKey))
            .forEach(entry -> {
                String region = entry.getKey();
                List<StateRainfallInfo> states = entry.getValue();
                
                double regionAvg = states.stream()
                    .mapToDouble(s -> s.mor)
                    .average()
                    .orElse(0);
                
                long floodStates = states.stream()
                    .filter(s -> s.requiresWarning)
                    .count();
                
                System.out.printf("\n%-25s: Avg MOR = %.2f mm | States: %d | Warning: %d\n", 
                    region, regionAvg, states.size(), floodStates);
                states.stream()
                    .sorted(Comparator.comparing(s -> s.state))
                    .forEach(s -> System.out.printf("  %-20s %.2f mm\n", s.state, s.mor));
            });
    }
    
    /**
     * Report 6: Rainfall Variability Analysis
     */
    private static void generateReport6(List<StateRainfallInfo> stateInfoList) {
        System.out.println("\n" + SEPARATOR);
        System.out.println("REPORT 6: RAINFALL VARIABILITY (STABILITY ANALYSIS)");
        System.out.println(SEPARATOR);
        System.out.printf("%-18s %-10s %-15s %s\n", "STATE", "MOR (mm)", "Std Dev", "STABILITY");
        System.out.println(SEPARATOR);
        
        stateInfoList.stream()
            .sorted(Comparator.comparingDouble((StateRainfallInfo s) -> s.stdDev))
            .forEach(info -> {
                String stability;
                if (info.stdDev < 1.5) {
                    stability = "✓ Very Stable";
                } else if (info.stdDev < 3.0) {
                    stability = "⚠ Moderately Stable";
                } else {
                    stability = "🔴 Unstable";
                }
                System.out.printf("%-18s %-10.2f %-15.2f %s\n", 
                    info.state, info.mor, info.stdDev, stability);
            });
    }
    
    /**
     * Print header
     */
    private static void printHeader() {
        System.out.println("\n" + SEPARATOR);
        System.out.println("NIGERIA METEOROLOGY SERVICES (NMS)");
        System.out.println("DAILY RAINFALL ANALYSIS REPORT");
        System.out.println("Generated: " + getCurrentDateTime());
        System.out.println("Flooding Threshold: > " + FLOODING_THRESHOLD + " mm");
        System.out.println(SEPARATOR);
    }
    
    /**
     * Get current date and time
     */
    private static String getCurrentDateTime() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return now.format(formatter);
    }
    
    /**
     * Export results to CSV file
     */
    private static void exportToCSV(HashMap<String, int[]> rainfallData) throws IOException {
        try (PrintWriter writer = new PrintWriter(EXPORT_FILE)) {
            // Write header
            writer.println("State,Region,MOR (mm),Min Rain (mm),Max Rain (mm),Median,Std Dev,Stations,Flooding Warning");
            
            // Write data
            for (Map.Entry<String, int[]> entry : rainfallData.entrySet()) {
                String state = entry.getKey();
                int[] readings = entry.getValue();
                
                double mor = calculateMOR(readings);
                double stdDev = calculateStandardDeviation(readings, mor);
                int minRain = findMin(readings);
                int maxRain = findMax(readings);
                double median = calculateMedian(readings);
                String region = getRegion(state);
                String warning = mor > FLOODING_THRESHOLD ? "YES" : "NO";
                
                writer.printf("%s,%s,%.2f,%d,%d,%.2f,%.2f,%d,%s\n",
                    state, region, mor, minRain, maxRain, median, stdDev, readings.length, warning);
            }
        }
    }
    
    // Display the data structure diagram
    public static void displayDataDiagram() {
        System.out.println("\nDATA STRUCTURE VISUALIZATION");
        System.out.println("===========================\n");
        System.out.println("Data Model: State -> Weather Stations -> Daily Rainfall Readings");
        System.out.println("\nExample Structure:");
        System.out.println("""
            
            Nigeria (Country)
            │
            ├── Lagos (State)
            │   ├── Station 1: 12mm
            │   ├── Station 2: 15mm
            │   ├── Station 3: 9mm
            │   └── Station 4: 14mm
            │   └── MOR = 12.5mm ✓
            │
            ├── Rivers (State)
            │   ├── Station 1: 18mm
            │   ├── Station 2: 20mm
            │   └── Station 3: 22mm
            │   └── MOR = 20mm ⚠ FLOODING
            │
            ├── Kano (State)
            │   ├── Station 1: 3mm
            │   ├── Station 2: 4mm
            │   └── Station 3: 2mm
            │   └── MOR = 3mm ✓
            │
            └── ... (other states)
            
            OVERALL NIGERIA MOR = Average of all station readings across all states
            """);
    }
}

/**
 * Inner class to store comprehensive state rainfall information
 */
class StateRainfallInfo {
    String state;
    double mor;
    int stationCount;
    boolean requiresWarning;
    double stdDev;
    int minRain;
    int maxRain;
    double median;
    int[] readings;
    
    public StateRainfallInfo(String state, double mor, int stationCount, boolean requiresWarning,
                           double stdDev, int minRain, int maxRain, double median, int[] readings) {
        this.state = state;
        this.mor = mor;
        this.stationCount = stationCount;
        this.requiresWarning = requiresWarning;
        this.stdDev = stdDev;
        this.minRain = minRain;
        this.maxRain = maxRain;
        this.median = median;
        this.readings = readings;
    }
}