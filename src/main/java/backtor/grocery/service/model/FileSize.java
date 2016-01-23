package backtor.grocery.service.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * <p>File size definition used to represent the size of a file or webpage.</p> 
 * <p>Based on the same principles as used in the {@link Money} class. Used as does not couple service to underlying primitives used to 
 * represent money. This allows the primitives to change without affecting how the program interacts with it.</p>
 * @author Dave Watson
 */
public class FileSize {
	  public static final FileSize ZERO = new FileSize(0);
	  public static final BigDecimal BYTES_IN_A_KILOBYTE = new BigDecimal(1024);
	  
	  private int bytes;
	 
	  private FileSize(int bytes) {
	    this.bytes = bytes;
	  }
	 
	  public static FileSize fromBytes(int bytes) {
	    return new FileSize(bytes);
	  }
	  
	  /** 
	   * Returns Kilobytes represented by this FileSize.
	   * @return Kilobytes represented by this FileSize, rounded to 1 decimal place.
	   */
	  public BigDecimal toKiloBytes() {
		  BigDecimal bd = new BigDecimal(bytes).divide(BYTES_IN_A_KILOBYTE);
		  bd = bd.setScale(1, RoundingMode.HALF_UP);
		  return bd;
	  }
	  
	 
	  @Override
	  public boolean equals(Object other) {
	    FileSize fs = (FileSize) other;
	    return bytes == fs.bytes;
	  }
	 
	  @Override
	  public int hashCode() {
	    return new Integer(bytes).hashCode();
	  }
	  
	  @Override
	  public String toString() {
		  return new Integer(bytes).toString() + " bytes";
	  }

	}
