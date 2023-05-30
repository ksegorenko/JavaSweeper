package org.example.model;

public class UpperField {
    public Matrix upperMatrix;
    private int countOfClosedBoxes;
    void createUpperField() {
        upperMatrix = new Matrix(Box.CLOSED);
        countOfClosedBoxes = Ranges.getSize().x * Ranges.getSize().y;
    }

    Box getBoxFromUpperMatrix(Coord coord) {
        return upperMatrix.getBoxByCoords(coord);
    }

    public void setOpenedToBox(Coord coord){
        upperMatrix.setBoxInMatrix(coord, Box.OPENED);
        --countOfClosedBoxes;
    }

    public void toggleFlagedToBox (Coord coord) {
        switch (upperMatrix.getBoxByCoords(coord)) {
            case FLAGED -> setClosedToBox(coord);
            case CLOSED -> setFlagedToBox(coord);
        }
    }

    public void setClosedToBox(Coord coord){
        upperMatrix.setBoxInMatrix(coord, Box.CLOSED);
    }

    public void setFlagedToBox(Coord coord){
        upperMatrix.setBoxInMatrix(coord, Box.FLAGED);
    }

    int getCountOfClosedBoxes() {
        return countOfClosedBoxes;
    }

    void setBombedToBox(Coord coord) {
        upperMatrix.setBoxInMatrix(coord, Box.BOMBED);
    }

    void setNobombToFlagedSafeBox(Coord coord) {
        if (upperMatrix.getBoxByCoords(coord) == Box.FLAGED) {
            upperMatrix.setBoxInMatrix(coord, Box.NOBOMB);
        }
    }

    void setOpenedToClosedBombBox(Coord coord) {
        if (upperMatrix.getBoxByCoords(coord) == Box.CLOSED){
            upperMatrix.setBoxInMatrix(coord, Box.OPENED);
        }
    }

    int getCountOfFlagedBoxesAround(Coord coord) {
        int count = 0;
        for (Coord around : Ranges.getCoordsAround(coord)){
            if (upperMatrix.getBoxByCoords(around) == Box.FLAGED) {
                count++;
            }
        }
        return count;
    }
}
