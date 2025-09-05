package ro.project.store_management_tool.domain;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import ro.project.store_management_tool.domain.entity.ProductEntity;
import ro.project.store_management_tool.domain.repository.ProductRepository;
import ro.project.store_management_tool.objectmother.EntityObjectMother;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class ProductRepositoryTest {

    private ProductRepository productRepository;

    @Autowired
    public ProductRepositoryTest(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Test
    void saveProduct_success() {
        ProductEntity productEntity = EntityObjectMother.productEntity();
        ProductEntity savedProductEntity = productRepository.save(productEntity);

        assertThat(savedProductEntity.getName()).isEqualTo(productEntity.getName());
        assertThat(savedProductEntity.getPrice()).isEqualTo(productEntity.getPrice());
        assertThat(productRepository.findById(savedProductEntity.getId())).isPresent();
    }
}
