import java.util.*;

public class Main {
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_PURPLE = "\u001B[35m";

    public static final String ANSI_RESET = "\u001B[0m";

    public static void printColored(String color, String message) {
        System.out.println(color + message + ANSI_RESET);
    }

    private static final ArrayList<Film> films = new ArrayList<>();
    private static final Scanner scanner = new Scanner(System.in);


    public static void main(String[] args) {
        try {

            System.out.println("        ");
            Helper.printCinemaArt();
            System.out.println("        ");
            printColored(ANSI_RED, "Welcome to the Cinema!");

            while (true) {
                System.out.println(" ");
                printColored(ANSI_PURPLE, "Please choose an operation");
                printColored(ANSI_GREEN, "1: Add Film");
                printColored(ANSI_YELLOW, "2: Update Film");
                printColored(ANSI_BLUE, "3: Delete Film");
                printColored(ANSI_GREEN, "4: Add Actor to Film");
                printColored(ANSI_YELLOW, "5:Update Actor");
                printColored(ANSI_BLUE, "6: Delete Actor");
                printColored(ANSI_GREEN, "7: Display Film Information");
                printColored(ANSI_YELLOW, "8: List All Films");
                printColored(ANSI_BLUE, "9: Display All Actors");
                printColored(ANSI_GREEN, "10: Add Review");
                printColored(ANSI_YELLOW, "11: Show Average Rating");
                printColored(ANSI_BLUE, "12: Display Top Rated Films");
                printColored(ANSI_RED, "13: Exit");




                String operation = scanner.nextLine();
                int operationNumber = 0;
                if (operation.isEmpty() || operation.isBlank()) {
                    printColored(ANSI_RED, "Operation cannot be empty");
                } else {
                    try {
                        operationNumber = Integer.parseInt(operation);
                    } catch (Exception e) {

                    }
                }

                if (operationNumber == 13) {
                    while (true) {
                        printColored(ANSI_PURPLE, "Are you sure you want to exit the program? (yes/no): ");
                        String confirmExit = scanner.nextLine().toLowerCase();

                        if (confirmExit.equals("yes")) {
                            printColored(ANSI_YELLOW, "Thank you for using the Cinema Management System! Exiting the program.");
                            System.exit(0);
                        } else if (confirmExit.equals("no")) {
                            printColored(ANSI_RED, "Returning to the main menu.");
                            break;
                        } else {
                            printColored(ANSI_RED, "Invalid input. Please enter 'yes' or 'no'.");
                        }
                    }


                    continue;
                }
                handleFilmOperation(operationNumber);
            }
        } finally {
            scanner.close();
        }
    }


    private static void handleFilmOperation(int operationNumber) {
        if (operationNumber == 1) {
            addFilm();
        } else if (operationNumber == 2) {
            updateFilm();
        } else if (operationNumber == 3) {
            deleteFilm();
        } else if (operationNumber == 4) {
            addActorToFilm();
        } else if (operationNumber == 5) {
            updateActorsInFilm();
        } else if (operationNumber == 6) {
            deleteActor();
        } else if (operationNumber == 7) {
            displayFilmInfo();
        } else if (operationNumber == 8) {
            listAllFilms();
        } else if (operationNumber == 9) {
            displayAllActors();
        }else if (operationNumber == 10) {
            addReview();
        }else if (operationNumber == 11) {
            showAverageRating();
        } else if (operationNumber == 12) {
            displayTopRatedFilmsWithIndex();
        } else {
            printColored(ANSI_RED, "Please enter a valid operation number");
        }
    }

