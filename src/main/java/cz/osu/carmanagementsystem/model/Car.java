package cz.osu.carmanagementsystem.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@Builder(toBuilder = true)
@ToString(of = {"id", "brand", "model", "year", "vin"})
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "car")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "brand", nullable = false)
    private String brand;
    @Column(name = "model", nullable = false)
    private String model;
    @Column(name = "year", nullable = false)
    private int year;
    @Column(name = "vin", nullable = false, unique = true)
    private String vin;
}
