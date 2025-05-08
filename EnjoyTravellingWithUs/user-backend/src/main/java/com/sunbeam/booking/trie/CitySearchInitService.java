package com.sunbeam.booking.trie;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Arrays;
import java.util.List;

/**
 * 📌 Loads city names into Trie when the application starts
 */
@Service
public class CitySearchInitService {

    @Autowired
    private CitySearchService citySearchService;

    @PostConstruct // Runs after Spring Boot starts
    public void loadCityNames() {
        List<String> cities = Arrays.asList("Mumbai", "Pune", "Nashik", "Nagpur", "Goa", "Bangalore", "Hyderabad", "Delhi", "Chennai", "Kolkata");
        citySearchService.addCitiesToTrie(cities);
    }
}
