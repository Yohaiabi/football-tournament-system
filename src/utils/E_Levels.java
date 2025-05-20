package utils;

/**
 * Enum representing skill levels used in the system.
 */
public enum E_Levels {
	BEGINNER(1),
	INTERMEDIATE(2),
	ADVANCED(3),
	PROFESSIONAL(4);

	private final int level;

	E_Levels(int level) {
		this.level = level;
	}

	public int getLevel() {
		return level;
	}

	/**
	 * Returns the level enum based on the given integer value.
	 * 0 or 1 → BEGINNER, 2 → INTERMEDIATE, 3 → ADVANCED, otherwise → PROFESSIONAL
	 *
	 * @param val the numeric level value
	 * @return E_Levels enum
	 */
	public static E_Levels returnLevel(int val) {
        return switch (val) {
            case 0, 1 -> BEGINNER;
            case 2 -> INTERMEDIATE;
            case 3 -> ADVANCED;
            default -> PROFESSIONAL;
        };
	}
}