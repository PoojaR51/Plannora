package com.plannora.package_service.repository;

import com.plannora.package_service.entity.Destination;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DestinationRepo extends JpaRepository<Destination, Long> {

    boolean existsByCityAndPlaceName(String city, String placeName);
}
