package backtor.grocery.adapter;

import backtor.grocery.service.api.ProductFetcher;

/**
 * In hexagonal architecture terms. An adaptor for ports to call which retrieves products as JSON.
 * @author Dave Watson
 *
 */
public class JsonAdapter {

	public JsonAdapter(ProductFetcher fetcher) {
		
	}

	public String fetchProductsAsJson() {
		return "{\"results\": [], \"total\": 0.00}";
	}
	
}
