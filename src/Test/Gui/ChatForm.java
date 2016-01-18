package Test.Gui;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Amysue on 2016/1/18.
 */
public class ChatForm extends JFrame {
    private JPanel jp1;
    private JTextArea jta;
    private JTextField jtf;
    private JButton jb1;
    private JComboBox<String> jcb;
    private JScrollPane jsp;
    private JMenuBar jmb;
    private JMenu jmn1;
    private JMenu jmn2;
    private JMenu jmn3;
    private JMenuItem jmi1;
    private JMenuItem jmi2;
    private JMenuItem jmi3;
    private JMenuItem jmi4;
    private JMenuItem jmi5;
    private JMenuItem jmi6;
    private JMenuItem jmi7;
    private int width, height;
    private int xSize, ySize;

    public ChatForm(int xSize, int ySize) throws HeadlessException {
        this.xSize = xSize;
        this.ySize = ySize;
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        width = dim.width;
        height = dim.height;
        String[] strs = new String[]{"amy", "kevin", "jay", "eric"};
        jp1 = new JPanel();
        jb1 = new JButton("Send");
        jta = new JTextArea(5, 3);
        jsp = new JScrollPane(jta);
        jtf = new JTextField(15);
        jcb = new JComboBox<>(strs);
        jmb = new JMenuBar();
        jmn1 = new JMenu("File");
        jmn2 = new JMenu("Edit");
        jmn3 = new JMenu("New...");
        jmi1 = new JMenuItem("Open...");
        jmi2 = new JMenuItem("Save All");
        jmi3 = new JMenuItem("Cut");
        jmi4 = new JMenuItem("Copy");
        jmi5 = new JMenuItem("Project...");
        jmi6 = new JMenuItem("Model");
        jmi7 = new JMenuItem("Code");

    }
    private void SetCentral(int x, int y) {
        int xLoc = (width -x)/2;
        int yLoc = (height - y)/2;
        this.setLocation(xLoc, yLoc);
    }

    public void TestChat() {
        this.setTitle("Chat Form");
        this.setSize(xSize,ySize);
        this.SetCentral(xSize, ySize);
        jmn3.add(jmi5);
        jmn3.add(jmi6);
        jmn1.add(jmi1);
        jmn1.add(jmi2);
        jmn1.add(jmn3);
        jmn2.add(jmi3);
        jmn2.add(jmi4);
        jmb.add(jmn1);
        jmb.add(jmn2);
        jmb.add(jmi7);
        this.add(jmb, BorderLayout.NORTH);
        this.add(jsp, BorderLayout.CENTER);
        jp1.add(jcb);
        jp1.add(jtf);
        jp1.add(jb1);
        this.add(jp1, BorderLayout.SOUTH);
        this.setResizable(false);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
