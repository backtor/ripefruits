package backtor.grocery.adapter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;

import org.junit.Test;

import backtor.grocery.service.model.FileSize;
import backtor.grocery.service.model.Money;
import backtor.grocery.service.model.Product;

public class ProductFetcherHTMLScraperDAOTest {
	public final static int EXPECTED_NUMBER_OF_PRODUCTS = 7;
	
	@Test
	public void testScrapeStubFileNotNull() throws URISyntaxException {
		ProductFetcherHTMLScraperDAO dao =  createDAOWithMockData();
		List<Product> products = dao.fetchProducts();
		
		assertNotNull("Null products returned", products);
	}

	@Test
	public void testScrapeStubFileHasCorrectProductCount() throws URISyntaxException {
		ProductFetcherHTMLScraperDAO dao =  createDAOWithMockData();
		List<Product> products = dao.fetchProducts();
		
		assertEquals("Incorrect number of products", EXPECTED_NUMBER_OF_PRODUCTS, products.size());
	}

	@Test
	public void testScrapeStubFileHasCorrectFirstProduct() throws URISyntaxException {
		ProductFetcherHTMLScraperDAO dao =  createDAOWithMockData();
		List<Product> products = dao.fetchProducts();
		
		Product expectedFirstProduct = Product.create("Sainsbury's Apricot Ripe & Ready x5", FileSize.ZERO, Money.fromPence(350), null);
		Product actualFirstProduct = products.get(0);
		
		assertEquals("First product mismatch", expectedFirstProduct, actualFirstProduct);
	}

	private ProductFetcherHTMLScraperDAO createDAOWithMockData() throws URISyntaxException {
		URL sourceUrl = getClass().getResource("/stubbeddata/stubbedproducts.html");
		File sourceFile = new File(sourceUrl.toURI());
		ProductFetcherHTMLScraperDAO dao =  new ProductFetcherHTMLScraperDAO(sourceFile);
		
		return dao;
	}
}
