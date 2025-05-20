package core;

import java.util.Objects;

/**
 * Represents the result of a soccer match.
 */
public class MatchResult {

	private final Team homeTeam;
	private final Team awayTeam;
	private final String matchID;
	private final int totalTime;
	private final boolean penaltyEnd;
	private final int homeTeamScore;
	private final int awayTeamScore;

	public MatchResult(
			Team homeTeam,
			Team awayTeam,
			String matchID,
			int totalTime,
			boolean penaltyEnd,
			int homeTeamScore,
			int awayTeamScore
	) {
		this.homeTeam = homeTeam;
		this.awayTeam = awayTeam;
		this.matchID = matchID;
		this.totalTime = totalTime;
		this.penaltyEnd = penaltyEnd;
		this.homeTeamScore = homeTeamScore;
		this.awayTeamScore = awayTeamScore;
	}

	public Team getHomeTeam() {
		return homeTeam;
	}

	public Team getAwayTeam() {
		return awayTeam;
	}

	public String getMatchID() {
		return matchID;
	}

	public int getTotalTime() {
		return totalTime;
	}

	public boolean isPenaltyEnd() {
		return penaltyEnd;
	}

	public int getHomeTeamScore() {
		return homeTeamScore;
	}

	public int getAwayTeamScore() {
		return awayTeamScore;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (!(obj instanceof MatchResult)) return false;
		MatchResult other = (MatchResult) obj;
		return totalTime == other.totalTime &&
				penaltyEnd == other.penaltyEnd &&
				homeTeamScore == other.homeTeamScore &&
				awayTeamScore == other.awayTeamScore &&
				Objects.equals(homeTeam, other.homeTeam) &&
				Objects.equals(awayTeam, other.awayTeam) &&
				Objects.equals(matchID, other.matchID);
	}

	@Override
	public String toString() {
		return String.format("Match Result - %s vs %s: %d-%d, Duration: %d min%s",
				homeTeam != null ? homeTeam.gettName() : "Unknown.",
				awayTeam != null ? awayTeam.gettName() : "Unknown.",
				homeTeamScore,
				awayTeamScore,
				totalTime,
				penaltyEnd ? ", Penalty Shootout" : "");
	}

	@Override
	public int hashCode() {
		return Objects.hash(homeTeam, awayTeam, matchID, totalTime, penaltyEnd, homeTeamScore, awayTeamScore);
	}
}