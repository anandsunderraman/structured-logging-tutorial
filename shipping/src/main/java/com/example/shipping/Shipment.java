package com.example.shipping;

public class Shipment {

    long orderId;

    public Shipment() {
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Shipment{");
        sb.append("orderId=").append(orderId);
        sb.append('}');
        return sb.toString();
    }
}
