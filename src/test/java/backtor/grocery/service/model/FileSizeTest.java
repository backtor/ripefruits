package backtor.grocery.service.model;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Test;

/**
 * Checks behaviour of the FileSize POJO.
 * @author Dave Watson
 *
 */
public class FileSizeTest {

	@Test
	public void testKbHasOneDecimalPlacesForLessThanOneKb() {
		FileSize fs = FileSize.fromBytes(133);
		BigDecimal actual = fs.toKiloBytes();
		
		assertEquals("Mismatched", new BigDecimal("0.1"), actual);
	}
	
	@Test
	public void testKbHasOneDecimalPlacesForGreaterThanOneKb() {
		FileSize fs = FileSize.fromBytes(1333);
		BigDecimal actual = fs.toKiloBytes();
		
		assertEquals("Mismatched", new BigDecimal("1.3"), actual);
	}
	
	@Test
	public void testNotEquals() {
		FileSize mx = FileSize.fromBytes(1235);
		FileSize my = FileSize.fromBytes(133);
		
		assertNotEquals("Should not be equal()", mx, my);
	}

	@Test
	public void testNotEqualsDifferentObject() {
		FileSize mx = FileSize.fromBytes(1235);
		
		assertNotEquals("Should not be equal()", mx, new Integer(1235));
	}

	@Test
	public void testNotEqualsWithNull() {
		FileSize mx = FileSize.fromBytes(1235);
		
		assertNotEquals("Should not be equal()", mx, null);
	}

	@Test
	public void testEquals() {
		FileSize mx = FileSize.fromBytes(1235);
		FileSize my = FileSize.fromBytes(1235);
		
		assertEquals("Should not be equal()", mx, my);
		assertEquals("Hascode should be equal", mx.hashCode(), my.hashCode());
	}

}
