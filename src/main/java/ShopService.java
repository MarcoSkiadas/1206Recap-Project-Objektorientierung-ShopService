import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor


public class ShopService {
    private ProductRepo productRepo = new ProductRepo();
    private OrderRepo orderRepo = new OrderMapRepo();
    private IdService idService;

    public Order addOrder(List<String> productIds) throws Exception{
        List<Product> products = new ArrayList<>();
        for (String productId : productIds) {
            Optional<Product> productToOrder = productRepo.getProductById(productId);
            if (productToOrder.isEmpty()) {
                throw new Exception("Product mit der Id: " + productId+ " existiert nicht!");
            }
            products.add(productToOrder.get());
        }

        Order newOrder = new Order(UUID.randomUUID().toString(), products,OrderStatus.PROCESSING, ZonedDateTime.now());

        return orderRepo.addOrder(newOrder);
    }
    public List<Order> getOrders() {
        return orderRepo.getOrders().stream()
                .sorted()
                .distinct()
                .toList();
    }
    public Order updateOrder(String orderId, OrderStatus orderStatus) throws Exception{
        Order order = orderRepo.getOrderById(orderId);
        if (order == null) {
            throw new Exception("Order mit Id: " + orderId + " existiert nicht!");
        }
        Order newOrder = order.withStatus(orderStatus);
        return orderRepo.addOrder(newOrder);
    }

}