    private static void addFilm() {
        while (true) {
            System.out.print("\033[36mEnter the title of the movie or \033[31m'back'\033[36m to return to the main menu: \033[0m");            String title = scanner.nextLine().trim();

            if (title.equalsIgnoreCase("back")) {
                return;
            }

            if (title.isEmpty() || title.isBlank()) {
                printColored(ANSI_RED, "Input cannot be empty. Please enter a valid title or 'back'.");
                continue;
            }


            if (!title.matches("^[a-zA-Z0-9\\s]+$")) {
                printColored(ANSI_RED, "Invalid input. Please enter a valid title or 'back'.");
                continue;
            }

            if (filmWithTitleExists(title)) {
                printColored(ANSI_RED, "A film with the title '" + title + "' already exists. Please enter a different title.");
                continue;
            }

            printColored(ANSI_CYAN,"Enter the year of release (between 1900 and 2024): ");
            int year = Helper.getNonNegativeIntegerInputForYear();

            Genre genre = null;
            do {
                try {
                    printColored(ANSI_CYAN,"Enter the genre (ACTION, COMEDY, DRAMA, HORROR, ROMANCE,SCIENCEFICTION,ANIMATION,WESTERN): ");
                    String genreInput = Helper.getNonEmptyStringInputForGenre().toUpperCase();
                    genre = Genre.valueOf(genreInput);
                } catch (IllegalArgumentException e) {
                    printColored(ANSI_RED, "Invalid genre. Please enter a valid genre.");
                }
            } while (genre == null);

            printColored(ANSI_CYAN, "Select a ticket package:");
            double ticketPrice = Helper.selectTicketPackage();

            int cinemaNumber = Helper.getPositiveIntegerInputForCinemaRoomNumber();

            String showTime = Helper.getShowTimeInput();

            Film film = new Film(title, year, genre);
            film.ticketPrice = ticketPrice;
            film.cinemaNumber = cinemaNumber;
            film.showTime = showTime;


            if (Helper.confirmOperation("Do you want to add this film?")) {
                films.add(film);
                printColored(ANSI_YELLOW, "Film added: " + title);
            } else {
                printColored(ANSI_PURPLE, "Film not added. Please enter the film details again.");
                return;
            }

            while (true) {
                System.out.print("Do you want to add another film? (yes/no): ");
                String addAnother = scanner.nextLine().toLowerCase();

                if (addAnother.equals("yes")) {

                    break;
                } else if (addAnother.equals("no")) {
                    return;
                } else {
                    printColored(ANSI_RED, "Invalid input. Please enter 'yes' or 'no'.");
                }
            }
        }
    }
    private static boolean filmWithTitleExists(String title) {
        for (Film existingFilm : films) {
            if (existingFilm.getTitle().equalsIgnoreCase(title)) {
                return true;
            }
        }
        return false;
    }
    private static void deleteFilm() {
        if(films.isEmpty()){
            printColored(ANSI_RED, "No films available to delete.Please add films before attempting to delete.");
            return;
        }
        listAllFilms();
        String titleToDelete;
        while(true) {
            System.out.print("\033[36mEnter the title of the movie or \033[31m'back'\033[36m to return to the main menu: \033[0m");
            titleToDelete = scanner.nextLine().trim();

            if (titleToDelete.equalsIgnoreCase("back")) {
                return;
            }

            if (titleToDelete.isEmpty() || titleToDelete.isBlank() || !titleToDelete.matches("^[a-zA-Z0-9\\s]*$")) {
                printColored(ANSI_RED, "Input cannot be empty. Please enter a valid title or 'back'.");
            } else {
                break;
            }
        }


        Film filmToDelete = findFilm(titleToDelete);
        if (filmToDelete == null) {
            printColored(ANSI_RED, "Film with title '" + titleToDelete + "' not found.");
            return;
        }


        if (Helper.confirmOperation("Are you sure you want to delete this film?")) {
            films.remove(filmToDelete);
            printColored(ANSI_YELLOW, "Film deleted: " + titleToDelete);
        } else {
            printColored(ANSI_PURPLE, "Film not deleted. Returning to the main menu.");
        }
    }
    private static void addActorToFilm() {
        listAllFilms();

        printColored(ANSI_BLUE, "Enter the title of the film to add actors to: ");
        String filmTitle = Helper.getNonEmptyStringInputForMovie();


        Film selectedFilm = findFilm(filmTitle);


        if (selectedFilm != null) {

            int actorCount = 0;

            Scanner scanner = new Scanner(System.in);

            while (true) {
                printColored(ANSI_YELLOW,"Enter details for actor #" + (actorCount + 1));


                printColored(ANSI_CYAN,"Actor name: ");
                String actorName = Helper.getNonEmptyStringInputOnlyLettersForActorName();


                printColored(ANSI_CYAN,"Actor surname: ");
                String actorSurname = Helper.getNonEmptyStringInputOnlyLettersForActorSurName();


                printColored(ANSI_CYAN,"Actor age: ");
                int actorAge = Helper.getPositiveIntegerInputOnlyNumbersForActorAge();

                Actor actor = new Actor(actorName, actorSurname, actorAge);


                printColored(ANSI_PURPLE, "Confirm actor details:");
                printColored(ANSI_BLUE, actor.toString());

                String confirmAddition;
                while (true) {
                    printColored(ANSI_BLUE, "Do you want to add this actor? (yes/no): ");
                    confirmAddition = scanner.nextLine().toLowerCase();

                    if (confirmAddition.equals("yes") || confirmAddition.equals("no")) {
                        break;
                    } else {
                        printColored(ANSI_RED, "Invalid input. Please enter either 'yes' or 'no'.");
                    }
                }

                if (confirmAddition.equals("yes")) {
                    selectedFilm.addActor(actor);
                    actorCount++;
                }



                String addAnotherActor;
                while (true) {
                    printColored(ANSI_PURPLE, "Do you want to add another actor? (yes/no): ");
                    addAnotherActor = scanner.nextLine().toLowerCase();

                    if (addAnotherActor.equals("yes") || addAnotherActor.equals("no")) {
                        break;
                    } else {
                        printColored(ANSI_RED, "Invalid input. Please enter either 'yes' or 'no'.");
                    }
                }

                if (!addAnotherActor.equals("yes")) {
                    break;
                }
            }

            printColored(ANSI_YELLOW, actorCount + " actor(s) added to " + selectedFilm.getTitle());
        } else {
            printColored(ANSI_RED, "Film not found with the specified title.");
        }
    }
    private static void deleteActor() {
        if (films.isEmpty()) {
            printColored(ANSI_RED, "No films available to delete actors from. Please add films before attempting to delete actors.");
            return;
        }

        listAllFilms();


        printColored(ANSI_BLUE, "Enter the title of the film to delete actors from: ");
        String filmTitle = Helper.getNonEmptyStringInputForMovie();


        Film selectedFilm = findFilm(filmTitle);


        if (selectedFilm != null) {
            if(selectedFilm.getActors1().isEmpty()) {
                printColored(ANSI_RED, "No actors available in the selected film.Returning to the main menu ");
                return;
            }

            listActorsInFilm(selectedFilm);


            System.out.print("\033[36mEnter the title of the movie or \033[31m'back'\033[36m to return to the main menu: \033[0m");

            String actorNameToDelete;

            while (true) {
                actorNameToDelete = scanner.nextLine().trim();

                if (actorNameToDelete.equalsIgnoreCase("back")) {
                    return;
                }

                if (actorNameToDelete.isEmpty() || actorNameToDelete.isBlank() || actorNameToDelete.contains(" ")) {
                    printColored(ANSI_RED, "Invalid input. Actor name cannot be empty or contain spaces. Please enter a valid actor name or 'back'.");
                    System.out.print("\033[36mEnter the name of the actor or \033[31m'back'\033[36m to return to the main menu: \033[0m");

                } else {
                    break;
                }
            }


            Actor actorToDelete = findActor(selectedFilm, actorNameToDelete);
            if (actorToDelete != null) {

                if (Helper.confirmOperation("Are you sure you want to delete this actor?")) {
                    selectedFilm.removeActor(actorToDelete);
                    printColored(ANSI_YELLOW, "Actor deleted: " + actorToDelete.getName() + " " + actorToDelete.getSurname());
                } else {
                    printColored(ANSI_PURPLE, "Actor not deleted. Returning to the main menu.");
                }
            } else {
                printColored(ANSI_RED, "Actor with name '" + actorNameToDelete + "' not found.");
            }
        } else {
            printColored(ANSI_RED, "Film not found with the specified title.");
        }
    }
    private static void listActorsInFilm(Film film) {
        List<Actor> actors = film.getActors1();

        if (actors.isEmpty()) {
            printColored(ANSI_RED, "No actors available for the film.");
        } else {
            printColored(ANSI_PURPLE, "Actors in " + film.getTitle() + ":");
            for (Actor actor : actors) {
                printColored(ANSI_BLUE, actor.toString());
            }
        }
    }
    private static Actor findActor(Film film, String actorName) {
        for (Actor existingActor : film.getActors1()) {
            if (existingActor.getName().equalsIgnoreCase(actorName)) {
                return existingActor;
            }
        }
        return null;
    }
    private static void updateFilm() {
        listAllFilms();
        printColored(ANSI_YELLOW, "Enter the title of the movie to update: ");
        String movieToUpdate = Helper.getNonEmptyStringInputForMovieTitle();
        Film film = findFilm(movieToUpdate);

        if (film != null) {
            boolean continueUpdating = true;

            while (continueUpdating) {
                printColored(ANSI_PURPLE, "Choose what to update for " + movieToUpdate + ":");
                printColored(ANSI_BLUE, "1. Title");
                printColored(ANSI_GREEN, "2. Year");
                printColored(ANSI_BLUE, "3. Genre");
                printColored(ANSI_GREEN, "4. Ticket Price");
                printColored(ANSI_BLUE, "5. Theater Room Number");
                printColored(ANSI_GREEN, "6. Show Time");
                printColored(ANSI_RED, "0. Exit");
                printColored(ANSI_YELLOW, "Enter your choice: ");

                int choice = Helper.getPositiveIntegerInput();

                if (choice == 1) {

                    printColored(ANSI_CYAN,"Enter the new title for " + movieToUpdate + ": ");
                    String newTitle = Helper.getNonEmptyStringInputForNewMovieTitle();
                    if (!newTitle.isEmpty() && !newTitle.equals(film.title)) {
                        film.title = newTitle;
                        printColored(ANSI_RED, "Title for " + movieToUpdate + " updated successfully.");
                    } else {
                        printColored(ANSI_RED, "The new title is the same as the current one. No update performed.");
                    }
                } else if (choice == 2) {

                    printColored(ANSI_CYAN,"Enter the new year for " + movieToUpdate + " between 1900 and 2024" + ": ");
                    int newYear = Helper.getNonNegativeIntegerInputForNewYear();
                    if (newYear != film.year) {
                        film.year = newYear;
                        printColored(ANSI_RED, "Year for " + movieToUpdate + " updated successfully.");
                    } else {
                        printColored(ANSI_RED, "The new year is the same as the current one. No update performed.");
                    }
                } else if (choice == 3) {

                    while (true) {
                        printColored(ANSI_CYAN,"Enter the new genre for " + movieToUpdate + ": " + "(ACTION, COMEDY, DRAMA, HORROR, ROMANCE,SCIENCEFICTION,ANIMATION,WESTERN)" + ":");
                        String newGenreInput = Helper.getNonEmptyStringInputForGenre().toUpperCase();

                        try {
                            Genre newGenre = Genre.valueOf(newGenreInput);
                            if (newGenre != film.genre) {
                                film.genre = newGenre;
                                printColored(ANSI_RED, "Genre for " + movieToUpdate + " updated successfully.");
                            } else {
                                printColored(ANSI_RED, "The new genre is the same as the current one. No update performed.");
                            }
                            break;
                        } catch (IllegalArgumentException e) {
                            printColored(ANSI_RED, "Invalid genre. Please enter a valid genre.");
                        }
                    }
                } else if (choice == 4) {

                    printColored(ANSI_CYAN,"Enter the new ticket price for " + movieToUpdate + ": $");
                    System.out.println();
                    double newTicketPrice = Helper.selectTicketPackage();
                    if (newTicketPrice != film.ticketPrice) {
                        film.ticketPrice = newTicketPrice;
                        printColored(ANSI_RED, "Ticket price for " + movieToUpdate + " updated successfully.");
                    } else {
                        printColored(ANSI_RED, "The new ticket price is the same as the current one. No update performed.");
                    }
                } else if (choice == 5) {

                    int newCinemaNumber = Helper.getPositiveIntegerInputForNewCinemaRoomNumber();
                    if (newCinemaNumber != film.cinemaNumber) {
                        film.cinemaNumber = newCinemaNumber;
                        printColored(ANSI_RED, "Cinema room number for " + movieToUpdate + " updated successfully.");
                    } else {
                        printColored(ANSI_RED, "The new cinema room number is the same as the current one. No update performed.");
                    }
                } else if (choice == 6) {

                    String newShowTime = Helper.getNewShowTimeInput();
                    if (!newShowTime.equals(film.showTime)) {
                        film.showTime = newShowTime;
                        printColored(ANSI_RED, "Show time for " + movieToUpdate + " updated successfully.");
                    } else {
                        printColored(ANSI_RED, "The new show time is the same as the current one. No update performed.");
                    }
                } else if (choice == 0) {
                    printColored(ANSI_YELLOW, "Exiting Update operation.");
                    continueUpdating = false;
                } else {
                    printColored(ANSI_RED, "Invalid choice. No updates performed.");
                }
            }
        } else {
            printColored(ANSI_RED, "Movie with the name '" + movieToUpdate + "' not found.");
        }
    }

