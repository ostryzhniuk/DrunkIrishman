package andrii.services;

import andrii.dao.OrderContentDAO;
import andrii.dao.OrderDAO;
import andrii.dto.OrderContentDTO;
import andrii.dto.OrderDTO;
import andrii.entities.Order;
import andrii.entities.OrderContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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

    @Transactional
    public List<OrderDTO> getOrders(OrderDTO.Status status) {
        List<OrderDTO> orderDTOList = convertToDTOList(orderDAO.getOrders(OrderDTO.convertToEntityStatus(status)));
        orderDTOList.sort(Comparator.comparing(OrderDTO::getDate));
        return orderDTOList;
    }

    public List<OrderDTO> convertToDTOList(List<Order> orderList) {
        return orderList
                .stream()
                .map(order -> OrderDTO.convertToDTO(order))
                .collect(Collectors.toList());
    }

    public List<OrderContentDTO> convertToContentDTOList(List<OrderContent> orderContentList) {
        return orderContentList
                .stream()
                .map(orderContent -> OrderContentDTO.convertToDTO(orderContent))
                .collect(Collectors.toList());
    }

    @Transactional
    public void changeOrderStatus(OrderDTO orderDTO) {
        orderDAO.updateOrderStatus(orderDTO.convertToEntity());
    }

    @Transactional
    public List<OrderContentDTO> getOrderContentList(Integer orderId) {
        List<OrderContent> orderContentList = orderContentDAO.getObjects(orderId);
        return convertToContentDTOList(orderContentList);
    }

}
