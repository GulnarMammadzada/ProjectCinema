import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class Film {
    String title;
    int year;
    Genre genre;
    double ticketPrice;
    int cinemaNumber;
    String showTime;
    ArrayList<Actor> actors;
    ArrayList<Review> reviews;
    private Set<String> reviewedUsernames;
    private List<Actor> actors1;


    public Film(String title, int year, Genre genre) {
        this.title = title;
        this.year = year;
        this.genre = genre;
        this.actors = new ArrayList<>();
        this.reviews = new ArrayList<>();

        reviewedUsernames = new HashSet<>();
    }
    public String getTitle() {
        return title;
    }


    public void addActor(Actor actor) {
        this.actors.add(actor);
    }
    public List<Actor> getActors1(){
        return actors;
    }

    public void addReview(Review review) {
        this.reviews.add(review);
    }
    public boolean hasUserReviewed(String username) {
        return reviewedUsernames.contains(username);
    }


    public void addUserToReviewedSet(String username) {
        reviewedUsernames.add(username);
    }


    public void displayInfo() {
        Main.printColored(Main.ANSI_YELLOW,"Movie: " + title);
        Main.printColored(Main.ANSI_YELLOW,"Year: " + year);
        Main.printColored(Main.ANSI_YELLOW,"Genre: " + genre);
        Main.printColored(Main.ANSI_YELLOW,"Ticket Price: $" + ticketPrice);
        Main.printColored(Main.ANSI_YELLOW,"Theater Room Number: No:" + cinemaNumber);
        Main.printColored(Main.ANSI_YELLOW,"Show Time: " + showTime);
    }
    public void removeActor(Actor actor) {
        actors.remove(actor);
    }
}

