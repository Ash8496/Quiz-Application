package Application;

import java.util.List;
import java.util.Scanner;

public class MainApp {

    final static QuestionServer service = new QuestionImp();
    private static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {

        System.out.println("===========================");
        System.out.println("SELECT THE OPTION");
        System.out.println("1. ADD QUESTIONS");
        System.out.println("2. REMOVE QUESTIONS");
        System.out.println("3. UPDATE QUESTIONS");
        System.out.println("4. DISPLAY ALL QUESTIONS");
        System.out.println("5. TAKE TEST");
        System.out.println("6. EXIT");
        System.out.print("CHOSE THE OPTION :");
        int ch = sc.nextInt();
        sc.nextLine();

        if (ch < 5) {
            System.out.println("Enter Password : ");
            String password = service.password();
            String pass = sc.next();
            if (!pass.equals(password)) {
                System.exit(0);
            }
        }

        switch (ch){
            case 1:
               addQuestions();
                break;
            case 2:
                removeQuestions();
                break;
            case 3:
                updateQuestions();
                break;
            case 4:
                displayAllQuestions();
                break;
            case 5:
                takeTest();
                break;
            case 6:
                System.exit(0);
            default:
                System.out.println("INVALID OPTIONS");
        }
        main(args);
    }


    public static void addQuestions(){
        System.out.println("\n===========================");
        System.out.println("ENTER THE QUESTION");
        String question = sc.nextLine();
        System.out.println("ENTER OPTION 1 ");
        String option1 = sc.nextLine();
        System.out.println("ENTER OPTION 2 ");
        String option2 = sc.nextLine();
        System.out.println("ENTER OPTION 3 ");
        String option3 = sc.nextLine();
        System.out.println("ENTER THE ANSWER");
        String answer = sc.nextLine();

        Questions newQuestion = new Questions(question, option1, option2, option3, answer);
        int n = service.addNewQuestions(newQuestion);
        System.out.println(n + " RECORD ADDED !!");

    }

    public static void removeQuestions(){
        System.out.println("========================================");
        System.out.println("ENTER THE QUESTION ID YOU WANT TO REMOVE");
            int id = sc.nextInt();
            int n =service.removeQuestion(id);
        System.out.println(n + " QUESTION DELETED !! ");
        }

    public static void updateQuestions(){
            System.out.println("===========================");
            System.out.println("ENTER THE OPTION");
            System.out.println("1. MODIFY QUESTION");
            System.out.println("2. MODIFY OPTION");
            System.out.println("3. EXIT");
            int opt = sc.nextInt();
            sc.nextLine();

            switch (opt){
                case 1:
                    System.out.println("===========================");
                    System.out.println("MODIFY QUESTIONS");
                    System.out.print("ENTER THE QUESTION ID :");
                    int id = sc.nextInt();
                    sc.nextLine();
                    System.out.println("ENTER THE QUESTION");
                    String question = sc.nextLine();
                    service.updateQuestion(id,question);
                case 2:
                    System.out.println("===========================");
                    System.out.println("MODIFY OPTION");
                    System.out.print("ENTER THE QUESTION ID :");
                    int id1 = sc.nextInt();
                    sc.nextLine();
                    System.out.println("ENTER OPTION 1 ");
                    String option1 = sc.nextLine();
                    System.out.println("ENTER OPTION 2 ");
                    String option2 = sc.nextLine();
                    System.out.println("ENTER OPTION 3 ");
                    String option3 = sc.nextLine();
                    System.out.println("ENTER THE ANSWER");
                    String answer = sc.nextLine();
                    service.updateOption(id1,option1,option2,option3,answer);
                case 3:
                    return;
            }
            updateQuestions();

        }

    private static void displayAllQuestions() {
        List<Questions> questionsList = service.getAllQuestions();
        for(Questions q : questionsList){
            System.out.println("Q"+q.getQuestionId()+". "+q.getQuestion());
            System.out.println("1. "+q.getOption1());
            System.out.println("2. "+q.getOption2());
            System.out.println("3. "+q.getOption3());
            System.out.println("---->"+q.getAnswer());
            System.out.println("\n==========================\n");
        }
    }

    private static void takeTest() {

        List<Questions> questionsList = service.getAllQuestions();
        int marks = 0;
        System.err.println("THERE WILL BE TOTAL 20 QUESTIONS EACH HAVING +5 FOR CORRECT ANSWER ANS -2 FOR INCORRECT ANSWER");
        System.err.println("NOTE: WHILE GIVING THE QUIZ SIMPLY COPY PASTE THE OPTION FOR AVOIDING ANY MISTAKES, EMPTY SPACE WAS ALSO CONSIDERED AS CHARACTER SO CHECK BEFORE SUBMITTING THE ANSWER.");
        System.err.println("PRESS ENTER TO START THE TEST,GOOD LUCK!!");

        System.out.println("READY FOR TEST");
        String ans = sc.nextLine();
        for (Questions q : questionsList) {
            System.out.println("\n==================================================\n");
            System.out.println("Q" + q.getQuestionId() + ". " + q.getQuestion());
            System.out.println("1. " + q.getOption1());
            System.out.println("2. " + q.getOption2());
            System.out.println("3. " + q.getOption3());
            System.out.print("ENTER YOUR ANSWER :");
            ans = sc.nextLine();
            if (ans.equals(q.getAnswer())) {
                marks += 5;
            } else
                marks -= 2;
        }
        System.out.println("\n============================");
        System.out.println("YOUR TOTAL MARKS IS :"+marks);
        System.out.println("============================");
        System.exit(0);
    }

}

