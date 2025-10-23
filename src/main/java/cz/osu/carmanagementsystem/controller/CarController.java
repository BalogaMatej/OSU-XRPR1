package cz.osu.carmanagementsystem.controller;

import cz.osu.carmanagementsystem.dto.CarDTO;
import cz.osu.carmanagementsystem.service.CarService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/cars")
@RequiredArgsConstructor
public class CarController {

    private final CarService carService;

    /**
     * Create a new car
     * POST /api/cars
     */
    @PostMapping
    public ResponseEntity<CarDTO> createCar(@RequestBody CarDTO carDTO) {
        log.info("POST /api/cars - Creating new car");
        try {
            CarDTO createdCar = carService.createCar(carDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdCar);
        } catch (IllegalArgumentException e) {
            log.error("Error creating car: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    /**
     * Get all cars
     * GET /api/cars
     */
    @GetMapping
    public ResponseEntity<List<CarDTO>> getAllCars() {
        log.info("GET /api/cars - Fetching all cars");
        List<CarDTO> cars = carService.getAllCars();
        return ResponseEntity.ok(cars);
    }

    /**
     * Get a car by ID
     * GET /api/cars/{id}
     */
    @GetMapping("/{id}")
    public ResponseEntity<CarDTO> getCarById(@PathVariable Long id) {
        log.info("GET /api/cars/{} - Fetching car by ID", id);
        return carService.getCarById(id)
                .map(car -> ResponseEntity.ok(car))
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Get a car by VIN
     * GET /api/cars/vin/{vin}
     */
    @GetMapping("/vin/{vin}")
    public ResponseEntity<CarDTO> getCarByVin(@PathVariable String vin) {
        log.info("GET /api/cars/vin/{} - Fetching car by VIN", vin);
        return carService.getCarByVin(vin)
                .map(car -> ResponseEntity.ok(car))
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Update a car
     * PUT /api/cars/{id}
     */
    @PutMapping("/{id}")
    public ResponseEntity<CarDTO> updateCar(@PathVariable Long id, @RequestBody CarDTO carDTO) {
        log.info("PUT /api/cars/{} - Updating car", id);
        try {
            return carService.updateCar(id, carDTO)
                    .map(car -> ResponseEntity.ok(car))
                    .orElse(ResponseEntity.notFound().build());
        } catch (IllegalArgumentException e) {
            log.error("Error updating car: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    /**
     * Delete a car
     * DELETE /api/cars/{id}
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCar(@PathVariable Long id) {
        log.info("DELETE /api/cars/{} - Deleting car", id);
        boolean deleted = carService.deleteCar(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
