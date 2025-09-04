package ro.project.store_management_tool.objectmother;

import ro.project.store_management_tool.model.ProductDetails;

import java.math.BigDecimal;

public class ProductObjectMother {

    public static ProductDetails productDetails() {
        return ProductDetails.builder()
                .name("Chocolate")
                .price(BigDecimal.valueOf(10))
                .producer("Milka").build();
    }
}
