package motyw.art.artMotywManager.dao;

import motyw.art.artMotywManager.domain.Clothing;
import motyw.art.artMotywManager.util.ProductAvailability;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
@Transactional
@SuppressWarnings("unchecked")
public class ClothingDao {
    private final SessionFactory sessionFactory;

    @Autowired
    public ClothingDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Clothing findById(String id) {
        return getSession().get(Clothing.class, id);
    }

    public List<Clothing> getAllClothesInPriceRange(double priceMin, double priceMax) {
        return getSession().createQuery("from Clothing where price between :priceMin and :priceMax")
                .setParameter("priceMin", priceMin).setParameter("priceMax", priceMax).getResultList();
    }

    public List<Clothing> getAllSoldClothes() {
        return getSession().createQuery("from Clothing where availability=:sold")
                .setParameter("sold", ProductAvailability.SOLD).getResultList();
    }

    public List<Clothing> getAllSoldClothesForMonth(int[] monthAndYear) {
        return getSession().createQuery("from Clothing where month(saleDate)=:month and year(saleDate)=:year")
                .setParameter("month", monthAndYear[0]).setParameter("year", monthAndYear[1]).getResultList();
    }

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }

}