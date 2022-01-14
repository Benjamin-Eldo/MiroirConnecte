
package miroir;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JComponent;

/**
 * Gére des JComponent
 * @author p2026386 GRANDY Guillaume
 * @version 1.1 Redéfinition de paintComponent
 */
public class Composant extends JComponent {
     private int height,width;

    public Composant( int height, int width){

        this.height=height;
        this.width=width;
        System.out.println(height);
        System.out.println(width);
    }

     @Override
    public Dimension getPreferredSize()
    {
        Dimension D=new Dimension(height,width);
        return D;
    }
    
    
     @Override
    public void paintComponent(Graphics g)
    {
        
        g.setColor(new Color(20,20,20));
        g.fillRoundRect(0, 0, width, height,30,30);
        
        g.setColor(Color.WHITE);
        g.drawRoundRect(0, 0, width-2, height-2, 30, 30);
        
    }
}
