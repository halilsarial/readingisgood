package tr.com.readingisgood.orderservice.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Schema(name = "Order Item model documentation", description = "Placing order is done with this object")
public class OrderItemDto {

    @Schema(name = "Book that is placed order id field of order item object.")
    @NotBlank
    private String bookId;

    @Schema(name = "Book that is placed order count field of order item object.")
    @Min(1)
    private Long quantity;

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }
}
