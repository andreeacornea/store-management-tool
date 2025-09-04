package ro.project.store_management_tool.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.project.store_management_tool.domain.entity.ProductEntity;
import ro.project.store_management_tool.domain.repository.ProductRepository;
import ro.project.store_management_tool.helper.Assembler;
import ro.project.store_management_tool.model.ProductDetails;

@Service
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
        productRepository.save(productEntity);
    }
}
