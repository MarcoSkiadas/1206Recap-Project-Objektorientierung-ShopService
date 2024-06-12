import org.junit.jupiter.api.Test;

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
        Order expected = new Order("-1", List.of(new Product("1", "Apfel")),OrderStatus.PROCESSING);
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
}
