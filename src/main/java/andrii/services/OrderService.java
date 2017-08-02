package andrii.services;

import andrii.dao.OrderContentDAO;
import andrii.dao.OrderDAO;
import andrii.dto.CartDTO;
import andrii.dto.OrderContentDTO;
import andrii.dto.OrderDTO;
import andrii.entities.Order;
import andrii.entities.OrderContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;

    @Transactional
    public void saveOrder(OrderDTO orderDTO) {
        Order order = orderDTO.convertToEntity();

        order.setUser(userService.getUserByEmail(userService.getCurrentUser().getUsername()));
        order.setPrice(cartService.sum());

        orderDAO.save(order);
        cartService.getCartSet(false)
                .stream()
                .map(cartDTO -> createOrderContent(cartDTO, order))
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

    @Transactional
    public List<OrderDTO> getUserOrders() {
        String userEmail = userService.getCurrentUser().getUsername();
        List<OrderDTO> orderDTOList = convertToDTOList(orderDAO.getOrders(userEmail));
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
    public List<OrderContentDTO> getOrderContentList(Integer orderId, boolean loadPhoto) {
        List<OrderContentDTO> orderContentDTOList = convertToContentDTOList(orderContentDAO.getObjects(orderId));
        if (loadPhoto) {
            orderContentDTOList.forEach(item -> {
                String photoBase64 = productService.loadPhoto(item.getProduct().getId());
                item.getProduct().setPhoto(photoBase64);
            });
        }
        return orderContentDTOList;
    }

    @Transactional
    public void changeOrderStatus(OrderDTO orderDTO) {
        orderDAO.updateOrderStatus(orderDAO.getOrder(orderDTO.getId()));
    }

    @Transactional
    public OrderContent createOrderContent(CartDTO cartDTO, Order order){
        OrderContent orderContent = new OrderContent();
        orderContent.setOrder(order);
        orderContent.setProduct(productService.getProductById(cartDTO.getId()));
        orderContent.setQuantity(cartDTO.getQuantity());
        return orderContent;
    }

}
