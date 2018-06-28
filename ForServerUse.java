package Essentials;

import javax.swing.JFrame;

public class ForServerUse {
 
 public static void main(String[] args){
     ImageServer server = new ImageServer();
     server.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     server.startRunning();
   }

}
