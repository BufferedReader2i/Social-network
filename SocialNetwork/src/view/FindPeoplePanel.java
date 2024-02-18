package view;

import service.Finder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.*;

/**
 * @author humeishan
 * @version 1.0
 */
public class FindPeoplePanel extends JPanel{
    FindPeoplePanel(Finder finder) {
        super();


        this.setLayout(new BorderLayout());


        JTextField field = new JTextField(10);

        field.setPreferredSize(new Dimension(100, 40));


        JButton button = new ConfirmButton("确认");
        JTextArea show=new JTextArea();
        JPanel top=new JPanel();



        ActionListener listener = e -> {
            String name = field.getText();
            if (finder.contains(name)){
                String s="附近的有：\n";
                ArrayList<String> people=finder.nearBy(name);

                for (String person:people){
                    s+=person+"\n";
                }
                show.setText(s);

            }else {
                JOptionPane.showMessageDialog((JFrame) SwingUtilities.getWindowAncestor(button.getParent()), "该人物不存在", "查找结果", JOptionPane.PLAIN_MESSAGE);
            }








        };

        // 为按钮添加监听器
        button.addActionListener(listener);

        // 将输入框和按钮添加到面板中
        top.add(field);
        top.add(button);
        this.add(top,BorderLayout.NORTH);
        this.add(show,BorderLayout.CENTER);


    }

}
