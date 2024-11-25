package az.myecommerceapp.entity;

import az.myecommerceapp.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Orders")
@FieldDefaults(level= AccessLevel.PRIVATE)
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    User user;

    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL)
    Payment payment;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    Set<OrderItem> orderItems;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    OrderStatus status;

    @Column(nullable = false)
    BigDecimal totalAmount;

    @Column(nullable = false, updatable = false)
    LocalDateTime createdAt;

    @Column(nullable = false)
    LocalDateTime updatedAt;

    private void calculateAndSetTotalAmount() {
        BigDecimal sum = BigDecimal.ZERO;
        if (orderItems != null) {
            for (OrderItem orderItem : orderItems) {
                sum = sum.add(orderItem.getTotalPrice());
            }
        }
        this.totalAmount = sum;
    }

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        calculateAndSetTotalAmount();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
        calculateAndSetTotalAmount();
    }
}
