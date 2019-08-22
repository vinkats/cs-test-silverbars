package com.marketplace.bar.silver.com.marketplace.bar.silver.domain;

import java.util.Objects;

final public class Order {
    public enum Type { Buy, Sell }

    private final UserId userId;
    private final double quantity;
    private final double pricePerKg;
    private final Order.Type orderType;

    public Order(UserId userId, double quantity, double pricePerKg, Order.Type orderType) {
        this.userId = userId;
        this.quantity = quantity;
        this.pricePerKg = pricePerKg;
        this.orderType = orderType;
    }

    public double quantity() {
        return quantity;
    }

    public double pricePerKg() {
        return pricePerKg;
    }

    public Order.Type type() {
        return orderType;
    }

    @Override
    public String toString() {
        return "Order{" +
                "userId=" + userId +
                ", quantity=" + quantity +
                ", pricePerKg=" + pricePerKg +
                ", orderType=" + orderType +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(userId, order.userId) &&
                Objects.equals(quantity, order.quantity) &&
                Objects.equals(pricePerKg, order.pricePerKg) &&
                orderType == order.orderType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, quantity, pricePerKg, orderType);
    }
}
