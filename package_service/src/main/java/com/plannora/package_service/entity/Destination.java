package com.plannora.package_service.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(
        name = "destinations",
        indexes = {
                @Index(name = "idx_destination_city", columnList = "city")
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Destination {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String city;

    @Column(name = "place_name", nullable = false)
    private String placeName;

    @Column(length = 1000)
    private String description;
}
