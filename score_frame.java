import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

public class score_frame {

    score_frame(String username, int Score){

        JFrame score_frame = new JFrame();

        // loading image on left-bottom of frame
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("score.png"));        
        // resizing the image (not croping it)
        Image i2 = i1.getImage().getScaledInstance(300, 250, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);

        JLabel image = new JLabel(i3);
        image.setBounds(0,200,300,250);

        
        JLabel heading = new JLabel("Thankyou " + username + " for playing Quiz Game.");
        heading.setBounds(45,30,700,30);
        // MCQs.setFont(new Font("Mongolian Baiti", Font.BOLD, 18));
        heading.setFont(new Font("Mongolian Baiti", Font.PLAIN,30));
        heading.setForeground(new Color(30, 144, 254));
        
        JLabel score_l  = new JLabel("Your Score is " +Score + "/" + Quiz_app_frame.no_of_mcqs_api);
        score_l.setBounds(350,200,300,30);
        score_l.setFont(new Font("Mongolian Baiti", Font.PLAIN,30));
        score_l.setForeground(new Color(30, 144, 254));


        JButton play_again = new JButton("Play Again");
        play_again.setBounds(351,270,120,40);
        play_again.setFont(new Font("Arial", Font.BOLD,16));
        play_again.setBackground(new Color(30, 144, 254));
        play_again.setForeground(Color.WHITE);

        JButton Exit = new JButton("Exit");
        Exit.setBounds(500,270,120,40);
        Exit.setFont(new Font("Arial", Font.BOLD,16));
        Exit.setBackground(new Color(30, 144, 254));
        Exit.setForeground(Color.WHITE);

        play_again.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                score_frame.dispose();
                new Login();
            }
        });

        Exit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                score_frame.dispose();
            }
        });



        score_frame.add(Exit);
        score_frame.add(play_again);
        score_frame.add(score_l);
        score_frame.add(heading);
        score_frame.add(image);
        score_frame.setBounds(300,90,750,550);
        score_frame.getContentPane().setBackground(Color.WHITE);
        score_frame.setLayout(null);
        score_frame.setVisible(true);
    }

    public static void main(String[] args) {
        new score_frame("Ali", 5);
    }
}
