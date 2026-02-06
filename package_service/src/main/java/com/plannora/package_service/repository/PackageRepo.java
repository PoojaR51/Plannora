package com.plannora.package_service.repository;

import com.plannora.package_service.entity.TravelPackage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PackageRepo extends JpaRepository<TravelPackage, Long> {
}
