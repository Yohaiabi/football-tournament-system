package core;

import java.util.Objects;
import java.util.TreeSet;

import utils.Country;

public class Team {

    private String tNumber;
    private String tName;
    private int fansCount;
    private Sponsor sponsor;
    private Country represents;
    private Coach coach;
    private Trophy<?> trophy;
    private TreeSet<Player> players = new TreeSet<>();
    private TreeSet<Match> matches = new TreeSet<>();

    public Team(String tNumber) {
        this.tNumber = tNumber;
    }

    public Team(String tNumber, String tName, int fansCount, String represents) {
        this.tNumber = tNumber;
        this.tName = tName;
        this.fansCount = fansCount;
        setRepresents(represents);
    }

    public String gettNumber() {
        return tNumber;
    }

    public void settNumber(String tNumber) {
        this.tNumber = tNumber;
    }

    public String gettName() {
        return tName;
    }

    public void settName(String tName) {
        this.tName = tName;
    }

    public int getFansCount() {
        return fansCount;
    }

    public void setFansCount(int fansCount) {
        this.fansCount = fansCount;
    }

    public Country getRepresents() {
        return represents;
    }

    public void setRepresents(String represents) {
        this.represents = Country.getCountryByName(represents);
    }

    public Sponsor getSponsor() {
        return sponsor;
    }

    public void setSponsor(Sponsor sponsor) {
        this.sponsor = sponsor;
    }

    public Coach getCoach() {
        return coach;
    }

    public void setCoach(Coach coach) {
        this.coach = coach;
    }

    public Trophy<?> getTrophy() {
        return trophy;
    }

    public void setTrophy(Trophy<?> trophy) {
        this.trophy = trophy;
    }

    public TreeSet<Player> getPlayers() {
        return players;
    }

    public void setPlayers(TreeSet<Player> players) {
        this.players = players;
    }

    public TreeSet<Match> getMatches() {
        return matches;
    }

    public void setMatches(TreeSet<Match> matches) {
        this.matches = matches;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Team team = (Team) o;
        return tNumber.equals(team.tNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tNumber);
    }

    @Override
    public String toString() {
        return String.format(
                "Team [%s] - %s | Country: %s | Coach: %s | Players: %d",
                tNumber,
                tName,
                represents != null ? represents.name() : "Unknown.",
                coach != null ? coach.getFullName() : "No Coach.",
                players.size()
        );
    }
}
