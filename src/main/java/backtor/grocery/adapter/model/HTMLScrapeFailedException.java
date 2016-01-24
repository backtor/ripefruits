/**
 * 
 */
package backtor.grocery.adapter.model;

/**
 * Unchecked exception indicating unable to scrape an HTML page. Note: Unchecked as not a business exception and reduces
 * code try/catch clutter.
 * @author Dave Watson
 *
 */
public class HTMLScrapeFailedException extends RuntimeException {

	/**
	 * Generated Serialisation
	 */
	private static final long serialVersionUID = 427610372104097052L;

	/**
	 * 
	 */
	public HTMLScrapeFailedException() {
		super();
	}

	/**
	 * @param message
	 */
	public HTMLScrapeFailedException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public HTMLScrapeFailedException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public HTMLScrapeFailedException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public HTMLScrapeFailedException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
