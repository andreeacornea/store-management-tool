package ro.project.store_management_tool.helper;

import org.springframework.stereotype.Component;
import ro.project.store_management_tool.domain.entity.ProductEntity;
import ro.project.store_management_tool.model.ProductDetails;

import java.math.BigDecimal;

@Component
public class Assembler {

    public ProductEntity mapToEntity (ProductDetails product) {
        ProductEntity entity = new ProductEntity();
        entity.setName(product.getName());
        entity.setProducer(product.getProducer());
        entity.setBarcode(product.getBarcode());
        if (product.getPrice() != null)
            entity.setPrice(product.getPrice().doubleValue());
        return entity;
    }

    public ProductDetails mapEntityToProduct (ProductEntity productEntity) {
        return ProductDetails.builder()
                .name(productEntity.getName())
                .producer(productEntity.getProducer())
                .price(productEntity.getPrice() != null ? BigDecimal.valueOf(productEntity.getPrice()): null).build();
    }
}
