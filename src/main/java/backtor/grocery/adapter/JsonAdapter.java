package backtor.grocery.adapter;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import backtor.grocery.adapter.model.ProductGroupForJson;
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

	public String fetchProductsAsJson() throws JsonGenerationException, JsonMappingException, IOException {
		ProductGroup results = fetcher.fetchProducts();
		ProductGroupForJson resultsForJson = ProductGroupForJson.createProductGroupForJson(results);
		ObjectMapper jsonMapper = new ObjectMapper();
		Writer writer = new StringWriter();
		
		jsonMapper.writeValue(writer, resultsForJson);
		
		return writer.toString();
	}
	
}
