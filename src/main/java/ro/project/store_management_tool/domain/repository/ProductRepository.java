package ro.project.store_management_tool.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.project.store_management_tool.domain.entity.ProductEntity;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
}
