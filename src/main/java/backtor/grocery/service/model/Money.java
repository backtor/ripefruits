package backtor.grocery.service.model;

/**
 * Defintion of money borrowed from Kevin Rutherford. Used as does not couple service to underlying primitives used to 
 * represent money. This allows the primitives to change without affecting how the program interacts with it. Currency not
 * included as surplus to requirements.  
 * @author Kevin Rutherford
 * @see <a href="http://silkandspinach.net/2015/01/25/connascence-of-meaning/">Connascence of Meaning</a>
 */
public class Money {
	  public static final Money ZERO = new Money(0);
	  private int pence;
	 
	  private Money(int pence) {
	    this.pence = pence;
	  }
	 
	  public static Money fromPence(int pence) {
	    return new Money(pence);
	  }
	 
	  public Money add(Money other) {
	    return new Money(pence + other.pence);
	  }
	 
	  @Override
	  public boolean equals(Object other) {
	    Money m = (Money) other;
	    return pence == m.pence;
	  }
	 
	  @Override
	  public int hashCode() {
	    return new Integer(pence).hashCode();
	  }
	  
	  @Override
	  public String toString() {
		  return new Integer(pence).toString() + " pence";
	  }
	}
