package az.myecommerceapp.entity;

import az.myecommerceapp.enums.ProductStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Products")
@FieldDefaults(level= AccessLevel.PRIVATE)
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;

    @Column(nullable = false)
    String name;

    @Column(length = 500)
    private String description;

    @Column(nullable = false)
    BigDecimal price;

    @Column(nullable = false)
    Integer stock;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    Category category;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    ProductStatus status;

    LocalDateTime createdAt;

    LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
