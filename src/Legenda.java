import javax.swing.*;
import java.awt.*;

class Legenda extends JPanel{
    public void paint(Graphics g){
        g.setColor(Color.BLUE);
        g.fillOval(10,0,20,20);
        g.drawString("- CIAŁO 1",35 , 15);

        g.setColor(Color.red);
        g.fillOval(140,0,20,20);
        g.drawString("- CIAŁO 2",165 , 15);

        g.setColor(Color.green);
        g.fillOval(270, 0,20,20);
        g.drawString("- CIAŁO 3",300 , 15);
    }
}