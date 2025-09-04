package ro.project.store_management_tool.service;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.project.store_management_tool.domain.entity.ProductEntity;
import ro.project.store_management_tool.domain.repository.ProductRepository;
import ro.project.store_management_tool.exception.DbException;
import ro.project.store_management_tool.helper.Assembler;
import ro.project.store_management_tool.model.ProductDetails;

@Service
@Slf4j
public class StoreManagementService {

    private final Assembler assembler;
    private final ProductRepository productRepository;

    @Autowired
    public StoreManagementService(Assembler assembler, ProductRepository productRepository) {
        this.assembler = assembler;
        this.productRepository = productRepository;
    }

    public void addProduct(ProductDetails product) {
        ProductEntity productEntity = assembler.mapToEntity(product);

        MDC.put("data",productEntity.toString());
        log.info("After mapping product to entity");
        MDC.remove("data");

        try {
            productRepository.save(productEntity);
            log.info("Successfully inserted payment to DB");
        } catch (Exception e) {
            log.error("Exception occured: ", e.getMessage());
            throw new DbException("Database exception occured while saving product");
        }

    }
}
