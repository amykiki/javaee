package Test.network;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.*;
import java.util.List;

/**
 * Created by Amysue on 2016/1/26.
 */
public class ClinetFrame extends JFrame {
    private String                   user;
    private JButton                  jbt;
    private JTextField               jtf;
    private JPanel                   jp;
    private JTextArea                jta;
    private JScrollPane              jsp1;
    private JScrollPane              jsp2;
    private JList<String>            jlist;
    private DefaultListModel<String> userList;
    private Font                     font;

    private Socket      sClinet;
    private Scanner     in;
    private PrintWriter out;

    private boolean shutdown = false;

    public static void main(String[] args) {
        new ClinetFrame("Amy");
    }

    public ClinetFrame(String user) throws HeadlessException {
        this.user = user;
        this.setTitle("Network Chat[" + this.user + "]");
        this.setLocation(500, 500);
        this.setSize(700, 600);
        this.addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
                out.println(Server.DISCONNECT_TOKEN);
                close();
            }
        });
        font = new Font("Arial", Font.PLAIN, 20);

        jbt = new JButton("Send");
        jtf = new JTextField(30);
        jp = new JPanel();
        jp.add(jtf);
        jp.add(jbt);
        jtf.setFont(font);
        jbt.setFont(font);
        this.add(jp, BorderLayout.SOUTH);
        jbt.addActionListener(new BtnClick());
        jtf.addKeyListener(new KeyClick());

        jta = new JTextArea();
        jsp1 = new JScrollPane(jta);

        jta.setFont(font);
        this.add(jsp1, BorderLayout.CENTER);

        userList = new DefaultListModel<>();
        jlist = new JList<>(userList);
        jlist.setFixedCellWidth(100);
        jlist.setFont(font);
        jsp2 = new JScrollPane(jlist);
        this.add(jsp2, BorderLayout.WEST);

        connect();
        new Thread(new listenMsg()).start();
        this.setVisible(true);
    }

    private void connect() {
        try {
            sClinet = new Socket(InetAddress.getLocalHost(), Server.PORT);
            in = new Scanner(sClinet.getInputStream());
            out = new PrintWriter(sClinet.getOutputStream(), true);
            out.println(user);
            userList.addElement("All");

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private class listenMsg implements Runnable {

        @Override
        public void run() {
            String sMsg = null;
            try {
                while (!shutdown && (sMsg = in.nextLine()) != null) {
                    if (sMsg.startsWith(Server.ADD_USERS)) {
                        add_user(sMsg);

                    } else if (sMsg.startsWith(Server.DEL_USERS)) {
                        del_user(sMsg);
                    } else {
                        jta.setText(jta.getText() + sMsg + "\n");
                    }
                }

            } catch (NoSuchElementException e) {
                System.out.println("Server Exit ERROR");
                e.printStackTrace();
            } finally {
                if (sClinet != null) {
                    try {
                        sClinet.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                System.exit(0);
            }

        }
    }

    private void del_user(String sMsg) {
        handld_user(false, sMsg);
    }

    private void add_user(String sMsg) {
        handld_user(true, sMsg);
    }

    private void handld_user(boolean add, String sMsg) {
        String userMsg = sMsg.substring(sMsg.indexOf(":") + 1);
        String[] users = userMsg.split(",");
        for (String user : users) {
            if (add) {
                userList.addElement(user);
            }
            else {
                userList.removeElement(user);
            }
        }
    }
    private class BtnClick implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == jbt) {
                sendMsg();
            }

        }
    }

    private class KeyClick extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                sendMsg();
            }
        }
    }

    private void close() {
        shutdown = true;
    }

    private void sendMsg() {
        String msg = jtf.getText();
        jtf.requestFocus();
        jtf.selectAll();
//        jtf.setText("");
        if (msg == null || msg.trim().equals("")) {
//            JOptionPane.showMessageDialog(null, "Please input the msg");
            return;
        }
        List<String> users = jlist.getSelectedValuesList();
        jlist.clearSelection();
        boolean isAll = false;
        String uMsg = "";
        if (users.size() <= 0) {
            isAll = true;
        }
        else {
            StringBuilder sb = new StringBuilder();
            for (String u : users) {
                if (u.equals("All")) {
                    isAll = true;
                    break;
                }
                sb.append(u + ",");
            }
            uMsg = sb.toString();
        }
        if (isAll) uMsg = "All";
        out.println(Server.TO_USERS_START + uMsg + Server.TO_USERS_END + msg);
    }
}
