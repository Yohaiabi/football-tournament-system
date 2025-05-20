package core;

import utils.E_Levels;

import java.net.URL;
import java.util.HashSet;
import java.util.Objects;

/**
 * Represents a customer (fan) in the system.
 */
public class Customer extends Person implements Comparable<Customer> {

    private URL email;
    private E_Levels level;
    private Team favoriteTeam;
    private Player player;
    private HashSet<Match> matches = new HashSet<>();

    public Customer(String pId, String fullName, int age, String nation, URL email, E_Levels level, Team favoriteTeam) {
        super(pId, fullName, age, nation);
        this.email = email;
        this.level = level;
        this.favoriteTeam = favoriteTeam;
    }

    public Customer(String pId) {
        super(pId);
    }

    public URL getEmail() {
        return email;
    }

    public void setEmail(URL email) {
        this.email = email;
    }

    public E_Levels getLevel() {
        return level;
    }

    public void setLevel(E_Levels level) {
        this.level = level;
    }

    public Team getFavoriteTeam() {
        return favoriteTeam;
    }

    public void setFavoriteTeam(Team favoriteTeam) {
        this.favoriteTeam = favoriteTeam;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public HashSet<Match> getMatches() {
        return matches;
    }

    public void setMatches(HashSet<Match> matches) {
        this.matches = matches;
    }

    @Override
    public int compareTo(Customer o) {
        return Integer.compare(this.level.getLevel(), o.level.getLevel());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!super.equals(o)) return false;
        if (getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(email, customer.email)
                && level == customer.level
                && Objects.equals(favoriteTeam, customer.favoriteTeam)
                && Objects.equals(player, customer.player)
                && Objects.equals(matches, customer.matches);
    }

    @Override
    public String toString() {
        return super.toString() + String.format(", Email: %s, Level: %s, Favorite Team: %s",
                email != null ? email.toString() : "No Email.",
                level != null ? level.name() : "No Level.",
                favoriteTeam != null ? favoriteTeam.gettName() : "No Favorite Team.");
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), email, level, favoriteTeam, player, matches);
    }
}