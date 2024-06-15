import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.net.ConnectException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import java.util.Scanner;
 
public class Quiz_app_frame {

    public static int category_api;
    public static String username_api;
    public static int no_of_mcqs_api;

    Quiz_app_frame() {

        // declaring the color
        final Color light_blue = Color.decode("#5699FD");
        final Color dark_blue = Color.decode("#45699E");
        final Color dark_yellow = Color.decode("#BB7FF5B");
        final Color light_red = Color.decode("#FF8585");
        final Color light_green = Color.decode("#76F592");


        String URL = "https://opentdb.com/api.php?amount=" + no_of_mcqs_api + "&category=" + category_api + "&difficulty=easy&type=multiple";
        // String URL = "https://opentdb.com/api.php?amount=" + "3" + "&category=" +"18"+"&difficulty=easy&type=multiple";

        // HTTP Request
        HttpRequest request = HttpRequest.newBuilder().GET().uri(URI.create(URL)).build();
        HttpClient client = HttpClient.newBuilder().build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                String responseBody = response.body();

                // Extract questions and answers from the parsequestion method and storing it in question array
                List<Question> questions = parseQuestions(responseBody);
                int totalQuestions = questions.size();
                int[] score = { 0 }; // changing the score variable to single varirable array because we cannot
                                     // increament a variable inside action listener

                int[] Question_counter = { 0 };
                // getting first question(including questions, choice and correct answer) from function
                Question q = questions.get(Question_counter[0]);
                String[] cor_ans = new String[1];
                cor_ans[0] = q.correctAnswer;

                JFrame main_Frame = new JFrame();

                JLabel Title = new JLabel("Quiz App Application");
                Title.setBounds(70, 70, 300, 30);
                Title.setFont(new java.awt.Font("Arial", Font.BOLD, 30));
                Title.setForeground(dark_yellow);

                JLabel Question_L = new JLabel("<html>"  + "Q#" + (Question_counter[0] + 1) + " " + q.question + "</html>");
                Question_L.setFont(new java.awt.Font("Arial", Font.BOLD, 16));
                Question_L.setBounds(25, 140, 400, 80);
                Question_L.setVerticalAlignment(SwingConstants.TOP);
                // Question_L.setText("<html>"+(Question_counter[0]+1) +" " + q.question + "</html>");
                Question_L.setForeground(dark_yellow);

                JLabel Score_L = new JLabel("Score: " + score[0] + "/" + totalQuestions);
                Score_L.setFont(new java.awt.Font("Arial", Font.BOLD, 16));
                Score_L.setBounds(345, 10, 96, 28);
                Score_L.setForeground(dark_yellow);

                JButton[] choice = new JButton[4];
                for (int i = 0; i < 4; i++) {
                    choice[i] = new JButton("" + q.choice.get(i));
                    choice[i].setFont(new java.awt.Font("Arial", 0, 14));
                    choice[i].setBounds(70, 220 + i * 40, 300, 30);
                    choice[i].setBackground(Color.WHITE);
                    choice[i].setForeground(dark_blue);
                }

                JButton Back_to_title = new JButton("Return to Title");
                Back_to_title.setFont(new java.awt.Font("Arial", Font.BOLD, 16));
                Back_to_title.setBounds(70, 400, 300, 30);
                Back_to_title.setForeground(light_blue);
                Back_to_title.setBackground(dark_yellow);
                Back_to_title.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        new Login();
                        main_Frame.dispose();
                    }
                });

                JButton Next = new JButton("Next");
                Next.setFont(new java.awt.Font("Arial", Font.BOLD, 16));
                Next.setBounds(270, 450, 100, 30);
                Next.setForeground(light_blue);
                Next.setBackground(dark_yellow);
                Next.setVisible(false);

                // vairable for checking if user click first time on button then it increament the Question_counter
                Boolean[] is_first_choice = { true };
                
                // we cannot increament loop Question_counter variable in Action listener so replacing it with array
                int[] loop_counter = { 0 };
                for (loop_counter[0] = 0; loop_counter[0] < 4; loop_counter[0]++) {

                    final int choice_selected = loop_counter[0];

                    // Adding action listener to button of choices
                    choice[loop_counter[0]].addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {

                            // getting the question according to question number
                            Question q = questions.get(Question_counter[0]);
                            String userAnswer = q.choice.get(choice_selected);

                            // updating the score if user click on correct button
                            if (userAnswer.equals(q.correctAnswer)) {
                                if (is_first_choice[0] == true) {
                                    score[0]++;
                                    Score_L.setText("Score: " + score[0] + "/" + totalQuestions);
                                    Question_counter[0]++;
                                }

                                // changing color of button to green if it is a correct answer
                                choice[choice_selected].setBackground(light_green);
                            } else {
                                if (is_first_choice[0] == true) {
                                    Question_counter[0]++;
                                }

                                // changing color of button to red if it is incorrect answer
                                choice[choice_selected].setBackground(light_red);
                                is_first_choice[0] = false;
                                for (int i = 0; i < 4; i++) {
                                    if (q.choice.get(i).equals(q.correctAnswer)) {
                                        choice[i].setBackground(light_green);
                                    }
                                }
                            }

                            // checking if it is last question
                            if (Question_counter[0] >= totalQuestions) {
                                new score_frame(username_api, score[0]);
                                System.out.println("Score of " + username_api + " is " + score[0]);
                                Next.setVisible(false);
                                main_Frame.dispose();
                                return;
                            }
                            else 
                            {
                                // making the next question visible if it is not a last question
                                Next.setVisible(true);
                            }
                        }
                    });
                }

                Next.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        is_first_choice[0] = true;
                        Question nextQuestion = questions.get(Question_counter[0]);
                        Question_L
                                .setText("<html>" + "Q#" + (Question_counter[0] + 1) + " " + nextQuestion.question + "</html>");
                        for (int j = 0; j < 4; j++) {
                            choice[j].setText("" + nextQuestion.choice.get(j));
                        }

                        // making all the JButton back to white
                        for (int i = 0; i < 4; i++) 
                        {
                            choice[i].setBackground(Color.WHITE);
                        }
                        Next.setVisible(false);
                    }
                });

                main_Frame.getContentPane().setBackground(light_blue);

                for (int i = 0; i < 4; i++) {
                    main_Frame.add(choice[i]);
                }

                main_Frame.add(Back_to_title);
                main_Frame.add(Score_L);
                main_Frame.add(Next);
                main_Frame.add(Title);
                main_Frame.add(Question_L);
                main_Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // closing by clicking closing button
                main_Frame.setSize(450, 600);
                main_Frame.setLocation(200, 50);
                main_Frame.setLayout(null);
                main_Frame.setVisible(true);

            }

            else if (response.statusCode() == 500) {
                   JOptionPane.showMessageDialog(null, "Something wrong happened Internet Error.");
                   }
                   else if (response.statusCode() == 408) {
                JOptionPane.showMessageDialog(null, "Something wrong happened Internet Error.");
                }
                else if (response.statusCode() == 504) {
                JOptionPane.showMessageDialog(null, "Something wrong happened Internet Error.");
            }

            else {
                JOptionPane.showMessageDialog(null, "Failed to fetch questions. Status code: " + response.statusCode());

            }


        }
        catch (ConnectException e) {
            JOptionPane.showMessageDialog(null, "Something wrong happened Internet Error: " + e.getMessage());
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        new Quiz_app_frame();
    }

    // Function to parse JSON response manually
    private static List<Question> parseQuestions(String json) {

        List<Question> questions = new ArrayList<>();

        //The first split method splits the string at "results":[{. The "result" word is start of the api questions
        //The second split method splits this string into individual question objects using the delimiter },{. 
        String[] items = json.split("\"results\":\\[\\{")[1].split("},\\{");


        // loop continues for item. Items here means total questions_pack
        for (String item : items) { 

            // This extract the question at particular item.
            // It then splits this part at "," to isolate the correct answer.
            String questionText = item.split("\"question\":\"")[1].split("\",")[0];
            
            // This extract the correct answer at particular item.
            String correctAnswer = item.split("\"correct_answer\":\"")[1].split("\",")[0];
            
            // This extract the incorrect answers at particular item.
            String[] incorrectAnswers = item.split("\"incorrect_answers\":\\[")[1].split("]")[0].replace("\"", "")
                    .split(",");

                
            // making array list for choices
            List<String> choice = new ArrayList<>();
            choice.add(correctAnswer);
            Collections.addAll(choice, incorrectAnswers);       // shuffing choices
            Collections.shuffle(choice);


            // returning question
            questions.add(new Question(questionText, correctAnswer, choice));
        }
        return questions;
    }
}

class Question {
    String question;
    String correctAnswer;
    List<String> choice;

    public Question(String question, String correctAnswer, List<String> choice) {
        this.question = question;
        this.correctAnswer = correctAnswer;
        this.choice = choice;
    }

}
