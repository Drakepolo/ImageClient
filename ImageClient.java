package Essentials;
import java.io.*;
import java.net.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class ImageClient extends JFrame{
   
   private JTextField userText;
   private JTextField sentText;
   
   private JButton switchOne;
   private JButton switchTwo;
   
   private PicturePanel picWindow;
   private JPanel firstPanel;
   private JPanel secondPanel;
   private PlaylistPanelTest playlistWindow;
   private PicturePanel playlistShow;
   private JPanel holdingPanel;
   CardLayout cl = new CardLayout();
   
   private ObjectOutputStream output;
   private ObjectInputStream input;
   private ServerSocket server;
   private Socket connection;
   private String website;
   private URL url;
   private String serverIP;
   private String message;
   
   public ImageClient(String host){
     
     super("Send Picture Client");
     serverIP = host;
     holdingPanel = new JPanel();
     holdingPanel.setLayout(cl);
     
     createPicPanel();
     createPlaylistPanel();
     holdingPanel.add(firstPanel,"1");
     holdingPanel.add(secondPanel, "2");
     cl.show(holdingPanel, "1");
     add(holdingPanel);
     setSize(400,400);
     setVisible(true);
     pack();
     
   }
   
   private class MyActionListener implements ActionListener{
     public void actionPerformed(ActionEvent e){
       sendMessage(e.getActionCommand());
       userText.setText("");
     }
   }
   
   public void startRunning(){
     
     try{
       connectToServer();
       setupStreams();
       whileChatting();
     }catch(EOFException eofExcpetion){
       showMessage("Client terminated connection");
     }catch(IOException ioException){
       ioException.printStackTrace();
     }finally{
       closeCrap();
     }
   }
   
   private void connectToServer() throws IOException{
     connection = new Socket(InetAddress.getByName(serverIP), 6789);
   }
   
   private void setupStreams() throws IOException{
     output = new ObjectOutputStream(connection.getOutputStream());
     output.flush();
     input = new ObjectInputStream(connection.getInputStream());
   }
   
   private void whileChatting() throws IOException{
     ableToType(true);
     do{
       try{
         message = (String) input.readObject();
         showMessage(message);
         changePic(message);
         //playlistWindow.makeView();
         updatePlaylist(message); // have updatePlaylist take in string link, turn to link then turn to image and use
         playlistWindow.makeView();
       }catch(ClassNotFoundException classNotFoundException){
         showMessage("I dont know what he sent broski");
       }
     }while(!message.equals("CLIENT - END"));
   }
   
   private void closeCrap(){
     showMessage("Closing connections");
     ableToType(false);
     try{
       output.close();
       input.close();
       connection.close();
     }catch(IOException ioException){
       ioException.printStackTrace();
     }
   }
   
   private void sendMessage(String message){
     
     try{
       output.writeObject(message);
       output.flush();
     }catch(IOException ex){
       System.out.println("You cant send that brodi :|");
     }
     
   }
   
   private void showMessage(final String text){
     
     SwingUtilities.invokeLater(new Runnable(){
       public void run(){
         sentText.setText(text);
       }
     });
   }
   
   private void ableToType(final boolean tof){
     
     SwingUtilities.invokeLater(new Runnable(){
       public void run(){
         userText.setEditable(tof);
       }
     });
     
   }
   
   private void createPicPanel(){
     
     firstPanel = new JPanel();
     picWindow = new PicturePanel("https://upload.wikimedia.org/wikipedia/commons/7/7c/Cima_da_Conegliano%2C_God_the_Father.jpg");
     
     userText = new JTextField();
     userText.setEditable(false);
     userText.setPreferredSize(new Dimension(150,50));
     sentText = new JTextField();
     sentText.setEditable(false);
     sentText.setPreferredSize(new Dimension(150,50));
     userText.addActionListener(new MyActionListener());
     switchOne = new JButton("First Button"); //Going to split eastern panel three vertical butttons w/ grid
     switchOne.addActionListener(new PlaylistSwitch());
     
     firstPanel.add(userText,BorderLayout.NORTH);
     firstPanel.add(sentText,BorderLayout.SOUTH);
     firstPanel.add(switchOne,BorderLayout.EAST);
     firstPanel.add(picWindow,BorderLayout.CENTER);
     
   }
   
   private void createPlaylistPanel(){
     
     secondPanel = new JPanel();
     playlistWindow = new PlaylistPanelTest(picWindow.returnImage());
     secondPanel.add(playlistWindow,BorderLayout.CENTER);
     switchTwo = new JButton("Switch to main panel");
     switchTwo.addActionListener(new MainSwitch());
     secondPanel.add(switchTwo,BorderLayout.EAST);
     
   }
   
   private void createPlaylistShow(){
     
     playlistShow = new PicturePanel();
     
   }
   
   private void changePic(final String link){
     SwingUtilities.invokeLater(new Runnable(){
       public void run(){
         picWindow.replaceImage(link);
       }
     });
   }
   
   private class PlaylistSwitch implements ActionListener{
     public void actionPerformed(ActionEvent e){
       changePanelOne();
     }
   }
   private void changePanelOne(){
     SwingUtilities.invokeLater(new Runnable(){
       public void run(){
         cl.show(holdingPanel,"2");
       }
     });
   }
   
   private class MainSwitch implements ActionListener{
     public void actionPerformed(ActionEvent e){
       changePanelTwo();
     }
   }
   
   private void changePanelTwo(){
     SwingUtilities.invokeLater(new Runnable(){
       public void run(){
         cl.show(holdingPanel,"1");
       }
     });
   }
   
   private void updatePlaylist(final String img){
     SwingUtilities.invokeLater(new Runnable(){
       public void run(){
         BufferedImage picture = null;
         try{
           URL url = new URL(img);
           picture = ImageIO.read(url);
         }catch(Exception ex){
           System.out.println("Playlist problems");
         }
         playlistWindow.add(picture);
         //playlistWindow.makeView();
       }
     });
   }
   
   public static void main(String[] args){
     
     ImageClient charlie;
     charlie = new ImageClient("127.0.0.1");
     charlie.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     charlie.startRunning();
     
   }
 }
