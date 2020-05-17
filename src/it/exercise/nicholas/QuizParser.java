package it.exercise.nicholas;

import it.exercise.nicholas.util.InputFormatException;

import java.util.*;

public class QuizParser {
    private static QuizParser instance;

    private Map<String, List<String>> questions;
    private QuizValidator validator;


    private QuizParser(){
        questions = new HashMap<>();
        validator = QuizValidator.getInstance();
    }

    public static QuizParser getInstance(){
        if(instance == null){
            instance = new QuizParser();
        }
        return instance;
    }

    public String parse(String inputText) throws InputFormatException {
        validator.validateQuestion(inputText);
        List<String> splittedInput = splitAnswerFromQuestions(inputText);
        String question = splittedInput.remove(0).trim();
        List<String> answers = formatAnswers(splittedInput);
        if(questions.containsKey(question)){
            //Print Answers already stored
            return printAnswers(questions.get(question));
        }
        else{
            //Save new Question with answers
            addQuestionWithAnswers(question,answers);
            return "the answer to life, universe and everything is 42";
        }

    }

    private void addQuestionWithAnswers(String question, List<String> answers) throws InputFormatException {
        validator.validateAnswers(answers);
        questions.put(question,answers);

    }

    private List<String> splitAnswerFromQuestions(String inputText) throws InputFormatException {
        List<String> splittedInput = new LinkedList<>(Arrays.asList(inputText.split("(?<=\\?)\\s+|\\s+(?=\")")));
        validator.validateSplittedInput(splittedInput);
        return splittedInput;
    }

    private List<String> formatAnswers(List<String > answers){
        List<String> formattedAnswers = new ArrayList<>();
        for(String answ: answers){
            formattedAnswers.add(answ.replace("\"",""));
        }
        return formattedAnswers;
    }

    private String printAnswers(List<String> answers){
        StringBuilder sb = new StringBuilder();
        for(String answer: answers){
            sb.append(answer);
            sb.append("\n");
        }
        return sb.toString();
    }

}
