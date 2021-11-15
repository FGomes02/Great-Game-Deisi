package pt.ulusofona.lp2.deisiGreatGame;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

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

    public Programmer(int ID, String name, ArrayList<String> favLanguages, ProgrammerColor color) {
        this.ID = ID;
        this.name = name;
        this.favLanguages = favLanguages;
        this.color = color;
        this.position = 1;
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
        ArrayList<String> sortedLanguages = new ArrayList<>(favLanguages);
        Collections.sort(sortedLanguages);

        StringBuilder str = new StringBuilder();
        str.append(ID).append(" | ").append(name).append(" | ").append(position).append(" | ");

        for (String language : sortedLanguages) {
            str.append(language).append("; ");
        }

        str.setLength(str.length()-2);

        str.append(" | ").append("Em Jogo");

        return str.toString();

    }

}
