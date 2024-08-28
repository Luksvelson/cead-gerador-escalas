package org.example.services;

import org.example.domain.Product;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class ProductService {

    @PersistenceContext
    private EntityManager em;

    public Product findProduct(Long id) {
        return em.find(Product.class, id);
    }

    public void createProduct(Product product) {
        em.persist(product);
    }

    public void updateProduct(Product product) {
        em.merge(product);
    }

    public void deleteProduct(Long id) {
        Product product = findProduct(id);
        if (product != null) {
            em.remove(product);
        }
    }

    public List<Product> listAllProducts() {
        return em.createQuery("SELECT p FROM Product p", Product.class).getResultList();
    }
}
