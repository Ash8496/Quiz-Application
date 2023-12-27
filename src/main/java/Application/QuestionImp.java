package Application;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class QuestionImp implements QuestionServer {


    private static Connection conn = null;

    static {
        String url = "jdbc:mysql://localhost:3306/questiondb";
        String userName = "root";
        String password = "tiger";

        try {
            conn = DriverManager.getConnection(url, userName, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public int addNewQuestions(Questions newQuestion) {
        int n = 0 ;
        String qry = "INSERT INTO QUESTION_INFO (question, option1, option2, option3, answer) VALUES (?, ?, ?, ?, ?)";

        try {
            PreparedStatement stat = conn.prepareStatement(qry);
            stat.setString(1, newQuestion.getQuestion());
            stat.setString(2, newQuestion.getOption1());
            stat.setString(3, newQuestion.getOption2());
            stat.setString(4, newQuestion.getOption3());
            stat.setString(5, newQuestion.getAnswer());
            n=stat.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Insert error");

        }return n;
    }

        @Override
        public int removeQuestion ( int id){
            int n = 0 ;
            String qry = "DELETE FROM QUESTION_INFO WHERE QUESTIONID = ?";

            try {
                PreparedStatement stat = conn.prepareStatement(qry);
                stat.setInt(1, id);
                n=stat.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            return n;

        }

    @Override
    public int updateQuestion(Questions uptQuestion) {

        String updateQuery = "UPDATE QUESTION_INFO SET QUESTION= ? WHERE QUESTIONID= ?";
        int n =0;
        try {
            PreparedStatement pstmt = conn.prepareStatement(updateQuery);
            pstmt.setString(1 , uptQuestion.getQuestion());
            n = pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("QUESTION NOT FOUND !!");
        }
        return n;
    }

    @Override
    public void updateQuestion(int id, String question) {
        String qry = "update question_info set question = ? where questionId = ?";

        try {
            PreparedStatement stat = conn.prepareStatement(qry);
            stat.setString(1, question);
            stat.setInt(2, id);
            stat.executeUpdate();
        } catch (SQLException e) {

        }

    }

    @Override
    public void updateOption(int id1, String option1, String option2, String option3, String answer) {
        String qry = "update question_info set option1 = ?, option2 = ?, option3 = ?, answer = ? where questionId = ?";

        try{
            PreparedStatement stat = conn.prepareStatement(qry);
            stat.setString(1, option1);
            stat.setString(2, option2);
            stat.setString(3, option3);
            stat.setString(4, answer);
            stat.setInt(5, id1);
            stat.executeUpdate();
        } catch (Exception e) {

        }

    }

    @Override
    public List<Questions> getAllQuestions() {
        String SelectQry = "SELECT * FROM question_info";
        List<Questions> questionsList = new ArrayList<>();

        try {
            Statement stat = conn.createStatement();
            ResultSet rs = stat.executeQuery(SelectQry);
            while(rs.next()){
                Questions q = new Questions();
                q.setQuestionId(rs.getInt(1));
                q.setQuestion(rs.getString(2));
                q.setOption1(rs.getString(3));
                q.setOption2(rs.getString(4));
                q.setOption3(rs.getString(5));
                q.setAnswer(rs.getString(6));

                questionsList.add(q);
            }

        } catch (SQLException e) {

        }
        return questionsList;

    }

    @Override
    public String password() {
        String qry = "select passwordID from passwords";
        String pass = "";
        Statement stat = null;
        try {
            stat = conn.createStatement();
            ResultSet rs = stat.executeQuery(qry);
            while (rs.next()) {
                pass = rs.getString(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
   return pass;
    }
}

