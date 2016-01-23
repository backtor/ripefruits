package backtor.grocery.adapter;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;

import backtor.grocery.service.model.Product;

public class ProductFetcherHTMLScraperDAOTest {

	@Test
	public void testScrapeStubFileNotNull() {
		ProductFetcherHTMLScraperDAO dao =  new ProductFetcherHTMLScraperDAO();
		
		List<Product> products = dao.fetchProducts();
		
		assertNotNull("Null products returned", products);
	}

}
