package backtor.grocery.service.model;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Tests total calculation in product group.
 * @author Dave Watson
 *
 */
public class ProductGroupTest extends TestCase {
    public ProductGroupTest(String testName) {
        super(testName);
    }

    public static Test suite() {
        return new TestSuite(ProductGroupTest.class);
    }

    /**
     * Rigorous Test :-)
     */
    public void testTotalNoProducts()
    {
    	ProductGroup group = ProductGroup.create();
    	
        assertEquals("Incorrect total", Money.ZERO, group.getTotalUnitPrice());
    }
 
    public void testTotalOneProduct() {
    	final int TEST_PENCE = 120;
    	Money unitPrice = Money.fromPence(TEST_PENCE);
    	Product product = Product.create("title", FileSize.ZERO, unitPrice, "description");
    	ProductGroup group = ProductGroup.create();
    	group.addProduct(product);
    	assertEquals("Incorrect total", unitPrice, group.getTotalUnitPrice());	
    }
    
    public void testTotalTwoProducts() {
    	final int TEST_PENCE_1 = 120;
    	Money unitPrice1 = Money.fromPence(TEST_PENCE_1);
    	Product product1 = Product.create("title", FileSize.ZERO, unitPrice1, "description");
    	
    	final int TEST_PENCE_2 = 55;
    	Money unitPrice2 = Money.fromPence(TEST_PENCE_2);
    	Product product2 = Product.create("title", FileSize.ZERO, unitPrice2, "description");
    	
    	ProductGroup group = ProductGroup.create();
    	group.addProduct(product1).addProduct(product2);
    	
    	assertEquals("Incorrect total", unitPrice1.add(unitPrice2), group.getTotalUnitPrice());	
    }

}
