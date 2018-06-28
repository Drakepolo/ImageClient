package Essentials;
import java.io.*;
import java.net.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.util.concurrent.TimeUnit;

public class PlaylistPanelTest extends JPanel{
   
   
   //Only need to make button for every image added to PictureNodeList with thumbnail image
   //and Scroll pane
   
    private PictureNodeList list;
    private JScrollPane scroll;
    PictureNode fill = null;
    private JFrame frame; 
    private PicturePanel panel;
    private BufferedImage selectedImage;
    
    public PlaylistPanelTest(BufferedImage img){
      
      
      list = new PictureNodeList(img);
      
      
    }
    
    public PlaylistPanelTest(){
      
      list = new PictureNodeList(fill);
      
    }
    
    public void add(BufferedImage img){
      
      list.insert(img);
      
    }
    
    public void makeView(){
      //Set preffered size for display buttons to in here
      // need to add a way for you to be able to add action listeners to the buttons
      frame = new JFrame("Displays selected");
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setVisible(false);
      
      removeAll();
      int n = list.getLength();
      BufferedImage temp;
      for(int i = 1; i <= n ; i++){
        PlaylistButton play;
        //JButton button = new JButton(); // add actionListener that returns image to each one 
        try{
          temp = list.findInList(i);
          play = new PlaylistButton(temp);
          //button.setIcon(new ImageIcon(temp)); //prolly need private Swing utilities for this\
          //button.setLabel(Integer.toString(i));
          //button.setPreferredSize(new Dimension(200,200));
          //button.addActionListener(new ActionListener(){
            //public void actionPerformed(ActionEvent e){
              
            //}
          //});
          //add(button);
          add(play);
        }catch(Exception ex){
          System.out.println("Button making messed up");
        }
      }
    }
    
    private class MyActionListener implements ActionListener{
      public void actionPerformed(ActionEvent e){
        panel = new PicturePanel();
        frame.add(panel);
        frame.setVisible(true);
        //changePlaylistImage(selectedImage);
      }
    }
    
    private void changePlaylistImage(final BufferedImage img){
      SwingUtilities.invokeLater(new Runnable(){
        public void run(){
          panel.replaceImage(img);
        }
      });
    }
    
    public static void main(String[] args){
      
      JFrame frame = new JFrame("Test");
      frame.setSize(400,400);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setVisible(true);
      BufferedImage img = null;
      BufferedImage image = null;
      BufferedImage last = null;
      try{
      img = ImageIO.read(new File("C:/Users/Quanah/Desktop/red-panda.jpg"));
      image = ImageIO.read(new File("C:/Users/Quanah/Desktop/LoveSicle.jpg"));
      last = ImageIO.read(new File("C:/Users/Quanah/Desktop/Idle.jpg"));
      }catch(Exception ex){
      }
      PlaylistPanelTest test = new PlaylistPanelTest(img);
      test.add(image);
      test.add(last);
      test.makeView(); //Only works with (x > 1) PictureNodeList
      frame.add(test);
      
    }
   
 }
