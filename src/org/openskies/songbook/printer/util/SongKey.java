package org.openskies.songbook.printer.util;

import java.util.ArrayList;
import java.util.List;

/**
 * The Enum SongKey.
 */
public enum SongKey {
	// TODO Klären mit Barbara, ob dies korrekt ist
	
	/**                          keyMajor                   keyMinor                     kadenz1                    kadenz2                      kadenz3                      kadenz4                    kadenz5                    kadenz6                      kadenz7. */
	C_Am          (new String[] {"C"},        new String[] {"Am"},         new String[] {"C"},        new String[] {"Dm"},         new String[] {"Em"},         new String[] {"F"},        new String[] {"G"},        new String[] {"Am"},         new String[] {"E"}),
	F_Dm          (new String[] {"F"},        new String[] {"Dm"},         new String[] {"F"},        new String[] {"Gm"},         new String[] {"Am"},         new String[] {"Bb"},       new String[] {"C"},        new String[] {"Dm"},         new String[] {"A"}),
	Bb_Gm         (new String[] {"Bb"},       new String[] {"Gm"},         new String[] {"Bb"},       new String[] {"Cm"},         new String[] {"Dm"},         new String[] {"Eb"},       new String[] {"F"},        new String[] {"Gm"},         new String[] {"D"}),
	Eb_Cm         (new String[] {"Eb"},       new String[] {"Cm"},         new String[] {"Eb"},       new String[] {"Fm"},         new String[] {"Gm"},         new String[] {"Ab"},       new String[] {"Bb"},       new String[] {"Cm"},         new String[] {"G"}),
	Ab_Fm         (new String[] {"Ab"},       new String[] {"Fm"},         new String[] {"Ab"},       new String[] {"A#m", "Bbm"}, new String[] {"A#m"},        new String[] {"Cm"},       new String[] {"C#"},       new String[] {"Fm"},         new String[] {"C"}),
	Db_Bbm        (new String[] {"Db"},       new String[] {"Bbm"},        new String[] {"C#", "Db"}, new String[] {"D#m", "Ebm"}, new String[] {"Fm"},         new String[] {"F#", "Gb"}, new String[] {"Ab"},       new String[] {"A#m", "Bbm"}, new String[] {"F"}),
	Fx_Gb_Dxm_Ebm (new String[] {"F#", "Gb"}, new String[] {"D#m", "Ebm"}, new String[] {"F#", "Gb"}, new String[] {"G#m", "Abm"}, new String[] {"A#m", "Bbm"}, new String[] {"B", "Cb"},  new String[] {"C#", "Db"}, new String[] {"D#m", "Ebm"}, new String[] {"Bb"}),
	B_Gxm         (new String[] {"B"},        new String[] {"G#m"},        new String[] {"B", "Cb"},  new String[] {"C#m"},        new String[] {"D#m", "Ebm"}, new String[] {"E"},        new String[] {"F#", "Gb"}, new String[] {"G#m", "Abm"}, new String[] {"Eb"}),
	E_Cxm         (new String[] {"E"},        new String[] {"C#m"},        new String[] {"E"},        new String[] {"F#m"},        new String[] {"G#m", "Abm"}, new String[] {"A"},        new String[] {"B", "Cb"},  new String[] {"C#m"},        new String[] {"Ab"}),
	A_Fxm         (new String[] {"A"},        new String[] {"F#m"},        new String[] {"A"},        new String[] {"Bm"},         new String[] {"C#m"},        new String[] {"D"},        new String[] {"E"},        new String[] {"F#m"},        new String[] {"C#", "Db"}),
	D_Bm          (new String[] {"D"},        new String[] {"Bm"},         new String[] {"D"},        new String[] {"Em"},         new String[] {"F#m"},        new String[] {"G"},        new String[] {"A"},        new String[] {"Bm"},         new String[] {"F#", "Gb"}),
	G_Em          (new String[] {"G"},        new String[] {"Em"},         new String[] {"G"},        new String[] {"Am"},         new String[] {"Bm"},         new String[] {"C"},        new String[] {"D"},        new String[] {"Em"},         new String[] {"B", "Cb"});
	
	/** The key minor. */
	private final String[] keyMinor;
	
	/** The key major. */
	private final String[] keyMajor;
	
	/** The kadenz 1. */
	private final String[] kadenz1;
	
	/** The kadenz 2. */
	private final String[] kadenz2;
	
	/** The kadenz 3. */
	private final String[] kadenz3;
	
	/** The kadenz 4. */
	private final String[] kadenz4;
	
	/** The kadenz 5. */
	private final String[] kadenz5;
	
	/** The kadenz 6. */
	private final String[] kadenz6;
	
	/** The kadenz 7. */
	private final String[] kadenz7;
	
	public String[] getKeyMinor() {
		return keyMinor;
	}

	public String[] getKeyMajor() {
		return keyMajor;
	}

	/**
	 * Contains chords.
	 *
	 * @param chords the chords
	 * @return true, if successful
	 */
	public boolean containsChords(List<String> chords) {
		List<String> found = new ArrayList<String>();
		
		for (String chord : chords) {
			for (String k : kadenz1) {
				if (k.equals(chord)) {
					found.add(chord);
				}
			}
			for (String k : kadenz2) {
				if (k.equals(chord)) {
					found.add(chord);
				}
			}
			for (String k : kadenz3) {
				if (k.equals(chord)) {
					found.add(chord);
				}
			}
			for (String k : kadenz4) {
				if (k.equals(chord)) {
					found.add(chord);
				}
			}
			for (String k : kadenz5) {
				if (k.equals(chord)) {
					found.add(chord);
				}
			}
			for (String k : kadenz6) {
				if (k.equals(chord)) {
					found.add(chord);
				}
			}
			for (String k : kadenz7) {
				if (k.equals(chord)) {
					found.add(chord);
				}
			}	
		}
		
		if (chords.size()==found.size()) {
			return true;
		}
		
		return false;
	}
	
	/**
	 * Instantiates a new song key.
	 *
	 * @param keyMajor the key major
	 * @param keyMinor the key minor
	 * @param kadenz1 the kadenz 1
	 * @param kadenz2 the kadenz 2
	 * @param kadenz3 the kadenz 3
	 * @param kadenz4 the kadenz 4
	 * @param kadenz5 the kadenz 5
	 * @param kadenz6 the kadenz 6
	 * @param kadenz7 the kadenz 7
	 */
	SongKey(final String keyMajor[], final String keyMinor[], final String kadenz1[], final String kadenz2[], final String kadenz3[], final String kadenz4[], final String kadenz5[],
			final String kadenz6[], final String kadenz7[]) {
		this.keyMajor = keyMajor;
		this.keyMinor = keyMinor;
		this.kadenz1 = kadenz1;
		this.kadenz2 = kadenz2;
		this.kadenz3 = kadenz3;
		this.kadenz4 = kadenz4;
		this.kadenz5 = kadenz5;
		this.kadenz6 = kadenz6;
		this.kadenz7 = kadenz7;
	}

}