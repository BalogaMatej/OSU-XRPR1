package cz.osu.carmanagementsystem.dto;

import cz.osu.carmanagementsystem.model.Car;
import org.springframework.stereotype.Component;

@Component
public class CarMapper {

    public CarDTO toDTO(Car car) {
        if (car == null) {
            return null;
        }

        return CarDTO.builder()
                .id(car.getId())
                .brand(car.getBrand())
                .model(car.getModel())
                .year(car.getYear())
                .vin(car.getVin())
                .build();
    }

    public Car toEntity(CarDTO carDTO) {
        if (carDTO == null) {
            return null;
        }

        return Car.builder()
                .id(carDTO.getId())
                .brand(carDTO.getBrand())
                .model(carDTO.getModel())
                .year(carDTO.getYear())
                .vin(carDTO.getVin())
                .build();
    }
}

