package main;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
//        playRnd();

    }

    private static void playRnd() {
        Scanner scanner = new Scanner(System.in);

        for (int iterations = 3, counter = 1, levelWeight = 3, fails = 0; counter <= iterations;
             counter++, levelWeight += 2) {
            int rnd = (int) (Math.random() * levelWeight);
            System.out.println("Level #" + counter + " of " + iterations +
                    ".\nPut your number between 0 and " + levelWeight);

            for (int attempts = 3; attempts > 0; attempts--) {
                System.out.print("You have " + attempts + " attempts: ");
                int userNumber = scanner.nextInt();

                if (userNumber == rnd) {
                    System.out.println("You win!");
                    break;
                } else {
                    String answer = userNumber > rnd ? "bigger" : "less";
                    System.out.println("Your number " + answer + " than desired number");
                    fails += 1;
                }

            }
            System.out.println("You have fails: " + fails + " (0 or negative value = you did not have misses).\n");
        }
    }
}