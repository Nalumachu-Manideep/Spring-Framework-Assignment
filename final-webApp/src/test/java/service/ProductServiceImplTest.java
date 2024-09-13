package service;

import com.springdemo.dao.ProductDAO;
import com.springdemo.entity.Product;
import com.springdemo.service.ProductServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceImplTest {

    @Mock
    private ProductDAO productDAO;



    @InjectMocks
    private ProductServiceImpl productService;


    @Before
    public void setup(){
        //setUp
    }

    @Test
    public void testGetProducts(){
        Product product1=new Product();
        Product product2=new Product();

        List<Product> productList=new ArrayList<>();
        productList.add(product1);
        productList.add(product2);

        when(productDAO.getProducts()).thenReturn(productList);

        List<Product>   products=productService.getProducts();
        assertNotNull(products);
        assertEquals(2,products.size());
        verify(productDAO,times(1)).getProducts();
    }

    @Test
    public void testSaveProduct(){
        Product product=new Product();
        productService.saveProduct(product);

        verify(productDAO, times(1)).saveProduct(product);

    }

    @Test
    public void testGetProduct(){
        Product product=new Product();
        int theId=1;
        when(productDAO.getProduct(theId)).thenReturn(product);

        Product result=productService.getProduct(theId);

        assertNotNull(result);
        verify(productDAO,times(1)).getProduct(theId);
    }

    @Test
    public void testDeleteProduct(){

        int theId=1;
        productService.deleteProduct(theId);
        verify(productDAO,times(1)).deleteProduct(theId);
    }

    @Test
    public void testGetProductsByIds(){
        List<Integer> list= Arrays.asList(1,2);
        List<Product> products=Arrays.asList(new Product(),new Product());
        when(productDAO.getProductsByIds(list)).thenReturn(products);

        List<Product> productList=productService.getProductsByIds(list);
        assertNotNull(productList);
        assertEquals(2,productList.size());
        verify(productDAO,times(1)).getProductsByIds(list);
    }
}
