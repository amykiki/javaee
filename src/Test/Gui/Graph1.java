package Test.Gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Created by Amysue on 2016/1/18.
 */
public class Graph1 extends JFrame{
    private CenterLoc cl;
    private MyPanel jp;
    private int width;
    private int height;
    public Graph1(int x, int y) throws HeadlessException {
        super("Graph1");
        cl = new CenterLoc();
        width = x;
        height = y;
        this.setSize(width, height);
        cl.setCenter(this, width, height);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void PaintGP() {
        jp = new MyPanel(30, 30);
        this.add(jp);
        this.addKeyListener(new MyKeyEvent());
        this.setVisible(true);
    }

    private class MyKeyEvent extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
//            super.keyPressed(e);
            if (e.getKeyCode() == KeyEvent.VK_UP) {
                jp.y -= 5;
            } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                jp.y += 5;

            } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                jp.x -= 5;
                if (jp.x <= 0) {
                    jp.x = 0;
                }
                System.out.println("jp.x = " + jp.x);
            } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                jp.x += 5;
                if (jp.x >= (Graph1.this.width - 20)) {
                    System.out.println("Graph1.this.width = " + Graph1.this.width);
                    jp.x = Graph1.this.width - 20;
                }
                System.out.println("jp.x = " + jp.x);
            }

            jp.repaint();
        }

    }
    private class MyPanel extends JPanel {
        private int x;
        private int y;

        public MyPanel(int aX, int aY) {
            this.x = aX;
            this.y = aY;
        }

        public void paint(Graphics g) {
            super.paint(g);
            g.setColor(Color.RED);
            g.fillOval(x, y, 20, 20);
        }

    }

}
