package Shopsphere.dto;

public class OrderResponseDTO {

    private Long id;
    private double totalAmount;
    private Long userId;

    public OrderResponseDTO(Long id, double totalAmount, Long userId) {
        this.id = id;
        this.totalAmount = totalAmount;
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public Long getUserId() {
        return userId;
    }
}