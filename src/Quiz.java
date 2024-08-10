import java.awt.event.*;
import java.awt.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.*;

public class Quiz implements ActionListener {

    String[] questions ={
            "Which of these nations is NOT considered part of East Africa?",
            "When did Idi Amin seize power in Uganda in a coup d'état?"
    };

    String[][] options = {
            {"Kenya", "Rwanda", "Tanzania", "Morocco"},
            {"1961", "1966", "1971", "1976"}
    };

    char [] answers = {
            'D',
            'C'
    };

    String inputName;
    String inputEmail;
    char answer;
    int index;
    int correct_guesses = 0;
    int total_questions = questions.length;
    int result;
    int seconds = 15;

    JFrame frame = new JFrame();
    JTextField textfield = new JTextField();
    JTextArea textarea = new JTextArea();
    JButton buttonA = new JButton();
    JButton buttonB = new JButton();
    JButton buttonC = new JButton();
    JButton buttonD = new JButton();

    JLabel answer_labelA = new JLabel();
    JLabel answer_labelB = new JLabel();
    JLabel answer_labelC = new JLabel();
    JLabel answer_labelD = new JLabel();
    //JLabel time_label = new JLabel();
    JLabel seconds_left = new JLabel();
    JTextField number_right = new JTextField();
    JTextField name = new JTextField();
    JLabel nameLabel = new JLabel();

    JTextField email = new JTextField();
    JLabel emailLabel = new JLabel();

    JButton submit = new JButton();