    private static void displayFilmInfo() {
        if (films.isEmpty()) {
            printColored(ANSI_RED, "No films available.");
        } else {
            System.out.println(" ");
            printColored(ANSI_BLUE, "List of All Films:");
            for (Film film : films) {
                printColored(ANSI_YELLOW, "- " + film.title);
            }

            printColored(ANSI_PURPLE,"Enter the title of the movie to display information: ");
            String movieToDisplay = Helper.getNonEmptyStringInputForDisplayInformation();
            Film filmToDisplay = findFilm(movieToDisplay);

            if (filmToDisplay != null) {
                filmToDisplay.displayInfo();

                if (filmToDisplay.actors.isEmpty()) {
                    printColored(ANSI_RED,"No actors have been added to the film yet.");
                } else {
                    displayActorInfo(filmToDisplay.actors);
                }
            } else {
                printColored(ANSI_RED, "Movie not found.");
            }
        }
    }

    private static void listAllFilms() {
        if (films.isEmpty()) {
            printColored(ANSI_RED, "No films available.");
        } else {
            printColored(ANSI_BLUE, "List of All Films:");
            for (Film film : films) {
                printColored(ANSI_YELLOW, "- " + film.title);
            }
        }
    }

    private static void addReview() {
        listAllFilms();
        System.out.print("Enter the title of the movie for the review: ");
        String movieTitle = Helper.getNonEmptyStringInputForReview();
        Film film = findFilm(movieTitle);

        if (film != null) {
            System.out.print("Enter your username: ");
            String username = Helper.getNonEmptyStringInputForUsername();

            if (film.hasUserReviewed(username)) {
                printColored(ANSI_RED, "You have already submitted a review for this movie.");
            } else {
                int rating = Helper.getValidRatingInput();


                Review review = new Review(username, rating);
                film.addReview(review);
                film.addUserToReviewedSet(username);

                printColored(ANSI_PURPLE, "Review added successfully for " + film.title);
            }
        } else {
            printColored(ANSI_RED, "Movie with the title '" + movieTitle + "' not found.");
        }
    }


