package backtor.grocery.service.api;

import backtor.grocery.service.model.ProductGroup;

/**
 * Service API describing a service responsible for retrieving products by some means.
 * @author Dave Watson
 *
 */
public interface ProductFetcher {
	/**
	 * Fetch a group of products. Note: Currently no identifiers passed in as surplus to current requirements.
	 * @return A product group. If no products this group will be empty, never <code>null</code>.
	 */
	public ProductGroup fetchProducts();
}
