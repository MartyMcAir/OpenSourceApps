package sweeper;

public class Coordinate {
    public int x, y;

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // для исключения размещения бомб в одни и те же координаты
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Coordinate))
            return super.equals(obj);

        Coordinate coordinate = (Coordinate) obj;
        return coordinate.x == this.x && coordinate.y == this.y;
    }
}