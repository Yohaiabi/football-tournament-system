package init;

import java.util.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import utils.*;

import core.Player;
import core.Team;
import core.Customer;
import core.Match;
import core.MatchResult;
import core.Sponsor;

public class MainClass {

    public static void main(String[] args) throws IOException, ParseException {

        String command;
        boolean isUpdated;

        MyFileLogWriter.initializeMyFileWriter();

        DateFormat df = new SimpleDateFormat("d/M/yyyy");
        df.setTimeZone(TimeZone.getTimeZone("Asia/Jerusalem"));

        JEuroTournament jEuroTournament = JEuroTournament.getInstance();
        Scanner input = new Scanner(new File("input.txt"));

        while (input.hasNextLine() && jEuroTournament != null) {
            String line = input.nextLine().trim();
            String[] tokens = line.split("\\s+");
            if (tokens.length == 0) continue;

            command = tokens[0];

            // Process each command by name
            switch (command) {
                case "addTeam" -> {
                    if (tokens.length != 5) continue;
                    String tId = tokens[1], tName = tokens[2], represents = tokens[4];
                    int fansCount = Integer.parseInt(tokens[3]);
                    isUpdated = jEuroTournament.addTeam(tId, tName, fansCount, represents);
                    MyFileLogWriter.writeToFileInSeparateLine("addTeam returns:");
                    MyFileLogWriter.writeToFileInSeparateLine(isUpdated
                            ? "\tSuccessfully added team " + tId
                            : "\tFailed to add team " + tId);
                }

                case "addCoach" -> {
                    if (tokens.length != 8) continue;
                    String pId = tokens[1];
                    String fullName = tokens[2] + " " + tokens[3];
                    int age = Integer.parseInt(tokens[4]);
                    String nation = tokens[5];
                    Team currentTeam = jEuroTournament.getTeams().get(tokens[6]);
                    E_Levels level;
                    try {
                        // Try to parse as number first
                        int levelNum = Integer.parseInt(tokens[7]);
                        level = E_Levels.returnLevel(levelNum);
                    } catch (NumberFormatException e) {
                        // Fallback to enum name
                        level = E_Levels.valueOf(tokens[7].toUpperCase());
                    }
                    isUpdated = jEuroTournament.addCoach(pId, fullName, age, nation, currentTeam, level);
                    if (currentTeam != null) {
                        currentTeam.setCoach(jEuroTournament.getCoaches().get(pId));
                    }
                    MyFileLogWriter.writeToFileInSeparateLine("addCoach returns:");
                    MyFileLogWriter.writeToFileInSeparateLine(isUpdated
                            ? "\tSuccessfully added Coach " + pId
                            : "\tFailed adding Coach " + pId);
                }

                case "addPlayer" -> {
                    if (tokens.length != 10) continue;
                    String pId = tokens[1];
                    String fullName = tokens[2] + " " + tokens[3];
                    int age = Integer.parseInt(tokens[4]);
                    String nation = tokens[5];
                    int pNum = Integer.parseInt(tokens[6]);
                    E_Position position = E_Position.valueOf(tokens[7].toUpperCase());
                    int fansCount = Integer.parseInt(tokens[8]);
                    Team currentTeam = jEuroTournament.getTeams().get(tokens[9]);
                    isUpdated = jEuroTournament.addPlayer(pId, fullName, age, nation, pNum, position, fansCount, currentTeam);
                    if (currentTeam != null) {
                        currentTeam.getPlayers().add(jEuroTournament.getPlayers().get(pId));
                    }
                    MyFileLogWriter.writeToFileInSeparateLine("addPlayer returns:");
                    MyFileLogWriter.writeToFileInSeparateLine(isUpdated
                            ? "\tSuccessfully added Player " + pId
                            : "\tFailed adding Player " + pId);
                }

                case "addCustomer" -> {
                    if (tokens.length != 9) continue;
                    String pId = tokens[1];
                    String fullName = tokens[2] + " " + tokens[3];
                    int age = Integer.parseInt(tokens[4]);
                    String nation = tokens[5];
                    URL email = new URL(tokens[6]);
                    E_Levels level = E_Levels.valueOf(tokens[7]);
                    Team favoriteTeam = jEuroTournament.getTeams().get(tokens[8]);
                    isUpdated = jEuroTournament.addCustomer(pId, fullName, age, nation, email, level, favoriteTeam);
                    MyFileLogWriter.writeToFileInSeparateLine("addCustomer returns:");
                    MyFileLogWriter.writeToFileInSeparateLine(isUpdated
                            ? "\tSuccessfully added Customer " + pId
                            : "\tFailed adding Customer " + pId);
                }

                case "addSponsor" -> {
                    if (tokens.length != 7) continue;
                    String pId = tokens[1];
                    String fullName = tokens[2] + " " + tokens[3];
                    int age = Integer.parseInt(tokens[4]);
                    String nation = tokens[5];
                    String nickName = tokens[6];
                    isUpdated = jEuroTournament.addSponsor(pId, fullName, age, nation, nickName);
                    MyFileLogWriter.writeToFileInSeparateLine("addSponsor returns:");
                    MyFileLogWriter.writeToFileInSeparateLine(isUpdated
                            ? "\tSuccessfully added Sponsor " + pId
                            : "\tFailed adding Sponsor " + pId);
                }
                case "addTeamToSponsor" -> {
                    if (tokens.length != 3) continue;
                    isUpdated = jEuroTournament.addTeamToSponsor(tokens[1], tokens[2]);
                    MyFileLogWriter.writeToFileInSeparateLine("addTeamToSponsor returns:");
                    MyFileLogWriter.writeToFileInSeparateLine(isUpdated
                            ? "\tSponsor " + tokens[2] + " added to team " + tokens[1]
                            : "\tFailed to add sponsor " + tokens[2] + " to team " + tokens[1]);
                }

                case "addMatchResult" -> {
                    if (tokens.length != 8) continue;
                    Team homeTeam = jEuroTournament.getTeams().get(tokens[1]);
                    Team awayTeam = jEuroTournament.getTeams().get(tokens[2]);
                    String matchId = tokens[3];
                    int totalTime = Integer.parseInt(tokens[4]);
                    boolean penaltyEnd = Boolean.parseBoolean(tokens[5]);
                    int homeTeamScore = Integer.parseInt(tokens[6]);
                    int awayTeamScore = Integer.parseInt(tokens[7]);
                    isUpdated = jEuroTournament.addMatchResult(homeTeam, awayTeam, matchId, homeTeamScore, awayTeamScore, totalTime, penaltyEnd);
                    MyFileLogWriter.writeToFileInSeparateLine("addMatchResult returns:");
                    MyFileLogWriter.writeToFileInSeparateLine(isUpdated
                            ? "\tSuccessfully added MatchResult"
                            : "\tFailed adding MatchResult");
                }
                case "addMatchToPlayer" -> {
                    if (tokens.length != 3) continue;
                    isUpdated = jEuroTournament.addMatchToPlayer(tokens[1], tokens[2]);
                    MyFileLogWriter.writeToFileInSeparateLine("addMatchToPlayer returns:");
                    MyFileLogWriter.writeToFileInSeparateLine(isUpdated
                            ? "\tMatch " + tokens[1] + " added to player " + tokens[2]
                            : "\tFailed to add match " + tokens[1] + " to player " + tokens[2]);
                }
                case "addMatchToTeam" -> {
                    if (tokens.length != 3) continue;
                    isUpdated = jEuroTournament.addMatchToTeam(tokens[1], tokens[2]);
                    MyFileLogWriter.writeToFileInSeparateLine("addMatchToTeam returns:");
                    MyFileLogWriter.writeToFileInSeparateLine(isUpdated
                            ? "\tMatch " + tokens[1] + " added to team " + tokens[2]
                            : "\tFailed to add match " + tokens[1] + " to team " + tokens[2]);
                }

                case "addMatch" -> {
                    if (tokens.length != 5) continue;
                    String matchID = tokens[1];
                    Date date = df.parse(tokens[2]);
                    Stadium stadiumEnum = Stadium.getStadiumByCity(tokens[3]);
                    String stadium = stadiumEnum != null ? stadiumEnum.name() : "UnknownStadium";
                    long totalTickets = Long.parseLong(tokens[4]);
                    MatchResult result = jEuroTournament.getMatchResults().get(matchID);
                    isUpdated = jEuroTournament.addMatch(matchID, date, stadium, totalTickets, result);
                    MyFileLogWriter.writeToFileInSeparateLine("addMatch returns:");
                    MyFileLogWriter.writeToFileInSeparateLine(isUpdated
                            ? "\tSuccessfully added match " + matchID
                            : "\tFailed to add match " + matchID);
                }
                case "addCostumerToMatch" -> {
                    if (tokens.length != 3) continue;
                    isUpdated = jEuroTournament.addCostumerToMatch(tokens[1], tokens[2]);
                    MyFileLogWriter.writeToFileInSeparateLine("addCostumerToMatch returns:");
                    MyFileLogWriter.writeToFileInSeparateLine(isUpdated
                            ? "\tCustomer " + tokens[1] + " added to match " + tokens[2]
                            : "\tFailed to add customer " + tokens[1] + " to match " + tokens[2]);
                }
                case "addPlayerToTeam" -> {
                    if (tokens.length != 3) continue;
                    isUpdated = jEuroTournament.addPlayerToTeam(tokens[1], tokens[2]);
                    MyFileLogWriter.writeToFileInSeparateLine("addPlayerToTeam returns:");
                    MyFileLogWriter.writeToFileInSeparateLine(isUpdated
                            ? "\tPlayer " + tokens[2] + " added to team " + tokens[1]
                            : "\tFailed to add player " + tokens[2] + " to team " + tokens[1]);
                }
                case "addTrophy" -> {
                    if (tokens.length != 4) continue;
                    try {
                        E_Trophy trophy = E_Trophy.valueOf(tokens[1].toUpperCase());
                        String ownerId = tokens[2];
                        Date trophyDate = df.parse(tokens[3]);

                        Object owner = null;
                        if (jEuroTournament.getPlayers().containsKey(ownerId)) {
                            owner = jEuroTournament.getPlayers().get(ownerId);
                        } else if (jEuroTournament.getCoaches().containsKey(ownerId)) {
                            owner = jEuroTournament.getCoaches().get(ownerId);
                        } else if (jEuroTournament.getTeams().containsKey(ownerId)) {
                            owner = jEuroTournament.getTeams().get(ownerId);
                        }

                        isUpdated = (owner != null) && jEuroTournament.addTrophy(trophy, owner, trophyDate);
                        MyFileLogWriter.writeToFileInSeparateLine("addTrophy returns:");
                        MyFileLogWriter.writeToFileInSeparateLine(isUpdated
                                ? "\tSuccessfully added Trophy " + trophy
                                : "\tFailed adding Trophy " + trophy);
                    } catch (Exception e) {
                        MyFileLogWriter.writeToFileInSeparateLine("addTrophy failed: " + e.getMessage());
                    }
                }
                case "getEntityWithMostTrophies" -> {
                    Object entity = jEuroTournament.getEntityWithMostTrophies();
                    MyFileLogWriter.writeToFileInSeparateLine("getEntityWithMostTrophies returns: " + entity);
                }

                case "getSponsorsOfGermany" -> {
                    Collection<Sponsor> sponsors = jEuroTournament.getSponsorsOfGermany();
                    MyFileLogWriter.writeToFileInSeparateLine("getSponsorsOfGermany returns: " + sponsors);
                }

                case "getMostActivePlayer" -> {
                    Player player = jEuroTournament.getMostActivePlayer();
                    MyFileLogWriter.writeToFileInSeparateLine("getMostActivePlayer returns: " + player);
                }

                case "getTheBestHomeMatch" -> {
                    Match match = jEuroTournament.getTheBestHomeMatch();
                    MyFileLogWriter.writeToFileInSeparateLine("getTheBestHomeMatch returns: " + match);
                }

                case "getTheBestCustomer" -> {
                    Collection<Customer> customers = jEuroTournament.getTheBestCustomer();
                    MyFileLogWriter.writeToFileInSeparateLine("getTheBestCustomer returns: " + customers);
                }

                case "getTeamsBestHomeScore" -> {
                    Collection<Team> teams = jEuroTournament.getTeamsBestHomeScore();
                    MyFileLogWriter.writeToFileInSeparateLine("getTeamsBestHomeScore returns: " + teams);
                }
            }
        }

        MyFileLogWriter.saveLogFile();
        input.close();
    }
}