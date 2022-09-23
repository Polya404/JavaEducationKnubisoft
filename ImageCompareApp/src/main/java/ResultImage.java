import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ResultImage {
    public BufferedImage getResult(BufferedImage img1, BufferedImage img2) {
        List<Point> points = new ArrayList<>(); // лист координат точек котрые отличаются
        getDifferentPoints(img1, img2, points); // добавляем в лист все отличающиеся точки
        List<List<Point>> groups = new ArrayList<>(); // лист в котором хранятся группы
        formGroups(points, groups);  // добавляем в лист все отличающиеся точки которые находятся рядом
        buildRectangles(img2, groups); // строим прямоугольник по группам точек
        return img2;
    }

    private void buildRectangles(BufferedImage img2, List<List<Point>> groups) {
        // перебираем все группы находим крайние вершины и по ним рисуем прямоугольник
        for (List<Point> list : groups){
            int mostLeft = Integer.MAX_VALUE;
            int mostTop = Integer.MAX_VALUE;
            int mostBottom = 0;
            int mostRight = 0;
            for (Point p : list){
                mostLeft = Math.min(p.getX(), mostLeft);
                mostTop = Math.min(p.getY(), mostTop);
                mostRight = Math.max(p.getX(), mostRight);
                mostBottom = Math.max(p.getY(), mostBottom);
            }
            highlight(img2, mostLeft, mostTop, mostBottom, mostRight);
        }
    }

    private void highlight(BufferedImage img2, int mostLeft, int mostTop, int mostBottom, int mostRight) {
        // рисуем рамку
        int width = mostRight - mostLeft;
        int height = mostBottom - mostTop;
        Graphics2D g2d = img2.createGraphics();
        g2d.setColor(Color.RED);
        g2d.drawRect(mostLeft - 5, mostTop - 5, width + 10, height + 10);
        g2d.dispose();
    }

    private void formGroups(List<Point> points, List<List<Point>> groups) {
        Comparator<Point> pointComparator = Comparator.comparingInt(Point::getX);
        points.sort(pointComparator);  // лист отсортированых точек по иксу
        while (!points.isEmpty()) {
            List<Point> group = new ArrayList<>();  // лист групп
            Point first = points.get(0);
            for (Point p : points) { // проходимся по всем отличающимся точкам и считаем дистанцию между ними
                if (calculateDistance(first.getX(), first.getY(), p.getX(), p.getY()) < 10) {
                    group.add(p); // если дистанция не большая добавляем их в одну группу
                }
            }
            points.removeAll(group); // удаляем точки которые были объеденены в группу
            mergeOrAddNewGroup(groups, group); // объеденняем точки в группе или создаем новую группу
        }
    }

    private void mergeOrAddNewGroup(List<List<Point>> groups, List<Point> group) {
        boolean flag = false;
        for (List<Point> list : groups) {  // итерация по группам
            for (Point fmp : group) { // итерация по отличающимся точкам
                for (Point p : list) { // сравниваем точки
                    if (calculateDistance(fmp.getX(), fmp.getY(), p.getX(), p.getY()) < 5) { // если расстояние между точками маленькое добавляем в одну группу
                        flag = true;
                        list.addAll(group);
                        break;
                    }
                }
                if (flag) break;
            }
            if (flag) break;
        }
        if (!flag) groups.add(group); // если точки не разделились на группы то добавляем изначальную группу
    }

    private double calculateDistance(int x, int y, int x1, int y1) {
        // рассчитываем дистанцию между точками
        return Math.sqrt(Math.pow(x - x1, 2) + Math.pow(y - y1, 2));
    }

    private void getDifferentPoints(BufferedImage img1, BufferedImage img2, List<Point> points) {
        // проходим по ширине и высоте сравниваем все пиксели и если они отличаются то добавляем в лист
        int width = img1.getWidth();
        int height = img1.getHeight();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                int pixel1 = img1.getRGB(j, i);
                int pixel2 = img2.getRGB(j, i);
                if (pixel1 != pixel2) {
                    points.add(new Point(j, i));
                }
            }
        }
    }
}
