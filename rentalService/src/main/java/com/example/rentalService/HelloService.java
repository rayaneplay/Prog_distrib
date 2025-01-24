package com.example.rentalService;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloService {

    private List<Car> cars = new ArrayList<>(List.of(
        new Car("Toyota", "Corolla", 2020, "ABC-123", false),
        new Car("Honda", "Civic", 2021, "XYZ-987", false),
        new Car("Ford", "Mustang", 2022, "LMN-456", false)
    ));

    @GetMapping("/bonjour")
    public String hello(){
        return "hello";
    }

    @GetMapping("/cars")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<Car>listOfCars(){
        return cars;
        
    }

    @GetMapping("/cars/{plateNumber}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Car aCar(@PathVariable("plateNumber") String plateNumber) throws Exception {

        return cars.stream()
                .filter(car -> car.getPlateNumber().equalsIgnoreCase(plateNumber))
                .findFirst()
                .orElseThrow(() -> new Exception("Car with plate number " + plateNumber + " not found."));
    }

    @PutMapping(value = "/cars/{plateNumber}")
    @ResponseStatus(HttpStatus.OK)
    public void rentOrGetBack(
            @PathVariable("plateNumber") String plateNumber,
            @RequestParam(value = "rent", required = true) boolean rent) throws Exception {

        // Find the car by plate number
        Car car = cars.stream()
                .filter(c -> c.getPlateNumber().equalsIgnoreCase(plateNumber))
                .findFirst()
                .orElseThrow(() -> new Exception("Car with plate number " + plateNumber + " not found."));

        // Update rental status
        car.setRented(rent);
    }

        private static final Logger logger = LoggerFactory.getLogger(HelloService.class);

    @PutMapping(value = "/voiture/{plateNumber}")
    @ResponseStatus(HttpStatus.OK)
    public void rent(
            @PathVariable("plateNumber") String plateNumber,
            @RequestParam(value = "rent", required = true) boolean rent,
            @RequestBody Dates dates) {

        // Log request details
        logger.info("Plate Number: {}", plateNumber);
        logger.info("Rent: {}", rent);
        logger.info("Dates: {}", dates);

        // Implement business logic
        if (rent) {
            logger.info("Car with plate number {} has been rented from {} to {}",
                    plateNumber, dates.getStartDate(), dates.getEndDate());
        } else {
            logger.info("Car with plate number {} is no longer rented.", plateNumber);
        }
    }
    
}
