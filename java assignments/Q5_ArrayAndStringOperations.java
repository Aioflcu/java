import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;
import java.util.Arrays;

/**
 * Q5: Array and String Operations
 * (a) Compute sum of all data in even indexes (2, 4, 6...)
 * (b) Search for a word in a string and count occurrences
 * 
 * Enhanced with:
 * - Multiple data type support (int, double, long)
 * - Case-insensitive word search
 * - Word frequency analysis
 * - Performance optimization
 * - Comprehensive statistics
 */
public class Q5_ArrayAndStringOperations {
    
    // Cache for word frequency
    private static Map<String, Integer> wordFrequencyCache = new HashMap<>();
    
    /**
     * (a) Method to compute sum of elements at even indexes (2, 4, 6...)
     * Note: Java array indexing starts from 0
     * 
     * @param arr the input array
     * @return sum of elements at even indexes
     */
    public static long sumEvenIndexes(int[] arr) {
        if (arr == null) {
            System.out.println("❌ Error: Array is null!");
            return 0;
        }
        
        long sum = 0;
        
        // Start from index 2 and increment by 2 (2, 4, 6, 8...)
        for (int i = 2; i < arr.length; i += 2) {
            sum += arr[i];
        }
        
        return sum;
    }
    
    /**
     * Overloaded method for double arrays
     */
    public static double sumEvenIndexes(double[] arr) {
        if (arr == null) {
            System.out.println("❌ Error: Array is null!");
            return 0;
        }
        
        double sum = 0;
        for (int i = 2; i < arr.length; i += 2) {
            sum += arr[i];
        }
        return sum;
    }
    
    /**
     * Overloaded method for long arrays
     */
    public static long sumEvenIndexes(long[] arr) {
        if (arr == null) {
            System.out.println("❌ Error: Array is null!");
            return 0;
        }
        
        long sum = 0;
        for (int i = 2; i < arr.length; i += 2) {
            sum += arr[i];
        }
        return sum;
    }
    
    /**
     * Method to get statistics about array elements at even indexes
     */
    public static void displayEvenIndexStats(int[] arr) {
        if (arr == null || arr.length < 3) {
            System.out.println("❌ Array must have at least 3 elements!");
            return;
        }
        
        long sum = 0;
        int count = 0;
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        
        for (int i = 2; i < arr.length; i += 2) {
            sum += arr[i];
            count++;
            if (arr[i] > max) max = arr[i];
            if (arr[i] < min) min = arr[i];
        }
        
        double average = (double) sum / count;
        
        System.out.println("\n📊 Statistics for Even Indexes (2, 4, 6...):");
        System.out.println("-".repeat(50));
        System.out.printf("Sum:\t\t%d%n", sum);
        System.out.printf("Count:\t\t%d%n", count);
        System.out.printf("Average:\t%.2f%n", average);
        System.out.printf("Maximum:\t%d%n", max);
        System.out.printf("Minimum:\t%d%n", min);
    }
    
    /**
     * (b) Method to search for a word in a text and count occurrences
     * Case-insensitive search with word boundary detection
     * 
     * @param text the document/text to search in
     * @param searchWord the word to search for
     * @return count of how many times the word appears
     */
    public static int countWordOccurrences(String text, String searchWord) {
        if (text == null || text.isEmpty() || searchWord == null || searchWord.isEmpty()) {
            return 0;
        }
        
        int count = 0;
        String[] words = text.toLowerCase().split("\\s+|[.,!?;:\"-]+");
        String targetWord = searchWord.toLowerCase();
        
        for (String word : words) {
            if (word.equals(targetWord)) {
                count++;
            }
        }
        
        return count;
    }
    
    /**
     * Method to get word frequency analysis of entire text
     * Returns frequency of all words in the text
     */
    public static Map<String, Integer> getWordFrequency(String text) {
        wordFrequencyCache.clear();
        
        if (text == null || text.isEmpty()) {
            return wordFrequencyCache;
        }
        
        String[] words = text.toLowerCase().split("\\s+|[.,!?;:\"-]+");
        
        for (String word : words) {
            if (!word.isEmpty()) {
                wordFrequencyCache.put(word, wordFrequencyCache.getOrDefault(word, 0) + 1);
            }
        }
        
        return wordFrequencyCache;
    }
    
