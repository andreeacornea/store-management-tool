package ro.project.store_management_tool.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
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

    @Override
    public String toString() {
        return "ProductDetails {" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", producer='" + producer + '\'' +
                '}';
    }
}
