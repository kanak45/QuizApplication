
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class QuizApplication {
    private static class Question {
        String question;
        String[] options;
        int correctAnswer;

        Question(String question, String[] options, int correctAnswer) {
            this.question = question;
            this.options = options;
            this.correctAnswer = correctAnswer;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Question> quizQuestions = new ArrayList<>();

        // Sample questions
        quizQuestions.add(new Question("What is the capital of India?", new String[]{"1. Paris", "2. New Delhi", "3. Rome", "4. Berlin"}, 1));
        quizQuestions.add(new Question("What is 2 + 3?", new String[]{"1. 3", "2. 4", "3. 5", "4. 6"}, 3));
        quizQuestions.add(new Question("Which planet is known as the Red Planet?", new String[]{"1. Earth", "2. Mars", "3. Jupiter", "4. Saturn"}, 1));
        quizQuestions.add(new Question("What is the largest ocean on Earth?", new String[]{"1. Atlantic Ocean", "2. Indian Ocean", "3. Arctic Ocean", "4. Pacific Ocean"}, 3));
        quizQuestions.add(new Question("Which element has the chemical symbol Ag?", new String[]{"1. Oxygen", "2. Gold", "3. Silver", "4. Iron"}, 2));

        int score = 0;
        int totalQuestions = quizQuestions.size();
        boolean[] answeredCorrectly = new boolean[totalQuestions];

        for (int i = 0; i < totalQuestions; i++) {
            Question q = quizQuestions.get(i);
            System.out.println("\nQuestion " + (i + 1) + ": " + q.question);
            for (String option : q.options) {
                System.out.println(option);
            }

            // Start a timer for answering the question
            final int questionIndex = i;  // Make the index final
            Timer timer = new Timer();
            TimerTask task = new TimerTask() {
                @Override
                public void run() {
                    System.out.println("\nTime's up!");
                    answeredCorrectly[questionIndex] = false; // Mark as incorrect
                }
            };
            timer.schedule(task, 20000); // 10 seconds for each question

            // User answer
            System.out.print("Your answer (1-4): ");
            int userAnswer = scanner.nextInt() - 1;

            // Cancel the timer if answered in time
            timer.cancel();

            // Check answer
            if (userAnswer == q.correctAnswer) {
                score++;
                answeredCorrectly[questionIndex] = true; // Mark as correct
                System.out.println("Correct!");
            } else {
                answeredCorrectly[questionIndex] = false; // Mark as incorrect
                System.out.println("Incorrect. The correct answer was: " + (q.correctAnswer + 1));
            }
        }

        // Display results
        System.out.println("\nQuiz Finished!");
        System.out.println("Your Score: " + score + "/" + totalQuestions);
        for (int i = 0; i < totalQuestions; i++) {
            System.out.println("Question " + (i + 1) + ": " + (answeredCorrectly[i] ? "Correct" : "Incorrect"));
        }

        // Close the scanner
        scanner.close();
    }
}
