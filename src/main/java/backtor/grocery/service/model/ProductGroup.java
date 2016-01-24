package backtor.grocery.service.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Represents a group of 0 or more products and expresses their total unit cost.
 * @author dwatson
 *
 */
public class ProductGroup {
	
	/**
	 * Products that make up the group. A list is used to allow for duplicate items to appear on the page, which a set would not
	 * It is not stipulated whether duplicates matter. The list's order is as added via {@link #addProduct}
	 */
	private List<Product> products = null;
	
	private ProductGroup() {
		products = new ArrayList<Product>();
	}
	
	private ProductGroup(List<Product> products) {
		this.products = products;
	}

	/**
	 * Adds product to the group. 
	 * 
	 * @param nextProduct Product to add to the group
	 * @return This product group so that addProduct calls can be chained. 
	 * Note: If this was purely functional a new product group would be returned but this is a bit much at this stage.
	 */
	public ProductGroup addProduct(Product nextProduct) {
		products.add(nextProduct);
		
		return this;
	}
	
	/**
	 * Returns the sum of all unit prices for products added to the group.
	 * @return The sum of all unit prices for products added to the group.
	 */
	public Money getTotalUnitPrice() {
		// Note: Total business logic here so total not decoupled from the items. This would change if reductions were to be applied.
		Money total = products.stream().map(Product::getUnitPrice).reduce(Money.ZERO, (a, b) -> a.add(b));
		
		return total;
	}
	
	/**
	 * Returns the groups products.
	 * @return An unmodifiable list of the groups products so the list cannot be modified outside of the ProductGroup. 
	 * If used more we could create a copy. 
	 */
	public List<Product> getProducts() {
		return Collections.unmodifiableList(products);
	}
	
	public static ProductGroup create() {
		return new ProductGroup();
	}

	/**
	 * Returns a group of products.
	 * @param products Products to seed the group with. Quicker than using {@link #add}
	 * @return A new ProductGroup
	 */
	public static ProductGroup create(List<Product> products) {
		return new ProductGroup(products);
	}

}
