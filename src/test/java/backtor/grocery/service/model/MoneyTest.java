package backtor.grocery.service.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.math.BigDecimal;

import org.junit.Test;

public class MoneyTest {

	@Test
	public void testPoundsAndPenceHasTwoDecimalPlacesForPenceWhenNoPounds() {
		Money m = Money.fromPence(30);
		BigDecimal actual = m.toPoundsAndPence();
		
		assertEquals("Mismatched", new BigDecimal("0.30"), actual);
	}

	@Test
	public void testPoundsAndPenceHasTwoDecimalPlacesForPenceWhenLessThanTenPounds() {
		Money m = Money.fromPence(230);
		BigDecimal actual = m.toPoundsAndPence();
		
		assertEquals("Mismatched", new BigDecimal("2.30"), actual);
	}

	@Test
	public void testPoundsAndPenceHasTwoDecimalPlacesForPenceWhenMoreThanTenPounds() {
		Money m = Money.fromPence(1235);
		BigDecimal actual = m.toPoundsAndPence();
		
		assertEquals("Mismatched", new BigDecimal("12.35"), actual);
	}

	@Test
	public void testAddMoney() {
		int x = 1235;
		int y = 133;
		
		Money mx = Money.fromPence(1235);
		Money my = Money.fromPence(133);
		
		Money mz = mx.add(my);
		
		assertEquals("Addition incorrect", x + y, mz.toPence());
	}
	
	@Test
	public void testNotEquals() {
		Money mx = Money.fromPence(1235);
		Money my = Money.fromPence(133);
		
		assertNotEquals("Should not be equal()", mx, my);
	}

	@Test
	public void testNotEqualsDifferentObject() {
		Money mx = Money.fromPence(1235);
		
		assertNotEquals("Should not be equal()", mx, new Integer(1235));
	}

	@Test
	public void testNotEqualsWithNull() {
		Money mx = Money.fromPence(1235);
		
		assertNotEquals("Should not be equal()", mx, null);
	}

	@Test
	public void testEquals() {
		Money mx = Money.fromPence(1235);
		Money my = Money.fromPence(1235);
		
		assertEquals("Should be equal", mx, my);
		assertEquals("Hascode should be equal", mx.hashCode(), my.hashCode());
	}

}
