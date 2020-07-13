package sweeper;

public class Matrix {
    private Box[][] matrix;

    public Matrix(Box box) {
        this.matrix = new Box[Ranges.getSize().x][Ranges.getSize().y];
        for (Coordinate coordinate : Ranges.getAllCoordinates())
            matrix[coordinate.x][coordinate.y] = box;
    }

    public Box getBox(Coordinate coordinate) {
        // если координата в перделах поля, т.е. не выходит за ее границы
        if (Ranges.inRange(coordinate))
            return matrix[coordinate.x][coordinate.y];
        return null;
    }

    public void setBox(Coordinate coordinate, Box box) {
        if (Ranges.inRange(coordinate))
            matrix[coordinate.x][coordinate.y] = box;
    }
}