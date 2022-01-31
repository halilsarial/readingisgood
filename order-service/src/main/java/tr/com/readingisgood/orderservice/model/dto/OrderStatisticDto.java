package tr.com.readingisgood.orderservice.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "Orders' Statistics model documentation", description = "The object keeps monthly order statistics")
public class OrderStatisticDto {

    @Schema(name = "Short Month Name field of order statistic object.")
    private String month;

    @Schema(name = "Monthly total order count field of order statistic object.")
    private Long totalOrderCount;

    @Schema(name = "Monthly total book that is ordered count field of order statistic object.")
    private Long totalBookCount;

    @Schema(name = "Monthly total book that is ordered total amount field of order statistic object.")
    private Double totalAmount;

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public Long getTotalOrderCount() {
        return totalOrderCount;
    }

    public void setTotalOrderCount(Long totalOrderCount) {
        this.totalOrderCount = totalOrderCount;
    }

    public Long getTotalBookCount() {
        return totalBookCount;
    }

    public void setTotalBookCount(Long totalBookCount) {
        this.totalBookCount = totalBookCount;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    @Override
    public String toString() {
        return "OrderStatisticDto{" + "totalOrderCount=" + totalOrderCount + ", totalBookCount=" + totalBookCount + ", totalAmount=" + totalAmount + '}';
    }
}
