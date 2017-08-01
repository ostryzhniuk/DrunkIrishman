package andrii.services;

import andrii.dao.OrderContentDAO;
import andrii.dao.OrderDAO;
import andrii.dto.OrderDTO;
import andrii.entities.Order;
import andrii.entities.OrderContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderService {

    @Autowired
    private OrderDAO orderDAO;

    @Autowired
    private OrderContentDAO orderContentDAO;

    @Autowired
    private CartService cartService;

    @Transactional
    public void saveOrder(OrderDTO orderDTO) {
        Order order = orderDTO.convertToEntity();

        order.setPrice(cartService.sum());

        orderDAO.save(order);
        cartService.getCartSet(false)
                .stream()
                .map(cartDTO -> OrderContent.createOrderContent(cartDTO, order))
                .forEach(this::saveOrderContent);

        cartService.clearCart();
    }

    @Transactional
    public void saveOrderContent(OrderContent orderContent) {
        orderContentDAO.save(orderContent);
    }



}
