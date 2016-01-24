package backtor;

import java.net.URL;

import backtor.grocery.adapter.JsonAdapter;

/**
 * Adaptor to allow console operation of the hexagon created by the {@link AppFactory}.
 * @author Dave Watson
 */
public class App {
    public static void main(String[] args) throws Exception {
    	
    	URL sourceUrl = new URL("http://hiring-tests.s3-website-eu-west-1.amazonaws.com/2015_Developer_Scrape/5_products.html");
        JsonAdapter adapter = AppFactory.createJsonHtmlScraper(sourceUrl);
        
        String json = adapter.fetchProductsAsJson();
    	System.out.println(json);
    }
}
