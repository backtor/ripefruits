package backtor.grocery.adapter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.junit.Test;

import backtor.grocery.adapter.model.HTMLScrapeFailedException;
import backtor.grocery.service.model.FileSize;
import backtor.grocery.service.model.Money;
import backtor.grocery.service.model.Product;

/**
 * This is an integration test checking we can scrape against an HTTP endpoint. Note: not great being in with the unit tests but
 * would take time to factor out. 
 * @author Dave Watson
 *
 */
public class ProductFetcherHTMLScraperDAOIntegrationTest {
	public final static int EXPECTED_NUMBER_OF_PRODUCTS = 7;
	
	@Test(expected = HTMLScrapeFailedException.class)
	public void testForcingAnException() throws MalformedURLException {
		URL sourceUrl = new URL("file:///a/b/c.txt");

		new ProductFetcherHTMLScraperDAO(sourceUrl).fetchProducts();
	}
	
	@Test
	public void testScrapeNotNull() throws MalformedURLException {
		ProductFetcherHTMLScraperDAO dao =  createDAO();
		List<Product> products = dao.fetchProducts();
		
		assertNotNull("Null products returned", products);
	}

	@Test
	public void testScrapeHasCorrectProductCount() throws MalformedURLException {
		ProductFetcherHTMLScraperDAO dao =  createDAO();
		List<Product> products = dao.fetchProducts();
		
		assertEquals("Incorrect number of products", EXPECTED_NUMBER_OF_PRODUCTS, products.size());
	}

	@Test
	public void testScrapeHasCorrectFirstProduct() throws MalformedURLException {
		ProductFetcherHTMLScraperDAO dao =  createDAO();
		List<Product> products = dao.fetchProducts();
		
		Product expectedFirstProduct = Product.create("Sainsbury's Apricot Ripe & Ready x5", FileSize.fromBytes(39185), Money.fromPence(350), "Apricots");
		Product actualFirstProduct = products.get(0);
		
		assertEquals("First product mismatch", expectedFirstProduct, actualFirstProduct);
	}

	private ProductFetcherHTMLScraperDAO createDAO() throws MalformedURLException {
		URL sourceUrl = new URL("http://hiring-tests.s3-website-eu-west-1.amazonaws.com/2015_Developer_Scrape/5_products.html");
		ProductFetcherHTMLScraperDAO dao =  new ProductFetcherHTMLScraperDAO(sourceUrl);
		
		return dao;
	}
}
