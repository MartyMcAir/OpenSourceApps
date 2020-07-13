package sweeper;

public class Game {
    //    private Bomb bomb; // нижний уровень с бомбами
//    private Flag flag; // верхний уровень с флагами
//    private GameState gameState; // состояние игры, выйграл проиграл.. процесс игры
//    private Matrix bombMap;
    private Bomb bomb;
    private Flag flag;
    private GameState gameState;

    public Game(int cols, int rows, int totalBombs) {
        Ranges.setSize(new Coordinate(cols, rows));
//        this.bomb = bomb;
        bomb = new Bomb(totalBombs);
        flag = new Flag();
    }

    // для запуска уровня с самого начала
    public void start() {
//        bombMap = new Matrix(Box.ZERO);
//        bombMap.setBox(new Coordinate(0, 0), Box.BOMB);
//        bombMap.setBox(new Coordinate(0, 1), Box.NUM1);
//        bombMap.setBox(new Coordinate(1, 0), Box.NUM2);
//        bombMap.setBox(new Coordinate(1, 1), Box.NUM3);
        bomb.start(); // всё что выше перенесенно в класс Bomb
        flag.start();
        gameState = GameState.PLAYED;
    }

    // возвщает box для каждой координаты
    // возвращает что должно быть в указанной клетке игрового поля (флаг / бомба ..)
    public Box getBox(Coordinate coordinate) {
//        return Box.values()[(coordinate.x + 2 * coordinate.y) % Box.values().length];
//        return bombMap.matrix[coordinate.x][coordinate.y];

        if (Box.OPENED == flag.getBox(coordinate)) // если клетка открыта
            return bomb.getBox(coordinate);
        else // а иначе возвращаем то что находится сверху (т.е. это скрывает, то что под квадратами)
            return flag.getBox(coordinate);
    }

    // нажатие лев и прав кнопки мыши _ lesson 36 _ действие открыть клетку
    public void pressLeftButton(Coordinate coordinate) {
//        flag.setOpenedToBox(coordinate);
//        gameState = GameState.BOMBED;
        if (isGameOver()) return;
        openBox(coordinate);
        checkWinner(); // validate не победили ли мы
    }

    // действие поставить флаг
    public void pressRightButton(Coordinate coordinate) {
//        flag.setFlaggedToBox(coordinate);
        if (isGameOver()) return;
        flag.toggleFlaggedToBox(coordinate);
    }

    private boolean isGameOver() {
        if (GameState.PLAYED != gameState) {
            start();
            return true;
        }
        return false; // пока состояние играем)
    }

    private void checkWinner() {
        if (GameState.PLAYED == gameState) { // если состояние игры - играет _ т.е. не проиграл и не выйграл
            // если кол-во закрытых бомб равно кол-ву бомб в игре
            if (flag.getTotalClosedBoxes() == bomb.getTotalBombs()) {
                gameState = GameState.WINNER;
                flag.setFlaggedToLastClosedBoxes();
            }
        }
    }

    // все варианты при открытии клетки
    private void openBox(Coordinate coordinate) {
        switch (flag.getBox(coordinate)) { // смотрим верхний слой игры
            case OPENED:
                setOpenedToClosedBoxesAroundNumber(coordinate);
                break;
            case FLAGGED:
                break;
            case CLOSED:
                switch (bomb.getBox(coordinate)) { // смотрим нижний слой игры
                    // ни одной бомбы вокруг нет
                    case ZERO:
                        openBoxesAroundZero(coordinate);
                        break;
                    // подорвались
                    case BOMB:
                        openBombs(coordinate);
                        break;
                    // вместо пречислений Num1-Num8
                    default:
                        flag.setOpenedToBox(coordinate);
                        break;
                }

        }
    }

    private void setOpenedToClosedBoxesAroundNumber(Coordinate coordinate) {
        if (Box.BOMB != bomb.getBox(coordinate)) { // проверка есть в самом enum в мтеоде getNumber()
            if (bomb.getBox(coordinate).getNumber() == flag.getCountOfFlaggedBoxesAround(coordinate)) {
                for (Coordinate around : Ranges.getCoordinatesAround(coordinate)) {
                    if (Box.CLOSED == flag.getBox(around))
                        openBox(around);
                }
            }
        }
    }

    private void openBombs(Coordinate bombedCoordinate) { // координата на которо1 мы подорвались
        flag.setBombedToBox(bombedCoordinate);
        // при проигрыше надо пооткрывать все неоткрытые бомбы
        for (Coordinate coordinate : Ranges.getAllCoordinates())
            if (bomb.getBox(coordinate) == Box.BOMB)
                flag.setOpenedToClosedBombBox(coordinate); // откроет бомбы, что не под флажками
            else // поменяет неправильно установленные флажки, которые не угадали что под ними бомба..
                flag.setNoBombToFlaggedSafeBox(coordinate);
        gameState = GameState.BOMBED; // статус проиграли
    }

    // имя метода само говорит за себя, вместо комментов
    private void openBoxesAroundZero(Coordinate coordinate) {
//        System.out.println(coordinate.x + " " + coordinate.y); // для видимости открытых координат рекурсией
        flag.setOpenedToBox(coordinate);
        for (Coordinate around : Ranges.getCoordinatesAround(coordinate))
            openBox(around); // повторно вызывает метод, который и вызывал текущий, до тех пор пока клетка ZERO
    }

    // Геттеры
    public GameState getGameState() {
        return gameState;
    }

    public int getTotalBombs() {
        return bomb.getTotalBombs();
    }

    public int getTotalFlags() {
        return flag.getTotalFlags();
    }
}