    static Film findFilm(String title) {
        for (Film film : films) {
            if (film.title.equals(title)) {
                return film;
            }
        }
        return null;
    }

    private static void displayActorInfo(ArrayList<Actor> actors) {
        if (actors.isEmpty()) {
            printColored(ANSI_RED, "No actors have been added to the film yet.");
        } else {
            printColored(ANSI_BLUE, "Actors in the Movie:");
            for (Actor actor : actors) {
                printColored(ANSI_YELLOW, "- " + actor.toString());
            }
        }
    }
    private static void displayAllActors() {
        if (films.isEmpty()) {
            printColored(ANSI_RED, "No films available.");
        } else {
            printColored(ANSI_BLUE, "List of All Actors:");
            for (Film film : films) {
                printColored(ANSI_YELLOW, "Movie: " + film.title);
                displayActorInfo(film.actors);
                System.out.println();
            }
        }
    }

    private static void showAverageRating() {
        if (films.isEmpty()) {
            printColored(ANSI_RED, "No films available.");
        } else {
            printColored(ANSI_BLUE, "Average Rating for Each Film:");
            for (Film film : films) {
                printColored(ANSI_YELLOW, "Movie: " + film.title);
                double averageRating = calculateAverageRating(film.reviews);
                printColored(ANSI_YELLOW, "Average Rating: " + averageRating);
                System.out.println();
            }
        }
    }
    private static void displayTopRatedFilmsWithIndex() {
        if (films.isEmpty()) {
            printColored(ANSI_RED, "No films available.");
        } else {
            films.sort(Comparator.comparingDouble(film -> calculateAverageRating(film.reviews)));
            Collections.reverse(films);

            printColored(ANSI_BLUE, "Top 10 Rated Films:");

            int limit = Math.min(films.size(), 10);
            for (int i = 0; i < limit; i++) {
                Film film = films.get(i);
                double averageRating = calculateAverageRating(film.reviews);
                printColored(ANSI_YELLOW, (i + 1) + ": " + film.title + " - Average Rating: " + averageRating);
            }
        }
    }

