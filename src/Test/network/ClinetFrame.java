package Test.network;

import sun.reflect.generics.tree.VoidDescriptor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketOption;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Created by Amysue on 2016/1/26.
 */
public class ClinetFrame extends JFrame {
    private String        user;
    private JButton       jbt;
    private JTextField    jtf;
    private JPanel        jp;
    private JTextArea     jta;
    private JScrollPane   jsp1;
    private JScrollPane   jsp2;
    private JList<String> jlist;
    private Font          font;

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

        jlist = new JList<>(new String[]{"All", "Amy", "Kevin"});
        jlist.setFixedCellWidth(60);
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
                    jta.setText(jta.getText() + sMsg + "\n");
                }

            } catch (NoSuchElementException e) {
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
        if (msg == null || msg.trim().equals("")) {
//            JOptionPane.showMessageDialog(null, "Please input the msg");
            return;
        }
        out.println(msg);
//        jta.setText(jta.getText() + user + ": " + msg + "\n");
        jtf.selectAll();
//        jtf.setText("");
    }
}
