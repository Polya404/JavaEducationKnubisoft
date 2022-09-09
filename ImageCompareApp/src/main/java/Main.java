import lombok.SneakyThrows;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.util.Arrays;
import java.util.Objects;

public class Main {
    @SneakyThrows
    public static void main(String[] args) {
//        FileWriter writer = new FileWriter("E:\\Polya project\\JavaProject\\JavaEducationKnubisoft\\ImageCompareApp\\src\\main\\resources\\pixel_values1.txt");
//        //Reading the image
        File file= new File("E:\\Polya project\\JavaProject\\JavaEducationKnubisoft\\ImageCompareApp\\src\\main\\resources\\2022-09-06.png");
        File file1= new File("E:\\Polya project\\JavaProject\\JavaEducationKnubisoft\\ImageCompareApp\\src\\main\\resources\\2022-09-06 (1).png");
//        BufferedImage img = ImageIO.read(file);
//        BufferedImage img1 = ImageIO.read(file1);
//        for (int y = 0; y < img1.getHeight(); y++) {
//            for (int x = 0; x < img1.getWidth(); x++) {
//                int pixel = img1.getRGB(x,y);
//                Color color = new Color(pixel, true);
//                int red = color.getRed();
//                int green = color.getGreen();
//                int blue = color.getBlue();
//                writer.append(red+":");
//                writer.append(green+":");
//                writer.append(blue+"");
//                writer.append("\n");
//                writer.flush();
//            }
//        }
//        writer.close();
        System.out.println(Arrays.equals(file.list(), file1.list()));
        System.out.println("RGB values at each pixel are stored in the specified file");
    }
}
