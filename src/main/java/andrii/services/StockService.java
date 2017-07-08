package andrii.services;

import andrii.dao.StockDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StockService {

    @Autowired
    private StockDao stockDao;

    public String getFirstStockStatus() {
        return stockDao.getObjects().get(0).getStatus().name();
    }
}
