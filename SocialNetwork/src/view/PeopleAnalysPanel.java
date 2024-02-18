package view;

import service.Finder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public class PeopleAnalysPanel extends JPanel{
    JTextArea show;
    public PeopleAnalysPanel(Finder finder) {

            super();


            this.setLayout(new BorderLayout());


            JTextField field = new JTextField(10);

            field.setPreferredSize(new Dimension(100, 40));




        JButton change = new ConfirmButton("设置阈值");
        change.setPreferredSize(new Dimension(100, 30));
            JButton button = new ConfirmButton("分析");

        change.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String set=field.getText();
                finder.setStandard(Integer.parseInt(set));


            }
        });
             show=new JTextArea();
        JScrollPane scrollPane = new JScrollPane(show);
            JPanel top=new JPanel();
        show.setFont(new Font("微软雅黑", Font.PLAIN, 14));



            ActionListener listener = e -> {


                    String s=finder.corePerson();
                    System.out.println(s);



                    show.setText(s);










            };


            button.addActionListener(listener);


            top.add(field);
            top.add(change);

            top.add(button);
            this.add(top,BorderLayout.NORTH);
            this.add(scrollPane,BorderLayout.CENTER);


        }

    }
