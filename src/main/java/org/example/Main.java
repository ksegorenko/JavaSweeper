package org.example;

import java.util.Scanner;
import org.example.view.gui.GuiInterface;

public class Main {
    enum GameInterface {
        GUI,
        TUI
    }

    private static GameInterface getGameInterface() {
        System.out.println("Press 1 to choose GUI");
        System.out.println("Press 2 to choose TUI");
        String interfaceNum;
        while(true) {
            Scanner scanner = new Scanner(System.in);
            interfaceNum = scanner.nextLine();
            if(interfaceNum.equals("1")){
                return GameInterface.GUI;
            }
            if(interfaceNum.equals("2")){
                return GameInterface.TUI;
            }
        }
    }

    public static void main(String[] args) {
        GameInterface gameInterface = getGameInterface();
        switch (gameInterface) {
            case GUI -> new GuiInterface();
            // case TUI -> new TUIInterface()
        }
    }
}