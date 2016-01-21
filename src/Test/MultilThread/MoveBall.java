package Test.MultilThread;

import Test.Gui.CenterLoc;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Amysue on 2016/1/20.
 */
public class MoveBall extends JFrame implements Runnable{
    private int width, height;
    private CenterLoc cloc;
    private MyPanel   mp;
    private JButton   jb;

    public MoveBall(int width, int height) throws HeadlessException {
        this.width = width;
        this.height = height;
        this.setTitle("Move Ball");
        cloc = new CenterLoc();
        cloc.setCenter(this, width, height);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mp = new MyPanel(0, 30, 15);
        this.add(mp);
        jb = new JButton("Move the Ball");
        this.add(jb, BorderLayout.NORTH);
        this.setVisible(true);
        this.setResizable(false);
        jb.addActionListener(new myBtnListener());

    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 50; i++) {
                mp.x += 10;
                if ((mp.x + 2*mp.r) > MoveBall.this.width) return;
                Thread.sleep(100);
                mp.repaint();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private class myBtnListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == jb) {
                Thread td = new Thread(MoveBall.this);
                td.start();
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
