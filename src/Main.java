import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);



    public static int generateFourDigitNumber() {
        List<Integer> digits = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            digits.add(i);
        }
        Collections.shuffle(digits);
        int result = 0;
        int placeValue = 1000;
        for (int i = 0; i < 4; i++) {
            result += digits.get(i) * placeValue;
            placeValue /= 10;
        }
        return result;
    }
    public static int trackBulls(String numString, int guess) {

        List<Integer> bulls = new ArrayList<>();
        String guessString = Integer.toString(guess);
        for (int i = 0; i < guessString.length(); i++) {
            if (numString.charAt(i) == guessString.charAt(i)) {
                bulls.add(Character.getNumericValue(numString.charAt(i)));
            }
        }
        return bulls.size();
    }

    public static int trackCows(String numString, int guess) {

        List<Integer> cows = new ArrayList<>();
        String guessString = Integer.toString(guess);
        for (int i = 0; i < guessString.length(); i++) {
            if (numString.contains(Character.toString(guessString.charAt(i))) &&
                    numString.charAt(i) !=guessString.charAt(i)) {
                cows.add(Character.getNumericValue(guessString.charAt(i)));
            }
        }
        return cows.size();
    }
    public static int getGuessFromUser() {
        System.out.println("Enter a number");
        int guess = scanner.nextInt();
        try {
            return guess;
        } catch (InputMismatchException e) {
            System.out.println("Error: Expected an integer input.");
            scanner.next();
            return -1;
        }
    }
    public static boolean validateGuess(int guess) {
        String guessString = Integer.toString(guess);
        boolean check = true;
        List<String> digits = new ArrayList<>();
        for (int i = 0; i < guessString.length(); i++) {
            digits.add(String.valueOf(guessString.charAt(i)));
        }

        for (int i = 0; i < digits.size(); i++) {
            for (int j = i + 1; j < digits.size(); j++) {
                if (digits.get(i).equals(digits.get(j))) {
                    check = false;
                    break;
                }
            }
        }


        if (guessString.length() < 4) {
            System.out.println("You have entered less than 4 digits, please try again!");
            return false;
        } else if (!check) {
            System.out.println("You entered duplicate numbers, please try again!");
            return false;
        }
        else if(guessString.length() >4) {
            System.out.println("You have entered more than 4 digits, please try again!");
            return false;
        }

        return true;
    }

    public static void playGame(String numString) {
        boolean gameOver = false;
        while (!gameOver) {
            int guess = getGuessFromUser();
            if (guess == -1) {
                break;
            }

            boolean validGuess = validateGuess(guess);
            if (validGuess ==true) {
                int bulls = trackBulls(numString, guess);
                int cows = trackCows(numString, guess);

                if (guess == Integer.parseInt(numString)) {
                    System.out.println("You won!");
                    break;
                }

                printResult(bulls, cows);
            }
        }
    } public static void printResult(int bulls, int cows) {
        System.out.println("You Have " + bulls + " Bulls" + " And "+ cows + " cows");
    }
    public static void main(String[] args) {
        int num = generateFourDigitNumber();
        String numString = Integer.toString(num);
        System.out.println(num);
        playGame(numString);
    }

}