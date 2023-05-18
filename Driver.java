import java.util.Scanner;
import java.io.IOException;

public class Driver {

    public static void main(String[] args) throws IOException {
        Scanner scan = new Scanner(System.in);
        Game g;
        int menuChoice = 0;
        boolean quit = false;

        while (menuChoice != 1 && menuChoice != 2 && !quit) {
            System.out.println("Welcome to the CS112 Gaming System.");
            System.out.println("Enter 1 to play Spaceship.");
            System.out.println("Enter 2 to play Cho-Han.");
            System.out.println("Or enter QUIT at anytime to end the game.");
        
            if (scan.hasNextInt()) {
                menuChoice = scan.nextInt();
                if (menuChoice != 1 && menuChoice != 2) {
                    System.out.println("Please enter 1 or 2.");
                }
            } else {
                String input = scan.next();
                if (input.equalsIgnoreCase("QUIT")) {
                    quit = true;
                } else {
                    System.out.println("You must enter a number.");
                }
            }
        }
        if (!quit) {
        	if(menuChoice == 1){
                g = new Spaceship();
            } else {
                g = new ChoHan();
            }
            System.out.println(g.explainRules());
            String guess;
            while(g.canPlayAgain()) {
                System.out.println(g.setup());
                guess = scan.next().toLowerCase();
                while (!g.goodPlayerInput(guess) && !guess.equals("quit")) {
                    System.out.println("Sorry, bad input. Please try again");
                    guess = scan.next().toLowerCase();
                }
                if (guess.equals("quit")) {
                    System.out.println("Goodbye!");
                    System.exit(0);
                }
                System.out.println(g.checkWinOrLose());
            }    
        } else {
            System.out.println("Goodbye!");
        }
        scan.close();
    }
}