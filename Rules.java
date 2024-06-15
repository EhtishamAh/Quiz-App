import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Rules {

    public static JFrame rules_frame;
    String name;
    JButton start, back;

    Rules(String name) {
        rules_frame = new JFrame();

        this.name = name;

        JLabel heading = new JLabel("Welcome " + name + " to Nust Entry Test");
        heading.setBounds(50, 20, 700, 30);
        heading.setFont(new Font("Viner Hand ITC", Font.BOLD, 28));
        heading.setForeground(new Color(30, 144, 254));
        rules_frame.add(heading);

        JLabel rules = new JLabel();
        rules.setBounds(20, 90, 700, 350);
        rules.setFont(new Font("Tahoma", Font.PLAIN, 16));
        rules.setText(
                "<html>" +
                        "1. You are trained to be a programmer and not a story teller, answer point to point"
                        + "<br><br>" +
                        "2. Do not unnecessarily smile at the person sitting next to you, they may also not know the answer"
                        + "<br><br>" +
                        "3. You may have lot of options in life but here all the questions are compulsory" + "<br><br>"
                        +
                        "4. Crying is allowed but please do so quietly." + "<br><br>" +
                        "5. Only a fool asks and a wise answers (Be wise, not otherwise)" + "<br><br>" +
                        "6. Do not get nervous if your friend is answering more questions, may be he/she is doing Jai Mata Di"
                        + "<br><br>" +
                        "7. Brace yourself, this paper is not for the faint hearted" + "<br><br>" +
                        "8. May you know more than what John Snow knows, Good Luck" + "<br><br>" +
                        "<html>");
        rules_frame.add(rules);

        back = new JButton("Back");
        back.setBounds(250, 500, 100, 30);
        back.setBackground(new Color(30, 144, 254));
        back.setForeground(Color.WHITE);
        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == back) {
                    rules_frame.dispose();
                    new Login();
                }
            }
        });
        rules_frame.add(back);

        start = new JButton("Start");
        start.setBounds(400, 500, 100, 30);
        start.setBackground(new Color(30, 144, 254));
        start.setForeground(Color.WHITE);
        start.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == start) {
                    rules_frame.dispose();
                    new Quiz_app_frame();
                }
            }
        });
        rules_frame.add(start);

        rules_frame.getContentPane().setBackground(Color.WHITE);
        rules_frame.setLayout(null);
        rules_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        rules_frame.setSize(800, 650);
        rules_frame.setLocation(350, 17);
        rules_frame.setVisible(true);
    }

    public static void main(String[] args) {
        new Rules("User");
    }
}
