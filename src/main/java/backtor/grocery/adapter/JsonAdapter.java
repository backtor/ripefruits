package backtor.grocery.adapter;

import backtor.grocery.service.api.ProductFetcher;
import backtor.grocery.service.model.ProductGroup;

/**
 * In hexagonal architecture terms. An adaptor for ports to call which retrieves products as JSON.
 * @author Dave Watson
 *
 */
public class JsonAdapter {

	ProductFetcher fetcher = null;
	
	public JsonAdapter(ProductFetcher fetcher) {
		if (fetcher == null) {
			throw new IllegalArgumentException("JsonAdapter must be given a ProductFetcher.");
		}
		
		this.fetcher = fetcher; 
	}

	public String fetchProductsAsJson() {
		ProductGroup results = fetcher.fetchProducts();
		
		return "{\"results\": [], \"total\": 0.00}";
	}
	
}
