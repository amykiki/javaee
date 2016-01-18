package Test.Gui;

import java.awt.*;

/**
 * Created by Amysue on 2016/1/18.
 */
public class CenterLoc {
    private int width, height;
    private int xLoc, yLoc;
    public CenterLoc() {
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        width = dim.width;
        height = dim.height;
        System.out.println("width = " + width);
        System.out.println("height = " + height);
    }

    public void setCenter(Component cp, int x, int y) {
        xLoc = (width -x)/2;
        yLoc = (height - y)/2;

        cp.setLocation(xLoc, yLoc);
    }

    public int getxLoc() {
        System.out.println("xLoc = " + xLoc);
        return xLoc;
    }

    public int getyLoc() {
        System.out.println("yLoc = " + yLoc);
        return yLoc;
    }
}
