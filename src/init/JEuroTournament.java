package init;

import java.net.URL;
import java.util.*;

import core.*;
import utils.*;

public class JEuroTournament {

	private static JEuroTournament instance;
	private HashMap<String, Player> players;
	private HashMap<String, Coach> coaches;
	private HashMap<String, Customer> customers;
	private HashMap<String, Sponsor> sponsors;
	private HashMap<String, Team> teams;
	private HashMap<String, Match> matches;
	private HashMap<String, MatchResult> matchResults;
	private HashSet<Trophy<?>> trophies;

	public JEuroTournament() {
		teams = new HashMap<>();
		matches = new HashMap<>();
		players = new HashMap<>();
		coaches = new HashMap<>();
		customers = new HashMap<>();
		sponsors = new HashMap<>();
		matchResults = new HashMap<>();
		trophies = new HashSet<>();
	}

	public static JEuroTournament getInstance() {
		if (instance == null)
			instance = new JEuroTournament();
		return instance;
	}

	public HashMap<String, Player> getPlayers() { return players; }
	public HashMap<String, Coach> getCoaches() { return coaches; }
	public HashMap<String, Customer> getCustomers() { return customers; }
	public HashMap<String, Sponsor> getSponsors() { return sponsors; }
	public HashMap<String, Team> getTeams() { return teams; }
	public HashMap<String, Match> getMatches() { return matches; }
	public HashMap<String, MatchResult> getMatchResults() { return matchResults; }
	public HashSet<Trophy<?>> getTrophies() { return trophies; }

	// Adds a new player to the system
	public boolean addPlayer(String pId, String fullName, int age, String nation, int pNum, E_Position position, int fansCount, Team currentTeam) {
		if (pId == null || fullName == null || age <= 0 || nation == null || pNum <= 0 || position == null || fansCount <= 0 || currentTeam == null)
			return false;
		if (players.containsKey(pId))
			return false;
		Player toPlayer = new Player(pId, fullName, age, nation, pNum, position, fansCount, currentTeam);
		players.put(pId, toPlayer);
		return true;
	}

	// Adds a new coach
	public boolean addCoach(String pId, String fullName, int age, String nation, Team currentTeam, E_Levels level) {
		if (pId == null || fullName == null || age <= 0 || nation == null || level == null || currentTeam == null)
			return false;
		if (coaches.containsKey(pId))
			return false;
		Coach toCoach = new Coach(pId, fullName, age, nation, currentTeam, level);
		coaches.put(pId, toCoach);
		return true;
	}

	// Adds a new customer
	public boolean addCustomer(String pId, String fullName, int age, String nation, URL email, E_Levels level, Team favoriteTeam) {
		if (pId == null || fullName == null || age <= 0 || nation == null || email == null || level == null || favoriteTeam == null)
			return false;
		if (customers.containsKey(pId))
			return false;
		Customer toCustomer = new Customer(pId, fullName, age, nation, email, level, favoriteTeam);
		customers.put(pId, toCustomer);
		return true;
	}

	// Adds a new sponsor
	public boolean addSponsor(String pId, String fullName, int age, String nation, String nickName) {
		if (pId == null || fullName == null || age <= 0 || nation == null || nickName == null)
			return false;
		if (sponsors.containsKey(pId)) // Fixed to check sponsor ID instead of nickname
			return false;
		Sponsor sponsor = new Sponsor(pId, fullName, age, nation, nickName);
		sponsors.put(pId, sponsor);
		return true;
	}

	// Adds a new team
	public boolean addTeam(String tNumber, String tName, int fansCount, String represents) {
		if (tNumber == null || tName == null || represents == null || fansCount <= 0)
			return false;
		if (teams.containsKey(tNumber))
			return false;
		Team toTeam = new Team(tNumber, tName, fansCount, represents);
		teams.put(tNumber, toTeam);
		return true;
	}

	// Adds a new match
	public boolean addMatch(String matchID, Date date, String stadium, long totalTickets, MatchResult matchResult) {
		if (matchID == null || date == null || stadium == null || totalTickets <= 0)
			return false;
		if (matches.containsKey(matchID))
			return false;
		Match toMatch = new Match(matchID, date, stadium, totalTickets, matchResult);
		matches.put(matchID, toMatch);
		return true;
	}

	// Adds a match result
	public boolean addMatchResult(Team homeTeam, Team awayTeam, String matchId, int homeTeamScore, int awayTeamScore, int totalTime, boolean penaltyEnd) {
		if (homeTeam == null || awayTeam == null || matchId == null || homeTeamScore < 0 || awayTeamScore < 0 || totalTime < 0)
			return false;
		if (matchResults.containsKey(matchId))
			return false;
		MatchResult result = new MatchResult(homeTeam, awayTeam, matchId, totalTime, penaltyEnd, homeTeamScore, awayTeamScore);
		matchResults.put(matchId, result);
		return true;
	}

