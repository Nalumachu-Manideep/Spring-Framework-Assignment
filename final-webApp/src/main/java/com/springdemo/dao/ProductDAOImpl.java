package com.springdemo.dao;

import com.springdemo.entity.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductDAOImpl implements ProductDAO {

    //need to inject the session factory
    private SessionFactory sessionFactory;
    @Autowired
    public ProductDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Product> getProducts() {
        //get yhe current hibernate session
        Session currentSession= sessionFactory.getCurrentSession();
        //create the query
        Query<Product> theQuery=
                currentSession.createQuery("from Product order by price",
                        Product.class);
        //execute the query and get result list
        List<Product> products=theQuery.getResultList();
        //return the results
        return products;
    }

    @Override
    public void saveProduct(Product theProduct) {
        //get current hibernate session
        Session currentSession=sessionFactory.getCurrentSession();
        //save/Update the customer
        currentSession.saveOrUpdate(theProduct);

    }

    @Override
    public Product getProduct(int theId) {
        Session currentSession=sessionFactory.getCurrentSession();
        Product theProduct=currentSession.get(Product.class,theId);

        return theProduct;
    }

    @Override
    public void deleteProduct(int id) {
        Session currentSession=sessionFactory.getCurrentSession();

        Query theQuery=currentSession.createQuery("delete from Product where id = :id");
        theQuery.setParameter("id",id);
        theQuery.executeUpdate();

    }

    @Override
    public List<Product> getProductsByIds(List<Integer> ids) {
        Session session=sessionFactory.getCurrentSession();
        Query<Product> query=session.createQuery("from Product where id in :ids",Product.class);
        query.setParameter("ids",ids);
        List<Product> products=query.getResultList();
        return products;
    }
}
