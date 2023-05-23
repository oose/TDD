package de.oose.stattauto.geschaeftslogik;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import de.oose.stattauto.geschaeftslogik.util.DateUtil;

public class ReservierungTest {

	private static final LocalDateTime reservierungsbeginn = DateUtil.toDate("23.02.2022 12:10");
	private static final LocalDateTime reservierungsende = DateUtil.toDate("28.02.2022 15:00");
	private Kfz kfz = new Kfz("Golf");
	private Mitglied mitglied = new Mitglied("Peter Meier", 1);

	@Test
	public void reservierungNullMinutenNichtErlaubt() {
		assertThrows(IllegalArgumentException.class, 
				() -> Reservierung.create(reservierungsbeginn, reservierungsbeginn, kfz, mitglied)
		);
	}
	
	@Test
	public void reservierungVonNachBisNichtErlaubt() {
		assertThrows(IllegalArgumentException.class, 
				() -> Reservierung.create(reservierungsende, reservierungsbeginn, kfz, mitglied)
		);
	}
	
	@Test
	public void reservierungVonNullNichtErlaubt() {
		assertThrows(NullPointerException.class, 
				() -> Reservierung.create(null, reservierungsende, kfz, mitglied)
		);
	}
	
	@Test
	public void reservierungBisNullNichtErlaubt() {
		assertThrows(NullPointerException.class, 
				() -> Reservierung.create(reservierungsbeginn, null, kfz, mitglied)
		);
	}
	
	@Test
	public void reservierungKfzNullNichtErlaubt() {
		assertThrows(NullPointerException.class, 
				() -> Reservierung.create(reservierungsbeginn, reservierungsende, null, mitglied)
		);
	}
	
	@Test
	public void reservierungMitgliedNullNichtErlaubt() {
		assertThrows(NullPointerException.class, 
				() -> Reservierung.create(reservierungsbeginn, reservierungsende, kfz, null)
		);
	}

	@Test
	@DisplayName("Erzeugte Reservierung ist am Mitglied verfügbar")
	public void mitgliedHatDieReservierung() {
		Reservierung reservierung = Reservierung.create(reservierungsbeginn, reservierungsende, kfz, mitglied);
		assertTrue(mitglied.getReservierungen().contains(reservierung));
	}
}
