package com.sunbeam.booking.trie;

import org.springframework.stereotype.Service;
import java.util.List;

/**
 * 📌 Service for City Search using Trie
 */
@Service
public class CitySearchService {
    private final Trie cityTrie = new Trie();

    /**
     * ✅ Adds multiple cities to the Trie
     */
    public void addCitiesToTrie(List<String> cityNames) {
        for (String city : cityNames) {
            cityTrie.insert(city.toLowerCase()); // Store in lowercase for case-insensitive search
        }
    }

    /**
     * ✅ Searches cities by prefix
     */
    public List<String> searchCitiesByPrefix(String prefix) {
        return cityTrie.searchByPrefix(prefix.toLowerCase());
    }
}
