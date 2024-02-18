package view;

import service.Finder;
import service.Reader;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionListener;
import java.util.*;

/**
 * @author humeishan
 * @version 1.0
 */
public class FindPathPanel extends JPanel {
    String s=null;
    public FindPathPanel(Finder finder) {
        super();


        this.setLayout(new BorderLayout());


        //  JTextField 输入框
        JTextField startField = new JTextField(10);
        JLabel to=new JLabel("——");
        JTextField endField = new JTextField(10);
        startField.setPreferredSize(new Dimension(100, 40));
        endField.setPreferredSize(new Dimension(100, 40));


        JButton button = new ConfirmButton("确认");
        JPanel top=new JPanel();
        JTextArea show=new JTextArea();
        JScrollPane scrollPane = new JScrollPane(show);



        ActionListener listener = e -> {

            String start = startField.getText();
            String end = endField.getText();


            if (finder.contains(start)&&finder.contains(end)){
                s="路径有：\n";


                ArrayList<ArrayList<String>> allpaths=finder.AllPaths(start,end);

                for (ArrayList<String> path:allpaths){
                    s+=start+"--";
                    for (String node:path){
                        s+=node;
                        s+="--";
                    }
                    s+=")";

                    s+="\n";
                }

                show.setText(s);
                int min=finder.MinPeople(start,end);



                JOptionPane.showMessageDialog((JFrame) SwingUtilities.getWindowAncestor(button.getParent()), "最少经过"+min+"人构成联系\n最短路径为：\n"+finder.s, "最少经过人数", JOptionPane.PLAIN_MESSAGE);


            }else {
                JOptionPane.showMessageDialog((JFrame) SwingUtilities.getWindowAncestor(button.getParent()), "该人物不存在", "查找结果", JOptionPane.PLAIN_MESSAGE);
            }

        };


        button.addActionListener(listener);

        // 将输入框和按钮添加到面板中
        top.add(startField);
        top.add(to);
        top.add(endField);
        top.add(button);
        this.add(top,BorderLayout.NORTH);

        this.add(scrollPane,BorderLayout.CENTER);


    }
}
