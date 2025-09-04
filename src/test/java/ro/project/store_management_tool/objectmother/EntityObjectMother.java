package ro.project.store_management_tool.objectmother;

import ro.project.store_management_tool.domain.entity.ProductEntity;

public class EntityObjectMother {

    public static ProductEntity productEntity() {
        return ProductEntity.builder()
                .name("Chocolate")
                .price(Double.valueOf(10))
                .producer("Milka").build();
    }
}
