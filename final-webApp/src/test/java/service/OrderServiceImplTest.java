package service;

import com.springdemo.dao.OrderDAO;
import com.springdemo.entity.Order;
import com.springdemo.service.OrderServiceImpl;
import org.hibernate.Hibernate;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class OrderServiceImplTest {

    @Mock
    private OrderDAO orderDAO;

    @InjectMocks
    private OrderServiceImpl orderService;

    @Before
    public void setup() {}


    @Test
    public void testGetOrders() {
        Order order1 = new Order();
        Order order2 = new Order();

        List<Order> orderList = new ArrayList<>();
        orderList.add(order1);
        orderList.add(order2);

        when(orderDAO.getOrders()).thenReturn(orderList);

        List<Order> orders = orderService.getOrders();

        assertNotNull(orders);
        assertEquals(2, orders.size());
        verify(orderDAO, times(1)).getOrders();
    }

    @Test
    public void testSaveOrder() {
        Order order = new Order();

        orderService.saveOrder(order);
        verify(orderDAO, times(1)).saveOrder(order);
    }

    @Test
    public void testDeleteOrder() {
        int orderId = 1;

        orderService.deleteOrder(orderId);
        verify(orderDAO, times(1)).deleteOrder(orderId);
    }

    @Test
    public void testGetOrderByID() {
        int orderId = 1;
        Order order = new Order();
        when(orderDAO.getOrderById(orderId)).thenReturn(order);

        Order resultOrder = orderService.getOrderByID(orderId);
        assertNotNull(resultOrder);
        verify(orderDAO, times(1)).getOrderById(orderId);
    }
}
