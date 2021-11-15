package pt.ulusofona.lp2.deisiGreatGame;

import javax.swing.*;
import java.util.*;

public class GameManager {

    static HashMap<Integer, ArrayList<Programmer>> map = new HashMap<Integer, ArrayList<Programmer>>();
    static HashMap<Integer, Programmer> programmers = new HashMap<>();
    static ArrayList<Integer> IDs;
    static int boardSize;
    static int turn;
    static int numOfPlayers;
    static int turnCount;


    public GameManager() {}

    public boolean createInitialBoard(String[][] playerInfo, int boardSize) {
        Set<String> availableColors = Set.of("PURPLE", "GREEN", "BROWN", "BLUE", "Purple", "Green", "Brown", "Blue", "purple", "green", "brown", "blue");
        ArrayList<String> usedColor = new ArrayList<>();
        ArrayList<String> usedIDs = new ArrayList<>();
        GameManager.boardSize = boardSize;

        //Checks if array is null or if player count is outside of limit.
        if (playerInfo == null || playerInfo.length > 4 || playerInfo.length < 2) {
            return false;
        }

        int minNumOfPositions = playerInfo.length * 2;

        if (boardSize < minNumOfPositions) {
            return false;
        }

        IDs = new ArrayList<>();
        numOfPlayers = playerInfo.length;

        for (int i = 1; i <= boardSize; i++) {
            ArrayList<Programmer> arrayList = new ArrayList<>();
            map.put(i, arrayList);              //pode dar erro
        }


        for (String[] programmer : playerInfo) {

            //Checks if name is null or empty.
            if (programmer[1] == null || programmer[1].equals("")) {
                return false;
            }

            //Checks if ID from player is positive number
            String regexNumPositive = "^[1-9]\\d*$";
            if (!programmer[0].matches(regexNumPositive)) {
                return false;
            }

            //Checks if ID has already been used. If not, adds the ID to usedIDs array.
            if (usedIDs.contains(programmer[0])) {
                return false;
            } else {
                usedIDs.add(programmer[0]);
            }


            //Checks if color is available.
            if (!availableColors.contains(programmer[3])) {
                return false;
            }

            //Checks if color has already been used. If not, adds the color to usedColor array.
            if (usedColor.contains(programmer[3])) {
                return false;
            } else {
                usedColor.add(programmer[3]);
            }

            int id = Integer.parseInt(programmer[0]);

            String[] splitLanguages = programmer[2].split(";");

            map.get(1).add(new Programmer(id, programmer[1], new ArrayList<>(Arrays.asList(splitLanguages)), stringToColor(programmer[3])));
            programmers.put(id, new Programmer(id, programmer[1], new ArrayList<>(Arrays.asList(splitLanguages)), stringToColor(programmer[3])));
            IDs.add(id);
        }

        Collections.sort(IDs);
        turn = IDs.get(0);

        return true;
    }


    public String getImagePng(int position) {

        if (position < 1 || position > boardSize) {
            return null;
        }

        if (position == boardSize) {
            return "glory.png";
        }

        if (map.get(position).isEmpty() || map.get(position) == null) {
            return "blank.png";
        } else {
            for (Programmer programmer : map.get(position)) {
                if (programmer.color.name().equals("Purple")) {
                    return "playerPurple.png";
                } else if (programmer.color.name().equals("Green")) {
                    return "playerGreen.png";
                } else if (programmer.color.name().equals("Brown")) {
                    return "playerBrown.png";
                } else if (programmer.color.name().equals("Blue")) {
                    return "playerBlue.png";
                }
            }
        }
        return null;
    }


    public ArrayList<Programmer> getProgrammers() {
        //ArrayList<Programmer> programmersAL = new ArrayList<>(programmers.values());
        ArrayList<Programmer> ALProgramming = new ArrayList<>();

        for (ArrayList<Programmer> array : map.values()) {
            ALProgramming.addAll(array);
        }

        return ALProgramming;
        //return new ArrayList<>(programmers.values());
    }


    public ArrayList<Programmer> getProgrammers(int position) {
        if (position < 1 || position > boardSize) {
            return null;
        }

        if (map.get(position) == null || map.get(position).isEmpty()) {
            return null;
        }

        return map.get(position);
    }

    public int getCurrentPlayerID() {

        return turn;
    }

    public boolean moveCurrentPlayer(int nrPositions) {
        if (nrPositions < 1 || nrPositions > 6 || map.isEmpty()) {
            return false;
        }

        int currentPosition = programmers.get(turn).position;

        if (currentPosition + nrPositions > boardSize) {
            int goBack = currentPosition + nrPositions - boardSize;
            int targetPosition = boardSize - goBack;

            if (!map.containsKey(targetPosition)) {
                return false;
            }

            programmers.get(turn).position = boardSize - goBack;
            map.get(currentPosition).removeIf(programmer -> programmer.getId() == turn);
            map.get(targetPosition).add(programmers.get(turn));

        } else {
            programmers.get(turn).position = currentPosition + nrPositions;
            map.get(currentPosition).removeIf(programmer -> programmer.getId() == turn);
            map.get(currentPosition + nrPositions).add(programmers.get(turn));
        }

        if (turnCount == numOfPlayers - 1) {
            turn = IDs.get(0);
            turnCount = 0;
        } else {
            turn = IDs.get(turnCount + 1);
            turnCount++;
        }

        return true;
    }

    public boolean gameIsOver() {
        if (map.get(boardSize) == null) {
            return false;
        }

        return !map.get(boardSize).isEmpty();
    }

    public ArrayList<String> getGameResults() {           ///////////////

        return new ArrayList<>();
    }

    public JPanel getAuthorsPanel() {                   ///////////////

        return null;
    }

    public ProgrammerColor stringToColor(String color) {
        if (color.equals("PURPLE") || color.equals("Purple") || color.equals("purple")) {
            return ProgrammerColor.PURPLE;
        } else if (color.equals("GREEN") || color.equals("Green") || color.equals("green")) {
            return ProgrammerColor.GREEN;
        } else if (color.equals("BROWN") || color.equals("Brown") || color.equals("brown")) {
            return ProgrammerColor.BROWN;
        }else if (color.equals("BLUE") || color.equals("Blue") || color.equals("blue")); {
            return ProgrammerColor.BLUE;
        }

    }

}
