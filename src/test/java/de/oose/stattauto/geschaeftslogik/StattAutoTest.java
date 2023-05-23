package de.oose.stattauto.geschaeftslogik;

import java.util.Collection;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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
	@DisplayName("Eine neu angelegte Station ist in StattAuto vorhanden")
	public void stationHinzufuegen() {
		Collection<Station> stationen = stattAuto.getStationen();
		assertNotNull(stationen);
		assertEquals(1, stationen.size());

		Station station = new Station("Hauptbahnhof");
		stattAuto.addStation(station);
		stationen = stattAuto.getStationen();
		assertNotNull(stationen);
		assertEquals(2, stationen.size());
		assertTrue(stationen.contains(station));
	}
	
	@Test
	@DisplayName("NULL als Station wird nicht akzeptiert")
	public void nullStationHinzufuegenNichtErlaubt() {
		assertThrows(IllegalArgumentException.class, 
			() -> stattAuto.addStation(null)
		);
	}
	
	@Test
	@DisplayName("NULL als KFZ wird nicht akzeptiert")
	public void nullKfzHinzufuegenNichtErlaubt() {
		assertThrows(IllegalArgumentException.class, 
			() -> stattAuto.addKfz(null)
		);
	}
	
	@Test
	@DisplayName("Ein neues Mitglied wird anhand seiner Mitgliedsnummer gefunden")
	public void mitgliedHinzufuegen() throws MitgliedVorhandenException {
		stattAuto.addMitglied(peter);
		Mitglied mitglied = stattAuto.getMitglied(MITGLIEDSNR);
		assertNotNull(mitglied);
		assertEquals(peter, mitglied);
	}

	@Test
	@DisplayName("NULL als Mitglied wird nichz akzeptiert")
	public void nullMitgliedHinzufuegenNichtErlaubt() throws MitgliedVorhandenException {
		assertThrows(IllegalArgumentException.class, 
			() -> stattAuto.addMitglied(null)
		);
	}

	@Test
	@DisplayName("Ein neues Mitglied mit bereits vorhandener Mitgliedsnummer wird nicht akzeptiert")
	public void mitgliedMitNummerVorhanden() throws MitgliedVorhandenException {
		stattAuto.addMitglied(peter);

		MitgliedVorhandenException exception = assertThrows(MitgliedVorhandenException.class, () -> stattAuto.addMitglied(new Mitglied("Zweiter", peter.getMitgliedsnr())));
		assertEquals(peter, exception.getMitglied());
	}
}
