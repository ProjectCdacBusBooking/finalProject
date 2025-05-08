package com.sunbeam.booking.trie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 📌 City Search REST API
 */
@RestController
@RequestMapping("/cities")
public class CitySearchController {

    @Autowired
    private CitySearchService citySearchService;

    /**
     * ✅ Endpoint to search cities by prefix
     */
    @GetMapping("/search")
    public List<String> searchCities(@RequestParam String prefix) {
        return citySearchService.searchCitiesByPrefix(prefix);
    }
}
