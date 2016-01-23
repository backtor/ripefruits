package backtor.grocery.adapter;

import backtor.grocery.service.api.ProductFetcher;
import backtor.grocery.service.model.ProductGroup;

/**
 * Stub to hold specific product groups to test JsonAdapter.
 * @author Dave Watson
 *
 */
public class ProductFetcherStub implements ProductFetcher {
	
	private ProductGroup productGroup = null;
	
	private ProductFetcherStub(ProductGroup productGroup) {
		this.productGroup = productGroup;
	}
	
	public static ProductFetcherStub create(ProductGroup productGroup) {
		return new ProductFetcherStub(productGroup);
	}

	@Override
	public ProductGroup fetchProducts() {
		return productGroup;
	}
}
