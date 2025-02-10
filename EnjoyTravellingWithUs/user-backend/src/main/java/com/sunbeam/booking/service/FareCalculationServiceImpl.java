
/*
 * 🏆 Optimized Features:
✅ Dijkstra's Algorithm - Efficient shortest path finding
✅ Priority Queue (Min Heap) - Fastest route selection
✅ Graph Representation - Stores connected cities dynamically
✅ Bidirectional Routes - Handles two-way travel
✅ Future DB Integration - Can fetch routes from a database

🚀 Perfect fare calculation system for travel booking!
 */


package com.sunbeam.booking.service;  
// 📌 Service layer madhil package declaration  

import java.util.*;  
// 📌 Data structures like HashMap, List, and PriorityQueue use karnya sathi  

import org.springframework.stereotype.Service;  
// 📌 Service annotation je Spring Boot la service class identify karnya sathi madat karte  

@Service  
// 📌 Ha class ek Spring service bean ahe  

public class FareCalculationServiceImpl implements FareCalculationService {  
    private static final double FARE_PER_KM = 10.0;  
    // 📌 **Per kilometer** charge define kela  

    // ✅ Graph representation using an adjacency list  
    private final Map<String, List<Route>> graph = new HashMap<>();  
    // 📌 Graph maintain karnya sathi ek adjacency list  

    public FareCalculationServiceImpl() {  
        initializeRoutes();  
        // 📌 Constructor madhe predefined routes initialize karto  
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
        // 📌 Null or empty values handle karnya sathi validation  

        source = source.toLowerCase().trim();  
        destination = destination.toLowerCase().trim();  
        // 📌 Case-insensitive comparison sathi cities la lowercase madhe convert kela  

        double distance = findShortestDistance(source, destination);  
        // 📌 **Dijkstra's algorithm** use karun shortest path calculate karto  

        if (distance == Double.MAX_VALUE) {  
            throw new IllegalArgumentException("Invalid route! No path found between " + source + " and " + destination);  
        }  
        // 📌 Route nasel tar exception throw karto  

        return distance * FARE_PER_KM;  
        // 📌 Distance multiply karnya sathi per km fare  
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
        // 📌 Predefined bus routes manually add kele  
        // 🔄 Future madhe ya routes la database madhun fetch karu shakto  
    }  

    /**
     * ✅ Adds a **bidirectional** route to the graph.
     * - Ensures case-insensitive city names.
     */
    private void addRoute(String city1, String city2, double distance) {  
        city1 = city1.toLowerCase();  
        city2 = city2.toLowerCase();  
        // 📌 Case-insensitive storage  

        graph.computeIfAbsent(city1, k -> new ArrayList<>()).add(new Route(city2, distance));  
        graph.computeIfAbsent(city2, k -> new ArrayList<>()).add(new Route(city1, distance));  
        // 📌 **Bidirectional** edge add karto karan bus **donhi direction** ne chalu aste  
    }  

    /**
     * ✅ Uses **Dijkstra's algorithm** to find the shortest distance between two cities.
     * - Returns `Double.MAX_VALUE` if no route is found.
     */
    private double findShortestDistance(String source, String destination) {  
        if (!graph.containsKey(source) || !graph.containsKey(destination)) {  
            return Double.MAX_VALUE;  
        }  
        // 📌 Route nasel tar direct `Double.MAX_VALUE` return karto  

        Map<String, Double> distances = new HashMap<>();  
        // 📌 **Distance tracking sathi** HashMap use kela  

        for (String city : graph.keySet()) {  
            distances.put(city, Double.MAX_VALUE);  
        }  
        distances.put(source, 0.0);  
        // 📌 Sarva cities la initial distance `Infinity (Double.MAX_VALUE)` set karto  
        // 📌 Source city chi distance 0 karto  

        PriorityQueue<Route> minHeap = new PriorityQueue<>(Comparator.comparingDouble(r -> r.distance));  
        // 📌 **Priority Queue** use karto je **minimum distance la priority** detha  

        minHeap.add(new Route(source, 0));  
        // 📌 Starting city priority queue madhe add karto  

        while (!minHeap.isEmpty()) {  
            Route current = minHeap.poll();  
            // 📌 Priority queue madhun **least distance city** pop karto  

            if (current.city.equals(destination)) {  
                return current.distance;  
                // 📌 Destination la pahunchlo mhanje minimum distance milala  
            }  

            for (Route neighbor : graph.getOrDefault(current.city, Collections.emptyList())) {  
                double newDist = current.distance + neighbor.distance;  
                if (newDist < distances.get(neighbor.city)) {  
                    distances.put(neighbor.city, newDist);  
                    minHeap.add(new Route(neighbor.city, newDist));  
                    // 📌 Neighbor distance update karto aani queue madhe add karto  
                }  
            }  
        }  
        return Double.MAX_VALUE;  
        // 📌 Route nasel tar `Infinity` return karto  
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
            // 📌 Distance comparator implement kela  
        }  
    }  
}  
