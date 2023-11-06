package ServerClient;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;

import javax.swing.*;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerReceived {
    static {System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        System.out.println(Core.VERSION);}
    public static void main(String[] args) throws IOException {
        JFrame window = new JFrame("Server: ");
        JLabel screen = new JLabel();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);
        Mat frame = new Mat();
        MatOfByte buf = new MatOfByte();
        ImageIcon ic = new ImageIcon();
        ServerSocket serverSocket =  new ServerSocket(1234);
        while(true){
            try{
                Socket cleintSocket = serverSocket.accept();
                DataInputStream inputStream = new DataInputStream(cleintSocket.getInputStream());
                while(true){
                    int dataLength = inputStream.readInt();
                    if(dataLength>0){
                        byte[] imageData = new byte[dataLength];
                        inputStream.readFully(imageData,0,dataLength);

                        ic = new ImageIcon(imageData);
                        screen.setIcon(ic);
                        window.setContentPane(screen);
                        window.pack();
                    }
                }

            }catch (RuntimeException e){
                System.out.println(e.getMessage());
            }
        }
    }
}
