package emp.view;

import emp.model.User;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Amysue on 2016/2/19.
 */
public class WelcomePanle extends JPanel {
    private JLabel jlb1;

    public WelcomePanle(User loginUser) {
        this.setLayout(new BorderLayout());

        jlb1 = new JLabel("Welcome " + loginUser.getNickname() + " to Harry Potter's World");
        this.add(jlb1, BorderLayout.CENTER);
    }
}
