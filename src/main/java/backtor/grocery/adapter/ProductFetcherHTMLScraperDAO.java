package backtor.grocery.adapter;

import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import backtor.grocery.service.api.ProductFetcherDAO;
import backtor.grocery.service.model.FileSize;
import backtor.grocery.service.model.Money;
import backtor.grocery.service.model.Product;

public class ProductFetcherHTMLScraperDAO implements ProductFetcherDAO {
	private File sourceFile = null;
	
	public ProductFetcherHTMLScraperDAO(File source) {
		sourceFile = source;
	}

	@Override
	public List<Product> fetchProducts() {
		List<Product> products = new ArrayList<Product>();
		try {
			Document document = Jsoup.parse(sourceFile, "UTF-8");
			Elements productElements = document.select("div.product ");
			
			//TODO: What would the lambda look like?
			for (Element e : productElements) {
				Product product = createProductFromElement(e);
				products.add(product);
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return products;
	}

	private Product createProductFromElement(Element productElement) throws IOException {
		Element productInfo = productElement.select("div.productInfo h3 a").first();	
		String productHref = productInfo.attr("href");
		String productText = productInfo.text();
		
		Element pricePerUnitElement = productElement.select("p.pricePerUnit").first();
		String priceText = pricePerUnitElement.text();
		Money unitPrice = priceTextToMoney(priceText);
		FileSize fileSize = getHTMLSize(productHref);

		//TODO: try/catch to deal with malformation?
		//TODO: process href to get description. -> need sample file.
		return Product.create(productText, fileSize, unitPrice, "TODO");
	}
	
	private FileSize getHTMLSize(final String href) throws IOException {
	    HttpURLConnection conn = null;
	    try {
	    	URL url = new URL(href);
	        conn = (HttpURLConnection) url.openConnection();
	        conn.setRequestMethod("HEAD");
	        conn.getInputStream();
	        return FileSize.fromBytes(conn.getContentLength());
	    } finally {
	        conn.disconnect();
	    }
	}
	 
	private Money priceTextToMoney(String priceText) {
		// strips out all non numerical characters, leaving price in pence which can be converted to an integer and made into Money!
		String penceString = priceText.replaceAll("[^0-9]", "");
		
		return Money.fromPence(new Integer(penceString));
	}
}
