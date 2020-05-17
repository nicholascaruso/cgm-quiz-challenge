package it.exercise.nicholas;

import it.exercise.nicholas.util.Constants;
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

    public void validateQuestion(String question) throws InputFormatException {
        if(! question.contains("?")){
            throw new InputFormatException(Constants.NO_QUESTION_MSG);
        }
       if(isAboveCharLimit(question)){
           throw new InputFormatException(Constants.QUESTION_LENGHT_EXCEEDED);
       }
    }

    public void validateSplittedInput(List<String> splittedInput) throws InputFormatException {
        if(splittedInput == null || splittedInput.isEmpty()){
            throw new InputFormatException(Constants.GENERIC_ERROR);
        }
    }

    public void validateAnswers(List<String> answers) throws InputFormatException {
        if(answers == null || answers.isEmpty()){
            throw new InputFormatException(Constants.NO_ANSWERS);
        }
        answers.forEach(answer -> {
            if(isAboveCharLimit(answer)){
                throw new InputFormatException(Constants.ANSWER_LENGHT_EXCEEDED);
            }
        });
    }

    private boolean isAboveCharLimit(String input) {
        if(input.length()>Constants.MAX_LENGHT){
                return true;
            }
        return false;
    }

}
