package az.myecommerceapp.entity;

import az.myecommerceapp.enums.Role;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.List;


@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Users")
@FieldDefaults(level=AccessLevel.PRIVATE)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @Column(nullable = false, unique = true)
    String userName;

    @Column(nullable = false)
    String password;

    @Transient
    String repeatPassword;

    @Column(nullable = false)
    String email;

    Role role = Role.CUSTOMER;

    @OneToMany(mappedBy = "user")
    List<Order> orders;

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

