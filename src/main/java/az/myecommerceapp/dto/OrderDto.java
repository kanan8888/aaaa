package az.myecommerceapp.dto;

import az.myecommerceapp.entity.OrderItem;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import java.util.Set;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderDto {
    Long userId;
    Set<OrderItem> orderItems;
    double totalAmount;
}
