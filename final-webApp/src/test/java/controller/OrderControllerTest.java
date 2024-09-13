package controller;

import com.springdemo.controller.OrderController;
import com.springdemo.entity.Order;
import com.springdemo.entity.Product;
import com.springdemo.service.OrderService;
import com.springdemo.service.ProductService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class OrderControllerTest {

    @Mock
    private OrderService orderService;

    @Mock
    private ProductService productService;

    @Mock
    private BindingResult bindingResult;

    @Mock
    private Model model;

    @InjectMocks
    private OrderController orderController;

    @Before
    public void setUp() {
        //setup
    }

    @Test
    public void testListOrders() {
        Order order = new Order();
        List<Order> orders = new ArrayList<>();
        orders.add(order);

        when(orderService.getOrders()).thenReturn(orders);

        String viewName = orderController.listOrders(model);
        assertEquals("list-orders", viewName);
        verify(model, times(1)).addAttribute(eq("orders"), anyList());
        verify(orderService, times(1)).getOrders();
    }

    @Test
    public void testShowFormForAdd() {
        String viewName = orderController.showFormForAdd(model);
        assertEquals("order-form", viewName);
        verify(model, times(1)).addAttribute(eq("order"), any(Order.class));
    }

    @Test
    public void testSaveOrderForSuccess() {
        Order order = new Order();
        when(bindingResult.hasErrors()).thenReturn(false);

        String viewName = orderController.saveOrder(order, bindingResult, model);
        assertEquals("redirect:list", viewName);
        verify(orderService, times(1)).saveOrder(order);
    }

    @Test
    public void testSaveOrderForFailure() {
        Order order = new Order();
        when(bindingResult.hasErrors()).thenReturn(true);

        String viewName = orderController.saveOrder(order, bindingResult, model);
        assertEquals("order-form", viewName);
        verify(model).addAttribute("order", order);
        verify(orderService, never()).saveOrder(order);
    }

    @Test
    public void testDeleteOrder() {
        int orderId = 1;

        String viewName = orderController.deleteOrder(orderId);
        assertEquals("redirect:/order/list", viewName);
        verify(orderService, times(1)).deleteOrder(orderId);
    }

    @Test
    public void testShowOrderProducts() {
        int orderId = 1;
        Order order = new Order();
        when(orderService.getOrderByID(orderId)).thenReturn(order);

        String viewName = orderController.showOrderProducts(orderId, model);
        assertEquals("order-products", viewName);
        verify(model, times(1)).addAttribute("order", order);
    }

    @Test
    public void testEditOrderProducts() {
        int orderId = 1;
        Order order = new Order();
        List<Product> allProducts = new ArrayList<>();
        when(orderService.getOrderByID(orderId)).thenReturn(order);
        when(productService.getProducts()).thenReturn(allProducts);

        String viewName = orderController.editOrderProducts(orderId, model);
        assertEquals("view-order-products", viewName);
        verify(model, times(1)).addAttribute("order", order);
        verify(model, times(1)).addAttribute("allProducts", allProducts);
    }

    @Test
    public void testSaveOrderProducts() {
        int orderId = 1;
        List<Integer> productIds = Arrays.asList(1, 2, 3);
        Order order = new Order();
        List<Product> selectedProducts = new ArrayList<>();
        when(orderService.getOrderByID(orderId)).thenReturn(order);
        when(productService.getProductsByIds(productIds)).thenReturn(selectedProducts);

        String viewName = orderController.saveOrderProducts(orderId, productIds);
        assertEquals("redirect:/order/list", viewName);
        verify(orderService, times(1)).saveOrder(order);
    }
}
