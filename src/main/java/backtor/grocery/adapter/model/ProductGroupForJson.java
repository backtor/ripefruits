package backtor.grocery.adapter.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import backtor.grocery.service.model.ProductGroup;

/**
 * Object for converting ProductFetcher responses to one for JSON encoding. This keeps service's model pure and encoding agnostic.  
 * @author Dave Watson
 *
 */
public class ProductGroupForJson {
	public List<ProductForJson> results = new ArrayList<ProductForJson>();
	
	public BigDecimal total = null;
	
	public static ProductGroupForJson createProductForJson(ProductGroup pg) {
		ProductGroupForJson pjson = new ProductGroupForJson();
		
		pjson.total = pg.getTotalUnitPrice().toPoundsAndPence();
		
		return pjson;
	}

}
