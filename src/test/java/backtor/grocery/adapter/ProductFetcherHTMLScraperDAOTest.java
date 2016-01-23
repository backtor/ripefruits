package backtor.grocery.adapter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;

import backtor.grocery.service.model.FileSize;
import backtor.grocery.service.model.Money;
import backtor.grocery.service.model.Product;

public class ProductFetcherHTMLScraperDAOTest {
	public final static int EXPECTED_NUMBER_OF_PRODUCTS = 7;
	
	@Test
	public void testScrapeStubFileNotNull() {
		ProductFetcherHTMLScraperDAO dao =  new ProductFetcherHTMLScraperDAO();
		List<Product> products = dao.fetchProducts();
		
		assertNotNull("Null products returned", products);
	}

	@Test
	public void testScrapeStubFileHasCorrectProductCount() {
		ProductFetcherHTMLScraperDAO dao =  new ProductFetcherHTMLScraperDAO();
		List<Product> products = dao.fetchProducts();
		
		assertEquals("Incorrect number of products", EXPECTED_NUMBER_OF_PRODUCTS, products.size());
	}

	@Test
	public void testScrapeStubFileHasCorrectFirstProduct() {
		ProductFetcherHTMLScraperDAO dao =  new ProductFetcherHTMLScraperDAO();
		List<Product> products = dao.fetchProducts();
		
		Product expectedFirstProduct = Product.create("Sainsbury's Apricot Ripe & Ready x5", FileSize.ZERO, Money.fromPence(350), null);
		Product actualFirstProduct = products.get(0);
		
		assertEquals("First product mismatch", expectedFirstProduct, actualFirstProduct);
	}

}
