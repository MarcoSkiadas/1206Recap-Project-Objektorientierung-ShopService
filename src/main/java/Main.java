import java.time.ZonedDateTime;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        ShopService shopService = new ShopService();
        Order order1 = new Order("1", List.of(new Product("1", "Apfel")),OrderStatus.PROCESSING, ZonedDateTime.now());
        Order order2 = new Order("2", List.of(new Product("2", "Birne")),OrderStatus.PROCESSING, ZonedDateTime.now());
        Order order3 = new Order("3", List.of(new Product("3", "Erdbeere")),OrderStatus.PROCESSING, ZonedDateTime.now());

    }
}
