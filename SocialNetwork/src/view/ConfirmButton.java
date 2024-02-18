package view;

import javax.swing.*;
import java.awt.*;

/**
 * @author humeishan
 * @version 1.0
 */
public class ConfirmButton extends JButton {
    ConfirmButton(String text){
        super(text);
        this.setPreferredSize(new Dimension(80, 30));
        this.setBackground(new Color(52, 152, 219));
        this.setForeground(Color.WHITE);
        this.setFocusPainted(false);
        this.setFont(new Font("黑体", Font.BOLD, 14));
    }
}
