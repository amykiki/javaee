package Test.Gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Amysue on 2016/1/18.
 */
public class LoginForm extends JFrame{
    private JPanel jp1, jp2, jp3;
    private JButton jb1, jb2;
    private JLabel jl1, jl2;
    private JTextField jtf;
    private JPasswordField jpf;
    private int width, height;
    private int xSize, ySize;

    public LoginForm(int x, int y) {
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        width = dim.width;
        height = dim.height;
        xSize = x;
        ySize =y;
        jp1 = new JPanel();
        jp2 = new JPanel();
        jp3 = new JPanel();
        jb1 = new JButton("Login");
        jb2 = new JButton("Cancel");
        jl1 = new JLabel("UserName:");
        jtf = new JTextField(15);
        jl2 = new JLabel("Password:");
        jpf = new JPasswordField(15);
    }

    private void SetCentral(int x, int y) {
        int xLoc = (width -x)/2;
        int yLoc = (height - y)/2;
        this.setLocation(xLoc, yLoc);
    }
    public void TestLogin() {
        this.setTitle("Login Form");
        this.setSize(xSize,ySize);
        this.SetCentral(xSize, ySize);
        this.setLayout(new GridLayout(3, 1, 20, 10));
        jp1.add(jl1);
        jp1.add(jtf);
        jp2.add(jl2);
        jp2.add(jpf);
        jp3.add(jb1);
        jp3.add(jb2);
        this.add(jp1);
        this.add(jp2);
        this.add(jp3);
        MyBtnListener btnListen = new MyBtnListener();
        jb1.addActionListener(btnListen);
        jb2.addActionListener(btnListen);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    private class MyBtnListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Object o = e.getSource();
            if (jb1.equals(o)) {
                System.out.println("Login");
                System.out.println("Username: "+ jtf.getText());
                System.out.println("Password: " + new String(jpf.getPassword()));
            } else if (jb2.equals(o)) {
                System.out.println("Cancel");
                jtf.setText("");
                jpf.setText("");
            }
        }
    }


}
