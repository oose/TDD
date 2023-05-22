package de.oose.stattauto.geschaeftslogik;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class MitgliedTest {

	@Test
	public void anlegen() {
		final String name = "Mitglied Name";
		Mitglied mitglied = new Mitglied(name, 1);
		assertEquals(name, mitglied.getName());
	}
	
	@Test
	public void nullNameNichtErlaubt() {
		assertThrows(IllegalArgumentException.class, () -> new Mitglied(null, 4));
	}
	
	@Test
	public void leererNameNichtErlaubt() {
		assertThrows(IllegalArgumentException.class, () -> new Mitglied("", 34));
	}
	
	@Test
	public void negativeMitgliedsnummerNichtErlaubt() {
		assertThrows(IllegalArgumentException.class, () -> new Mitglied("Peter", -234));
	}
	
	@Test
	public void mitgliedsnr0NichtErlaubt() {
		IllegalArgumentException expected = assertThrows(IllegalArgumentException.class, () -> new Mitglied("Peter", 0));
		assertEquals("mitgliedsnr > 0", expected.getMessage());
	}
}
