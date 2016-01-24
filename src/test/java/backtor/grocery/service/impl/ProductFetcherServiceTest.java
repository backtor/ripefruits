package backtor.grocery.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import backtor.grocery.service.api.ProductFetcherDAO;
import backtor.grocery.service.model.FileSize;
import backtor.grocery.service.model.Money;
import backtor.grocery.service.model.Product;
import backtor.grocery.service.model.ProductGroup;

/**
 * Checks the Product Fetcher service correctly creates product groups based upon what a DAO
 * gives it.
 * @author Dave Watson
 *
 */
public class ProductFetcherServiceTest {

	@Test
	public void testReturnsCorrectProductGroup() {
		ProductGroup stubProductGroup = createProductGroup();
		ProductFetcherDAO dao = new ProductFetcherDAOStub(stubProductGroup);
		ProductFetcherServiceImpl service = new ProductFetcherServiceImpl(dao);
		
		ProductGroup pg = service.fetchProducts();

		assertNotNull("No product group returned by service", pg);
		assertEquals("Totals mismatch", stubProductGroup.getTotalUnitPrice(), pg.getTotalUnitPrice());
		assertEquals("Products mismatch", stubProductGroup.getProducts(), pg.getProducts());
	}

	private ProductGroup createProductGroup() {
    	final int TEST_PENCE_1 = 120;
    	Money unitPrice1 = Money.fromPence(TEST_PENCE_1);
    	Product product1 = Product.create("title", FileSize.ZERO, unitPrice1, "description");
    	
    	final int TEST_PENCE_2 = 55;
    	Money unitPrice2 = Money.fromPence(TEST_PENCE_2);
    	Product product2 = Product.create("title", FileSize.ZERO, unitPrice2, "description");
    	
    	ProductGroup group = ProductGroup.create();
    	group.addProduct(product1).addProduct(product2);
		
    	return group;
	}
	
}
