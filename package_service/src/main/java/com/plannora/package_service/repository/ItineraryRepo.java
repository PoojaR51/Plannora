package com.plannora.package_service.repository;

import com.plannora.package_service.entity.Itinerary;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ItineraryRepo extends JpaRepository<Itinerary, Long> {

    List<Itinerary> findByTravelPackageIdOrderByDayNoAsc(Long packageId);

    Optional<Itinerary> findByTravelPackageIdAndDayNo(Long packageId, Integer dayNo);
}
