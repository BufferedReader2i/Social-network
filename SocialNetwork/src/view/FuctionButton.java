package view;

import javax.swing.*;
import java.awt.*;

/**
 * @author humeishan
 * @version 1.0
 */
public class FuctionButton extends JButton{
    FuctionButton(String text){
        super(text);
        this.setPreferredSize(new Dimension(120, 40));
        this.setBackground(Color.WHITE);
        this.setForeground(Color.BLACK);
        this.setFont(new Font("黑体", Font.BOLD, 16));
        this.setFocusPainted(false);
        this.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

    }
}
