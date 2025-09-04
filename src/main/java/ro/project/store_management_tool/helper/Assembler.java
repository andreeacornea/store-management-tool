package ro.project.store_management_tool.helper;

import org.springframework.stereotype.Component;
import ro.project.store_management_tool.domain.entity.ProductEntity;
import ro.project.store_management_tool.model.ProductDetails;

@Component
public class Assembler {

    public ProductEntity mapToEntity (ProductDetails product) {
        ProductEntity entity = new ProductEntity();
        entity.setName(product.getName());
        entity.setProducer(product.getProducer());
        if (product.getPrice() != null)
            entity.setPrice(product.getPrice().doubleValue());
        return entity;
    }
}
