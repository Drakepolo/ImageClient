package Essentials;
import java.io.*;
import java.net.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class PlaylistButton extends JButton{
   
   private BufferedImage img;
   private JFrame frame;
   private PicturePanel panel;
   
   public PlaylistButton(BufferedImage img){
     
     this.img = img;
     this.setIcon(new ImageIcon(img));
     this.setPreferredSize(new Dimension(200,200));
     this.addActionListener(new MyListener());
     
     
   }
   
   public BufferedImage getImage(){
     
      return img;
     
     
   }
   
   private void displayFromButton(){
     
     frame = new JFrame("Display from button");
     panel = new PicturePanel(img);
     
     frame.setSize(500,500);
     frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
     frame.setVisible(true);
     
     frame.add(panel);
     
     
     
   }
   
   private class MyListener implements ActionListener{
     public void actionPerformed(ActionEvent e){
       displayFromButton();
     }
   }
   
 }
