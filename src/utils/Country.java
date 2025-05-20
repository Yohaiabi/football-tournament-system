package utils;

/**
 * Enum representing countries and their corresponding acronyms.
 */
public enum Country {

	POLAND("PO"),
	UKRAINE("UA"),
	GERMANY("DE"),
	RUSSIA("RU"),
	ITALY("IT"),
	FRANCE("FR"),
	NETHERLANDS("NL"),
	GREECE("GR"),
	ENGLAND("GB"),
	DENMARK("DK"),
	SPAIN("LT"),
	SWEDEN("SE"),
	CROATIA("HR"),
	CZECH_REPUBLIC("CZ"),
	PORTUGAL("PT"),
	IRELAND("IE"),
	CHINA("CI"),
	UNKNOWN("UN");

	private final String acronym;

	Country(String acronym) {
		this.acronym = acronym;
	}

	public String getAcronym() {
		return acronym;
	}

	/**
	 * Finds the country enum value by its name (case-sensitive).
	 *
	 * @param country the name of the country
	 * @return corresponding Country enum or UNKNOWN if not found
	 */
	public static Country getCountryByName(String country) {
		String normalized = country.toUpperCase();

		for (Country c : values()) {
			if (c.name().equalsIgnoreCase(normalized) || c.getAcronym().equalsIgnoreCase(normalized)) {
				return c;
			}
		}

		return UNKNOWN;
	}

	/**
	 * Returns a formatted string representation of the country.
	 */
	public String printCountry() {
		return String.format("Country: %s, %s", this.name(), getAcronym());
	}
}