package ServerClient;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.videoio.VideoCapture;

import javax.swing.*;
import java.awt.event.WindowEvent;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientTransmition {
    static {System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        System.out.println("version: " +Core.VERSION);}

    public static void main(String[] args) throws IOException {
        JFrame window = new JFrame();
        JLabel screen = new JLabel();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);

        Mat frame = new Mat();
        MatOfByte buf = new MatOfByte();
        ImageIcon ic = new ImageIcon();
        byte[] imageData;
        VideoCapture videoCapture = new VideoCapture(0);

        Socket socket = new Socket("localhost",1234);
        DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());

        while(videoCapture.grab()){
            try{
                videoCapture.read(frame);
                Imgcodecs.imencode(".png", frame,buf);
                imageData = buf.toArray();
                int dataLength = imageData.length;
                outputStream.writeInt( dataLength);
                outputStream.write(imageData,0,dataLength);
                ic=new ImageIcon(imageData);
                screen.setIcon(ic);
                window.setContentPane(screen);
                window.pack();
            } catch (RuntimeException e){
                System.out.println(e.getMessage());
            } finally {
                videoCapture.release();
                window.dispatchEvent(new WindowEvent(window, WindowEvent.WINDOW_CLOSED));
            }
        }


    }
}
