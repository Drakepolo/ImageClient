package Essentials;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.util.concurrent.TimeUnit;

public class PictureNodeList{
   
   private PictureNode start;
   private File fl;
   private PicturePanel panel;
   JTextField text;
   private int length;
   
   public PictureNodeList(PictureNode start){
     
     this.start = start;
     fl = new File("TestFolder");
     fl.mkdir();
     //start.storeImage("C:/Users/Quanah/Desktop/Java Practices/TestFolder/a.png");
     start.setNext(null);
     raiseLength();
     setPlaces();
   }
   
   public PictureNodeList(BufferedImage image){
     
     start = new PictureNode(image);
     fl = new File("TestFolder");
     fl.mkdir();
     start.setNext(null);
     raiseLength();
     setPlaces();
     
   }
   
   public void insert(PictureNode ins){
     
     
     if(start == null){
       start = ins;
       start.setNext(null);
       raiseLength();
       setPlaces();
     }
     else{
       PictureNode current = start;
       while(current.getNext() != null){
         current = current.getNext();
       }
       current.setNext(ins);
       ins.setNext(null);
       raiseLength();
       setPlaces();
     }
   }
   
   public void insert(BufferedImage bi){
     
     
     PictureNode newNode = new PictureNode(bi);
     if(start == null){
       start = newNode;
       start.setNext(null);
       raiseLength();
       setPlaces();
     }
     else{
       PictureNode current = start;
       while(current.getNext() != null){
         current = current.getNext();
       }
       newNode = new PictureNode(bi);
       current.setNext(newNode);
       newNode.setNext(null);
       raiseLength();
       setPlaces();
     }
     
   }
   
   //Doesnt show last in list
   public void showList(){
     
     JFrame frame = new JFrame();
     text = new JTextField();
     panel = new PicturePanel(start.getImage());
     
     frame.setSize(300,300);
     frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     frame.setVisible(true);
     frame.add(panel,BorderLayout.CENTER);
     showPlace(start.getPlace());
     frame.add(text,BorderLayout.SOUTH);
     //Place Time delay here
     try{
        TimeUnit.SECONDS.sleep(2);
       }catch(InterruptedException ex){
         System.err.println("Timer messed up");
       }
     
     PictureNode current = start.getNext();
     while(current.getNext() != null){
       changePic(current.getImage());
       showPlace(current.getPlace());
       try{
        TimeUnit.SECONDS.sleep(2);
       }catch(InterruptedException ex){
         System.err.println("Timer messed up");
       }
       current = current.getNext();
     }
     changePic(current.getImage());
     showPlace(current.getPlace());
     try{
        TimeUnit.SECONDS.sleep(2);
       }catch(InterruptedException ex){
         System.err.println("Timer messed up");
       }
     frame.dispatchEvent(new WindowEvent(frame,WindowEvent.WINDOW_CLOSING));
     
   }
   
   public void storeList(PictureNodeList pnl){
     
   }
   
   private void changePic(final BufferedImage input){
     SwingUtilities.invokeLater(new Runnable(){
       public void run(){
         panel.replaceImage(input);
       }
     });
     
   }
   
   private void showPlace(final int n){
     SwingUtilities.invokeLater(new Runnable(){
       public void run(){
         String number = Integer.toString(n);
         text.setText(number);
       }
     });
   }
   
   public BufferedImage findInList(int number){
     
     PictureNode temp = start;
     while(temp.getPlace() != number){
       temp = temp.getNext();
     }
     return temp.getImage();
   }
   
   public int getLength(){
     
     return length;     
   }
   public void raiseLength(){
     length = (length + 1);
   }
   
   public void setPlaces(){
     PictureNode temp = start;
     for(int i = 1; i < length ; i++ ){
       if(temp.getNext() != null){
         temp.setPlace(i);
         temp = temp.getNext();
       }
       temp.setPlace(length);
     }
   }
 }
