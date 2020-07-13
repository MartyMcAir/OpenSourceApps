package sweeper;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Ranges {
    private static Coordinate size;
    private static List<Coordinate> allCoordinates; // хранилище все возможных координат игрового поля
    private static Random random = new Random();

    static void setSize(Coordinate size) {
        Ranges.size = size;
        allCoordinates = new ArrayList<>();
        for (int x = 0; x < size.x; x++) {
            for (int y = 0; y < size.y; y++)
                allCoordinates.add(new Coordinate(x, y));
        }
    }

    public static void setSize(int cols, int rows) {
        Coordinate coordinate = new Coordinate(cols, rows);
        setSize(coordinate);
    }

    public static Coordinate getSize() {
        return size;
    }

    public static List<Coordinate> getAllCoordinates() {
        return allCoordinates;
    }

    // возвращает все клетки вокруг координаты, для размещения единиц вокруг бомб
    public static List<Coordinate> getCoordinatesAround(Coordinate coordinate) {
        List<Coordinate> coordinatesList = new ArrayList<>();
        Coordinate around;

        for (int x = coordinate.x - 1; x <= coordinate.x + 1; x++) {
            for (int y = coordinate.y - 1; y <= coordinate.y + 1; y++) {
                // проверяем что координаты не вышли за границы поля,
                // а заодно и инициализируем переменную around
                if (inRange(around = new Coordinate(x, y))) {
                    if (!around.equals(coordinate))
                        coordinatesList.add(around);
                }
            }
        }
        return coordinatesList;
    }

    public static Coordinate getRandomCoordinate() {
        return new Coordinate(random.nextInt(size.x), random.nextInt(size.y));
    }

    // определяет (проверяет) находимся ли мы в пределах нашего поля _ (т.е. не выходим ли за его рамки)
    static boolean inRange(Coordinate coordinate) {
        return coordinate.x >= 0 && coordinate.x < size.x
                && coordinate.y >= 0 && coordinate.y < size.y;
    }

    static int getSquare() {
        return size.x * size.y;
    }
}