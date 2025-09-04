package ro.project.store_management_tool.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ro.project.store_management_tool.domain.entity.ProductEntity;
import ro.project.store_management_tool.domain.repository.ProductRepository;
import ro.project.store_management_tool.exception.DbException;
import ro.project.store_management_tool.helper.Assembler;
import ro.project.store_management_tool.model.ProductDetails;
import ro.project.store_management_tool.objectmother.EntityObjectMother;
import ro.project.store_management_tool.objectmother.ProductObjectMother;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class StoreManagementServiceTest {

    @Mock
    private Assembler assembler;

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private StoreManagementService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void addProduct_success() {
        ProductDetails validProduct = ProductObjectMother.productDetails();
        ProductEntity mappedEntity = EntityObjectMother.productEntity();

        when(assembler.mapToEntity(validProduct)).thenReturn(mappedEntity);
        when(productRepository.save(mappedEntity)).thenReturn(mappedEntity);
        service.addProduct(validProduct);

        verify(productRepository, times(1)).save(mappedEntity);
    }

    @Test
    void addProduct_dbException_throwsDbException() {
        ProductDetails validProduct = ProductObjectMother.productDetails();
        ProductEntity mappedEntity = EntityObjectMother.productEntity();

        when(assembler.mapToEntity(validProduct)).thenReturn(mappedEntity);
        doThrow(new RuntimeException("DB failure")).when(productRepository).save(mappedEntity);

        assertThrows(DbException.class, () -> service.addProduct(validProduct));
    }
}
