public class ChoHan extends Game {
    private static final String[] JAPANESE_NUMBERS = {"ICHI", "NI", "SAN", "SHI", "GO", "ROKU", "NANA", "HACHI", "KYUU", "JYUU"};
    private String correctAnswer;
    private String choice;
    private int dice1;
    private int dice2;
    private int balance;

    public ChoHan() {
        dice1 = 0;
        dice2 = 0;
        balance = 2;
    }

    @Override
    public String explainRules() {
        String rules = "Cho-Han is a traditional Japanese guessing game.\n"
                + "A dealer rolls two 6-sided die, and you must guess\n"
                + "if their sum is CHO (even) or HAN (odd).\n"
                + "You start with $2. A wrong answer loses you $1,\n"
                + "a right answer wins $1. You must stop playing at $0.\n"
                + "Let's play!\n";
        return rules;
    }

    @Override
    public String setup() {
        // "rolled two dice" and summed them, then asked the user to guess "Cho" or "Han"
        dice1 = (int)(Math.random() * 6) + 1;
        dice2 = (int)(Math.random() * 6) + 1;
        int sum = dice1 + dice2;
        if (sum % 2 == 0) {
            correctAnswer = "CHO";
        } else {
            correctAnswer = "HAN";
        }
        String message = "The dealer swirls the cup and you hear the rattle of dice.\n"
                + "The dealer slams the cup on the floor, still covering the\n"
                + "dice and asks for your bet.\n\n"
                + "      CHO (even) or HAN (odd)?";
        return message;
    }

    @Override
    public boolean goodPlayerInput(String guess) {
        if (guess.equalsIgnoreCase("cho") || guess.equalsIgnoreCase("han")) {
            this.choice = guess;
            return true;
        }
        return false;
    }

    @Override
    public String checkWinOrLose() {
        String sumInJapanese = JAPANESE_NUMBERS[dice1 - 1] + " - " + JAPANESE_NUMBERS[dice2 - 1];
        System.out.printf("The dealer lifts the cups to reveal: %n%s%n%d - %d%n", sumInJapanese, dice1, dice2);
        System.out.println("The correct answer is " + correctAnswer);
        if (choice.equalsIgnoreCase(correctAnswer)) {
            balance++;
            System.out.printf("You won! You started the game at $2, and now you have $%d%n", balance);
        } else {
            balance--;
            System.out.printf("You lost! You started the game at $2, and now you have $%d%n", balance);
        }
        if (balance <= 0) {
            return "\nYour game is done. Good bye!";
        }
        return "";
    }

    @Override
    public boolean canPlayAgain() {
        if (balance > 0) {
            return true;
        }
        return false;
    }
}