package emp.view;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;

/**
 * Created by Amysue on 2016/2/2.
 */
public class ManagerFrame extends JFrame {
    private JMenu jm1, jm2, jm3;
    private JMenuItem jmit1, jmit2, jmit3;
    private JMenuBar jmb;
    private int width, height;
    private Dimension dim;
    private Font font;
    private JPanel contentPanel, dp, ep, up;
    public ManagerFrame(int width, int height) throws HeadlessException {
        this.setTitle("信息管理系统");
        this.width = width;
        this.height = height;
        font = new Font("黑体", Font.PLAIN, 20);
        setUIFont();
        itemActionListener itemListen = new itemActionListener();

        jm1 = new JMenu("部门管理");
        jm2 = new JMenu("雇员管理");
        jm3 = new JMenu("用户管理");

        jmit1 = new JMenuItem("查询部门信息");
        jmit1.addActionListener(itemListen);
        jmit2 = new JMenuItem("查询雇员信息");
        jmit2.addActionListener(itemListen);
        jmit3 = new JMenuItem("查询员工信息");
        jmit3.addActionListener(itemListen);

        jm1.add(jmit1);
        jm2.add(jmit2);
        jm3.add(jmit3);
        jmb = new JMenuBar();
        jmb.add(jm1);
        jmb.add(jm2);
        jmb.add(jm3);
        this.add(jmb, BorderLayout.NORTH);

        contentPanel = new JPanel();
        dp = new depPanel();
        ep = new empPanel();
        up = new UserPanel();
        this.add(contentPanel, BorderLayout.CENTER);

    }

    public void start() {
        dim = Toolkit.getDefaultToolkit().getScreenSize();
        setCenter();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public void setCenter() {
        int xLoc = (dim.width - width)/2;
        int yLoc = (dim.height - height)/2;
        this.setLocation(xLoc, yLoc);
        this.setSize(width, height);
    }

    public void setUIFont() {
        FontUIResource f = new FontUIResource(font);
        Enumeration keys = UIManager.getDefaults().keys();
        while (keys.hasMoreElements()) {
            Object key = keys.nextElement();
            Object value = UIManager.get(key);
            if (value != null && value instanceof FontUIResource) {
                UIManager.put(key, f);
            }
        }
    }

    private class itemActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JPanel panel = null;
            if (e.getSource() == jmit1) {
                panel = dp;
            }
            else if (e.getSource() == jmit2) {
                panel = ep;
            }
            else if (e.getSource() == jmit3) {
                panel = up;
            }
            contentPanel.removeAll();
            contentPanel.add(panel);
            contentPanel.revalidate();
            contentPanel.repaint();
        }
    }

    protected static void showMsg(Component cp, String msg) {
        JOptionPane.showMessageDialog(cp, msg, "Error", JOptionPane.ERROR_MESSAGE);
    }

    protected static int showConfim(Component cp, String msg) {
        return JOptionPane.showConfirmDialog(cp, msg, "Confirm", JOptionPane.YES_NO_OPTION);
    }

}
