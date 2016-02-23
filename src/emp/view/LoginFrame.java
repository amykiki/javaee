package emp.view;

import emp.dao.DaoFactory;
import emp.dao.IUserDao;
import emp.dao.UserDao;
import emp.model.EmpException;
import emp.model.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Amysue on 2016/2/19.
 */
public class LoginFrame extends JFrame {
    private JLabel jlb1, jlb2;
    private JTextField     jtName;
    private JPasswordField jpf;
    private JButton        jb1, jb2;
    private JPanel jp1, jp2, jp3;

    public LoginFrame() throws HeadlessException {
        this.setLayout(new FlowLayout());

        this.setTitle("Login");
        this.setSize(300, 180);
        this.setResizable(false);
        this.setLocation(800, 500);
        jlb1 = new JLabel("User Name:");
        jlb2 = new JLabel("User Pwd:  ");
        jtName = new JTextField(15);
        jpf = new JPasswordField(15);
        jb1 = new JButton("Login");
        jb2 = new JButton("Reset");

        jp1 = new JPanel();
        jp1.add(jlb1);
        jp1.add(jtName);

        jp2 = new JPanel();
        jp2.add(jlb2);
        jp2.add(jpf);

        jp3 = new JPanel();
        jp3.add(jb1);
        jp3.add(jb2);

        this.add(jp1);
        this.add(jp2);
        this.add(jp3);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

        BtnListen btnlisten = new BtnListen();
        jb1.addActionListener(btnlisten);
        jb2.addActionListener(btnlisten);
    }

    private void LoginManager() {
        String   name     = jtName.getText().trim();
        String   inputPwd = String.valueOf(jpf.getPassword());
        IUserDao ud       = DaoFactory.getUserDao();
        try {
            if (ud.login(name, inputPwd)) {
                this.setVisible(false);
                ManagerFrame mf = new ManagerFrame(ud.load(name), 900, 700);
                mf.start();
            }
        } catch (EmpException e) {
            ManagerFrame.showMsg(this, e.getMessage());
        }
    }

    private class BtnListen implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == jb1) {
                LoginManager();
            } else if (e.getSource() == jb2) {
                jtName.setText("");
                jpf.setText("");
            }
        }
    }
}

