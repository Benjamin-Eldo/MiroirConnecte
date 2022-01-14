/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package periph;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 * Permet de dessiner sur une photo
 * @author p2026386 GRANDY Guillaume
 * @version 1.0
 */
class Gribouilleur extends JPanel 
    {
        ArrayList<Integer> point;
        private Image image;
        
        Gribouilleur(ImageIcon img) 
        {
            point=new ArrayList<>();
            image=((ImageIcon)img).getImage();
            setPreferredSize(new Dimension(480,780));
            addMouseListener(new Appuyeur());
            addMouseMotionListener(new Dragueur());
            setOpaque(false);
        }
        
        public void paintComponent(Graphics g) 
        {
            g.setColor(Color.red);
           g.drawImage(image, 0, 0, 480, 780, this);
            Graphics2D g2 = (Graphics2D) g;
            g2.setStroke(new BasicStroke(3));
            
            if(point.size()>3)
            {
                for(int i=1;i<point.size()/2-1;i++)
                {
                   g2.drawLine(point.get(i*2-2), point.get(i*2-1), point.get(i*2), point.get(i*2+1));
                }
            }
        }

    public ArrayList<Integer> getPoint() {
        return point;
    }
        
        
        
class Appuyeur extends MouseAdapter
        {
        public void mousePressed(MouseEvent e) {
            if(point.size()<2000)
            {
            point.add(e.getX());
            point.add(e.getY());
            }
        }
        }
        
class Dragueur extends MouseMotionAdapter {
        public void mouseDragged(MouseEvent e) {
            if(point.size()<2000)
            {
            point.add(e.getX());
            point.add(e.getY());
            }
        repaint();
        }
        }
    }
