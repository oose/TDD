package de.oose.stattauto.geschaeftslogik;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Collection;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class StattAutoTest {

	private static final int MITGLIEDSNR = 234;
	private StattAuto stattAuto;
	private Mitglied peter;
	private Station station;
	private Kfz golfAnStation = new Kfz("Golf");
	private Kfz fiat = new Kfz("Fiat");
	private Kfz opel = new Kfz("Opel");

	@BeforeEach
	public void before() {
		peter = new Mitglied("Peter Putz", MITGLIEDSNR);
		stattAuto = new StattAuto();
		station = new Station("Christuskirche");
		stattAuto.addStation(station);
		stattAuto.addKfz(golfAnStation);
		golfAnStation.setStation(station);
		stattAuto.addKfz(opel);
		stattAuto.addKfz(fiat);
	}
	
	@Test
	public void stationHinzufuegen() {
		Collection<Station> stationen = stattAuto.getStationen();
		assertNotNull(stationen);
		assertEquals(1, stationen.size());

		Station station = new Station("Hauptbahnhof");
		stattAuto.addStation(station);
		stationen = stattAuto.getStationen();
		assertNotNull(stationen);
		assertEquals(2, stationen.size());
	}
	
	@Test
	public void nullStationHinzufuegenNichtErlaubt() {
		assertThrows(IllegalArgumentException.class, 
			() -> stattAuto.addStation(null)
		);
	}
	
	@Test
	public void nullKfzHinzufuegenNichtErlaubt() {
		assertThrows(IllegalArgumentException.class, 
			() -> stattAuto.addKfz(null)
		);
	}
	
	@Test
	public void mitgliedHinzufuegen() throws MitgliedVorhandenException {
		stattAuto.addMitglied(peter);
		Mitglied mitglied = stattAuto.getMitglied(234);
		assertNotNull(mitglied);
		assertSame(peter, mitglied);
		assertNull(stattAuto.getMitglied(345));
	}

	@Test
	public void nullMitgliedHinzufuegenNichtErlaubt() throws MitgliedVorhandenException {
		assertThrows(IllegalArgumentException.class, 
			() -> stattAuto.addMitglied(null)
		);
	}

	@Test
	public void mitgliedMitNummerVorhanden() throws MitgliedVorhandenException {
		stattAuto.addMitglied(peter);
		try {
			stattAuto.addMitglied(new Mitglied("Zweiter", peter.getMitgliedsnr()));
			fail();
		} catch(MitgliedVorhandenException expected) {
			assertSame(peter, expected.getMitglied());
		}
	}
}
