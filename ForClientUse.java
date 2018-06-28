package Essentials;

import javax.swing.JFrame;

public class ForClientUse {
 
public static void main(String[] args){
     
     ImageClient charlie;
     charlie = new ImageClient("127.0.0.1");
     charlie.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     charlie.startRunning();
     
   }

}
