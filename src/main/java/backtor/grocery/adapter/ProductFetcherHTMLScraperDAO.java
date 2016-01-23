package backtor.grocery.adapter;

import java.util.ArrayList;
import java.util.List;

import backtor.grocery.service.api.ProductFetcherDAO;
import backtor.grocery.service.model.FileSize;
import backtor.grocery.service.model.Money;
import backtor.grocery.service.model.Product;

public class ProductFetcherHTMLScraperDAO implements ProductFetcherDAO {

	@Override
	public List<Product> fetchProducts() {
		List<Product> products = new ArrayList<Product>();
		
		for(int i = 0; i < 7; i++) { products.add(Product.create("title", FileSize.ZERO, Money.ZERO, "description")); }
		return products;
	}

}
