package view;

import service.Finder;
import service.Reader;
import struct.Graph;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainApp extends JFrame {
    private JPanel sidebar;

    private JSplitPane splitPane;
    private JToggleButton toggleButton;


    public MainApp() {
        setTitle("社交网络图");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1920, 1080);


        Reader reader=new Reader();
        Finder finder=new Finder(reader);
        Graph g;

        sidebar = new JPanel();
        sidebar.setBackground(Color.WHITE);
        sidebar.setPreferredSize(new Dimension(200, getHeight()));





        toggleButton = new JToggleButton("收起");
        toggleButton=setButton(toggleButton);
        toggleButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (toggleButton.isSelected()) {
                    splitPane.setDividerLocation(0); // 收起侧边栏
                    sidebar.setVisible(false); // 隐藏侧边栏

                } else {
                    splitPane.setDividerLocation(400); // 展开侧边栏
                    sidebar.setVisible(true); // 显示侧边栏
                }
            }
        });

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout());
        topPanel.add(toggleButton, BorderLayout.WEST);

        JPanel menu=new JPanel();
        FuctionButton FindPath=new FuctionButton("联系路径");
        FindPath.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                splitPane.setDividerLocation(400); // 展开侧边栏
                splitPane.setLeftComponent(new FindPathPanel(finder) );
                splitPane.setRightComponent(new JPanel());
            }
        });
        FuctionButton FindPeople=new FuctionButton("附近的人");
        FindPeople.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                splitPane.setLeftComponent(new FindPeoplePanel(finder));
                splitPane.setRightComponent(new JPanel());
            }
        });
        FuctionButton circle=new FuctionButton("交往圈子");
        circle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                splitPane.setLeftComponent(new SocialCirclePanel(finder));
            }
        });
        FuctionButton OneMiddle=new FuctionButton("一次中间人能联络的人");
        OneMiddle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                splitPane.setLeftComponent(new OneMidPanel(finder));
                splitPane.setRightComponent(new JPanel());
            }
        });
        OneMiddle.setPreferredSize(new Dimension(200, 40));
        FuctionButton PeopleAnalys=new FuctionButton("社交分析");
       PeopleAnalys.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                splitPane.setLeftComponent(new PeopleAnalysPanel(finder));
                splitPane.setDividerLocation(400);
                splitPane.setRightComponent(new JPanel());

            }
        });
        FuctionButton Exchange=new FuctionButton("查找和修改坐标");
        Exchange.setPreferredSize(new Dimension(160, 40));
        Exchange.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                splitPane.setLeftComponent(new ExchangePanel(finder));
                splitPane.setRightComponent(new JPanel());

            }
        });
        menu.add(FindPath);
        menu.add(FindPeople);
        menu.add(OneMiddle);
        menu.add(PeopleAnalys);
        menu.add(circle);
        menu.add(Exchange);

        topPanel.add(menu,BorderLayout.CENTER);

        splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, sidebar, new JPanel());
        splitPane.setDividerLocation(200); // 设置分割位置
        splitPane.setDividerSize(4);


        getContentPane().add(topPanel, BorderLayout.NORTH);
        getContentPane().add(splitPane, BorderLayout.CENTER);

        setVisible(true);
    }

    static private JToggleButton setButton(JToggleButton toggleButton) {
        toggleButton.setFont(new Font("黑体", Font.BOLD, 16));
        toggleButton.setBackground(new Color(250,250,210));
        toggleButton.setForeground(Color.BLACK);
        toggleButton.setBorder(BorderFactory.createLineBorder(new Color(106,139,139)));
        toggleButton.setPreferredSize(new Dimension(50, 25));
        return toggleButton;

    }

    public static void main(String[] args) {

            new MainApp();

    }
     class SocialCirclePanel extends JPanel{
        SocialCirclePanel(Finder finder) {
            super();


            //this.setLayout(new BorderLayout());


            JTextField field = new JTextField(10);

            field.setPreferredSize(new Dimension(100, 40));


            JButton button = new ConfirmButton("确认");



            ActionListener listener = e -> {
                String name = field.getText();
                if (finder.contains(name)){
                    splitPane.setRightComponent(new GraphPanel(name));

                }








            };

            
            button.addActionListener(listener);

            
            this.add(field);
            this.add(button);


        }

    }
}
