package core;

import utils.E_Levels;

import java.util.HashSet;
import java.util.Objects;

/**
 * Represents a coach in the system.
 */
public class Coach extends Person {

    private Team mainTeam;
    private E_Levels level;
    private Trophy<?> trophy;
    private HashSet<Team> teams = new HashSet<>();

    public Coach(String pId, String fullName, int age, String nation, Team mainTeam, E_Levels level) {
        super(pId, fullName, age, nation);
        this.mainTeam = mainTeam;
        this.level = level;
    }

    public Coach(String pId) {
        super(pId);
    }

    public Team getMainTeam() {
        return mainTeam;
    }

    public void setMainTeam(Team mainTeam) {
        this.mainTeam = mainTeam;
    }

    public E_Levels getLevel() {
        return level;
    }

    public void setLevel(E_Levels level) {
        this.level = level;
    }

    public Trophy<?> getTrophy() {
        return trophy;
    }

    public void setTrophy(Trophy<?> trophy) {
        this.trophy = trophy;
    }

    public HashSet<Team> getTeams() {
        return teams;
    }

    public void setTeams(HashSet<Team> teams) {
        this.teams = teams;
    }

    /**
     * Transfers the coach to a new main team.
     *
     * @param team the new team
     * @return true if transfer was successful
     */
    public boolean transferTo(Team team) {
        if (team == null || this.mainTeam.equals(team)) {
            return false;
        }
        setMainTeam(team);
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!super.equals(o)) return false;
        if (getClass() != o.getClass()) return false;
        Coach coach = (Coach) o;
        return Objects.equals(mainTeam, coach.mainTeam)
                && level == coach.level
                && Objects.equals(trophy, coach.trophy)
                && Objects.equals(teams, coach.teams);
    }
    @Override
    public String toString() {
        return super.toString() + String.format(", Team: %s, Level: %s",
                mainTeam != null ? mainTeam.gettName() : "No Team.",
                level != null ? level.name() : "No Level.");
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), mainTeam, level, trophy, teams);
    }
}