package andrii.dao;

import andrii.entities.Stock;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class StockDao extends GenericDao<Stock> {

    @Override
    public void save(Stock stock) {

    }

    @Override
    public List<Stock> getObjects() {
        return getSession().createQuery("from Stock")
                .list();
    }

    @Override
    public void update(Stock stock) {

    }

    @Override
    public void delete(Stock stock) {

    }
}
