package cz.osu.carmanagementsystem;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
        info = @Info(
                title = "Car Management System API",
                version = "1.0",
                description = "REST API for managing cars in the system. Provides CRUD operations for car entities.",
                contact = @Contact(
                        name = "Car Management System",
                        email = "support@carmanagementsystem.com"
                ),
                license = @License(
                        name = "Apache 2.0",
                        url = "https://www.apache.org/licenses/LICENSE-2.0.html"
                )
        ),
        servers = {
                @Server(url = "http://localhost:8080", description = "Local Development Server")
        }
)
public class CarManagementSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(CarManagementSystemApplication.class, args);
    }

}
