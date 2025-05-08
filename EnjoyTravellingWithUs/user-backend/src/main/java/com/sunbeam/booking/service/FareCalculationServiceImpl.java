package com.sunbeam.booking.service;

import java.util.*;

import org.springframework.stereotype.Service;

@Service
public class FareCalculationServiceImpl implements FareCalculationService {
    private static final double FARE_PER_KM = 10.0;

    // ✅ Graph representation using an adjacency list
    private final Map<String, List<Route>> graph = new HashMap<>();

    public FareCalculationServiceImpl() {
        initializeRoutes();
    }

    /**
     * ✅ Calculates the fare between two cities based on the shortest route.
     * - Uses **Dijkstra's algorithm** to find the shortest path.
     * - Returns `distance * FARE_PER_KM` if a valid route is found.
     */
    @Override
    public double calculateFare(String source, String destination) {
        if (source == null || destination == null || source.isEmpty() || destination.isEmpty()) {
            throw new IllegalArgumentException("Source and destination cannot be empty.");
        }

        source = source.toLowerCase().trim();
        destination = destination.toLowerCase().trim();

        double distance = findShortestDistance(source, destination);
        if (distance == Double.MAX_VALUE) {
            throw new IllegalArgumentException("Invalid route! No path found between " + source + " and " + destination);
        }
        return distance * FARE_PER_KM;
    }

    /**
     * ✅ Initializes predefined routes.
     * - Can be **replaced with a database fetch** in the future.
     */
    private void initializeRoutes() {
        addRoute("Pune", "Mumbai", 150);
        addRoute("Mumbai", "Nashik", 180);
        addRoute("Pune", "Nashik", 210);
        addRoute("Mumbai", "Goa", 550);
        addRoute("Pune", "Goa", 560);
        addRoute("Nashik", "Nagpur", 700);
    }

    /**
     * ✅ Adds a **bidirectional** route to the graph.
     * - Ensures case-insensitive city names.
     */
    private void addRoute(String city1, String city2, double distance) {
        city1 = city1.toLowerCase();
        city2 = city2.toLowerCase();

        graph.computeIfAbsent(city1, k -> new ArrayList<>()).add(new Route(city2, distance));
        graph.computeIfAbsent(city2, k -> new ArrayList<>()).add(new Route(city1, distance));
    }

    /**
     * ✅ Uses **Dijkstra's algorithm** to find the shortest distance between two cities.
     * - Returns `Double.MAX_VALUE` if no route is found.
     */
    private double findShortestDistance(String source, String destination) {
        if (!graph.containsKey(source) || !graph.containsKey(destination)) {
            return Double.MAX_VALUE;
        }

        Map<String, Double> distances = new HashMap<>();
        for (String city : graph.keySet()) {
            distances.put(city, Double.MAX_VALUE);
        }
        distances.put(source, 0.0);

        PriorityQueue<Route> minHeap = new PriorityQueue<>(Comparator.comparingDouble(r -> r.distance));
        minHeap.add(new Route(source, 0));

        while (!minHeap.isEmpty()) {
            Route current = minHeap.poll();
            if (current.city.equals(destination)) {
                return current.distance;
            }

            for (Route neighbor : graph.getOrDefault(current.city, Collections.emptyList())) {
                double newDist = current.distance + neighbor.distance;
                if (newDist < distances.get(neighbor.city)) {
                    distances.put(neighbor.city, newDist);
                    minHeap.add(new Route(neighbor.city, newDist));
                }
            }
        }
        return Double.MAX_VALUE;
    }

    /**
     * ✅ Represents a **route between two cities**.
     * - Implements `Comparable` for priority queue sorting.
     */
    private static class Route implements Comparable<Route> {
        String city;
        double distance;

        public Route(String city, double distance) {
            this.city = city;
            this.distance = distance;
        }

        @Override
        public int compareTo(Route other) {
            return Double.compare(this.distance, other.distance);
        }
    }
}
