package com.sunbeam.booking.service;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sunbeam.booking.dto.BusDTO;
import com.sunbeam.booking.entity.Bus;
import com.sunbeam.booking.repository.BusRepository;
import com.sunbeam.booking.trie.Trie;

@Service
public class BusService {
    @Autowired
    private BusRepository repository;

    @Autowired
    private FareCalculationService fareCalculationService;

    @Autowired
    private Trie routeTrie;


    public void init() {
        routeTrie = new Trie();
        List<Bus> buses = repository.findAll();
        for (Bus bus : buses) {
            routeTrie.insert(bus.getRoute());
        }
    }

    public List<BusDTO> findAll() {
        return repository.findAll().stream()
            .map(this::convertToBusDTO)
            .collect(Collectors.toList());
    }

    public Bus save(Bus bus) {
        routeTrie.insert(bus.getRoute());
        return repository.save(bus);
    }

    public Bus findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public BusDTO findBusDTOById(Long id) {
        Bus bus = findById(id);
        if (bus != null) {
            return convertToBusDTO(bus);
        }
        return null;
    }

    public Bus updateBus(Long id, Bus busDetails) {
        Bus bus = findById(id);
        if (bus != null) {
            bus.setBusNumber(busDetails.getBusNumber());
            bus.setRoute(busDetails.getRoute());
            bus.setCapacity(busDetails.getCapacity());
            bus.setAvailableSeats(busDetails.getAvailableSeats());
            return repository.save(bus);
        }
        return null;
    }

    public void deleteById(Long id) {
        Bus bus = findById(id);
        if (bus != null) {
            // Assuming remove method exists in Trie
            // routeTrie.remove(bus.getRoute());
        }
        repository.deleteById(id);
    }

    public List<String> searchBusRoutesByPrefix(String prefix) {
        return routeTrie.searchByPrefix(prefix);
    }

    public double calculateFare(String source, String destination) {
        return fareCalculationService.calculateFare(source, destination);
    }

    private BusDTO convertToBusDTO(Bus bus) {
        return new BusDTO(
            bus.getId(),
            bus.getBusNumber(),
            bus.getRoute(),
            bus.getDepartureTime(),
            bus.getArrivalTime(),
            bus.getAvailableSeats(),
            bus.getCapacity()
        );
    }
}
