package backtor.grocery.service.impl;

import java.util.List;

import backtor.grocery.service.api.ProductFetcher;
import backtor.grocery.service.api.ProductFetcherDAO;
import backtor.grocery.service.model.Product;
import backtor.grocery.service.model.ProductGroup;

/**
 * Service (middle of the hexagon) which takes products from a DAO and placed into a ProductGroup.
 * This allows for total cost calculation.
 * @author Dave Watson
 *
 */
public class ProductFetcherServiceImpl implements ProductFetcher {

	ProductFetcherDAO adapter = null;
	
	public ProductFetcherServiceImpl(ProductFetcherDAO adapter) {
		this.adapter = adapter;
	}

	@Override
	public ProductGroup fetchProducts() {
		List<Product> products = adapter.fetchProducts();
		// applies business logic by combining into a group. DAO only cares about Products.
		ProductGroup productGroup = ProductGroup.create(products);
		
		return productGroup;
	}

}
