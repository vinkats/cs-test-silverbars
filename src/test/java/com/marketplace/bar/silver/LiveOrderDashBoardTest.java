package com.marketplace.bar.silver;

import com.marketplace.bar.silver.com.marketplace.bar.silver.domain.Order;
import com.marketplace.bar.silver.com.marketplace.bar.silver.domain.UserId;
import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Collections;

public class LiveOrderDashBoardTest {

    private LiveOrderDashBoard liveOrderDashBoard;

    @Before
    public void setUp() throws Exception {
        liveOrderDashBoard = new LiveOrderDashBoard();
    }

    @Test
    public void should_start_with_no_orders_displayed() {

        Assert.assertThat(liveOrderDashBoard.getSummaryInformation(), Is.is(Collections.emptyList()));
    }

    @Test
    public void should_display_a_registered_order() {
        Order fifthOrder = new Order(
                new UserId("some id4"),
                3.5,
                100,
                Order.Type.Sell
        );
        Order firstOrder = new Order(
                new UserId("some id121"),
                3.5,
                306,
                Order.Type.Buy
        );
        Order firstOrder1 = new Order(
                new UserId("some id32442"),
                3.5,
                306,
                Order.Type.Buy
        );
        liveOrderDashBoard.register(fifthOrder);
        liveOrderDashBoard.register(firstOrder);
        liveOrderDashBoard.register(firstOrder1);
        Order secondOrder = new Order(
                new UserId("some id1232321"),
                3.5,
                306,
                Order.Type.Buy
        );
        Order thirdOrder = new Order(
                new UserId("some id32131"),
                2.5,
                300,
                Order.Type.Sell
        );
        Order thirdOrder1 = new Order(
                new UserId("some id32131"),
                2.5,
                300,
                Order.Type.Sell
        );
        Order fourthOrder = new Order(
                new UserId("some id4213121"),
                3.5,
                100,
                Order.Type.Buy
        );

        liveOrderDashBoard.register(secondOrder);
        liveOrderDashBoard.register(thirdOrder);
        liveOrderDashBoard.register(thirdOrder1);
        liveOrderDashBoard.register(fourthOrder);
        Assert.assertThat(liveOrderDashBoard.getSummaryInformation().size(), Is.is(4));
    }

}
