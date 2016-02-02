package emp.view;

import javax.swing.*;

/**
 * Created by Amysue on 2016/2/2.
 */
public class empPanel extends JPanel {
    private JLabel jlb;

    public empPanel() {
        jlb = new JLabel("雇员信息");
        this.add(jlb);
    }
}
