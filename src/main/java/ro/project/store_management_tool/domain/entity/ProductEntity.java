package ro.project.store_management_tool.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Table(
        name = "PRODUCTS",
        uniqueConstraints = {
                @UniqueConstraint(name = "uc_product_barcode", columnNames = {"barcode"})
        }
)
public class ProductEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Double price;
    private String producer;
    private String barcode;
}
