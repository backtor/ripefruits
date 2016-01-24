package backtor.grocery.service.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

/**
 * Checks behaviour of the Product POJO.
 * @author Dave Watson
 *
 */
public class ProductTest {
	public final static Product PRODUCT_1 = Product.create("title 1", FileSize.fromBytes(1033), Money.fromPence(167), "desc 1");
	public final static Product PRODUCT_2 = Product.create("title 2", FileSize.fromBytes(1032), Money.fromPence(253), "desc 2");
	public final static Product PRODUCT_1_EQUAL = Product.create("title 1", FileSize.fromBytes(1033), Money.fromPence(167), "desc 1");

	@Test
	public void testNotEquals() {
		assertNotEquals("Should not be equal()", PRODUCT_1, PRODUCT_2);
	}

	@Test
	public void testNotEqualsDifferentObject() {
		assertNotEquals("Should not be equal()", PRODUCT_1, new Integer(1235));
	}

	@Test
	public void testNotEqualsWithNull() {
		assertNotEquals("Should not be equal()", PRODUCT_2, null);
	}

	@Test
	public void testEquals() {
		assertEquals("Should be equal", PRODUCT_1, PRODUCT_1_EQUAL);
		assertEquals("Hascode should be equal", PRODUCT_1.hashCode(), PRODUCT_1_EQUAL.hashCode());
	}

}
