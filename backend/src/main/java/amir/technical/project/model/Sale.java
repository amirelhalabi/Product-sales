package amir.technical.project.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime creationDate = LocalDateTime.now();

    @ManyToOne
    private Client client;

    @ManyToOne
    private Client seller;

    private Double total;

    @OneToMany(mappedBy = "sale", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<SaleTransaction> transactions = new ArrayList<>();

    @Override
    public String toString() {
        return "Sale{" +
                "id=" + id +
                ", creationDate=" + creationDate +
                ", client=" + (client != null ? client.getId() : "null") + // Avoid full client info
                ", seller=" + (seller != null ? seller.getId() : "null") + // Avoid full seller info
                ", total=" + total +
                ", transactionsCount=" + (transactions != null ? transactions.size() : 0) + // Print only the count
                '}';
    }

}
