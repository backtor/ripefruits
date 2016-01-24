package backtor.grocery.service.impl;

import java.util.List;

import backtor.grocery.service.api.ProductFetcherDAO;
import backtor.grocery.service.model.Product;
import backtor.grocery.service.model.ProductGroup;

/**
 * Helper class. Fake DAO so tests can control responses.
 * @author Dave Watson.
 *
 */
public class ProductFetcherDAOStub implements ProductFetcherDAO {

	ProductGroup productGroup = null; 
	
	public ProductFetcherDAOStub(ProductGroup pg) {
		productGroup = pg;
	}

	@Override
	public List<Product> fetchProducts() {
		return productGroup.getProducts();
	}

}
