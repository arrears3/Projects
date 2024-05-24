import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Question {
    private String prompt;
    private String answer;

    public Question(String prompt, String answer) {
        this.prompt = prompt;
        this.answer = answer;
    }

    public String getPrompt() {
        return prompt;
    }

    public String getAnswer() {
        return answer;
    }
}

class Quiz {
    private List<Question> questions;
    private int score;

    public Quiz() {
        questions = new ArrayList<>();
        score = 0;
        populateQuestions();
    }

    private void populateQuestions() {
        questions.add(new Question("What is the capital of France?\n(a) Paris\n(b) London\n(c) Rome", "a"));
        questions.add(new Question("What is 5 + 7?\n(a) 10\n(b) 12\n(c) 15", "b"));
        questions.add(new Question("What is the largest planet?\n(a) Earth\n(b) Jupiter\n(c) Mars", "b"));
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);

        for (Question question : questions) {
            System.out.println(question.getPrompt());
            String answer = scanner.nextLine();
            if (answer.equals(question.getAnswer())) {
                score++;
            }
        }

        System.out.println("You got " + score + "/" + questions.size() + " correct.");
    }
}

public class OnlineQuiz {
    public static void main(String[] args) {
        Quiz quiz = new Quiz();
        quiz.run();
    }
}

