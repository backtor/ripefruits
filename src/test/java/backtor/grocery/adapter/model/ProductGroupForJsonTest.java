package backtor.grocery.adapter.model;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Test;

import backtor.grocery.service.model.FileSize;
import backtor.grocery.service.model.Money;
import backtor.grocery.service.model.Product;
import backtor.grocery.service.model.ProductGroup;

public class ProductGroupForJsonTest {
	
	@Test
	public void testProductGroupMappedToJsonProductGroup() {
		Product p1 = Product.create("title 1", FileSize.fromBytes(1033), Money.fromPence(167), "desc 1");
		Product p2 = Product.create("title 2", FileSize.fromBytes(1032), Money.fromPence(253), "desc 2");
		ProductGroup group = ProductGroup.create();
		group.addProduct(p1).addProduct(p2);
		
		ProductGroupForJson pgson = ProductGroupForJson.createProductGroupForJson(group);
		
		assertEquals("Total mismatch :-)", new BigDecimal("4.20"), pgson.total);
		assertEquals("Product count mismatch", 2, pgson.results.size());
	}

}
