package core;

import utils.E_Trophy;

import java.util.Date;
import java.util.Objects;

public class Trophy<T> implements Comparable<Trophy<T>> {

    private E_Trophy trophy;
    private T owner;
    private Date trophyWinningDate;
    private Player player;
    private Coach coach;

    public Trophy(E_Trophy trophy, T owner, Date trophyWinningDate) {
        this.trophy = trophy;
        this.owner = owner;
        this.trophyWinningDate = trophyWinningDate;
    }

    public E_Trophy getTrophy() {
        return trophy;
    }

    public void setTrophy(E_Trophy trophy) {
        this.trophy = trophy;
    }

    public T getOwner() {
        return owner;
    }

    public void setOwner(T owner) {
        this.owner = owner;
    }

    public Date getTrophyWinningDate() {
        return trophyWinningDate;
    }

    public void setTrophyWinningDate(Date trophyWinningDate) {
        this.trophyWinningDate = trophyWinningDate;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Coach getCoach() {
        return coach;
    }

    public void setCoach(Coach coach) {
        this.coach = coach;
    }

    @Override
    public int compareTo(Trophy<T> other) {
        return this.trophyWinningDate.compareTo(other.trophyWinningDate);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Trophy)) return false;
        Trophy<?> trophy1 = (Trophy<?>) o;
        return trophy == trophy1.trophy &&
                Objects.equals(owner, trophy1.owner) &&
                Objects.equals(trophyWinningDate, trophy1.trophyWinningDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(trophy, owner, trophyWinningDate);
    }
}