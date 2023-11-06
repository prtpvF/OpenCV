import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.videoio.VideoCapture;
import org.opencv.videoio.VideoWriter;

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
        //создание объекта записи видео
        //VideoWriter writer = new VideoWriter("src/testSaveVideo.mpeg", VideoWriter.fourcc('m','j','p','g'),30,new Size(640,480));

        Mat frame  = new Mat();
        MatOfByte buf = new MatOfByte();
        ImageIcon ic;

        while(cap.grab()){
            cap.read(frame);
            //запись видео по кадрам
            //writer.write(frame);

            Scalar color = new Scalar(0,255,0);
            Imgproc.line(frame,new Point(0,-60), new Point(640,480), color,2 );
            Imgproc.rectangle(frame, new Rect(200,100,240,280), color, 4);

            Imgcodecs.imencode(".png", frame, buf);
            ic = new ImageIcon(buf.toArray());
            screen.setIcon(ic);
            window.setContentPane(screen);
            window.pack();
        }
        cap.release();
        //сохранение полученного видео
        //writer.release();
        window.dispatchEvent(new WindowEvent(window, WindowEvent.WINDOW_CLOSED));

    }
}
