package Test.network;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.peer.FontPeer;

/**
 * Created by Amysue on 2016/1/26.
 */
public class LoginFrame extends JFrame {
    private JLabel     jl;
    private JButton    jbt;
    private JTextField jtf;
    private Font       font;

    public static void main(String[] args) {
        new LoginFrame();
    }

    public LoginFrame() throws HeadlessException {
        this.setTitle("User Login");
        this.setLocation(600, 600);
        this.setSize(400, 100);
        this.setResizable(false);
        this.setLayout(new FlowLayout());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        font = new Font("Arial", Font.PLAIN, 18);

        jl = new JLabel("Input Name");
        jl.setFont(font);
        jbt = new JButton("Connect");
        jbt.setFont(font);
        jtf = new JTextField(10);
        jtf.setFont(font);
        this.add(jl);
        this.add(jtf);
        this.add(jbt);

        jbt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = jtf.getText();
                if (name == null || name.trim().equals("")) {
                    JOptionPane.showMessageDialog(null, "Please input the user name");
                    return;
                }
                new ClinetFrame(name);
                LoginFrame.this.setVisible(false);
            }
        });
        this.setVisible(true);

    }
}
