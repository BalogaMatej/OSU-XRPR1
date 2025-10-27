package cz.osu.carmanagementsystem.service;

import cz.osu.carmanagementsystem.dto.CarDTO;
import cz.osu.carmanagementsystem.dto.CarMapper;
import cz.osu.carmanagementsystem.model.Car;
import cz.osu.carmanagementsystem.persistance.CarRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class CarService {

    private final CarRepository carRepository;
    private final CarMapper carMapper;

    @Transactional
    public CarDTO createCar(CarDTO carDTO) {
        log.info("Creating new car with VIN: {}", carDTO.getVin());

        // Check if car with this VIN already exists
        if (carRepository.existsByVin(carDTO.getVin())) {
            throw new IllegalArgumentException("Car with VIN " + carDTO.getVin() + " already exists");
        }

        Car car = carMapper.toEntity(carDTO);
        car.setId(null); // Ensure it's a new entity
        Car savedCar = carRepository.save(car);

        log.info("Successfully created car with ID: {}", savedCar.getId());
        return carMapper.toDTO(savedCar);
    }

    @Transactional(readOnly = true)
    public Optional<CarDTO> getCarById(Long id) {
        log.debug("Fetching car with ID: {}", id);
        return carRepository.findById(id)
                .map(carMapper::toDTO);
    }

    @Transactional(readOnly = true)
    public List<CarDTO> getAllCars() {
        log.debug("Fetching all cars");
        return carRepository.findAll()
                .stream()
                .map(carMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public Optional<CarDTO> updateCar(Long id, CarDTO carDTO) {
        log.info("Updating car with ID: {}", id);

        return carRepository.findById(id)
                .map(existingCar -> {
                    // Check if VIN is being changed and if new VIN already exists
                    if (!existingCar.getVin().equals(carDTO.getVin()) &&
                        carRepository.existsByVin(carDTO.getVin())) {
                        throw new IllegalArgumentException("Car with VIN " + carDTO.getVin() + " already exists");
                    }

                    existingCar.setBrand(carDTO.getBrand());
                    existingCar.setModel(carDTO.getModel());
                    existingCar.setProductionYear(carDTO.getYear());
                    existingCar.setVin(carDTO.getVin());

                    Car updatedCar = carRepository.save(existingCar);
                    log.info("Successfully updated car with ID: {}", id);
                    return carMapper.toDTO(updatedCar);
                });
    }

    @Transactional
    public boolean deleteCar(Long id) {
        log.info("Deleting car with ID: {}", id);

        if (carRepository.existsById(id)) {
            carRepository.deleteById(id);
            log.info("Successfully deleted car with ID: {}", id);
            return true;
        }

        log.warn("Car with ID {} not found for deletion", id);
        return false;
    }

    @Transactional(readOnly = true)
    public Optional<CarDTO> getCarByVin(String vin) {
        log.debug("Fetching car with VIN: {}", vin);
        return carRepository.findByVin(vin)
                .map(carMapper::toDTO);
    }
}

