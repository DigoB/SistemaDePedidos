package br.com.rodrigobraz.OrderSystem.domain.enums;

public enum PaymentStatus {

    PENDING(1, "Pending"),
    PAYD(2, "Payd"),
    CANCELLED(3, "Cancelled");

    private Integer cod;
    private String description;

    PaymentStatus(Integer cod, String description) {
        this.cod = cod;
        this.description = description;
    }

    public Integer getCod() {
        return cod;
    }

    public String getDescription() {
        return description;
    }

    public static PaymentStatus toEnum(Integer cod) {
        if (cod == null)  {
            return null;
        }

        for (PaymentStatus x : PaymentStatus.values()) {
            if (cod.equals(x.getCod())) {
                return x;
            }
        }
        throw new IllegalArgumentException("Invalid id: " + cod);
    }

}
