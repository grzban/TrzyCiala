import javax.swing.*;
import java.awt.*;

/**
 * Created by gb on 2016-10-02.
 */

class Legenda extends JPanel{
    public void paint(Graphics g){
        g.setColor(Color.BLUE);
        g.fillOval(10,0,20,20);
        g.drawString("- CIA�O 1",35 , 15);

        g.setColor(Color.red);
        g.fillOval(140,0,20,20);
        g.drawString("- CIA�O 2",165 , 15);

        g.setColor(Color.green);
        g.fillOval(270, 0,20,20);
        g.drawString("- CIA�O 3",300 , 15);
    }
}