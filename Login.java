import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale.Category;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Login {

    JButton rules, back, categoryButton;
    JTextField tfname, numMCQs;
    JComboBox categoryField;

    Login() {

        JFrame login_frame = new JFrame();
        
        //adding image on left side of frame
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("login.jpeg"));
        JLabel image = new JLabel(i1);
        image.setBounds(0, 0, 600, 500);
        login_frame.add(image);

        //adding container types items
        JLabel heading = new JLabel("Nust Entry Test");
        heading.setBounds(750, 60, 800, 45);
        heading.setFont(new Font("Viner Hand ITC", Font.BOLD, 40));
        heading.setForeground(new Color(30, 144, 254));
        login_frame.add(heading);
        
        JLabel name = new JLabel("Enter your name");
        name.setBounds(810, 150, 300, 20);
        name.setFont(new Font("Mongolian Baiti", Font.BOLD, 18));
        name.setForeground(new Color(30, 144, 254));
        login_frame.add(name);
        
        tfname = new JTextField();
        tfname.setBounds(735, 180, 300, 25);
        tfname.setFont(new Font("Times New Roman", Font.BOLD, 20));
        login_frame.add(tfname);
        
        JLabel MCQs = new JLabel("Enter Number of MCQs");
        MCQs.setBounds(810, 220, 300, 20);
        MCQs.setFont(new Font("Mongolian Baiti", Font.BOLD, 18));
        MCQs.setForeground(new Color(30, 144, 254));
        login_frame.add(MCQs);
        
        numMCQs = new JTextField();
        numMCQs.setBounds(735, 250, 300, 25);
        numMCQs.setFont(new Font("Times New Roman", Font.BOLD, 20));
        login_frame.add(numMCQs);
        
        JLabel category = new JLabel("Select Category of MCQs");
        category.setBounds(810, 290, 300, 20);
        category.setFont(new Font("Mongolian Baiti", Font.BOLD, 18));
        category.setForeground(new Color(30, 144, 254));
        login_frame.add(category);
        
        categoryField = new JComboBox();
        categoryField.setBounds(735, 320, 300, 25);
        categoryField.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        categoryField.addItem("Computer Science");  // 18
        categoryField.addItem("Math");              // 19
        categoryField.addItem("General Knowledge");// 9
        categoryField.addItem("Books");             // 10
        categoryField.addItem("Sports");            // 21
        categoryField.addItem("History");           // 23
        categoryField.addItem("politics");          // 24
        categoryField.addItem("Animals");           // 27
        categoryField.addItem("Film");              //11
        categoryField.addItem("Music");             //12
        categoryField.addItem("Television");        //14
        categoryField.addItem("Science and Nature");// 17
        categoryField.addItem("Geography");         //22
        categoryField.addItem("Vehicles");          //28
        categoryField.setSelectedIndex(0);
        login_frame.add(categoryField);
        
        
        rules = new JButton("Rules");
        rules.setBounds(735, 383, 120, 25);
        rules.setBackground(new Color(30, 144, 254));
        rules.setForeground(Color.WHITE);
        rules.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == rules) {
                    
                    // fetching api index from comboBox and storing in static variable in Quiz_app_frame
                    Quiz_app_frame.category_api = category(categoryField.getSelectedIndex());;
                    
                    Boolean proceed = true;
                    
                    String name = tfname.getText();
                    String num_of_mcqs = numMCQs.getText();
                    
                    // checking if the text field of name or no of MCQs are empty and there is only integer in no_of_mcqs 
                    // if these condition are met then program will proceed to rules
                    
                    // trim().isEmpty(): Trims the text and checks if it is empty, ensuring that only whitespace input is treated as empty.
                    if(( name == null || name.trim().isEmpty() ) && ( num_of_mcqs == null || num_of_mcqs.trim().isEmpty())){
                        JOptionPane.showMessageDialog(null, "Enter your Name and No of MCQs to Continue");
                        proceed = false;
                        }
                        
                        else if (!name.matches("[a-zA-Z\\s]+")){
                            JOptionPane.showMessageDialog(null, "Enter your Correct Name to Continue");
                            proceed = false;
                            }

                            else if ( (num_of_mcqs == null || num_of_mcqs.trim().isEmpty())){
                                JOptionPane.showMessageDialog(null, "Enter your No of MCQs to Continue");
                                proceed = false;
                        }
                        else if( name == null || name.trim().isEmpty() ){
                            JOptionPane.showMessageDialog(null, "Enter your Name to Continue");
                            proceed = false;
                            }
                            else{
                                try{
                                    Integer.parseInt(num_of_mcqs);
                                    proceed =true;
                                    }
                                    catch(NumberFormatException n){
                                        JOptionPane.showMessageDialog(null , "Enter only Number in No of MCQs to Continue");
                                        proceed = false;
                                        }
                                        }
                                        
                                        if(proceed == true){
                                            System.out.println("The selected category index is:" +categoryField.getSelectedIndex());
                                            Quiz_app_frame.username_api = name;
                            Quiz_app_frame.no_of_mcqs_api = Integer.parseInt(numMCQs.getText());
                            login_frame.dispose();
                            new Rules(name);
                        }
                }
                }
                });
                login_frame.add(rules);
                
                back = new JButton("Exit");
                back.setBounds(915, 383, 120, 25);
                back.setBackground(new Color(30, 144, 254));
                back.setForeground(Color.WHITE);
                back.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                if (e.getSource() == back) {
                    login_frame.dispose();
                    }
                    }
                    });
                    login_frame.add(back);
                    

        login_frame.getContentPane().setBackground(Color.WHITE);
        login_frame.setLayout(null);
        login_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        login_frame.setSize(1200, 500);
        login_frame.setLocation(75, 55);
        login_frame.setVisible(true);
        }
        
    //checking category of selected subject and returning corresponding api category index 
    public int category(int index){

        if(index == 0){
            return 18;
        }
        
        else if(index == 1){
            return 19;
        }

        else if(index == 2){
            return 9;
        }

        else if(index == 3){
            return 10;
        }

        else if(index == 4){
            return 21;
        }

        else if(index == 5){
            return 23;
        }

        else if(index == 6){
            return 24;
        }

        else if(index == 7){
            return 27;
        }

        else if (index == 8){
            return 11;
        }

        else if(index == 9){
            return 12;
        }

        else if (index == 10){
            return 14;
        }

        else if (index == 11){
            return 17;
        }

        else if (index == 12){
            return 22;
        }

        else if (index == 13){
            return 28;
        }

        return 18;
    }


    public static void main(String[] args) {
        new Login();
    }
}