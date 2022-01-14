
package periph;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JComponent;

/**
 * Permet de dessiner sur une photo
 * @author p2026386 GRANDY Guillaume
 * @version 1.0
 */
public class Dessin extends JComponent implements MouseListener,ActionListener{
    private ArrayList<Point> liste;
    private Image image;

    public Dessin(ImageIcon img) 
    {
        this.image=((ImageIcon)img).getImage();
        liste=new ArrayList<>();
    }
    
    
    @Override
    public Dimension getPreferredSize()
    {
        Dimension D=new Dimension(480,780);
        return D;
    }
    
    
    @Override
    public void paintComponent(Graphics g)
    {        
        
        g.drawImage(image, 0, 0, 480, 780, this);
        for(int i=0;i<liste.size()-1;i++)
        {
           g.setColor(Color.blue);
           g.drawLine(liste.get(i).x, liste.get(i).y, liste.get(i+1).x,liste.get(i+1).y);
        }
    }    

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) 
    {
       liste.add(new Point(e.getX(),e.getY()));
       repaint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }
}
