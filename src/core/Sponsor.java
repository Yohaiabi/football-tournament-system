package core;

import java.util.HashSet;
import java.util.Objects;

public class Sponsor extends Person implements Comparable<Sponsor> {
    private String nickName;
    private HashSet<Team> teams = new HashSet<>();

    public Sponsor(String pId) {
        super(pId);
    }

    public Sponsor(String pId, String fullName, int age, String nation, String nickName) {
        super(pId, fullName, age, nation);
        this.nickName = nickName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public HashSet<Team> getTeams() {
        return teams;
    }

    public void setTeams(HashSet<Team> teams) {
        this.teams = teams;
    }

    @Override
    public int compareTo(Sponsor o) {
        return this.getpId().compareTo(o.getpId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), nickName);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        Sponsor other = (Sponsor) obj;
        return Objects.equals(nickName, other.nickName);
    }
    @Override
    public String toString() {
        return super.toString() + String.format(" [Nickname: %s, Sponsored Teams: %d]",
                nickName,
                teams != null ? teams.size() : 0);
    }

}