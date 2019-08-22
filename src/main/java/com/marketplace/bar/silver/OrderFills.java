package com.marketplace.bar.silver;

import com.marketplace.bar.silver.com.marketplace.bar.silver.domain.Order;

import java.util.Map;

public class OrderFills {

    private Order.Type orderType;
    private double pricePerKg;
    private double quantity;
    private Map<Double, Double> groupByPrice;

    public OrderFills(Order.Type orderType, double pricePerKg, double quantity) {
        this.orderType = orderType;
        this.pricePerKg = pricePerKg;
        this.quantity = quantity;
    }

    public OrderFills(Order.Type key, Map<Double, Double> groupByPrice) {
        this.orderType = key;
        this.groupByPrice = groupByPrice;
    }


    public Order.Type getOrderType() {
        return orderType;
    }

    public double getPricePerKg() {
        return pricePerKg;
    }

    public Map<Double, Double> getGroupByPrice() {
        return groupByPrice;
    }

    @Override
    public String toString() {
        return "" + orderType + " " +quantity +
              " kg for £" + pricePerKg ;
    }
}
