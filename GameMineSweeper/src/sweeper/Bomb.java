package sweeper;

class Bomb {
    private Matrix bombMap;   // матрица карт бомб
    private int totalBombs;  // при перезапуске игры, общее кол-во бомб текущей игры

    Bomb(int totalBombs) {
        this.totalBombs = totalBombs;
        fixBombsCount();
    }

    // создает новое поле бомб _ // для запуска уровня с самого начала
    void start() {
        bombMap = new Matrix(Box.ZERO);
        // заполняем матрицу
//        bombMap.setBox(new Coordinate(0, 0), Box.BOMB);
//        bombMap.setBox(new Coordinate(0, 1), Box.NUM1);
//        bombMap.setBox(new Coordinate(1, 0), Box.NUM2);
//        bombMap.setBox(new Coordinate(1, 1), Box.NUM3);
        for (int i = 0; i < totalBombs; i++)
            placeBombs(); // код выше перенесен в данный метод
    }

    // кажет какой Box находится в данных координатах
    Box getBox(Coordinate coordinate) {
        return bombMap.getBox(coordinate);
    }

    // получение общего кол-ва бомб
    int getTotalBombs() {
        return totalBombs;
    }

    // проверка на максимал кол-во бомб _ половина от площади поля
    // допустимое кол-во бомб в соотвветствии с размером поля _ т.е. не больше чем самих клеток в игре
    void fixBombsCount() {
        int maxBombs = Ranges.getSize().x * Ranges.getSize().y / 2;
        if (totalBombs > maxBombs)
            totalBombs = maxBombs;
    }

    // размещение бомб в определенные клетки (в рандомные координаты)
    private void placeBombs() {
        // https://www.udemy.com/course/javasweeper/learn/lecture/11039888#overview lesson33
        // бессконечный цикл, для рандомного размещения бомб
        while (true) { // бессконечный, дабы разместить во все возможные места
            // _ так при поле 9 на 9=81 клетка, и поставив 81 бомбу, все клетки должны быть заполнены
            Coordinate randomCoordinate = Ranges.getRandomCoordinate();
            if (Box.BOMB == bombMap.getBox(randomCoordinate))
                continue;
            // заполняем матрицу
            bombMap.setBox(randomCoordinate, Box.BOMB);
            incNumbersAroundBomb(randomCoordinate);
            break; // если место найдено выходим из цикла
        }
    }

    private void incNumbersAroundBomb(Coordinate randomCoordinate) {
        for (Coordinate around : Ranges.getCoordinatesAround(randomCoordinate)) {
//            bombMap.setBox(around, Box.NUM1); // ставим еденицы вокруг каждой бомбы
            // цифра в игре сообщает об кол-ве бомб вокруг данной клетки
            // проверям что бы при переборе на след. элемент, если это бомба то её не затирало
            if (Box.BOMB != bombMap.getBox(around))
                bombMap.setBox(around, bombMap.getBox(around).nextNumberBox()); // ставим еденицы вокруг каждой бомбы
        }
    }
}