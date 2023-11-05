import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.imgcodecs.Imgcodecs;

import javax.swing.*;

public class imgShow extends JFrame {
    static{System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        System.out.println("Version: " + Core.VERSION);}

    public static void main(String[] args) {
        JFrame window = new JFrame();
        JFrame screen = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);

        Mat img = Imgcodecs.imread("пушкин.jpg");
        MatOfByte buf = new MatOfByte(); //создание байтовой матрицы
        Imgcodecs.imencode(".jpg", img,buf);  //перезапись матрицы в байтовую матрицу


    }
}
