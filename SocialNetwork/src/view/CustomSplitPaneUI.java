package view;

import javax.swing.*;
import javax.swing.plaf.basic.BasicSplitPaneDivider;
import javax.swing.plaf.basic.BasicSplitPaneUI;
import java.awt.*;

/**
 * @author humeishan
 * @version 1.0
 */
public class CustomSplitPaneUI extends BasicSplitPaneUI {
    @Override
    public BasicSplitPaneDivider createDefaultDivider() {
        return new CustomSplitPaneDivider(this);
    }
}

class CustomSplitPaneDivider extends BasicSplitPaneDivider {
    public CustomSplitPaneDivider(BasicSplitPaneUI ui) {
        super(ui);
        setBorder(BorderFactory.createLineBorder(Color.RED, 2)); // 设置分隔条的边框样式
        setBackground(Color.YELLOW); // 设置分隔条的背景颜色
    }
}