	// Adds a trophy for a given owner
	public <T> boolean addTrophy(E_Trophy trophy, T owner, Date trophyWinningDate) {
		if (trophy == null || owner == null || trophyWinningDate == null)
			return false;
		Trophy<T> newTrophy = new Trophy<>(trophy, owner, trophyWinningDate);
		if (trophies.contains(newTrophy))
			return false;
		trophies.add(newTrophy);
		return true;
	}

	// Links a team to a coach
	public boolean addTeamToCoach(String teamId, String coachId) {
		Team team = teams.get(teamId);
		Coach coach = coaches.get(coachId);
		if (team != null && coach != null) {
			coach.getTeams().add(team);
			return true;
		}
		return false;
	}

	// Links a match to a player
	public boolean addMatchToPlayer(String matchId, String playerId) {
		Match match = matches.get(matchId);
		Player player = players.get(playerId);
		if (match != null && player != null && !player.getMatches().contains(match)) {
			player.getMatches().add(match);
			return true;
		}
		return false;
	}

	// Links a team to a sponsor
	public boolean addTeamToSponsor(String teamId, String sponsorId) {
		Team team = teams.get(teamId);
		Sponsor sponsor = sponsors.get(sponsorId);
		if (team != null && sponsor != null && sponsor.getTeams().size() < 3) {
			sponsor.getTeams().add(team);
			team.setSponsor(sponsor); // Ensure sponsor is also set on the team
			return true;
		}
		return false;
	}

	// Adds a player to a team
	public boolean addPlayerToTeam(String teamId, String playerId) {
		Team team = teams.get(teamId);
		Player player = players.get(playerId);
		if (team != null && player != null && team.getPlayers().size() < 20) {
			team.getPlayers().add(player);
			return true;
		}
		return false;
	}

	// Links a match to a team
	public boolean addMatchToTeam(String matchId, String teamId) {
		Match match = matches.get(matchId);
		Team team = teams.get(teamId);
		if (match != null && team != null) {
			team.getMatches().add(match);
			return true;
		}
		return false;
	}

	// Adds a customer to a match
	public boolean addCostumerToMatch(String costumerId, String matchId) {
		Customer customer = customers.get(costumerId);
		Match match = matches.get(matchId);
		if (customer != null && match != null && match.getFans().size() < match.getTotalTickets()) {
			match.getFans().add(customer);
			return true;
		}
		return false;
	}

	// Returns best customers (based on attendance count)
	public Collection<Customer> getTheBestCustomer() {
		Map<Customer, Integer> attendanceCount = new HashMap<>();
		for (Match match : matches.values()) {
			for (Customer c : match.getFans()) {
				attendanceCount.put(c, attendanceCount.getOrDefault(c, 0) + 1);
			}
		}
		int max = attendanceCount.values().stream().max(Integer::compareTo).orElse(0);
		HashSet<Customer> best = new HashSet<>();
		for (Map.Entry<Customer, Integer> entry : attendanceCount.entrySet()) {
			if (entry.getValue() == max) {
				best.add(entry.getKey());
			}
		}
		return best;
	}

	// Returns the match with the most tickets sold
	public Match getTheBestHomeMatch() {
		Match best = null;
		for (Match m : matches.values()) {
			if (best == null || m.getTotalTickets() > best.getTotalTickets())
				best = m;
		}
		return best;
	}

	// Returns the most active player (by number of matches)
	public Player getMostActivePlayer() {
		Player mostActive = null;
		for (Player p : players.values()) {
			if (mostActive == null || p.getMatches().size() > mostActive.getMatches().size())
				mostActive = p;
		}
		return mostActive;
	}

	// Returns sponsors that support teams from Germany
	public Collection<Sponsor> getSponsorsOfGermany() {
		Set<Sponsor> result = new HashSet<>();
		for (Team team : teams.values()) {
			if (team.getRepresents() == Country.GERMANY && team.getSponsor() != null) {
				result.add(team.getSponsor());
			}
		}
		return result;
	}

	// Returns the entity that has won the most trophies
	public Object getEntityWithMostTrophies() {
		Map<Object, Integer> countMap = new HashMap<>();
		for (Trophy<?> t : trophies) {
			countMap.put(t.getOwner(), countMap.getOrDefault(t.getOwner(), 0) + 1);
		}
		return countMap.entrySet().stream()
				.max(Map.Entry.comparingByValue())
				.map(Map.Entry::getKey)
				.orElse(null);
	}

	// Returns the team(s) with the most home wins
	public Collection<Team> getTeamsBestHomeScore() {
		Map<String, Integer> wins = new HashMap<>();
		for (Match m : matches.values()) {
			MatchResult r = m.getResult();
			if (r != null && r.getHomeTeamScore() > r.getAwayTeamScore()) {
				String id = r.getHomeTeam().gettNumber();
				wins.put(id, wins.getOrDefault(id, 0) + 1);
			}
		}
		int maxWins = wins.values().stream().max(Integer::compareTo).orElse(0);
		HashSet<Team> result = new HashSet<>();
		for (Map.Entry<String, Integer> entry : wins.entrySet()) {
			if (entry.getValue() == maxWins)
				result.add(teams.get(entry.getKey()));
		}
		return result;
	}
}