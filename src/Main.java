import java.util.*;
public class Main {
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
    public static void getNums(String numString) {
        Scanner scanner = new Scanner(System.in);
        boolean trueorfalse = true;
        while (trueorfalse) {
            boolean Check = true;

            List<String> Digits = new ArrayList<>();
            System.out.println("Enter a number");
            try {
                int guess = scanner.nextInt();
                String guessString = Integer.toString(guess);
                for (int i = 0; i < guessString.length(); i++) {
                    Digits.add(String.valueOf(guessString.charAt(i)));
                }
                for (int i = 0; i < Digits.size(); i++) {
                    for (int j = i + 1; j < Digits.size(); j++) {
                        if (Digits.get(i).equals(Digits.get(j))) {
                            Check = false;
                        }
                    }
                }//
                boolean trueorfalse2 = true;
                boolean notEnoughNums = true;

                if (guessString.length() < 4) {
                    notEnoughNums = false;
                }
                if (guessString.equals(numString)) {
                    System.out.println("You won!");
                    System.exit(0);
                }
                if (Check == false) {
                    System.out.println("You entered the same numbers, please try again!");
                }
                if (notEnoughNums == false) {
                    System.out.println("You have entered less than 4 digits, please try again!");
                }
                try {
                    trackBulls(numString, guess);
                    trackCows(numString, guess);
                } catch (IndexOutOfBoundsException e) {
                    System.out.println(e);
                    trueorfalse2 = false;
                }

                if (Check == true && trueorfalse2 == true && notEnoughNums == true) {
                    System.out.println("You Have " + trackBulls(numString, guess) + " Bulls");
                    System.out.println("You Have " + trackCows(numString,guess) + " Cows");
                }
            }catch (InputMismatchException e) {
                System.out.println("Error: Expected an integer input.");
                scanner.next();
            }
        }
    }
    public static void main(String[] args) {
        int num = generateFourDigitNumber();
        String numString = Integer.toString(num);
        System.out.println(num);
        getNums(numString);
    }
}