import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.regex.Pattern;

class Helper {
    private static final Scanner scanner = new Scanner(System.in);
    private static final int MAX_CINEMA_ROOMS = 50;

    public static int getPositiveIntegerInputForCinemaRoomNumber() {
        int input;

        do {
            try {
                while (true) {
                    Main.printColored(Main.ANSI_CYAN,"Enter the Cinema room number between 1 and " + MAX_CINEMA_ROOMS + ":");
                    String inputString = scanner.nextLine().trim();

                    if (inputString.isEmpty()) {
                        Main.printColored(Main.ANSI_RED, "Input cannot be empty. Please try again.");
                        continue;
                    }


                    if (containsSymbolsOrSpaces(inputString)) {
                        Main.printColored(Main.ANSI_RED, "Invalid input. Please enter a valid integer.");
                        continue;
                    }

                    input = Integer.parseInt(inputString);

                    if (input <= 0 || input > MAX_CINEMA_ROOMS) {
                        Main.printColored(Main.ANSI_RED, "Please enter a positive integer between 1 and " + MAX_CINEMA_ROOMS + ".");
                    } else {
                        return input;
                    }
                }
            } catch (NumberFormatException e) {
                Main.printColored(Main.ANSI_RED, "Invalid input. Please enter a valid positive integer.");
            }
        } while (true);
    }

    public static int getPositiveIntegerInputForNewCinemaRoomNumber() {
        int input;

        do {
            try {
                while (true) {
                    Main.printColored(Main.ANSI_CYAN,"Enter the new Cinema room number between 1 and " + MAX_CINEMA_ROOMS + ":");
                    String inputString = scanner.nextLine().trim();

                    if (inputString.isEmpty()) {
                        Main.printColored(Main.ANSI_RED, "Input cannot be empty. Please try again.");
                        continue;
                    }


                    if (containsSymbolsOrSpaces(inputString)) {
                        Main.printColored(Main.ANSI_RED, "Invalid input. Please enter a valid integer.");
                        continue;
                    }

                    input = Integer.parseInt(inputString);

                    if (input <= 0 || input > MAX_CINEMA_ROOMS) {
                        Main.printColored(Main.ANSI_RED, "Please enter a positive integer between 1 and " + MAX_CINEMA_ROOMS + ".");
                    } else {
                        return input;
                    }
                }
            } catch (NumberFormatException e) {
                Main.printColored(Main.ANSI_RED, "Invalid input. Please enter a valid positive integer.");
            }
        } while (true);
    }


    public static double selectTicketPackage() {
        while (true) {
            Main.printColored(Main.ANSI_GREEN, "1: Regular (2D) - $10  (Standard 2D movie experience)");
            Main.printColored(Main.ANSI_YELLOW, "2: Premium (3D) - $15  (Enhanced 3D movie experience)");
            Main.printColored(Main.ANSI_GREEN, "3: IMAX - $20  (Immersive IMAX theater experience)");
            Main.printColored(Main.ANSI_YELLOW, "4: Student - $8  (Discounted ticket for students)");
            Main.printColored(Main.ANSI_GREEN, "5: Family - $30  (Special package for families(2 parents and 2 children)");
            Main.printColored(Main.ANSI_YELLOW, "6: VIP - $50  (VIP treatment with exclusive features)");

            String userInput = scanner.nextLine().trim();

            if (isValidChoice(userInput)) {
                int packageChoice = Integer.parseInt(userInput);
                Main.printColored(Main.ANSI_BLUE, "You selected: " + getPackageDescription(packageChoice));
                return getPackagePrice(packageChoice);
            } else {
                Main.printColored(Main.ANSI_RED, "Invalid choice. Please enter a number between 1 and 6.");
            }
        }
    }


