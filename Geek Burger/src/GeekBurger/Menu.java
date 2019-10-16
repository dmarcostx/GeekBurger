package GeekBurger;

import javax.swing.*;
import java.util.List;

class Menu {
    private JPanel panel;
    private JButton Product1;
    private JButton Product2;
    private JButton Product3;
    private JButton Product4;
    private JLabel label;

    Menu(List<? extends Product> menu) {
        final JButton[] buttons = new JButton[4];
        buttons[0] = Product1;
        buttons[1] = Product2;
        buttons[2] = Product3;
        buttons[3] = Product4;
        for (int i = 0; i < 4; i++) {
            if(i >= menu.size()) {
                buttons[i].setText("Out of stock");
                buttons[i].setEnabled(false);
            }
            else
                buttons[i].setText(menu.get(i).name);
        }
        Product1.addActionListener(Totem::product);
        Product2.addActionListener(Totem::product);
        Product3.addActionListener(Totem::product);
        Product4.addActionListener(Totem::product);
    }

    JPanel getPanel() {
        return panel;
    }

    JLabel getLabel() {
        return label;
    }
}
