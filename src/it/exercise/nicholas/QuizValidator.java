package it.exercise.nicholas;

import it.exercise.nicholas.util.InputFormatException;

import java.util.List;

public class QuizValidator {

    private static QuizValidator instance;

    private QuizValidator(){
    }

    public static QuizValidator getInstance(){
        if(instance == null){
            instance = new QuizValidator();
        }
        return instance;
    }

    public void validateQuestion(String text) throws InputFormatException {
        if(! text.contains("?")){
            throw new InputFormatException("You didn't ask a question, try again :)");
        }
    }

    public void validateSplittedInput(List<String> splittedInput) throws InputFormatException {
        if(splittedInput == null || splittedInput.isEmpty()){
            throw new InputFormatException("Error with your insertion, try again");
        }
    }

    public void validateAnswers(List<String> answers) throws InputFormatException {
        if(answers == null || answers.isEmpty()){
            throw new InputFormatException("You Added Question but you didn't provide answers, try again");
        }
    }


}
