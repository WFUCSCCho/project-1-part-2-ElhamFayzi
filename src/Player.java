public class Player implements Comparable<Player> {
    private String name;
    private int jerseyNum;
    private String club;
    private String position;
    private String nationality;
    private int age;
    private int appearances;
    private int wins;
    private int goals;
    private int assists;

    // Default Constructor
    public Player() {
        name = null;
        jerseyNum = 0;               // Handle the InvalidNumberFormat Exception
        club = null;
        position = null;
        nationality = null;
        age = 0;
        appearances = 0;
        wins = 0;
        goals = 0;
        assists = 0;
    }

    // Parameterized Constructor
    public Player(String[] data) {
        name = data[0];
        jerseyNum = Integer.parseInt(data[1]);               // Handle the InvalidNumberFormat Exception
        club = data[2];
        position = data[3];
        nationality = data[4];
        age = Integer.parseInt(data[5]);
        appearances = Integer.parseInt(data[6]);            // Handle the InvalidNumberFormat Exception for all of parsed values
        wins = Integer.parseInt(data[7]);
        goals = Integer.parseInt(data[9]);
        assists = Integer.parseInt(data[10]);
    }

    //TEST
    public Player(String n) {
        name = n;
    }

    // Copy Constructor
    public Player(Player p) {
        this.name = p.name;
        this.jerseyNum = p.jerseyNum;
        this.club = p.club;
        this.position = p.position;
        this.nationality = p.nationality;
        this.age = p.age;
        this.appearances = p.appearances;
        this.wins = p.wins;
        this.goals = p.goals;
        this.assists = p.assists;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(name).append(jerseyNum).append(club).append(position).append(nationality).append(age).append(appearances).append(wins).append(goals).append(assists);
        return sb.toString();
    }

    @Override
    public int compareTo(Player p) {                        // Compare players based on their number of appearances in games
        if (this.appearances < p.appearances) { return -1; }
        else if (this.appearances > p.appearances) { return 1; }
        else { return 0; }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) { return true; }
        if (obj == null) { return false; }

        Player p = (Player) obj;
        return name.equals(p.name) && jerseyNum == p.jerseyNum && club.equals(p.club);          // Since each object is a player, checking for names, jersey numbers, and clubs would be sufficient to determine whether two players are the same.
    }



    // No need for getters or setters since each object of type Player is only created using single lines of the parsed data set passed to the constructor
}
