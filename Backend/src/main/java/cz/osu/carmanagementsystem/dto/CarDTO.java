package cz.osu.carmanagementsystem.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Data Transfer Object representing a car")
public class CarDTO {

    @Schema(description = "Unique identifier of the car", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @Schema(description = "Brand/manufacturer of the car", example = "Toyota", required = true)
    private String brand;

    @Schema(description = "Model of the car", example = "Camry", required = true)
    private String model;

    @Schema(description = "Production year of the car", example = "2023", required = true)
    private Integer year;

    @Schema(description = "Vehicle Identification Number (VIN) - must be unique", example = "1HGBH41JXMN109186", required = true)
    private String vin;
}