    private static double calculateAverageRating(ArrayList<Review> reviews) {
        if (reviews.isEmpty()) {
            return 0.0;
        }

        double sum = 0.0;
        for (Review review : reviews) {
            sum += review.rating;
        }

        return sum / reviews.size();
    }

    private static void updateActorsInFilm() {
        listAllFilms();
        printColored(ANSI_PURPLE,"Enter the title of the film to update actors: ");
        String filmTitle = Helper.getNonEmptyStringInputForMovie();

        Film selectedFilm = findFilm(filmTitle);

        if (selectedFilm != null) {

            if (selectedFilm.actors.isEmpty()) {
                printColored(ANSI_RED,"No actors added to the film " + selectedFilm.getTitle() + ".");
            } else {
                printColored(ANSI_BLUE,"Current Actors in " + selectedFilm.getTitle() + ":");
                int actorIndex = 1;
                for (Actor actor : selectedFilm.actors) {
                    printColored(ANSI_YELLOW,actorIndex + ": " + actor.toString());
                    actorIndex++;
                }

                while (true) {

                    int actorIndexToUpdate = Helper.getValidActorIndex(selectedFilm);

                    if (actorIndexToUpdate != -1 && actorIndexToUpdate != 0) {
                        Actor actorToUpdate = selectedFilm.actors.get(actorIndexToUpdate - 1);

                        String originalName = actorToUpdate.name;
                        String originalSurname = actorToUpdate.surname;
                        int originalAge = actorToUpdate.age;


                        printColored(ANSI_BLUE,"Enter updated details for actor:");

                        do {
                            int attributeChoice = getValidAttributeChoice();

                            if (attributeChoice != 0) {

                                if (attributeChoice == 1) {

                                    printColored(ANSI_CYAN,"Enter updated name: ");
                                    String updatedActorName = Helper.getNonEmptyStringInputOnlyLettersForActorName();
                                    if (!updatedActorName.isEmpty() && !updatedActorName.equals(actorToUpdate.name)) {
                                        actorToUpdate.name = updatedActorName;
                                    } else {
                                        printColored(ANSI_RED, "The new name is the same as the current one. No update performed.");
                                    }
                                } else if (attributeChoice == 2) {

                                    printColored(ANSI_CYAN,"Enter updated surname: ");
                                    String updatedActorSurname = Helper.getNonEmptyStringInputOnlyLettersForActorSurName();
                                    if (!updatedActorSurname.isEmpty() && !updatedActorSurname.equals(actorToUpdate.surname)) {
                                        actorToUpdate.surname = updatedActorSurname;
                                    } else {
                                        printColored(ANSI_RED, "The new surname is the same as the current one. No update performed.");
                                    }
                                } else if (attributeChoice == 3) {

                                    printColored(ANSI_CYAN,"Enter updated age: ");
                                    int updatedActorAge = Helper.getPositiveIntegerInputOnlyNumbersForActorAge();
                                    if (updatedActorAge != actorToUpdate.age) {
                                        actorToUpdate.age = updatedActorAge;
                                    } else {
                                        printColored(ANSI_RED, "The new age is the same as the current one. No update performed.");
                                    }
                                } else {
                                    printColored(ANSI_RED,"Invalid attribute choice. Skipping update.");
                                }


                                String updateAnotherAttribute;
                                while (true) {
                                    printColored(ANSI_YELLOW,"Do you want to update another attribute? (yes/no): ");
                                    updateAnotherAttribute = scanner.nextLine().toLowerCase().trim();

                                    if (updateAnotherAttribute.equals("yes") || updateAnotherAttribute.equals("no")) {
                                        break;
                                    } else {
                                        printColored(ANSI_RED,"Invalid input. Please enter 'yes' or 'no'.");
                                    }
                                }

                                if (updateAnotherAttribute.equals("no")) {
                                    break;
                                }
                            } else {
                                printColored(ANSI_RED,"Update operation skipped. No attribute was updated.");
                                break;
                            }
                        } while (true);


                        boolean confirmation = confirmUpdate();

                        if (confirmation) {
                            printColored(ANSI_CYAN,"Actor updated: " + actorToUpdate.toString());
                        } else {
                            actorToUpdate.name = originalName;
                            actorToUpdate.surname = originalSurname;
                            actorToUpdate.age = originalAge;
                            printColored(ANSI_RED,"Update cancelled. Reverting changes to the actor.");
                        }
                    } else if (actorIndexToUpdate == 0) {
                        printColored(ANSI_RED,"Update operation cancelled. No actor was updated.");
                        break;
                    }


                    while (true) {
                        printColored(ANSI_YELLOW,"Do you want to update another actor? (yes/no): ");
                        String updateAnotherActor = scanner.nextLine().toLowerCase();

                        if (updateAnotherActor.equals("yes")) {
                            break;
                        } else if (updateAnotherActor.equals("no")) {
                            return;
                        } else {
                            printColored(ANSI_RED,"Invalid input. Please enter 'yes' or 'no'.");
                        }
                    }
                }
            }
        } else {
            printColored(ANSI_RED,"Film not found with the specified title.");
        }
    }

