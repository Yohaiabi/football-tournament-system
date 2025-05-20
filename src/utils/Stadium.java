package utils;

/**
 * Enum representing stadiums, their capacity, country, and city.
 */
public enum Stadium {

	NationalStadium(56070, Country.POLAND, "Warsaw"),
	PGE_Arena(39150, Country.POLAND, "Gdansk"),
	MunicipalStadium(40000, Country.POLAND, "Wrocław"),
	MunicipalRStadium(39550, Country.POLAND, "Poznań"),
	OlympicStadium(64640, Country.UKRAINE, "Kiev"),
	DonbassArena(49400, Country.UKRAINE, "Donetsk"),
	MetalistStadium(37750, Country.UKRAINE, "Kharkiv"),
	ArenaLviv(32990, Country.UKRAINE, "Lviv");

	private final int capacity;
	private final Country country;
	private final String city;

	Stadium(int capacity, Country country, String city) {
		this.capacity = capacity;
		this.country = country;
		this.city = city;
	}

	public int getCapacity() {
		return capacity;
	}

	public Country getCountry() {
		return country;
	}

	public String getCity() {
		return city;
	}

	/**
	 * Returns a stadium by its name.
	 *
	 * @param stadium the name of the stadium
	 * @return Stadium if found, otherwise null
	 */
	public static Stadium getStadiumByName(String stadium) {
		for (Stadium s : Stadium.values()) {
			if (s.name().equals(stadium)) {
				return s;
			}
		}
		return null;
	}

	public static Stadium getStadiumByCity(String city) {
		for (Stadium s : Stadium.values()) {
			if (s.getCity().equalsIgnoreCase(city)) {
				return s;
			}
		}
		return null;
	}

	public String printStadium() {
		return String.format("Stadium: %s\n\tCountry: %s\n\tCapacity: %d\n\tCity: %s",
				this.name(), getCountry(), getCapacity(), getCity());
	}
}