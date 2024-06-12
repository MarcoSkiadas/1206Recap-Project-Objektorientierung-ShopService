import org.junit.jupiter.api.Test;

import java.time.ZonedDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ShopServiceTest {

    @Test
    void addOrderTest() {
        //GIVEN
        ShopService shopService = new ShopService();
        List<String> productsIds = List.of("1");

        //WHEN
        Order actual = null;
        try {
            actual = shopService.addOrder(productsIds);
        } catch (Exception e) {

        }

        //THEN
        Order expected = new Order("-1", List.of(new Product("1", "Apfel")),OrderStatus.PROCESSING, ZonedDateTime.now());
        assertEquals(expected.products(), actual.products());
        assertNotNull(expected.id());
    }

    @Test
    void addOrderTest_whenInvalidProductId_ThrowsException() {
        //GIVEN
        ShopService shopService = new ShopService();
        List<String> productsIds = List.of("1", "2");

        //WHEN
        Order actual = null;
        try {
           actual = shopService.addOrder(productsIds);
           fail("Should have thrown an exception");
        } catch (Exception e) {

        }

        //THEN
        assertThrows(Exception.class, () -> shopService.addOrder(productsIds));
    }
    @Test
    void getOrders_shouldReturnSortedListOfOrders_whenCalledWithUnsortedList() {
        ShopService shopService = new ShopService();
        List<String> productsIds = List.of("1");
        Order order1 = new Order("-1", List.of(new Product("1", "Apfel")),OrderStatus.PROCESSING, ZonedDateTime.now());
        Order order2 = new Order("-1", List.of(new Product("2", "Birne")),OrderStatus.PROCESSING, ZonedDateTime.now());
        List<Order> orderList = List.of(order2,order1);
        List<Order> actual = shopService.getOrders();

        assertEquals(orderList, actual);
    }

    @Test
    void getOrders_shouldReturnListOfOrdersWithNoDuplicates_whenCalledWithDuplicates() {
        ShopService shopService = new ShopService();
        List<String> productsIds = List.of("1");
        Order order1 = new Order("-1", List.of(new Product("1", "Apfel")),OrderStatus.PROCESSING, ZonedDateTime.now());
        Order order2 = new Order("-1", List.of(new Product("2", "Birne")),OrderStatus.PROCESSING, ZonedDateTime.now());
        List<Order> orderList = List.of(order2,order1,order2);
        List<Order> actual = shopService.getOrders();

        assertNotEquals(orderList, actual);
    }
    @Test
    void updateOrder_shouldThrowException_whenOrderIdIsUnknown() {
        ShopService shopService = new ShopService();

        //WHEN
        Order actual = null;
        try {
            actual = shopService.updateOrder("1",OrderStatus.PROCESSING);
            fail("Should have thrown an exception");
        } catch (Exception e) {

        }
        assertThrows(Exception.class, () -> shopService.updateOrder("1",OrderStatus.PROCESSING));

    }
    @Test
    void updateOrder_shouldNotThrowException_whenOrderIdIsKnown() {
        ShopService shopService = new ShopService();

        //WHEN
        Order actual = new Order("1", List.of(new Product("1", "Apfel")),OrderStatus.PROCESSING, ZonedDateTime.now());
        try {
            actual = shopService.updateOrder("1",OrderStatus.IN_DELIVERY);
            fail("Should have thrown an exception");
        } catch (Exception e) {

        }
        Order expected = new Order("1", List.of(new Product("1", "Apfel")),OrderStatus.IN_DELIVERY, ZonedDateTime.now());
        assertEquals(expected,actual);

    }


}
