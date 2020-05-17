package it.exercise.nicholas;

import it.exercise.nicholas.util.Constants;
import it.exercise.nicholas.util.InputFormatException;

import java.util.Scanner;

public class AppDemo {
    private static QuizParser parser;
    private static Scanner scanner;

    public static void main( String []args ) {
        initCommands();
        scanner = new Scanner(System.in).useDelimiter(Constants.NEW_LINE);
        String userInput="";
        String result="";
        boolean exitRequested = false;
        while(! exitRequested){
            userInput = scanner.nextLine().trim();
            switch(userInput){
                case Constants.EXIT_COMMAND:
                    exitRequested = true;
                    break;
                default:
                    parser =  QuizParser.getInstance();
                    try {
                        result = parser.parse(userInput);
                        System.out.println(result);
                    } catch (InputFormatException e) {
                        System.out.println(e.getMessage());
                    }
            }
        }
        scanner.close();
    }

   private static void initCommands(){
        System.out.println(Constants.WELCOME_MSG);
        System.out.println(Constants.EXIT_MSG);
    }

}
