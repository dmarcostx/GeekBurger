package GeekBurger;

import javax.swing.JButton;
import javax.swing.JPanel;

class Welcome {
    private JButton pictureButton;
    private JPanel panel;

    Welcome() {
        pictureButton.addActionListener(Totem::picture);
    }

    JPanel getPanel() {
        return panel;
    }
}
