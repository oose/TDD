package de.oose.stattauto.geschaeftslogik;

import java.time.LocalDateTime;

import com.google.common.base.Preconditions;

public class Reservierung {

	private final LocalDateTime von;
	private final LocalDateTime bis;
	
	public static Reservierung create(LocalDateTime von, LocalDateTime bis, Kfz kfz, Mitglied mitglied) {
		Preconditions.checkNotNull(von);
		Preconditions.checkNotNull(bis);
		Preconditions.checkNotNull(kfz);
		Preconditions.checkNotNull(mitglied);
		Preconditions.checkArgument(von.isBefore(bis), "von < bis");

		Reservierung reservierung = new Reservierung(von, bis);
		kfz.addReservierung(reservierung);
		mitglied.addReservierung(reservierung);
		return reservierung;
	}
	
	private Reservierung(LocalDateTime von, LocalDateTime bis) {
		this.von = von;
		this.bis = bis;
	}

	public boolean ueberlappt(LocalDateTime anfrageVon, LocalDateTime anfrageBis) {
		boolean zeitraumLiegtVorher = anfrageBis.isBefore(this.von);
		boolean zeitraumLiegtHinterher = anfrageVon.isAfter(this.bis);
		return !zeitraumLiegtVorher && !zeitraumLiegtHinterher;
	}

}
