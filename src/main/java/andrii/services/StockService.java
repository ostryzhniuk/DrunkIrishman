package andrii.services;

import andrii.dao.StockDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StockService {

    @Autowired
    private StockDAO stockDAO;

    @Transactional
    public String getFirstStockStatus() {
        return stockDAO.getObjects().get(0).getStatus().name();
    }
}
