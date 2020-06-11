package com.example.orders;

public class Order {

    long orderId;
    String orderName;
    Integer orderAmount;

    public Order(long orderId, String orderName, Integer orderAmount) {
        this.orderId = orderId;
        this.orderName = orderName;
        this.orderAmount = orderAmount;
    }

    public Integer getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(Integer orderAmount) {
        this.orderAmount = orderAmount;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public long getOrderId() {
        return orderId;
    }

    public String getOrderName() {
        return orderName;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Order{");
        sb.append("orderId=").append(orderId);
        sb.append(", orderName='").append(orderName).append('\'');
        sb.append(", orderAmount=").append(orderAmount);
        sb.append('}');
        return sb.toString();
    }
}
