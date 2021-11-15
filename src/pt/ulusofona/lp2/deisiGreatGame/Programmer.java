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

    public int getId() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public ProgrammerColor getColor() {                   /////////////////////
        return color;
    }

    public String toString() {                     ///////////////////////
        StringBuilder str = new StringBuilder();
        str.append(ID).append(" | ").append(name).append(" | ").append(position).append(" | ");

        for (String language : favLanguages) {
            str.append(language).append("; ");
        }

        str.setLength(str.length()-2);

        str.append(" | ").append("Em Jogo");

        return str.toString();

    }

}