    private static boolean confirmUpdate() {
        while (true) {
            printColored(ANSI_PURPLE, "Do you want to confirm the update? (yes/no): ");
            String confirmation = scanner.nextLine().toLowerCase().trim();

            if (confirmation.equals("yes")) {
                return true;
            } else if (confirmation.equals("no")) {
                return false;
            } else {
                printColored(ANSI_RED, "Invalid input. Please enter 'yes' or 'no'.");
            }
        }
    }

    private static int getValidAttributeChoice() {
        int attributeChoice = -1;

        while (true) {
            printColored(ANSI_PURPLE, "Select the attribute to update:");
            printColored(ANSI_GREEN, "1: Name");
            printColored(ANSI_BLUE, "2: Surname");
            printColored(ANSI_YELLOW, "3: Age");
            printColored(ANSI_RED, "0: Exit update");

            try {
                attributeChoice = Integer.parseInt(scanner.nextLine());

                if (attributeChoice >= 0 && attributeChoice <= 3) {
                    break;
                } else {
                    printColored(ANSI_RED, "Invalid attribute choice. Please enter a valid number.");
                }
            } catch (NumberFormatException e) {
                printColored(ANSI_RED, "Invalid input. Please enter a valid number.");
            }
        }

        return attributeChoice;
    }
}