    /**
     * Method to find most frequent words in text
     */
    public static void displayWordFrequency(String text) {
        Map<String, Integer> frequency = getWordFrequency(text);
        
        if (frequency.isEmpty()) {
            System.out.println("No words found.");
            return;
        }
        
        System.out.println("\n📊 Word Frequency Analysis:");
        System.out.println("-".repeat(50));
        System.out.printf("%-20s | %s%n", "Word", "Count");
        System.out.println("-".repeat(50));
        
        frequency.entrySet().stream()
            .sorted((a, b) -> b.getValue().compareTo(a.getValue()))
            .forEach(entry -> System.out.printf("%-20s | %d%n", entry.getKey(), entry.getValue()));
    }
    
    /**
     * Alternative method using indexOf for word search
     */
    public static int countWordOccurrencesAlt(String text, String searchWord) {
        if (text == null || text.isEmpty() || searchWord == null || searchWord.isEmpty()) {
            return 0;
        }
        
        int count = 0;
        int index = 0;
        String lowerText = text.toLowerCase();
        String lowerWord = searchWord.toLowerCase();
        
        while ((index = lowerText.indexOf(lowerWord, index)) != -1) {
            count++;
            index += lowerWord.length();
        }
        
        return count;
    }
    
    /**
     * Main method to demonstrate both operations
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("\n" + "=".repeat(70));
        System.out.println("    Q5: ARRAY AND STRING OPERATIONS");
        System.out.println("=".repeat(70));
        
        // Part (a): Sum of even indexes
        System.out.println("\n=== PART (A): Sum of Elements at Even Indexes ===\n");
        
        System.out.print("Select data type (1=int, 2=double, 3=long): ");
        int choice = getValidInput(scanner);
        
        if (choice == 1) {
            System.out.print("Enter the size of array: ");
            int size = getValidInput(scanner);
            int[] arr = new int[size];
            
            System.out.println("Enter array elements:");
            for (int i = 0; i < size; i++) {
                System.out.print("Element [" + i + "]: ");
                arr[i] = getValidInput(scanner);
            }
            
            System.out.println("\nArray elements: " + Arrays.toString(arr));
            System.out.println("Even indexes (2, 4, 6...): " + getEvenIndexElements(arr));
            
            long evenIndexSum = sumEvenIndexes(arr);
            System.out.printf("✓ Sum of elements at even indexes: %d%n", evenIndexSum);
            displayEvenIndexStats(arr);
        }
        else if (choice == 2) {
            System.out.print("Enter the size of array: ");
            int size = getValidInput(scanner);
            double[] arr = new double[size];
            
            System.out.println("Enter array elements:");
            for (int i = 0; i < size; i++) {
                System.out.print("Element [" + i + "]: ");
                arr[i] = getValidDouble(scanner);
            }
            
            System.out.println("\nArray elements: " + Arrays.toString(arr));
            double evenIndexSum = sumEvenIndexes(arr);
            System.out.printf("✓ Sum of elements at even indexes: %.2f%n", evenIndexSum);
        }
        else if (choice == 3) {
            System.out.print("Enter the size of array: ");
            int size = getValidInput(scanner);
            long[] arr = new long[size];
            
            System.out.println("Enter array elements:");
            for (int i = 0; i < size; i++) {
                System.out.print("Element [" + i + "]: ");
                arr[i] = scanner.nextLong();
            }
            
            System.out.println("\nArray elements: " + Arrays.toString(arr));
            long evenIndexSum = sumEvenIndexes(arr);
            System.out.printf("✓ Sum of elements at even indexes: %d%n", evenIndexSum);
        }
        
        // Part (b): Word search and count
        System.out.println("\n" + "=".repeat(70));
        System.out.println("=== PART (B): Word Search in Text ===\n");
        
        scanner.nextLine(); // Clear buffer
        
        System.out.println("Enter the text document:");
        String text = scanner.nextLine();
        
        System.out.print("Enter the word to search: ");
        String searchWord = scanner.nextLine();
        
        int occurrences = countWordOccurrences(text, searchWord);
        
        System.out.printf("\nText: \"%s\"%n", text);
        System.out.printf("Search word: \"%s\"%n", searchWord);
        System.out.printf("✓ The word \"%s\" appears %d time(s) in the document.%n", searchWord, occurrences);
        
        // Display word frequency
        displayWordFrequency(text);
        
        System.out.println("\n" + "=".repeat(70) + "\n");
        
        scanner.close();
    }
    
    /**
     * Helper method to convert array to string
     */
    private static String arrayToString(int[] arr) {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < arr.length; i++) {
            sb.append(arr[i]);
            if (i < arr.length - 1) sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }
    
    /**
     * Helper method to get elements at even indexes
     */
    private static String getEvenIndexElements(int[] arr) {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 2; i < arr.length; i += 2) {
            sb.append("arr[").append(i).append("]=").append(arr[i]);
            if (i + 2 < arr.length) sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
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
