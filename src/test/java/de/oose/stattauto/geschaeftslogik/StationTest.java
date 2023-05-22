package de.oose.stattauto.geschaeftslogik;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class StationTest {

	@Test
	public void anlegen() {
		final String name = "Name der Station";
		Station station = new Station(name);
		assertEquals(name, station.getName());
	}
	
	@Test
	public void nullNameNichtErlaubt() {
		assertThrows(IllegalArgumentException.class, 
			() -> new Station(null)
		);
	}
	
	@Test
	public void leererNameNichtErlaubt() {
		assertThrows(IllegalArgumentException.class, 
				() -> new Station("")
			);
	}
}
