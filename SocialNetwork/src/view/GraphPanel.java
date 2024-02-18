package view;

import service.Reader;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

import javax.swing.*;

public class GraphPanel extends JPanel {
    private static final int NODE_SIZE = 50;

    private Node[] nodes;
    private int selectedNodeIndex;
    private int mouseX, mouseY;
    private Node center;


    public GraphPanel(String name) {
        Reader reader=new Reader();
        Set<String> set=new HashSet<>();
        int k=reader.total.indexOf(name);
        for (int i = 0; i < 115; i++) {
            if(reader.adj[k][i]!=0){
                set.add(reader.total.get(i));
            }
        }
        for (int i = 0; i < 115; i++) {
            if(reader.adj[i][k]!=0){
                set.add(reader.total.get(i));
            }
        }
        ArrayList<String> names=new ArrayList<>(set);


        nodes = new Node[set.size()];

        for (int i = 0; i <set.size() ; i++) {
            Random random=new Random();
            int x=random.nextInt(770);
            int y=random.nextInt(570);
            nodes[i]=new Node(names.get(i),x,y);
        }

         center=new Node(name,450,250);

        selectedNodeIndex = -1;

        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                mouseX = e.getX();
                mouseY = e.getY();
                selectedNodeIndex = getSelectedNodeIndex(mouseX, mouseY);
                repaint();
            }

            public void mouseReleased(MouseEvent e) {
                selectedNodeIndex = -1;
                repaint();
            }
        });

        addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) {
                if (selectedNodeIndex != -1) {
                    int dx = e.getX() - mouseX;
                    int dy = e.getY() - mouseY;
                    Node selectedNode = nodes[selectedNodeIndex];
                    selectedNode.move(dx, dy);
                    selectedNode.setDragging(true);
                    mouseX += dx;
                    mouseY += dy;
                    repaint();
                }
            }
        });
    }

    private int getSelectedNodeIndex(int x, int y) {
        for (int i = 0; i < nodes.length; i++) {
            if (nodes[i].contains(x, y)) {
                return i;
            }
        }
        return -1;
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        for (Node node : nodes) {
            node.draw(g2d);

        }
        center.draw(g2d);
        g2d.setColor(Color.LIGHT_GRAY);
        for (int i = 0; i < nodes.length; i++) {
            g2d.drawLine(nodes[i].getX(), nodes[i].getY(),center.getX(), center.getY());
        }
        center.draw(g2d);
        for (Node node : nodes) {
            node.draw(g2d);

        }



        /*if (selectedNodeIndex != -1) {
            g2d.setColor(Color.BLACK);
            Node selectedNode = nodes[selectedNodeIndex];
            for (Node node : nodes) {
                if (node != selectedNode) {
                    if (!node.isDragging() && !selectedNode.isDragging()) {
                        g2d.drawLine(selectedNode.getX(), selectedNode.getY(), node.getX(), node.getY());
                    }
                }
            }
        }*/
    }

    private class Node {
        private String name;
        private int x, y;
        private boolean dragging;

        public Node(String name, int x, int y) {
            this.name = name;
            this.x = x;
            this.y = y;
            this.dragging = false;
        }

        public void move(int dx, int dy) {
            x += dx;
            y += dy;
        }

        public boolean contains(int x, int y) {
            return x >= this.x && x <= this.x + NODE_SIZE && y >= this.y && y <= this.y + NODE_SIZE;
        }

        public void draw(Graphics2D g2d) {
            Color color=new Color(193, 255 ,193);
            g2d.setColor(color);
            g2d.fillRect(x, y, NODE_SIZE, NODE_SIZE);
            g2d.setColor(color);
            g2d.drawRect(x, y, NODE_SIZE, NODE_SIZE);
            g2d.setColor(Color.BLACK);
            Font font = new Font("Arial", Font.BOLD, 20);
            g2d.setFont(font);
            g2d.drawString(name, x + NODE_SIZE / 2 - g2d.getFontMetrics().stringWidth(name) / 2, y + NODE_SIZE / 2);
        }

        public int getX() {
            return x + NODE_SIZE / 2;
        }

        public int getY() {
            return y + NODE_SIZE / 2;
        }

        public boolean isDragging() {
            return dragging;
        }

        public void setDragging(boolean dragging) {
            this.dragging = dragging;
        }
    }
}