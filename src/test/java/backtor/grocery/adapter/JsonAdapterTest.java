package backtor.grocery.adapter;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import backtor.grocery.service.api.ProductFetcher;
import backtor.grocery.service.model.FileSize;
import backtor.grocery.service.model.Money;
import backtor.grocery.service.model.Product;
import backtor.grocery.service.model.ProductGroup;

/**
 * Tests JSON serialisation of Product Groups. 
 * @author Dave Watson
 *
 */
public class JsonAdapterTest {
	public static final String TITLE_1 = "Product Title 1";
	public static final FileSize FILE_SIZE_1 = FileSize.fromBytes(123);
	public static final Money UNIT_PRICE_1 = Money.fromPence(133);
	public static final String DECRIPTION_1 = "Product Description 1";
	
	public static final String TITLE_2 = "Product Title 2";
	public static final FileSize FILE_SIZE_2 = FileSize.fromBytes(1243);
	public static final Money UNIT_PRICE_2 = Money.fromPence(949);
	public static final String DECRIPTION_2 = "Product Description 2";

	
	@Test
	public void testJsonWhenNoProducts() throws JsonProcessingException, IOException {
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

	@Test
	public void testJsonWhenOneProduct() throws JsonParseException, JsonMappingException, IOException {
		ProductGroup productGroup = createProductGroupWithOneProduct();
		ProductFetcher fetcher = ProductFetcherStub.create(productGroup);
		JsonAdapter ja = new JsonAdapter(fetcher);
		
		Map<String, Object> expected = createMapFromProductGroup(productGroup);

		ObjectMapper jsonMapper = new ObjectMapper();
		Map<?,?> responseAsMap = jsonMapper.readValue(ja.fetchProductsAsJson(), Map.class);
		assertEquals("Mismatched JSON", expected, responseAsMap);

	}
	
	@Test
	public void testJsonWhenTwoProducts() throws JsonParseException, JsonMappingException, IOException {
		ProductGroup productGroup = createProductGroupWithOneProduct();
		Product product2 = Product.create(TITLE_2, FILE_SIZE_2, UNIT_PRICE_2, DECRIPTION_2);
		productGroup.addProduct(product2);
		ProductFetcher fetcher = ProductFetcherStub.create(productGroup);
		JsonAdapter ja = new JsonAdapter(fetcher);
		
		Map<String, Object> expected = createMapFromProductGroup(productGroup);

		ObjectMapper jsonMapper = new ObjectMapper();
		Map<?,?> responseAsMap = jsonMapper.readValue(ja.fetchProductsAsJson(), Map.class);
		assertEquals("Mismatched JSON", expected, responseAsMap);

	}

	private ProductGroup createProductGroupWithOneProduct() {
		ProductGroup productGroup = ProductGroup.create();
		Product product = Product.create(TITLE_1, FILE_SIZE_1, UNIT_PRICE_1, DECRIPTION_1);
		productGroup.addProduct(product);
		
		return productGroup;
	}
	
	private Map<String, Object> createMapFromProductGroup(ProductGroup productGroup) {
		Map<String, Object> m = new LinkedHashMap<>();
		List<Map<String,Object>> results = new ArrayList<>(); 
		
		List<Product> products = productGroup.getProducts();
		for (Product p : products) {
			Map<String, Object> productAsMap = createMapFromProduct(p);
			results.add(productAsMap);
		}
		
		m.put("results", results);
		m.put("total", new Double(productGroup.getTotalUnitPrice().toPoundsAndPence().doubleValue()));
		
		return m;
	}
	
	private Map<String, Object> createMapFromProduct(Product product) {
		Map<String, Object> m = new LinkedHashMap<>();
		m.put("title", product.getTitle());
		m.put("size", product.getSize().toKiloBytes() + "kb");
		m.put("unit_price", new Double(product.getUnitPrice().toPoundsAndPence().doubleValue()));
		m.put("description", product.getDescription());
		return m;
	}
}