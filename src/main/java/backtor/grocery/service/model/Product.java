package backtor.grocery.service.model;

/**
 * Represents a product that can be sold. All fields can be <code>null</code> as no optionality defined.  
 * @author dwatson
 *
 */
public class Product {
	/**
	 * Brief product description.
	 */
	private String title = null;
	
	/**
	 * Size of HTML page describing the product.
	 */
	private FileSize size = null;
	
	/**
	 * Total price of all the items in the product. For example the unit price of 4 oranges is £5.00 when each orange is £1.25.
	 */
	private Money unitPrice = null;
	
	/**
	 * Further information about the product. 
	 */
	private String description = null;

	private Product(String title, FileSize size, Money unitPrice, String description) {
		this.title = title;
		this.size = size;
		this.unitPrice = unitPrice;
		this.description = description;
	}
	
	public String getTitle() {
		return title;
	}
	public FileSize getSize() {
		return size;
	}
	public Money getUnitPrice() {
		return unitPrice;
	}
	public String getDescription() {
		return description;
	}
	
	public static Product create(String title, FileSize size, Money unitPrice, String description) {
		return new Product(title, size, unitPrice, description);
	}
	
	@Override
	public boolean equals(Object other) {
		if (other == null) { return false; }
		if (!(other instanceof Product)) { return false; }
		Product p = (Product) other;
		
		return getTitle().equals(p.getTitle()) 
				&& getSize().equals(p.getSize())
				&& getUnitPrice().equals(p.getUnitPrice())
				&& getDescription().equals(p.getDescription());
  }
 
	@Override
	public int hashCode() {
		return getTitle().hashCode();
	}
	  
	@Override  
	public String toString() {
		return String.format("title: '%s', fileSize: '%s', unitPrice: '%s', description: '%s'", title, size, unitPrice, description);
	}
}
