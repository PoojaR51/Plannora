package com.plannora.package_service.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(
        name = "itineraries",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_package_day",
                        columnNames = {"package_id", "day_no"}
                )
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Itinerary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "day_no", nullable = false)
    private Integer dayNo;

    @Column(nullable = false)
    private String activity;

    @Column(length = 1000)
    private String description;

    /* ===================== RELATION ===================== */

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "package_id", nullable = false)
    private TravelPackage travelPackage;
}
