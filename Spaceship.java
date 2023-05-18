import java.util.Arrays;
import java.nio.file.*;

public class Spaceship extends Game {
    private static final int MAX_TRIES = 9;
    private String wordToGuess;
    private int numberWrongGuess;
    private String currentLetter;
    private char[] currentGuess;
    private String missedLetters;
    private String alreadyGuessed;
    private String[] words = {"ANT", "BABOON", "BADGER", "BAT", "BEAR", "BEAVER", "CAMEL", "CAT", "CLAM", "COBRA", "COUGAR", "COYOTE", "CROW", "DEER", "DOG", "DONKEY", "DUCK", "EAGLE", "FERRET", "FOX", "FROG", "GOAT", "GOOSE", "HAWK", "LION", "LIZARD", "LLAMA", "MOLE", "MONKEY", "MOOSE", "MOUSE", "MULE", "NEWT", "OTTER", "OWL", "PANDA", "PARROT", "PIGEON", "PYTHON", "RABBIT", "RAM", "RAT", "RAVEN", "RHINO", "SALMON", "SEAL", "SHARK", "SHEEP", "SKUNK", "SLOTH", "SNAKE", "SPIDER", "STORK", "SWAN", "TIGER", "TOAD", "TROUT", "TURKEY", "TURTLE", "WEASEL", "WHALE", "WOLF", "WOMBAT", "ZEBRA"};
    private String[] rocketArt;
    private boolean win;

    public Spaceship() {
        int randomIndex = (int) (Math.random() * words.length);
        wordToGuess = words[randomIndex].toUpperCase();
        currentGuess = new char[wordToGuess.length()];
        Arrays.fill(currentGuess, '_');
        missedLetters = "";
        currentLetter = "";
        alreadyGuessed = "";
        numberWrongGuess = 0;
        win = false;
        try {
            rocketArt = Files.readString(Path.of("rockets.txt")).split(",");
        } catch(Exception e){
            System.out.println(e);
        }  
    }

    @Override
    public String explainRules() {
        String rules = "Spaceship is a sanitized version of Hangman.\n" 
                + "Given only the number of letters in a word,\n"
                + "you must decipher the word in 9 tries,\n"
                + "guessing one letter at a time.\n"
                + "A spaceship shows your progress. When it\n"
                + "reaches LIFTOFF, you are out of guesses.\n"
                + "Let's play!\n\n";
        return rules;
    }

    @Override
    public String setup() {
        //  showed the rocket image; showed any missed letters; showed the word spaced and correctly guessed letters (as in l_tt_rs); and asked the user to guess a letter.
        System.out.println(rocketArt[numberWrongGuess]);
        if (numberWrongGuess == 0) {
            System.out.println("Missed letters: No missed letters yet");
        } else {
            System.out.println("Missed letters: " + missedLetters);  
        }
        System.out.println(String.join(" ", new String(currentGuess).split("")));
        String message = "Guess a letter\n";
        return message;
    }

    @Override
    public boolean goodPlayerInput(String guess) {
        if (guess.length() == 1 && Character.isLetter(guess.charAt(0)) && missedLetters.indexOf(guess.toUpperCase()) < 0 && alreadyGuessed.indexOf(guess.toUpperCase()) < 0) {
            currentLetter = guess.toUpperCase();
            return true;
        }
        return false;
    }

    @Override
    public String checkWinOrLose() {
        if (wordToGuess.indexOf(currentLetter) >= 0) {
            alreadyGuessed += currentLetter;
            for (int i = 0; i < wordToGuess.length(); i++) {
                if (wordToGuess.charAt(i) == currentLetter.charAt(0)) {
                    currentGuess[i] = currentLetter.charAt(0);
                }
            }
            if (!String.valueOf(currentGuess).contains("_")) {
                win = true;
                return "  The word was " + wordToGuess + "\n  Congrats...You won! "+ "\nYour game is done. Good bye!";
            }
            System.out.println("Yes, that letter is in the word.");

        } else {
            numberWrongGuess++;
            if (numberWrongGuess == MAX_TRIES) {
                System.out.println(rocketArt[numberWrongGuess]);
                String message = "  The word was " + wordToGuess 
                                + "\nYour game is done. Good bye!";
                return message;
            }
            missedLetters += " " + currentLetter;
            System.out.println("No, that letter is not in the word. The engines rumble...");
        }  
        return "";
    }
    
    @Override
    public boolean canPlayAgain() {
        if (numberWrongGuess < MAX_TRIES && !win) {
            return true;
        }
        return false;
    }
}