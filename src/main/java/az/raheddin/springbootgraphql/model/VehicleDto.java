package az.raheddin.springbootgraphql.model;

import lombok.Data;

@Data
public class VehicleDto {
    private String type;
    private String modelCode;
    private String brandName;
}
