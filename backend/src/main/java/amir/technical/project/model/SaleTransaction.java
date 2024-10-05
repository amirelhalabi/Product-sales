package amir.technical.project.model;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class SaleTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JsonBackReference
    private Sale sale;

    @ManyToOne
    private Product product;

    private Integer quantity;
    private Double price;
    @Override
    public String toString() {
        return "SaleTransaction{" +
                "id=" + id +
                ", product=" + (product != null ? product.getId() : "null") + // Avoid full product info
                ", quantity=" + quantity +
                ", price=" + price +
                '}';
    }
}
