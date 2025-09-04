package ro.project.store_management_tool.service;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import ro.project.store_management_tool.domain.entity.ProductEntity;
import ro.project.store_management_tool.domain.repository.ProductRepository;
import ro.project.store_management_tool.exception.DbException;
import ro.project.store_management_tool.exception.NotFoundException;
import ro.project.store_management_tool.helper.Assembler;
import ro.project.store_management_tool.model.ProductDetails;

import java.util.List;
import java.util.Optional;

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
        } catch (DataIntegrityViolationException e) {
            log.error("Exception occured: ", e.getMessage());
            throw new DbException("A product with the same barcode already exists");
        } catch (Exception e) {
            log.error("Exception occured: ", e.getMessage());
            throw new DbException("Database exception occurred while saving product");
        }
    }

    public ProductDetails getProductByBarcode(String barcode) {
        Optional<List<ProductEntity>> optionalProductEntityList =
                productRepository.findByBarcode(barcode);

        MDC.put("data",optionalProductEntityList.toString());
        log.info("Log returned entity from DB");
        MDC.remove("data");

        List<ProductDetails> productDetailsList = optionalProductEntityList
                .map(list -> list.stream()
                        .map(assembler::mapEntityToProduct)
                        .toList())
                .orElse(List.of());

        if (productDetailsList.size() > 1) {
            throw new DbException("Too many products found for barcode: " + barcode);
        } else if (productDetailsList.isEmpty()) {
            throw new NotFoundException("Product not found for barcode: " + barcode);
        } else {
            return productDetailsList.get(0);
        }
    }
}

