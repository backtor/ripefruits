package backtor.grocery.adapter;

import java.util.Collections;
import java.util.List;

import backtor.grocery.service.api.ProductFetcherDAO;
import backtor.grocery.service.model.Product;

public class ProductFetcherHTMLScraperDAO implements ProductFetcherDAO {

	@Override
	public List<Product> fetchProducts() {
		return Collections.emptyList();
	}

}
