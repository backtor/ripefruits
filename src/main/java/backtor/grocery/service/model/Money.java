package backtor.grocery.service.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Defintion of money borrowed from Kevin Rutherford. Used as does not couple service to underlying primitives used to 
 * represent money. This allows the primitives to change without affecting how the program interacts with it. Currency not
 * included as surplus to requirements.  
 * @author Kevin Rutherford
 * @see <a href="http://silkandspinach.net/2015/01/25/connascence-of-meaning/">Connascence of Meaning</a>
 */
public class Money {
	  public static final Money ZERO = new Money(0);
	  public static final BigDecimal PENCE_IN_POUND = new BigDecimal(100);
	  
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
	 
	  public int toPence() {
		  return pence;
	  }
	  
	  /** 
	   * Returns Pounds and pence represented by this FileSize.
	   * @return Pounds and pence represented by this FileSize, rounded to 2 decimal places.
	   */
	  public BigDecimal toPoundsAndPence() {
		  BigDecimal poundsAndPence = new BigDecimal(pence).divide(PENCE_IN_POUND);
		  poundsAndPence = poundsAndPence.setScale(2, RoundingMode.HALF_UP);

		  return poundsAndPence;
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
