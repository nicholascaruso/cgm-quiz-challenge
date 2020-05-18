package it.exercise.nicholas;

import it.exercise.nicholas.util.Constants;
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

    public Map<String,List<String>> getQuestionsWithAnswers(){
        return this.questions;
    }

    public String parse(String inputText) throws InputFormatException {
        validator.validateQuestion(inputText);
        List<String> splittedInput = splitAnswerFromQuestions(inputText);
        String question = splittedInput.remove(0).trim();
        List<String> answers = formatAnswers(splittedInput);
        if(questions.containsKey(question)){
            //Print Answers from existing question
            return printAnswers(questions.get(question));
        }
        else{
            //Save new Question with answers
            addQuestionWithAnswers(question,answers);
            return Constants.NEW_QUESTION_MSG;
        }

    }

    private void addQuestionWithAnswers(String question, List<String> answers) throws InputFormatException {
        validator.validateAnswers(answers);
        questions.put(question,answers);

    }

    private List<String> splitAnswerFromQuestions(String inputText) throws InputFormatException {
        List<String> splittedInput = new LinkedList<>(Arrays.asList(inputText.split(Constants.REGEX_PATTERN)));
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
        Iterator<String> iterator = answers.iterator();
        while(iterator.hasNext()){
            sb.append(Constants.BULLET_POINT);
            sb.append(iterator.next());
            if(iterator.hasNext()){sb.append(Constants.NEW_LINE);}
        }
        return sb.toString();
    }

}