    private static boolean isValidChoice(String input) {
        try {
            int choice = Integer.parseInt(input);
            return choice >= 1 && choice <= 6;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static String getPackageDescription(int choice) {
        String[] packageDescriptions = {
                "Regular (2D) - $10  (Standard 2D movie experience)",
                "Premium (3D) - $15  (Enhanced 3D movie experience)",
                "IMAX - $20  (Immersive IMAX theater experience)",
                "Student - $8  (Discounted ticket for students)",
                "Family - $30  (Special package for families)",
                "VIP - $50  (VIP treatment with exclusive amenities)"
        };

        if (choice >= 1 && choice <= 6) {
            return packageDescriptions[choice - 1];
        } else {
            return packageDescriptions[0];
        }
    }

    private static double getPackagePrice(int choice) {
        double[] packagePrices = {10.0, 15.0, 20.0, 8.0, 30.0, 50.0};

        if (choice >= 1 && choice <= 6) {
            return packagePrices[choice - 1];
        } else {
            return packagePrices[0];
        }
    }

    private static boolean containsSymbolsOrSpaces(String input) {
        return Pattern.compile("[^\\d.]+").matcher(input).find();
    }



    public static String getNonEmptyStringInputForMovie() {
        String input;
        do {
            input = scanner.nextLine().trim();
            if (input.isEmpty() || containsMultipleSpaces(input) || containsInvalidCharacters(input)) {
                Main.printColored(Main.ANSI_RED, "Invalid input. Please enter a non-empty string without excessive spaces or symbols.");
                System.out.print("Enter the title of the movie: ");
            }
        } while (input.isEmpty() || containsMultipleSpaces(input) || containsInvalidCharacters(input));
        return input;
    }

    public static String getNonEmptyStringInputForMovieTitle() {
        String input;
        do {
            input = scanner.nextLine().trim();
            if (input.isEmpty() || containsMultipleSpaces(input) || containsInvalidCharacters(input)) {
                Main.printColored(Main.ANSI_RED, "Invalid input. Please enter a non-empty string without excessive spaces or symbols.");
                String movieToUpdate = null;
                System.out.print("Enter the title of the movie to update: ");
            }
        } while (input.isEmpty() || containsMultipleSpaces(input) || containsInvalidCharacters(input));
        return input;
    }

    public static String getNonEmptyStringInputForNewMovieTitle() {
        String input;
        do {
            input = scanner.nextLine().trim();
            if (input.isEmpty() || containsMultipleSpaces(input) || containsInvalidCharacters(input)) {
                Main.printColored(Main.ANSI_RED, "Invalid input. Please enter a non-empty string without excessive spaces or symbols.");
                Main.printColored(Main.ANSI_CYAN,"Enter the new title : ");
            }
        } while (input.isEmpty() || containsMultipleSpaces(input) || containsInvalidCharacters(input));
        return input;
    }


    public static String getNonEmptyStringInputForDisplayInformation() {
        String input;
        do {
            input = scanner.nextLine().trim();
            if (input.isEmpty() || containsMultipleSpaces(input) || containsInvalidCharacters(input)) {
                Main.printColored(Main.ANSI_RED, "Invalid input. Please enter a non-empty string without excessive spaces or symbols.");
                String movieToUpdate = null;
                System.out.print("Enter the title of the movie to display information: ");
            }
        } while (input.isEmpty() || containsMultipleSpaces(input) || containsInvalidCharacters(input));
        return input;
    }

    public static String getNonEmptyStringInputForReview() {
        String input;
        do {
            input = scanner.nextLine().trim();
            if (input.isEmpty() || containsMultipleSpaces(input) || containsInvalidCharacters(input)) {
                Main.printColored(Main.ANSI_RED, "Invalid input. Please enter a non-empty string without excessive spaces or symbols.");
                String movieToUpdate = null;
                System.out.print("Enter the title of the movie for the review: ");
            }
        } while (input.isEmpty() || containsMultipleSpaces(input) || containsInvalidCharacters(input));
        return input;
    }

    public static String getNonEmptyStringInputForUsername() {
        String input;
        do {
            input = scanner.nextLine().trim();
            if (input.isEmpty() || containsMultipleSpaces(input) || containsInvalidCharacters(input)) {
                Main.printColored(Main.ANSI_RED, "Invalid input. Please enter a non-empty string without excessive spaces or symbols.");
                String movieToUpdate = null;
                System.out.print("Enter your username: ");
            }
        } while (input.isEmpty() || containsMultipleSpaces(input) || containsInvalidCharacters(input));
        return input;
    }

    public static String getNonEmptyStringInputForGenre() {
        String input;
        do {
            input = scanner.nextLine().trim();
            if (input.isEmpty() || containsMultipleSpaces(input) || containsInvalidCharacters(input)) {
                Main.printColored(Main.ANSI_RED, "Invalid input. Please enter a non-empty string without excessive spaces or symbols.");
                String movieToUpdate = null;
                Main.printColored(Main.ANSI_CYAN, "Enter the genre (ACTION, COMEDY, DRAMA, HORROR, ROMANCE,SCIENCEFICTION,ANIMATION,WESTERN): ");
            }
        } while (input.isEmpty() || containsMultipleSpaces(input) || containsInvalidCharacters(input));
        return input;
    }


    private static boolean containsMultipleSpaces(String input) {
        return input.matches(".*\\s{2,}.*");
    }


    private static boolean containsInvalidCharacters(String input) {
        return input.matches(".*[^a-zA-Z0-9\\s].*");
    }


    public static String getShowTimeInput() {
        String showTime;

        do {
            try {
                Main.printColored(Main.ANSI_CYAN,"Enter the show time between 10:00 and midnight 00:00 (format HH:mm): ");
                showTime = scanner.nextLine().trim();


                LocalTime time = LocalTime.parse(showTime);


                int hours = time.getHour();
                int minutes = time.getMinute();

                if (!isRealisticShowTime(hours, minutes)) {
                    Main.printColored(Main.ANSI_RED, "Please enter a showtime between 10:00 AM and midnight (00:00).");
                    continue;
                }

                return showTime;
            } catch (DateTimeParseException e) {
                Main.printColored(Main.ANSI_RED, "Invalid input. Please enter the show time in HH:mm format.");
            }
        } while (true);
    }

    private static boolean isRealisticShowTime(int hours, int minutes) {
        return (hours >= 10 && hours <= 23) || (hours == 0 && minutes == 0);
    }


    public static String getNewShowTimeInput() {
        String showTime;

        do {
            try {
                Main.printColored(Main.ANSI_CYAN,"Enter the new show time between 10:00 and midnight 00:00 (format HH:mm): ");
                showTime = scanner.nextLine().trim();


                LocalTime time = LocalTime.parse(showTime);


                int hours = time.getHour();
                int minutes = time.getMinute();

                if (!isRealisticShowTime(hours, minutes)) {
                    Main.printColored(Main.ANSI_RED, "Please enter a showtime between 10:00 AM and midnight (00:00).");
                    continue;
                }

                return showTime;
            } catch (DateTimeParseException e) {
                Main.printColored(Main.ANSI_RED, "Invalid input. Please enter the show time in HH:mm format.");
            }
        } while (true);
    }


    public static int getNonNegativeIntegerInputForYear() {
        int input;

        do {
            try {
                String inputString = scanner.nextLine().trim();

                if (inputString.isEmpty()) {
                    Main.printColored(Main.ANSI_RED, "Input cannot be empty. Please try again.");
                    System.out.print("Enter the year of release(between 1900 and 2024): ");
                    continue;
                }


                if (!inputString.matches("^[1-9]\\d*$")) {
                    Main.printColored(Main.ANSI_RED, "Invalid input. Please enter a valid non-negative integer.");
                    System.out.print("Enter the year of release(between 1900 and 2024): ");
                    continue;
                }

                input = Integer.parseInt(inputString);

                if (input < 1900 || input > 2024) {
                    Main.printColored(Main.ANSI_RED, "Please enter a valid release year between 1900 and 2024.");
                    System.out.print("Enter the year of release(between 1900 and 2024): ");
                } else {
                    return input;
                }
            } catch (NumberFormatException e) {
                Main.printColored(Main.ANSI_RED, "Invalid input. Please enter a valid non-negative integer.");
                System.out.print("Enter the year of release(between 1900 and 2024): ");
            }
        } while (true);
    }

    public static int getNonNegativeIntegerInputForNewYear() {
        int input;

        do {
            try {
                String inputString = scanner.nextLine().trim();

                if (inputString.isEmpty()) {
                    Main.printColored(Main.ANSI_RED, "Input cannot be empty. Please try again.");
                    System.out.print("Enter the year of release(between 1900 and 2024): ");
                    continue;
                }


                if (!inputString.matches("\\d+")) {
                    Main.printColored(Main.ANSI_RED, "Invalid input. Please enter a valid non-negative integer.");
                    System.out.print("Enter the year of release(between 1900 and 2024): ");
                    continue;
                }

                input = Integer.parseInt(inputString);

                if (input < 1900 || input > 2024) {
                    Main.printColored(Main.ANSI_RED, "Please enter a valid year between 1900 and 2024.");
                    System.out.print("Enter the year of release(between 1900 and 2024): ");
                } else {
                    return input;
                }
            } catch (NumberFormatException e) {
                Main.printColored(Main.ANSI_RED, "Invalid input. Please enter a valid non-negative integer.");
                System.out.print("Enter the year of release(between 1900 and 2024): ");
            }
        } while (true);
    }


    public static int getPositiveIntegerInputOnlyNumbersForActorAge() {
        int input;
        do {
            try {
                while (true) {
                    String inputString = scanner.nextLine().trim();

                    if (inputString.isEmpty()) {
                        Main.printColored(Main.ANSI_RED, "Input cannot be empty. Please try again.");
                        System.out.print("Actor age: ");
                        continue;
                    }


                    if (containsSymbolsOrSpaces(inputString)) {
                        Main.printColored(Main.ANSI_RED, "Invalid input. Please enter a valid  integer.");
                        System.out.print("Actor age: ");
                        continue;
                    }

                    input = Integer.parseInt(inputString);

                    if (input <= 0) {
                        Main.printColored(Main.ANSI_RED, "Please enter a positive integer.");
                        System.out.print("Actor age: ");
                    } else {
                        return input;
                    }
                }
            } catch (NumberFormatException e) {
                Main.printColored(Main.ANSI_RED, "Invalid input. Please enter a valid positive integer.");
                System.out.print("Actor age: ");
            }
        } while (true);
    }

    public static String getNonEmptyStringInputOnlyLettersForActorName() {
        String input;
        do {
            try {
                while (true) {
                    input = scanner.nextLine().trim();

                    if (input.isEmpty()) {
                        Main.printColored(Main.ANSI_RED, "Input cannot be empty. Please try again.");
                        System.out.print("Actor name: ");
                        continue;
                    }


                    if (!input.matches("^[a-zA-Z]*$")) {
                        Main.printColored(Main.ANSI_RED, "Invalid input. Input should contain only letters.");
                        System.out.print("Actor name: ");
                        continue;
                    }

                    return input;
                }
            } catch (Exception e) {
                Main.printColored(Main.ANSI_RED, "Invalid input. Please try again.");
                System.out.print("Actor name: ");
            }
        } while (true);
    }

    public static String getNonEmptyStringInputOnlyLettersForActorSurName() {
        String input;
        do {
            try {
                while (true) {
                    input = scanner.nextLine().trim();

                    if (input.isEmpty()) {
                        Main.printColored(Main.ANSI_RED, "Input cannot be empty. Please try again.");
                        System.out.print("Actor surname: ");
                        continue;
                    }


                    if (!input.matches("^[a-zA-Z]*$")) {
                        Main.printColored(Main.ANSI_RED, "Invalid input. Input should contain only letters.");
                        System.out.print("Actor surname: ");
                        continue;
                    }

                    return input;
                }
            } catch (Exception e) {
                Main.printColored(Main.ANSI_RED, "Invalid input. Please try again.");
                System.out.print("Actor surname: ");
            }
        } while (true);
    }
    static int getValidRatingInput() {
        int rating;

        while (true) {
            Main.printColored(Main.ANSI_CYAN, "Enter the rating (1 to 5): ");
            String ratingInput = scanner.nextLine().trim();

            try {
                rating = Integer.parseInt(ratingInput);

                if (rating < 1 || rating > 5) {
                    Main.printColored(Main.ANSI_RED, "Invalid rating. Please enter a number between 1 and 5.");
                } else {
                    break;
                }
            } catch (NumberFormatException e) {
                Main.printColored(Main.ANSI_RED, "Invalid input. Please enter a number between 1 and 5.");
            }
        }

        return rating;
    }


    public static void printCinemaArt() {
        Main.printColored(Main.ANSI_PURPLE, "  CCCCC     III     N     N     EEEEE     M     M     AAAAAA   ");

        Main.printColored(Main.ANSI_GREEN, " CC         I I     NN    N     E         MM   MM     A    A  ");

        Main.printColored(Main.ANSI_YELLOW, " CC         I I     N N   N     EEEE      M M M M     AAAAAA ");

        Main.printColored(Main.ANSI_BLUE, " CC         I I     N  N  N     E         M  M  M     A    A");

        Main.printColored(Main.ANSI_PURPLE, "  CCCCC     III     N   N N     EEEEE     M     M     A    A");


    }

    public static int getPositiveIntegerInput() {
        int input;
        while (true) {
            try {
                input = Integer.parseInt(scanner.nextLine());
                if (input >= 0) {
                    break;
                } else {
                    Main.printColored(Main.ANSI_RED, "Please enter a positive integer: ");
                    System.out.print("Enter your choice: ");
                }
            } catch (NumberFormatException e) {
                Main.printColored(Main.ANSI_RED, "Invalid input. Please enter a positive integer: ");
                System.out.print("Enter your choice: ");
            }
        }
        return input;
    }


    static int getValidActorIndex(Film selectedFilm) {
        int actorIndex = -1;

        while (true) {
            Main.printColored(Main.ANSI_PURPLE, "Enter the actor index you want to update (or 0 to go back): ");

            try {
                actorIndex = Integer.parseInt(scanner.nextLine());

                if (actorIndex >= 0 && actorIndex <= selectedFilm.actors.size()) {
                    break;
                } else {
                    Main.printColored(Main.ANSI_RED, "Invalid actor index. Please enter a valid number.");

                }
            } catch (NumberFormatException e) {
                Main.printColored(Main.ANSI_RED, "Invalid input. Please enter a valid number.");

            }
        }

        return actorIndex;
    }

    static boolean confirmOperation(String prompt) {
        while (true) {
            Main.printColored(Main.ANSI_PURPLE, prompt + " (yes/no): ");
            String confirmation = scanner.nextLine().toLowerCase().trim();

            if (confirmation.equals("yes")) {
                return true;
            } else if (confirmation.equals("no")) {
                return false;
            } else {
                Main.printColored(Main.ANSI_RED, "Invalid input. Please enter 'yes' or 'no'.");
            }
        }
    }

    static {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            if (scanner != null) {
                scanner.close();
            }
        }));
    }
}


