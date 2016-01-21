package Test.MultilThread;

import Test.Gui.CenterLoc;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Amysue on 2016/1/20.
 */
public class MoveBall extends JFrame implements Runnable {
    private int width, height;
    private CenterLoc cloc;
    private MyPanel   mp;
    private JButton   jb1;
    private JButton   jb2;
    private JButton   jb3;
    private JButton   jb4;
    private boolean   flag;
    private boolean   dir;

    public MoveBall(int width, int height) throws HeadlessException {
        this.flag = true;
        this.dir = true;
        this.width = width;
        this.height = height;
        this.setTitle("Move Ball");
        cloc = new CenterLoc();
        cloc.setCenter(this, width, height);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mp = new MyPanel(0, 30, 15);
        this.add(mp);
        jb1 = new JButton("Move the Ball");
        jb2 = new JButton("Stop the Ball");
        jb3 = new JButton("Back the Ball");
        jb4 = new JButton("Forward the Ball");
        this.add(jb1, BorderLayout.NORTH);
        this.add(jb2, BorderLayout.SOUTH);
        this.add(jb3, BorderLayout.WEST);
        this.add(jb4, BorderLayout.EAST);
        this.setVisible(true);
        this.setResizable(false);
        jb1.addActionListener(new myBtnListener());
        jb2.addActionListener(new myBtnListener());
        jb3.addActionListener(new myBtnListener());
        jb4.addActionListener(new myBtnListener());
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 10000; i++) {
                if (!this.flag) {
                    this.flag = true;
                    break;
                }
                if (MoveBall.this.dir == true) {
                    mp.x += 10;
                } else {
                    mp.x -= 10;
                }
//                if ((mp.x + 2 * mp.r) > MoveBall.this.width) return;
                Thread.sleep(100);
                mp.repaint();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void stop() {
        this.flag = false;
    }

    private class myBtnListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == jb1) {
                Thread td = new Thread(MoveBall.this);
                MoveBall.this.dir = true;
                td.start();
            } else if (e.getSource() == jb2) {
                MoveBall.this.stop();
            } else if (e.getSource() == jb3) {
                MoveBall.this.dir = false;
            } else if (e.getSource() == jb4) {
                MoveBall.this.dir = true;
            }
        }
    }

    private class MyPanel extends JPanel {
        private int x, y, r;

        public MyPanel(int x, int y, int r) {
            this.x = x;
            this.y = y;
            this.r = r;
        }

        @Override
        public void paint(Graphics g) {
            super.paint(g);
            g.setColor(Color.DARK_GRAY);
            g.fillOval(x, y, 2 * r, 2 * r);

        }
    }

}
