package backtor.grocery.adapter.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import backtor.grocery.service.model.Product;
import backtor.grocery.service.model.ProductGroup;

/**
 * Object for converting ProductFetcher responses to one for JSON encoding. This keeps service's model pure and encoding agnostic.  
 * @author Dave Watson
 *
 */
public class ProductGroupForJson {
	public List<ProductForJson> results = new ArrayList<ProductForJson>();
	
	public BigDecimal total = null;
	
	public static ProductGroupForJson createProductGroupForJson(ProductGroup productGroup) {
		ProductGroupForJson pjson = new ProductGroupForJson();
		
		for (Product p : productGroup.getProducts()) {
			pjson.results.add(ProductForJson.createProductForJson(p));
		}
		
		pjson.total = productGroup.getTotalUnitPrice().toPoundsAndPence();
		
		return pjson;
	}

}
