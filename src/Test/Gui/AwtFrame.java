package Test.Gui;

import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

/**
 * Created by Amysue on 2016/1/17.
 */
public class AwtFrame extends Frame{

    public AwtFrame() throws HeadlessException {
        this.setTitle("Hello World");
        this.setSize(800, 600);
        this.setLocation(800, 500);
    }

    public void showFrame() {
        this.addWindowListener(new WinListen());
        this.setVisible(true);
    }

    private class WinListen implements WindowListener {
        @Override
        public void windowOpened(WindowEvent e) {
            System.out.println("win is opened");
        }

        @Override
        public void windowClosing(WindowEvent e) {
            System.out.println("Win is closing");
            System.exit(0);
        }

        @Override
        public void windowClosed(WindowEvent e) {
            System.out.println("Win is close");
        }

        @Override
        public void windowIconified(WindowEvent e) {
            System.out.println("Win is windowIconified");
        }

        @Override
        public void windowDeiconified(WindowEvent e) {
            System.out.println("Win is windowDeiconified");

        }

        @Override
        public void windowActivated(WindowEvent e) {
            System.out.println("Win is Activied");

        }

        @Override
        public void windowDeactivated(WindowEvent e) {
            System.out.println("Win is Deactivied");

        }
    }

}
