package de.oose.stattauto.geschaeftslogik;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.common.base.Preconditions;

public class StattAuto {

	private final Map<Integer, Mitglied> mitglieder = new HashMap<>();
	private final List<Kfz> fahrzeuge = new ArrayList<>();
	private final List<Station> stationen = new ArrayList<>();
	
	public void addKfz(Kfz kfz) {
		Preconditions.checkArgument(kfz != null);
		fahrzeuge.add(kfz);
	}

	public void addStation(Station station) {
		Preconditions.checkArgument(station != null);
		stationen.add(station);
	}
	
	public Collection<Station> getStationen() {
		return Collections.unmodifiableCollection(stationen);
	}

	public void addMitglied(Mitglied mitglied) throws MitgliedVorhandenException {
		Preconditions.checkArgument(mitglied != null);

		Integer mitgliedsnr = mitglied.getMitgliedsnr();
		if (mitglieder.containsKey(mitgliedsnr)) {
			throw new MitgliedVorhandenException(mitglieder.get(mitgliedsnr));
		}
		mitglieder.put(mitgliedsnr, mitglied);
	}

	public Mitglied getMitglied(int mitgliedsnr) {
		return mitglieder.get(mitgliedsnr);
	}

}
