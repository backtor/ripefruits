package backtor;

import java.net.URL;

import backtor.grocery.adapter.JsonAdapter;
import backtor.grocery.adapter.ProductFetcherHTMLScraperDAO;
import backtor.grocery.service.api.ProductFetcher;
import backtor.grocery.service.api.ProductFetcherDAO;
import backtor.grocery.service.impl.ProductFetcherServiceImpl;

/**
 * IoC container that binds the application together. Could use spring for bigger projects.
 * @author Dave Watson
 */
public class AppFactory {

	private AppFactory() {
	}
	
	/**
	 * Return application 'hexagon' (In hexagonal architecture nomenclature) to scrape HTML at given URL and return product information in JSON.
	 * @param sourceUrl The given URL.
	 * @return Application to retrieve JSON.
	 */
	public static JsonAdapter createJsonHtmlScraper(URL sourceUrl) {
		ProductFetcherDAO daoAdapter = new ProductFetcherHTMLScraperDAO(sourceUrl);
		ProductFetcher service = new ProductFetcherServiceImpl(daoAdapter);
		JsonAdapter jsonAdapter = new JsonAdapter(service);
		
		return jsonAdapter;
	}
}
