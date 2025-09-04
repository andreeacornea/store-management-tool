package ro.project.store_management_tool.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ro.project.store_management_tool.model.ProductDetails;
import ro.project.store_management_tool.objectmother.ProductObjectMother;
import ro.project.store_management_tool.service.StoreManagementService;
import ro.project.store_management_tool.web.controller.StoreManagementController;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class StoreManagementControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private StoreManagementService storeManagementService;

    @InjectMocks
    private StoreManagementController controller;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    void addProduct_success_returnsAccepted() throws Exception {
        ProductDetails validProduct = ProductObjectMother.productDetails();
        Mockito.doNothing().when(storeManagementService).addProduct(validProduct);

        mockMvc.perform(post("/product")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(validProduct))
                        .header("TraceId", "12345")
                        .header("ApplicationUser", "user"))
                .andExpect(status().isAccepted());
    }

    @Test
    void addProduct_missingTraceId_returnsBadRequest() throws Exception {
        ProductDetails validProduct = ProductObjectMother.productDetails();
        mockMvc.perform(post("/product")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(validProduct))
                        .header("ApplicationUser", "user"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void addProduct_invalidBody_returnsBadRequest() throws Exception {
        ProductDetails invalidProduct = new ProductDetails();
        invalidProduct.setPrice(BigDecimal.valueOf(10));

        mockMvc.perform(post("/product")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invalidProduct))
                        .header("TraceId", "12345")
                        .header("ApplicationUser", "user"))
                .andExpect(status().isBadRequest());
    }
}