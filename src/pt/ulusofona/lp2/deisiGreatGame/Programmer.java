package pt.ulusofona.lp2.deisiGreatGame;

import java.util.ArrayList;

enum ProgrammerColor {
    PURPLE,
    BLUE,
    GREEN,
    BROWN;
}

public class Programmer {

    String name;
    ArrayList<String> favLanguages;
    int ID;
    ProgrammerColor color;
    int position;

    public Programmer(int ID, String name, ArrayList<String> favLanguages, ProgrammerColor color, int position) {
        this.ID = ID;
        this.name = name;
        this.favLanguages = favLanguages;
        this.color = color;
        this.position = position;
    }

    int getId() {
        return ID;
    }

    String getName() {
        return name;
    }

    ProgrammerColor getColor() {                   /////////////////////
        return color;
    }

    public String toString() {                     ///////////////////////

        return "";
    }

}
