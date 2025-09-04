package ro.project.store_management_tool.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductDetails {
    @NotBlank(message = "Name is mandatory to be mentioned")
    @Schema(requiredMode = Schema.RequiredMode.REQUIRED,
    description = "Product name")
    String name;
    @NotNull(message = "Price is mandatory")
    @Schema(requiredMode = Schema.RequiredMode.REQUIRED,
            description = "Product price")
    BigDecimal price;
    @Schema(requiredMode = Schema.RequiredMode.NOT_REQUIRED,
            description = "Product producer")
    String producer;
    @NotBlank(message = "Barcode is mandatory to be mentioned")
    @Schema(requiredMode = Schema.RequiredMode.REQUIRED,
            description = "Product barcode")
    String barcode;
}