    Timer timer = new Timer(1000, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            seconds--;
            seconds_left.setText(String.valueOf(seconds));
            if(seconds <= 0){
                displayAnswer();
            }
        }
    });


    public Quiz(){
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(650, 650);
        try {
           frame.setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("background1.jpg")))));
        } catch (IOException e) {
           e.printStackTrace();
        }
        frame.setLayout(null);
        frame.setResizable(false);

        textfield.setBounds(0, 0, 650, 50);
        //textfield.setBackground(new Color(25,25,25));
        textfield.setOpaque(false);
        textfield.setForeground(new Color(77,77,77));
        textfield.setFont(new Font("Calibri", Font.BOLD, 30));
        textfield.setHorizontalAlignment(JTextField.CENTER);
        textfield.setBorder(BorderFactory.createBevelBorder(2));
        textfield.setEditable(false);

        textarea.setBounds(0, 50, 650, 50);
        textarea.setLineWrap(true);
        textarea.setWrapStyleWord(true);
        textarea.setOpaque(false);
        textarea.setForeground(new Color(77,77,77));
        textarea.setFont(new Font("Roboto", Font.BOLD, 20));
        textarea.setBorder(BorderFactory.createBevelBorder(2));
        textarea.setEditable(false);

        buttonA.setBounds(0, 100, 100, 100);
        buttonA.setFont(new Font("MV Boli", Font.BOLD, 35));
        buttonA.setFocusable(false);
        buttonA.addActionListener(this);
        buttonA.setText("A");

        buttonB.setBounds(0, 200, 100, 100);
        buttonB.setFont(new Font("MV Boli", Font.BOLD, 35));
        buttonB.setFocusable(false);
        buttonB.addActionListener(this);
        buttonB.setText("B");

        buttonC.setBounds(0, 300, 100, 100);
        buttonC.setFont(new Font("MV Boli", Font.BOLD, 35));
        buttonC.setFocusable(false);
        buttonC.addActionListener(this);
        buttonC.setText("C");

        buttonD.setBounds(0, 400, 100, 100);
        buttonD.setFont(new Font("MV Boli", Font.BOLD, 35));
        buttonD.setFocusable(false);
        buttonD.addActionListener(this);
        buttonD.setText("D");

        answer_labelA.setBounds(125,100,500,100);
        answer_labelA.setForeground(new Color(3,33,61));
        answer_labelA.setFont(new Font("Ink Free", Font.BOLD,35));

        answer_labelB.setBounds(125,200,500,100);
        answer_labelB.setForeground(new Color(3,33,61));
        answer_labelB.setFont(new Font("Ink Free", Font.BOLD,35));

        answer_labelC.setBounds(125,300,500,100);
        answer_labelC.setForeground(new Color(3,33,61));
        answer_labelC.setFont(new Font("Ink Free", Font.BOLD,35));

        answer_labelD.setBounds(125,400,500,100);
        answer_labelD.setForeground(new Color(3,33,61));
        answer_labelD.setFont(new Font("Ink Free", Font.BOLD,35));

        seconds_left.setBounds(535, 510, 100, 100);
        seconds_left.setBackground(new Color(25,25,25));
        seconds_left.setForeground(new Color(255,0,0));
        seconds_left.setFont(new Font("Ink Free", Font.BOLD,60));
        seconds_left.setBorder(BorderFactory.createBevelBorder(1));
        seconds_left.setOpaque(true);
        seconds_left.setHorizontalAlignment(JTextField.CENTER);
        seconds_left.setText(String.valueOf(seconds));

        number_right.setBounds(225, 100, 200, 200);
        number_right.setBackground(new Color(25,25,25));
        number_right.setForeground(new Color (25,255,0));
        number_right.setFont(new Font("Ink Free", Font.BOLD, 50));
        number_right.setBorder(BorderFactory.createBevelBorder(1));
        number_right.setHorizontalAlignment(JTextField.CENTER);
        number_right.setEditable(false);

        name.setBounds(250,330,250,30);
        name.setBackground(new Color(255, 255, 255));
        name.setForeground(new Color(0,0,0));
        name.setFont(new Font("MV Boli", Font.PLAIN,14));

        nameLabel.setBounds(200,325,50,50);
        nameLabel.setBackground(new Color(50, 50, 50));
        nameLabel.setForeground(new Color(77,77,77));
        nameLabel.setFont(new Font("MV Boli", Font.PLAIN,14));
        nameLabel.setText("Name");

        email.setBounds(250,380,250,30);
        email.setBackground(new Color(255, 255, 255));
        email.setForeground(new Color(0,0,0));
        email.setFont(new Font("MV Boli", Font.PLAIN,14));

        emailLabel.setBounds(200,375,50,50);
        emailLabel.setBackground(new Color(50, 50, 50));
        emailLabel.setForeground(new Color(77,77,77));
        emailLabel.setFont(new Font("MV Boli", Font.PLAIN,14));
        emailLabel.setText("Email");

        submit.setBounds(225, 425, 100, 30);
        submit.setFont(new Font("MV Boli", Font.BOLD, 14));
        submit.setFocusable(false);
        submit.addActionListener(this);
        submit.setText("Submit");

        frame.add(textfield);
        frame.add(textarea);
        frame.add(buttonA);frame.add(buttonB);frame.add(buttonC);frame.add(buttonD);
        frame.add(answer_labelA);frame.add(answer_labelB);frame.add(answer_labelC);frame.add(answer_labelD);
        frame.add(seconds_left);
        frame.setVisible(true);

        nextQuestion();
    }

    public void nextQuestion(){

        if(index >= total_questions){
            results();
        }
        else{
            textfield.setText("Question " + (index+1));
            textarea.setText(questions[index]);
            answer_labelA.setText(options[index][0]);
            answer_labelB.setText(options[index][1]);
            answer_labelC.setText(options[index][2]);
            answer_labelD.setText(options[index][3]);
            timer.start();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e){

        buttonA.setEnabled(false);
        buttonB.setEnabled(false);
        buttonC.setEnabled(false);
        buttonD.setEnabled(false);

        if(e.getSource()==buttonA){
            answer = 'A';
            displayAnswer();
            if(answer == answers[index]){
                correct_guesses++;
            }
        }

        if(e.getSource()==buttonB){
            answer = 'B';
            displayAnswer();
            if(answer == answers[index]){
                correct_guesses++;
            }
        }

        if(e.getSource()==buttonC){
            answer = 'C';
            displayAnswer();
            if(answer == answers[index]){
                correct_guesses++;
            }
        }

        if(e.getSource()==buttonD){
            answer = 'D';
            displayAnswer();
            if(answer == answers[index]){
                correct_guesses++;
            }
        }


        if (e.getSource()==submit){
            inputName = name.getText();
            inputEmail = email.getText();
            Append app = new Append();
            String[] data = {inputName, inputEmail, String.valueOf(correct_guesses)};
            app.appendData(data);
            frame.dispose();

        }
    }

    public void displayAnswer(){
        timer.stop();
        buttonA.setEnabled(false);
        buttonB.setEnabled(false);
        buttonC.setEnabled(false);
        buttonD.setEnabled(false);

        if (answers[index]== 'A'){
            answer_labelA.setForeground(new Color(55,255,0));
            answer_labelB.setForeground(new Color(255,0,0));
            answer_labelC.setForeground(new Color(255,0,0));
            answer_labelD.setForeground(new Color(255,0,0));
        }

        if (answers[index]== 'B'){
            answer_labelB.setForeground(new Color(55,255,0));
            answer_labelA.setForeground(new Color(255,0,0));
            answer_labelC.setForeground(new Color(255,0,0));
            answer_labelD.setForeground(new Color(255,0,0));
        }

        if (answers[index]== 'C'){
            answer_labelC.setForeground(new Color(55,255,0));
            answer_labelB.setForeground(new Color(255,0,0));
            answer_labelA.setForeground(new Color(255,0,0));
            answer_labelD.setForeground(new Color(255,0,0));
        }

        if (answers[index]== 'D'){
            answer_labelD.setForeground(new Color(55,255,0));
            answer_labelB.setForeground(new Color(255,0,0));
            answer_labelC.setForeground(new Color(255,0,0));
            answer_labelA.setForeground(new Color(255,0,0));
        }

        Timer pause = new Timer(1500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                answer_labelA.setForeground(new Color(3,33,61));
                answer_labelB.setForeground(new Color(3,33,61));
                answer_labelC.setForeground(new Color(3,33,61));
                answer_labelD.setForeground(new Color(3,33,61));

                answer = ' ';
                seconds = 15;
                seconds_left.setText(String.valueOf(seconds));
                buttonA.setEnabled(true);
                buttonB.setEnabled(true);
                buttonC.setEnabled(true);
                buttonD.setEnabled(true);
                index++;
                nextQuestion();
            }
        });
        pause.setRepeats(false);
        pause.start();
    }

    public void results(){
        buttonA.setEnabled(false);
        buttonB.setEnabled(false);
        buttonC.setEnabled(false);
        buttonD.setEnabled(false);

        textfield.setText("RESULT!");
        textarea.setText("");
        answer_labelA.setText("");
        answer_labelB.setText("");
        answer_labelC.setText("");
        answer_labelD.setText("");
        number_right.setText(correct_guesses+"/"+total_questions);

        frame.add(number_right);
        frame.add(name);frame.add(nameLabel);
        frame.add(email); frame.add(emailLabel);
        frame.add(submit);
    }
}
