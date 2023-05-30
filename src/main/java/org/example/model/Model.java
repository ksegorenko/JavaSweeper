package org.example.model;

public class Model {
    private final BottomField bottomField;
    public UpperField upperField;

    private GameState state;
    public GameState getState() {
        return state;
    }
    public Model(int cols, int rows, int bombs) {
        Ranges.setSize(new Coord(cols, rows));
        bottomField = new BottomField(bombs);
        upperField = new UpperField();
    }

    public void start() {
        bottomField.createBombMatrix();
        upperField.createUpperField();
        state = GameState.PLAYED;
    }

    public Box getBox (Coord coord) {
        if (upperField.getBoxFromUpperMatrix(coord) == Box.OPENED)
            return bottomField.getBoxFromBottomField(coord);
        else
            return upperField.getBoxFromUpperMatrix(coord);
    }

    public void checkWinner() {
        if (state == GameState.PLAYED){
            if (upperField.getCountOfClosedBoxes() == bottomField.getTotalBombs()) {
                state = GameState.WINNER;
            }
        }
    }

    public void openBox (Coord coord) {
        switch (upperField.getBoxFromUpperMatrix(coord)) {
            case OPENED : setOpenedToClosedBoxesAroundNumber(coord); return;
            case FLAGED : return;
            case CLOSED :
                switch (bottomField.getBoxFromBottomField(coord)) {
                    case ZERO : openBoxesAround(coord); return;
                    case BOMB : openBombs(coord); return;
                    default   : upperField.setOpenedToBox(coord); return;
                }
        }
    }

    private void setOpenedToClosedBoxesAroundNumber (Coord coord) {
        if (bottomField.getBoxFromBottomField(coord) != Box.BOMB)
            if (upperField.getCountOfFlagedBoxesAround(coord) == bottomField.getBoxFromBottomField(coord).getNumber())
                for (Coord around : Ranges.getCoordsAround(coord))
                    if(upperField.getBoxFromUpperMatrix(around) == Box.CLOSED)
                        openBox(around);
    }

    private void openBombs(Coord bombed) {
        state = GameState.BOMBED;
        upperField.setBombedToBox(bombed);
        for (Coord coord : Ranges.getAllCoords()){
            if (bottomField.getBoxFromBottomField(coord) == Box.BOMB) {
                upperField.setOpenedToClosedBombBox(coord);
            }
            else {
                upperField.setNobombToFlagedSafeBox(coord);
            }
        }
    }

    private void openBoxesAround(Coord coord) {
        upperField.setOpenedToBox(coord);
        for (Coord around : Ranges.getCoordsAround(coord))
            openBox(around);
    }

    public boolean gameOver() {
        if (state == GameState.PLAYED)
            return false;
        start();
        return true;
    }
}
