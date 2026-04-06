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

    }
}
