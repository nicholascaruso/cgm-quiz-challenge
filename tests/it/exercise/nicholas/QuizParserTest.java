package it.exercise.nicholas;


import it.exercise.nicholas.util.Constants;
import it.exercise.nicholas.util.InputFormatException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class QuizParserTest {
    private QuizParser parser;

    @BeforeEach
    public void init(){
        parser = QuizParser.getInstance();
    }

    @Test
    public void questionSavedWithSuccess(){
        String result = parser.parse(TestConstants.QUESTION_WITH_ANSWERS);
        assertEquals(result,Constants.NEW_QUESTION_MSG);
        assertTrue(parser.getQuestionsWithAnswers().containsKey(TestConstants.QUESTION));

        assertTrue(parser.getQuestionsWithAnswers().get(TestConstants.QUESTION).contains(TestConstants.ANSWER_FIRST));
        assertTrue(parser.getQuestionsWithAnswers().get(TestConstants.QUESTION).contains(TestConstants.ANSWER_SECOND));
        assertTrue(parser.getQuestionsWithAnswers().get(TestConstants.QUESTION).contains(TestConstants.ANSWER_THIRD));

    }


    @Test
    public void wrongQuestionFormat() {
        Exception exception = assertThrows(InputFormatException.class,() -> {
            parser.parse(TestConstants.WRONG_QUESTION);
        });

        String expectedMessage = Constants.NO_QUESTION_MSG;
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void noAnswerProvided() {
        Exception exception = assertThrows(InputFormatException.class,() -> {
            parser.parse(TestConstants.QUESTION);
        });

        String expectedMessage = Constants.NO_ANSWERS;
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

   @Test
    public void exceededLenght(){
       Exception exception = assertThrows(InputFormatException.class,() -> {
           parser.parse(TestConstants.LONG_QUESTION);
       });
       String expectedMessage = Constants.QUESTION_LENGHT_EXCEEDED;
       String actualMessage = exception.getMessage();

       assertTrue(actualMessage.contains(expectedMessage));
   }

}