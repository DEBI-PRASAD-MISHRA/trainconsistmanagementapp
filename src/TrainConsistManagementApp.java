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

    }
}
