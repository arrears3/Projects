import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Player {
    private String name;
    private int number;

    public Player(String name, int number) {
        this.name = name;
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public int getNumber() {
        return number;
    }

    @Override
    public String toString() {
        return "Player{" + "name='" + name + '\'' + ", number=" + number + '}';
    }
}

class Team {
    private String name;
    private List<Player> players;
    private int points;
    private int goalsFor;
    private int goalsAgainst;

    public Team(String name) {
        this.name = name;
        this.players = new ArrayList<>();
        this.points = 0;
        this.goalsFor = 0;
        this.goalsAgainst = 0;
    }

    public String getName() {
        return name;
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void updateStats(int goalsFor, int goalsAgainst) {
        this.goalsFor += goalsFor;
        this.goalsAgainst += goalsAgainst;
        if (goalsFor > goalsAgainst) {
            points += 3;
        } else if (goalsFor == goalsAgainst) {
            points += 1;
        }
    }

    public int getPoints() {
        return points;
    }

    public int getGoalsFor() {
        return goalsFor;
    }

    public int getGoalsAgainst() {
        return goalsAgainst;
    }

    @Override
    public String toString() {
        return "Team{" +
                "name='" + name + '\'' +
                ", points=" + points +
                ", goalsFor=" + goalsFor +
                ", goalsAgainst=" + goalsAgainst +
                '}';
    }
}

class Match {
    private Team homeTeam;
    private Team awayTeam;
    private int homeGoals;
    private int awayGoals;

    public Match(Team homeTeam, Team awayTeam, int homeGoals, int awayGoals) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.homeGoals = homeGoals;
        this.awayGoals = awayGoals;
        recordResult();
    }

    private void recordResult() {
        homeTeam.updateStats(homeGoals, awayGoals);
        awayTeam.updateStats(awayGoals, homeGoals);
    }

    @Override
    public String toString() {
        return "Match{" +
                "homeTeam=" + homeTeam.getName() +
                ", awayTeam=" + awayTeam.getName() +
                ", homeGoals=" + homeGoals +
                ", awayGoals=" + awayGoals +
                '}';
    }
}

public class FootballLeague {
    private List<Team> teams;
    private List<Match> matches;

    public FootballLeague() {
        this.teams = new ArrayList<>();
        this.matches = new ArrayList<>();
    }

    public void addTeam(Team team) {
        teams.add(team);
    }

    public void scheduleMatch(Team homeTeam, Team awayTeam, int homeGoals, int awayGoals) {
        matches.add(new Match(homeTeam, awayTeam, homeGoals, awayGoals));
    }

    public void displayStandings() {
        teams.sort((t1, t2) -> {
            if (t2.getPoints() != t1.getPoints()) {
                return t2.getPoints() - t1.getPoints();
            }
            return (t2.getGoalsFor() - t2.getGoalsAgainst()) - (t1.getGoalsFor() - t1.getGoalsAgainst());
        });

        System.out.println("League Standings:");
        for (Team team : teams) {
            System.out.println(team);
        }
    }

    public static void main(String[] args) {
        FootballLeague league = new FootballLeague();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Football League Manager!");

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Add Team");
            System.out.println("2. Schedule Match");
            System.out.println("3. Display Standings");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter team name: ");
                    String teamName = scanner.nextLine();
                    Team team = new Team(teamName);
                    league.addTeam(team);

                    System.out.print("Enter number of players: ");
                    int numPlayers = scanner.nextInt();
                    scanner.nextLine(); // Consume newline

                    for (int i = 0; i < numPlayers; i++) {
                        System.out.print("Enter player name: ");
                        String playerName = scanner.nextLine();
                        System.out.print("Enter player number: ");
                        int playerNumber = scanner.nextInt();
                        scanner.nextLine(); // Consume newline

                        team.addPlayer(new Player(playerName, playerNumber));
                    }
                    break;

                case 2:
                    System.out.println("Teams:");
                    for (int i = 0; i < league.teams.size(); i++) {
                        System.out.println((i + 1) + ". " + league.teams.get(i).getName());
                    }

                    System.out.print("Enter home team number: ");
                    int homeTeamNumber = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    Team homeTeam = league.teams.get(homeTeamNumber - 1);

                    System.out.print("Enter away team number: ");
                    int awayTeamNumber = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    Team awayTeam = league.teams.get(awayTeamNumber - 1);

                    System.out.print("Enter home team goals: ");
                    int homeGoals = scanner.nextInt();
                    scanner.nextLine(); // Consume newline

                    System.out.print("Enter away team goals: ");
                    int awayGoals = scanner.nextInt();
                    scanner.nextLine(); // Consume newline

                    league.scheduleMatch(homeTeam, awayTeam, homeGoals, awayGoals);
                    break;

                case 3:
                    league.displayStandings();
                    break;

                case 4:
                    System.out.println("Exiting...");
                    return;

                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}

