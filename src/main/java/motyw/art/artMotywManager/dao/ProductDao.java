package motyw.art.artMotywManager.dao;

import motyw.art.artMotywManager.domain.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional

public class ProductDao {
    private final SessionFactory sessionFactory;

    @Autowired
    public ProductDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Product findById(String id) {
        return getSession().get(Product.class, id);
    }

    public List<Product> getListOfAllProducts() {
        return getSession().createQuery("from Product", Product.class).getResultList();
    }

    public void saveOrUpdateProduct(Product product) {
        getSession().saveOrUpdate(product);
    }

    public void deleteProduct(Product product) {
        getSession().delete(product);
    }

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }
}
