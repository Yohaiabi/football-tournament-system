package core;

import java.io.Serializable;
import java.util.*;

import utils.E_Position;

public class Player extends Person implements Comparable<Player>, Serializable {
    private int pNum;
    private E_Position position;
    private int fansCount;
    private Team currentTeam;
    private HashSet<Trophy<?>> trophies = new HashSet<>();
    private HashSet<Customer> fans = new HashSet<>();
    private List<Match> matches = new ArrayList<>();

    public Player(String pId) {
        super(pId);
    }

    public Player(String pId, String fullName, int age, String nation, int pNum,
                  E_Position position, int fansCount, Team currentTeam) {
        super(pId, fullName, age, nation);
        this.pNum = pNum;
        this.position = position;
        this.fansCount = fansCount;
        this.currentTeam = currentTeam;
    }

    public int getpNum() {
        return pNum;
    }

    public void setpNum(int pNum) {
        this.pNum = pNum;
    }

    public E_Position getPosition() {
        return position;
    }

    public void setPosition(E_Position position) {
        this.position = position;
    }

    public int getFansCount() {
        return fansCount;
    }

    public void setFansCount(int fansCount) {
        this.fansCount = fansCount;
    }

    public Team getCurrentTeam() {
        return currentTeam;
    }

    public void setCurrentTeam(Team currentTeam) {
        this.currentTeam = currentTeam;
    }

    public HashSet<Trophy<?>> getTrophies() {
        return trophies;
    }

    public void setTrophies(HashSet<Trophy<?>> trophies) {
        this.trophies = trophies;
    }

    public HashSet<Customer> getFans() {
        return fans;
    }

    public void setFans(HashSet<Customer> fans) {
        this.fans = fans;
    }

    public List<Match> getMatches() {
        return matches;
    }

    public void setMatches(List<Match> matches) {
        this.matches = matches;
    }

    public boolean transferTo(Team team) {
        if (team == null || team.equals(this.currentTeam))
            return false;
        this.currentTeam = team;
        return true;
    }
    @Override
    public String toString() {
        return super.toString() + String.format(", Number: %d, Position: %s, Fans: %d, Team: %s.",
                pNum, position, fansCount,
                currentTeam != null ? currentTeam.toString() : "No Team.");
    }

    @Override
    public int compareTo(Player o) {
        return this.getpId().compareTo(o.getpId());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!super.equals(o)) return false;
        if (getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return pId.equals(player.pId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), pId);
    }
}