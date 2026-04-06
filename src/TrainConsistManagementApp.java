import java.util.*;
import java.util.stream.Collectors;

class InvalidCapacityException extends Exception {
    public InvalidCapacityException(String message) {
        super(message);
    }
}

public class TrainConsistManagementApp {
    public static void main(String[] args) {
        // UC1 — Initialize Train and Display Consist Summary
        System.out.println("--- UC1: Initialize Train ---");
        String trainId = "EXP-101";
        String trainName = "Rajdhani Express";
        int totalBogieCount = 0;
        System.out.println("Train ID: " + trainId + ", Name: " + trainName + ", Bogies: " + totalBogieCount);

        // UC2 — Add Passenger Bogies to Train (ArrayList)
        System.out.println("\n--- UC2: Add Passenger Bogies ---");
        List<String> arrayListBogies = new ArrayList<>();
        arrayListBogies.add("B1-Sleeper");
        arrayListBogies.add("B2-Sleeper");
        arrayListBogies.add("B3-AC");
        arrayListBogies.add("B4-AC");
        arrayListBogies.add("B5-General");
        for (int i = 0; i < arrayListBogies.size(); i++) {
            System.out.println("Index " + i + ": " + arrayListBogies.get(i));
        }
        totalBogieCount += arrayListBogies.size();

        // UC3 — Track Unique Bogie IDs (Set — HashSet)
        System.out.println("\n--- UC3: Track Unique Bogie IDs ---");
        Set<String> uniqueBogieIds = new HashSet<>();
        uniqueBogieIds.add("B1-Sleeper");
        uniqueBogieIds.add("B2-Sleeper");
        uniqueBogieIds.add("B1-Sleeper"); // Duplicate
        System.out.println("Unique Bogie IDs (Duplicates rejected): " + uniqueBogieIds);

        // UC4 — Maintain Ordered Bogie IDs (TreeSet)
        System.out.println("\n--- UC4: Maintain Ordered Bogie IDs ---");
        Set<String> orderedBogieIds = new TreeSet<>();
        orderedBogieIds.add("C3");
        orderedBogieIds.add("A1");
        orderedBogieIds.add("B2");
        System.out.println("Ordered Bogie IDs: " + orderedBogieIds);

        // UC5 — Preserve Insertion Order of Bogies (LinkedHashSet)
        System.out.println("\n--- UC5: Preserve Insertion Order ---");
        Set<String> insertionOrderedBogies = new LinkedHashSet<>();
        insertionOrderedBogies.add("Gen-1");
        insertionOrderedBogies.add("SL-1");
        insertionOrderedBogies.add("AC-1");
        System.out.println("Insertion Ordered Bogies: " + insertionOrderedBogies);

        // UC6 — Map Bogie to Capacity (HashMap)
        System.out.println("\n--- UC6: Map Bogie to Capacity ---");
        Map<String, Integer> bogieCapacityMap = new HashMap<>();
        bogieCapacityMap.put("B1-Sleeper", 72);
        bogieCapacityMap.put("B2-Sleeper", 72);
        bogieCapacityMap.put("B3-AC", 64);
        bogieCapacityMap.put("B4-AC", 64);
        bogieCapacityMap.put("B5-General", 90);
        for (Map.Entry<String, Integer> entry : bogieCapacityMap.entrySet()) {
            System.out.println("Bogie: " + entry.getKey() + ", Capacity: " + entry.getValue());
        }

        // UC7 — Sort Bogies by Capacity (Comparator)
        System.out.println("\n--- UC7: Sort Bogies by Capacity ---");
        List<Map.Entry<String, Integer>> listToSort = new ArrayList<>(bogieCapacityMap.entrySet());
        listToSort.sort(Map.Entry.comparingByValue());
        for (Map.Entry<String, Integer> entry : listToSort) {
            System.out.println("Bogie: " + entry.getKey() + " -> " + entry.getValue());
        }

        // UC8 — Filter Passenger Bogies Using Streams
        System.out.println("\n--- UC8: Filter Bogies Using Streams ---");
        List<String> filteredBogies = arrayListBogies.stream()
                .filter(b -> b.contains("AC") || b.contains("Sleeper"))
                .collect(Collectors.toList());
        System.out.println("Filtered Bogies (AC/Sleeper): " + filteredBogies);

        // UC9 — Group Bogies by Type
        System.out.println("\n--- UC9: Group Bogies by Type ---");
        Map<String, List<String>> groupedBogies = arrayListBogies.stream()
                .collect(Collectors.groupingBy(b -> {
                    if (b.contains("AC")) return "AC";
                    if (b.contains("Sleeper")) return "Sleeper";
                    return "General";
                }));
        for (Map.Entry<String, List<String>> entry : groupedBogies.entrySet()) {
            System.out.println("Type: " + entry.getKey() + " -> " + entry.getValue());
        }

        // UC10 — Count Total Seats in Train (reduce)
        System.out.println("\n--- UC10: Total Seats (reduce) ---");
        int totalSeats = bogieCapacityMap.values().stream()
                .reduce(0, Integer::sum);
        System.out.println("Total Seating Capacity: " + totalSeats);

        // UC11 — Validate Train ID & Cargo Codes
        System.out.println("\n--- UC11: Validate Train ID & Cargo Codes ---");
        String testTrainId = "EXP101";
        List<String> cargoCodes = Arrays.asList("CRG01", "C@RGO");
        boolean isTrainIdValid = testTrainId.matches("^[A-Za-z]+.*\\d+$");
        System.out.println("Train ID '" + testTrainId + "' valid? " + isTrainIdValid);
        for (String code : cargoCodes) {
            boolean isCargoValid = code.matches("^[A-Za-z0-9]+$");
            System.out.println("Cargo Code '" + code + "' valid? " + isCargoValid);
        }

        // UC12 — Safety Compliance Check for Goods Bogies
        System.out.println("\n--- UC12: Safety Compliance Check ---");
        Map<String, Integer> goodsBogies = new HashMap<>();
        goodsBogies.put("G1", 45);
        goodsBogies.put("G2", 60);
        goodsBogies.put("G3", 50);
        for (Map.Entry<String, Integer> entry : goodsBogies.entrySet()) {
            boolean isCompliant = entry.getValue() <= 50;
            System.out.println("Bogie " + entry.getKey() + " (Load: " + entry.getValue() + " tons) - Compliant: " + isCompliant);
        }

        // UC13 — Performance Comparison (Loops vs Streams)
        System.out.println("\n--- UC13: Performance Comparison ---");
        long startTimeLoop = System.nanoTime();
        List<String> loopFiltered = new ArrayList<>();
        for (String b : arrayListBogies) {
            if (b.contains("AC")) loopFiltered.add(b);
        }
        long endTimeLoop = System.nanoTime();
        long durationLoop = endTimeLoop - startTimeLoop;

        long startTimeStream = System.nanoTime();
        List<String> streamFiltered = arrayListBogies.stream().filter(b -> b.contains("AC")).collect(Collectors.toList());
        long endTimeStream = System.nanoTime();
        long durationStream = endTimeStream - startTimeStream;

        System.out.println("Loop Duration: " + durationLoop + " ns");
        System.out.println("Stream Duration: " + durationStream + " ns");
        System.out.println("Faster approach: " + (durationLoop < durationStream ? "Loop" : "Stream"));

    }
}
