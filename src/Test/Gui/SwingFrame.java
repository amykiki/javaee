package Test.Gui;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Amysue on 2016/1/17.
 */
public class SwingFrame extends JFrame {
    private JButton jb1, jb2, jb3, jb4, jb5;
    private JPanel jp1, jp2;

    public SwingFrame() throws HeadlessException {
        this.setSize(800, 600);
        this.setLocation(800, 500);
        this.setBackground(Color.BLACK);
        this.addButton();
    }

    public void showPane() {
        this.setTitle("Hello JFrame");
        this.showFrame();
    }

    public void addButton() {
        jb1 = new JButton("Upward");
        jb2 = new JButton("Downward");
        jb3 = new JButton("Right");
        jb4 = new JButton("Left");
        jb5 = new JButton("Center");
    }

    public void TestBorderLayout() {
        this.setTitle("Button Test");
        this.setLayout(new BorderLayout(10, 5));
        this.add(jb1, BorderLayout.NORTH);
        this.add(jb2, BorderLayout.SOUTH);
        this.add(jb3, BorderLayout.EAST);
        this.add(jb4, BorderLayout.WEST);
        this.add(jb5, BorderLayout.CENTER);
        this.showFrame();
    }
    public void TestFlowLayout() {
        this.setTitle("FlowLayout Test");
        this.setLayout(new FlowLayout(FlowLayout.LEADING, 100, 100));
        this.add(jb1);
        this.add(jb2);
        this.add(jb3);
        this.add(jb4);
        this.add(jb5);
        this.showFrame();
    }

    public void TestGridLayout() {
        this.setTitle("GridLayout Test");
        this.setLayout(new GridLayout(2,3, 30, 20));
        this.add(jb1);
        this.add(jb2);
        this.add(jb3);
        this.add(jb4);
        this.add(jb5);
        this.showFrame();
    }
    public void showFrame() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public void TestPanel() {
        this.setTitle("JPanel Test");
        jp1 = new JPanel();
        jp2 = new JPanel();
        jp1.add(jb1);
        jp1.add(jb2);
        jp2.add(jb3);
        jp2.add(jb4);
        this.add(jp1, BorderLayout.NORTH);
        this.add(jp2, BorderLayout.SOUTH);
        this.add(jb5, BorderLayout.CENTER);
        this.showFrame();

    }

}
