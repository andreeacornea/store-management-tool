package ro.project.store_management_tool.domain.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ro.project.store_management_tool.domain.entity.ProductEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

    @Query("SELECT p FROM ProductEntity p WHERE p.barcode = :barcode")
    Optional<List<ProductEntity>> findByBarcode(@Param("barcode") String barcode);

    @Modifying
    @Transactional
    @Query("UPDATE ProductEntity p SET p.price = :price WHERE p.barcode = :barcode")
    int updatePriceByBarcode(@Param("barcode") String barcode,
                             @Param("price") Double price);
}
