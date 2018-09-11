package motyw.art.artMotywManager.dao;

import motyw.art.artMotywManager.domain.Jewelery;
import motyw.art.artMotywManager.util.ProductAvailability;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;


@Repository
@Transactional
@SuppressWarnings("unchecked")
public class JeweleryDao {
    private final SessionFactory sessionFactory;

    @Autowired
    public JeweleryDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Jewelery findById(String id) {
        return getSession().get(Jewelery.class, id);
    }

    public List<Jewelery> getAllJeweleryInPriceRange(double priceMin, double priceMax) {
        return getSession().createQuery("from Jewelery where price between :priceMin and :priceMax")
                .setParameter("priceMin", priceMin).setParameter("priceMax", priceMax).getResultList();
    }

    public List<Jewelery> getAllSoldJewelery() {
        return getSession().createQuery("from Jewelery where availability=:sold")
                .setParameter("sold", ProductAvailability.SOLD).getResultList();
    }

    public List<Jewelery> getAllSoldJeweleryForMonth(int[] monthAndYear) {
        return getSession().createQuery("from Jewelery where month(saleDate)=:month and year(saleDate)=:year")
                .setParameter("month", monthAndYear[0]).setParameter("year", monthAndYear[1]).getResultList();
    }

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }
}

