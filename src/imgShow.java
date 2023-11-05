import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import javax.swing.*;

public class imgShow extends JFrame {
    static{System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        System.out.println("Version: " + Core.VERSION);}

    public static void main(String[] args) {
        JFrame window = new JFrame();
        JLabel screen = new JLabel();//контейнер изображения
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);

        Mat img = Imgcodecs.imread("src/pushkin.jpg");

        Mat imgEmpty = new Mat(img.size(), img.type());
        Mat kernel = new Mat(20,20, CvType.CV_8SC1, new Scalar(1.0));

        Imgproc.dilate(img,imgEmpty,kernel);
        Imgproc.erode(img, imgEmpty,kernel);
      //  Imgproc.cvtColor(img,imgEmpty,Imgproc.COLOR_BGR2GRAY ); меняет цвет
        Imgproc.GaussianBlur(img,imgEmpty,new Size(15,15),0 ); //блюр фотографии
        Imgproc.Canny(img,imgEmpty,2,2); //
        Imgproc.resize(imgEmpty,imgEmpty,new Size(200,250)); //изменяем размер

       // Mat imgCrop = img.rowRange(100,250).colRange(50,250); // Обрезаем картинку по оси y с 100 пикселя до 250
        Imgcodecs.imwrite("src/savedImg.png", imgEmpty);



        MatOfByte buf = new MatOfByte(); //создание байтовой матрицы
        Imgcodecs.imencode(".jpg", imgEmpty,buf);  //перезапись матрицы в байтовую матрицу

        ImageIcon ic = new ImageIcon(buf.toArray()); //конвертация байтовой информации в объект изображения
        screen.setIcon(ic);
        window.getContentPane().add(screen);
        window.pack();





    }
}
