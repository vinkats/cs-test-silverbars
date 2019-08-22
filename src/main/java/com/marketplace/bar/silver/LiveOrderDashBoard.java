package com.marketplace.bar.silver;

import com.marketplace.bar.silver.com.marketplace.bar.silver.domain.Order;
import com.marketplace.bar.silver.com.marketplace.bar.silver.domain.UserId;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Function;

import static java.util.stream.Collectors.*;

public class LiveOrderDashBoard {
    private List<Order> orderFills = null;

    public LiveOrderDashBoard() {
        orderFills = new CopyOnWriteArrayList<>();
    }

    public static void main(String[] args) {
        LiveOrderDashBoard liveOrderDashBoard = new LiveOrderDashBoard();
        Order fifthOrder = new Order(
                new UserId("some id4"),
                3.5,
                100,
                Order.Type.Sell
        );
        Order firstOrder = new Order(
                new UserId("some id"),
                3.5,
                306,
                Order.Type.Buy
        );
        Order firstOrder1 = new Order(
                new UserId("some id"),
                3.5,
                306,
                Order.Type.Buy
        );
        liveOrderDashBoard.register(firstOrder);
        liveOrderDashBoard.register(firstOrder1);
        liveOrderDashBoard.register(fifthOrder);
        Order secondOrder = new Order(
                new UserId("some id1"),
                3.5,
                306,
                Order.Type.Buy
        );
        Order thirdOrder = new Order(
                new UserId("some id3"),
                2.5,
                300,
                Order.Type.Sell
        );
        Order thirdOrder1 = new Order(
                new UserId("some id3"),
                1.3,
                300,
                Order.Type.Sell
        );
        Order fourthOrder = new Order(
                new UserId("some id4"),
                3.5,
                100,
                Order.Type.Buy
        );

        liveOrderDashBoard.register(secondOrder);
        liveOrderDashBoard.register(thirdOrder);
        liveOrderDashBoard.register(thirdOrder1);
        liveOrderDashBoard.register(fourthOrder);
        liveOrderDashBoard.getSummaryInformation();
    }


    public void register(Order order) {
        orderFills.add(order);
    }

    public void cancel(Order order) {
        orderFills.remove(order);
    }

    public List<OrderFills> getSummaryInformation() {

        List<OrderFills> orderFills = new ArrayList<>();
        this.orderFills.stream().
                collect(groupingBy(Order::type, groupingBy(Order::pricePerKg, summingDouble(Order::quantity)))).
                entrySet().stream().map(x -> {
                    return new OrderFills(x.getKey(), x.getValue());
        }).collect(toList()).stream().forEach(y -> {
                y.getGroupByPrice().entrySet().forEach(z -> {
                    orderFills.add(new OrderFills(y.getOrderType(), z.getKey(), z.getValue()));
                });
        });
        orderFills.sort(new Comparator<OrderFills>() {
            @Override
            public int compare(OrderFills o1, OrderFills o2) {
                if (o1.getOrderType() != o2.getOrderType()) {
                    return o1.getOrderType().compareTo(o2.getOrderType());
                }

                if (o1.getOrderType() == Order.Type.Sell) {
                    return Double.valueOf(o1.getPricePerKg()).compareTo(Double.valueOf(o2.getPricePerKg()));
                }

                if (o1.getOrderType() == Order.Type.Buy) {
                    return Double.valueOf(o2.getPricePerKg()).compareTo(Double.valueOf(o1.getPricePerKg()));
                }

                return 0;
            }
        });
        orderFills.stream().forEach(System.out::println);
        return orderFills;
    }

    private Function<Map.Entry<Order.Type, Map<Double, Double>>, OrderFills> toOrderSummary() {
//        double totalevent = myList.stream().collect(summingDouble(f -> f));
        return entry -> new OrderFills(entry.getKey(), entry.getValue());
    }

}
