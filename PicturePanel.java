package Essentials;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.io.ByteArrayOutputStream;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.awt.Graphics;
import java.awt.Dimension;

public class PicturePanel extends JPanel{
   
   private BufferedImage picture;
   //private File fl;
   private Dimension size = new Dimension(1000,1000);
   
   public PicturePanel(String website){
     
     try{
       URL url = new URL(website);
       picture = ImageIO.read(url);
       
     }catch(IOException ex){
       System.err.println("You messed up");
     }
     
   }
   
   public PicturePanel(File file){
     
     try{
       picture = ImageIO.read(file);
     }catch(IOException ex){
       System.err.println("You messed up");
     }
     
   }
   
   public PicturePanel(BufferedImage img){
     
     picture = img;
     
   }
   
   public PicturePanel(){
     
   }
   
   public void paint(Graphics g){
     super.paint(g);
     g.drawImage(picture,0,0,(picture.getWidth() /2 ),(picture.getHeight() / 2) , null);
   }
   
   public void replaceImage(String webpage){
     
     try{
       URL url = new URL(webpage);
       picture = ImageIO.read(url);
       repaint();
     }catch(IOException ex){
       System.err.println("You messed up");
     }
     
   }
   
   public void replaceImage(File file){
     
     try{
       picture = ImageIO.read(file);
       repaint();
     }catch(IOException ex){
       System.err.println("You messed up");
     }
     
   }
   
   public void replaceImage(BufferedImage img){
    
     picture = img;
     repaint();
     
   }
   
   public void storeImage(String fileName){
     
     try{
       BufferedImage stored = picture;
       File outputFile = new File(fileName);
       ImageIO.write(stored,"png",outputFile);
     }catch(IOException ex){
       System.out.println("storeImage messed up");
     }
     
   }
   
   public Dimension getPreferredSize(){
     return size;
   }
   
   //Seems to only return initial image, not post replaceImage 
   public BufferedImage returnImage(){
     return picture;
   }
 }
