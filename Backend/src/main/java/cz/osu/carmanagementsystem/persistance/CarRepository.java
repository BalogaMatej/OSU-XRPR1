package cz.osu.carmanagementsystem.persistance;

import cz.osu.carmanagementsystem.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

    /**
     * Find a car by its VIN (Vehicle Identification Number)
     * @param vin the VIN to search for
     * @return Optional containing the car if found
     */
    Optional<Car> findByVin(String vin);

    /**
     * Check if a car with the given VIN exists
     * @param vin the VIN to check
     * @return true if a car with this VIN exists
     */
    boolean existsByVin(String vin);
}
