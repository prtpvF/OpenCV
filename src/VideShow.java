import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.videoio.VideoCapture;

import javax.swing.*;
import java.awt.event.WindowEvent;

public class VideShow extends JFrame {
    static{System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        System.out.println("Version: " + Core.VERSION);}

    public static void main(String[] args) {
        JFrame window = new JFrame();
        JLabel screen = new JLabel();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);

        //инициализация видео потока
        VideoCapture cap = new VideoCapture(0);
        Mat frame  = new Mat();
        MatOfByte buf = new MatOfByte();
        ImageIcon ic;

        while(cap.grab()){
            cap.read(frame);

            Imgcodecs.imencode(".png", frame, buf);
            ic = new ImageIcon(buf.toArray());
            screen.setIcon(ic);
            window.setContentPane(screen);
            window.pack();
        }
        cap.release();
        window.dispatchEvent(new WindowEvent(window, WindowEvent.WINDOW_CLOSED));

    }
}
