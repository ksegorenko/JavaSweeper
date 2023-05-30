package org.example.controller;

import org.example.model.*;

public class GUIController {
    private Model game;

    public GUIController(Model game){
        this.game = game;
    }

    public void pressLeftButton(Coord coord) {
        if (game.gameOver()) return;
        game.openBox(coord);
        game.checkWinner();
    }

    public void pressRightButton(Coord coord) {
        if (game.gameOver()) return;
        game.upperField.toggleFlagedToBox(coord);
    }
}
