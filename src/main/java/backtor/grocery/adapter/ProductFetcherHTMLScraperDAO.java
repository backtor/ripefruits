package backtor.grocery.adapter;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import backtor.grocery.adapter.model.HTMLScrapeFailedException;
import backtor.grocery.service.api.ProductFetcherDAO;
import backtor.grocery.service.model.FileSize;
import backtor.grocery.service.model.Money;
import backtor.grocery.service.model.Product;

public class ProductFetcherHTMLScraperDAO implements ProductFetcherDAO {
	/**
	 * Default time to wait for response for page to scrape.
	 */
	public static int DEFAULT_TIMEOUT_IN_MS = 7000; 
	
	private URL sourceUrl = null;
	
	public ProductFetcherHTMLScraperDAO(URL source) {
		sourceUrl = source;
	}

	@Override
	public List<Product> fetchProducts() {
		List<Product> products = new ArrayList<Product>();
		try {
			Document document = getDocument(sourceUrl);
			Elements productElements = document.select("div.product ");
			
			//TODO: What would the lambda look like?
			for (Element e : productElements) {
				Product product = createProductFromElement(e);
				products.add(product);
			}
			
		} catch (IOException | URISyntaxException e) {
			throw new HTMLScrapeFailedException("Unable to scrape HTML", e);
		}
		
		return products;
	}

	private Product createProductFromElement(Element productElement) throws IOException, URISyntaxException {
		Element productInfo = productElement.select("div.productInfo h3 a").first();	
		String productHref = productInfo.attr("href");
		String productText = productInfo.text();
		
		Element pricePerUnitElement = productElement.select("p.pricePerUnit").first();
		String priceText = pricePerUnitElement.text();
		Money unitPrice = priceTextToMoney(priceText);
		FileSize fileSize = fetchHTMLSize(productHref);
		String description = fetchDescription(productHref);

		return Product.create(productText, fileSize, unitPrice, description);
	}
	
	private FileSize fetchHTMLSize(final String href) throws IOException, URISyntaxException {
		URL url = new URL(href);
	    // Use HTTP HEAD request to determine content size, rather than bring back entire document. 
		HttpURLConnection conn = null;
	   
		try {
	        conn = (HttpURLConnection) url.openConnection();
	        conn.setRequestMethod("HEAD");
	        conn.getInputStream();
	        return FileSize.fromBytes(conn.getContentLength());
	    } finally {
	        conn.disconnect();
	    }
	}
	
	private String fetchDescription(final String href) throws IOException, URISyntaxException {
		URL url = new URL(href);
		Document document = getDocument(url);
		// if structure changes prone to NullPointerExceptions.
		Element productDescriptionElement = document.select("h3.productDataItemHeader + div.productText > p").first();
		
		return productDescriptionElement.text();
	}
	
	private Money priceTextToMoney(String priceText) {
		// strips out all non numerical characters, leaving price in pence which can be converted to an integer and made into Money!
		String penceString = priceText.replaceAll("[^0-9]", "");
		
		return Money.fromPence(new Integer(penceString));
	}
	
	private Document getDocument(URL url) throws URISyntaxException, IOException {
		return Jsoup.parse(url, getTimeoutInMs());
	}

	private int getTimeoutInMs() {
		return DEFAULT_TIMEOUT_IN_MS;
	}
}
