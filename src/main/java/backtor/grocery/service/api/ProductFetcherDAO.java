package backtor.grocery.service.api;

import java.util.List;

import backtor.grocery.service.model.Product;

/**
 * Adapter interface to allow the Product Fetcher to access products from wherever they are held.
 * @author Dave Watson
 *
 */
public interface ProductFetcherDAO {
	public List<Product> fetchProducts();

}
