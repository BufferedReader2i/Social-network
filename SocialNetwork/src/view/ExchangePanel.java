package view;

import service.Finder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * @author humeishan
 * @version 1.0
 */
public class ExchangePanel extends JPanel{
    public ExchangePanel(Finder finder) {
        super();


        this.setLayout(new BorderLayout());


        JTextField nameField = new JTextField(10);
        JTextField startField = new JTextField(10);
        JLabel findname=new JLabel("名字:");

        JLabel X=new JLabel("X:");
        JLabel Y=new JLabel("Y:");

        JTextField endField = new JTextField(10);
        nameField.setPreferredSize(new Dimension(100, 40));
        startField.setPreferredSize(new Dimension(100, 40));
        endField.setPreferredSize(new Dimension(100, 40));


        JButton find = new ConfirmButton("查找");
        JButton button = new ConfirmButton("修改");
        JPanel top=new JPanel();
        JTextArea show=new JTextArea();
        JScrollPane scrollPane = new JScrollPane(show);
        find.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name=nameField.getText();
                if (finder.contains(name)){
                    startField.setText(""+finder.FindX(name));
                    endField.setText(""+finder.FindY(name));
                }else {
                    JOptionPane.showMessageDialog((JFrame) SwingUtilities.getWindowAncestor(button.getParent()), "该人物不存在", "查找结果", JOptionPane.PLAIN_MESSAGE);

                }
            }
        });



        ActionListener listener = e -> {

            double x = Double.parseDouble(startField.getText());
            double y = Double.parseDouble(endField.getText());


            String name=nameField.getText();
            if (finder.contains(name)){
                finder.setX(name,x);
                finder.setY(name,y);
            }
            JOptionPane.showMessageDialog((JFrame) SwingUtilities.getWindowAncestor(button.getParent()), "修改成功", "修改结果", JOptionPane.PLAIN_MESSAGE);

            };


        button.addActionListener(listener);


        top.add(findname);
        top.add(nameField);
        top.add(X);

        top.add(startField);
        top.add(Y);
        top.add(endField);
        top.add(find);

        this.add(top,BorderLayout.NORTH);
        JPanel down=new JPanel();
        down.add(button);

        this.add(down,BorderLayout.CENTER);


    }

}
