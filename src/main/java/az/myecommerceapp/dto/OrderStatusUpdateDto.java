package az.myecommerceapp.dto;

import az.myecommerceapp.enums.OrderStatus;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderStatusUpdateDto {
    OrderStatus status;
}
