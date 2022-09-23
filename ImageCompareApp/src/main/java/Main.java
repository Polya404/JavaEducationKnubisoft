import lombok.SneakyThrows;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class Main {
    @SneakyThrows
    public static void main(String[] args) {
        BufferedImage input1 = ImageIO.read(new File("ImageCompareApp/src/main/resources/img.png"));
        BufferedImage input2 = ImageIO.read(new File("ImageCompareApp/src/main/resources/img_1.png"));
        ImageIO.write(new ResultImage().getResult(input1, input2), "jpg", new File("ImageCompareApp/src/main/resources/img_1.png"));
    }
}
