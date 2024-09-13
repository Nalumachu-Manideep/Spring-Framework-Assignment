package controller;

import com.springdemo.controller.ProductController;
import com.springdemo.entity.Product;

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
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ProductControllerTest {
    @Mock
    private ProductService productService;


    @Mock
    private BindingResult bindingResult;

    @Mock
    private Model model;

    @InjectMocks
    private ProductController productController;

    @Before
    public void setUp(){
        //setUp
    }

    @Test
    public void testToListProducts(){
        Product product=new Product(1,"Apple Watch","Auto Location Update",19999.99,new ArrayList<>());
        List<Product> productList=new ArrayList<>();
        productList.add(product);
        when(productService.getProducts()).thenReturn(productList);
        String viewName = productController.listProducts(model);
        assertEquals("list-products", viewName);
        verify(model, times(1)).addAttribute(eq("products"), anyList());
        verify(productService, times(1)).getProducts();
    }

    @Test
    public void testShowFormForAdd(){
        String viewName=productController.showFormForAdd(model);
        assertEquals("product-form",viewName);
        verify(model,times(1)).addAttribute(eq("product"),any(Product.class));

    }

    @Test
    public void testSaveProductForSuccess(){
        Product product=new Product();
        when(bindingResult.hasErrors()).thenReturn(false);

        String viewName=productController.saveProduct(product, bindingResult,model);
        assertEquals("redirect:/product/list",viewName);
        verify(productService,times(1)).saveProduct(product);

    }

    @Test
    public void testShowFormForUpdate(){
        int theId=1;
        Product product=new Product();
        when(productService.getProduct(theId)).thenReturn(product);

        String viewName=productController.showFormForUpdate(theId,model);
        assertEquals("product-form",viewName);
        verify(model,times(1)).addAttribute("product",product);
    }

    @Test
    public void testSaveProductForFailure(){
        Product product=new Product();
        when(bindingResult.hasErrors()).thenReturn(true);


        String viewName=productController.saveProduct(product, bindingResult,model);
        assertEquals("product-form",viewName);
        verify(model).addAttribute("product",product);
        verify(productService, never()).saveProduct(product);

    }

    @Test
    public void testDeleteProduct(){
        int theId=1;

        String viewName=productController.deleteProduct(theId);
        assertEquals("redirect:/product/list",viewName);
        verify(productService).deleteProduct(theId);
    }
}
