package sweeper;

class Flag {
    private Matrix flagMap; // хранит значения клетки (верхний слой значения флагов _ а низ бомбы)
    int totalFlags; // кол-во флагов, будет уменьшаться при открытии флагов
    int totalClosedBoxes; // при открытии любой клетки, этот счетчик уменьшается

    // перезапуск уровня
    void start() {
        flagMap = new Matrix(Box.CLOSED);
        totalFlags = 0;
        // всё кол-во клеток поля т.е. площадь квадрата исходя из x и y координат
        totalClosedBoxes = Ranges.getSquare(); // в начале игры все закрыты
        // откроет клетку в центре и то, что вокруг неё
//        Coordinate coordinate = new Coordinate(4, 4);
//        for (Coordinate around : Ranges.getCoordinatesAround(coordinate)) {
//            flagMap.setBox(around, Box.OPENED);
//        }
//        flagMap.setBox(new Coordinate(4, 4), Box.OPENED);
    }

    // для проверки, а что именно находится по данным координатам клетки (флаг или бомба или..)
    Box getBox(Coordinate coordinate) {
        return flagMap.getBox(coordinate);
    }

    // открыть клетку с координатами.. (естественно клетка, при этом должна быть закрытой)
    void setOpenedToBox(Coordinate coordinate) {
        totalClosedBoxes--;
        flagMap.setBox(coordinate, Box.OPENED);
    }

    // пометить флагом _
    private void setFlaggedToBox(Coordinate coordinate) {
        totalFlags++;
        flagMap.setBox(coordinate, Box.FLAGGED);
    }

    // убрать флаг с клетки
    private void setClosedToBox(Coordinate coordinate) {
        totalFlags--;
        flagMap.setBox(coordinate, Box.CLOSED);
    }

    // засеттить по данным координатам, значок что мы подорвались _ т.е. проиграли
    void setBombedToBox(Coordinate coordinate) {
        flagMap.setBox(coordinate, Box.BOMBED);
    }

    // поставить - убрать флажок _ toggle on-off
    void toggleFlaggedToBox(Coordinate coordinate) {
        switch (flagMap.getBox(coordinate)) {
            case FLAGGED:
                setClosedToBox(coordinate);
                break;
            case CLOSED:
                setFlaggedToBox(coordinate);
                break;
        }
    }

    // метка, что не было бомбы в координатах, помеченных юзером флагом
    void setNoBombToFlaggedSafeBox(Coordinate coordinate) {
        if (Box.FLAGGED == flagMap.getBox(coordinate))
            flagMap.setBox(coordinate, Box.NO_BOMB);
    }

    // открывает пустые клетки, в местах где были бомбы _ при проигрыше..
    void setOpenedToClosedBombBox(Coordinate coordinate) {
        if (Box.CLOSED == flagMap.getBox(coordinate))
            flagMap.setBox(coordinate, Box.OPENED);
    }

    // считает кол-во боксов, помеченных флагов вокруг клетки
    // помеченное не открывает, а отрывает остальные клетки..
    int getCountOfFlaggedBoxesAround(Coordinate coordinate) {
        int count = 0;
        for (Coordinate around : Ranges.getCoordinatesAround(coordinate)) {
            if (Box.FLAGGED == flagMap.getBox(around))
                count++;
        }
        return count;
    }

    // геттеры
    int getTotalClosedBoxes() {
        return totalClosedBoxes;
    }

    int getTotalFlags() {
        return totalFlags;
    }

    public void setFlaggedToLastClosedBoxes() {
        for (Coordinate coordinate : Ranges.getAllCoordinates()) {
            // если по окончанию игры есть координаты клетки в которой, клетка закрыта
            if (Box.CLOSED == flagMap.getBox(coordinate)) {
                setFlaggedToBox(coordinate); // то ставим на нее флаг
            }
        }
    }
}