package Application;

import java.util.List;

public interface QuestionServer {


    int addNewQuestions(Questions newQuestion);

    int removeQuestion(int id);

    int updateQuestion(Questions uptQuestion);

    void updateQuestion(int id, String question);

    void updateOption(int id1, String option1, String option2, String option3, String answer);

    List<Questions> getAllQuestions();

    String password();
}
