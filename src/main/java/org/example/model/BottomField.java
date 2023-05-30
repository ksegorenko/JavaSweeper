package org.example.model;

class BottomField {
    private Matrix bottomMatrix;
    private int totalBombs;

    BottomField(int totalBombs) {
        this.totalBombs = totalBombs;
        fixBombsCount();
    }

    private void fixBombsCount () {
        int maxBombs = Ranges.getSize().x * Ranges.getSize().y / 2;
        if (totalBombs > maxBombs)
            totalBombs = maxBombs;
    }

    void createBombMatrix() {
        bottomMatrix = new Matrix (Box.ZERO);
        for (int j = 0; j < totalBombs; ++j) {
            placeBomb();
        }
    }

    private void placeBomb() {
        while (true) {
            Coord coord = Ranges.getRandomCoord();
            if (Box.BOMB == bottomMatrix.getBoxByCoords(coord))
                continue;
            bottomMatrix.setBoxInMatrix(coord, Box.BOMB);
            incNumberAroundBomb(coord);
            break;
        }
    }

    Box getBoxFromBottomField(Coord coord) {
        return bottomMatrix.getBoxByCoords(coord);
    }

    private void incNumberAroundBomb (Coord coord) {
        for (Coord around : Ranges.getCoordsAround(coord)) {
            if (Box.BOMB != bottomMatrix.getBoxByCoords(around)) {
                bottomMatrix.setBoxInMatrix(around, bottomMatrix.getBoxByCoords(around).getNextNumberBox());
            }
        }
    }

    int getTotalBombs() {
        return totalBombs;
    }
}
