package cz.osu.carmanagementsystem.controller;

import cz.osu.carmanagementsystem.dto.CarDTO;
import cz.osu.carmanagementsystem.service.CarService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Car Management", description = "APIs for managing cars in the system")
public class CarController {

    private final CarService carService;

    /**
     * Create a new car
     * POST /api/cars
     */
    @PostMapping
    @Operation(summary = "Create a new car", description = "Creates a new car entry in the system")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Car successfully created",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = CarDTO.class))),
            @ApiResponse(responseCode = "409", description = "Conflict - Car with this VIN already exists",
                    content = @Content)
    })
    public ResponseEntity<CarDTO> createCar(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Car details to create", required = true)
            @RequestBody CarDTO carDTO) {
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
    @Operation(summary = "Get all cars", description = "Retrieves a list of all cars in the system")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list of cars",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = CarDTO.class)))
    })
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
    @Operation(summary = "Get car by ID", description = "Retrieves a specific car by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Car found",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = CarDTO.class))),
            @ApiResponse(responseCode = "404", description = "Car not found",
                    content = @Content)
    })
    public ResponseEntity<CarDTO> getCarById(
            @Parameter(description = "ID of the car to retrieve", required = true)
            @PathVariable Long id) {
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
    @Operation(summary = "Get car by VIN", description = "Retrieves a specific car by its Vehicle Identification Number")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Car found",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = CarDTO.class))),
            @ApiResponse(responseCode = "404", description = "Car not found",
                    content = @Content)
    })
    public ResponseEntity<CarDTO> getCarByVin(
            @Parameter(description = "VIN of the car to retrieve", required = true)
            @PathVariable String vin) {
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
    @Operation(summary = "Update a car", description = "Updates an existing car's information")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Car successfully updated",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = CarDTO.class))),
            @ApiResponse(responseCode = "404", description = "Car not found",
                    content = @Content),
            @ApiResponse(responseCode = "409", description = "Conflict - VIN already exists for another car",
                    content = @Content)
    })
    public ResponseEntity<CarDTO> updateCar(
            @Parameter(description = "ID of the car to update", required = true)
            @PathVariable Long id,
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Updated car details", required = true)
            @RequestBody CarDTO carDTO) {
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
    @Operation(summary = "Delete a car", description = "Deletes a car from the system")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Car successfully deleted",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Car not found",
                    content = @Content)
    })
    public ResponseEntity<Void> deleteCar(
            @Parameter(description = "ID of the car to delete", required = true)
            @PathVariable Long id) {
        log.info("DELETE /api/cars/{} - Deleting car", id);
        boolean deleted = carService.deleteCar(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
