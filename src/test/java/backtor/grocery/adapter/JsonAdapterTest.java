package backtor.grocery.adapter;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import backtor.grocery.service.api.ProductFetcher;
import backtor.grocery.service.model.ProductGroup;
import junit.framework.TestCase;

public class JsonAdapterTest extends TestCase {

	@Test
	public void testJsonNoProducts() throws JsonProcessingException, IOException {
		ProductGroup productGroup = ProductGroup.create();
		ProductFetcher fetcher = ProductFetcherStub.create(productGroup);
		
		JsonAdapter ja = new JsonAdapter(fetcher);
		
		// Use map to compare JSON as order not guaranteed.
		Map<String, Object> m = new HashMap<>();
		m.put("results", Collections.EMPTY_LIST);
		m.put("total", 0.00);
		
		ObjectMapper jsonMapper = new ObjectMapper();
		Map<?,?> responseAsMap = jsonMapper.readValue(ja.fetchProductsAsJson(), Map.class);
		assertEquals("Mismatched JSON", m, responseAsMap);
	}

}
