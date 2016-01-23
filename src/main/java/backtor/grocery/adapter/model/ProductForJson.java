package backtor.grocery.adapter.model;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

import backtor.grocery.service.model.Product;

public class ProductForJson {
	
	public String title = null;

	@JsonProperty("size")
	public String sizeInKb = null;
	
	@JsonProperty("unit_price")
	public BigDecimal unitPrice = null;
	
	public String description = null;

	public static ProductForJson createProductForJson(Product p) {
		ProductForJson pjson = new ProductForJson();
		
		pjson.title = p.getTitle();
		pjson.sizeInKb = p.getSize().toKiloBytes() + "kb";
		pjson.unitPrice = p.getUnitPrice().toPoundsAndPence();
		pjson.description = p.getDescription();
		
		return pjson;
	}

}
