package az.raheddin.springbootgraphql.controller;

import az.raheddin.springbootgraphql.model.Vehicle;
import az.raheddin.springbootgraphql.model.VehicleDto;
import az.raheddin.springbootgraphql.repository.VehicleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class VehicleController {

    private final VehicleRepository vehicleRepository;

    @QueryMapping
    public List<Vehicle> getVehicles(@Argument("type") String type) {
        return vehicleRepository.getByTypeLike(type);
    }

    @QueryMapping
    public List<Vehicle> getAllVehicles() {
        return vehicleRepository.findAll();
    }

    @QueryMapping
    public Optional<Vehicle> getVehicle(@Argument("id") Long id) {
        return vehicleRepository.findById(Long.valueOf(id));
    }

    @SchemaMapping(typeName = "Query",field = "getVehiclesNew")
    public List<Vehicle>  getVehicleWithBrandName(@Argument("type") String brandName) {
        return vehicleRepository.getByBrandNameLike(brandName);
    }

    @MutationMapping
    public Vehicle createVehicle(@Argument("vehicle") VehicleDto vehicle) {
        return vehicleRepository.save(dtoToEntity(vehicle));
    }

    private Vehicle dtoToEntity(VehicleDto vehicleDto) {
        Vehicle vehicle = new Vehicle();
        vehicle.setBrandName(vehicleDto.getBrandName());
        vehicle.setModelCode(vehicleDto.getModelCode());
        vehicle.setType(vehicleDto.getType());
        vehicle.setLunchDate(new Date());
        return vehicle;
    }

}
