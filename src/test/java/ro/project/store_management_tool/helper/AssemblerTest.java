package ro.project.store_management_tool.helper;

import org.junit.jupiter.api.Test;
import ro.project.store_management_tool.domain.entity.ProductEntity;
import ro.project.store_management_tool.model.ProductDetails;
import ro.project.store_management_tool.objectmother.EntityObjectMother;
import ro.project.store_management_tool.objectmother.ProductObjectMother;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AssemblerTest {

    @Test
    public void assembleProductToEntity() {
        ProductDetails productDetails = ProductObjectMother.productDetails();
        Assembler assembler = new Assembler();
        ProductEntity mappedProductEntity = assembler.mapToEntity(productDetails);
        ProductEntity expectedProductEntity = EntityObjectMother.productEntity();

        assertEquals(expectedProductEntity.getName(), mappedProductEntity.getName());
        assertEquals(expectedProductEntity.getPrice(), mappedProductEntity.getPrice());
    }
}
