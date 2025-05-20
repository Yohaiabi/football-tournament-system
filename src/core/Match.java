package core;

import java.text.SimpleDateFormat;
import java.util.*;
import utils.Stadium;

/**
 * Represents a soccer match, including details like date, location, result, and participants.
 */
public class Match implements Comparable<Match> {

    private String mId;
    private Date mDate;
    private Stadium tookPlace;
    private long totalTickets;
    private MatchResult result;
    private List<Player> players = new ArrayList<>();
    private HashSet<Customer> fans = new HashSet<>();

    public Match(String mId, Date mDate, String tookPlace, long totalTickets, MatchResult result) {
        this.mId = mId;
        this.mDate = mDate;
        this.tookPlace = Stadium.getStadiumByName(tookPlace);
        this.totalTickets = totalTickets;
        this.result = result;
    }

    public Match(String mId) {
        this.mId = mId;
    }

    public String getmId() {
        return mId;
    }

    public void setmId(String mId) {
        this.mId = mId;
    }

    public Date getmDate() {
        return mDate;
    }

    public void setmDate(Date mDate) {
        this.mDate = mDate;
    }

    public Stadium getTookPlace() {
        return tookPlace;
    }

    public void setTookPlace(String tookPlace) {
        this.tookPlace = Stadium.getStadiumByName(tookPlace);
    }

    public void setTookPlace(Stadium tookPlace) {
        this.tookPlace = tookPlace;
    }

    public long getTotalTickets() {
        return totalTickets;
    }

    public void setTotalTickets(long totalTickets) {
        this.totalTickets = totalTickets;
    }

    public MatchResult getResult() {
        return result;
    }

    public void setResult(MatchResult result) {
        this.result = result;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public HashSet<Customer> getFans() {
        return fans;
    }

    public void setFans(HashSet<Customer> fans) {
        this.fans = fans;
    }

    @Override
    public int compareTo(Match o) {
        return this.mDate.compareTo(o.mDate);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Match)) return false;
        Match match = (Match) o;
        return Objects.equals(mId, match.mId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mId);
    }

    @Override
    public String toString() {
        String base = String.format(
                "Match ID: %s | Date: %s | Stadium: %s (%s) | Tickets: %d",
                mId,
                new SimpleDateFormat("dd/MM/yyyy").format(mDate),
                tookPlace != null ? tookPlace.name() : "Unknown.",
                tookPlace != null ? tookPlace.getCity() : "Unknown.",
                totalTickets
        );

        if (result != null) {
            base += String.format(
                    " | Result: %s %d - %d %s",
                    result.getHomeTeam().gettName(),
                    result.getHomeTeamScore(),
                    result.getAwayTeamScore(),
                    result.getAwayTeam().gettName()
            );
        }

        return base;
    }
}