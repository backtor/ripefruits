package backtor.grocery.adapter.model;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Test;

import backtor.grocery.service.model.FileSize;
import backtor.grocery.service.model.Money;
import backtor.grocery.service.model.Product;

public class ProductForJsonTest {

	public static final String TITLE = "a title";
	public static final String DESCRIPTION = "a description";
	
	@Test
	public void testProductMappedToJsonProduct() {
		Product p = Product.create(TITLE, FileSize.fromBytes(1033), Money.fromPence(167), DESCRIPTION);
		ProductForJson pjson = ProductForJson.createProductForJson(p);
		
		assertEquals("Title mismatch", TITLE, pjson.title);
		assertEquals("Filesize mismatch", "1.0kb", pjson.sizeInKb);
		assertEquals("Money mismatch", new BigDecimal("1.67"), pjson.unitPrice);
		assertEquals("Description mismatch", DESCRIPTION, pjson.description);
	}

}
