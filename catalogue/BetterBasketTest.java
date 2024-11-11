package catalogue;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
class BetterBasketTest {

    @org.junit.jupiter.api.Test
    void testMergeAddProduct() {
        ArrayList<Product> br = new ArrayList<>();
        Product p1 = new Product("0001", "toaster", 12.3, 1);
        Product p2 = new Product("0002", "kettle", 10.00, 1);
        br.add(p1);
        br.add(p2);
        br.add(p2);
        br.add(p2);
        assertEquals(2, br.size(),"incorrect size after merge");
        assertEquals(3,br.get(1).getQuantity(),"incorrect quantity after merge");
    }
}