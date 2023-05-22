package de.oose.stattauto.geschaeftslogik;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import de.oose.stattauto.geschaeftslogik.util.DateUtil;

public class KfzTest {
	
	@Test
	public void anlegen() {
		final String name = "KfzName";
		Kfz kfz = new Kfz(name);
		assertEquals(name, kfz.getName());
	}

	@Test
	public void anderesKfzAnlegen() {
		final String name = "AndererName";
		Kfz kfz = new Kfz(name);
		assertEquals(name, kfz.getName());
	}
	
	@Test
	public void nullNameNichtErlaubt() {
		assertThrows(IllegalArgumentException.class, () -> new Kfz(null));
		
	}

	@Test
	public void leererNameNichtErlaubt() {
		assertThrows(IllegalArgumentException.class, () -> new Kfz(""));
	}

	@Test
	public void nullStationEintragenNichtErlaubt() {
		Kfz kfz = new Kfz("Golf");
		assertThrows(NullPointerException.class, () -> kfz.setStation(null));
	}
	
	@Test
	public void nullReservierungEinfuegenHatKeinenEffekt() {
		Kfz kfz = new Kfz("Golf");
		kfz.addReservierung(null);
		assertTrue(kfz.istFrei(DateUtil.toDate("23.03.2022 10:00"), DateUtil.toDate("25.03.2022 12:00")));
	}
	
	@Test
	public void kfzOhneStationIstNichtAnStation() {
		Kfz kfz = new Kfz("Name");
		assertFalse(kfz.istAn(new Station("Station")));
	}
	
	@Test
	public void kfzMitStationIstNurAnDieserStation() {
		Station station = new Station("Station");
		Kfz kfz = new Kfz("Name", station);
		assertTrue(kfz.istAn(station));
		assertFalse(kfz.istAn(new Station("Station")));
	}
	
	@Test
	public void kfzIstNichtAnStationNull() {
		Kfz kfz = new Kfz("Name", new Station("Station"));
		assertFalse(kfz.istAn(null));
	}
	
	@Test
	public void kfzOhneStationIstNichtAnStationNull() {
		Kfz kfz = new Kfz("Name");
		assertFalse(kfz.istAn(null));
	}
	
	@Test
	public void kfzOhneKategorieHatKeineKategorie() {
		Kfz kfz = new Kfz("Name", new Station("Station"));
		assertFalse(kfz.hatKategorie(Kategorie.KLEINWAGEN));
		assertFalse(kfz.hatKategorie(Kategorie.EXTRA));
		assertFalse(kfz.hatKategorie(Kategorie.KOMPAKT));
		assertFalse(kfz.hatKategorie(Kategorie.KOMFORT));
	}

	@Test
	public void kfzHatNurEineKategorie() {
		Kfz kfz = new Kfz("Name", new Station("XYZ"), Kategorie.KLEINWAGEN);
		assertTrue(kfz.hatKategorie(Kategorie.KLEINWAGEN));
		assertFalse(kfz.hatKategorie(Kategorie.EXTRA));
		assertFalse(kfz.hatKategorie(Kategorie.KOMPAKT));
		assertFalse(kfz.hatKategorie(Kategorie.KOMFORT));
	}
	
	@Test
	public void kategorieNachtraeglichEintragen() {
		Kfz kfz = new Kfz("Name");
		assertFalse(kfz.hatKategorie(Kategorie.EXTRA));
		kfz.setKategorie(Kategorie.EXTRA);
		assertTrue(kfz.hatKategorie(Kategorie.EXTRA));
		kfz.setKategorie(Kategorie.KOMPAKT);
		assertTrue(kfz.hatKategorie(Kategorie.KOMPAKT));
	}
}
