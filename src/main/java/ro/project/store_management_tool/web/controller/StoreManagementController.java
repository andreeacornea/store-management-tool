package ro.project.store_management_tool.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ro.project.store_management_tool.model.Error;
import ro.project.store_management_tool.model.ProductDetails;
import ro.project.store_management_tool.service.StoreManagementService;

@Slf4j
@RestController
@Validated
public class StoreManagementController {

    private StoreManagementService storeManagementService;

    @Autowired
    public StoreManagementController(StoreManagementService storeManagementService) {
        this.storeManagementService = storeManagementService;
    }

    @PostMapping (path = "product", consumes = MediaType.APPLICATION_JSON_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Add product to store")
    @ApiResponses({
            @ApiResponse(responseCode = "202", description = "Accepted"),
            @ApiResponse(responseCode = "400", description = "Bad Request", content =
            @Content(schema = @Schema(implementation = Error.class))),
            @ApiResponse(responseCode = "422", description = "Unprocessable Entity", content =
            @Content(schema = @Schema(implementation = Error.class))),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content =
            @Content(schema = @Schema(implementation = Error.class)))
    })
    public ResponseEntity<Void> addProduct (@RequestBody @Valid ProductDetails product,
                                            @Valid @NotBlank @RequestHeader(name = "TraceId") String traceId,
                                            @RequestHeader(name = "ApplicationUser", required = false) String applicationUser) {

        log.info("Add a new product operation started");
        MDC.put("data", product.toString());
        log.info("Request object logged");
        MDC.remove("data");

        storeManagementService.addProduct(product);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    @GetMapping (path = "product", consumes = MediaType.APPLICATION_JSON_VALUE,
             produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Get product by barcode")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK", content =
            @Content(schema = @Schema(implementation = ProductDetails.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request", content =
            @Content(schema = @Schema(implementation = Error.class))),
            @ApiResponse(responseCode = "422", description = "Unprocessable Entity", content =
            @Content(schema = @Schema(implementation = Error.class))),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content =
            @Content(schema = @Schema(implementation = Error.class)))
    })
    public ResponseEntity<ProductDetails> getProductByBarcode (@RequestParam @Valid @NotBlank String barcode,
                                            @Valid @NotBlank @RequestHeader(name = "TraceId") String traceId,
                                            @RequestHeader(name = "ApplicationUser", required = false) String applicationUser) {

        log.info("Retrieve product operation started");

        ProductDetails productDetails = storeManagementService.getProductByBarcode(barcode);
        return ResponseEntity.status(HttpStatus.OK).body(productDetails);
    }
}
